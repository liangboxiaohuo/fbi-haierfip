package fip.view.sbs;

import fip.common.constant.PayoutBatRtnCode;
import fip.common.constant.PayoutBatTxnStep;
import fip.common.constant.PayoutDetlRtnCode;
import fip.common.constant.PayoutDetlTxnStep;
import fip.common.utils.MessageUtil;
import fip.repository.model.FipJoblog;
import fip.repository.model.FipPayoutbat;
import fip.repository.model.FipPayoutdetl;
import fip.service.fip.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import java.io.Serializable;
import java.util.*;

/**
 * sbs��������
 */
@ManagedBean
@ViewScoped
public class SbsPayoutAction implements Serializable {
    private static final Logger logger = LoggerFactory.getLogger(SbsPayoutAction.class);
    @ManagedProperty(value = "#{payoutbatService}")
    private PayoutbatService payoutbatService;
    @ManagedProperty(value = "#{payoutDetlService}")
    private PayoutDetlService payoutDetlService;
    @ManagedProperty(value = "#{payoutTxnService}")
    private PayoutTxnService payoutTxnService;
    @ManagedProperty(value = "#{jobLogService}")
    private JobLogService jobLogService;

    private List<FipPayoutbat> n057List;
    private List<FipPayoutbat> unipayList;
    private List<FipPayoutbat> qryList;
    private List<FipPayoutbat> sbsConfirmList;
    private List<FipPayoutbat> allList;

    private List<FipPayoutdetl> detlList;
    private List<FipJoblog> joblogList;

    private FipPayoutbat[] selectedBats;
    private FipPayoutdetl selectedDetl;

    private PayoutBatRtnCode batRetCode = PayoutBatRtnCode.TXN_HALFWAY;
    private PayoutDetlRtnCode detlRetCode = PayoutDetlRtnCode.HALFWAY;
    private PayoutDetlTxnStep detlTxnStep = PayoutDetlTxnStep.INIT;
    private PayoutBatTxnStep batTxnStep = PayoutBatTxnStep.INIT;

    @PostConstruct
    public void init() {
        String reqsn = (String) FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("reqsn");
        if (StringUtils.isEmpty(reqsn)) {
            onQuery();
        } else {
            detlList = payoutDetlService.qryRecordsBySn(reqsn);
            joblogList = new ArrayList<FipJoblog>();
            for (FipPayoutdetl detl : detlList) {
                joblogList.addAll(jobLogService.selectJobLogsByOriginPkid("fip_payoutdetl", detl.getPkid()));
            }
        }
    }

    public String onQuery() {
        try {
            n057List = payoutbatService.qryPayoutbatsByBatSts(PayoutBatRtnCode.TXN_HALFWAY, PayoutBatTxnStep.INIT);
            unipayList = payoutbatService.qryPayoutbatsByBatSts(PayoutBatRtnCode.TXN_HALFWAY, PayoutBatTxnStep.SBSN057);
            qryList = payoutbatService.qryPayoutbatsByBatSts(PayoutBatRtnCode.TXN_HALFWAY, PayoutBatTxnStep.UNIONPAY_TXN_PAYOUT);
            sbsConfirmList = payoutbatService.qryPayoutbatsByBatSts(PayoutBatRtnCode.TXN_HALFWAY, PayoutBatTxnStep.UNIONPAY_TXN_OVER);
        } catch (Exception e) {
            MessageUtil.addError("��ѯʧ�ܡ�" + (e.getMessage() == null ? "" : e.getMessage()));
            logger.error("��ѯʧ�ܡ�", e);
        }
        return null;
    }

    public String onQryALl() {
        try {
            allList = payoutbatService.qryAllPayoutbats();
            if (allList.isEmpty()) {
                MessageUtil.addWarn("û�����ݼ�¼��");
            }
        } catch (Exception e) {
            MessageUtil.addError("��ѯʧ�ܡ�" + (e.getMessage() == null ? "" : e.getMessage()));
            logger.error("��ѯʧ�ܡ�", e);
        }
        return null;
    }

    // SBS��������
    public String onAllN057() {
        try {
            // sbs����
            int cnt = payoutTxnService.processN057(n057List);
            if (cnt > 0) {
                MessageUtil.addInfo("SBS֧�����˳ɹ�����:" + cnt);
            } else {
                MessageUtil.addInfo("SBS֧������ʧ�ܡ�");
            }
            // �ٴβ�ѯ
            onQuery();
        } catch (Exception e) {
            MessageUtil.addError("֧��[N057]ʧ�ܡ�" + (e.getMessage() == null ? "" : e.getMessage()));
            logger.error("֧��[N057]ʧ�ܡ�", e);
        }
        return null;
    }

    public String onN057() {
        try {
            // sbs����
            int cnt = payoutTxnService.processN057(Arrays.asList(selectedBats));
            if (cnt > 0) {
                MessageUtil.addInfo("SBS֧�����˳ɹ�����:" + cnt);
            } else {
                MessageUtil.addInfo("SBS֧������ʧ�ܡ�");
            }
            // �ٴβ�ѯ
            onQuery();
        } catch (Exception e) {
            MessageUtil.addError("֧��[N057]ʧ�ܡ�" + (e.getMessage() == null ? "" : e.getMessage()));
            logger.error("֧��[N057]ʧ�ܡ�", e);
        }
        return null;
    }

