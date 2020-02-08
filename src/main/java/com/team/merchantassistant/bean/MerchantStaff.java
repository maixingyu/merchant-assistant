package com.team.merchantassistant.bean;

import lombok.*;

/**
 * @ClassName Staff
 * @Description TODO
 * @Author mai
 * @Date 2020/1/14 19:37
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class MerchantStaff {
    private Integer id;
    private String avatar;
    private String name;
    private String sex;
    private String tel;
    private String idCard;
    private String address;
    private String remarks;
    private String openid;
}
