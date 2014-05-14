package fip.gateway.newcms.controllers;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import fip.gateway.newcms.domain.T100107.T100107Request;
import fip.gateway.newcms.domain.T100107.T100107Response;
import fip.gateway.newcms.domain.T100107.T100107ResponseRecord;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import pgw.NewCmsManager;

import java.util.ArrayList;
import java.util.List;


/**
 * ��ǰ���� ��ȡ �����¼  �������Ŵ� ���޴��ཻ�� 2010/11/20��
 * User: zhanrui
 * Date: 2010-8-27
 * Time: 13:22:35
 * To change this template use File | Settings | File Templates.
 */

public class T100107CTL extends BaseCTL implements java.io.Serializable {

    private Log logger = LogFactory.getLog(this.getClass());
    private T100107ResponseRecord responseRecord = new T100107ResponseRecord();
    List<T100107ResponseRecord> responseFDList = new ArrayList();
    List<T100107ResponseRecord> responseXFList = new ArrayList();
    List<T100107ResponseRecord> responseGMList = new ArrayList();


    public T100107ResponseRecord getResponseRecord() {
        return responseRecord;
    }

    public void setResponseRecord(T100107ResponseRecord responseRecord) {
        this.responseRecord = responseRecord;
    }

    public List<T100107ResponseRecord> getResponseFDList() {
        return responseFDList;
    }

    public void setResponseFDList(List<T100107ResponseRecord> responseFDList) {
        this.responseFDList = responseFDList;
    }

    public List<T100107ResponseRecord> getResponseXFList() {
        return responseXFList;
    }

    public void setResponseXFList(List<T100107ResponseRecord> responseXFList) {
        this.responseXFList = responseXFList;
    }

    public List<T100107ResponseRecord> getResponseGMList() {
        return responseGMList;
    }

    public void setResponseGMList(List<T100107ResponseRecord> responseGMList) {
        this.responseGMList = responseGMList;
    }

    public final static void main(String[] args) throws Exception {

        T100107CTL ctl = new T100107CTL();
        ctl.start("1");

    }

    public String test() {
        return "about.xhtml";
    }

    public List<T100107ResponseRecord> getAllFDRecords() {
        //��ѯ ����/�����Ŵ���1/2�� ����
        setResponseFDList(start("1"));
        return this.getResponseFDList();
    }

    public List<T100107ResponseRecord> getAllXFRecords() {
        //��ѯ ����/�����Ŵ���1/2�� ����
        setResponseXFList(start("2"));
        return this.getResponseXFList();

    }

    public List<T100107ResponseRecord> getAllGRMFRecords() {
        //��ѯ ����/�����Ŵ�/�������Ŵ���1/2/3�� ����
        setResponseGMList(start("3"));
        return this.getResponseGMList();

    }


    public String query() {
        setResponseFDList(start("1"));
        setResponseXFList(start("2"));

        return null;
    }

    /**
     * @param systemcode Ҫ��ѯ��ϵͳ��    ����/�����Ŵ���1/2�� ����
     * @return
     */
    public List<T100107ResponseRecord> start(String systemcode) {
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(T100107Request.class);
        xstream.processAnnotations(T100107Response.class);

        T100107Request request = new T100107Request();

        request.initHeader("0100", "100107", "3");

        //��ѯ ����/�����Ŵ���1/2�� ����
        request.setStdcxlx(systemcode);
        int pkgcnt = 100;
        int startnum = 1;
        request.setStdymjls(String.valueOf(pkgcnt));
        //request.setStdqsjls("1");

        NewCmsManager ncm = new NewCmsManager();

        List<T100107ResponseRecord> responseList = new ArrayList();
        int totalcount = processTxn(responseList, ncm, xstream, request, pkgcnt, startnum);
        logger.info("received list zise:" + responseList.size());
        if (totalcount != responseList.size()) {
            logger.error("��ȡ�������ݱ�������Ӧ�ձ�����" + responseList.size() + "ʵ�ձ�����" + totalcount);
            throw new RuntimeException("��ȡ�������ݱ�������.");
        }
        return responseList;
    }

    /**
     * �ݹ��ȡ����������
     *
     * @param responseList
     * @param ncm
     * @param xstream
     * @param request
     * @param pkgcnt
     * @param startnum
     * @return
     */
    public int processTxn(List<T100107ResponseRecord> responseList,
                          NewCmsManager ncm, XStream xstream, T100107Request request,
                          int pkgcnt, int startnum) {
        
        //��ѯ ����/�����Ŵ���1/2�� ����
        request.setStdqsjls(String.valueOf(startnum));


        String strXml = "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xstream.toXML(request);
        //System.out.println(strXml);

        //��������
        String responseBody = ncm.doPostXml(strXml);

        T100107Response response = (T100107Response) xstream.fromXML(responseBody);

        //ͷ���ܼ�¼��
        String std400acur = response.getStd400acur();
        if (std400acur == null || std400acur.equals("")) {
            std400acur = "0";
        }
        int totalcount = Integer.parseInt(std400acur);


        if (totalcount == 0) {

        } else {
            List<T100107ResponseRecord> tmpList = response.getBody().getContent();

            int currCnt = tmpList.size();
            logger.info(currCnt);
            logger.info("totalcount:" + totalcount + " currCnt:" + currCnt + " startnum:" + startnum);

            //���������list��
            for (T100107ResponseRecord record : tmpList) {
                responseList.add(record);
            }

            //һ���������Դ�����
            if (totalcount > pkgcnt) {
                startnum += pkgcnt;
                if (startnum <= totalcount) {
                    processTxn(responseList, ncm, xstream, request, pkgcnt, startnum);
                }
            }
        }


        //uploadCutpayResultBatch(response.getBody().getContent());
        return totalcount;
    }

}
