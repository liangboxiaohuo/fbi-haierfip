package fip.gateway.ccms.txn;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import fip.gateway.ccms.domain.T200101.T200101Request;
import fip.gateway.ccms.domain.T200101.T200101Response;
import fip.gateway.ccms.domain.T200101.T200101ResponseRecord;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pgw.CCMSHttpManager;
import pgw.HttpManager;

import java.util.ArrayList;
import java.util.List;


/**
 * 正常代发 提取 代发记录
 * User: zhanrui
 * Date: 2012-6-11
 * Time: 13:22:35
 */

public class T200101Handler extends BaseTxnHandler implements java.io.Serializable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private T200101ResponseRecord responseRecord = new T200101ResponseRecord();
    List<T200101ResponseRecord> responseFDList = new ArrayList();
    List<T200101ResponseRecord> responseXFList = new ArrayList();
    List<T200101ResponseRecord> responseGMList = new ArrayList();


    public T200101ResponseRecord getResponseRecord() {
        return responseRecord;
    }

    public void setResponseRecord(T200101ResponseRecord responseRecord) {
        this.responseRecord = responseRecord;
    }

    public List<T200101ResponseRecord> getResponseFDList() {
        return responseFDList;
    }

    public void setResponseFDList(List<T200101ResponseRecord> responseFDList) {
        this.responseFDList = responseFDList;
    }

    public List<T200101ResponseRecord> getResponseXFList() {
        return responseXFList;
    }

    public void setResponseXFList(List<T200101ResponseRecord> responseXFList) {
        this.responseXFList = responseXFList;
    }

    public List<T200101ResponseRecord> getResponseGMList() {
        return responseGMList;
    }

    public void setResponseGMList(List<T200101ResponseRecord> responseGMList) {
        this.responseGMList = responseGMList;
    }

    public String test() {
        return "about.xhtml";
    }

    public List<T200101ResponseRecord> getAllFDRecords() {
        //查询 房贷/消费信贷（1/2） 数据
        setResponseFDList(start("1"));
        return this.getResponseFDList();
    }

    public List<T200101ResponseRecord> getAllXFRecords() {
        //查询 房贷/消费信贷（1/2） 数据
        setResponseXFList(start("2"));
        return this.getResponseXFList();

    }
    public List<T200101ResponseRecord> getAllGRMFRecords() {
        //查询 房贷/消费信贷/个人买方信贷（1/2/3） 数据
        setResponseGMList(start("3"));
        return this.getResponseGMList();

    }

    public String query() {
        setResponseFDList(start("1"));
        setResponseXFList(start("2"));

        return null;
    }

    /**
     * @param systemcode 要查询的系统别    房贷/消费信贷（1/2） 数据
     * @return
     */
    public List<T200101ResponseRecord> start(String systemcode) {
        XStream xstream = new XStream(new DomDriver());
        xstream.processAnnotations(T200101Request.class);
        xstream.processAnnotations(T200101Response.class);

        T200101Request request = new T200101Request();

        request.initHeader("0100", "200101", "3");

        //查询 房贷/消费信贷（1/2） 数据
        request.setStdcxlx(systemcode);
        int pkgcnt = 100;
        int startnum = 1;
        request.setStdymjls(String.valueOf(pkgcnt));
        //request.setStdqsjls("1");

        CCMSHttpManager httpManager = new CCMSHttpManager(SERVER_ID);

        List<T200101ResponseRecord> responseList = new ArrayList();
        int totalcount = processTxn(responseList, httpManager, xstream, request, pkgcnt, startnum);
        if (totalcount==0) {
            return responseList;
        }
        logger.info("received list zise:" + responseList.size());
        if (totalcount != responseList.size()) {
            logger.error("获取还款数据笔数有误！应收笔数：" + responseList.size() + "实收笔数：" + totalcount);
            throw new RuntimeException("获取还款数据笔数有误.");
        }
        return responseList;
    }

    /**
     * 递归获取服务器数据
     */
    public int processTxn(List<T200101ResponseRecord> responseList,
                          HttpManager httpManager, XStream xstream, T200101Request request,
                          int pkgcnt, int startnum) {
        
        //查询 房贷/消费信贷（1/2） 数据
        request.setStdqsjls(String.valueOf(startnum));


        String strXml = "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xstream.toXML(request);
        //System.out.println(strXml);

        //发送请求
        String responseBody = httpManager.doPostXml(strXml);
        if (StringUtils.isEmpty(responseBody)) {
            return 0;
        }
        T200101Response response = (T200101Response) xstream.fromXML(responseBody);

        //头部总记录数
        String std400acur = response.getStd400acur();
        if (std400acur == null || std400acur.equals("")) {
            std400acur = "0";
        }
        int totalcount = Integer.parseInt(std400acur);


        if (totalcount == 0) {

        } else {
            List<T200101ResponseRecord> tmpList = response.getBody().getContent();

            int currCnt = tmpList.size();
            logger.info("totalcount:" + totalcount + " currCnt:" + currCnt + " startnum:" + startnum);

            //打包到返回list中
            for (T200101ResponseRecord record : tmpList) {
                responseList.add(record);
            }

            //一个包不可以处理完
            if (totalcount > pkgcnt) {
                startnum += pkgcnt;
                if (startnum <= totalcount) {
                    processTxn(responseList, httpManager, xstream, request, pkgcnt, startnum);
                }
            }
        }


        //uploadCutpayResultBatch(response.getBody().getContent());
        return totalcount;
    }

}
