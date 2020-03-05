package com.team.merchantassistant.bean;

import lombok.*;

import java.math.BigDecimal;

/**
 * @ClassName MerchantDiscount
 * @Description TODO
 * @Author mai
 * @Date 2020/3/4 16:15
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MerchantDiscount {
    private Integer id;
    private BigDecimal money;
    private String openid;
    private Integer mId;
}