    // ��������
    public String onUnipayout() {
        try {
            // unionpay ����
            int cnt = payoutTxnService.processUnionpayPayout(unipayList);
            if (cnt > 0) {
                MessageUtil.addInfo("��������������ɣ��������ѯ������:" + cnt);
            } else {
                MessageUtil.addInfo("������������ʧ�ܡ�");
            }
            // �ٴβ�ѯ
            onQuery();
        } catch (Exception e) {
            MessageUtil.addError("��������ʧ�ܡ�" + (e.getMessage() == null ? "" : e.getMessage()));
            logger.error("��������ʧ�ܡ�", e);
        }
        return null;
    }

    // ������ѯ
    public String onUnionpayQry() {
        try {
            // unionpay query
            payoutTxnService.processUnionpayQry(qryList);
            MessageUtil.addInfo("���������ѯ�������в���������ף����Ժ��ٴβ�ѯ��");
            // �ٴβ�ѯ
            onQuery();
        } catch (Exception e) {
            MessageUtil.addError("��ѯʧ�ܡ�" + (e.getMessage() == null ? "" : e.getMessage()));
            logger.error("��ѯʧ�ܡ�", e);
        }
        return null;
    }

    // sbs �������� n058��n059
    public String onSbsConfirm() {
        try {
            // unionpay query
            int cnt = payoutTxnService.processSbsPayoutConfirm(sbsConfirmList);
            if (cnt > 0) {
                MessageUtil.addInfo("SBS����ȷ�ϱ���:" + cnt);
            } else {
                MessageUtil.addInfo("SBS���˳���������" + (sbsConfirmList.size() - cnt));
            }
            // �ٴβ�ѯ
            onQuery();
        } catch (Exception e) {
            MessageUtil.addError("��ѯʧ�ܡ�" + (e.getMessage() == null ? "" : e.getMessage()));
            logger.error("��ѯʧ�ܡ�", e);
        }
        return null;
    }
    // ---------------------------

    public PayoutbatService getPayoutbatService() {
        return payoutbatService;
    }

    public void setPayoutbatService(PayoutbatService payoutbatService) {
        this.payoutbatService = payoutbatService;
    }

    public PayoutDetlService getPayoutDetlService() {
        return payoutDetlService;
    }

    public void setPayoutDetlService(PayoutDetlService payoutDetlService) {
        this.payoutDetlService = payoutDetlService;
    }

    public PayoutTxnService getPayoutTxnService() {
        return payoutTxnService;
    }

    public void setPayoutTxnService(PayoutTxnService payoutTxnService) {
        this.payoutTxnService = payoutTxnService;
    }

    public List<FipPayoutbat> getN057List() {
        return n057List;
    }

    public void setN057List(List<FipPayoutbat> n057List) {
        this.n057List = n057List;
    }

    public List<FipPayoutbat> getSbsConfirmList() {
        return sbsConfirmList;
    }

    public void setSbsConfirmList(List<FipPayoutbat> sbsConfirmList) {
        this.sbsConfirmList = sbsConfirmList;
    }

    public List<FipPayoutbat> getQryList() {
        return qryList;
    }

    public void setQryList(List<FipPayoutbat> qryList) {
        this.qryList = qryList;
    }

    public List<FipPayoutbat> getUnipayList() {
        return unipayList;
    }

    public void setUnipayList(List<FipPayoutbat> unipayList) {
        this.unipayList = unipayList;
    }

    public FipPayoutbat[] getSelectedBats() {
        return selectedBats;
    }

    public void setSelectedBats(FipPayoutbat[] selectedBats) {
        this.selectedBats = selectedBats;
    }

    public PayoutBatRtnCode getBatRetCode() {
        return batRetCode;
    }

    public void setBatRetCode(PayoutBatRtnCode batRetCode) {
        this.batRetCode = batRetCode;
    }

    public PayoutDetlRtnCode getDetlRetCode() {
        return detlRetCode;
    }

    public void setDetlRetCode(PayoutDetlRtnCode detlRetCode) {
        this.detlRetCode = detlRetCode;
    }

    public PayoutDetlTxnStep getDetlTxnStep() {
        return detlTxnStep;
    }

    public void setDetlTxnStep(PayoutDetlTxnStep detlTxnStep) {
        this.detlTxnStep = detlTxnStep;
    }

    public PayoutBatTxnStep getBatTxnStep() {
        return batTxnStep;
    }

    public void setBatTxnStep(PayoutBatTxnStep batTxnStep) {
        this.batTxnStep = batTxnStep;
    }

    public List<FipPayoutbat> getAllList() {
        return allList;
    }

    public void setAllList(List<FipPayoutbat> allList) {
        this.allList = allList;
    }

    public List<FipPayoutdetl> getDetlList() {
        return detlList;
    }

    public void setDetlList(List<FipPayoutdetl> detlList) {
        this.detlList = detlList;
    }

    public List<FipJoblog> getJoblogList() {
        return joblogList;
    }

    public void setJoblogList(List<FipJoblog> joblogList) {
        this.joblogList = joblogList;
    }

    public FipPayoutdetl getSelectedDetl() {
        return selectedDetl;
    }

    public void setSelectedDetl(FipPayoutdetl selectedDetl) {
        this.selectedDetl = selectedDetl;
    }

    public JobLogService getJobLogService() {
        return jobLogService;
    }

    public void setJobLogService(JobLogService jobLogService) {
        this.jobLogService = jobLogService;
    }
}
