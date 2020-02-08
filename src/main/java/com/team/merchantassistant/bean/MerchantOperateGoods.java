package com.team.merchantassistant.bean;

import lombok.*;

/**
 * @ClassName operateGoods
 * @Description TODO
 * @Author mai
 * @Date 2020/1/16 20:00
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MerchantOperateGoods {
    private Integer id;
    private Integer quantity;
    private String date;
    private Integer flag;
    private String supplierName;
    private Integer gId;
}
