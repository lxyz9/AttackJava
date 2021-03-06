package com.lxyz.cloud.orderservice.form;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author lbf
 * @date 2021/4/19
 */
@Slf4j
@Data
public class OrderForm {


    private String userId;

    private String  productId;


    private Integer productCount;

    private Integer score;

}
