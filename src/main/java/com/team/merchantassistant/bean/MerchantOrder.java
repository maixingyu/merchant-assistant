package com.team.merchantassistant.bean;

import lombok.*;

import java.math.BigDecimal;

/**
 * @ClassName MerchantOrder
 * @Description TODO
 * @Author mai
 * @Date 2020/1/24 20:47
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MerchantOrder {
    private Integer id;
    private String orderNumber;
    private BigDecimal money;
    private String time;
    private String openid;
    private Integer mId;
}
