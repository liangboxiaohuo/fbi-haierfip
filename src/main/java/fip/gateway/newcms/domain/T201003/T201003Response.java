package fip.gateway.newcms.domain.T201003;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import fip.gateway.newcms.domain.common.MsgHeader;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 2010-10-10
 * Time: 17:18:39
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("ROOT")
public class T201003Response extends MsgHeader {
    String stdshmc; // �̻�����
    String stdkkzh; // �ۿ��˺�
    String stdcwxx;  //������Ϣ    1-�޲�ѯ�����뵥�ţ� 2-��ǰ����绰δά����Ӧ�ۿ��˺�


    public String getStdkkzh() {
        return stdkkzh;
    }

    public void setStdkkzh(String stdkkzh) {
        this.stdkkzh = stdkkzh;
    }

    public String getStdcwxx() {
        return stdcwxx;
    }

    public void setStdcwxx(String stdcwxx) {
        this.stdcwxx = stdcwxx;
    }

    public String getStdshmc() {
        return stdshmc;
    }

    public void setStdshmc(String stdshmc) {
        this.stdshmc = stdshmc;
    }

    public static void main(String[] args) {
       /* String str = ConstSqlString.TEST_201002_RESPONSE_XML;
        T201002Response res = (T201002Response)T201002Response.toObject(T201002Response.class, str);
        System.out.println(res.getStdsqdzt());*/
    }

}
