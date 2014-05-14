package fip.view.sbs;

import fip.common.constant.TxSendFlag;
import fip.repository.model.DepSbsZfqs;
import fip.service.fip.DepSbsZfqsService;
import fip.common.utils.MessageUtil;
import fip.service.fip.FtpSbsFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 13-3-27
 * Time: ����4:04
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class DepSbsZfqsAction implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(DepSbsZfqsAction.class);

    private String startDate;
    private String endDate;
    private String obtainDate;
    @ManagedProperty(value = "#{depSbsZfqsService}")
    private DepSbsZfqsService depSbsZfqsService;
    @ManagedProperty(value = "#{ftpSbsFileService}")
    private FtpSbsFileService ftpSbsFileService;

    private List<DepSbsZfqs> recordList;
    private DepSbsZfqs[] selectedRecords;
    private TxSendFlag sendFlag = TxSendFlag.UNSEND;

    @PostConstruct
    public void init() {
        obtainDate = getDateAfter(new Date(), -1, "yyyy-MM-dd");
        startDate = obtainDate;
        endDate = obtainDate;
        recordList = depSbsZfqsService.qryUnsendRecords();
    }

    public String onObtain() {
        try {
            // ��ȡ�ܷ��˻���������
            String[] lines = ftpSbsFileService.obtainSbsZfqsMsgs(obtainDate);
            if (lines == null || lines.length == 0) {
                MessageUtil.addInfo("����δ��ȡ��SBS���ݡ�[" + obtainDate + "]û���ܷ��˻�������ϸ��");
            } else {
                MessageUtil.addInfo("���λ�ȡ������ϸ������" + lines.length);
                // ����
                int cnt = depSbsZfqsService.saveZfqsRecords(lines);
                MessageUtil.addInfo("������ϸ������" + cnt + ", �ظ���ȡ����" + (lines.length - cnt));
            }
            //��ѯ����δ���ͼ�¼
            recordList = depSbsZfqsService.qryUnsendRecords();

        } catch (Exception e) {
            MessageUtil.addError("����SBS��ȡ�ļ�ʧ�ܡ�" + (e.getMessage() == null ? "" : e.getMessage()));
            logger.error("����SBS��ȡ�ļ�ʧ�ܡ�", e);
        }
        return null;
    }

    public String onSend() {
        int cnt = 0;
        int allCnt = 0;
        try {
            if (recordList == null || recordList.isEmpty()) {
                MessageUtil.addWarn("û��δ�������ݡ�");
                return null;
            } else {
                /*
                String[] sns = depJdeZfqsService.sendSbsZfqsRecords(recordList);
                int cnt = depSbsZfqsService.updateToSendStsBySns(sns);*/
                allCnt = recordList.size();
                String operTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                for (DepSbsZfqs record : recordList) {
                    String snMsg = depSbsZfqsService.sendSbsZfqs(record);
                    cnt += depSbsZfqsService.updateToSentSts(snMsg, operTime) == 1 ? 1 : 0;
                }
                MessageUtil.addInfo("���ͳɹ�������" + cnt + " ����ʧ�ܱ�����" + (allCnt - cnt));
                //��ѯ����δ���ͼ�¼
                recordList = depSbsZfqsService.qryUnsendRecords();
                if (recordList == null || recordList.isEmpty()) {
                    MessageUtil.addInfo("�����ѻ�ȡ������ϸ�����ͳɹ���");
                }
            }
        } catch (Exception e) {
            MessageUtil.addWarn("���ͳɹ�������" + cnt + " ����ʧ�ܱ�����" + (allCnt - cnt));
            MessageUtil.addError("����������JDE���̳����쳣��" + (e.getMessage() == null ? "" : e.getMessage()));
            logger.error("����������JDE���̳����쳣��", e);
        }
        return null;
    }

    public String onMultiSend() {
        int cnt = 0;
        int allCnt = 0;
        try {
            if (selectedRecords == null || selectedRecords.length == 0) {
                MessageUtil.addWarn("������ѡ��һ�����ݼ�¼��");
                return null;
            } else {
                /*
                String[] sns = depJdeZfqsService.sendSbsZfqsRecords(recordList);
                int cnt = depSbsZfqsService.updateToSendStsBySns(sns);*/
                allCnt = selectedRecords.length;
                String operTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
                for (DepSbsZfqs record : selectedRecords) {
                    String snMsg = depSbsZfqsService.sendSbsZfqs(record);
                    cnt += depSbsZfqsService.updateToSentSts(snMsg, operTime) == 1 ? 1 : 0;
                }
                MessageUtil.addInfo("���ͳɹ�������" + cnt + " ����ʧ�ܱ�����" + (allCnt - cnt));
                //��ѯ����δ���ͼ�¼
                recordList = depSbsZfqsService.qryUnsendRecords();
                if (recordList == null || recordList.isEmpty()) {
                    MessageUtil.addInfo("�����ѻ�ȡ������ϸ�����ͳɹ���");
                }
            }
        } catch (Exception e) {
            MessageUtil.addWarn("���ͳɹ�������" + cnt + " ����ʧ�ܱ�����" + (allCnt - cnt));
            MessageUtil.addError("����������JDE���̳����쳣��" + (e.getMessage() == null ? "" : e.getMessage()));
            logger.error("����������JDE���̳����쳣��", e);
        }
        return null;
    }

    public String onQuery() {
        try {
            startDate = startDate.substring(0, 4) + startDate.substring(5, 7) + startDate.substring(8, 10);
            endDate = endDate.substring(0, 4) + endDate.substring(5, 7) + endDate.substring(8, 10);
            //��ѯ����δ���ͼ�¼
            recordList = depSbsZfqsService.qryUnSendRecordsByDate(startDate, endDate);
            if (recordList == null || recordList.isEmpty()) {
                MessageUtil.addInfo("û�в�ѯ�����������ݡ�");
            }
        } catch (Exception e) {
            MessageUtil.addError("��ѯ����ʧ�ܡ�" + (e.getMessage() == null ? "" : e.getMessage()));
            logger.error("��ѯ����ʧ�ܡ�", e);
        }
        return null;
    }

    private String getDateAfter(Date date, int days, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + days);
        return sdf.format(calendar.getTime());
    }

    // ---------------------------

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<DepSbsZfqs> getRecordList() {
        return recordList;
    }

    public void setRecordList(List<DepSbsZfqs> recordList) {
        this.recordList = recordList;
    }

    public String getObtainDate() {
        return obtainDate;
    }

    public void setObtainDate(String obtainDate) {
        this.obtainDate = obtainDate;
    }

    public DepSbsZfqsService getDepSbsZfqsService() {
        return depSbsZfqsService;
    }

    public void setDepSbsZfqsService(DepSbsZfqsService depSbsZfqsService) {
        this.depSbsZfqsService = depSbsZfqsService;
    }

    public FtpSbsFileService getFtpSbsFileService() {
        return ftpSbsFileService;
    }

    public void setFtpSbsFileService(FtpSbsFileService ftpSbsFileService) {
        this.ftpSbsFileService = ftpSbsFileService;
    }

    public TxSendFlag getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(TxSendFlag sendFlag) {
        this.sendFlag = sendFlag;
    }

    public DepSbsZfqs[] getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(DepSbsZfqs[] selectedRecords) {
        this.selectedRecords = selectedRecords;
    }
}
