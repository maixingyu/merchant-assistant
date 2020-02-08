package com.team.merchantassistant.bean;

import lombok.*;

/**
 * @ClassName MerchantVip
 * @Description 商户地vip客户
 * @Author mai
 * @Date 2020/1/23 18:30
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MerchantVip {
    private Integer id;
    private String name;
    private int age;
    private String sex;
    private String openid;
    private Integer mId;
}
