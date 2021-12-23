package com.zqy.supernet.response;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 作者: zhangqingyou
 * 时间: 2021/6/3 11:05
 * 描述:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
//@ApiModel("翻页返回数据Model")
public class PageModel<T> implements Serializable {

   // @ApiModelProperty(value = "总数", example = "99999")
    private Long total;
    //@ApiModelProperty(value = "数据")
    private T data;

//    public static <D> PageModel<D> getPageModel(Long total, D data) {
//        return new PageModel<D>(total, data);
//    }
}
