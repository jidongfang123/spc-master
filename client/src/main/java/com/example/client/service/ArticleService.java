package com.example.client.service;

import com.example.client.model.ArticleDO;
import com.example.client.util.ResponseVo;

/**
 * @author jidongfang
 */
public interface ArticleService {

  ResponseVo insertSelective(ArticleDO articleDO);

  ResponseVo getListArticle(ArticleDO articleDO);

  ResponseVo updateArticle(ArticleDO articleDO);

  ResponseVo deleteArticle(ArticleDO articleDO);

  ResponseVo recoveryArticle(ArticleDO articleDO);

  ResponseVo getRecycleBinArticle(ArticleDO articleDO);
}
