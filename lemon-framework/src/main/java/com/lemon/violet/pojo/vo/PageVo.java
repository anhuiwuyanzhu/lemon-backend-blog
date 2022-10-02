package com.lemon.violet.pojo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageVo <T>{
    //数据
    private T rows;
    //数据总条数
    private Long total;
}
