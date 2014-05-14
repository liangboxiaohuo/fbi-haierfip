<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="javax.sql.rowset.CachedRowSet" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="java.util.Vector" %>
<%@ page import="pub.platform.db.ConnectionManager" %>
<%@ page import="pub.platform.db.DatabaseConnection" %>
<%@ page import="pub.platform.db.DB2_81" %>
<%@ page import="hfc.xf.XFConf" %>
<%@ page import="pub.cenum.Level" %>
<%@ page import="pub.platform.utils.BusinessDate" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="hfc.common.AppCommonEnum" %>
<%--
===============================================
Title: �����Ŵ�-�������ѷ��ڸ���������
Description: �������ѷ��ڸ��������顣
 * @version  $Revision: 1.0 $  $Date: 2009/03/02 08:20:31 $
 * @author liujian
 * <p/>�޸ģ�$Author: liuj $
===============================================
--%>
<%
    request.setCharacterEncoding("GBK");
    DecimalFormat df = new DecimalFormat("###,###,###,##0.00");

    String APPNO = request.getParameter("appno");         //���뵥���
    String SENDFLAG = request.getParameter("sendflag");   //������Դ 1 ��ѯ ҳ��ֻ�� 2 ����ɹ� 3 �޸�ʧ�� 4 ��������
    String readonly = "";
    if (SENDFLAG != null) {
        if (SENDFLAG.equals("2")) {
%>
<script type="text/javascript">
    alert("�ɹ�¼�롣");
    top.window.opener.document.getElementById("queryForm:btnQuery").click();
    window.close();
</script>
<%
} else if (SENDFLAG.equals("1")) {
    readonly = "readonly";
} else if (SENDFLAG.equals("4")) {
%>
<script type="text/javascript">
    alert("��Ϣ�޸�ʧ�ܣ�\r\n�����ն��Ѹ��¸����ݣ������´򿪸�ҳ������޸Ĳ�����");
    top.window.opener.document.getElementById("queryForm:btnQuery").click();
    window.close();
</script>
<%
} else if (SENDFLAG.equals("3")) {
%>
<script type="text/javascript">
    alert("��Ϣ�޸ĳ����쳣������¼����Ϣ�Ƿ���ȷ��");
    top.window.opener.document.getElementById("queryForm:btnQuery").click();
    window.close();
</script>
<%
        }
    }
    String SID = "";                                       //�����̳Ǳ��
    String ORDERNO = "";                                   //�����̳ǵ���
    String REQUESTTIME = "";                               //�����̳Ƕ�������ʱ��
    APPNO = (APPNO == null) ? "" : APPNO;
    if (APPNO != null) {
        DB2_81 manager = new DB2_81();
        String sql1 = "", sql2 = "";
        if (!APPNO.equals("")) {
            sql1 = "select a.PREPAY_CUTPAY_TYPE,xp.CITY,xp.PROVINCE,a.RECVERSION,xp.CHANNEL as UNIONCHANNEL,a.COMMISSIONRATE,a.SHCOMMISSIONRATE,COMPAYDATE,a.SHARETYPE,a.COMMISSIONTYPE," +
                    " c.SERVFROMMONTH,c.LIVEFROMMONTH,p.SERVFROMMONTH PSERVFROMMONTH,p.LIVEFROMMONTH PLIVEFROMMONTH," +
                    " xp.BANKACTNAME,ct.SOCIALSECURITYID,ct.ANNUALINCOME,ct.ETPSCOPTYPE,a.SALER,a.APPSALARYRATE,a.MONTHPAYSLRRATE,c.SLRYEVETYPE, " +
                    " ccd.CCVALIDPERIOD,ccd.CCDYNUM,ccd.CCXYNUM,ccd.CCCDNUM,ccd.CCDYAMT,ccd.CCXYAMT,ccd.CCCDAMT," +
                    " ccd.CCDYNOWBAL,ccd.CCXYNOWBAL , ccd.CCCDNOWBAL ,ccd.CCDYRPMON , ccd.CCXYRPMON , ccd.CCCDRPMON ," +
                    " ccd.CCDYYEARRPTIME,ccd.CCXYYEARRPTIME,ccd.CCCDYEARRPTIME,ccd.CCDYNOOVERDUE,ccd.CCXYNOOVERDUE,ccd.CCCDNOOVERDUE," +
                    " ccd.CCDY1TIMEOVERDUE,ccd.CCXY1TIMEOVERDUE,ccd.CCCD1TIMEOVERDUE,ccd.CCDY2TIMEOVERDUE,ccd.CCXY2TIMEOVERDUE,ccd.CCCD2TIMEOVERDUE," +
                    " ccd.CCDYM3TIMEOVERDUE,ccd.CCXY3TIMEOVERDUE,ccd.CCCD3TIMEOVERDUE,ccd.ACAGE,ccd.ACWAGE,ccd.ACZX1,ccd.ACZX2,ccd.ACZX3," +
                    " ccd.ACFACILITY,ccd.ACRATE,ccd.CCRPTOTALAMT,ccd.CCDIVIDAMT," +
                    "c.CLIENTNO,to_char(c.BIRTHDAY,'yyyyMMdd') BIRTHDAY,c.GENDER,c.NATIONALITY,c.MARRIAGESTATUS,c.HUKOUADDRESS,c.CURRENTADDRESS," +
                    "c.COMPANY,c.TITLE,c.QUALIFICATION,c.EDULEVEL,c.PHONE1,c.PHONE2," +
                    "c.PHONE3,c.NAME,c.CLIENTTYPE,c.DEGREETYPE,c.COMADDR,c.SERVFROM,c.RESIDENCEADR,c.HOUSINGSTS," +
                    "c.HEALTHSTATUS,c.MONTHLYPAY,c.BURDENSTATUS,c.SOCIALSECURITY,c.LIVEFROM,c.PC,c.COMPC,c.RESDPC,c.RESDADDR,c.EMAIL," +
                    "p.NAME  PNAME ,p.IDTYPE PIDTYPE ,p.ID PID ,p.COMPANY PCOMPANY ,p.TITLE PTITLE ,p.PHONE1 PPHONE1 ," +
                    "p.PHONE3 PPHONE3 ,p.CLIENTTYPE PCLIENTTYPE ,p.SERVFROM PSERVFROM ,p.MONTHLYPAY PMONTHLYPAY ," +
                    "p.LIVEFROM PLIVEFROM," +
                    "a.CHANNEL,a.ADDR,a.TOTALNUM,a.TOTALAMT,a.RECEIVEAMT,a.APPAMT,a.DIVID," +
                    "d.ACTOPENINGBANK,d.BANKACTNO,d.XY,d.XYR,d.DY,d.DYW,d.ZY,d.ZYW,d.BZ,d.BZR,d.CREDITTYPE,d.MONPAYAMT," +
                    "d.LINKMAN,d.LINKMANGENDER,d.LINKMANPHONE1,d.LINKMANPHONE2,d.APPRELATION,d.LINKMANADD,d.LINKMANCOMPANY,d.ACTOPENINGBANK_UD," +
                    "a.IDTYPE,a.ID,to_char(a.APPDATE,'yyyyMMdd') as APPDATE,a.APPSTATUS,a.SID,a.ORDERNO,a.REQUESTTIME " +
                    "from XFCLIENT c,CMINDVCLIENT ct,XFAPPADD d,XFAPP a " +
                    "left outer join XFCLIENT p on a.APPNO=p.APPNO and p.XFCLTP='2' " +
                    "left join  xfcreditinfobatch ccd on a.appno=ccd.appno left join XFAPPREPAYMENT xp on a.appno=xp.appno " +
                    "where a.APPNO='" + APPNO + "' and a.APPNO=c.APPNO and c.XFCLTP='1' " +
                    "and a.APPNO=d.APPNO and c.IDTYPE=ct.IDTYPE and c.id=ct.id ";
            sql2 = " select t.COMMNMTYPE,t.NUM,t.AMT,t.RECEIVEAMT,COMMNO,APPTYPE from xfappcommbatch t " +
                    " where t.appno='" + APPNO + "' order by t.APPNO ";
        }
        String PREPAY_CUTPAY_TYPE = "";              //�׸����Ƿ����
        String RECVERSION = "";                //�汾��
        String COMMISSIONRATE = "";            //�ͻ���������
        String SHCOMMISSIONRATE = "";          //�̻���������
        String COMPAYDATE = "";                //������
        String SHARETYPE = "";                 //��̯��ʽ
        String COMMISSIONTYPE = "";            //�ͻ������ѽ��ɷ�ʽ
        //������Ϣ
        String CCVALIDPERIOD = "";               //������Ϣ��Чʱ�� ENUM=CCValidPeriod
        String CCDYNUM = "";                     //��Ѻ��������(����)
        String CCXYNUM = "";                     //���ô�������(����)
        String CCCDNUM = "";                     //���ÿ�����(����)
        String CCDYAMT = "";                     //��Ѻ�����ܴ������
        String CCXYAMT = "";                     //CAP=���ô����ܴ������
        String CCCDAMT = "";                     //CAP=���ÿ��ܴ������
        String CCDYNOWBAL = "";                     //��Ѻ���������
        String CCXYNOWBAL = "";                     //���ô��������
        String CCCDNOWBAL = "";                     //���ÿ������
        String CCDYRPMON = "";                     //��Ѻ����»����
        String CCXYRPMON = "";                     // ���ô���»����
        String CCCDRPMON = "";                     // ���ÿ�����»����
        String CCDYYEARRPTIME = "";                  //��Ѻ����12���»����¼ (����)
        String CCXYYEARRPTIME = "";                 // ���ô���12���»����¼ (����)
        String CCCDYEARRPTIME = "";                 //���ÿ�12���»����¼ (����)
        String CCDYNOOVERDUE = "";                     // ��Ѻ������ enum=YesNo
        String CCXYNOOVERDUE = "";                     //���������� enum=YesNo
        String CCCDNOOVERDUE = "";                     // ���ÿ������� enum=YesNo
        String CCDY1TIMEOVERDUE = "";                     //��Ѻ����1-30��(1��)
        String CCXY1TIMEOVERDUE = "";                     // ��������1-30��(1��)
        String CCCD1TIMEOVERDUE = "";                     //���ÿ�����1-30��(1��)
        String CCDY2TIMEOVERDUE = "";                     //��Ѻ����31-60��(2��)
        String CCXY2TIMEOVERDUE = "";                     //��������31-60��(2��)
        String CCCD2TIMEOVERDUE = "";                     //���ÿ�����31-60��(2��)
        String CCDYM3TIMEOVERDUE = "";                     //��Ѻ����60������(3��)
        String CCXY3TIMEOVERDUE = "";                     //��������60������(3��)
        String CCCD3TIMEOVERDUE = "";                     //���ÿ�����60������(3��)
        String ACAGE = "";                     //����Ҫ�� enum=YesNo
        String ACWAGE = "";                     //����Ҫ�� enum=YesNo
        String ACZX1 = "";                     //����ϵͳ�����һ����δ�����¼ enum=YesNo
        String ACZX2 = "";                     //����ϵͳ�ڹ�ȥ12���������������������2�� enum=YesNo
        String ACZX3 = "";                     //����ϵͳ�ڹ�ȥ3���������������60������ enum=YesNo
        String ACFACILITY = "";                     //���ڻ����������Ҫ��(����30%) enum=YesNo
        String ACRATE = "";                    //ÿ�»����������Ҫ��(����60%) enum=YesNo
        String CCRPTOTALAMT = "";              //�ܻ����
        String CCDIVIDAMT = "";                //�ñʷ��ڻ����

        String IDTYPE = "";                    //֤������
        String ID = "";                        //֤������
        String NAME = "";                      //�ͻ����� desc=��ҵ(����)����
        String APPSTATUS = "";                 //����״̬
        String CLIENTNO = "";                  //�ͻ���
        String BIRTHDAY = "";                  //��������
        String GENDER = "";                    //�Ա� enum=Gender
        String NATIONALITY = "";               //����
        String MARRIAGESTATUS = "";            //����״�� enum=MarriageStatus
        String HUKOUADDRESS = "";              //�������ڵ�
        String CURRENTADDRESS = "";            //��סַ
        String COMPANY = "";                   //������λ
        String TITLE = "";                     //ְ�� enum=Title
        String QUALIFICATION = "";             //ְ�� enum=Qualification
        String EDULEVEL = "";                  //ѧ�� enum=EduLevel
        String PHONE1 = "";                    //�ƶ��绰
        String PHONE2 = "";                    //��ͥ�绰
        String PHONE3 = "";                    //�칫�绰
        String CLIENTTYPE = "";                //�ͻ����� enum=ClientType1
        String DEGREETYPE = "";                //���ѧλ enum=DegreeType
        String COMADDR = "";                   //��λ��ַ
        String SERVFROM = "";                  //�ֵ�λ��������
        String SERVFROMMONTH = "";             //�ֵ�λ��������
        String RESIDENCEADR = "";              //�������ڵ�(�����) enum=ResidenceADR
        String HOUSINGSTS = "";                //��ס״�� enum=HousingSts
        String HEALTHSTATUS = "";              //����״�� enum=HealthStatus
        String MONTHLYPAY = "";                //����������
        String SLRYEVETYPE = "";               //����֤������
        String BURDENSTATUS = "";              //����״�� enum=BurdenStatus
        String SOCIALSECURITYID = "";          //�籣���
        String ANNUALINCOME = "";              //��������
        String ETPSCOPTYPE = "";                //��ҵ����
        String SOCIALSECURITY = "";            //��ᱣ�� enum=SocialSecurity
        String LIVEFROM = "";                  //���ؾ�ס����
        String LIVEFROMMONTH = "";             //���ؾ�ס����
        String PC = "";                        //סլ�ʱ�
        String COMPC = "";                     //��λ�ʱ�
        String RESDPC = "";                    //���͵�ַ�ʱ�
        String RESDADDR = "";                  //���͵�ַ
        String EMAIL = "";                     //�����ʼ�

        String PNAME = "";                     //��ż����
        String PIDTYPE = "";                   //��ż֤������
        String PID = "";                       //��ż֤������
        String PCOMPANY = "";                  //��ż������λ
        String PTITLE = "";                    //��żְ�� enum=Title
        String PPHONE1 = "";                   //��ż�ƶ��绰
        String PPHONE3 = "";                   //��ż�칫�绰
        String PCLIENTTYPE = "";               //��ż�ͻ�����(��λ����) enum=ClientType1
        String PSERVFROM = "";                 //��ż�ֵ�λ����ʱ��
        String PMONTHLYPAY = "";               //��ż����������
        String PLIVEFROM = "";                 //��ż���ؾ�סʱ��
        String PSERVFROMMONTH = "";            //��ż�ֵ�λ��������
        String PLIVEFROMMONTH = "";            //��ż���ؾ�ס����
        String CHANNEL = "";                   //���۵�λ(��������)
        String ADDR = "";                      //���͵�ַ
        String TOTALNUM = "";                  //��������
        String TOTALAMT = "";                  //�ܽ��
        String SALER = "";                     //������Ա
//        String
        String RECEIVEAMT = "";                //�Ѹ����
        String APPAMT = "";                    //���ڽ��
        String DIVID = "";                     //��������
        String APPSALARYRATE = "";             //���ڸ������������
        String MONTHPAYSLRRATE = "";           //��ծ�������������������
        String ACTOPENINGBANK = "";            //������ enum=Bank
        String UNIONCHANNEL = "";              //�������� 01 ͨ������ 00 ��
        String BANKACTNO = "";                 //�����ʺ�
        String PROVINCE = "";                  //����ʡ��-�й�����ʱ��
        String CITY = "";                      //�������ڳ��� ��ҵ����
        String XY = "";                        //���� enum=YesNo
        String XYR = "";                       //����������
        String DY = "";                        //��Ѻ enum=YesNo
        String DYW = "";                       //��Ѻ������
        String ZY = "";                        //��Ѻ enum=YesNo
        String ZYW = "";                       //��Ѻ������
        String BZ = "";                        //��֤ enum=YesNo
        String BZR = "";                       //��֤������
        String CREDITTYPE = "";                //�������� enum=CreditType
        String MONPAYAMT = "";                 //�¾������
        String LINKMAN = "";                   //��ϵ������
        String LINKMANGENDER = "";             //��ϵ���Ա�
        String LINKMANPHONE1 = "";              //��ϵ���ƶ��绰
        String LINKMANPHONE2 = "";              //��ϵ�˹̶��绰
        String APPRELATION = "";               //�������˹�ϵ enum=AppRelation
        String LINKMANADD = "";                //��ϵ��סַ
        String LINKMANCOMPANY = "";            //��ϵ�˹�����λ
        String ACTOPENINGBANK_UD = "";         //���������ƣ���㡢��������¼�����ƣ�
        String BANKACTNAME = "";               //����
        String APPDATE = BusinessDate.getToday();                   //��������


        boolean ifErrClient = false;
        CachedRowSet crs = null;
        if (!sql1.equals("")) crs = manager.getRs(sql1);
        if (crs != null && crs.size() > 0) {
            crs.next();
            RECVERSION = crs.getString("RECVERSION");
            //if (NAME.compareTo(crs.getString("NAME")) != 0) ifErrClient = true;  //�ͻ����� desc=��ҵ(����)����)
            NAME = crs.getString("NAME");                           //�ͻ����� desc=��ҵ(����)����)
            CLIENTNO = crs.getString("CLIENTNO");                //�ͻ���
            BIRTHDAY = crs.getString("BIRTHDAY");                //��������
            GENDER = crs.getString("GENDER");                    //�Ա� enum=Gender
            NATIONALITY = crs.getString("NATIONALITY");         //����
            MARRIAGESTATUS = crs.getString("MARRIAGESTATUS");   //����״�� enum=MarriageStatus
            HUKOUADDRESS = crs.getString("HUKOUADDRESS");       //�������ڵ�
            CURRENTADDRESS = crs.getString("CURRENTADDRESS");   //��סַ
            COMPANY = crs.getString("COMPANY");                  //������λ
            TITLE = crs.getString("TITLE");                      //ְ�� enum=Title
            QUALIFICATION = crs.getString("QUALIFICATION");     //ְ�� enum=Qualification
            EDULEVEL = crs.getString("EDULEVEL");                //ѧ�� enum=EduLevel
            PHONE1 = crs.getString("PHONE1");                   //�ƶ��绰
            PHONE2 = crs.getString("PHONE2");                   //��ͥ�绰
            PHONE3 = crs.getString("PHONE3");                   //�칫�绰
            CLIENTTYPE = crs.getString("CLIENTTYPE");           //�ͻ����� enum=ClientType1
            DEGREETYPE = crs.getString("DEGREETYPE");          //���ѧλ enum=DegreeType
            COMADDR = crs.getString("COMADDR");                 //��λ��ַ
            SERVFROM = crs.getString("SERVFROM");               //�ֵ�λ����ʱ��
            RESIDENCEADR = crs.getString("RESIDENCEADR");       //�������ڵ�(�����) enum=ResidenceADR
            HOUSINGSTS = crs.getString("HOUSINGSTS");           //��ס״�� enum=HousingSts
            HEALTHSTATUS = crs.getString("HEALTHSTATUS");       //����״�� enum=HealthStatus
            MONTHLYPAY = crs.getString("MONTHLYPAY");           //����������
            SLRYEVETYPE = crs.getString("SLRYEVETYPE");         //����֤������
            BURDENSTATUS = crs.getString("BURDENSTATUS");       //����״�� enum=BurdenStatus
            SOCIALSECURITYID = crs.getString("SOCIALSECURITYID");  //Ա��������
            ANNUALINCOME = crs.getString("ANNUALINCOME");       //��������
            ETPSCOPTYPE = crs.getString("ETPSCOPTYPE");           //��ҵ����
            SOCIALSECURITY = crs.getString("SOCIALSECURITY");   //��ᱣ�� enum=SocialSecurity
            LIVEFROM = crs.getString("LIVEFROM");               //���ؾ�סʱ��
            PC = crs.getString("PC");                            //סլ�ʱ�
            COMPC = crs.getString("COMPC");                      //��λ�ʱ�
            RESDPC = crs.getString("RESDPC");                    //���͵�ַ�ʱ�
            RESDADDR = crs.getString("RESDADDR");                //���͵�ַ
            EMAIL = crs.getString("EMAIL");                      //�����ʼ�
            SERVFROMMONTH = crs.getString("SERVFROMMONTH");     //�ֵ�λ��������
            LIVEFROMMONTH = crs.getString("LIVEFROMMONTH");     //���ؾ�ס����
            PNAME = crs.getString("PNAME");                      //��ż����
            PIDTYPE = crs.getString("PIDTYPE");                  //��ż֤������
            PID = crs.getString("PID");                          //��ż֤������
            PCOMPANY = crs.getString("PCOMPANY");                //��ż������λ
            PTITLE = crs.getString("PTITLE");                    //��żְ�� enum=Title
            PPHONE1 = crs.getString("PPHONE1");                  //��ż�ƶ��绰
            PPHONE3 = crs.getString("PPHONE3");                  //��ż�칫�绰
            PCLIENTTYPE = crs.getString("PCLIENTTYPE");         //��ż�ͻ�����(��λ����) enum=ClientType1
            PSERVFROM = crs.getString("PSERVFROM");             //��ż�ֵ�λ����ʱ��
            PMONTHLYPAY = crs.getString("PMONTHLYPAY");         //��ż����������
            PLIVEFROM = crs.getString("PLIVEFROM");             //��ż���ؾ�סʱ��
            PSERVFROMMONTH = crs.getString("PSERVFROMMONTH");  //��ż�ֵ�λ��������
            PLIVEFROMMONTH = crs.getString("PLIVEFROMMONTH");  //��ż���ؾ�ס����

            CHANNEL = crs.getString("CHANNEL");                  //���۵�λ(��������)
            ADDR = crs.getString("ADDR");                         //���͵�ַ
            TOTALNUM = crs.getString("TOTALNUM");                //��������
            TOTALAMT = crs.getString("TOTALAMT");                 //�ܽ��
            RECEIVEAMT = crs.getString("RECEIVEAMT");            //�Ѹ����
            APPAMT = crs.getString("APPAMT");                     //���ڽ��
            DIVID = crs.getString("DIVID");                       //��������
            APPSALARYRATE = crs.getString("APPSALARYRATE");      //���ڸ������������
            MONTHPAYSLRRATE = crs.getString("MONTHPAYSLRRATE");
            SALER = crs.getString("SALER");                       //������Ա
            COMMISSIONRATE = crs.getString("COMMISSIONRATE");
            SHCOMMISSIONRATE = crs.getString("SHCOMMISSIONRATE");
            COMPAYDATE = crs.getString("COMPAYDATE");
            SHARETYPE = crs.getString("SHARETYPE");
            COMMISSIONTYPE = crs.getString("COMMISSIONTYPE");
            PREPAY_CUTPAY_TYPE = crs.getString("PREPAY_CUTPAY_TYPE");        //�׸����Ƿ����
            ACTOPENINGBANK = crs.getString("ACTOPENINGBANK");   //������ enum=Bank
            UNIONCHANNEL = crs.getString("UNIONCHANNEL");       //��������
            PROVINCE = crs.getString("PROVINCE");               //����ʡ��
            CITY = crs.getString("CITY");                       //���г���
            BANKACTNO = crs.getString("BANKACTNO");              //�����ʺ�
            XY = crs.getString("XY");                             //���� enum=YesNo
            XYR = crs.getString("XYR");                           //����������
            DY = crs.getString("DY");                             //��Ѻ enum=YesNo
            DYW = crs.getString("DYW");                           //��Ѻ������
            ZY = crs.getString("ZY");                             //��Ѻ enum=YesNo
            ZYW = crs.getString("ZYW");                           //��Ѻ������
            BZ = crs.getString("BZ");                             //��֤ enum=YesNo
            BZR = crs.getString("BZR");                           //��֤������
            CREDITTYPE = crs.getString("CREDITTYPE");            //�������� enum=CreditType
            MONPAYAMT = crs.getString("MONPAYAMT");              //�¾������
            LINKMAN = crs.getString("LINKMAN");                   //��ϵ������
            LINKMANGENDER = crs.getString("LINKMANGENDER");      //��ϵ���Ա�
            LINKMANPHONE1 = crs.getString("LINKMANPHONE1");      //��ϵ���ƶ��绰
            LINKMANPHONE2 = crs.getString("LINKMANPHONE2");      //��ϵ�˹̶��绰
            APPRELATION = crs.getString("APPRELATION");          //�������˹�ϵ enum=AppRelation
            LINKMANADD = crs.getString("LINKMANADD");            //��ϵ��סַ
            LINKMANCOMPANY = crs.getString("LINKMANCOMPANY");    //��ϵ�˹�����λ
            ACTOPENINGBANK_UD = crs.getString("ACTOPENINGBANK_UD");    //���������ƣ���㡢��������¼�����ƣ�
            BANKACTNAME = crs.getString("BANKACTNAME");          //����
            //������Ϣ
            CCVALIDPERIOD = crs.getString("CCVALIDPERIOD");               //������Ϣ��Чʱ�� ENUM=CCValidPeriod
            CCDYNUM = crs.getString("CCDYNUM");                     //��Ѻ��������(����)
            CCXYNUM = crs.getString("CCXYNUM");                     //���ô�������(����)
            CCCDNUM = crs.getString("CCCDNUM");                     //���ÿ�����(����)
            CCDYAMT = crs.getString("CCDYAMT");                     //��Ѻ�����ܴ������
            CCXYAMT = crs.getString("CCXYAMT");                     //CAP=���ô����ܴ������
            CCCDAMT = crs.getString("CCCDAMT");                     //CAP=���ÿ��ܴ������
            CCDYNOWBAL = crs.getString("CCDYNOWBAL");                     //��Ѻ���������
            CCXYNOWBAL = crs.getString("CCXYNOWBAL");                     //���ô��������
            CCCDNOWBAL = crs.getString("CCCDNOWBAL");                     //���ÿ������
            CCDYRPMON = crs.getString("CCDYRPMON");                     //��Ѻ����»����
            CCXYRPMON = crs.getString("CCXYRPMON");                     // ���ô���»����
            CCCDRPMON = crs.getString("CCCDRPMON");                     // ���ÿ�����»����
            CCDYYEARRPTIME = crs.getString("CCDYYEARRPTIME");                  //��Ѻ����12���»����¼ (����)
            CCXYYEARRPTIME = crs.getString("CCXYYEARRPTIME");                 // ���ô���12���»����¼ (����)
            CCCDYEARRPTIME = crs.getString("CCCDYEARRPTIME");                 //���ÿ�12���»����¼ (����)
            CCDYNOOVERDUE = crs.getString("CCDYNOOVERDUE");                     // ��Ѻ������ enum=YesNo
            CCXYNOOVERDUE = crs.getString("CCXYNOOVERDUE");                     //���������� enum=YesNo
            CCCDNOOVERDUE = crs.getString("CCCDNOOVERDUE");                     // ���ÿ������� enum=YesNo
            CCDY1TIMEOVERDUE = crs.getString("CCDY1TIMEOVERDUE");                     //��Ѻ����1-30��(1��)
            CCXY1TIMEOVERDUE = crs.getString("CCXY1TIMEOVERDUE");                     // ��������1-30��(1��)
            CCCD1TIMEOVERDUE = crs.getString("CCCD1TIMEOVERDUE");                     //���ÿ�����1-30��(1��)
            CCDY2TIMEOVERDUE = crs.getString("CCDY2TIMEOVERDUE");                     //��Ѻ����31-60��(2��)
            CCXY2TIMEOVERDUE = crs.getString("CCXY2TIMEOVERDUE");                     //��������31-60��(2��)
            CCCD2TIMEOVERDUE = crs.getString("CCCD2TIMEOVERDUE");                     //���ÿ�����31-60��(2��)
            CCDYM3TIMEOVERDUE = crs.getString("CCDYM3TIMEOVERDUE");                     //��Ѻ����60������(3��)
            CCXY3TIMEOVERDUE = crs.getString("CCXY3TIMEOVERDUE");                     //��������60������(3��)
            CCCD3TIMEOVERDUE = crs.getString("CCCD3TIMEOVERDUE");                     //���ÿ�����60������(3��)
            ACAGE = crs.getString("ACAGE");                     //����Ҫ�� enum=YesNo
            ACWAGE = crs.getString("ACWAGE");                     //����Ҫ�� enum=YesNo
            ACZX1 = crs.getString("ACZX1");                     //����ϵͳ�����һ����δ�����¼ enum=YesNo
            ACZX2 = crs.getString("ACZX2");                     //����ϵͳ�ڹ�ȥ12���������������������2�� enum=YesNo
            ACZX3 = crs.getString("ACZX3");                     //����ϵͳ�ڹ�ȥ3���������������60������ enum=YesNo
            ACFACILITY = crs.getString("ACFACILITY");                     //���ڻ����������Ҫ��(����30%) enum=YesNo
            ACRATE = crs.getString("ACRATE");                    //ÿ�»����������Ҫ��(����60%) enum=YesNo
            CCRPTOTALAMT = crs.getString("CCRPTOTALAMT");              //�ܻ����
            CCDIVIDAMT = crs.getString("CCDIVIDAMT");

            ID = crs.getString("ID");                              //֤������
            IDTYPE = crs.getString("IDTYPE");                     //֤������
            APPDATE = crs.getString("APPDATE");                   //��������
            APPSTATUS = crs.getString("APPSTATUS");               //����״̬


            SID = crs.getString("SID");                            //�����̳Ǳ��
            ORDERNO = crs.getString("ORDERNO");                    //�����̳ǵ���
            REQUESTTIME = crs.getString("REQUESTTIME");           //�����̳Ƕ�������ʱ��
        }
        CachedRowSet crs2 = null;
        if (!sql2.equals("")) crs2 = manager.getRs(sql2);


        NAME = (NAME == null) ? "" : NAME.trim();
        PNAME = (PNAME == null) ? "" : PNAME.trim();
        APPSTATUS = (APPSTATUS == null) ? "" : APPSTATUS;
        BIRTHDAY = (BIRTHDAY == null) ? "" : BIRTHDAY;
        ETPSCOPTYPE = (ETPSCOPTYPE == null) ? "" : ETPSCOPTYPE;

        SID = (SID == null) ? "" : SID.trim();                          //�����̳Ǳ�š���������ֱ��|001�������̳�
        ORDERNO = (ORDERNO == null) ? "" : ORDERNO;
        REQUESTTIME = (REQUESTTIME == null) ? "" : REQUESTTIME.trim();
        CHANNEL = (CHANNEL == null) ? "" : CHANNEL.trim();
        TOTALNUM = (TOTALNUM == null) ? "" : TOTALNUM.trim();
        TOTALAMT = (TOTALAMT == null) ? "" : TOTALAMT.trim();
        PREPAY_CUTPAY_TYPE = (PREPAY_CUTPAY_TYPE == null) ? "0" : PREPAY_CUTPAY_TYPE.trim();
        //������Ϣ
        CCVALIDPERIOD = (CCVALIDPERIOD == null) ? "" : CCVALIDPERIOD.trim();
        CCDYNOOVERDUE = (CCDYNOOVERDUE == null) ? "" : CCDYNOOVERDUE.trim();
        CCXYNOOVERDUE = (CCXYNOOVERDUE == null) ? "" : CCXYNOOVERDUE.trim();
        CCCDNOOVERDUE = (CCCDNOOVERDUE == null) ? "" : CCCDNOOVERDUE.trim();
        ACAGE = (ACAGE == null) ? "" : ACAGE.trim();
        ACWAGE = (ACWAGE == null) ? "" : ACWAGE.trim();
        ACZX1 = (ACZX1 == null) ? "" : ACZX1.trim();
        ACZX2 = (ACZX2 == null) ? "" : ACZX2.trim();
        ACZX3 = (ACZX3 == null) ? "" : ACZX3.trim();
        ACFACILITY = (ACFACILITY == null) ? "" : ACFACILITY.trim();
        ACRATE = (ACRATE == null) ? "" : ACRATE.trim();

        COMPAYDATE = (COMPAYDATE == null || StringUtils.isEmpty(COMPAYDATE)) ? (Level.getEnumItemName("COMPAYDATE", "1")) : COMPAYDATE;
        PROVINCE = (PROVINCE == null) ? "" : PROVINCE;
        String ACTOPENINGBANKUP = (ACTOPENINGBANK + "-" + UNIONCHANNEL);
        if (ACTOPENINGBANK.equals("313")) {
            if (CITY.equals(AppCommonEnum.CITY_GUANGZHOU)) {
                ACTOPENINGBANKUP = "2@" + ACTOPENINGBANKUP;
            } else if (CITY.equals(AppCommonEnum.CITY_SHENZHEN)) {
                ACTOPENINGBANKUP = "1@" + ACTOPENINGBANKUP;
            }
        } else if (ACTOPENINGBANK.equals("403")) {
            if (PROVINCE.equals(AppCommonEnum.PROVINCE_GUANGDONG)) {
                ACTOPENINGBANKUP = "1@" + ACTOPENINGBANKUP;
            } else if (PROVINCE.equals(AppCommonEnum.PROVINCE_HENAN)) {
                ACTOPENINGBANKUP = "2@" + ACTOPENINGBANKUP;
            }
        }

        String readonly_input = "readonly";
        String submit = "class='btn_2k3'";
        String title = "�������ѷ��ڸ���������";

        if (ifErrClient) {
            String mess = "�ͻ���Ϣ���󣬴�֤���ѱ������ͻ�ʹ�ã�";
            mess = "<li class='error_message_li'>" + mess.trim() + "</li>";
            session.setAttribute("msg", mess);
            response.sendRedirect("../showinfo.jsp");
        }
%>
<html>
<head id="head1">
    <title>�����Ŵ�</title>
    <link href="../../../css/platform.css" rel="stylesheet" type="text/css">
    <meta http-equiv="Content-Type" content="text/html; charset=GBK">
    <style type="text/css">
        <!--
        body {
            margin-top: 0px;
            margin-left: 0px;
            margin-right: 0px;
        }

        -->
    </style>
</head>
<SCRIPT type="text/javascript">
    var hkey_root,hkey_path,hkey_key;
    hkey_root = "HKEY_CURRENT_USER";

    var hk_pageHead,hk_pageFoot;
    <!--��ַ��д�����ϸ����˫б��-->
    hkey_path = "\\Software\\Microsoft\\Internet Explorer\\PageSetup";
    //������ҳ��ӡ��ҳüҳ��Ϊ��
    function pagesetup_null() {
        try {
            var RegWsh = new ActiveXObject("WScript.Shell");
            hkey_key = "\\header";
            hk_pageHead = RegWsh.RegRead(hkey_root + hkey_path + hkey_key);
            RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
            hkey_key = "\\footer";
            hk_pageFoot = RegWsh.RegRead(hkey_root + hkey_path + hkey_key);
            RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
        } catch(e) {
            //alert('��Ҫ��������Activex���ܽ��д�ӡ���á�');
        }
    }
    //������ҳ��ӡ��ҳüҳ��ΪĬ��ֵ
    function pagesetup_default() {
        try {
            var RegWsh = new ActiveXObject("WScript.Shell");
            hkey_key = "\\header";
            //RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "&w&bҳ��,&p/&P");
            RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, hk_pageHead);
            hkey_key = "\\footer";
            //RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "&u&b&d");
            RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, hk_pageFoot);
        } catch(e) {
        }
    }


