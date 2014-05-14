package fip.repository.dao;

import fip.repository.model.Xfappgrade;
import fip.repository.model.XfappgradeExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface XfappgradeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPGRADE
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int countByExample(XfappgradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPGRADE
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int deleteByExample(XfappgradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPGRADE
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int deleteByPrimaryKey(String appno);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPGRADE
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int insert(Xfappgrade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPGRADE
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int insertSelective(Xfappgrade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPGRADE
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    List<Xfappgrade> selectByExample(XfappgradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPGRADE
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    Xfappgrade selectByPrimaryKey(String appno);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPGRADE
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int updateByExampleSelective(@Param("record") Xfappgrade record, @Param("example") XfappgradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPGRADE
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int updateByExample(@Param("record") Xfappgrade record, @Param("example") XfappgradeExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPGRADE
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int updateByPrimaryKeySelective(Xfappgrade record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPGRADE
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int updateByPrimaryKey(Xfappgrade record);
}