package fip.repository.dao;

import fip.repository.model.Xfcreditinfo;
import fip.repository.model.XfcreditinfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface XfcreditinfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int countByExample(XfcreditinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int deleteByExample(XfcreditinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int deleteByPrimaryKey(String appno);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int insert(Xfcreditinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int insertSelective(Xfcreditinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    List<Xfcreditinfo> selectByExample(XfcreditinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    Xfcreditinfo selectByPrimaryKey(String appno);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int updateByExampleSelective(@Param("record") Xfcreditinfo record, @Param("example") XfcreditinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int updateByExample(@Param("record") Xfcreditinfo record, @Param("example") XfcreditinfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int updateByPrimaryKeySelective(Xfcreditinfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.XFCREDITINFO
     *
     * @mbggenerated Sat Aug 13 21:38:48 CST 2011
     */
    int updateByPrimaryKey(Xfcreditinfo record);
}