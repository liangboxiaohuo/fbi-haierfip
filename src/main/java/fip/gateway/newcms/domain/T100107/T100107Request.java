package fip.gateway.newcms.domain.T100107;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import fip.gateway.newcms.domain.common.MsgHeader;

/**
 * ��ѯ���ں�ķ�Ϣ���ۼ�¼�Լ��Ƿ�������.
 * User: zhanrui
 * Date: 2012-10-10
 * Time: 17:18:39
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("ROOT")
public class T100107Request extends MsgHeader {
    //��ѯ���� 1������  2�������Ŵ�
    private String stdcxlx;
    //��ʼ��¼��
    private String stdqsjls;
    //ҳ���¼����������¼����
    private String stdymjls;

    public String getStdcxlx() {
        return stdcxlx;
    }

    public void setStdcxlx(String stdcxlx) {
        this.stdcxlx = stdcxlx;
    }

    public String getStdqsjls() {
        return stdqsjls;
    }

    public void setStdqsjls(String stdqsjls) {
        this.stdqsjls = stdqsjls;
    }

    public String getStdymjls() {
        return stdymjls;
    }

    public void setStdymjls(String stdymjls) {
        this.stdymjls = stdymjls;
    }

}
