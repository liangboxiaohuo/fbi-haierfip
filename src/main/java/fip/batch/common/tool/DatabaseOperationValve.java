package fip.batch.common.tool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * ����բ�ڲ��������ݿ��ά��.
 * User: zhanrui
 * Date: 12-12-5
 * Time: ����9:21
 * To change this template use File | Settings | File Templates.
 */

@Component
public class DatabaseOperationValve implements OperationValve {
    private static final Logger logger = LoggerFactory.getLogger(DatabaseOperationValve.class);

    @Override
    public boolean isOpen(String valveID) {
        try {
            //TODO
            return false;
        } catch (Exception e) {
            logger.error("��ȡ�����ļ�����", e);
            return false;
        }
    }

    @Override
    public void openValve(String valveID) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void closeValve(String valveID) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
