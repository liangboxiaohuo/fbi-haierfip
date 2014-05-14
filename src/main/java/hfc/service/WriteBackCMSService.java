package hfc.service;

import fip.gateway.newcms.domain.T201001.T201001Request;
import fip.repository.dao.XfappMapper;
import fip.repository.model.Xfapp;
import hfc.common.CmsAppStatusEnum;
import hfc.common.ConstSqlString;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pub.platform.db.ConnectionManager;
import pub.platform.db.DatabaseConnection;
import pub.platform.db.RecordSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhangxiaobo
 * Date: 11-8-12
 * Time: ����10:59
 * To change this template use File | Settings | File Templates.
 */
@Service("writeBackCMSService")
public class WriteBackCMSService {

    private Log logger = LogFactory.getLog(this.getClass());
    @Autowired
    private XfappMapper xfappMapper;

    public Xfapp getXfappByPkid(String pkid) {

        return xfappMapper.selectByPrimaryKey(pkid);
    }

    // appstatus==��1���������ύ���ɷ���
    public boolean isSendable(Xfapp xfapp) {
        if (xfapp != null && CmsAppStatusEnum.NEED_SEND.getCode().equalsIgnoreCase(xfapp.getAppstatus())) {
            return true;
        }
        return false;
    }

    // update appstatus
    public int updateAppStatus(Xfapp xfapp, String status) {
        int rtnCnt = 0;
        if (xfapp != null) {
            xfapp.setAppstatus(status);
            rtnCnt = xfappMapper.updateByPrimaryKey(xfapp);
        }
        return rtnCnt;
    }

