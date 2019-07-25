package com.leyou.item.service;

import com.leyou.item.pojo.Category;

import java.util.List;

public interface CategoryService {
  public List<Category> queryCategoriesByPid(Long pid);

  public List<Category> queryByBrandId(Long bid);

  public List<String> queryNamesByIds(List<Long> asList);
}
