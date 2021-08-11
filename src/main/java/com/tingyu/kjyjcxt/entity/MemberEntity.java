package com.tingyu.kjyjcxt.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author essionshy
 * @Create 2021/5/24 23:01
 * @Version kjyjcxt
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("tb_member")
public class MemberEntity {
    private String id;
    private String name;
    private int age;
}
