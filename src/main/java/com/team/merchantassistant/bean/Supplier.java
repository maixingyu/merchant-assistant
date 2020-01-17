package com.team.merchantassistant.bean;

import lombok.*;

/**
 * @ClassName Supplier
 * @Description TODO
 * @Author mai
 * @Date 2020/1/15 19:12
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Supplier {
    private Integer id;
    private String name;
    private String tel;
    private String address;
    private String openid;
}
