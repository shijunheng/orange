package com.leyou.item.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.item.pojo.Brand;
import com.leyou.item.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandsByPage(
            @RequestParam(value = "key", required = false)String key,
            @RequestParam(value = "page", defaultValue = "1")Integer page,
            @RequestParam(value = "rows", defaultValue = "5")Integer rows,
            @RequestParam(value = "sortBy", required = false)String sortBy,
            @RequestParam(value = "desc", required = false)Boolean desc
    ){
        PageResult<Brand> result = this.brandService.queryBrandsByPage(key, page, rows, sortBy, desc);
        return ResponseEntity.ok(result);
    }
   @PostMapping
   public ResponseEntity<Void> saveBrand(@ModelAttribute Brand brand, @RequestParam("cids") List<Long> cids){
       this.brandService.saveBrand(brand, cids);
       return ResponseEntity.status(HttpStatus.CREATED).build();
   }
   @PutMapping
   public ResponseEntity<Void> updateBrand(@ModelAttribute Brand brand, @RequestParam("cids") List<Long> cids){
       this.brandService.updateBrand(brand, cids);
       return ResponseEntity.status(HttpStatus.CREATED).build();
   }
    @GetMapping("bid/{bid}")
    public ResponseEntity<Void> delectByBrandId(@PathVariable("bid") Long bid){
        int result = this.brandService.delectByBrandId(bid);
        if (result == 0 ){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @GetMapping("cid/{cid}")
    public ResponseEntity<List<Brand>> queryBrandsByCid(@PathVariable("cid")Long cid){
        List<Brand> brand = this.brandService.queryBrandsByCid(cid);
        if(CollectionUtils.isEmpty(brand)){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(brand);
    }
}
