package com.leyou.item.serviceImpl;

import com.leyou.item.mapper.SpecGroupMapper;
import com.leyou.item.mapper.SpecParamMapper;
import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    @Autowired
    private SpecGroupMapper specGroupMapper;
    @Autowired
    private SpecParamMapper specParamMapper;

    @Override
    public List<SpecGroup> queryGroupsByCid(Long cid) {
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        return this.specGroupMapper.select(specGroup);
    }

    @Override
    public List<SpecParam> queryParams(Long gid, Long cid, Boolean generic, Boolean searching) {
        SpecParam record = new SpecParam();
        record.setGroupId(gid);
        record.setCid(cid);
        record.setGeneric(generic);
        record.setSearching(searching);
        return this.specParamMapper.select(record);
    }

    @Override
    public void saveSpecGroup(SpecGroup specGroup) {
        this.specGroupMapper.insert(specGroup);
    }

    @Override
    public void delectSpecGroupByCid(Long cid) {
        this.specGroupMapper.deleteByPrimaryKey(cid);
    }

    @Override
    public void updateSpecGroup(SpecGroup group) {
        this.specGroupMapper.updateByPrimaryKey(group);
    }

    @Override
    public void saveSpecParam(SpecParam param) {
        this.specParamMapper.insertSelective(param);
    }

    @Override
    public void delectSpecParamById(Long gid) {
        this.specParamMapper.deleteByPrimaryKey(gid);
    }

    @Override
    public void updateSpecParam(SpecParam param) {
        this.specParamMapper.updateByPrimaryKey(param);
    }
}
