package fip.gateway.unionpay;

import com.ccb.util.StringUtil;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@Deprecated
public class FbidepMessageListener implements MessageListener {
    private static final Logger logger = LoggerFactory.getLogger(FbidepMessageListener.class);

    @Autowired
    private RtnMessageHandler rtnMessageHandler;

    @Override
    public void onMessage(Message message) {
        if (message != null && message instanceof TextMessage) {
            String msgContent = null;
            String rtnCode = "";
            String rtnMsg = "";
            try {
                rtnCode = message.getStringProperty("depReturnCode");
                rtnMsg = message.getStringProperty("depReturnMsg");
                msgContent = ((TextMessage) message).getText();
                logger.info("FIP�յ�MQ��Ϣ���ı����ݣ�\n" + msgContent);
            } catch (JMSException e) {
                logger.error("FIP�յ���Ϣʱ����", e);
            }

            if ("200".equals(rtnCode)) {
                if (!StringUtils.isEmpty(msgContent)) {
                    int txCode = Integer.parseInt(StringUtil.getSubstrBetweenStrs(msgContent, "<TRX_CODE>", "</TRX_CODE>"));
                    // TODO �Ƿ��б�Ҫ���ӿڴ���д��properties�ļ�
                    switch (txCode) {
                        case 100001:  // TODO Ӳ����
                            rtnMessageHandler.handle100001Message(msgContent);
                            break;
                        case 100004:  // TODO Ӳ����
                            rtnMessageHandler.handle100004Message(msgContent);
                            break;
                        case 200001:  // TODO Ӳ����
                            rtnMessageHandler.handle200001BatchMessage(msgContent);
                            break;
                    }
                } else {
                    //δ�յ������ķ��ؽ����ֻ����DEP�ķ��ؽ��
                    logger.error("FIP�յ���Ϣʱ������ϢΪ�ջ����ʹ���");
                }
            }else if ("400".equals(rtnCode)) {
                logger.error("��Ϣ���������ĳ���:" + rtnMsg);
                //TODO
            }else if ("504".equals(rtnCode)) {
                logger.error("����ʱ:" + rtnMsg);
                //TODO
            }else{
                logger.error("�������:" + rtnMsg);
                //TODO
            }
        } else {
            logger.error("FIP�յ���Ϣʱ������ϢΪ�ջ����ʹ���");
        }
    }

}
