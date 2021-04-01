package com.free.es.controller;

import com.free.common.constant.EsIndexConstant;
import com.free.common.utils.R;
import com.free.es.model.Article;
import com.free.es.utils.EsUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/es")
@RequiredArgsConstructor
public class IndexController {

    private final EsUtil esUtil;

    /**
     * 创建索引库
     */
    @RequestMapping("createIndex")
    public R createIndex() {
        try {
            esUtil.createIndexIfNotExist(Article.class);
            return R.succeed("创建成功");
        } catch (Exception e) {
            return R.failed("创建失败");
        }
    }

    /**
     * 删除索引库
     */
    @RequestMapping("delIndex")
    public R delIndex() {
        try {
            esUtil.delIndex(EsIndexConstant.ARTICLE_INDEX);
            return R.succeed("删除成功");
        } catch (Exception e) {
            return R.failed("删除失败");
        }
    }
}
