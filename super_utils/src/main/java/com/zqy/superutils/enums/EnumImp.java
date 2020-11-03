package com.zqy.superutils.enums;

import java.util.List;

/**
 * 作者: zhangqingyou
 * 时间: 2020/6/17 9:41
 * 描述:
 */
public interface EnumImp<K, V> {

    K getKey(V v);

    V getValue(K k);

    List<K> getKey();

    List<V> getValue();

    int indexOfK(K k);

    int indexOfV(V v);

    int size();


}
