package fip.repository.dao;

import fip.repository.model.XfapprepaymentH;
import fip.repository.model.XfapprepaymentHExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface XfapprepaymentHMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPREPAYMENT_H
     *
     * @mbggenerated Fri Aug 19 17:37:21 CST 2011
     */
    int countByExample(XfapprepaymentHExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPREPAYMENT_H
     *
     * @mbggenerated Fri Aug 19 17:37:21 CST 2011
     */
    int deleteByExample(XfapprepaymentHExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPREPAYMENT_H
     *
     * @mbggenerated Fri Aug 19 17:37:21 CST 2011
     */
    int insert(XfapprepaymentH record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPREPAYMENT_H
     *
     * @mbggenerated Fri Aug 19 17:37:21 CST 2011
     */
    int insertSelective(XfapprepaymentH record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPREPAYMENT_H
     *
     * @mbggenerated Fri Aug 19 17:37:21 CST 2011
     */
    List<XfapprepaymentH> selectByExample(XfapprepaymentHExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPREPAYMENT_H
     *
     * @mbggenerated Fri Aug 19 17:37:21 CST 2011
     */
    int updateByExampleSelective(@Param("record") XfapprepaymentH record, @Param("example") XfapprepaymentHExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFAPPREPAYMENT_H
     *
     * @mbggenerated Fri Aug 19 17:37:21 CST 2011
     */
    int updateByExample(@Param("record") XfapprepaymentH record, @Param("example") XfapprepaymentHExample example);
}