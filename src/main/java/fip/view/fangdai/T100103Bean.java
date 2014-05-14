package fip.view.fangdai;

import fip.gateway.newcms.controllers.T100103CTL;
import fip.gateway.newcms.controllers.T100104CTL;
import fip.gateway.newcms.domain.T100103.T100103ResponseRecord;
import fip.gateway.newcms.domain.T100104.T100104RequestList;
import fip.gateway.newcms.domain.T100104.T100104RequestRecord;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.primefaces.event.SelectEvent;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 2010-10-21
 * Time: 11:28:16
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean(name = "T100103")
@ViewScoped
public class T100103Bean implements Serializable {

    private T100103ResponseRecord selectedRecord;
    private T100103ResponseRecord[] selectedRecords;

    private Log logger = LogFactory.getLog(this.getClass());
    private T100103ResponseRecord responseRecord = new T100103ResponseRecord();
    List<UIT100103ResponseRecord> responseFDList;
    List<T100103ResponseRecord> responseXFList;

    T100103CTL t100103ctl = new T100103CTL();

    private int totalcount;
    private BigDecimal totalamt;
    private BigDecimal totalPrincipalAmt;   //����
    private BigDecimal totalInterestAmt;    //��Ϣ

    private String[] regionCodes = {"0532", "0531", "023", "0351"};
    private String[] regionNames = {"�ൺ", "����", "����", "̫ԭ"};
    private SelectItem[] regionOptions;


    public T100103Bean() {
        initFilters();
    }

    private void initList() throws InvocationTargetException, IllegalAccessException {
        FacesContext context = FacesContext.getCurrentInstance();

        List<T100103ResponseRecord> fdList = null;
        try {
            fdList = t100103ctl.getAllFDRecords();
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "�����Ŵ��Ľӿڳ��ִ���", "����ѯϵͳ������Ա��"));
            throw new RuntimeException("�����Ŵ��Ľӿڳ��ִ���.");
        }
        totalcount = 0;
        initAmt();
        responseFDList = new ArrayList<UIT100103ResponseRecord>();

        for (T100103ResponseRecord record : fdList) {
            String tmpStr = record.getStddqh();
            String regioncdTmp, bankcdTmp, nameTmp;
            if (tmpStr == null || tmpStr.equals("null")) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "��������ת������", ""));
                throw new RuntimeException("��������ת������");
            } else {
                String[] code = tmpStr.split("-");
                record.setStddqh(code[0].trim());
                record.setStdyhh(code[1].trim());
            }
            UIT100103ResponseRecord ui = new UIT100103ResponseRecord();
            BeanUtils.copyProperties(ui, record);
            if ("2".equals(record.getStdsfsd())) {
                ui.setLocked("δ����");
            }else if ("1".equals(record.getStdsfsd())){
                ui.setLocked("������");
            }else{
                ui.setLocked("��ȷ��");
            }
            responseFDList.add(ui);
            totalcount++;
            countAmt(record);

        }

    }

    private void initFilters() {
        regionOptions = createRegionOptions(regionNames, regionCodes);
    }

    private SelectItem[] createRegionOptions(String[] names, String[] codes) {
        SelectItem[] options = new SelectItem[codes.length + 1];

        options[0] = new SelectItem("", "ȫ������");
        for (int i = 0; i < codes.length; i++) {
//            options[i + 1] = new SelectItem(codes[i],codes[i]);
            options[i + 1] = new SelectItem(codes[i], names[i]);
        }

        return options;
    }

    public String query() {

        try {
            initList();
        } catch (Exception e) {
            //TODO
            return null;
        }

        String hth, dqh, khmc;
        dqh = responseRecord.getStddqh();
        hth = responseRecord.getStdhth();
        khmc = responseRecord.getStdkhmc();
        totalcount = 0;
        initAmt();
        List<UIT100103ResponseRecord> newResponseFDList = new ArrayList<UIT100103ResponseRecord>();
        for (UIT100103ResponseRecord record : responseFDList) {
            if (StringUtils.isNotEmpty(hth)) {
                if (!record.getStdhth().equals(hth)) {
                    continue;
                }
            }
            if (StringUtils.isNotEmpty(khmc)) {
                if (!record.getStdkhmc().equals(khmc)) {
                    continue;
                }
            }
            if (StringUtils.isNotEmpty(dqh)) {
                if (!record.getStddqh().equals(dqh)) {
                    continue;
                }
            }
            newResponseFDList.add(record);
            totalcount++;
            countAmt(record);
        }
        responseFDList = newResponseFDList;
        return null;
    }

    private void initAmt() {
        totalamt = new BigDecimal(0);
        totalPrincipalAmt = new BigDecimal(0);
        totalInterestAmt = new BigDecimal(0);
    }

    private void countAmt(T100103ResponseRecord record) {
        totalamt = totalamt.add(new BigDecimal(record.getStdhkje()));
        totalPrincipalAmt = totalPrincipalAmt.add(new BigDecimal(record.getStdhkbj()));
        totalInterestAmt = totalInterestAmt.add(new BigDecimal(record.getStdhklx()));
    }

    public String onRowSelectNavigate(SelectEvent event) {
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedRecord", event.getObject());

        return "FDCutpayDetail?faces-redirect=true";
    }


    //��д��������Ϣ������
    public String doLockAll() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.responseFDList == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "��Ϣ����ʱ���ִ���", "�����Ƚ��л����¼��ѯ��"));

            return null;
        }
        if (selectedRecords.length > 0) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "��Ϣ����ʱ���ִ���", "����ѡ����ϸ��¼��"));
            return null;
        }
        T100103ResponseRecord[] records = new T100103ResponseRecord[this.responseFDList.size()];
        startWriteBack(this.responseFDList.toArray(records),"3");
        //init();
        query();
        return null;
    }

    public String doLockMulti() {

        FacesContext context = FacesContext.getCurrentInstance();

        if (selectedRecords.length == 0) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "��Ϣ����ʱ���ִ���", "δѡ����ϸ��¼��"));
            return null;
        }

        startWriteBack(selectedRecords,"3");
        //init();
        query();

        return null;

    }

    public String doUnlockAll() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (this.responseFDList == null) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "��Ϣ����ʱ���ִ���", "�����Ƚ��л����¼��ѯ��"));

            return null;
        }
        if (selectedRecords.length > 0) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "��Ϣ����ʱ���ִ���", "����ѡ����ϸ��¼��"));
            return null;
        }
        T100103ResponseRecord[] records = new T100103ResponseRecord[this.responseFDList.size()];
        startWriteBack(this.responseFDList.toArray(records),"2");
        //init();
        query();

        return null;
    }

    public String doUnlockMulti() {

        FacesContext context = FacesContext.getCurrentInstance();

        if (selectedRecords.length == 0) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "��Ϣ����ʱ���ִ���", "δѡ����ϸ��¼��"));
            return null;
        }

        startWriteBack(selectedRecords,"2");
        //init();
        query();
        
        return null;

    }

    private void startWriteBack(T100103ResponseRecord[] detls, String option) {
        FacesContext context = FacesContext.getCurrentInstance();

        try {
            int result = processWriteBack(detls, option);
            if (result != detls.length) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "������ɡ�", "�ɹ�������" + result + "  ʧ�ܱ�����" + (detls.length - result)));
            } else {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,
                        "�������", "  ������" + result));
            }
        } catch (Exception e) {
            logger.error("�������ִ���", e);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
                    "�������ִ���", null));
        }
        //init();

    }

    /**
     *  20101020 ���ʴ���
     * @param detls
     * @param option   1-�ɹ� 2-ʧ��(��Ϣ����)  3-��Ϣ����
     * @return
     * @throws Exception
     */

    public int processWriteBack(T100103ResponseRecord[] detls, String option) throws Exception {

        if ((!"1".equals(option))
                &&(!"2".equals(option))
                &&(!"3".equals(option))
                ) {
            throw new RuntimeException("��������");
        }

        int count = 0;

        T100104CTL t100104ctl = new T100104CTL();

        for (T100103ResponseRecord detl : detls) {
/*
            if (!detl.getBillstatus().equals(XFBillStatus.BILLSTATUS_CORE_SUCCESS)) {
                logger.error("״̬���ʧ��" + detl.getJournalno());
                continue;
            }
*/
            boolean txResult = false;
            T100104RequestRecord record = new T100104RequestRecord();
            record.setStdjjh(detl.getStdjjh());
            record.setStdqch(detl.getStdqch());
            record.setStdjhkkr(detl.getStdjhhkr());
            //1-�ɹ� 2-ʧ��(��Ϣ����)  3-��Ϣ����
            record.setStdkkjg(option);
            T100104RequestList list = new T100104RequestList();
            list.add(record);
            //���ʷ��ʹ���
            txResult = t100104ctl.start(list);

            if (txResult) {
//                cutpaydetlPk.setJournalno(detl.getJournalno());
//                detl.setBillstatus(XFBillStatus.FD_WRITEBACK_SUCCESS);
//                detlDao.update(cutpaydetlPk, detl);
                count++;
            }
        }

        return count;
    }







    //==============================================================