</SCRIPT>
<!--media=print �������˵�������ڴ�ӡʱ��Ч-->
<!--ϣ����ӡʱ����ʾ����������class="Noprint"��ʽ-->
<!--ϣ����Ϊ���÷�ҳ��λ������class="PageNext"��ʽ-->
<style media=print>
    .Noprint {
        display: none;
    }

    .PageNext {
        page-break-after: always;
    }

    table {
        border-color: #000 !important;
        color: #000 !important;
    }

    tr {
        border-color: #000 !important;
        color: #000 !important;
    }

    td {
        border-color: #000 !important;
        color: #000 !important;
    }
</style>

<body>
<table width="100%" height="100%" border="0" cellspacing="0" cellpadding="0">
<tr class='page_form_tr'>
<td align="center" valign="middle">
<table height="325" border="0" style="margin-top:10px;" align="center"
       cellspacing="0" cellpadding="0" bordercolor="#816A82" bgcolor="#ffffff" width="678">
<tr align="left">
    <td height="30" bgcolor="#A4AEB5">
        <img src="../../../images/formtile1.gif" style="margin-left:4px;" height="22px" width="22px" align="absmiddle">
        <font size="2" color="#FFFFFF"><b>�������Ų����������ι�˾�������ѷ��ڸ���������</b></font>
    </td>
