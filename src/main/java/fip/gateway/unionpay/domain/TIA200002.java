package fip.gateway.unionpay.domain;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.io.HierarchicalStreamDriver;
import com.thoughtworks.xstream.io.xml.XmlFriendlyReplacer;
import com.thoughtworks.xstream.io.xml.XppDriver;
import fip.gateway.unionpay.core.TIA;
import fip.gateway.unionpay.core.TIABody;
import fip.gateway.unionpay.core.TIAHeader;
import fip.repository.model.fip.UnipayQryParam;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * ���ջ���ʷ��ѯ����
 * User: zhan  2011-11-01
 */
@XStreamAlias("GZELINK")
public class TIA200002 extends TIA {
    private static final Logger logger = LoggerFactory.getLogger(TIA200002.class);

    public Header INFO = new Header();
    public Body BODY = new Body();

    @Override
    public TIAHeader getHeader() {
        return INFO;
    }

    @Override
    public TIABody getBody() {
        return BODY;
    }

    public static class Header extends TIAHeader {
    }

    public static class Body extends TIABody{
        public BodyHeader QUERY_TRANS;
        public static class BodyHeader {
            public String MERCHANT_ID = "";
            public String QUERY_SN = "";
            public String BEGIN_DATE = "";     //YYYYMMDD ��ʱ��ѯ�����¼
            public String END_DATE = "";
            public String PAGE_NUM;   //Ϊ��Ĭ�ϵ�һҳ
            public String PAGE_SIZE;   //Ϊ��Ĭ�ϴ�С��1000��
            public String RESULT_TYPE = "1";   //1��ȫ����2���ɹ���3��ʧ�ܣ�Ĭ��ȫ����
            public String NEED_DETAIL = "1";   //1�����أ�2�������أ�Ĭ�Ϸ��أ�
            public String QUERY_REMARK = "HAIER TXN QRY";
        }
    }

    public TIA200002(Map paraMap){
        assembleTIA(paraMap);
    }

    @Override
    public String toString() {
        XmlFriendlyReplacer replacer = new XmlFriendlyReplacer("$", "_");
        HierarchicalStreamDriver hierarchicalStreamDriver = new XppDriver(replacer);
        XStream xs = new XStream(hierarchicalStreamDriver);
        xs.processAnnotations(TIA200002.class);
        return "<?xml version=\"1.0\" encoding=\"GBK\"?>" + "\n" + xs.toXML(this);
    }

    private void assembleTIA(Map paraMap) {
        UnipayQryParam record = (UnipayQryParam) paraMap.get("paramBean");
        this.INFO.TRX_CODE = "200002";

        //TODO
        this.INFO.REQ_SN = System.currentTimeMillis() + "";

        this.INFO.VERSION = "03";
        this.BODY.QUERY_TRANS = new TIA200002.Body.BodyHeader();

        this.BODY.QUERY_TRANS.QUERY_REMARK = record.getBIZ_ID();
        this.BODY.QUERY_TRANS.QUERY_SN = record.getQUERY_SN();

        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdf2 = new SimpleDateFormat("yyyyMMdd");
        if (StringUtils.isNotEmpty(record.getBEGIN_DATE())) {
            try {
                this.BODY.QUERY_TRANS.BEGIN_DATE = sdf2.format(sdf1.parse(record.getBEGIN_DATE()));
            } catch (ParseException e) {
                logger.error("���ڸ�ʽ������" + record.getBEGIN_DATE());
                throw new RuntimeException("���ڸ�ʽ������");
            }
        }
        if (StringUtils.isNotEmpty(record.getEND_DATE())) {
            try {
                this.BODY.QUERY_TRANS.END_DATE = sdf2.format(sdf1.parse(record.getEND_DATE()));
            } catch (ParseException e) {
                logger.error("���ڸ�ʽ������" + record.getEND_DATE());
                throw new RuntimeException("���ڸ�ʽ������");
            }
        }
        this.BODY.QUERY_TRANS.RESULT_TYPE = record.getRESULT_TYPE();
        this.BODY.QUERY_TRANS.PAGE_NUM = record.getPAGE_NUM();
    }
}
