package fip.repository.dao;

import fip.repository.model.Cmindvclient;
import fip.repository.model.CmindvclientExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component

public interface CmindvclientMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CMINDVCLIENT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int countByExample(CmindvclientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CMINDVCLIENT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int deleteByExample(CmindvclientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CMINDVCLIENT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int deleteByPrimaryKey(BigDecimal clientno);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CMINDVCLIENT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int insert(Cmindvclient record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CMINDVCLIENT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int insertSelective(Cmindvclient record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CMINDVCLIENT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    List<Cmindvclient> selectByExample(CmindvclientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CMINDVCLIENT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    Cmindvclient selectByPrimaryKey(BigDecimal clientno);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CMINDVCLIENT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int updateByExampleSelective(@Param("record") Cmindvclient record, @Param("example") CmindvclientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CMINDVCLIENT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int updateByExample(@Param("record") Cmindvclient record, @Param("example") CmindvclientExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CMINDVCLIENT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int updateByPrimaryKeySelective(Cmindvclient record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table CMINDVCLIENT
     *
     * @mbggenerated Fri Jul 22 13:32:19 CST 2011
     */
    int updateByPrimaryKey(Cmindvclient record);
}