</tr>
<tr align="center">
<td height="260" valign="middle">
<table width="100%" height="100%" cellspacing="0" cellpadding="0" border="0">
<tr class='page_form_tr'>
<td width="20">&nbsp;</td>
<td align="center" valign="middle" width="638">
<script src='../../../js/xf/main.js' type='text/javascript'></script>
<script src='../../../js/xf/check.js' type='text/javascript'></script>
<script src='../../../js/xf/meizzDate.js' type='text/javascript'></script>
<script src='../../../js/xf/checkID2.js' type='text/javascript'></script>
<script src='../../../js/xf/pagebutton.js' type='text/javascript'></script>
<script src='../../../js/xf/xfutil.js' type='text/javascript'></script>

<script src='../../../js/xf/checkReName.js' type='text/javascript'></script>

<form id='winform' method='post' action='./application_save.jsp'>
<table class='page_form_regTable' id='page_form_table' width="638" cellspacing="1" cellpadding="0" border=0>
<col width="69"/>
<col width="110"/>
<col width="160"/>
<col width="31"/>
<col width="95"/>
<col width="160"/>
<col width="13"/>
<tr class='page_form_tr'>
    <td colspan="7" align="center" class="page_form_List_title">�������Ų����������ι�˾�������ѷ��ڸ���������</td>
</tr>
<tr class='page_form_tr'>
    <td colspan="4">
        <input type="hidden" name="APPACTFLAG" value="1"><%--ִ�ж�����־��1��������2���˻أ�3������--%>
        <input type="hidden" name="CLIENTNO" value="<%=CLIENTNO%>">
        <input type="hidden" name="APPNO" value="<%=APPNO%>" id="APPNO">
        <input type="hidden" name="APPSTATUS" value="1" id="APPSTATUS">
        <input type="hidden" name="RECVERSION" value="<%=RECVERSION%>" id="RECVERSION"/>
        <input type="hidden" name="SID" value="<%=SID%>">
        <input type="hidden" name="ORDERNO" value="<%=ORDERNO%>">
        <input type="hidden" name="REQUESTTIME" value="<%=REQUESTTIME%>">
        ��������:<%
        out.print(APPDATE == null ? "" : APPDATE);
    %>
        &nbsp;����״̬:<%=(APPSTATUS == null || APPSTATUS.equals("")) ? "������" : Level.getEnumItemName("AppStatus", APPSTATUS)%>
    </td>
    <td align="right">���뵥��:&nbsp;<%
        String XFAPPNO = "";
        if (!APPNO.equals("")) {
            XFAPPNO = APPNO;
        }
    %>
    </td>
    <td>
        <input <%=readonly%> class="page_form_text" type="text" name="XFAPPNO" id="XFAPPNO"
                             value="<%=XFAPPNO%>" maxlength="22">
    </td>
    <td>&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_button_tbl_tr" colspan="7" height="5"></td>
</tr>
<tr class='page_form_tr'>
    <td rowspan="12" class="page_left_table_title" nowrap>���������</td>
    <td class="page_form_title_td" nowrap>&nbsp;��&nbsp;&nbsp;����</td>
    <td class="page_form_td" nowrap><input type="text" <%=readonly%> name="NAME"
                                           value="<%=NAME==null?"":NAME%>"
                                           class="page_form_text" maxlength="40"></td>
    <td class="page_form_td" nowrap>&nbsp;</td>
    <td class="page_form_title_td" nowrap>&nbsp;��&nbsp;&nbsp;��</td>
    <td colspan="2" nowrap class="page_form_td"><%=Level.radioHere("GENDER", "Gender", GENDER)%>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;��&nbsp;&nbsp;����</td>
    <td class="page_form_td" nowrap><input type="text" <%=readonly%> name="PHONE1"
                                           value="<%=PHONE1==null?"":PHONE1%>"
                                           class="page_form_text" maxlength="15"></td>
    <td class="page_form_td" nowrap>&nbsp;</td>
    <td class="page_form_title_td" nowrap>&nbsp;�籣��ţ�</td>
    <td class="page_form_td" nowrap><input type="text" <%=readonly%> name="SOCIALSECURITYID"
                                           value="<%=SOCIALSECURITYID==null?"":SOCIALSECURITYID%>"
                                           class="page_form_text" maxlength="28">

    </td>
    <td class="page_form_td" nowrap><span style="color:red">*</span></td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;���֤�����ƣ�</td>
    <td colspan="2" nowrap class="page_form_td">
        <%=Level.levelHere("IDTYPE", "IDType", IDTYPE)%>
    </td>
    <td class="page_form_title_td" nowrap>&nbsp;֤�����룺</td>
    <td class="page_form_td" nowrap><input type="text" <%=readonly%> name="ID" id="ID"
                                           value="<%=ID==null?"":ID%>" class="page_form_text"
                                           maxlength="18"></td>
    <td class="page_form_td" nowrap>&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;�������ڣ�</td>
    <td class="page_form_td" nowrap><input type="text" <%=readonly_input%> name="BIRTHDAY"
                                           value="<%=BIRTHDAY.equals("")?"":BIRTHDAY%>"
    <%--value="<%=BIRTHDAY.equals("")?"":DBUtil.to_Date(BIRTHDAY)%>"--%>
                                           class="page_form_text"></td>
    <td class="page_form_td" nowrap><input type="button" value="��" class="page_form_refbutton"
                                           onClick="setday(this,winform.BIRTHDAY)"></td>
    <td class="page_form_title_td" nowrap>&nbsp;����״����</td>
    <td colspan="2" nowrap class="page_form_td"><%=Level.radioHere("HEALTHSTATUS", "HealthStatus", HEALTHSTATUS)%>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;����״����</td>
    <td colspan="2" nowrap class="page_form_td"><%=Level.radioHere("MARRIAGESTATUS", "MarriageStatus", MARRIAGESTATUS)%>
    </td>
    <td class="page_form_title_td" nowrap>&nbsp;�Ƿ�����Ů��</td>
    <td colspan="2" nowrap class="page_form_td"><%=Level.radioHere("BURDENSTATUS", "BurdenStatus", BURDENSTATUS)%>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;�������ڵأ�</td>
    <td colspan="5" class="page_form_td" nowrap><%=Level.radioHere("RESIDENCEADR", "ResidenceADR", RESIDENCEADR)%>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;��ס�����</td>
    <td colspan="4" class="page_form_td">
        <%=Level.radioHere("HOUSINGSTS", "HousingSts", HOUSINGSTS)%>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;��ͥסַ��</td>
    <td colspan="4" class="page_form_td">
        <input type="text" <%=readonly%> name="CURRENTADDRESS"
               value="<%=CURRENTADDRESS==null?"":CURRENTADDRESS%>"
               class="page_form_text" style="width:478px" maxlength="40"></td>
    <td class="page_form_td" nowrap>&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;סլ�ʱࣺ</td>
    <td colspan="2" class="page_form_td"><input type="text" <%=readonly%> name="PC"
                                                value="<%=PC==null?"":PC%>"
                                                class="page_form_text" maxlength="6"></td>
    <td class="page_form_title_td" nowrap>&nbsp;�������䣺</td>
    <td class="page_form_td">
        <input type="text" <%=readonly%> name="EMAIL"
               value="<%=EMAIL==null?"":EMAIL%>"
               class="page_form_text" maxlength="40"></td>
    <td class="page_form_td" nowrap>&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;��ͥ�绰��</td>
    <td colspan="2" class="page_form_td"><input type="text" <%=readonly%> name="PHONE2"
                                                value="<%=PHONE2==null?"":PHONE2%>"
                                                class="page_form_text" maxlength="15"></td>
    <td class="page_form_title_td" nowrap>&nbsp;���ؾ�סʱ�䣺</td>
    <td colspan="2" class="page_form_td">
        <input type="text" <%=readonly%> name="LIVEFROM"
               value="<%=LIVEFROM==null?"":LIVEFROM%>"
               class="page_form_text" style="width:70px" maxlength="3">��
        <input type="text" <%=readonly%> name="LIVEFROMMONTH"
               value="<%=LIVEFROMMONTH==null?"":LIVEFROMMONTH%>"
               class="page_form_text" style="width:70px" maxlength="2">��
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;�ܽ����̶ȣ�</td>
    <td colspan="5" class="page_form_td" nowrap><%=Level.radioHere("EDULEVEL", "EduLevel", EDULEVEL)%>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;ְ&nbsp;&nbsp;�ƣ�</td>
    <td colspan="5" class="page_form_td"
        nowrap><%=Level.radioHere("QUALIFICATION", "Qualification", QUALIFICATION)%>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_button_tbl_tr" colspan="7" height="5"></td>
