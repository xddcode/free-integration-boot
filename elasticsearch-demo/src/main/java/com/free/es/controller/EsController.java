package com.free.es.controller;

import com.free.common.utils.R;
import com.free.es.model.Article;
import com.free.es.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/es")
@AllArgsConstructor
public class EsController {

    private ArticleService articleService;

    @RequestMapping("findAll")
    public R findAll() {
        List<Article> list = articleService.findAll();
        return R.succeed(list, "查询成功");
    }

    @RequestMapping("findByAuthor")
    public R findByAuthor(String text) {
        List<Article> list = articleService.findByAuthor(text);
        return R.succeed(list, "查询成功");
    }

    @RequestMapping("findMultiMatchQuery")
    public R findMultiMatchQuery(String text) {
        List<Article> list = articleService.findMultiMatchQuery(text);
        return R.succeed(list, "查询成功");
    }

    @RequestMapping("findByConditions")
    public R findByConditions(String text) {
        List<Article> list = articleService.findByConditions(text);
        return R.succeed(list, "查询成功");
    }

    @RequestMapping("save")
    public R save() {
        articleService.saveBatch();
        return R.succeed("插入成功");
    }

    @RequestMapping("delIndex")
    public R delIndex() {
        articleService.deleteIndex();
        return R.succeed("删除成功");
    }
}
