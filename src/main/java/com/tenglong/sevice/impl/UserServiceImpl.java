package com.tenglong.sevice.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.tenglong.Util.EncryptionUtil;
import com.tenglong.entity.User;
import com.tenglong.exception.BusinessException;
import com.tenglong.mapper.UserMapper;
import com.tenglong.sevice.UserService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Override
    public User findPasswordByName(String username) {
        return baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getNickname, username));
    }

    @Override
    public User register(User user) {
        User t = baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getNickname, user.getNickname()));
        if(t!=null) throw new BusinessException("用户名已存在");
        EncryptionUtil encryptionUtil=new EncryptionUtil();
        Map<String, String> map = encryptionUtil.encryption(user.getPassword());
        user.setPassword(map.get("password"));
        user.setSalt(map.get("salt"));
        baseMapper.insert(user);
        return user;
    }

    @Override
    public void updateUser(String username,String oldPassword,String newPassword) {
        User t = baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getNickname,username));
        if(t!=null&&!t.getPassword().equals(oldPassword)) throw new BusinessException("旧密码错误");
        if(oldPassword.equals(newPassword)) throw  new BusinessException("新旧密码不能一样");
        User old = baseMapper.selectOne(new LambdaQueryWrapper<User>().eq(User::getNickname, username));
        if(!old.getPassword().equals(oldPassword)) throw new BusinessException("旧密码错误!");
        EncryptionUtil encryptionUtil=new EncryptionUtil();
        Map<String, String> map = encryptionUtil.encryption(newPassword);
        User user =new User();
        user.setNickname(username);
        user.setPassword(map.get("password"));
        user.setSalt(map.get("salt"));
        SecurityUtils.getSubject().logout();
        baseMapper.updateById(user);
    }
}