</tr>
<tr class='page_form_tr'>
    <td rowspan="9" class="page_left_table_title">�������ϣ�</td>
    <td class="page_form_title_td" nowrap>&nbsp;������λ��</td>
    <td colspan="4" class="page_form_td">
        <input type="text" <%=readonly%> name="COMPANY"
               value="<%=COMPANY==null?"":COMPANY%>"
               class="page_form_text" style="width:478px" maxlength="40">
    </td>
    <td class="page_form_td" nowrap>&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;��λ�绰��</td>
    <td colspan="2" class="page_form_td">
        <input type="text" <%=readonly%> name="PHONE3"
               value="<%=PHONE3==null?"":PHONE3%>"
               class="page_form_text" maxlength="15">
    </td>
    <td class="page_form_title_td" nowrap>&nbsp;��λ�ʱࣺ</td>
    <td class="page_form_td">
        <input type="text" <%=readonly%> name="COMPC"
               value="<%=COMPC==null?"":COMPC%>"
               class="page_form_text" maxlength="6">
    </td>
    <td class="page_form_td" nowrap>&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;��λ��ַ��</td>
    <td colspan="4" class="page_form_td">
        <input type="text" <%=readonly%> name="COMADDR"
               value="<%=COMADDR==null?"":COMADDR%>"
               class="page_form_text" style="width:478px" maxlength="40">
    </td>
    <td class="page_form_td" nowrap>&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;��λ���ʣ�</td>
    <td colspan="5" class="page_form_td" nowrap><%=Level.radioHere("CLIENTTYPE", "ClientType1", CLIENTTYPE)%>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;��ҵ���ʣ�</td>
    <td colspan="2" class="page_form_td" nowrap><%=Level.levelHere("ETPSCOPTYPE", "EtpScopType1", ETPSCOPTYPE)%>
    </td>
    <td class="page_form_title_td" nowrap>&nbsp;ְ&nbsp;&nbsp;��</td>
    <td colspan="2" class="page_form_td" nowrap><%=Level.levelHere("TITLE", "Title", TITLE)%>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;�ֵ�λ����ʱ�䣺</td>
    <td colspan="5" class="page_form_td">
        <input type="text" <%=readonly%> name="SERVFROM"
               value="<%=SERVFROM==null?"":SERVFROM%>"
               class="page_form_text" style="width:70px" maxlength="3">��
        <input type="text" <%=readonly%> name="SERVFROMMONTH"
               value="<%=SERVFROMMONTH==null?"":SERVFROMMONTH%>"
               class="page_form_text" style="width:70px" maxlength="2">��

    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;���������룺</td>
    <td class="page_form_td">
        <input type="text" <%=readonly%> name="MONTHLYPAY"
               value="<%=MONTHLYPAY==null?"":MONTHLYPAY%>" onblur="blurMonthpay(this)"
               class="page_form_text" maxlength="12"></td>
    <td class="page_form_td">Ԫ</td>
    <td class="page_form_title_td" nowrap>&nbsp;�������ܶ</td>
    <td class="page_form_td">
        <input type="text" <%=readonly%> name="ANNUALINCOME"
               value="<%=ANNUALINCOME==null?"":ANNUALINCOME%>"
               class="page_form_text" maxlength="12"></td>
    <td colspan="2" class="page_form_td">Ԫ</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;����֤�����ͣ�</td>
    <td colspan="5" class="page_form_td">
        <input type="text" <%=readonly%> name="SLRYEVETYPE"
               value="<%=SLRYEVETYPE==null?"":SLRYEVETYPE%>"
               class="page_form_text" maxlength="40">
        <span style="color:red">*</span>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;������ḣ��<br>&nbsp;�����ƶ������</td>
    <td colspan="5" class="page_form_td"
        nowrap><%=Level.checkHere("SOCIALSECURITY", "SocialSecurity", SOCIALSECURITY)%>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_button_tbl_tr" colspan="7" height="5"></td>
</tr>

<tr class='page_form_tr' onClick="showList(1);" id="HT2">
    <td class="page_slide_table_title" colspan="7" height="18" id="HT1" title="�������" onMouseOver="mOvr(this);"
        onMouseOut="mOut(this);">
        �����˻���ѹ��������ż��ͬ�������д��ż������Ϣ -- ��<font color="red">(���չ��)</font>
    </td>
</tr>
<tr>
    <td colspan="7" bgcolor="#3366FF" style="margin:0; padding:0" width="100%">
        <table class="page_form_table_slide" width="100%" id="HA1" style="display:none;border:1px dashed #FDF8DF"
               cellspacing="0" cellpadding="0">
            <COL width=66>
            <COL width=111>
            <COL width=160>
            <COL width=31>
            <COL width=96>
            <COL width=160>
            <COL width=13>
            <tr class='page_form_tr'>
                <td rowspan="8" class="page_left_table_title" nowrap>��ż�������(������żΪ��ͬ���������������)</td>
                <td class="page_form_title_td" nowrap>&nbsp;��ż������</td>
                <td colspan="5" class="page_form_td" nowrap><input type="text" <%=readonly%> name="PNAME"
                                                                   value="<%=PNAME==null?"":PNAME%>"
                                                                   class="page_form_text" maxlength="40"></td>
            </tr>
            <tr class='page_form_tr'>
                <td class="page_form_title_td" nowrap>��ż���֤�����ƣ�</td>
                <td class="page_form_td" colspan="2" nowrap><%=Level.levelHere("PIDTYPE", "IDType", PIDTYPE)%>
                </td>
                <td class="page_form_title_td" nowrap>&nbsp;��ż֤�����룺</td>
                <td class="page_form_td">
                    <input type="text" <%=readonly%> name="PID" id="PID" value="<%=PID==null?"":PID%>"
                           onblur="if(this.value.Trim()!=''&&document.getElementById('PNAME').value.Trim()==''){alert('��ż��������Ϊ�գ�');document.getElementById('PNAME').focus();}else checkIDCard(document.getElementById('PIDTYPE'),this);"
                           class="page_form_text" maxlength="18"></td>
                <td class="page_form_td">&nbsp;</td>
            </tr>
            <tr class='page_form_tr'>
                <td class="page_form_title_td" nowrap>&nbsp;������λ��</td>
                <td colspan="4" class="page_form_td">
                    <input type="text" <%=readonly%> name="PCOMPANY"
                           value="<%=PCOMPANY==null?"":PCOMPANY%>"
                           class="page_form_text" style="width:510px" maxlength="40"></td>
                <td class="page_form_td">&nbsp;</td>
            </tr>
            <tr class='page_form_tr'>
                <td class="page_form_title_td" nowrap>&nbsp;��λ�绰��</td>
                <td colspan="5" class="page_form_td">
                    <input type="text" <%=readonly%> name="PPHONE3"
                           value="<%=PPHONE3==null?"":PPHONE3%>"
                           class="page_form_text" maxlength="15">
                </td>
            </tr>
            <tr class='page_form_tr'>
                <td class="page_form_title_td" nowrap>&nbsp;��λ���ʣ�</td>
                <td colspan="5" class="page_form_td"
                    nowrap><%=Level.radioHere("PCLIENTTYPE", "ClientType1", PCLIENTTYPE)%>
                </td>
            </tr>
            <tr class='page_form_tr'>
                <td class="page_form_title_td" nowrap>&nbsp;ְ&nbsp;&nbsp;��</td>
                <td colspan="5" class="page_form_td" nowrap><%=Level.levelHere("PTITLE", "Title", PTITLE)%>
                </td>
            </tr>
            <tr class='page_form_tr'>
                <td class="page_form_title_td" nowrap>&nbsp;�ֵ�λ����ʱ�䣺</td>
                <td colspan="2" class="page_form_td">
                    <input type="text" <%=readonly%> name="PSERVFROM"
                           value="<%=PSERVFROM==null?"":PSERVFROM%>"
                           class="page_form_text" style="width:70px" maxlength="3">��
                    <input type="text" <%=readonly%> name="PSERVFROMMONTH"
                           value="<%=PSERVFROMMONTH==null?"":PSERVFROMMONTH%>"
                           class="page_form_text" style="width:70px" maxlength="2">��

                </td>
                <td class="page_form_title_td" nowrap>&nbsp;���ؾ�סʱ�䣺</td>
                <td colspan="2" class="page_form_td">
                    <input type="text" <%=readonly%> name="PLIVEFROM"
                           value="<%=PLIVEFROM==null?"":PLIVEFROM%>"
                           class="page_form_text" style="width:70px" maxlength="3">��
                    <input type="text" <%=readonly%> name="PLIVEFROMMONTH"
                           value="<%=PLIVEFROMMONTH==null?"":PLIVEFROMMONTH%>"
                           class="page_form_text" style="width:70px" maxlength="2">��

                </td>
            </tr>
            <tr class='page_form_tr'>
                <td class="page_form_title_td" nowrap>&nbsp;���˵绰��</td>
                <td class="page_form_td">
                    <input type="text" <%=readonly%> name="PPHONE1"
                           value="<%=PPHONE1==null?"":PPHONE1%>"
                           class="page_form_text" maxlength="15"></td>
                <td class="page_form_td">&nbsp;</td>
                <td class="page_form_title_td" nowrap>&nbsp;���������룺</td>
                <td class="page_form_td">
                    <input type="text" <%=readonly%> name="PMONTHLYPAY"
                           value="<%=PMONTHLYPAY==null?"":PMONTHLYPAY%>"
                           class="page_form_text" maxlength="12">
                </td>
                <td class="page_form_td">Ԫ</td>
            </tr>
        </table>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_button_tbl_tr" colspan="7" height="5"></td>
