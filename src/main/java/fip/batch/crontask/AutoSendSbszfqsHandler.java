package fip.batch.crontask;

import fip.batch.common.tool.OperationValve;
import fip.repository.model.DepSbsZfqs;
import fip.service.fip.DepSbsZfqsService;
import fip.service.fip.FtpSbsFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 13-4-2
 * Time: ����10:32
 * To change this template use File | Settings | File Templates.
 */

@Component
public class AutoSendSbszfqsHandler {
    private static final Logger logger = LoggerFactory.getLogger(AutoSendSbszfqsHandler.class);

    @Autowired
    private DepSbsZfqsService depSbsZfqsService;
    @Autowired
    private FtpSbsFileService ftpSbsFileService;

    @Autowired @Qualifier("propertyFileOperationValve")
    private OperationValve operationValve;

    // �Զ���ȡ������ ���� SBS�ڲ��ܷ��˻�������ϸ
    public void obtainAndSendSbsZfqsRecords() {
        if (!isCronTaskOpen()) {
            logger.info("�Զ������������ѹرա�");
            return;
        }
        String date10 = getDateAfter(new Date(), -1, "yyyy-MM-dd");
        try {
            // ��ȡ�ܷ��˻���������
            String[] lines = ftpSbsFileService.obtainSbsZfqsMsgs(date10);
            int obtainCnt = 0;
            if (lines == null || lines.length == 0) {
                logger.info("����δ��ȡ��SBS���ݡ�[" + date10 + "]û���ܷ��˻�������ϸ��");
                return;
            } else {
                // ����
                obtainCnt = depSbsZfqsService.saveZfqsRecords(lines);
            }
            String date8 = getDateAfter(new Date(), -1, "yyyyMMdd");
            List<DepSbsZfqs> recordList = depSbsZfqsService.qryUnSendRecordsByDate(date8, date8);
            String operTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            int sendCnt = 0;
            for (DepSbsZfqs record : recordList) {
                String snMsg = depSbsZfqsService.sendSbsZfqs(record);
                sendCnt += depSbsZfqsService.updateToSentSts(snMsg, operTime) == 1 ? 1 : 0;
            }
           /* List<DepSmsCfg> smsCfgList = smsCfgService.qrySmsCfgsByTxnCode("sendSbsZfqs");
            String msg = date8 + "��ȡ��ϸ������" + lines.length + ",���������" + obtainCnt + ",�ظ�����"
                    + (lines.length - obtainCnt) + ",JDE���մ���ɹ�����" + sendCnt + " JDE����ʧ������" + (recordList.size() - sendCnt);
            for(DepSmsCfg sms : smsCfgList) {
                SmsTool.sendMessage(sms.getUserPhone(), "[" + sms.getTxnName() + "]" + msg );
            }*/
            logger.info(date10 + "�Զ�����SBS�ڲ��ܷ��˻�������ϸ����" + sendCnt);
            logger.info(date10 + " obtainAndSendSbsZfqsRecords��success.");
        } catch (Exception e) {
            logger.error(date10 + "�Զ���ȡ������SBS�ڲ��ܷ��˻�������ϸʧ�ܡ�", e);
        }
    }

    private String getDateAfter(Date date, int days, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + days);
        return sdf.format(calendar.getTime());
    }

    private boolean isCronTaskOpen(){
        try {
            return operationValve.isOpen("cron_task_mode");
        } catch (Exception e) {
            logger.error("��ȡ�����ļ�����", e);
            return false;
        }
    }
}
