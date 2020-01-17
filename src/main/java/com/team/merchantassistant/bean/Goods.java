package com.team.merchantassistant.bean;

import lombok.*;

import java.util.List;

/**
 * @ClassName Goods
 * @Description TODO
 * @Author mai
 * @Date 2020/1/16 18:34
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Goods {
    private Integer id;
    private String name;
    private Integer quantity;
    private String unit;
    private String openid;
    private List<OperateGoods> operateGoodsList;
}
