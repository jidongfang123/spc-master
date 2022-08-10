package com.example.client.service.impl;

import com.example.client.enums.Message;
import com.example.client.mapper.ArticleMapper;
import com.example.client.model.ArticleDO;
import com.example.client.model.ArticleDOExample;
import com.example.client.service.ArticleService;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import com.example.client.util.ResponseVo;
import org.springframework.stereotype.Service;

/**
 * @author jidongfang
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleMapper articleMapper;

    @Override
    public ResponseVo insertSelective(ArticleDO articleDO) {
        ResponseVo responseVo = new ResponseVo();
        articleDO.setCerateTime(new Date());
        articleDO.setUpdateTime(new Date());
        int falg = articleMapper.insertOrUpdateSelective(articleDO);
        if (falg > 0) {
            responseVo.setCode(Message.OK.getCode());
            responseVo.setMessage(Message.OK.getMessage());
            return responseVo;
        }
        throw new RuntimeException("入库失败");
    }

    @Override
    public ResponseVo getListArticle(ArticleDO articleDO) {
        //分页(原理是动态代理,非侵入式,会在下面执行sql的后面追加上一些sql语句,具体可以看打印日志)
       PageHelper.startPage(articleDO.getCurrentPage(), articleDO.getPageSize());
        ResponseVo responseVo = new ResponseVo();
        ArticleDOExample example = new ArticleDOExample();
        example.createCriteria().andCreateUseridEqualTo(articleDO.getCreateUserid()).andFalgEqualTo(1);
        try {
            List<ArticleDO> articleDOS = articleMapper.selectByExample(example);
            //获取总条数
            Long total = ((Page)articleDOS).getTotal();
            Map<String,Object> map = new HashMap<>();
            responseVo.setCode(Message.OK.getCode());
            responseVo.setMessage(Message.OK.getMessage());
            map.put("data",articleDOS);
            map.put("total",total);
            responseVo.setData(map);

        } catch (Exception e) {
            responseVo.setCode(Message.FAIL.getCode());
            responseVo.setMessage(Message.FAIL.getMessage());
        }
        return responseVo;
    }

    @Override
    public ResponseVo updateArticle(ArticleDO articleDO) {
        ResponseVo responseVo = new ResponseVo();
        articleDO.setUpdateTime(new Date());
        final int i = articleMapper.updateByPrimaryKeySelective(articleDO);
        responseVo.setCode(Message.OK.getCode());
        responseVo.setMessage(Message.OK.getMessage());
        return responseVo;
    }

    @Override
    public ResponseVo deleteArticle(ArticleDO articleDO) {
        ResponseVo responseVo = new ResponseVo();
        articleDO.setFalg(0);
        articleMapper.updateByPrimaryKeySelective(articleDO);
        responseVo.setCode(Message.OK.getCode());
        responseVo.setMessage(Message.OK.getMessage());
        return responseVo;
    }

    @Override
    public ResponseVo recoveryArticle(ArticleDO articleDO) {
        ResponseVo responseVo = new ResponseVo();
        articleDO.setFalg(1);
        articleMapper.updateByPrimaryKeySelective(articleDO);
        responseVo.setCode(Message.OK.getCode());
        responseVo.setMessage(Message.OK.getMessage());
        return responseVo;
    }

    @Override
    public ResponseVo getRecycleBinArticle(ArticleDO articleDO) {
        ResponseVo responseVo = new ResponseVo();
        ArticleDOExample example = new ArticleDOExample();
        example.createCriteria().andCreateUseridEqualTo(articleDO.getCreateUserid()).andFalgEqualTo(0);
        try {
            List<ArticleDO> articleDOS = articleMapper.selectByExample(example);
            responseVo.setData(articleDOS);
            responseVo.setCode(Message.OK.getCode());
            responseVo.setMessage(Message.OK.getMessage());
        } catch (Exception e) {
            responseVo.setCode(Message.FAIL.getCode());
            responseVo.setMessage(Message.FAIL.getMessage());
        }
        return responseVo;
    }


}
