package com.leyou.item.service;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;

import java.util.List;

public interface SpecificationService {
    List<SpecGroup> queryGroupsByCid(Long cid);

    void saveSpecGroup(SpecGroup specGroup);

    void delectSpecGroupByCid(Long cid);

    void updateSpecGroup(SpecGroup specGroup);

    void saveSpecParam(SpecParam param);

    void delectSpecParamById(Long gid);

    void updateSpecParam(SpecParam param);

    List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching);
}
