package fip.gateway.newcms.domain.T100107;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import fip.gateway.newcms.domain.common.MsgHeader;

/**
 * 查询逾期后的罚息代扣记录以及是否已锁定.
 * User: zhanrui
 * Date: 2012-10-10
 * Time: 17:18:39
 * To change this template use File | Settings | File Templates.
 */
@XStreamAlias("ROOT")
public class T100107Request extends MsgHeader {
    //查询类型 1：房贷  2：消费信贷
    private String stdcxlx;
    //起始记录数
    private String stdqsjls;
    //页面记录数（本报记录数）
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
