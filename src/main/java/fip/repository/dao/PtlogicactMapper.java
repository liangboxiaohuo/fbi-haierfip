package fip.repository.dao;

import fip.repository.model.Ptlogicact;
import fip.repository.model.PtlogicactExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component

public interface PtlogicactMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTLOGICACT
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    int countByExample(PtlogicactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTLOGICACT
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    int deleteByExample(PtlogicactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTLOGICACT
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    int insert(Ptlogicact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTLOGICACT
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    int insertSelective(Ptlogicact record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTLOGICACT
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    List<Ptlogicact> selectByExample(PtlogicactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTLOGICACT
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    int updateByExampleSelective(@Param("record") Ptlogicact record, @Param("example") PtlogicactExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table PTLOGICACT
     *
     * @mbggenerated Fri Jul 22 13:16:43 CST 2011
     */
    int updateByExample(@Param("record") Ptlogicact record, @Param("example") PtlogicactExample example);
}