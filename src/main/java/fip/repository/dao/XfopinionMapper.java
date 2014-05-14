package fip.repository.dao;

import fip.repository.model.Xfopinion;
import fip.repository.model.XfopinionExample;
import fip.repository.model.XfopinionKey;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public interface XfopinionMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFOPINION
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int countByExample(XfopinionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFOPINION
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int deleteByExample(XfopinionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFOPINION
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int deleteByPrimaryKey(XfopinionKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFOPINION
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int insert(Xfopinion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFOPINION
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int insertSelective(Xfopinion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFOPINION
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    List<Xfopinion> selectByExample(XfopinionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFOPINION
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    Xfopinion selectByPrimaryKey(XfopinionKey key);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFOPINION
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int updateByExampleSelective(@Param("record") Xfopinion record, @Param("example") XfopinionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFOPINION
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int updateByExample(@Param("record") Xfopinion record, @Param("example") XfopinionExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFOPINION
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int updateByPrimaryKeySelective(Xfopinion record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table XFOPINION
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int updateByPrimaryKey(Xfopinion record);
}