//    public LazyDataModel<T100103ResponseRecord> getLazyModel() {
//        return lazyModel;
//    }

/*    public void setLazyModel(LazyDataModel<T100103ResponseRecord> lazyModel) {
        this.lazyModel = lazyModel;
    }*/

    public T100103ResponseRecord getSelectedRecord() {
        return selectedRecord;
    }

    public void setSelectedRecord(T100103ResponseRecord selectedRecord) {
        this.selectedRecord = selectedRecord;
    }

    public List<UIT100103ResponseRecord> getResponseFDList() {
        return responseFDList;
    }

    public void setResponseFDList(List<UIT100103ResponseRecord> responseFDList) {
        this.responseFDList = responseFDList;
    }

    public List<T100103ResponseRecord> getResponseXFList() {
        return responseXFList;
    }

    public void setResponseXFList(List<T100103ResponseRecord> responseXFList) {
        this.responseXFList = responseXFList;
    }

    public T100103ResponseRecord getResponseRecord() {
        return responseRecord;
    }

    public void setResponseRecord(T100103ResponseRecord responseRecord) {
        this.responseRecord = responseRecord;
    }

    public int getTotalcount() {
        return totalcount;
    }

    public BigDecimal getTotalamt() {
        return totalamt;
    }

    public SelectItem[] getRegionOptions() {
        return regionOptions;
    }

    public BigDecimal getTotalPrincipalAmt() {
        return totalPrincipalAmt;
    }

    public BigDecimal getTotalInterestAmt() {
        return totalInterestAmt;
    }

    public String[] getRegionCodes() {
        return regionCodes;
    }

    public void setRegionCodes(String[] regionCodes) {
        this.regionCodes = regionCodes;
    }

    public String[] getRegionNames() {
        return regionNames;
    }

    public void setRegionNames(String[] regionNames) {
        this.regionNames = regionNames;
    }

    public T100103ResponseRecord[] getSelectedRecords() {
        return selectedRecords;
    }

    public void setSelectedRecords(T100103ResponseRecord[] selectedRecords) {
        this.selectedRecords = selectedRecords;
    }
}