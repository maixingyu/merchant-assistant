package com.team.merchantassistant.common;

import lombok.*;

/**
 * @ClassName RecordResult
 * @Description 这是返回记账记录的格式，比如昨日，一周，一月，一年
 * @Author mai
 * @Date 2020/1/14 10:31
 **/
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class RecordResult {
    private String interval;
    private String time;
    private String pay;
}
