package com.zqy.supernet.response;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

/**
 * 作者: zhangqingyou
 * 时间: 2021/3/24
 * 描述:
 */
@Getter
@Setter
public class BaseResult<T> implements Serializable {
    public T data;

}
