package fip.batch.common.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pub.platform.advance.utils.PropertyManager;

/**
 * Default ����բ�ڲ����������ļ�ά��.
 * User: zhanrui
 * Date: 12-12-5
 * Time: ����9:21
 * To change this template use File | Settings | File Templates.
 */

@Component
public class PropertyFileOperationValve implements OperationValve {
    private static final Logger logger = LoggerFactory.getLogger(PropertyFileOperationValve.class);

    @Override
    public boolean isOpen(String valveID) {
        try {
            //String mode = PropertyManager.getProperty("cron_task_mode");
            String mode = PropertyManager.getProperty(valveID);
            return mode != null && !"".equals(mode) && "open".equals(mode);
        } catch (Exception e) {
            logger.error("��ȡ�����ļ�����", e);
            return false;
        }
    }

    @Override
    public void openValve(String valveID) {
        //TODO  set open
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void closeValve(String valveID) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
