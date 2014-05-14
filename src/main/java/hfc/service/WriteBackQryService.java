package hfc.service;

import fip.repository.dao.fip.FipCommonMapper;
import fip.repository.model.Xfapp;
import hfc.parambean.AppQryParam;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.platform.db.ConnectionManager;
import pub.platform.db.DatabaseConnection;
import pub.platform.db.RecordSet;

import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 11-8-12
 * Time: ����10:59
 * To change this template use File | Settings | File Templates.
 */
@Service
public class WriteBackQryService {

    private Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private FipCommonMapper commonMapper;

    public List<Xfapp> getXfappsByCondition(AppQryParam app) {
         return commonMapper.selectAppByCondition(app);
    }

   /* // ���뵥��Ŀ
    public int getXfappCntLikeAppno(String appno) {
        XfappExample example = new XfappExample();
        example.createCriteria().andAppnoLike("%" + appno + "%").andAppstatusNotEqualTo(AppStatusEnum.APP_INVALID.getCode());
        return xfappMapper.countByExample(example);
    }

    // ���뵥�б�
    public List<Xfapp> getXfappsLikeAppno(String appno) {
        XfappExample example = new XfappExample();
        example.createCriteria().andAppnoLike("%" + appno + "%").andAppstatusNotEqualTo(AppStatusEnum.APP_INVALID.getCode());
        return xfappMapper.selectByExample(example);
    }*/


    private RecordSet qryRecordsBySql(String sql) {
        DatabaseConnection conn = null;
        RecordSet recordSet = null;
        try {
            conn = ConnectionManager.getInstance().getConnection();
            logger.info(sql);
            recordSet = conn.executeQuery(sql);
            if (recordSet.getRecordCount() == 0) {
                logger.error("δ�ҵ�Ҫ��ѯ����Ϣ" + sql);
                throw new RuntimeException("δ�ҵ�Ҫ��ѯ����Ϣ");
            }
            return recordSet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.getInstance().releaseConnection(conn);
        }
    }
}