    public List<T201001Request> getWriteBackRecords() {
        T201001Request reqRecord = null;
        List<T201001Request> requestList = null;
        RecordSet recordSet = qryRecordsBySql(ConstSqlString.WRITE_BACK_RECORDS_SQL);
        if (recordSet != null || recordSet.getRecordCount() >= 1) {
            requestList = new ArrayList<T201001Request>();
            while (recordSet.next()) {
                reqRecord = new T201001Request();
                reqRecord.initHeader("0200", "201001", "2");
                reqRecord.setStdtermtrc(new SimpleDateFormat("hhmmss").format(new Date()));    //TODO   ��ˮ��
                reqRecord.setStdsqdh(recordSet.getString("appno"));    //���뵥��
                reqRecord.setStdsqlsh(recordSet.getString("pkid")); // ������ˮ��
                reqRecord.setStdurl("http://10.143.19.203:7001/faces/attachment/download.xhtml?appno=" + recordSet.getString("appno"));    //�ļ�URL
                reqRecord.setStdkhxm(recordSet.getString("name"));    //�ͻ�����
                reqRecord.setStdzjlx(recordSet.getString("idtype"));    // ֤������
                reqRecord.setStdzjhm(recordSet.getString("id"));          // ֤������
                reqRecord.setStdlxfs(recordSet.getString("phone1"));    //��ϵ��ʽ
                reqRecord.setStdxb(recordSet.getString("gender"));    //  �Ա�
                reqRecord.setStdcsrq(recordSet.getString("birthday"));    // ��������
                reqRecord.setStdgj("CHN");    // TODO ����
                reqRecord.setStdkhxz("0");    // TODO �ͻ�����
                /**
                 * 0-��������
                 1-	������Ա

                 */
                reqRecord.setStdhyzk(recordSet.getString("marriagestatus"));    // ����״��
                reqRecord.setStdjycd(recordSet.getString("edulevel"));    // �����̶�
                reqRecord.setStdkhly("99");    //  �ͻ���Դ 99-����
                /**
                 01-	���ѽ���
                 02-	��������
                 03-	ý����
                 04-	�̻��Ƽ�
                 05-	רҵ�Ƽ�
                 06-	����
                 99-����
                 */
                reqRecord.setStdhjszd(recordSet.getString("residenceadr"));    //�������ڵ�
                int adr = Integer.parseInt(recordSet.getString("residenceadr"));
                switch (adr) {
                    case 1://  ����
                        reqRecord.setStdhkxz("1");
                        break;
                    case 2:   //  ��� //  ���� // �⼮��۰�̨
                    case 3:
                        reqRecord.setStdhkxz("4");
                        break;
                    default: // ����
                        reqRecord.setStdhkxz("5");
                        break;
                }
                /**
                 * 1-	���ػ����Ҿ�ס����5������
                 2-	���ػ����Ҿ�ס����5������
                 3-	�Ǳ��ػ��ڵ���ס����5������
                 4-	�Ǳ��ػ��ڵ���ס����5������
                 5-	����
                 */
                reqRecord.setStdrhzxqk("6");    // �����������
                /**
                 * 0-��������ÿ�����������������Ƿ��¼
                 1-��������ÿ����������ϡ���������������Ƿ��¼
                 2-��������ÿ�����������������Ƿ��¼
                 3-��δ���
                 4-�н�����ڳ���������
                 6-	�н���ѻ�������������

                 */
                reqRecord.setStdfdyqqs("0");    //  ���ż�¼�����۷��������������  0
                reqRecord.setStdffdyqqs("0");    //  ���ż�¼�����۷Ƿ��������������  0
                reqRecord.setStdjtdz(recordSet.getString("currentaddress"));    //��ͥ��ַ
                reqRecord.setStdjtdzdh(recordSet.getString("phone2"));    //��ͥ��ַ�绰
                reqRecord.setStdzzxz(recordSet.getString("chousingsts"));    // סլ����
                //ְҵ
                int zy = Integer.parseInt(recordSet.getString("clienttype"));
                switch (zy) {
                    case 1:
                        reqRecord.setStdzy("0");
                        break;
                    case 2:
                        reqRecord.setStdzy("3");
                        break;
                    case 3:
                        reqRecord.setStdzy("4");
                        break;
                    case 4:
                        reqRecord.setStdzy("X");
                        break;
                    default:
                        reqRecord.setStdzy("Y");
                        break;
                }
                reqRecord.setStdzw(recordSet.getString("title"));    //  ְ��
                reqRecord.setStdzc(recordSet.getString("qualification"));    //  ְ��
                reqRecord.setStdgzdw(recordSet.getString("company"));    //������λ
                reqRecord.setStdsshy("U");    //  ������ҵ    U
                /**
                 * A-	ũ���֡�������ҵ
                 B-	�ɾ�ҵ
                 C-	����ҵ
                 D-	������ȼ����ˮ�������͹�Ӧҵ
                 E-	����ҵ
                 F-	��ͨ���䡢�ִ�������ҵ
                 G-	��Ϣ���䡢�������������ҵ
                 H-	����������ҵ
                 I-	ס�޺Ͳ���ҵ
                 J-	����ҵ
                 K-	���ز�ҵ
                 L-	���޺��������ҵ
                 M-	��ѧ�о�����������ҵ�͵��ʿ���ҵ
                 N-	ˮ���������͹�����ʩ����ҵ
                 O-	����������������ҵ
                 P-	����
                 Q-	��������ᱣ�Ϻ���ḣ��ҵ
                 R-	�Ļ�������������ҵ
                 S-	��������������֯
                 T-	������֯
                 U-	δ֪
                 */
                reqRecord.setStdszqyrs("2");    //  ������ҵ����       2
                /**
                 * 1-  	500������
                 2-    	100-500��
                 3-	  10-99��
                 4-	  1-9��
                 */
                String servyears = recordSet.getString("servfrom");
                if (servyears == null) {
                    servyears = "0000";
                } else {
                    int length = servyears.trim().length();
                    switch (length) {
                        case 1:
                            servyears = "0" + servyears + "00";
                            break;
                        case 2:
                            servyears = servyears + "00";
                            break;
                        case 0:
                            servyears = "0000";
                            break;
                        default:
                            servyears = "0000";
                    }
                }
                reqRecord.setStdgznx(servyears);    //Ŀǰ������������
                reqRecord.setStdlxr(recordSet.getString("linkman"));    //��ϵ��
                reqRecord.setStdlxrdh(recordSet.getString("linkmanphone1"));    //��ϵ�˵绰/
                String grysr = recordSet.getString("monthlypay");
                reqRecord.setStdgrysr(grysr);    //����������
                String pgrysr = recordSet.getString("PMONTHLYPAY");
                double dl_pgrysr = (pgrysr == null) ? 0 : Double.parseDouble(pgrysr);
                double jtysr = Double.parseDouble(grysr) + dl_pgrysr;
                reqRecord.setStdjtwdsr(String.format("%.2f", jtysr));    //  ��ͥ�ȶ����� ���˻�����ż�ϼ�
                reqRecord.setStdzwzc(recordSet.getString("MONPAYAMT"));    //  ÿ������ծ��֧��
                reqRecord.setStddkzje(recordSet.getString("APPAMT"));     //�����ܽ��
                reqRecord.setStddkqx(String.valueOf(recordSet.getString("DIVID")));  //��������
                reqRecord.setStdftfs(recordSet.getString("SHARETYPE"));     //��̯��ʽ
                reqRecord.setStdkhsxfl(String.valueOf(Double.parseDouble(recordSet.getString("COMMISSIONRATE")) / 1000));   // �ͻ���������
                reqRecord.setStdshsxfl(String.valueOf(Double.parseDouble(recordSet.getString("SHCOMMISSIONRATE")) / 100)); //�̻���������
                reqRecord.setStdhkr(recordSet.getString("COMPAYDATE"));    // ������
                reqRecord.setStdkhjl(recordSet.getString("operid"));   // �ͻ�����
                reqRecord.setStdspmc(recordSet.getString("commnmtype"));  //��Ʒ����
                reqRecord.setStdspxh(recordSet.getString("commnmtype"));    //��Ʒ�ͺ�
                double spamt = Double.parseDouble(recordSet.getString("commamt"));
                int spnum = Integer.parseInt(recordSet.getString("commnum"));
                String spdj = String.format("%.2f", spamt / spnum);
                reqRecord.setStdspdj(spdj); // ��Ʒ����
                reqRecord.setStdspsl(String.valueOf(spnum));  // ��Ʒ����
                reqRecord.setStdsfk(recordSet.getString("RECEIVEAMT"));  // �׸���
                reqRecord.setStdhkzh(recordSet.getString("BANKACTNO"));// �����˺�
                requestList.add(reqRecord);
            }
        }
        return requestList;
    }

    private RecordSet qryRecordsBySql(String sql) {
        DatabaseConnection conn = null;
        RecordSet recordSet = null;
        try {
            conn = ConnectionManager.getInstance().getConnection();
            logger.info(sql);
            recordSet = conn.executeQuery(sql);
            if (recordSet != null && recordSet.getRecordCount() == 0) {
                logger.info("δ�ҵ�Ҫ��ѯ����Ϣ" + sql);
               // throw new RuntimeException("δ�ҵ�Ҫ��ѯ����Ϣ");
            }
            return recordSet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            ConnectionManager.getInstance().releaseConnection(conn);
        }
    }
}
