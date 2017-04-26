package com.taotao.service;

import com.taotao.commom.EuDataJson;

/**
 * Created by winsion on 2017/4/24.
 */
public interface ContentService {

    EuDataJson getcontentList(Long categoryId,Integer page,Integer rows) throws Exception;
}