</tr>
<tr class='page_form_tr'>
    <td colspan="7" width="100%">
        <table id="tabCommContent" class='page_form_regTable' cellpadding="0" cellspacing="0" border="1">
            <tr>
                <td height="30px" class="page_left_table_title" style="text-align:left;" colspan="6">
                    �⹺��Ʒ���<span style="color:red">&nbsp;(������������¼��)</span>
                </td>
            </tr>
            <tr height="25px">
                <td style="display:none">1</td>
                <td width="200" class="page_form_title_th">��Ʒ���Ƽ��ͺ�</td>
                <td width="150" class="page_form_title_th">��Ʒ����</td>
                <td width="80" class="page_form_title_th">����(̨/��)</td>
                <td width="110" class="page_form_title_th">�۸�(Ԫ)</td>
                <td width="110" class="page_form_title_th">�׸����(Ԫ)</td>
            </tr>
            <%
                String noCOMMNO = "";
                String noCOMMNMTYPE = "";
                String noNUM = "";
                String noAMT = "";
                String noRECEIVEAMT = "";
                String noAPPTYPE = "";
                int rowCnt = 1;
                if (crs2 != null && crs2.size() > 0) {
                    while (crs2.next()) {
                        String strRowCnt = String.valueOf(rowCnt);
                        noCOMMNO = "COMMNO" + strRowCnt;
                        noCOMMNMTYPE = "COMMNMTYPE" + strRowCnt;
                        noNUM = "NUM" + strRowCnt;
                        noAMT = "AMT" + strRowCnt;
                        noRECEIVEAMT = "RECEIVEAMT" + strRowCnt;
                        noAPPTYPE = "APPTYPE" + strRowCnt;
                        String vaCOMMNO = crs2.getString("COMMNO");
                        String vaCOMMNMTYPE = crs2.getString("COMMNMTYPE");
                        String vaNUM = crs2.getString("NUM");
                        String vaAMT = crs2.getString("AMT");
                        String vaRECEIVEAMT = crs2.getString("RECEIVEAMT");
                        String vaAPPTYPE = crs2.getString("APPTYPE");
            %>
            <tr class='page_form_tr'>
                <td style="display:none;">
                    <input type="text" name="noCOMMNO" id="<%=noCOMMNO%>" value="<%=vaCOMMNO%>"/>
                </td>
                <td class="page_form_td">
                    <input maxlength="500" <%=readonly%> onkeyup="checkComm(this)"
                           name="noCOMMNMTYPE" id="<%=noCOMMNMTYPE%>" style="width:190px;"
                           value="<%=vaCOMMNMTYPE%>"/>
                </td>
                <td class="page_form_td">
                    <%=Level.radioHere(noAPPTYPE, "AppType", vaAPPTYPE)%>
                </td>
                <td class="page_form_td">
                    <input maxlength="10" <%=readonly%> onkeyup="checkComm(this)" id="<%=noNUM%>"
                           onblur="countTotalNum(this,'TOTALNUM')"
                           style="ime-mode:disabled;width:70px;"
                           name="noNUM" value="<%=vaNUM%>"/>
                </td>
                <td class="page_form_td">
                    <input maxlength="12" <%=readonly%> onkeyup="checkComm(this)" id="<%=noAMT%>"
                           onblur="countTotalNum(this,'TOTALAMT')"
                           style="ime-mode:disabled;width:100px;"
                           name="noAMT" value="<%=vaAMT%>"/>
                </td>
                <td class="page_form_td">
                    <input maxlength="12" <%=readonly%> onkeyup="checkComm(this)" id="<%=noRECEIVEAMT%>"
                           onblur="countTotalNum(this,'RECEIVEAMT')"
                           style="ime-mode:disabled;width:100px;"
                           name="noRECEIVEAMT" value="<%=vaRECEIVEAMT%>"/>
                </td>
            </tr>
            <%
                        rowCnt++;
                    }
                }
                String strRowCnt = String.valueOf(rowCnt);
                noCOMMNO = "COMMNO" + strRowCnt;
                noCOMMNMTYPE = "COMMNMTYPE" + strRowCnt;
                noNUM = "NUM" + strRowCnt;
                noAMT = "AMT" + strRowCnt;
                noRECEIVEAMT = "RECEIVEAMT" + strRowCnt;
                noAPPTYPE = "APPTYPE" + strRowCnt;
            %>
            <tr>
                <td style="display:none;">
                    <input type="text" name="noCOMMNO" id="<%=noCOMMNO%>" value="<%=strRowCnt%>"/>
                </td>
                <td class="page_form_td">
                    <input maxlength="500" <%=readonly%> onkeyup="checkComm(this)"
                           id="<%=noCOMMNMTYPE%>" style="width:190px;"
                           name="noCOMMNMTYPE" value=""/>
                </td>
                <td class="page_form_td">
                    <%=Level.radioHere(noAPPTYPE, "AppType", "")%>
                </td>
                <td class="page_form_td">
                    <input maxlength="12" <%=readonly%> onkeyup="checkComm(this)" id="<%=noNUM%>"
                           onblur="countTotalNum(this,'TOTALNUM')"
                           style="ime-mode:disabled;width:70px;"
                           name="noNUM" value=""/>
                </td>
                <td class="page_form_td">
                    <input maxlength="12" <%=readonly%> onkeyup="checkComm(this)" id="<%=noAMT%>"
                           onblur="countTotalNum(this,'TOTALAMT')"
                           style="ime-mode:disabled;width:100px;"
                           name="noAMT" value=""/>
                </td>
                <td class="page_form_td">
                    <input maxlength="12" <%=readonly%> onkeyup="checkComm(this)" id="<%=noRECEIVEAMT%>"
                           onblur="countTotalNum(this,'RECEIVEAMT')"
                           style="ime-mode:disabled;width:100px;"
                           name="noRECEIVEAMT" value=""/>
                </td>
            </tr>
        </table>
    </td>
</tr>
<tr class='page_form_tr'>
    <td colspan="2" class="page_form_title_td">���۵�λ�����ƣ���</td>
    <td colspan="4" class="page_form_td">
        <textarea name="CHANNEL" <%=readonly%> class="page_form_textfield" style="width:478px" rows="3"
                  onkeyup="limitTextarea(this)"
                  onchange="limitTextarea(this)"
                  onpropertychange="limitTextarea(this)"><%=CHANNEL == null ? "" : CHANNEL.trim()%>
        </textarea>
    </td>
    <td class="page_form_td" nowrap>&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td colspan="2" class="page_form_title_td" nowrap>�⹺��Ʒʹ�õ�ַ��</td>
    <td colspan="4" class="page_form_td">
        <input type="text" <%=readonly%> name="ADDR" id="ADDR"
               value="<%=ADDR==null?"":ADDR%>"
               class="page_form_text" style="width:478px" maxlength="40">
    </td>
    <td class="page_form_td" nowrap>&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td colspan="2" class="page_form_title_td" nowrap>&nbsp;�⹺��Ʒ��������</td>
    <td colspan="1" class="page_form_td">
        <input type="text" readonly="true" name="TOTALNUM"
               value="<%=TOTALNUM==null?"":TOTALNUM%>"
               class="page_form_text" maxlength="10"></td>
    <td class="page_form_td">̨/��</td>
    <td class="page_form_title_td" nowrap>&nbsp;��Ʒ�ܽ�</td>
    <td class="page_form_td">
        <input type="text" readonly="true" name="TOTALAMT"
               value="<%=TOTALAMT==null?"":TOTALAMT%>"
               class="page_form_text" maxlength="12" onKeyUp="countAppAmt()">
    </td>
    <td class="page_form_td">Ԫ</td>
</tr>
<tr class='page_form_tr'>
    <td colspan="2" class="page_form_title_td" nowrap>&nbsp;����Ա��</td>
    <td colspan="1" class="page_form_td">
        <input type="text" <%=readonly%> name="SALER"
               value="<%=SALER==null?"":SALER%>"
               class="page_form_text" maxlength="12">
    </td>
    <td class="page_form_td"><span style="color:red">*</span></td>
    <td class="page_form_title_td">&nbsp;���׸��</td>
    <td class="page_form_td">
        <input type="text" name="RECEIVEAMT"
               value="<%=RECEIVEAMT==null?"":RECEIVEAMT%>"
               class="page_form_text" maxlength="12" onblur="countAppAmtAndRate(this)"
                ></td>
    <td class="page_form_td">Ԫ</td>
</tr>
<tr class='page_form_tr'>
    <td colspan="2" class="page_form_title_td" nowrap>&nbsp;�׸����Ƿ���գ�</td>
    <td colspan="5" class="page_form_td">
        <%=Level.radioHere("PREPAY_CUTPAY_TYPE", "YesNo", PREPAY_CUTPAY_TYPE)%>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_button_tbl_tr" colspan="7" height="5"></td>
</tr>
<tr class='page_form_tr'>
    <td rowspan="6" class="page_left_table_title">����������</td>
    <td class="page_form_title_td" nowrap>��������ܽ���</td>
    <td class="page_form_td">
        <input type="text" readonly="true" name="APPAMT" id="APPAMT"
               value="<%=APPAMT==null?"":APPAMT%>"
               class="page_form_text" maxlength="15"></td>
    <td class="page_form_td">Ԫ</td>
    <td class="page_form_title_td" nowrap>&nbsp;�������ޣ�</td>
    <td colspan="2" nowrap class="page_form_td"><%=Level.radioHere("DIVID", "Divid", DIVID)%>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;���ʽ��
        <input type="hidden" id="UNIONCHANNEL" name="UNIONCHANNEL"
               value="<%=UNIONCHANNEL==null?"0":UNIONCHANNEL%>"/>
        <input type="hidden" id="ACTOPENINGBANK" name="ACTOPENINGBANK"
               value="<%=ACTOPENINGBANK==null?"0":ACTOPENINGBANK%>"/>
        <input type="hidden" id="CITY" name="CITY" value="<%=CITY==null?"":CITY%>"/>
    </td>
    <td colspan="2" nowrap class="page_form_td">
        <%=Level.levelHereNew("ACTOPENINGBANKUP", "Bank4App", "UnionPay", ACTOPENINGBANKUP)%>
    </td>
    <td class="page_form_title_td" nowrap>�ʻ�����</td>
    <td nowrap class="page_form_td">
        <input maxlength="40" <%=readonly%> name="BANKACTNAME" id="BANKACTNAME"
               class="page_form_text" value="<%=BANKACTNAME==null?NAME:BANKACTNAME%>"/>
    </td>
    <td class="page_form_td"></td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;�����˺ţ�</td>
    <td class="page_form_td">
        <input type="text" <%=readonly%> name="BANKACTNO"
               value="<%=BANKACTNO==null?"":BANKACTNO%>"
               class="page_form_text" maxlength="30">
    </td>
    <td class="page_form_td">&nbsp;</td>
    <td class="page_form_title_td" nowrap><span style="display:none;" id="lblPROVINCE">ѡ��ʡ�ݣ�</span></td>
    <td nowrap class="page_form_td">
        <%=Level.levelHere("PROVINCE", "PROVINCE", PROVINCE)%>
    </td>
    <td style="display:none;"><span id="BANK_UD1">&nbsp;���������ƣ�</span>
        <input type="text" id="BANK_UD2" style="display:none" <%=readonly%>
               name="ACTOPENINGBANK_UD"
               value="<%=ACTOPENINGBANK_UD==null?"":ACTOPENINGBANK_UD%>"
               class="page_form_text" maxlength="40"></td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap><span>&nbsp;��̯��ʽ��</span></td>
    <td nowrap class="page_form_td">
        <%=Level.levelHere("SHARETYPE", "SHARETYPE", SHARETYPE)%>
    </td>
    <td class="page_form_td">&nbsp;</td>
    <td class="page_form_title_td" nowrap>&nbsp;�����գ�</td>
    <td class="page_form_td">
        <input type="text" readonly="true" name="COMPAYDATE"
               value="<%=COMPAYDATE%>" class="page_form_text">
    </td>
    <td class="page_form_td" nowrap>��</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;�ͻ��������ʣ�</td>
    <td class="page_form_td">
        <input type="text" <%=readonly%> name="COMMISSIONRATE"
               value="<%=COMMISSIONRATE==null?"":COMMISSIONRATE%>" onblur="blurcountMONTHPAYSLRRATE()"
               class="page_form_text" maxlength="13">
    </td>
    <td class="page_form_td">��</td>
    <td class="page_form_title_td" nowrap><span>&nbsp;�̻��������ʣ�</span></td>
    <td nowrap class="page_form_td"><input type="text" <%=readonly%>
                                           name="SHCOMMISSIONRATE"
                                           value="<%=SHCOMMISSIONRATE==null?"":SHCOMMISSIONRATE%>"
                                           class="page_form_text" maxlength="13"></td>
    <td class="page_form_td">%</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap><span>&nbsp;�ͻ������ѽ��ɷ�ʽ��</span></td>
    <td nowrap class="page_form_td">
        <%=Level.levelHere("COMMISSIONTYPE", "COMMISSIONTYPE", COMMISSIONTYPE)%>
    </td>
    <td colspan="4">&nbsp;</td>
</tr>

<tr class='page_form_tr'>
    <td class="page_button_tbl_tr" colspan="7" height="5"></td>
</tr>
<tr class='page_form_tr'>
    <td rowspan="4" class="page_left_table_title">��������ʹ�����</td>
    <td class="page_form_title_td" nowrap>&nbsp;�������ࣺ</td>
    <td colspan="5" class="page_form_td" nowrap><%=Level.checkHere("CREDITTYPE", "CreditType", CREDITTYPE)%>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;���д����¾�����</td>
    <td class="page_form_td">
        <input type="text" readonly="true" name="MONPAYAMT"
               value="<%=MONPAYAMT==null?"":MONPAYAMT%>"
               class="page_form_text" maxlength="12"></td>
    <td class="page_form_td">Ԫ</td>
    <td class="page_form_title_td" nowrap>&nbsp;���ڸ�����������ʣ�</td>
    <td class="page_form_td">
        <input type="text" readonly="true" name="APPSALARYRATE"
               value="<%=APPSALARYRATE==null?"":APPSALARYRATE%>"
               class="page_form_text" maxlength="8"></td>
    <td class="page_form_td">%</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;�ñʷ��ڻ���</td>
    <td class="page_form_td">
        <input type="text" readonly="true" name="CCDIVIDAMT"
               value="<%=CCDIVIDAMT==null?"":CCDIVIDAMT%>"
               class="page_form_text" maxlength="12"></td>
    <td class="page_form_td">Ԫ</td>
    <td class="page_form_title_td" nowrap>&nbsp;ÿ�»�����������ʣ�</td>
    <td class="page_form_td">
        <input type="text" readonly="true" name="MONTHPAYSLRRATE"
               value="<%=MONTHPAYSLRRATE==null?"":MONTHPAYSLRRATE%>"
               class="page_form_text" maxlength="8"></td>
    <td class="page_form_td">%</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;���ܻ���</td>
    <td class="page_form_td">
        <input type="text" readonly name="CCRPTOTALAMT"
               value="<%=CCRPTOTALAMT==null?"":CCRPTOTALAMT%>"
               class="page_form_text" maxlength="12"></td>
    <td colspan="4" class="page_form_td">Ԫ</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_button_tbl_tr" colspan="7" height="5"></td>
</tr>
<tr class='page_form_tr'>
    <td rowspan="2" class="page_left_table_title">���Ż���<br/>
        �Ŵ���¼
    </td>
    <td class="page_form_title_td" nowrap>
        ���ü�¼�ڼ䣺
    </td>
    <td colspan="5">
        <%=Level.radioHere("CCVALIDPERIOD", "CCValidPeriod", CCVALIDPERIOD)%>
    </td>
