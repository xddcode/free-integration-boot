package com.free.es.controller;

import com.free.common.utils.R;
import com.free.es.model.Article;
import com.free.es.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/es")
@RequiredArgsConstructor
public class EsController {

    private final ArticleService articleService;

    @GetMapping("findAll")
    public Iterable<Article> findAll() {

        return articleService.findAll();
    }

    @RequestMapping("save")
    public R save() {
        List<Article> list = new ArrayList<>();
        articleService.save(list);
        return R.succeed("插入成功");
    }

    @GetMapping("findByName")
    public List<Article> findByName(String author) {
        return articleService.findByAuthor(author);
    }

    @GetMapping("findByNameOrDesc")
    public List<Article> findByNameOrDesc(String text) {
        return articleService.findByTitleOrContent(text);
    }

}
