package com.example.shardingreadwrite.service;


import com.cxytiandi.jdbc.EntityService;
import com.example.shardingreadwrite.po.User;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserServiceImpl extends EntityService<User> implements UserService {

    @Override
    public List<User> list() {
        // 强制路由主库
//		HintManager.getInstance().setMasterRouteOnly();
        return super.list();
    }

    @Override
    public Long add(User user) {
        return (Long) super.save(user);
    }
}