</tr>
<tr>
    <td colspan="7" width="600">
        <table class='page_form_regTable' width="100%" cellpadding="0" cellspacing="0" border="1">
            <col width="150"/>
            <col width="150"/>
            <col width="150"/>
            <col width="150"/>
            <tr>
                <td class="page_form_title_td">&nbsp;</td>
                <td class="page_form_title_td">��Ѻ����</td>
                <td class="page_form_title_td">���ô���</td>
                <td class="page_form_title_td">���ÿ�</td>
            </tr>
            <tr>
                <td class="page_form_title_td">����(��)������</td>
                <td>
                    <input type="text" <%=readonly%> name="CCDYNUM" onblur="blurChkDb(this)"
                           value="<%=CCDYNUM==null?"":CCDYNUM%>" class="page_form_text" maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCXYNUM" onblur="blurChkDb(this)"
                           value="<%=CCXYNUM==null?"":CCXYNUM%>" class="page_form_text" maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCCDNUM" onblur="blurChkDb(this)"
                           value="<%=CCCDNUM==null?"":CCCDNUM%>" class="page_form_text" maxlength="12"/>
                </td>
            </tr>
            <tr>
                <td class="page_form_title_td">�ܴ����/��ȣ�</td>
                <td>
                    <input type="text" <%=readonly%> name="CCDYAMT" onblur="blurChkDb(this)"
                           value="<%=CCDYAMT==null?"":CCDYAMT%>" class="page_form_text" maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCXYAMT" onblur="blurChkDb(this)"
                           value="<%=CCXYAMT==null?"":CCXYAMT%>" class="page_form_text" maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCCDAMT" onblur="blurChkDb(this)"
                           value="<%=CCCDAMT==null?"":CCCDAMT%>" class="page_form_text" maxlength="12"/>
                </td>
            </tr>
            <tr>
                <td class="page_form_title_td">�ܴ������/��ȣ�</td>
                <td>
                    <input type="text" <%=readonly%> name="CCDYNOWBAL" onblur="blurChkDb(this)"
                           value="<%=CCDYNOWBAL==null?"":CCDYNOWBAL%>" class="page_form_text" maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCXYNOWBAL" onblur="blurChkDb(this)"
                           value="<%=CCXYNOWBAL==null?"":CCXYNOWBAL%>" class="page_form_text" maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCCDNOWBAL" onblur="blurChkDb(this)"
                           value="<%=CCCDNOWBAL==null?"":CCCDNOWBAL%>" class="page_form_text" maxlength="12"/>
                </td>
            </tr>
            <tr>
                <td class="page_form_title_td">���»���</td>
                <td>
                    <input type="text" <%=readonly%> name="CCDYRPMON" onblur="countMonPayAmt(this)"
                           value="<%=CCDYRPMON==null?"":CCDYRPMON%>" class="page_form_text" maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCXYRPMON" onblur="countMonPayAmt(this)"
                           value="<%=CCXYRPMON==null?"":CCXYRPMON%>" class="page_form_text" maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCCDRPMON" onblur="countMonPayAmt(this)"
                           value="<%=CCCDRPMON==null?"":CCCDRPMON%>" class="page_form_text" maxlength="12"/>
                </td>
            </tr>
            <tr>
                <td class="page_form_title_td">12���»����¼(����)��</td>
                <td>
                    <input type="text" <%=readonly%> name="CCDYYEARRPTIME" onblur="blurChkDb(this)"
                           value="<%=CCDYYEARRPTIME==null?"":CCDYYEARRPTIME%>" class="page_form_text" maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCXYYEARRPTIME" onblur="blurChkDb(this)"
                           value="<%=CCXYYEARRPTIME==null?"":CCXYYEARRPTIME%>" class="page_form_text" maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCCDYEARRPTIME" onblur="blurChkDb(this)"
                           value="<%=CCCDYEARRPTIME==null?"":CCCDYEARRPTIME%>" class="page_form_text" maxlength="12"/>
                </td>
            </tr>
            <tr>
                <td class="page_form_title_td">�����ڣ�</td>
                <td>
                    <%=Level.radioHere("CCDYNOOVERDUE", "YesNo", CCDYNOOVERDUE)%>
                </td>
                <td>
                    <%=Level.radioHere("CCXYNOOVERDUE", "YesNo", CCXYNOOVERDUE)%>
                </td>
                <td>
                    <%=Level.radioHere("CCCDNOOVERDUE", "YesNo", CCCDNOOVERDUE)%>
                </td>
            </tr>
            <tr>
                <td class="page_form_title_td">����1-30��(1)��</td>
                <td>
                    <input type="text" <%=readonly%> name="CCDY1TIMEOVERDUE" onblur="blurChkDb(this)"
                           value="<%=CCDY1TIMEOVERDUE==null?"":CCDY1TIMEOVERDUE%>" class="page_form_text"
                           maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCXY1TIMEOVERDUE" onblur="blurChkDb(this)"
                           value="<%=CCXY1TIMEOVERDUE==null?"":CCXY1TIMEOVERDUE%>" class="page_form_text"
                           maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCCD1TIMEOVERDUE" onblur="blurChkDb(this)"
                           value="<%=CCCD1TIMEOVERDUE==null?"":CCCD1TIMEOVERDUE%>" class="page_form_text"
                           maxlength="12"/>
                </td>
            </tr>
            <tr>
                <td class="page_form_title_td">����31-60��(2)��</td>
                <td>
                    <input type="text" <%=readonly%> name="CCDY2TIMEOVERDUE" onblur="blurChkDb(this)"
                           value="<%=CCDY2TIMEOVERDUE==null?"":CCDY2TIMEOVERDUE%>" class="page_form_text"
                           maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCXY2TIMEOVERDUE" onblur="blurChkDb(this)"
                           value="<%=CCXY2TIMEOVERDUE==null?"":CCXY2TIMEOVERDUE%>" class="page_form_text"
                           maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCCD2TIMEOVERDUE" onblur="blurChkDb(this)"
                           value="<%=CCCD2TIMEOVERDUE==null?"":CCCD2TIMEOVERDUE%>" class="page_form_text"
                           maxlength="12"/>
                </td>
            </tr>
            <tr>
                <td class="page_form_title_td">����60������(�R3)��</td>
                <td>
                    <input type="text" <%=readonly%> name="CCDYM3TIMEOVERDUE" onblur="blurChkDb(this)"
                           value="<%=CCDYM3TIMEOVERDUE==null?"":CCDYM3TIMEOVERDUE%>" class="page_form_text"
                           maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCXY3TIMEOVERDUE" onblur="blurChkDb(this)"
                           value="<%=CCXY3TIMEOVERDUE==null?"":CCXY3TIMEOVERDUE%>" class="page_form_text"
                           maxlength="12"/>
                </td>
                <td>
                    <input type="text" <%=readonly%> name="CCCD3TIMEOVERDUE" onblur="blurChkDb(this)"
                           value="<%=CCCD3TIMEOVERDUE==null?"":CCCD3TIMEOVERDUE%>" class="page_form_text"
                           maxlength="12"/>
                </td>
            </tr>
        </table>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_button_tbl_tr" colspan="7" height="5"></td>
</tr>
<tr class='page_form_tr'>
    <td rowspan="7" class="page_left_table_title">����Ҫ��
    </td>
    <td colspan="4" class="page_form_title_td" nowrap>&nbsp;����Ҫ��21-60</td>
    <td colspan="2" class="page_form_td">
        <%=Level.radioHere("ACAGE", "YesNo", ACAGE)%>
    </td>
    <td class="page_form_td">&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td colspan="4" class="page_form_title_td" nowrap>&nbsp;ÿ����͹���Ҫ�󣺲����������2,000Ԫ</td>
    <td colspan="2" class="page_form_td">
        <%=Level.radioHere("ACWAGE", "YesNo", ACWAGE)%>
    </td>
    <td class="page_form_td">&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td colspan="4" class="page_form_title_td" nowrap>&nbsp;����ϵͳ�����һ����δ�����¼��</td>
    <td colspan="2" class="page_form_td">
        <%=Level.radioHere("ACZX1", "YesNo", ACZX1)%>
    </td>
    <td class="page_form_td">&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td colspan="4" class="page_form_title_td" nowrap>&nbsp;����ϵͳ�ڹ�ȥ12����������31-60���ڲ�����2�Σ�</td>
    <td colspan="2" class="page_form_td">
        <%=Level.radioHere("ACZX2", "YesNo", ACZX2)%>
    </td>
    <td class="page_form_td">&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td colspan="4" class="page_form_title_td" nowrap>&nbsp;����ϵͳ�ڹ�ȥ3����û��һ�����ڳ���60�죺</td>
    <td colspan="2" class="page_form_td">
        <%=Level.radioHere("ACZX3", "YesNo", ACZX3)%>
    </td>
    <td class="page_form_td">&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td colspan="4" class="page_form_title_td" nowrap>&nbsp;���ڸ�����������Ƿ����30%��</td>
    <td colspan="2" class="page_form_td">
        <%=Level.radioHere("ACFACILITY", "YesNo", ACFACILITY)%>
    </td>
    <td class="page_form_td">&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td colspan="4" class="page_form_title_td" nowrap>&nbsp;ÿ�»�����������Ƿ����60%��</td>
    <td colspan="2" class="page_form_td">
        <%=Level.radioHere("ACRATE", "YesNo", ACRATE)%>
    </td>
    <td class="page_form_td">&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_button_tbl_tr" colspan="7" height="5"></td>
</tr>
<tr class='page_form_tr'>
    <td rowspan="2" class="page_left_table_title" nowrap>�ʼĵ�ַ<br><span
            style="font-size:12px;font-weight:normal; color:red;">���ź����˵���ַ��</span>
    </td>
    <td class="page_form_title_td" nowrap>&nbsp;�ʼĵ�ַ��</td>
    <td colspan="4" class="page_form_td" nowrap>
        <input type="text" <%=readonly%> name="RESDADDR"
               value="<%=RESDADDR==null?"":RESDADDR%>"
               class="page_form_text" style="width:478px" maxlength="40">
    </td>
    <td class="page_form_td" nowrap>&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;�������룺</td>
    <td colspan="4" class="page_form_td"><input type="text" <%=readonly%> id="RESDPC" name="RESDPC"
                                                value="<%=RESDPC==null?"":RESDPC%>"
                                                class="page_form_text" maxlength="6"></td>

    <td class="page_form_td" nowrap>&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_button_tbl_tr" colspan="7" height="5"></td>
</tr>
<tr class='page_form_tr'>
    <td rowspan="5" class="page_left_table_title">������ϵ��</td>
    <td class="page_form_title_td" nowrap>&nbsp;��&nbsp;&nbsp;����</td>
    <td class="page_form_td">
        <input type="text" <%=readonly%> name="LINKMAN"
               value="<%=LINKMAN==null?"":LINKMAN%>"
               class="page_form_text" maxlength="40"
               onblur="checkLinkname('LINKMAN')"></td>
    <td class="page_form_td">&nbsp;</td>
    <td class="page_form_title_td" nowrap>&nbsp;��&nbsp;&nbsp;��</td>
    <td colspan="2" nowrap class="page_form_td"><%=Level.radioHere("LINKMANGENDER", "Gender", LINKMANGENDER)%>
    </td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;��&nbsp;&nbsp;����</td>
    <td class="page_form_td">
        <input type="text" <%=readonly%> name="LINKMANPHONE1"
               value="<%=LINKMANPHONE1==null?"":LINKMANPHONE1%>"
               class="page_form_text" maxlength="15"
               onblur="checkLinkPhone('LINKMANPHONE1');"></td>
    <td class="page_form_td">&nbsp;</td>
    <td class="page_form_title_td" nowrap>&nbsp;�̶��绰��</td>
    <td nowrap class="page_form_td"><input type="text" <%=readonly%> name="LINKMANPHONE2"
                                           value="<%=LINKMANPHONE2==null?"":LINKMANPHONE2%>"
                                           class="page_form_text" maxlength="15"></td>
    <td class="page_form_td">&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;�������˹�ϵ��</td>
    <td colspan="4" class="page_form_td">
        <%=Level.radioHere("APPRELATION", "AppRelation", APPRELATION)%>
    </td>
    <td class="page_form_td" nowrap>&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;��ͥ��ַ��</td>
    <td colspan="4" class="page_form_td">
        <input type="text" <%=readonly%> name="LINKMANADD"
               value="<%=LINKMANADD==null?"":LINKMANADD%>"
               class="page_form_text" style="width:478px" maxlength="40">
    </td>
    <td class="page_form_td" nowrap>&nbsp;</td>
</tr>
<tr class='page_form_tr'>
    <td class="page_form_title_td" nowrap>&nbsp;������λ��</td>
    <td colspan="4" class="page_form_td">
        <input type="text" <%=readonly%> name="LINKMANCOMPANY"
               value="<%=LINKMANCOMPANY==null?"":LINKMANCOMPANY%>"
               class="page_form_text" style="width:478px" maxlength="40">
    </td>
    <td class="page_form_td" nowrap>&nbsp;</td>
</tr>

</table>

<input type='hidden' name='Plat_Form_Request_Instance_ID' value='2'>
<input type='hidden' name='Plat_Form_Request_Event_ID' value=''>
<input type='hidden' name='Plat_Form_Request_Event_Value' value='12'>
<input type='hidden' name='Plat_Form_Request_Button_Event' value=''>
</form>
</td>
<td width="20">&nbsp;</td>
</tr>
</table>
</td>
</tr>
<tr height="35" align="center" valign="middle">
    <td align="center">
        <table border="0" cellspacing="0" cellpadding="0" width="538" class="Noprint">
            <tr class='page_form_tr'>
                <td nowrap align="center">
                    <table bgcolor="#ffffff">
                        <tr class='page_button_tbl_tr'>
                            <%
                                if (SENDFLAG == null || !SENDFLAG.equals("1")) {
                            %>
                            <td class='page_button_tbl_td'><input type='button' <%=submit%> id='saveadd' name='save'
                                                                  value=' �� �� '/></td>
                            <%
                                }
                            %>
                            <td class='page_button_tbl_td'><input type='button' <%=submit%> id='reback' name='reback'
                                                                  value=' �� �� '
                                                                  onClick="winClose()">
                            </td>
                        </tr>
                    </table>
                </td>
            </tr>
        </table>
    </td>
</tr>
</table>
</td>
</tr>
</table>
</body>

</html>
<script src='../../../js/jquery-1.6.js' type="text/javascript"></script>
<script language="javascript" type="text/javascript">
var citySZ = "����";
var cityGZ = "����";
var provinceGD = "�㶫";
var provinceHN = "����";
document.onkeydown = function TabReplace() {
    if (event.keyCode == 13) {
        if (event.srcElement.getAttribute("id") != 'saveadd')
            event.keyCode = 9;
//        else
//            event.srcElement.click();
    }
}
function winClose() {
    window.close();
}

$(document).ready(function() {
    setIDblur();
    setADDRblur();
});

/**
 * ���������֤����Ϣ*/
function setIDblur() {
    $("#ID").blur(function() {
        var idtypeobj = document.getElementById('IDTYPE');
        var idobj = document.getElementById("ID");
        if (checkIDCard(document.getElementById('IDTYPE'), idobj)) getbirthday(idobj.value, 'BIRTHDAY');
        var appno = $("#APPNO").val();
        if (idobj.value != "") {
            var data = {"appno":appno,"idtype":idtypeobj.value,"idval":idobj.value,"addr":$("#ADDR").val(),"dotype":"checkID"};
            $.ajax({
                url:"application_query.jsp",
                type:"get",
                data:data,
                success:function(json) {
                    var applen = json[0].chkID.length;
                    if (applen != 0) {
                        alert(checkOrigApp(json));
                    }
//                alert(json[0].chkID.length);alert(json[1].totalamt);alert(json[2].chkaddr);
                }
            });
        }

    });
}

