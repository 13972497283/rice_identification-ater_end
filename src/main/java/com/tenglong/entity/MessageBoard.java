package com.tenglong.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@TableName("message_board")
@Data
@NoArgsConstructor
public class MessageBoard  implements Serializable {
    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;

    private Date time;

    private String content;

    private String reply;

    private String theme;

    private String isreply;
}
