package com.leyou.search.client;

import com.leyou.item.api.GoodsApi;
import com.leyou.item.pojo.Spu;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "item-service")
public interface GoodsClient extends GoodsApi {

}