/*���֤���ͻ�ԭ�д�������*/
function checkOrigApp(json) {
    var applen = json[0].chkID.length;
    var alertstr = "";
    var plan = "---------------------------------------------------------------------------\r\n";
    alertstr += "�ÿͻ��Ѵ��ڴ������룺\r\n" + plan;
    for (var i = 0; i < applen; i++) {
        alertstr += "�ͻ���:" + json[0].chkID[i].name + ";  ���뵥��:" + json[0].chkID[i].appno + ";  ���ڽ��:" + json[0].chkID[i].appamt + "\r\n";
        alertstr += plan;
    }
    var appamt = parseFloat($("#APPAMT").val() == "" ? "0" : $("#APPAMT").val());
    var totalamt = parseFloat(json[1].totalamt) + appamt;
    alertstr += "�������:" + totalamt + "\r\n";
    if (totalamt >= 50000) {
        alertstr += plan;
        alertstr += "�ÿͻ����д���������,��ֹ�����롣";
//        $("#saveadd").attr("disabled", "true");
    }
//    alert(alertstr);
    return alertstr;
}

/**
 * Ϊ�����͵�ַ��� ���ύ��ťclick�¼������˴�  todo return false*/

function setADDRblur() {
    $("#saveadd").click(function() {
        var idtypeobj = document.getElementById('IDTYPE');
        var idobj = document.getElementById("ID");
        var appno = $("#APPNO").val();
        var data = {"appno":appno,"idtype":idtypeobj.value,"idval":idobj.value,"addr":$("#ADDR").val(),"dotype":"checkaddr"};
        var boolflag4id = true;
        var boolflag = "0";
        $.ajax({
            url:"application_query.jsp",
            type:"get",
            data:data,
            async:false,
            success:function(json) {
                var applen = json[0].chkID.length;
                var chkaddr = json[2].chkaddr;
                if (applen != 0) {
                    //���ͻ�ԭ�д�������
                    if (!confirm(checkOrigApp(json) + "\r\n�Ƿ��ύ��")) {
                        boolflag4id = false;
                    }
                }
                if (chkaddr != 0) {
                    boolflag = chkaddr;
                }

            }
        });
        if (boolflag4id == false) {
            $("#ID").css("color","red");
            $("#ID").focus();
            return false;
        } else if (boolflag != "0") {
            if (!confirm('��Ʒʹ�õ�ַ�����ظ���\r\n�ظ�'+boolflag + "\r\n�Ƿ�ȷ���ύ��")) {
                $("#ADDR").css("color","red");
                $("#ADDR").focus();
                return false;
            }
        }
        Regvalid();
    });
}

/*
 * blur�¼����ּ��*/

function blurChkDb(obj) {
    if (isNaN(obj.value)) {
        obj.value = "";
        obj.focus();
        alert("����������");
        return false;
    } else return true;
}
/**
 * ����������Ϣ�ĵ��»���ϼ�
 * ��ȡ���д����¾������ MONPAYAMT
 * ���� ���д����¾������
 *      ÿ�»������������
 *      ÿ���ܻ���� 11*/

function countMonPayAmt(obj) {
    if (!blurChkDb(obj)) return false;
    var ccdymon = document.getElementById("CCDYRPMON").value;
    var ccxymon = document.getElementById("CCXYRPMON").value;
    var cccdmon = document.getElementById("CCCDRPMON").value;
    ccdymon = (ccdymon == "") ? "0" : ccdymon;
    ccxymon = (ccxymon == "") ? "0" : ccxymon;
    cccdmon = (cccdmon == "") ? "0" : cccdmon;
    var monpayamt = parseFloat(ccdymon) + parseFloat(ccxymon) + parseFloat(cccdmon);
    document.getElementById("MONPAYAMT").value = Math.round(monpayamt * 100) / 100;  //���д����¾������
    var ccdividamt = document.getElementById("CCDIVIDAMT").value; //�ñʷ��ڻ����
    ccdividamt = (ccdividamt == "") ? "0" : ccdividamt;
    document.getElementById("CCRPTOTALAMT").value = Math.round((parseFloat(ccdividamt) + monpayamt) * 100) / 100;  //���ܻ����
    blurcountMONTHPAYSLRRATE();   //����ÿ�»������������
}
/**
 * �Զ������е��õ�
 * ��Ʒ��Ϣ¼����*/
function checkCommExtend() {
    var obj = getElement();
    checkComm(obj);
}

/*        COMMNMTYPE
 * ��Ʒ��Ϣ¼����*/

function checkComm(obj) {
    var comnumber = obj.getAttribute("id");
    comnumber = comnumber.substring(comnumber.length - 1, comnumber.length);
    for (var i = 1; i < comnumber; i++) {
        if (!isEmptyItem1("COMMNMTYPE" + i, "����������¼����Ʒ��Ϣ��")) {
            obj.value = "";
            return false;
        } else if (!isEmptyItem1("NUM" + i, "����������¼����Ʒ��Ϣ��")) {
            obj.value = "";
            return false;
        } else if (!isEmptyItem1("AMT" + i, "����������¼����Ʒ��Ϣ��")) {
            obj.value = "";
            return false;
        } else if (!isEmptyItem1("RECEIVEAMT" + i, "����������¼����Ʒ��Ϣ��")) {
            obj.value = "";
            return false;
        }
    }
}

/**
 * �ж��Ƿ�Ϊ�� ���� true or false*/

function textIsEmpty(objId) {
    var obj = document.getElementById(objId);
    if (obj.value.Trim() == "") return true;
    else return false;
}
/**��Ʒ��Ϣ���һ��*/

function jurgeLastRow() {
    var tabObj = document.getElementById("tabCommContent");
    var rowLen = tabObj.rows.length;
    var comnumber = rowLen - 2;
    if ((textIsEmpty("COMMNMTYPE" + comnumber) && textIsEmpty("NUM" + comnumber) && textIsEmpty("AMT" + comnumber)
            && textIsEmpty("RECEIVEAMT" + comnumber)) || (!textIsEmpty("COMMNMTYPE" + comnumber) && !textIsEmpty("NUM" + comnumber)
            && !textIsEmpty("AMT" + comnumber) && !textIsEmpty("RECEIVEAMT" + comnumber))) {
        return true;
    } else {
        document.getElementById("tabCommContent").focus();
        alert("������¼�����һ����Ʒ��Ϣ��");
        return false;
    }
}
/** �ж��Ƿ���¼������ blur�е���*/
function jurgeTurnRow(obj) {
    var comnumber = obj.getAttribute("id");
    comnumber = comnumber.substring(comnumber.length - 1, comnumber.length);
    if (!isEmptyItem1("COMMNMTYPE" + comnumber, "������¼����Ʒ��Ϣ��")) {
        obj.value = "";
        return false;
    } else if (!isEmptyItem1("NUM" + comnumber, "������¼����Ʒ��Ϣ��")) {
        obj.value = "";
        return false;
    } else if (!isEmptyItem1("AMT" + comnumber, "������¼����Ʒ��Ϣ��")) {
        obj.value = "";
        return false;
    } else if (!isEmptyItem1("RECEIVEAMT" + comnumber, "������¼����Ʒ��Ϣ��")) {
        obj.value = "";
        return false;
    }
    return true;
}

/* �Զ������е��õ�
 * ������Ʒ�������ܶ���׸�*/

function countTotalNumExtd(totalId) {
    var obj = getElement();
    countTotalNum(obj, totalId);
}

function countTotalNum(obj, totalId) {
    if (isNaN(obj.value)) {
        alert("����������.");
        obj.value = "";
        obj.focus();
        return false;
    }
    var objNoId = obj.getAttribute("id");
    var objId = objNoId.substr(0, objNoId.length - 1);
    var tabObj = document.getElementById("tabCommContent");
    var rowLen = tabObj.rows.length;
    var totalnum = 0;
    for (var i = 1; i < (rowLen - 1); i++) {
        var num = document.getElementById(objId + i).value;
        num = num == "" ? "0" : num;
        totalnum += parseFloat(num, 10);
    }
    if (objId == "NUM") {
        totalnum = parseInt(totalnum, 10);
    } else {
        totalnum = Math.round(totalnum * 100) / 100;
    }
    document.getElementById(totalId).value = totalnum;
    if (objId != "NUM") {
        countAppAmt();
        blurcountMONTHPAYSLRRATE();
    }
    var rowNum = parseInt(objNoId.substr(objNoId.length - 1, objNoId.length), 10) + 2;
    if (rowNum == rowLen && objId == "RECEIVEAMT") {
        if (!jurgeTurnRow(obj)) return false;
        appendCommRow(tabObj);
        document.getElementById("COMMNMTYPE" + (rowNum - 1)).focus();
    }
}

/**
 * ���׸���¼����blur
 * ���� �����ܸ��� */
function countAppAmtAndRate(obj) {
    if (isNaN(obj.value)) {
        alert("����������.");
        obj.value = "";
        obj.focus();
        return false;
    }
    countAppAmt();
    blurcountMONTHPAYSLRRATE();
}
/*
 * �»���� blur*/

function blurMonthpay(obj) {
    if (isNaN(obj.value)) {
        alert("����������.");
        obj.value = "";
        obj.focus();
        return false;
    }
    blurcountMONTHPAYSLRRATE();
}

/*
 * ���� ��ծ��ÿ�»�������������� �·��ڻ����������*/
function blurcountMONTHPAYSLRRATE() {
    var e = document.getElementsByName("DIVID");
    var obj = null;
    for (var i = 0; i < e.length; i++) {
        if (e[i].checked) {
            obj = e[i];
            break;
        }
    }
    if (obj != null) countMONTHPAYSLRRATE(obj);
}
/*
 * ������ڸ������������
 * ��ծ��ÿ�»�������������� 11*/

function countMONTHPAYSLRRATE(obj) {
    var slrypay = document.getElementById("MONTHLYPAY").value;             //������
    var monpayamt = document.getElementById("MONPAYAMT").value;            //�»����
    slrypay = slrypay == "" ? "0" : slrypay;
    monpayamt = monpayamt == "" ? "0" : monpayamt;
    if (slrypay != "0") {
        var appamt = document.getElementById("APPAMT").value;
        var commrate = document.getElementById("COMMISSIONRATE").value;
        appamt = parseFloat((appamt == "" ? "0" : appamt));
        var divid = (obj.value == "" ? "0" : obj.value);
        divid = parseFloat(divid);
        monpayamt = parseFloat(monpayamt);
        slrypay = parseFloat(slrypay);
        //��ծ��ÿ�»��������������
        var totalmonpay = ((commrate / 1000 * divid + 1) * appamt / divid) + monpayamt;
        var monpayslrrate = Math.round((totalmonpay / slrypay * 100) * 100) / 100;
        document.getElementById("MONTHPAYSLRRATE").value = monpayslrrate;
        //������ڸ������������
        var appslrypay = ((commrate / 1000 * divid + 1) * appamt / divid);     //�ñ��·��ڻ�����
        var apppayrate = Math.round((appslrypay / slrypay * 100) * 100) / 100;
        document.getElementById("APPSALARYRATE").value = apppayrate;
        //�ñʷ��ڻ�����
        document.getElementById("CCDIVIDAMT").value = Math.round(appslrypay * 100) / 100; //�ñʷ��ڻ����
        var monpayamt = document.getElementById("MONPAYAMT").value; //���д����¾������
        monpayamt = (monpayamt == "") ? "0" : monpayamt;
        document.getElementById("CCRPTOTALAMT").value = Math.round((parseFloat(monpayamt) + appslrypay) * 100) / 100;  //���ܻ����
    }
}

function setDIVIDRadio(str) {
    var e = document.getElementsByName(str);
    for (var i = 0; i < e.length; i++) {
        if (window.addEventListener) { // Mozilla, Netscape, Firefox
            e[i].addEventListener('click', resetDIVIDRadio(), false);
        }
        else {
            e[i].attachEvent("onclick", function() {
                resetDIVIDRadio();
            });
        }
    }
}

function resetDIVIDRadio() {
    var obj = getElement();
    countMONTHPAYSLRRATE(obj);
}

document.title = "<%=title%>";
document.focus();

function setBankRadio(str) {
    var obj = document.getElementsByName(str)[0];
    var labelName = obj.options[obj.selectedIndex].text.split("-")[0];

    var obj2 = document.getElementsByName("BANKACTNO");
    inputObjpreObj(obj2).innerText = " " + labelName + "�ʺţ�";
    document.getElementById("BANK_UD2").value = labelName;
    if (obj.value == '901') {
        //haiyu 2010-08-11 for �޸�cell����ɫ
        getObject("BANK_UD2").style.display = "block";
        getObject('BANK_UD1').parentNode.display = "block";
    }
    //�ж��Ƿ�ͨ������
    document.getElementById("UNIONCHANNEL").value = obj.value.split("-")[1];
    //��ȡ���д���
    var bankno = obj.value.split("-")[0];
    var bankno2 = new Array();
    bankno2 = bankno.split("@");
    if (bankno == "104") {
        document.getElementById("PROVINCE").style.display = "block";
        document.getElementById("lblPROVINCE").style.display = "block";
    } else {
        document.getElementById("PROVINCE").style.display = "none";
        document.getElementById("lblPROVINCE").style.display = "none";
    }
    if (bankno2.length == 1) {
        document.getElementById("ACTOPENINGBANK").value = bankno;
        document.getElementById("CITY").value = "";  //���ڳ�������Ϊ��
    }
    else {
        //������ҵ����
        if (bankno2[1] == "313") {
            if (bankno2[0] == "1") {
                document.getElementById("CITY").value = citySZ;  //���ڳ���
                document.getElementById("PROVINCE").value = provinceGD;      //�㶫
            } else if (bankno2[0] == "2") {
                document.getElementById("CITY").value = cityGZ;  //���ڳ���
                document.getElementById("PROVINCE").value = provinceGD;      //�㶫
            }
        } else if (bankno2[1] == "403") {                           //�ʴ�����
            if (bankno2[0] == "1") {
                document.getElementById("PROVINCE").value = provinceGD;      //�㶫
                document.getElementById("CITY").value = "";  //���ڳ��п�
            } else if (bankno2[0] == "2") {
                document.getElementById("PROVINCE").value = provinceHN;       //����
                document.getElementById("CITY").value = "";  //���ڳ��п�
            }
        }
    }
    if (window.addEventListener) { // Mozilla, Netscape, Firefox
        obj.addEventListener('click', reSetActnoText("BANKACTNO"), false);
    }
    else {
        obj.attachEvent("onclick", function() {
            reSetActnoText("BANKACTNO");
        });
    }
}

