package fip.repository.dao;

import fip.repository.model.ChkZongfenTxn;
import fip.repository.model.ChkZongfenTxnExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ChkZongfenTxnMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    int countByExample(ChkZongfenTxnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    int deleteByExample(ChkZongfenTxnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    int deleteByPrimaryKey(String pkid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    int insert(ChkZongfenTxn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    int insertSelective(ChkZongfenTxn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    List<ChkZongfenTxn> selectByExample(ChkZongfenTxnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    ChkZongfenTxn selectByPrimaryKey(String pkid);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    int updateByExampleSelective(@Param("record") ChkZongfenTxn record, @Param("example") ChkZongfenTxnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    int updateByExample(@Param("record") ChkZongfenTxn record, @Param("example") ChkZongfenTxnExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    int updateByPrimaryKeySelective(ChkZongfenTxn record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table FIP.CHK_ZONGFEN_TXN
     *
     * @mbggenerated Fri Jul 27 14:51:12 CST 2012
     */
    int updateByPrimaryKey(ChkZongfenTxn record);
}