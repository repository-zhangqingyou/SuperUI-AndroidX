package com.zqy.superhttp.response;

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

//    public static <T> PageModel<T> getPageModel(Long total, T data) {
//        PageModel<T> pageModel=new PageModel<>();
//        pageModel.setTotal(total);
//        pageModel.setData(data);
//        return pageModel;
//    }
}