document.getElementsByName("CREDITTYPE")[0].onclick = function() {
    listCheck("CREDITTYPE", 0);
}

document.body.onload = function() {
    setBankRadio("ACTOPENINGBANKUP");
    listCheck("CREDITTYPE", 0);
    setDIVIDRadio("DIVID");
    document.getElementById("XFAPPNO").focus();
}

function reSetActnoText(str) {
    var obj1 = getElement();
    var obj2 = document.getElementsByName(str);
    if (obj1.value == '901') {
        getObject("BANK_UD2").style.display = "block";
        getObject('BANK_UD1').parentNode.style.display = "block";
    } else {
        getObject("BANK_UD2").style.display = "none";
        getObject('BANK_UD1').parentNode.style.display = "none";
    }
    var bankname = obj1.options[obj1.selectedIndex].text.split("-")[0];
    inputObjpreObj(obj2).innerText = " " + bankname + "�ʺţ�";
    obj2[0].value = "";
    document.getElementById("BANK_UD2").value = bankname;
    //�ж��Ƿ�ͨ������
    document.getElementById("UNIONCHANNEL").value = obj1.value.split("-")[1];
    //��ȡ���д���
//    document.getElementById("ACTOPENINGBANK").value = obj1.value.split("-")[0];
    var bankno = obj1.value.split("-")[0];
    if (bankno == "104") {
        document.getElementById("PROVINCE").style.display = "block";
        document.getElementById("lblPROVINCE").style.display = "block";
    } else {
        document.getElementById("PROVINCE").style.display = "none";
        document.getElementById("lblPROVINCE").style.display = "none";
    }
    var bankno2 = new Array();
    bankno2 = bankno.split("@");
    if (bankno2.length == 1) {
        document.getElementById("ACTOPENINGBANK").value = bankno;
        document.getElementById("CITY").value = "";  //���ڳ�������Ϊ��
    }
    else {
        document.getElementById("ACTOPENINGBANK").value = bankno2[1]; //�������д���
        //������������ʡ�� ������ҵ����
        if (bankno2[1] == "313") {
            if (bankno2[0] == "1") {
                document.getElementById("CITY").value = citySZ;  //���ڳ���
                document.getElementById("PROVINCE").value = provinceGD;      //�㶫
            } else if (bankno2[0] == "2") {
                document.getElementById("CITY").value = cityGZ;  //���ڳ���
                document.getElementById("PROVINCE").value = provinceGD;      //�㶫
            }
        } else if (bankno2[1] == "403") {                           //�ʴ�����
            if (bankno2[0] == "1") {
                document.getElementById("PROVINCE").value = provinceGD;      //�㶫
                document.getElementById("CITY").value = "";  //���ڳ��п�
            } else if (bankno2[0] == "2") {
                document.getElementById("PROVINCE").value = provinceHN;       //����
                document.getElementById("CITY").value = "";  //���ڳ��п�
            }
        }
    }
}

function limitTextarea(obj) {
    var len = 150;
    //    if (obj.value.getBytes() > len){
    if (obj.value.length > len) {
        obj.value = obj.value.substr(0, len);
    }
}

function listCheck(objnm, id) {
    var crdtps = document.getElementsByName(objnm);
    if (crdtps[id].checked) {
        for (var i = 0; i < crdtps.length; i++) {
            if (i == id)continue;
            crdtps[i].checked = false;
            crdtps[i].disabled = true;
        }
        document.getElementById("MONPAYAMT").value = 0;
//        document.getElementById("MONPAYAMT").readOnly = true;
        document.getElementById("CCDYRPMON").readOnly = true;
        document.getElementById("CCXYRPMON").readOnly = true;
        document.getElementById("CCCDRPMON").readOnly = true;
        blurcountMONTHPAYSLRRATE();
    } else {
        for (var i = 0; i < crdtps.length; i++) {
            if (i == id)continue;
            crdtps[i].disabled = false;
        }
//        document.getElementById("MONPAYAMT").readOnly = false;
        document.getElementById("CCDYRPMON").readOnly = false;
        document.getElementById("CCXYRPMON").readOnly = false;
        document.getElementById("CCCDRPMON").readOnly = false;
    }
}

function fileup(appno, appstatus) {
    var filetp = "APP";
    var url = "../fileupdown/fileup.jsp?OPNO=" + appno + "&FILETP=" + filetp + "&APPSTATUS=" + appstatus;

    var x = window.screen.width;
    var y = window.screen.height;
    x = (x - 390) / 2;
    y = (y - 470) / 2;
    //window.showModalDialog(url, 'fileup', 'dialogHeight:390px;dialogWidth:450px;status:no;scroll:no;help:no;resizable:yes');
    window.open(url, 'fileup', 'left=' + x + ',top=' + y + ',height=390,width=470,toolbar=no,scrollbars=yes,resizable=yes');
}


function goSave(bank) {
    if (!isEmptyItem("XFAPPNO")) return false;
    if (!isEmptyItem("NAME")) return false;
    if (!isEmptyItem("GENDER"))return false;
    if (!isEmptyItem("PHONE1"))return false;
    else if (!isPhone("PHONE1"))return false;
    if (!isEmptyItem("ID"))return false;
    if (!isEmptyItem("HEALTHSTATUS"))return false;
    if (!isEmptyItem("MARRIAGESTATUS"))return false;
    if (!isEmptyItem("BURDENSTATUS"))return false;
    if (!isEmptyItem("RESIDENCEADR"))return false;
    if (!isEmptyItem("CURRENTADDRESS"))return false;
    if (!isZipCode("PC"))return false;
    if (!isEmail("EMAIL"))return false;
    if (!isEmptyItem("PHONE2"))return false;
    else if (!isPhone("PHONE2"))return false;
    if (!isEmptyItem("LIVEFROM"))return false;
    else if (!chkintWithAlStr("LIVEFROM", "���ؾ�סʱ���뱣��������"))return false;
    if (!isEmptyItem("EDULEVEL"))return false;
    if (!isEmptyItem("QUALIFICATION"))return false;
    if (!isEmptyItem("COMPANY"))return false;
    if (!isEmptyItem("PHONE3"))return false;
    else if (!isPhone("PHONE3"))return false;
    if (!isZipCode("COMPC"))return false;
    if (!isEmptyItem("COMADDR"))return false;
    if (!isEmptyItem("CLIENTTYPE"))return false;
    if (!isEmptyItem("SERVFROM"))return false;
    else if (!chkintWithAlStr("SERVFROM", "�ֵ�λ����ʱ���뱣��������"))return false;

    if (!isEmptyItem("MONTHLYPAY"))return false;
    else if (!chkdecWithAlStr("MONTHLYPAY", "������������¼�����֣�"))return false;
    //if (!isEmptyItem("SOCIALSECURITY"))return false;
    if (document.getElementById("PID").value.Trim() != "") {
        if (document.getElementById("ID").value == document.getElementById("PID").value) {
            alert("��ż֤�������������ʵ��");
            document.getElementById("PID").focus();
            return false;
        }
    }
    if (document.getElementById("PNAME").value.Trim() != "") {
        if (!isEmptyItem("PID"))return false;
        if (!isEmptyItem("PCLIENTTYPE"))return false;
        if (!isEmptyItem("PMONTHLYPAY"))return false;
        else if (!chkdecWithAlStr("PMONTHLYPAY", "������������¼�����֣�"))return false;
    }
    if (!isPhone("PPHONE1"))return false;
    if (!isPhone("PPHONE3"))return false;

    if (!isEmptyItem("CHANNEL"))return false;
    if (!isEmptyItem("TOTALNUM"))return false;
    else if (!chkintWithAlStr("TOTALNUM", "�⹺��Ʒ����������������"))return false;

    if (!isEmptyItem("TOTALAMT"))return false;
    else if (!chkdecWithAlStr("TOTALAMT", "��Ʒ�ܽ����¼�����֣�"))return false;
    if (!chkdecWithAlStr("RECEIVEAMT", "�Ѹ��ۿ���¼�����֣�"))return false;
    if (!isEmptyItem("DIVID"))return false;
    if (!isEmptyItem("ACTOPENINGBANKUP"))return false;
    if (!isEmptyItem("BANKACTNAME")) return false;
    if (bank != "802")
        if (!isEmptyItem("BANKACTNO"))return false;
    if (bank == "901")
        if (!isEmptyItem("ACTOPENINGBANK_UD"))return false;
    if (!isEmptyItem("CREDITTYPE"))return false;
    if (!isEmptyItem("MONPAYAMT"))return false;
    else if (!chkdecWithAlStr("MONPAYAMT", "�¾��������¼�����֣�"))return false;
    if (!isEmptyItem("RESDADDR"))return false;
    if (!isEmptyItem("RESDPC"))return false;
    else if (!isZipCode("RESDPC"))return false;
    if (!isEmptyItem("LINKMAN"))return false;
    if (!isEmptyItem("LINKMANPHONE1"))return false;
    else if (!isPhone("LINKMANPHONE1"))return false;
    if (!isPhone("LINKMANPHONE2"))return false;
    if (!isEmptyItem("APPRELATION"))return false;
    if (!checkLinkname('LINKMAN')) return false;
    if (!checkLinkPhone('LINKMANPHONE1')) return false;
    if (!isEmptyItem1("COMMNMTYPE1", "��Ʒ���Ʋ���Ϊ��!")) return false;
    if (!jurgeLastRow()) return false;   //�ж����һ����Ʒ¼���Ƿ�����
    if (!isEmptyItem("HOUSINGSTS")) return false;
    if (!isEmptyItem("ETPSCOPTYPE")) return false;
    if (!isEmptyItem("ANNUALINCOME")) return false;
    else if (!chkdecWithAlStr("ANNUALINCOME", "���������������֣�")) return false;
    if (!isEmptyItem("ACAGE")) return false;
    if (!isEmptyItem("ACWAGE")) return false;
    if (!isEmptyItem("ACZX1")) return false;
    if (!isEmptyItem("ACZX2")) return false;
    if (!isEmptyItem("ACZX3")) return false;
    if (!isEmptyItem("ACFACILITY")) return false;
    if (!isEmptyItem("ACRATE")) return false;
    if (!checkAmt()) return false;
    return true;
}


function Regvalid() {//�ύ
    var bank;
    var e = document.getElementsByName("ACTOPENINGBANK");
    bank = e[0].value;
    if (goSave(bank)) {
        //������֧���´��ڿ���
        //if (bank == "801" || bank == "802")document.forms[0].target = "_blank";
//        document.forms[0].target = "_blank";
        document.forms[0].action = "application_save.jsp";
        document.forms[0].submit();
    }
}

function Regvalid1() {//����
    if (confirm("ȷʵҪ�������뵥��")) {
//        document.forms[0].target = "_blank";
        document.all.APPACTFLAG.value = "3";
        document.forms[0].action = "application_save.jsp";
        document.forms[0].submit();
    }
}

function Regvalid2() {//�˻�
    if (confirm("ȷʵҪ�����뵥�˻���һ��������")) {
        document.all.APPACTFLAG.value = "2";
        document.forms[0].action = "application_save.jsp";
        document.forms[0].submit();
    }
}

function countAppAmt() {//�Զ�������ڽ��
    var obj1 = document.getElementsByName("TOTALAMT")[0];
    var obj2 = document.getElementsByName("RECEIVEAMT")[0];
    var obj3 = document.getElementsByName("APPAMT")[0];

    if (obj1.value != null && chkdec("TOTALAMT")) {
        var countamt = 0;
        if (obj2.value != null && chkdec("RECEIVEAMT")) {
            countamt = obj1.value - obj2.value;
        } else countamt = obj1.value;
        obj3.value = Math.round(countamt * 100) / 100;
    }
}


function getObject(objectId) {
    if (document.getElementById && document.getElementById(objectId)) {

        return document.getElementById(objectId);
    } else if (document.all && document.all(objectId)) {

        return document.all(objectId);
    } else if (document.layers && document.layers[objectId]) {

        return document.layers[objectId];
    } else {
        return false;
    }
}

function showList(n) {
    var itext = getObject('HT' + n).innerText;
    itext = itext.substr(0, itext.indexOf(" -- "));
    if (getObject("HA" + n).style.display == "none") {
        disList(n, itext);
    }
    else {
        hidList(n, itext);
    }
}

function disList(n, itext) {
    getObject('HT' + n).title = "�������";
    getObject('HT' + n).innerText = itext + " -- ��(�������)";
    getObject("HA" + n).style.display = "block";
}

function hidList(n, itext) {
    getObject('HT' + n).title = "���չ��";
    getObject('HT' + n).innerText = itext + " -- ��(���չ��)";
    getObject("HA" + n).style.display = "none";
}


function mOvr(src) {
    if (!src.contains(event.fromElement)) {
        dataBgColor = src.bgColor;
        src.style.cursor = "hand";
        src.style.backgroundColor = "#E1FFF0";
    }
}

function mOut(src) {
    if (!src.contains(event.toElement)) {
        src.style.cursor = "default";
        src.style.backgroundColor = "#FDF8DF";
    }
}

//20100108  zhanrui ���ӶԵ�����ϵ�˵ļ��
//function checkName() {
//    if (document.getElementById("RESDADDR").value == "")
//        document.getElementById("RESDADDR").value = document.getElementById("CURRENTADDRESS").value;
//}

</script>

<%if (APPNO.equals("")) {%>
<script type="text/javascript">
    document.forms[0].CHANNEL.focus();
</script>

<%
    }
    if (!PNAME.equals("")) {%>
<script type="text/javascript">
    showList(1);
</script>
<%
    }
    if (APPSTATUS.equals("")) {
%>
<script type="text/javascript">
    //    document.getElementById("print").className = "page_button_disabled";
    //    document.getElementById("print").disabled = true;
</script>
<%
    }
    if (!SID.equals("")) {
%>
<script type="text/javascript">
    countAppAmt();
    document.forms[0].CHANNEL.readOnly = true;
    document.forms[0].TOTALNUM.readOnly = true;
    document.forms[0].TOTALAMT.readOnly = true;
</script>
<%}%>
<%}%>


