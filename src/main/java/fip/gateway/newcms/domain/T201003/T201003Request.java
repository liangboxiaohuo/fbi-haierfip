package fip.gateway.newcms.domain.T201003;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import fip.gateway.newcms.domain.common.MsgHeader;

/**
 * �����Ŵ��׸��˺Ų�ѯ��201003��
 * User: zhanrui
 * Date: 2010-8-27
 * Time: 17:11:06
 * To change this template use File | Settings | File Templates.
 */

@XStreamAlias("ROOT")
public class T201003Request extends MsgHeader {
    private String stdsqdh;

    public String getStdsqdh() {
        return stdsqdh;
    }

    public void setStdsqdh(String stdsqdh) {
        this.stdsqdh = stdsqdh;
    }

}
