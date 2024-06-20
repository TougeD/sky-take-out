package com.sky.controller.admin;

import com.sky.dto.CategoryDTO;
import com.sky.dto.CategoryPageQueryDTO;
import com.sky.entity.Category;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 小小低头哥
 * @version v1.0
 * @api
 * @since 2024年 月 日
 */

@RestController
@Slf4j
@Api(tags = "分类相关接口")
@RequestMapping("admin/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PutMapping
    @ApiOperation(value = "修改分类")
    public Result update(@RequestBody CategoryDTO categoryDTO){
        categoryService.update(categoryDTO);
        return Result.success();
    }

    @DeleteMapping()
    @ApiOperation(value = "根据id删除分类")
    public Result delete(@RequestParam(value = "id") Long id){
        categoryService.deleteById(id);
        return Result.success();
    }

    @GetMapping("list")
    @ApiOperation(value = "根据类型查询分类")
    public Result<List<Category>> list(Integer type) {  //@RequestParam(value = "type",required = false)
        List<Category> categoryList = categoryService.list(type);
        return Result.success(categoryList);
    }

    @GetMapping("page")
    @ApiOperation(value = "分类分页查询")
    public Result<PageResult> page(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageResult pageResult = categoryService.pageQuery(categoryPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 启用禁用分类
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("status/{status}")
    @ApiOperation(value = "启用、禁用分类")
    public Result status(@PathVariable(value = "status") Integer status,
                         @RequestParam(value = "id") Long id) {
        categoryService.updateStatus(status, id);
        return Result.success();
    }

    /**
     * 新增分类
     *
     * @param categoryDTO
     * @return
     */
    @PostMapping()
    @ApiOperation(value = "新增分类")
    public Result addCategory(@RequestBody CategoryDTO categoryDTO) {
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }
}
