package fip.repository.dao;

import fip.repository.model.FipCutpaybat;
import fip.repository.model.FipCutpaybatExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface FipCutpaybatMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.FIP_CUTPAYBAT
     *
     * @mbggenerated Fri Aug 19 14:07:02 CST 2011
     */
    int countByExample(FipCutpaybatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.FIP_CUTPAYBAT
     *
     * @mbggenerated Fri Aug 19 14:07:02 CST 2011
     */
    int deleteByExample(FipCutpaybatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.FIP_CUTPAYBAT
     *
     * @mbggenerated Fri Aug 19 14:07:02 CST 2011
     */
    int deleteByPrimaryKey(String txpkgSn);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.FIP_CUTPAYBAT
     *
     * @mbggenerated Fri Aug 19 14:07:02 CST 2011
     */
    int insert(FipCutpaybat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.FIP_CUTPAYBAT
     *
     * @mbggenerated Fri Aug 19 14:07:02 CST 2011
     */
    int insertSelective(FipCutpaybat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.FIP_CUTPAYBAT
     *
     * @mbggenerated Fri Aug 19 14:07:02 CST 2011
     */
    List<FipCutpaybat> selectByExample(FipCutpaybatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.FIP_CUTPAYBAT
     *
     * @mbggenerated Fri Aug 19 14:07:02 CST 2011
     */
    FipCutpaybat selectByPrimaryKey(String txpkgSn);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.FIP_CUTPAYBAT
     *
     * @mbggenerated Fri Aug 19 14:07:02 CST 2011
     */
    int updateByExampleSelective(@Param("record") FipCutpaybat record, @Param("example") FipCutpaybatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.FIP_CUTPAYBAT
     *
     * @mbggenerated Fri Aug 19 14:07:02 CST 2011
     */
    int updateByExample(@Param("record") FipCutpaybat record, @Param("example") FipCutpaybatExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.FIP_CUTPAYBAT
     *
     * @mbggenerated Fri Aug 19 14:07:02 CST 2011
     */
    int updateByPrimaryKeySelective(FipCutpaybat record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.FIP_CUTPAYBAT
     *
     * @mbggenerated Fri Aug 19 14:07:02 CST 2011
     */
    int updateByPrimaryKey(FipCutpaybat record);
}