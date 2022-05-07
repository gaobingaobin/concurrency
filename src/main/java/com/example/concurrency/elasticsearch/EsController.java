package com.example.concurrency.elasticsearch;

import com.example.concurrency.model.BlogModel;
import com.example.concurrency.repository.BlogRepository;
import com.example.concurrency.result.Result;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description
 * @Author gaobin
 * @Date 2021-11-23 11:20
 */
@RestController
@RequestMapping("/blog")
public class EsController {

    @Autowired
    private BlogRepository blogRepository;


    @PostMapping("/add")
    public Result add(@RequestBody BlogModel blogModel) {
        blogRepository.save(blogModel);
        return Result.success(null);
    }

    @GetMapping("/get/{id}")
    public Result getById(@PathVariable String id){
        if(StringUtils.isEmpty(id)){
            return Result.error();
        }
        Optional<BlogModel> blogModelOptional = blogRepository.findById(id);
        if (blogModelOptional.isPresent()) {
            BlogModel blogModel = blogModelOptional.get();
            return Result.success(blogModel);
        }
        return Result.error();

    }

    @GetMapping("/getAll")
    public Result getAll() {
        Iterable<BlogModel> iterable = blogRepository.findAll();
        List<BlogModel> list = new ArrayList<>();
        iterable.forEach(list::add);
        return Result.success(list);
    }

    @PostMapping("/update")
    public Result updateById(@RequestBody BlogModel blogModel) {
        String id = blogModel.getId();
        if (StringUtils.isEmpty(id)){
            return Result.error();

        }
        blogRepository.save(blogModel);
        return Result.success(null);
    }

    @DeleteMapping("/delete/{id}")
    public Result deleteById(@PathVariable String id) {
        if (StringUtils.isEmpty(id)){
            return Result.error();
        }
        blogRepository.deleteById(id);
        return Result.success(null);
    }

    @DeleteMapping("/deleteAll")
    public Result deleteById() {
        blogRepository.deleteAll();
        return Result.success(null);
    }

    @GetMapping("/rep/search/title")
    public Result repSearchTitle(String keyword) {
        if (StringUtils.isEmpty(keyword)){

            return Result.error();
        }
        return Result.success(blogRepository.findByTitleLike(keyword));
    }

    @GetMapping("/rep/search/title/custom")
    public Result repSearchTitleCustom(String keyword) {
        if (StringUtils.isEmpty(keyword)) {
            return Result.error();
        }
        return Result.success(blogRepository.findByTitleCustom(keyword));
    }
}
