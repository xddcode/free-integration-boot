package com.free.es.controller;

import com.free.common.utils.PageResult;
import com.free.common.utils.R;
import com.free.es.model.Article;
import com.free.es.model.vo.ArticleVO;
import com.free.es.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/article")
@RequiredArgsConstructor
public class EsController {

    private final ArticleService articleService;

    /**
     * 查询文章列表
     */
    @GetMapping({"", "/list"})
    public R findAll(ArticleVO articleVO) {
        PageResult<Article> list = articleService.findAll(articleVO);
        return R.succeed(list, "查询成功");
    }

    /**
     * 新增或修改文章信息
     */
    @PostMapping()
    public R saveOrUpdateArticle(Article article) {
        if (articleService.saveOrUpdateArticle(article)) {
            return R.succeed("操作成功");
        }
        return R.failed("操作失败");
    }


    /**
     * 根据id删除文章
     */
    @DeleteMapping("{/id}")
    public R delete(@PathVariable  Long id) {
        if (articleService.deleteArticle(id)) {
            return R.succeed("删除成功");
        }
        return R.failed("删除失败");
    }

}
