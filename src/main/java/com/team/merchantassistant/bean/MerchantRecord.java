package com.team.merchantassistant.bean;

import lombok.*;

import java.math.BigDecimal;

/**
 * @ClassName Record
 * @Description TODO
 * @Author mai
 * @Date 2020/1/13 23:03
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MerchantRecord {
    private Integer id;
    private String category;
    private String typename;
    private BigDecimal money;
    private String date;
    private String time;
    private String remarks;
    private String openid;
}
