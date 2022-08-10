package com.example.client.mapper;

import com.example.client.model.ArticleDO;
import com.example.client.model.ArticleDOExample;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
/**
 * @author jidongfang
 */
@Mapper
public interface ArticleMapper {

    /**
     * 查询总条数
     * @param example
     * @return
     */
    int countByExample(ArticleDOExample example);

    /**
     * 删除
     * @param example
     * @return
     */
    int deleteByExample(ArticleDOExample example);

    /**
     * 删除
     * @param id
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 插入
     * @param record
     * @return
     */
    int insert(ArticleDO record);

    /**
     * 插入
     * @param record
     * @return
     */
    int insertSelective(ArticleDO record);

    int insertOrUpdateSelective(ArticleDO record);

    /**
     * 查询列表
     * @param example
     * @return
     */
    List<ArticleDO> selectByExample(ArticleDOExample example);

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    ArticleDO selectByPrimaryKey(Integer id);

    /**
     * 修改
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") ArticleDO record, @Param("example") ArticleDOExample example);

    /**
     * 修改
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") ArticleDO record, @Param("example") ArticleDOExample example);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ArticleDO record);

    /**
     * 修改
     * @param record
     * @return
     */
    int updateByPrimaryKey(ArticleDO record);
}