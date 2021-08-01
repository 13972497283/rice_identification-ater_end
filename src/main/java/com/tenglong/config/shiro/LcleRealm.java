package com.tenglong.config.shiro;

import com.tenglong.entity.User;
import com.tenglong.sevice.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class LcleRealm extends AuthorizingRealm {

    @Autowired
    private  UserService userService;

    //执行授权逻辑
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    //执行认证逻辑，执行login()方法以后，会进入到这里，进行用户名密码的比对
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {

        //执行认证逻辑
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //获取密码
        User user = userService.findPasswordByName(token.getUsername());
        if(null==user){
            throw new UnknownAccountException();
        }
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(user.getSalt()), "");
    }
}