package com.leyou.item.api;

import com.leyou.item.pojo.Brand;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


public interface BrandApi {

    @GetMapping("brand/cid/{id}")
    Brand queryBrandsByCid(@PathVariable("id") Long id);
}