package com.example.client.controller;

import com.example.client.model.ArticleDO;
import com.example.client.service.ArticleService;
import com.example.client.util.ResponseVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jidongfang
 */
@CrossOrigin
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
  public ResponseVo saveArticle(@RequestBody ArticleDO articleDO){
    return articleService.insertSelective(articleDO);
  }

  /**
   * 查看状态正常的本人文章
   * @param articleDO
   * @return
   */
  @PostMapping("listArticle")
  public ResponseVo listArticle(@RequestBody ArticleDO articleDO){
    return articleService.getListArticle(articleDO);
  }

  /**
   * 修改内容
   * @param articleDO
   * @return
   */
  @PostMapping("updateArticle")
  public ResponseVo updateArticle(@RequestBody ArticleDO articleDO){
    return articleService.updateArticle(articleDO);
  }

  /**
   * 删除
   * @param articleDO
   * @return
   */
  @PostMapping("deleteArticle")
  public ResponseVo deleteArticle(@RequestBody ArticleDO articleDO){
    return articleService.deleteArticle(articleDO);
  }

  /**
   * 恢复
   * @param articleDO
   * @return
   */
  @PostMapping("recoveryArticle")
  public ResponseVo recoveryArticle(@RequestBody ArticleDO articleDO){
    return articleService.recoveryArticle(articleDO);
  }

  /**
   * 查看已删除列表
   * @param articleDO
   * @return
   */
  @PostMapping("recycleBinArticle")
  public ResponseVo recycleBinArticle(@RequestBody ArticleDO articleDO){
    return articleService.getRecycleBinArticle(articleDO);
  }

}
