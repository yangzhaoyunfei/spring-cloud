package com.fangjia.mqtask;

import com.fangjia.mqclient.TransactionMqRemoteClient;
import com.fangjia.mqclient.dto.TransactionMessage;
import org.apache.commons.lang.time.DateFormatUtils;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

@Service
public class ProcessMessageTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProcessMessageTask.class);

    @Autowired
    private TransactionMqRemoteClient transactionMqRemoteClient;

    @Autowired
    private Producer producer;

    @Autowired
    private RedissonClient redisson;

    private ExecutorService fixedThreadPool = Executors.newFixedThreadPool(10);

    ////用semaphore控制处理速度
    private Semaphore semaphore = new Semaphore(20);

    public void start() {
        Thread th = new Thread(() -> {
            while (true) {
                final RLock lock = redisson.getLock("transaction-mq-task");
                try {
                    lock.lock();
                    System.out.println("开始发送消息:" + DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
                    int sleepTime = process();
                    if (sleepTime > 0) {
                        Thread.sleep(sleepTime);
                    }
                } catch (Exception e) {
                    LOGGER.error("", e);
                } finally {
                    lock.unlock();
                }
            }
        });
        th.start();
    }

    private int process() throws Exception {
        //默认执行完之后等等10秒
        int sleepTime = 10000;
        List<TransactionMessage> messageList = transactionMqRemoteClient.findByWatingMessage(5000);
        if (messageList.size() == 5000) {
            sleepTime = 0;
        }

        // 保证一批数据都处理完之后再执行之后的逻辑
        final CountDownLatch latch = new CountDownLatch(messageList.size());

        //用线程池去执行
        for (final TransactionMessage message : messageList) {
            //用semaphore控制处理速度
            semaphore.acquire();
            fixedThreadPool.execute(() -> {
                try {
                    doProcess(message);
                } catch (Exception e) {
                    LOGGER.error("", e);
                } finally {
                    semaphore.release();
                    latch.countDown();
                }
            });
        }

        //本批数据都处理完之后再执行之后的逻辑
        latch.await();
        return sleepTime;
    }

    private void doProcess(TransactionMessage message) {
        //检查此消息是否满足死亡条件
        if (message.getSendCount() > message.getDieCount()) {
            transactionMqRemoteClient.confirmDieMessage(message.getId());
            return;
        }

        //距离上次发送时间超过一分钟才继续发送
        long lastSendTime = 0;
        if (message.getSendDate() != null) {
            lastSendTime = message.getSendDate().getTime();
        }
        if (System.currentTimeMillis() - lastSendTime > 60000) {
            System.out.println("发送具体消息：" + message.getId());

            //向MQ发送消息
            MessageDto messageDto = new MessageDto();
            messageDto.setMessageId(message.getId());
            messageDto.setMessage(message.getMessage());
            producer.send(message.getQueue(), JsonUtils.toJson(messageDto));

            //修改消息发送次数以及最近发送时间
            transactionMqRemoteClient.incrSendCount(message.getId(), DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));

        }
    }
}
