package com.example.client.controller;

import com.example.client.model.ArticleDO;
import com.example.client.service.ArticleService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jidongfang
 */
@RestController
@RequestMapping("article")
public class ArticleController {
  @Autowired
  private ArticleService articleService;

  /**
   *新增文章
   * @param articleDO
   * @return
   */
  @PostMapping("saveArticle")
  public Map<String,Object> saveArticle(@RequestBody ArticleDO articleDO){
    return articleService.insertSelective(articleDO);
  }

  /**
   * 查看状态正常的本人文章
   * @param articleDO
   * @return
   */
  @PostMapping("listArticle")
  public Map<String,Object> listArticle(@RequestBody ArticleDO articleDO){
    return articleService.getListArticle(articleDO);
  }

  /**
   * 修改内容
   * @param articleDO
   * @return
   */
  @PostMapping("updateArticle")
  public Map<String,Object> updateArticle(@RequestBody ArticleDO articleDO){
    return articleService.updateArticle(articleDO);
  }

  /**
   * 删除
   * @param articleDO
   * @return
   */
  @PostMapping("deleteArticle")
  public Map<String,Object> deleteArticle(@RequestBody ArticleDO articleDO){
    return articleService.deleteArticle(articleDO);
  }

  /**
   * 恢复
   * @param articleDO
   * @return
   */
  @PostMapping("recoveryArticle")
  public Map<String,Object> recoveryArticle(@RequestBody ArticleDO articleDO){
    return articleService.recoveryArticle(articleDO);
  }

  /**
   * 查看已删除列表
   * @param articleDO
   * @return
   */
  @PostMapping("recycleBinArticle")
  public Map<String,Object> recycleBinArticle(@RequestBody ArticleDO articleDO){
    return articleService.getRecycleBinArticle(articleDO);
  }

}
