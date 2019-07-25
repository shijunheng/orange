package com.leyou.item.serviceImpl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leyou.common.pojo.PageResult;
import com.leyou.item.mapper.BrandMapper;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandMapper brandMapper;

    @Override
    public PageResult<Brand> queryBrandsByPage(String key, Integer page, Integer rows, String sortBy, Boolean desc) {
        Example example = new Example(Brand.class);
        Example.Criteria criteria = example.createCriteria();
        // 根据name模糊查询，或者根据首字母查询
        if (StringUtils.isNotBlank(key)) {
            criteria.andLike("name", "%" + key + "%").orEqualTo("letter", key);
        }

        // 添加分页条件
        PageHelper.startPage(page, rows);

        // 添加排序条件
        if (StringUtils.isNotBlank(sortBy)) {
            example.setOrderByClause(sortBy + " " + (desc ? "desc" : "asc"));
        }

        List<Brand> brands = this.brandMapper.selectByExample(example);
        // 包装成pageInfo
        PageInfo<Brand> pageInfo = new PageInfo<>(brands);
        // 包装成分页结果集返回
        return new PageResult<>(pageInfo.getTotal(), pageInfo.getList());
    }

    @Override
    public void saveBrand(Brand brand, List<Long> cids) {
        // 先新增brand
        this.brandMapper.insertSelective(brand);

        // 在新增中间表
        cids.forEach(cid -> {
            this.brandMapper.insertBrandAndCategory(cid, brand.getId());
        });
    }

    @Override
    public int delectByBrandId(Long bid) {
        this.brandMapper.deleteBrandAndCategory(bid);
        return this.brandMapper.deleteByPrimaryKey(bid);
    }

    @Override
    public void updateBrand(Brand brand, List<Long> cids) {
        // 先修改brand
        this.brandMapper.updateByPrimaryKey(brand);
        // 删除中间表
        this.brandMapper.deleteBrandAndCategory(brand.getId());
        // 在新增中间表
        cids.forEach(cid -> {
            this.brandMapper.insertBrandAndCategory(cid, brand.getId());
        });
    }

    @Override
    public List<Brand> queryBrandsByCid(Long cid) {
        return this.brandMapper.selectBrandByCid(cid);
    }

}
