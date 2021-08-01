package com.tenglong.sevice;

import com.baomidou.mybatisplus.extension.service.IService;
import com.tenglong.entity.User;
import com.tenglong.exception.BusinessException;
import org.springframework.stereotype.Service;

public interface UserService extends IService<User> {
    User findPasswordByName(String username);

    User register(User user) throws BusinessException;

    void updateUser(String username,String oldPassword,String newPassword);
}
