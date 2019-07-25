package com.leyou.item.controller;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.item.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * 根据id查询分组信息
     * @param cid
     * @return
     */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> queryGroupsByCid (@PathVariable("cid")Long cid ){
       List<SpecGroup> list = this.specificationService.queryGroupsByCid(cid);
       if (CollectionUtils.isEmpty(list)){
           return ResponseEntity.badRequest().build();
       }
       return ResponseEntity.ok(list);
    }

    /**
     * 根据id查询参数规格
     * @param gid
     * @return
     */
    @GetMapping("params")
    public ResponseEntity<List<SpecParam>> queryParams(
            @RequestParam(value = "gid", required = false)Long gid,
            @RequestParam(value = "cid", required = false)Long cid,
            @RequestParam(value = "generic", required = false)Boolean generic,
            @RequestParam(value = "searching", required = false)Boolean searching
    ){
        List<SpecParam> params = this.specificationService.queryParams(gid, cid, generic, searching);
        return ResponseEntity.ok(params);
    }

    /**
     * 新增分组
     */
    @PostMapping("group")
    public ResponseEntity<Void> saveSpecGroup(@RequestBody SpecGroup group){
        this.specificationService.saveSpecGroup(group);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("group/{cid}")
    public ResponseEntity<Void> delectSpecGroupByCid(@PathVariable("cid")Long cid){
        this.specificationService.delectSpecGroupByCid(cid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("group")
    public ResponseEntity<Void> updateSpecGroup(@RequestBody SpecGroup group){
        this.specificationService.updateSpecGroup(group);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    /**
     * 新增分组
     */
    @PostMapping("param")
    public ResponseEntity<Void> saveSpecParam(@RequestBody SpecParam param){
        this.specificationService.saveSpecParam(param);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @DeleteMapping("param/{gid}")
    public ResponseEntity<Void> delectSpecParamById(@PathVariable("gid")Long gid){
        this.specificationService.delectSpecParamById(gid);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PutMapping("param")
    public ResponseEntity<Void> updateSpecParam(@RequestBody SpecParam param){
        this.specificationService.updateSpecParam(param);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
