package hfc.view.consume;

import fip.common.utils.MessageUtil;
import fip.repository.model.Xfapp;
import hfc.common.AppPrepayStatus;
import hfc.common.AppStatus;
import hfc.service.PrepayService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 11-8-27
 * Time: ����5:18
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean
@ViewScoped
public class PrepayAction {
    private Log logger = LogFactory.getLog(this.getClass());

    @ManagedProperty(value = "#{prepayService}")
    private PrepayService prepayService;

    private List<Xfapp> detlList;
    //�����ɿۿ��¼
    private List<Xfapp> handledDetlList;
    private Xfapp[] selectedRecords;
    private Xfapp selectedRecord;

    @PostConstruct
    public void init() {
        detlList = prepayService.selectPendCutpayList(AppStatus.APP_UPSUCCESS, AppPrepayStatus.INIT);
        handledDetlList = prepayService.selectPendCutpayList(AppPrepayStatus.CUTPAY_RECORD_GENERATED);
    }

    public String onConfirmAll() {
        return process(this.detlList);
    }
    public String onConfirmMulti() {
        return process(Arrays.asList(this.selectedRecords));
    }

    private String process(List<Xfapp> list) {
        try {
            prepayService.generateCutpayRecords(list);
            MessageUtil.addError("���ɿۿ��¼��������鿴��������ϸ��");
        } catch (Exception e) {
            logger.error("���ɿۿ��¼ʱ���ִ������ѯ��", e);
            MessageUtil.addError("���ɿۿ��¼ʱ���ִ������ѯ��" + e.getMessage());
        }
        init();
        return null;
    }


    public PrepayService getPrepayService() {
        return prepayService;
    }

    public void setPrepayService(PrepayService prepayService) {
        this.prepayService = prepayService;
    }

    public List<Xfapp> getDetlList() {
        return detlList;
    }

    public void setDetlList(List<Xfapp> detlList) {
        this.detlList = detlList;
    }

    public Xfapp[] getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(Xfapp[] selectedRecords) {
        this.selectedRecords = selectedRecords;
    }

    public Xfapp getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(Xfapp selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<Xfapp> getHandledDetlList() {
        return handledDetlList;
    }

    public void setHandledDetlList(List<Xfapp> handledDetlList) {
        this.handledDetlList = handledDetlList;
    }

}
