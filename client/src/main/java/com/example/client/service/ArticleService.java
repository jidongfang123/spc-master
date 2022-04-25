package com.example.client.service;

import com.example.client.model.ArticleDO;
import java.util.Map;

/**
 * @author jidongfang
 */
public interface ArticleService {

  Map<String, Object> insertSelective(ArticleDO articleDO);

  Map<String, Object> getListArticle(ArticleDO articleDO);

  Map<String, Object> updateArticle(ArticleDO articleDO);

  Map<String, Object> deleteArticle(ArticleDO articleDO);

  Map<String, Object> recoveryArticle(ArticleDO articleDO);

  Map<String, Object> getRecycleBinArticle(ArticleDO articleDO);
}
