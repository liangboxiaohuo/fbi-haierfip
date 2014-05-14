package fip.service.fip;

import fip.gateway.ftp.FtpClient;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.platform.advance.utils.PropertyManager;

import javax.jms.JMSException;
import java.io.IOException;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 13-4-1
 * Time: ����1:15
 * To change this template use File | Settings | File Templates.
 */
@Service
public class FtpSbsFileService {

    private static final Logger logger = LoggerFactory.getLogger(FtpSbsFileService.class);

    @Autowired
    private JobLogService jobLogService;

    //  ftp����sbs��ȡ������ϸ�ļ�����
    public String[] obtainSbsZfqsMsgs(String date) throws JMSException, IOException {
        logger.info("FTP ����SBS ִ�н��׿�ʼ----------��");
        // ����sbs��ȡ����anonymous ident
        FtpClient ftpClient = new FtpClient(PropertyManager.getProperty("SBS_HOSTIP"), "anonymous", "");
        String lines = ftpClient.readCotent("pub/print/" + date, PropertyManager.getProperty("SBS_ZFQS_FILE_NAME"));
        logger.info("���ڣ�" + date + "  SBS�ܷ��˻�������ϸ��ȡ������");
        ftpClient.logout();
        // ��¼��־
        appendFtpSbsJoblog(UUID.randomUUID().toString(), date);
        logger.info("��ȡ��sbs�ļ�����:  \n" + lines);
        return StringUtils.isEmpty(lines) ? null : lines.split("\n");
    }

    public void appendFtpSbsJoblog(String pkid, String bizDate) {
        jobLogService.insertNewJoblog(pkid, "dep_sbs_zfqs", "ftp���Ӷ�ȡsbs�ļ�", "ftp���Ӷ�ȡsbs�ڲ��ܷ��˻��������ļ�,ҵ������[" + bizDate + "]", "9999", "ϵͳ����Ա");
    }
}
