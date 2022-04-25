package com.example.client.service.impl;

import com.example.client.mapper.ArticleMapper;
import com.example.client.model.ArticleDO;
import com.example.client.model.ArticleDOExample;
import com.example.client.service.ArticleService;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * @author jidongfang
 */
@Service
public class ArticleServiceImpl implements ArticleService {
  @Resource
  private ArticleMapper articleMapper;
  @Override
  public Map<String, Object> insertSelective(ArticleDO articleDO) {
    Map<String, Object> map = new HashMap<>(16);
    articleDO.setCerateTime(new Date());
    articleDO.setUpdateTime(new Date());
     int falg = articleMapper.insertSelective(articleDO);
     if (falg>0){
       map.put("falg",true);
       map.put("msg","成功");
       return map;
     }
    throw new RuntimeException("入库失败");
  }

  @Override
  public Map<String, Object> getListArticle(ArticleDO articleDO) {
    Map<String, Object> map = new HashMap<>(16);
    ArticleDOExample example = new ArticleDOExample();
    example.createCriteria().andCreateUseridEqualTo(articleDO.getCreateUserid()).andFalgEqualTo(1);
    try {
      List<ArticleDO> articleDOS = articleMapper.selectByExample(example);
      map.put("data",articleDOS);
      map.put("falg",true);
      map.put("msg","查询成功");
    }catch (Exception e){
      map.put("falg",false);
    }
    return map;
  }

  @Override
  public Map<String, Object> updateArticle(ArticleDO articleDO) {
    Map<String, Object> map = new HashMap<>();
    articleDO.setUpdateTime(new Date());
    final int i = articleMapper.updateByPrimaryKeySelective(articleDO);
    map.put("falg",true);
    map.put("msg","修改成功");
    return map;
  }

  @Override
  public Map<String, Object> deleteArticle(ArticleDO articleDO) {
    Map<String, Object> map = new HashMap<>();
    articleDO.setFalg(0);
    articleMapper.updateByPrimaryKeySelective(articleDO);
    map.put("falg",true);
    map.put("msg","删除成功");
    return map;
  }

  @Override
  public Map<String, Object> recoveryArticle(ArticleDO articleDO) {
    Map<String, Object> map = new HashMap<>();
    articleDO.setFalg(1);
    articleMapper.updateByPrimaryKeySelective(articleDO);
    map.put("falg",true);
    map.put("msg","恢复成功");
    return map;
  }

  @Override
  public Map<String, Object> getRecycleBinArticle(ArticleDO articleDO) {
    Map<String, Object> map = new HashMap<>();
    ArticleDOExample example = new ArticleDOExample();
    example.createCriteria().andCreateUseridEqualTo(articleDO.getCreateUserid()).andFalgEqualTo(0);
    try {
      List<ArticleDO> articleDOS = articleMapper.selectByExample(example);
      map.put("data",articleDOS);
      map.put("falg",true);
      map.put("msg","查询成功");
    }catch (Exception e){
      map.put("falg",false);
    }
    return map;
  }


}
