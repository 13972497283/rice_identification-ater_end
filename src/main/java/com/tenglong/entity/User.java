package com.tenglong.entity;


import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@TableName("user")
@Data
@NoArgsConstructor
public class User  implements Serializable {

    @TableId(value = "nickname")
    private String nickname;

    private String password;

    private String salt;
}
