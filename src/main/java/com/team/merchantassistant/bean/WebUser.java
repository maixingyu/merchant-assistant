package com.team.merchantassistant.bean;

import lombok.*;

/**
 * @ClassName AdminUser
 * @Description TODO
 * @Author mai
 * @Date 2020/1/13 15:15
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class WebUser {
    private Integer id;
    private String username;
    private String password;
    private Integer cId;
}
