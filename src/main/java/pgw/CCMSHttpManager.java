package pgw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * �����Ŵ�ϵͳ�ӿ�.
 * User: zhanrui
 * Date: 2012-1-13
 * Time: 11:15:25
 * To change this template use File | Settings | File Templates.
 */
public class CCMSHttpManager extends HttpManager {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public CCMSHttpManager(String serverUrl) {
        super(serverUrl);
    }
}
