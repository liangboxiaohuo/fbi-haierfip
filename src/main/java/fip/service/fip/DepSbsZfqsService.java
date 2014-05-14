package fip.service.fip;

import fip.common.constant.TxSendFlag;
import fip.repository.dao.DepSbsZfqsMapper;
import fip.repository.model.DepSbsZfqs;
import fip.repository.model.DepSbsZfqsExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jms.JMSException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 13-4-1
 * Time: ����1:15
 * To change this template use File | Settings | File Templates.
 */
@Service
public class DepSbsZfqsService {

    private static final Logger logger = LoggerFactory.getLogger(DepSbsZfqsService.class);

    @Autowired
    private DepService depService;
    @Autowired
    private JobLogService jobLogService;

    @Autowired
    private DepSbsZfqsMapper depSbsZfqsMapper;

    //  ����������ϸ��JDE�����ط��ͳɹ���¼��ˮ��
    public String sendSbsZfqs(DepSbsZfqs record) throws JMSException {

        // ����
        String msgid = depService.sendDepMessage("300", record.toFormatString(), "transCreditInfoFromSBStoJDE");
        String rtnmsg = depService.recvDepMessage(msgid);
        // ��¼��־
        appendDepJdeJoblog(record.getPkid(), record.getSerialNo());
        return rtnmsg;
    }

    public void appendDepJdeJoblog(String pkid, String serialNo) {
        jobLogService.insertNewJoblog(pkid, "dep_sbs_zfqs", "transCreditInfoFromSBStoJDE", "����SBS�ڲ��ܷ��˻��������ݵ�JDE,[" + serialNo + "]", "9999", "ϵͳ����Ա");
    }

    // -----------------------------------------
    // ����DEP���ص��ַ����б�������ϸ����
    public int saveZfqsRecords(String[] originRecords) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        int existCnt = 0;
        for (String line : originRecords) {
            DepSbsZfqs record = new DepSbsZfqs();
            record.setSerialNo(line.substring(0, 14));
            // �����ݿ��Ѵ��ڸ���ˮ�ţ�����Ϊ�ѻ�ȡ�ñ���ϸ
            if (isExist(record.getSerialNo())) {
                existCnt++;
                continue;
            }

            record.setTxnDate(line.substring(14, 22));
            record.setFenAccountNo(line.substring(22, 40));
            record.setZongAccountNo(line.substring(40, 58));
            record.setTxnAmt(new BigDecimal(line.substring(58, 74)));
            record.setTxnDirection(line.substring(74, 75));
            record.setCreateTime(record.getTxnDate());
            record.setSendFlag(TxSendFlag.UNSEND.getCode());
            addZfqsRecord(record);
        }
        logger.info("�����ι���ȡ��¼������" + originRecords.length + "�����ظ���ȡ������" + existCnt);
        return originRecords.length - existCnt;
    }

    public int addZfqsRecord(DepSbsZfqs record) {
        return depSbsZfqsMapper.insert(record);
    }

    public boolean isExist(String serialNo) {
        DepSbsZfqsExample example = new DepSbsZfqsExample();
        example.createCriteria().andSerialNoEqualTo(serialNo);
        return depSbsZfqsMapper.countByExample(example) > 0;
    }

    public List<DepSbsZfqs> qryUnsendRecords() {
        DepSbsZfqsExample example = new DepSbsZfqsExample();
        example.createCriteria().andSendFlagNotEqualTo(TxSendFlag.SENT.getCode());
        return depSbsZfqsMapper.selectByExample(example);
    }

    public List<DepSbsZfqs> qryUnSendRecordsByDate(String startDate, String endDate) {
        DepSbsZfqsExample example = new DepSbsZfqsExample();
        example.createCriteria().andSendFlagNotEqualTo(TxSendFlag.SENT.getCode()).andCreateTimeBetween(startDate, endDate);
        return depSbsZfqsMapper.selectByExample(example);
    }

    @Deprecated
    public int updateToSendStsBySns(String[] snList) {
        int cnt = 0;
        String operTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        for (String sn : snList) {
          /* 1��F  ����ʧ�ܣ�����ʧ��
            2��S  ����ɹ�
            3��D  �˺��Ѵ��� */
            String[] record = sn.split("\\|");
            if ("S".equalsIgnoreCase(record[1]) || "D".equalsIgnoreCase(record[1])) {
                cnt += (updateToSendStsBySn(record[0], operTime, TxSendFlag.SENT, record[1]) == -1 ? 0 : 1);
            }
        }
        return cnt;
    }

    public int updateToSentSts(String snMsg, String operTime) {
        String[] record = snMsg.split("\\|");
        if ("S".equalsIgnoreCase(record[1]) || "D".equalsIgnoreCase(record[1])) {
            return updateToSendStsBySn(record[0], operTime, TxSendFlag.SENT, record[1]);
        } else {
            return updateToSendStsBySn(record[0], operTime, TxSendFlag.UNSEND, record[1]);
        }
    }

    public int updateToSendStsBySn(String sn, String operTime, TxSendFlag sendFlag, String remark) {

        DepSbsZfqsExample example = new DepSbsZfqsExample();
        example.createCriteria().andSerialNoEqualTo(sn);
        DepSbsZfqs originRecord = depSbsZfqsMapper.selectByExample(example).get(0);
        originRecord.setSendFlag(sendFlag.getCode());
        originRecord.setOperTime(operTime);
        originRecord.setRemark(remark);
        return depSbsZfqsMapper.updateByPrimaryKey(originRecord);
    }
}
