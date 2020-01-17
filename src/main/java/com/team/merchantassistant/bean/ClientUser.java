package com.team.merchantassistant.bean;

import lombok.*;

/**
 * @ClassName ClientUser
 * @Description TODO
 * @Author mai
 * @Date 2020/1/13 12:43
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ClientUser {
    private Integer id;
    private String openId;
    private Integer wId;
}
