package com.taotao.service;

import com.taotao.commom.EuTreeNode;

import java.util.List;

/**
 * Created by winsion on 2017/4/15.
 */
public interface ItemCatService {
    List<EuTreeNode> getCastList(long parentId);
}
