package com.tenglong.controller;

import com.tenglong.entity.Encyclopedia;
import com.tenglong.entity.Result;
import com.tenglong.sevice.EncyclopediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/encyclopedia")
public class EncyclopediaController {
    private final EncyclopediaService encyclopediaService;
    @GetMapping("/findAll")
    public Result<Encyclopedia> findAll(){
        List<Encyclopedia> list=encyclopediaService.findAll();
        return new Result<>(true,200,"查询成功",list);
    }

    @GetMapping("/findByName")
    public Result<Encyclopedia> findByName(String name){
        Encyclopedia list=encyclopediaService.findByName(name);
        return new Result<>(true,200,"查询成功",list);
    }
}
