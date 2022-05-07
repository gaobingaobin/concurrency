package com.example.concurrency.repository;

import com.example.concurrency.model.BlogModel;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Description
 * @Author gaobin
 * @Date 2021-11-29 11:00
 */
public interface BlogRepository extends ElasticsearchRepository<BlogModel,String> {

    List<BlogModel> findByTitleLike(String keyword);

    @Query("{\"match_phrase\":{\"title\":\"?0\"}}")
    List<BlogModel> findByTitleCustom(String keyword);
}
