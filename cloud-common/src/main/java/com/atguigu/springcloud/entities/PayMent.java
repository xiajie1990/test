package com.atguigu.springcloud.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @ClassName: PayMent
 * @Author: xiajie
 * Date: 2020/05/08 15:03:35
 * project name: cloud
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PayMent implements Serializable {

    private Long id;
    private String payMent;
}
