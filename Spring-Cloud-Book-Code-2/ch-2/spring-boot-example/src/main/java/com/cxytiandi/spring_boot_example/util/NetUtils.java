package com.cxytiandi.spring_boot_example.util;


import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author yinjihuan
 * @create 2017-11-21 15:48
 **/
public class NetUtils {

    /***
     *  true:already in using  false:not using
     * @param port
     */
    public static boolean isLocalPortUsing(int port) {
        return isPortUsing("127.0.0.1", port);
    }

    /***
     *  true:already in using  false:not using
     * @param host
     * @param port
     * @throws UnknownHostException
     */
    public static boolean isPortUsing(String host, int port) {
        boolean flag = false;
        try {
            InetAddress theAddress = InetAddress.getByName(host);
            Socket socket = new Socket(theAddress, port);
            socket.close();
            //如果可以链接，说明端口已被其他进程使用;因为空端口无法被链接，
            flag = true;
        } catch (Exception e) {
        }
        return flag;
    }

}
