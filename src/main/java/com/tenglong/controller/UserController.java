package com.tenglong.controller;

import com.tenglong.entity.Result;
import com.tenglong.entity.User;
import com.tenglong.exception.BusinessException;
import com.tenglong.sevice.UserService;
import lombok.RequiredArgsConstructor;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    @GetMapping("/login")
    public Result<User> login(String username,String password){
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        }catch (IncorrectCredentialsException e){
            throw new BusinessException("密码错误!");
        } catch (AuthenticationException e) {
            throw new BusinessException("认证错误!");
        }
        User principal = (User)subject.getPrincipal();
        return  new Result(true,200,"登录成功",principal);
    }
    @PostMapping("/register")
    public Result<User> register(@RequestBody User user){
        User result =userService.register(user);
        return  new Result(true,200,"注册成功",result);
    }
    @GetMapping("/logout")
    public Result  logout(){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return new Result(true,200,"退出登录成功",null);
    }
    @GetMapping("/toUpdateUser")
    public Result<User> toUpdateUser(String username){
        User user = userService.findPasswordByName(username);
        return  new Result<>(true,200,"查询成功",user);
    }
    @GetMapping("/updateUser")
    public Result updateUser(String username,String oldPassword,String newPassword){
        userService.updateUser(username,oldPassword,newPassword);
        return new Result(true,200,"修改成功",null);
    }
}
