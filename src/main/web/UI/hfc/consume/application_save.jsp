<%@ page contentType="text/html; charset=GBK" %>
<%@ page import="javax.sql.rowset.CachedRowSet" %>
<%@ page import="java.math.BigDecimal" %>
<%@ page import="pub.cenum.EnumValue" %>
<%@ page import="pub.platform.db.ConnectionManager" %>
<%@ page import="hfc.xf.XFConf" %>
<%@ page import="org.apache.commons.lang.StringUtils" %>
<%@ page import="pub.platform.security.OperatorManager" %>
<%@ page import="fip.common.SystemService" %>
<%@ page import="pub.platform.form.config.SystemAttributeNames" %>
<%@ page import="com.ccb.util.SeqUtil" %>
<%@ page import="pub.platform.advance.utils.PropertyManager" %>

<%--
===============================================
Title: �����Ŵ�-�������ѷ��ڸ����������ύ
Description: �����Ŵ�-�������ѷ��ڸ����������ύ��
 * @version  $Revision: 1.0 $  $Date: 2009/03/11 06:33:37 $
 * @author
 * <p/>�޸ģ�$Author: liuj $
===============================================
--%>
<SCRIPT language=JavaScript>
    <!--

    self.moveTo((screen.width - 450) / 2, (screen.height - 400) / 2);
    self.resizeTo(450, 420);

    this.onunload = function() {
        window.opener.location.reload();
    }
    //-->
</SCRIPT>
<%
    request.setCharacterEncoding("GBK");
    String CLIENTNO = request.getParameter("CLIENTNO");   //�ͻ���
    String RECVERSION = request.getParameter("RECVERSION");
    String COMMISSIONRATE = request.getParameter("COMMISSIONRATE");   //�ͻ���������
    String SHCOMMISSIONRATE = request.getParameter("SHCOMMISSIONRATE");//�̻���������
    String COMPAYDATE = request.getParameter("COMPAYDATE");
    String SHARETYPE = request.getParameter("SHARETYPE");
    String COMMISSIONTYPE = request.getParameter("COMMISSIONTYPE");
    /*SERVFROMMONTH,LIVEFROMMONTH  PSERVFROMMONTH  PLIVEFROMMONTH*/
    String SERVFROMMONTH = request.getParameter("SERVFROMMONTH");        //�־���
    String LIVEFROMMONTH = request.getParameter("LIVEFROMMONTH");        //��
    String PSERVFROMMONTH = request.getParameter("PSERVFROMMONTH");
    String PLIVEFROMMONTH = request.getParameter("PLIVEFROMMONTH");
    SERVFROMMONTH = (SERVFROMMONTH == null || StringUtils.isEmpty(SERVFROMMONTH)) ? "0" : SERVFROMMONTH;
    LIVEFROMMONTH = (LIVEFROMMONTH == null || StringUtils.isEmpty(LIVEFROMMONTH)) ? "0" : LIVEFROMMONTH;
    PSERVFROMMONTH = (PSERVFROMMONTH == null || StringUtils.isEmpty(PSERVFROMMONTH)) ? "0" : PSERVFROMMONTH;
    PLIVEFROMMONTH = (PLIVEFROMMONTH == null || StringUtils.isEmpty(PLIVEFROMMONTH)) ? "0" : PLIVEFROMMONTH;
    String SOCIALSECURITYID = request.getParameter("SOCIALSECURITYID");
    String ANNUALINCOME = request.getParameter("ANNUALINCOME");
    String ETPSCOPTYPE = request.getParameter("ETPSCOPTYPE");
    String SALER = request.getParameter("SALER");
    String APPSALARYRATE = request.getParameter("APPSALARYRATE");
    String MONTHPAYSLRRATE = request.getParameter("MONTHPAYSLRRATE");
    String SLRYEVETYPE = request.getParameter("SLRYEVETYPE");
    //������Ϣ
    String CCRPTOTALAMT = request.getParameter("CCRPTOTALAMT");
    String CCVALIDPERIOD = request.getParameter("CCVALIDPERIOD");
    String CCDYNUM = request.getParameter("CCDYNUM");
    String CCXYNUM = request.getParameter("CCXYNUM");
    String CCCDNUM = request.getParameter("CCCDNUM");
    String CCDYAMT = request.getParameter("CCDYAMT");
    String CCXYAMT = request.getParameter("CCXYAMT");
    String CCCDAMT = request.getParameter("CCCDAMT");
    String CCDYNOWBAL = request.getParameter("CCDYNOWBAL");
    String CCXYNOWBAL = request.getParameter("CCXYNOWBAL");
    String CCCDNOWBAL = request.getParameter("CCCDNOWBAL");
    String CCDYRPMON = request.getParameter("CCDYRPMON");
    String CCXYRPMON = request.getParameter("CCXYRPMON");
    String CCCDRPMON = request.getParameter("CCCDRPMON");
    String CCDYYEARRPTIME = request.getParameter("CCDYYEARRPTIME");
    String CCXYYEARRPTIME = request.getParameter("CCXYYEARRPTIME");
    String CCCDYEARRPTIME = request.getParameter("CCCDYEARRPTIME");
    String CCDYNOOVERDUE = request.getParameter("CCDYNOOVERDUE");
    String CCXYNOOVERDUE = request.getParameter("CCXYNOOVERDUE");
    String CCCDNOOVERDUE = request.getParameter("CCCDNOOVERDUE");
    String CCDY1TIMEOVERDUE = request.getParameter("CCDY1TIMEOVERDUE");
    String CCXY1TIMEOVERDUE = request.getParameter("CCXY1TIMEOVERDUE");
    String CCCD1TIMEOVERDUE = request.getParameter("CCCD1TIMEOVERDUE");
    String CCDY2TIMEOVERDUE = request.getParameter("CCDY2TIMEOVERDUE");
    String CCXY2TIMEOVERDUE = request.getParameter("CCXY2TIMEOVERDUE");
    String CCCD2TIMEOVERDUE = request.getParameter("CCCD2TIMEOVERDUE");
    String CCDYM3TIMEOVERDUE = request.getParameter("CCDYM3TIMEOVERDUE");
    String CCXY3TIMEOVERDUE = request.getParameter("CCXY3TIMEOVERDUE");
    String CCCD3TIMEOVERDUE = request.getParameter("CCCD3TIMEOVERDUE");
    String ACAGE = request.getParameter("ACAGE");
    String ACWAGE = request.getParameter("ACWAGE");
    String ACZX1 = request.getParameter("ACZX1");
    String ACZX2 = request.getParameter("ACZX2");
    String ACZX3 = request.getParameter("ACZX3");
    String ACFACILITY = request.getParameter("ACFACILITY");
    String ACRATE = request.getParameter("ACRATE");
    String CCDIVIDAMT = request.getParameter("CCDIVIDAMT");

    String APPACTFLAG = request.getParameter("APPACTFLAG");           //���뵥ִ��״̬   ִ�ж�����־��1��������2���˻أ�3������
    String APPNO = request.getParameter("APPNO");                      //���뵥���
    String XFAPPNO = request.getParameter("XFAPPNO");                  //�����뵥���
    String APPDATE = request.getParameter("APPDATE");                  //��������
    String IDTYPE = request.getParameter("IDTYPE");                    //֤������
    String ID = request.getParameter("ID");                             //֤������
    String APPTYPE = "";                                               //�������� �ϳ�
    String APPSTATUS = request.getParameter("APPSTATUS");              //����״̬
    String BIRTHDAY = request.getParameter("BIRTHDAY");                //��������
    String GENDER = request.getParameter("GENDER");                    //�Ա� enum=Gender
    String NATIONALITY = request.getParameter("NATIONALITY");         //����
    String MARRIAGESTATUS = request.getParameter("MARRIAGESTATUS");   //����״�� enum=MarriageStatus
    String HUKOUADDRESS = request.getParameter("HUKOUADDRESS");       //�������ڵ�
    String CURRENTADDRESS = request.getParameter("CURRENTADDRESS");   //��סַ
    String COMPANY = request.getParameter("COMPANY");                  //������λ
    String TITLE = request.getParameter("TITLE");                      //ְ�� enum=Title
    String QUALIFICATION = request.getParameter("QUALIFICATION");     //ְ�� enum=Qualification
    String EDULEVEL = request.getParameter("EDULEVEL");                //ѧ�� enum=EduLevel
    String PHONE1 = request.getParameter("PHONE1");                   //�ƶ��绰
    String PHONE2 = request.getParameter("PHONE2");                   //��ͥ�绰
    String PHONE3 = request.getParameter("PHONE3");                   //�칫�绰
    String NAME = request.getParameter("NAME");                       //�ͻ����� desc=��ҵ(����)����
    String CLIENTTYPE = request.getParameter("CLIENTTYPE");           //�ͻ����� enum=ClientType1
    String DEGREETYPE = request.getParameter("DEGREETYPE");          //���ѧλ enum=DegreeType
    String COMADDR = request.getParameter("COMADDR");                 //��λ��ַ
    String SERVFROM = request.getParameter("SERVFROM");               //�ֵ�λ����ʱ��
    String RESIDENCEADR = request.getParameter("RESIDENCEADR");       //�������ڵ�(�����) enum=ResidenceADR
    String HOUSINGSTS = request.getParameter("HOUSINGSTS");           //��ס״�� enum=HousingSts
    String HEALTHSTATUS = request.getParameter("HEALTHSTATUS");       //����״�� enum=HealthStatus
    String MONTHLYPAY = request.getParameter("MONTHLYPAY");           //����������
    String BURDENSTATUS = request.getParameter("BURDENSTATUS");       //����״�� enum=BurdenStatus
    String EMPNO = request.getParameter("EMPNO");                      //Ա��������
    String SOCIALSECURITY_G[] = request.getParameterValues("SOCIALSECURITY");   //��ᱣ�� enum=SocialSecurity
    String LIVEFROM = request.getParameter("LIVEFROM");                //���ؾ�סʱ��
    String PC = request.getParameter("PC");                             //סլ�ʱ�
    String COMPC = request.getParameter("COMPC");                      //��λ�ʱ�
    String RESDPC = request.getParameter("RESDPC");                    //���͵�ַ�ʱ�
    String RESDADDR = request.getParameter("RESDADDR");                //���͵�ַ
    String EMAIL = request.getParameter("EMAIL");                      //�����ʼ�

    String PNAME = request.getParameter("PNAME");                      //��ż����
    String PIDTYPE = request.getParameter("PIDTYPE");                  //��ż֤������
    String PID = request.getParameter("PID");                          //��ż֤������
    String PCOMPANY = request.getParameter("PCOMPANY");                //��ż������λ
    String PTITLE = request.getParameter("PTITLE");                    //��żְ�� enum=Title
    String PPHONE1 = request.getParameter("PPHONE1");                  //��ż�ƶ��绰
    String PPHONE3 = request.getParameter("PPHONE3");                  //��ż�칫�绰
    String PCLIENTTYPE = request.getParameter("PCLIENTTYPE");         //��ż�ͻ�����(��λ����) enum=ClientType1
    String PSERVFROM = request.getParameter("PSERVFROM");             //��ż�ֵ�λ����ʱ��
    String PMONTHLYPAY = request.getParameter("PMONTHLYPAY");         //��ż����������
    String PLIVEFROM = request.getParameter("PLIVEFROM");             //��ż���ؾ�סʱ��

    String CHANNEL = request.getParameter("CHANNEL");                  //���۵�λ(��������)
//    String COMMNAME = request.getParameter("COMMNAME");                //��Ʒ����
//    String COMMTYPE = request.getParameter("COMMTYPE");                //��Ʒ�ͺ�
    String ADDR = request.getParameter("ADDR");                         //���͵�ַ
    String TOTALNUM = request.getParameter("TOTALNUM");                           //��������
    String TOTALAMT = request.getParameter("TOTALAMT");                           //�ܽ��
    String RECEIVEAMT = request.getParameter("RECEIVEAMT");            //�Ѹ����
    String APPAMT = request.getParameter("APPAMT");                     //���ڽ��
    String DIVID = request.getParameter("DIVID");                       //��������
    String PREPAY_CUTPAY_TYPE = request.getParameter("PREPAY_CUTPAY_TYPE");         //�׸����Ƿ����

    String ACTOPENINGBANK = request.getParameter("ACTOPENINGBANK");   //������ enum=Bank
    String BANKACTNO = request.getParameter("BANKACTNO");              //�����ʺ�
    String PROVINCE = request.getParameter("PROVINCE");                //��������ʡ��
    String CITY = request.getParameter("CITY");                        //�������ڳ���
    String XY = request.getParameter("XY");                             //���� enum=YesNo
    String XYR = request.getParameter("XYR");                           //����������
    String DY = request.getParameter("DY");                             //��Ѻ enum=YesNo
    String DYW = request.getParameter("DYW");                           //��Ѻ������
    String ZY = request.getParameter("ZY");                             //��Ѻ enum=YesNo
    String ZYW = request.getParameter("ZYW");                           //��Ѻ������
    String BZ = request.getParameter("BZ");                             //��֤ enum=YesNo
    String BZR = request.getParameter("BZR");                           //��֤������
    String CREDITTYPE_G[] = request.getParameterValues("CREDITTYPE");  //�������� enum=CreditType
    String MONPAYAMT = request.getParameter("MONPAYAMT");              //�¾������
    String LINKMAN = request.getParameter("LINKMAN");                   //��ϵ������
    String LINKMANGENDER = request.getParameter("LINKMANGENDER");      //��ϵ���Ա�
    String LINKMANPHONE1 = request.getParameter("LINKMANPHONE1");       //��ϵ���ƶ��绰
    String LINKMANPHONE2 = request.getParameter("LINKMANPHONE2");       //��ϵ�˹̶��绰
    String APPRELATION = request.getParameter("APPRELATION");           //�������˹�ϵ enum=AppRelation
    String LINKMANADD = request.getParameter("LINKMANADD");             //��ϵ��סַ
    String LINKMANCOMPANY = request.getParameter("LINKMANCOMPANY");    //��ϵ�˹�����λ
    String ACTOPENINGBANK_UD = request.getParameter("ACTOPENINGBANK_UD");    //���������ƣ���㡢��������¼�����ƣ�
    String BANKACTNAME = request.getParameter("BANKACTNAME");          //����
    String UNIONCHANNEL = request.getParameter("UNIONCHANNEL");        //�ǻ��ͨ������  01=ͨ������ 00=��ͨ��
    String SID = request.getParameter("SID");                           //�����̳Ǳ��
    String ORDERNO = request.getParameter("ORDERNO");                   //�����̳ǵ���
    String REQUESTTIME = request.getParameter("REQUESTTIME");          //�����̳Ƕ�������ʱ��

    //������Ϣ
    CCRPTOTALAMT = (CCRPTOTALAMT == null || StringUtils.isEmpty( CCRPTOTALAMT.trim())) ? "0" : CCRPTOTALAMT.trim();
    CCDYNUM = (CCDYNUM == null || StringUtils.isEmpty( CCDYNUM.trim())) ? "0" : CCDYNUM.trim();
    CCXYNUM = (CCXYNUM == null || StringUtils.isEmpty( CCXYNUM.trim())) ? "0" : CCXYNUM.trim();
    CCCDNUM = (CCCDNUM == null || StringUtils.isEmpty( CCCDNUM.trim())) ? "0" : CCCDNUM.trim();
    CCDYAMT = (CCDYAMT == null || StringUtils.isEmpty( CCDYAMT.trim())) ? "0" : CCDYAMT.trim();
    CCXYAMT = (CCXYAMT == null || StringUtils.isEmpty( CCXYAMT.trim())) ? "0" : CCXYAMT.trim();
    CCCDAMT = (CCCDAMT == null || StringUtils.isEmpty( CCCDAMT.trim())) ? "0" : CCCDAMT.trim();
    CCDYNOWBAL = (CCDYNOWBAL == null || StringUtils.isEmpty( CCDYNOWBAL.trim())) ? "0" : CCDYNOWBAL.trim();
    CCXYNOWBAL = (CCXYNOWBAL == null || StringUtils.isEmpty( CCXYNOWBAL.trim())) ? "0" : CCXYNOWBAL.trim();
    CCCDNOWBAL = (CCCDNOWBAL == null || StringUtils.isEmpty( CCCDNOWBAL.trim())) ? "0" : CCCDNOWBAL.trim();
    CCDYRPMON = (CCDYRPMON == null || StringUtils.isEmpty( CCDYRPMON.trim())) ? "0" : CCDYRPMON.trim();
    CCXYRPMON = (CCXYRPMON == null || StringUtils.isEmpty( CCXYRPMON.trim())) ? "0" : CCXYRPMON.trim();
    CCCDRPMON = (CCCDRPMON == null || StringUtils.isEmpty( CCCDRPMON.trim())) ? "0" : CCCDRPMON.trim();
    CCDYYEARRPTIME = (CCDYYEARRPTIME == null || StringUtils.isEmpty( CCDYYEARRPTIME.trim())) ? "0" : CCDYYEARRPTIME.trim();
    CCXYYEARRPTIME = (CCXYYEARRPTIME == null || StringUtils.isEmpty( CCXYYEARRPTIME.trim())) ? "0" : CCXYYEARRPTIME.trim();
    CCCDYEARRPTIME = (CCCDYEARRPTIME == null || StringUtils.isEmpty( CCCDYEARRPTIME.trim())) ? "0" : CCCDYEARRPTIME.trim();
    CCDY1TIMEOVERDUE = (CCDY1TIMEOVERDUE == null || StringUtils.isEmpty( CCDY1TIMEOVERDUE.trim())) ? "0" : CCDY1TIMEOVERDUE.trim();
    CCXY1TIMEOVERDUE = (CCXY1TIMEOVERDUE == null || StringUtils.isEmpty( CCXY1TIMEOVERDUE.trim())) ? "0" : CCXY1TIMEOVERDUE.trim();
    CCCD1TIMEOVERDUE = (CCCD1TIMEOVERDUE == null || StringUtils.isEmpty( CCCD1TIMEOVERDUE.trim())) ? "0" : CCCD1TIMEOVERDUE.trim();
    CCDY2TIMEOVERDUE = (CCDY2TIMEOVERDUE == null || StringUtils.isEmpty( CCDY2TIMEOVERDUE.trim())) ? "0" : CCDY2TIMEOVERDUE.trim();
    CCXY2TIMEOVERDUE = (CCXY2TIMEOVERDUE == null || StringUtils.isEmpty( CCXY2TIMEOVERDUE.trim())) ? "0" : CCXY2TIMEOVERDUE.trim();
    CCCD2TIMEOVERDUE = (CCCD2TIMEOVERDUE == null || StringUtils.isEmpty( CCCD2TIMEOVERDUE.trim())) ? "0" : CCCD2TIMEOVERDUE.trim();
    CCDYM3TIMEOVERDUE = (CCDYM3TIMEOVERDUE == null || StringUtils.isEmpty( CCDYM3TIMEOVERDUE.trim())) ? "0" : CCDYM3TIMEOVERDUE.trim();
    CCXY3TIMEOVERDUE = (CCXY3TIMEOVERDUE == null || StringUtils.isEmpty( CCXY3TIMEOVERDUE.trim())) ? "0" : CCXY3TIMEOVERDUE.trim();
    CCCD3TIMEOVERDUE = (CCCD3TIMEOVERDUE == null || StringUtils.isEmpty( CCCD3TIMEOVERDUE.trim())) ? "0" : CCCD3TIMEOVERDUE.trim();
    CCDIVIDAMT = (CCDIVIDAMT == null || StringUtils.isEmpty(CCDIVIDAMT.trim())) ? "0" : CCDIVIDAMT.trim();
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

    COMMISSIONRATE = (COMMISSIONRATE == null || StringUtils.isEmpty(COMMISSIONRATE)) ? "0" : COMMISSIONRATE;
    SHCOMMISSIONRATE = (SHCOMMISSIONRATE == null || StringUtils.isEmpty(SHCOMMISSIONRATE)) ? "0" : SHCOMMISSIONRATE;
    COMPAYDATE = (COMPAYDATE == null) ? "0" : COMPAYDATE;
    SHARETYPE = (SHARETYPE == null || StringUtils.isEmpty(SHARETYPE)) ? "0" : SHARETYPE;
    COMMISSIONTYPE = (COMMISSIONTYPE == null || StringUtils.isEmpty(COMMISSIONTYPE)) ? "0" : COMMISSIONTYPE;
    CLIENTNO = (CLIENTNO == null || StringUtils.isEmpty(CLIENTNO)) ? "0" : CLIENTNO;
    APPNO = (APPNO == null) ? "" : APPNO.trim();                    //���뵥���
    APPDATE = (APPDATE == null) ? "SYSDATE" : "to_date('" + APPDATE.trim() + "','YYYY-MM-DD')";      //��������
    IDTYPE = (IDTYPE == null) ? "" : IDTYPE;                        //֤������
    ID = (ID == null) ? "" : ID;                                    //֤������
    APPTYPE = (APPTYPE == null) ? "" : APPTYPE;                     //��������
    APPTYPE = (StringUtils.isEmpty(APPTYPE)) ? "0" : APPTYPE;            //Ĭ��Ϊ0 �ⲿ�ͻ�
    APPSTATUS = (APPSTATUS == null) ? "" : APPSTATUS;               //����״̬
    BIRTHDAY = (BIRTHDAY == null) ? "" : BIRTHDAY;                  //��������
    GENDER = (GENDER == null) ? "" : GENDER;                        //�Ա� enum=Gender
    NATIONALITY = (NATIONALITY == null) ? "" : NATIONALITY;         //����
    MARRIAGESTATUS = (MARRIAGESTATUS == null) ? "" : MARRIAGESTATUS;//����״�� enum=MarriageStatus
    HUKOUADDRESS = (HUKOUADDRESS == null) ? "" : HUKOUADDRESS;      //�������ڵ�
    CURRENTADDRESS = (CURRENTADDRESS == null) ? "" : CURRENTADDRESS;//��סַ
    COMPANY = (COMPANY == null) ? "" : COMPANY;                     //������λ
    TITLE = (TITLE == null) ? "" : TITLE;                           //ְ�� enum=Title
    QUALIFICATION = (QUALIFICATION == null) ? "" : QUALIFICATION;   //ְ�� enum=Qualification
    EDULEVEL = (EDULEVEL == null) ? "" : EDULEVEL;                  //ѧ�� enum=EduLevel
    PHONE1 = (PHONE1 == null) ? "" : PHONE1;                        //�ƶ��绰
    PHONE2 = (PHONE2 == null) ? "" : PHONE2;                        //��ͥ�绰
    PHONE3 = (PHONE3 == null) ? "" : PHONE3;                        //�칫�绰
    NAME = (NAME == null) ? "" : NAME;                              //�ͻ����� desc=(��ҵ(����)����
    CLIENTTYPE = (CLIENTTYPE == null) ? "" : CLIENTTYPE;            //�ͻ����� enum=ClientType1
    DEGREETYPE = (DEGREETYPE == null) ? "" : DEGREETYPE;            //���ѧλ enum=DegreeType
    COMADDR = (COMADDR == null) ? "" : COMADDR;                     //��λ��ַ
    SERVFROM = (SERVFROM == null) ? "" : SERVFROM;                  //�ֵ�λ����ʱ��
    RESIDENCEADR = (RESIDENCEADR == null) ? "" : RESIDENCEADR;      //�������ڵ�(�����) enum=ResidenceADR
    HOUSINGSTS = (HOUSINGSTS == null) ? "" : HOUSINGSTS;            //��ס״�� enum=HousingSts
    HEALTHSTATUS = (HEALTHSTATUS == null) ? "" : HEALTHSTATUS;      //����״�� enum=HealthStatus
    MONTHLYPAY = (MONTHLYPAY == null) ? "" : MONTHLYPAY;           //����������
    BURDENSTATUS = (BURDENSTATUS == null) ? "" : BURDENSTATUS;      //����״�� enum=BurdenStatus
    EMPNO = (EMPNO == null) ? "" : EMPNO.trim();                    //Ա��������
    LIVEFROM = (LIVEFROM == null) ? "" : LIVEFROM;                  //���ؾ�סʱ��
    PC = (PC == null) ? "" : PC;                                    //סլ�ʱ�
    COMPC = (COMPC == null) ? "" : COMPC;                           //��λ�ʱ�
    RESDPC = (RESDPC == null) ? "" : RESDPC;                        //���͵�ַ�ʱ�
    RESDADDR = (RESDADDR == null) ? "" : RESDADDR;                  //���͵�ַ
    EMAIL = (EMAIL == null) ? "" : EMAIL;                           //�����ʼ�

    PNAME = (PNAME == null) ? "" : PNAME;                           //��ż����
    PIDTYPE = (PIDTYPE == null) ? "" : PIDTYPE;                     //��ż֤������
    PID = (PID == null) ? "" : PID;                                 //��ż֤������
    PCOMPANY = (PCOMPANY == null) ? "" : PCOMPANY;                  //��ż������λ
    PTITLE = (PTITLE == null) ? "" : PTITLE;                        //��żְ�� enum=Title
    PPHONE1 = (PPHONE1 == null) ? "" : PPHONE1;                     //��ż�ƶ��绰
    PPHONE3 = (PPHONE3 == null) ? "" : PPHONE3;                     //��ż�칫�绰
    PCLIENTTYPE = (PCLIENTTYPE == null) ? "" : PCLIENTTYPE;         //��ż�ͻ�����(��λ����) enum=ClientType1
    PSERVFROM = (PSERVFROM == null) ? "" : PSERVFROM;               //��ż�ֵ�λ����ʱ��
    PMONTHLYPAY = (PMONTHLYPAY == null) ? "" : PMONTHLYPAY;        //��ż����������
    PLIVEFROM = (PLIVEFROM == null) ? "" : PLIVEFROM;               //��ż���ؾ�סʱ��

    CHANNEL = (CHANNEL == null) ? "" : CHANNEL.trim();              //���۵�λ(��������)
//    COMMNAME = (COMMNAME == null) ? "" : COMMNAME.trim();           //��Ʒ����
//    COMMTYPE = (COMMTYPE == null) ? "" : COMMTYPE.trim();           //��Ʒ�ͺ�
    ADDR = (ADDR == null) ? "" : ADDR;                              //���͵�ַ
    TOTALNUM = (TOTALNUM == null) ? "" : TOTALNUM;                  //��������
    TOTALAMT = (TOTALAMT == null) ? "" : TOTALAMT;                  //�ܽ��
    RECEIVEAMT = (RECEIVEAMT == null) ? "" : RECEIVEAMT;            //�Ѹ����
    APPAMT = (APPAMT == null) ? "" : APPAMT;                        //���ڽ��
    DIVID = (DIVID == null) ? "" : DIVID;                           //��������
    PREPAY_CUTPAY_TYPE = (PREPAY_CUTPAY_TYPE == null)?"0":PREPAY_CUTPAY_TYPE;         //�׸����Ƿ����
    ACTOPENINGBANK = (ACTOPENINGBANK == null) ? "" : ACTOPENINGBANK;//������ enum=Bank
    BANKACTNO = (BANKACTNO == null) ? "" : BANKACTNO;               //�����ʺ�
    PROVINCE = (PROVINCE == null) ? "":PROVINCE;                    //��������ʡ��
    CITY = (CITY == null) ? "":CITY;                                //�������ڳ���
    XY = (XY == null) ? "" : XY;                                    //���� enum=YesNo
    XYR = (XYR == null) ? "" : XYR;                                 //����������
    DY = (DY == null) ? "" : DY;                                    //��Ѻ enum=YesNo
    DYW = (DYW == null) ? "" : DYW;                                 //��Ѻ������
    ZY = (ZY == null) ? "" : ZY;                                    //��Ѻ enum=YesNo
    ZYW = (ZYW == null) ? "" : ZYW;                                 //��Ѻ������
    BZ = (BZ == null) ? "" : BZ;                                    //��֤ enum=YesNo
    BZR = (BZR == null) ? "" : BZR;                                 //��֤������
    MONPAYAMT = (MONPAYAMT == null) ? "" : MONPAYAMT;               //�¾������
    LINKMAN = (LINKMAN == null) ? "" : LINKMAN;                     //��ϵ������
    LINKMANGENDER = (LINKMANGENDER == null) ? "" : LINKMANGENDER;   //��ϵ���Ա�
    LINKMANPHONE1 = (LINKMANPHONE1 == null) ? "" : LINKMANPHONE1;   //��ϵ���ƶ��绰
    LINKMANPHONE2 = (LINKMANPHONE2 == null) ? "" : LINKMANPHONE2;   //��ϵ�˹̶��绰
    APPRELATION = (APPRELATION == null) ? "" : APPRELATION;         //�������˹�ϵ enum=AppRelation
    LINKMANADD = (LINKMANADD == null) ? "" : LINKMANADD;            //��ϵ��סַ
    LINKMANCOMPANY = (LINKMANCOMPANY == null) ? "" : LINKMANCOMPANY;//��ϵ�˹�����λ
    ACTOPENINGBANK_UD = (ACTOPENINGBANK_UD == null) ? "" : ACTOPENINGBANK_UD;//���������ƣ���㡢��������¼�����ƣ�
    BANKACTNAME = (BANKACTNAME == null) ? "" : BANKACTNAME;
    UNIONCHANNEL = (UNIONCHANNEL == null) ? "" : UNIONCHANNEL;          //ͨ������
    SID = (SID == null) ? "" : SID;                                 //�����̳Ǳ��
    ORDERNO = (ORDERNO == null) ? "" : ORDERNO;                     //�����̳ǵ���
    REQUESTTIME = (REQUESTTIME == null) ? "" : REQUESTTIME;         //�����̳Ƕ�������ʱ��

    BigDecimal dMONTHLYPAY = new BigDecimal((MONTHLYPAY.equals("")) ? "0" : MONTHLYPAY);
    BigDecimal dPMONTHLYPAY = new BigDecimal((PMONTHLYPAY.equals("")) ? "0" : PMONTHLYPAY);
    String CONFMONPAY = String.valueOf(dMONTHLYPAY.add(dPMONTHLYPAY));//�˶�������


    if (IDTYPE.equals("") || ID.equals("")) {
        session.setAttribute("msg", "û�з��ִ�����Ĳ�����");
        response.sendRedirect("showinfo.jsp");
    }

    boolean temp = false;
    ConnectionManager manager = ConnectionManager.getInstance();
    CachedRowSet crs;
    String prevRecversion = "";
    String nowRecversion = "";
    if (!StringUtils.isEmpty(APPNO)) {
        prevRecversion = RECVERSION;
        CachedRowSet crsRecversion = manager.getRs("select RECVERSION from XFAPP where appno='" + APPNO + "'");
        crsRecversion.next();
        if (crsRecversion != null && crsRecversion.size() > 0)
            nowRecversion = crsRecversion.getString("RECVERSION");
    }
    if (!prevRecversion.equals(nowRecversion)) {
        //��������
        response.sendRedirect("application_start.jsp?sendflag=4&appno=" + APPNO);
    } else {

        //���ڶ�ѡ���Ĭ�ϵ� splitechar"_" �ָ��鴮
        String splitechar = EnumValue.SPLIT_STR, SOCIALSECURITY = "", CREDITTYPE = "";
        if (SOCIALSECURITY_G != null) {
            for (String aSOCIALSECURITY_G : SOCIALSECURITY_G) {
                SOCIALSECURITY += splitechar + aSOCIALSECURITY_G;
            }
            SOCIALSECURITY = SOCIALSECURITY.replaceFirst(splitechar, "");
        }
        if (CREDITTYPE_G != null) {
            for (String aCREDITTYPE_G : CREDITTYPE_G) {
                CREDITTYPE += splitechar + aCREDITTYPE_G;
            }
            CREDITTYPE = CREDITTYPE.replaceFirst(splitechar, "");
        }
        //��Ʒ��Ϣ��ȡ
        String COMMNOARY[] = request.getParameterValues("noCOMMNO");
        String COMMNMTYPEARY[] = request.getParameterValues("noCOMMNMTYPE");
        String NUMARY[] = request.getParameterValues("noNUM");
        String AMTARY[] = request.getParameterValues("noAMT");
        String RECEIVEAMTARY[] = request.getParameterValues("noRECEIVEAMT");
        int commCnt = COMMNMTYPEARY.length; //��Ʒ����
        if (StringUtils.isEmpty(COMMNMTYPEARY[commCnt - 1].trim())) {
            commCnt -= 1;
        }
        String[] sql = new String[9 + commCnt];
        String sql11, sql12, sql13;
        String SEQNO;
        try {
            if (APPNO.equals("")) {
                APPNO = XFAPPNO;
                if (ACTOPENINGBANK.equals("801") || ACTOPENINGBANK.equals("802"))
                    APPSTATUS = XFConf.APPSTATUS_QIANYUE; //֧��������Ǯ����ҪǩԼ״̬��
                else APPSTATUS = XFConf.APPSTATUS_TIJIAO;
                String XFAPP_PKID = SeqUtil.getAPPSEQ();
                sql[0] = "insert into XFAPP (appno,CLIENTNO,name, idtype, id, spouseidtype, spouseid, CONFMONPAY, " +
                        "APPTYPE, APPSTATUS, APPDATE, SID, ORDERNO, REQUESTTIME," +
                        "SALER,APPSALARYRATE,MONTHPAYSLRRATE,COMMISSIONRATE,CHANNEL,ADDR,TOTALNUM,TOTALAMT," +
                        "RECEIVEAMT,APPAMT,DIVID,SHCOMMISSIONRATE,COMPAYDATE,SHARETYPE,PKID,PREPAY_CUTPAY_TYPE,COMMISSIONTYPE) " +
                        "values ('" + APPNO + "','" + CLIENTNO + "','" + NAME + "','" + IDTYPE + "','" + ID + "','" + PIDTYPE + "','" + PID + "','" + CONFMONPAY + "','"
                        + APPTYPE + "','" + APPSTATUS + "'," + APPDATE + ",'" + SID + "','" + ORDERNO + "', to_date('" + REQUESTTIME + "','YYYYMMDDHH24MISS')," +
                        "'" + SALER + "'," + APPSALARYRATE + "," + MONTHPAYSLRRATE + "," + COMMISSIONRATE + ",'" + CHANNEL + "'," +
                        "'" + ADDR + "','" + TOTALNUM + "'," + TOTALAMT + "," + RECEIVEAMT + "," + APPAMT + ",'" + DIVID + "'," +
                        SHCOMMISSIONRATE + "," + COMPAYDATE + ",'" + SHARETYPE + "','" + XFAPP_PKID + "','" + PREPAY_CUTPAY_TYPE + "','" + COMMISSIONTYPE + "')";
                sql[1] = " insert into xfcreditinfobatch (appno, ccvalidperiod, ccdynum, ccxynum, cccdnum, ccdyamt, ccxyamt, cccdamt, ccdynowbal, ccxynowbal," +
                        " cccdnowbal, ccdyrpmon, ccxyrpmon, cccdrpmon, ccdyyearrptime, ccdynooverdue, ccxynooverdue, cccdnooverdue, ccdy1timeoverdue," +
                        " ccxy1timeoverdue, cccd1timeoverdue, ccdy2timeoverdue, ccxy2timeoverdue, cccd2timeoverdue, ccdym3timeoverdue, ccxy3timeoverdue," +
                        " cccd3timeoverdue, ccloanyearquerytime, cccardyearquerytime," +
                        " acage, acwage,acfacility,ACRATE,ccrptotalamt, ccrprate,aczx1, aczx2, aczx3," +
                        " ccxyyearrptime, cccdyearrptime,CCDIVIDAMT,CCRPNOWAMT) " +
                        " values ('" + APPNO + "','" + CCVALIDPERIOD + "'," + CCDYNUM + "," + CCXYNUM + "," + CCCDNUM + "," + CCDYAMT + "," +
                        " " + CCXYAMT + "," + CCCDAMT + "," + CCDYNOWBAL + "," + CCXYNOWBAL + "," + CCCDNOWBAL + "," + CCDYRPMON + "," + CCXYRPMON + "," +
                        " " + CCCDRPMON + "," + CCDYYEARRPTIME + ",'" + CCDYNOOVERDUE + "','" + CCXYNOOVERDUE + "','" + CCCDNOOVERDUE + "'," + CCDY1TIMEOVERDUE + "," + CCXY1TIMEOVERDUE + "," +
                        " " + CCCD1TIMEOVERDUE + "," + CCDY2TIMEOVERDUE + "," + CCXY2TIMEOVERDUE + "," + CCCD2TIMEOVERDUE + "," + CCDYM3TIMEOVERDUE + "," + CCXY3TIMEOVERDUE + "," + CCCD3TIMEOVERDUE + "," +
                        " 0,0,'" + ACAGE + "','" + ACWAGE + "','" + ACFACILITY + "','" + ACRATE + "'," + CCRPTOTALAMT + "," + MONTHPAYSLRRATE + ",'" + ACZX1 + "','" +
                        "" + ACZX2 + "','" + ACZX3 + "'," + CCXYYEARRPTIME + "," + CCCDYEARRPTIME + "," + CCDIVIDAMT + "," + MONPAYAMT + ")";
                sql[2] = "insert into XFAPPADD (appno, idtype, id, actopeningbank, bankactno, xy, xyr, dy, dyw, zy, zyw, bz, bzr, credittype, monpayamt, linkman, linkmangender, linkmanphone1,linkmanphone2,apprelation,LINKMANADD,LINKMANCOMPANY,ACTOPENINGBANK_UD) " +
                        "values ('" + APPNO + "','" + IDTYPE + "','" + ID + "','" + ACTOPENINGBANK + "','" + BANKACTNO + "','" + XY + "','" + XYR + "','" + DY + "','" + DYW + "','" + ZY + "','" + ZYW + "','" + BZ + "','" + BZR + "','" + CREDITTYPE + "','" + MONPAYAMT + "','" + LINKMAN + "','" + LINKMANGENDER + "','" + LINKMANPHONE1 + "','" + LINKMANPHONE2 + "','" + APPRELATION + "','" + LINKMANADD + "','" + LINKMANCOMPANY + "','" + ACTOPENINGBANK_UD + "')";
                sql[3] = "insert into XFCLIENT (APPNO,XFCLTP,LASTMODIFIED,APPDATE,BIRTHDAY,GENDER,NATIONALITY," +
                        "MARRIAGESTATUS,HUKOUADDRESS,CURRENTADDRESS,COMPANY,TITLE,QUALIFICATION,EDULEVEL," +
                        "PHONE1,PHONE2,PHONE3,CLIENTNO,NAME,IDTYPE,ID,CLIENTTYPE,DEGREETYPE,COMADDR,SERVFROM," +
                        "RESIDENCEADR,HOUSINGSTS,HEALTHSTATUS,MONTHLYPAY,BURDENSTATUS,EMPNO,SOCIALSECURITY," +
                        "LIVEFROM,PC,COMPC,RESDPC,RESDADDR,EMAIL,actopeningbank,bankactno,SLRYEVETYPE," +
                        "SERVFROMMONTH,LIVEFROMMONTH) " +
                        "values ('" + APPNO + "',1,SYSDATE," + APPDATE + ",to_date('" + BIRTHDAY + "','YYYY-MM-DD'),'" +
                        GENDER + "','" + NATIONALITY + "','" + MARRIAGESTATUS + "','" + HUKOUADDRESS + "','" + CURRENTADDRESS +
                        "','" + COMPANY + "','" + TITLE + "','" + QUALIFICATION + "','" + EDULEVEL + "','" + PHONE1 +
                        "','" + PHONE2 + "','" + PHONE3 + "','" + CLIENTNO + "','" + NAME + "','" + IDTYPE + "','" + ID +
                        "','" + CLIENTTYPE + "','" + DEGREETYPE + "','" + COMADDR + "'," + SERVFROM + ",'" + RESIDENCEADR +
                        "','" + HOUSINGSTS + "','" + HEALTHSTATUS + "','" + MONTHLYPAY + "','" + BURDENSTATUS + "','" + EMPNO +
                        "','" + SOCIALSECURITY + "'," + LIVEFROM + ",'" + PC + "','" + COMPC + "','" + RESDPC + "','" + RESDADDR +
                        "','" + EMAIL + "','" + ACTOPENINGBANK + "','" + BANKACTNO + "','" + SLRYEVETYPE + "'," +
                        SERVFROMMONTH + "," + LIVEFROMMONTH + ")";
                if (!PID.equals(""))
                    sql[4] = "insert into XFCLIENT (APPNO,XFCLTP,LASTMODIFIED,APPDATE,MARRIAGESTATUS,NAME,IDTYPE,ID," +
                            "COMPANY,TITLE,PHONE1,PHONE3,CLIENTTYPE,SERVFROM,MONTHLYPAY,LIVEFROM," +
                            "SERVFROMMONTH,LIVEFROMMONTH) " +
                            "values ('" + APPNO + "',2,SYSDATE," + APPDATE + ",'" + MARRIAGESTATUS + "','" + PNAME + "','" + PIDTYPE +
                            "','" + PID + "','" + PCOMPANY + "','" + PTITLE + "','" + PPHONE1 + "','" + PPHONE3 + "','" + PCLIENTTYPE +
                            "'," + PSERVFROM + "','" + PMONTHLYPAY + "'," + PLIVEFROM + "," +
                            PSERVFROMMONTH + "," + PLIVEFROMMONTH + ")";
                //20110718 haiyu ���ӱ�
                OperatorManager opm = (OperatorManager) session.getAttribute(SystemAttributeNames.USER_INFO_NAME);
                sql[5] = " insert into XFAPPREPAYMENT (appno, actopeningbank, bankactno, actopeningbank_ud, bankactname, channel, recversion, operid, operdate,PROVINCE,CITY) " +
                        "values ('" + APPNO + "','" + ACTOPENINGBANK + "','" + BANKACTNO + "','" + ACTOPENINGBANK_UD + "','" + BANKACTNAME + "'," +
                        "'" + UNIONCHANNEL + "',0,'" + opm.getOperatorId() + "',SYSDATE,'" + PROVINCE +  "','" + CITY + "')";

            } else {

                sql[0] = "update XFAPP set CLIENTNO='" + CLIENTNO + "', name='" + NAME + "',idtype='" + IDTYPE + "'," +
                        "id='" + ID + "',spouseidtype='" + PIDTYPE + "',spouseid='" + PID + "',CONFMONPAY='" + CONFMONPAY + "'," +
                        "APPTYPE='" + APPTYPE + "',APPSTATUS='" + APPSTATUS + "',APPDATE=" + APPDATE + ",SALER='" + SALER + "'," +
                        " APPSALARYRATE=" + APPSALARYRATE + ",MONTHPAYSLRRATE=" + MONTHPAYSLRRATE + ",COMMISSIONRATE=" + COMMISSIONRATE +
                        ",CHANNEL='" + CHANNEL + "',ADDR='" + ADDR + "',TOTALNUM='" + TOTALNUM + "',TOTALAMT=" + TOTALAMT +
                        ",RECEIVEAMT=" + RECEIVEAMT + ",APPAMT=" + APPAMT + ",DIVID='" + DIVID + "'" +
                        ",SHCOMMISSIONRATE=" + SHCOMMISSIONRATE +
                        ",COMPAYDATE=" + COMPAYDATE + ",SHARETYPE='" + SHARETYPE + "',RECVERSION=(RECVERSION+1),PREPAY_CUTPAY_TYPE='" + PREPAY_CUTPAY_TYPE + "'" +
                        ",COMMISSIONTYPE='" + COMMISSIONTYPE + "'" +
                        ",APPNO='" + XFAPPNO + "'" +
                        " where appno= '" + APPNO + "'";
                /*CCDIVIDAMT  CCRPNOWAMT
                * */
                sql[1] = "update xfcreditinfobatch set CCVALIDPERIOD ='" + CCVALIDPERIOD + "',CCDYNUM=" + CCDYNUM + ",CCXYNUM=" + CCXYNUM + "," +
                        "CCCDNUM=" + CCCDNUM + ",CCDYAMT=" + CCDYAMT + ",CCXYAMT=" + CCXYAMT + ",CCCDAMT=" + CCCDAMT + ",CCDYNOWBAL=" + CCDYNOWBAL + "," +
                        "CCXYNOWBAL=" + CCXYNOWBAL + ",CCCDNOWBAL=" + CCCDNOWBAL + ",CCDYRPMON=" + CCDYRPMON + ",CCXYRPMON=" + CCXYRPMON + "," +
                        "CCCDRPMON=" + CCCDRPMON + ",CCDYYEARRPTIME=" + CCDYYEARRPTIME + ",CCXYYEARRPTIME=" + CCXYYEARRPTIME + ",CCCDYEARRPTIME=" + CCCDYEARRPTIME +"," +
                        "CCDYNOOVERDUE='" + CCDYNOOVERDUE + "',CCXYNOOVERDUE='" + CCXYNOOVERDUE + "',CCCDNOOVERDUE='" + CCCDNOOVERDUE + "'," +
                        "CCDY1TIMEOVERDUE=" + CCDY1TIMEOVERDUE + ",CCXY1TIMEOVERDUE=" + CCXY1TIMEOVERDUE + ",CCCD1TIMEOVERDUE=" + CCCD1TIMEOVERDUE + "," +
                        "CCDY2TIMEOVERDUE=" + CCDY2TIMEOVERDUE +"," + "CCXY2TIMEOVERDUE=" + CCXY2TIMEOVERDUE +"," + "CCCD2TIMEOVERDUE=" + CCCD2TIMEOVERDUE +"," +
                        "CCDYM3TIMEOVERDUE=" + CCDYM3TIMEOVERDUE +"," + "CCXY3TIMEOVERDUE=" + CCXY3TIMEOVERDUE +"," + "CCCD3TIMEOVERDUE=" + CCCD3TIMEOVERDUE +"," +
                        "ACAGE='" + ACAGE + "',ACWAGE='" + ACWAGE + "',ACZX1='" + ACZX1 + "'," + "ACZX2='" + ACZX2 + "',ACZX3='" + ACZX3 + "'," +
                        "ACFACILITY='" + ACFACILITY + "'," + "ACRATE='" + ACRATE + "'," + "CCRPTOTALAMT=" + CCRPTOTALAMT + "," +
                        "CCRPNOWAMT=" + MONPAYAMT + ",CCDIVIDAMT=" + CCDIVIDAMT +
                        ",APPNO='" + XFAPPNO + "'" +
                        " where appno= '" + APPNO + "'";
                sql[2] = "update XFAPPADD set idtype='" + IDTYPE + "',id='" + ID + "',actopeningbank='" + ACTOPENINGBANK + "',bankactno='" + BANKACTNO + "'," +
                        "xy='" + XY + "',xyr='" + XYR + "',dy='" + DY + "',dyw='" + DYW + "',zy='" + ZY + "',zyw='" + ZYW + "',bz='" + BZ + "'," +
                        "bzr='" + BZR + "',credittype='" + CREDITTYPE + "',monpayamt='" + MONPAYAMT + "',linkman='" + LINKMAN + "'," +
                        "linkmangender='" + LINKMANGENDER + "',linkmanphone1='" + LINKMANPHONE1 + "',linkmanphone2='" + LINKMANPHONE2 + "'," +
                        "apprelation='" + APPRELATION + "',LINKMANADD='" + LINKMANADD + "',LINKMANCOMPANY='" + LINKMANCOMPANY + "'," +
                        "ACTOPENINGBANK_UD='" + ACTOPENINGBANK_UD + "'" +
                        ",APPNO='" + XFAPPNO + "'" +
                        " where appno= '" + APPNO + "'";
                sql[3] = "update XFCLIENT set LASTMODIFIED=SYSDATE,BIRTHDAY=to_date('" + BIRTHDAY + "','YYYY-MM-DD')," +
                        "GENDER='" + GENDER + "',NATIONALITY='" + NATIONALITY + "',MARRIAGESTATUS='" + MARRIAGESTATUS + "'," +
                        "HUKOUADDRESS='" + HUKOUADDRESS + "',CURRENTADDRESS='" + CURRENTADDRESS + "',COMPANY='" + COMPANY + "'," +
                        "TITLE='" + TITLE + "',QUALIFICATION='" + QUALIFICATION + "',EDULEVEL='" + EDULEVEL + "',PHONE1='" + PHONE1 + "'," +
                        "PHONE2='" + PHONE2 + "',PHONE3='" + PHONE3 + "',CLIENTNO='" + CLIENTNO + "',NAME='" + NAME + "',IDTYPE='" + IDTYPE + "'," +
                        "ID='" + ID + "',CLIENTTYPE='" + CLIENTTYPE + "',DEGREETYPE='" + DEGREETYPE + "',COMADDR='" + COMADDR + "'," +
                        "SERVFROM=" + SERVFROM + ",RESIDENCEADR='" + RESIDENCEADR + "',HOUSINGSTS='" + HOUSINGSTS + "'," +
                        "HEALTHSTATUS='" + HEALTHSTATUS + "',MONTHLYPAY='" + MONTHLYPAY + "',BURDENSTATUS='" + BURDENSTATUS + "'," +
                        "EMPNO='" + EMPNO + "',SOCIALSECURITY='" + SOCIALSECURITY + "',LIVEFROM=" + LIVEFROM + ",PC='" + PC + "'," +
                        "COMPC='" + COMPC + "',RESDPC='" + RESDPC + "',RESDADDR='" + RESDADDR + "',EMAIL='" + EMAIL + "'," +
                        "actopeningbank='" + ACTOPENINGBANK + "',bankactno='" + BANKACTNO + "',SLRYEVETYPE='" + SLRYEVETYPE + "'" +
                        /*SERVFROMMONTH,LIVEFROMMONTH  PSERVFROMMONTH  PLIVEFROMMONTH*/
                        ",SERVFROMMONTH=" + SERVFROMMONTH + ",LIVEFROMMONTH=" + LIVEFROMMONTH +
                        ",APPNO='" + XFAPPNO + "'" +
                        " where APPNO='" + APPNO + "' and XFCLTP='1' ";
                if (!PID.equals("")) {
                    sql11 = "select * from XFCLIENT where APPNO='" + APPNO + "' and XFCLTP='2'";
                    crs = manager.getRs(sql11);
                    if (crs.next()) {
                        sql[4] = "update XFCLIENT set LASTMODIFIED=SYSDATE, MARRIAGESTATUS='" + MARRIAGESTATUS + "', NAME='" + PNAME + "',IDTYPE='" + PIDTYPE + "'," +
                                "ID='" + PID + "',COMPANY='" + PCOMPANY + "',TITLE='" + PTITLE + "',PHONE1='" + PPHONE1 + "',PHONE3='" + PPHONE3 + "'," +
                                "CLIENTTYPE='" + PCLIENTTYPE + "',SERVFROM=" + PSERVFROM + ",MONTHLYPAY='" + PMONTHLYPAY + "',LIVEFROM=" + PLIVEFROM + "" +
                                ",SERVFROMMONTH=" + PSERVFROMMONTH + ",LIVEFROMMONTH=" + PLIVEFROMMONTH +
                                ",APPNO='" + XFAPPNO + "'" +
                                " where APPNO='" + APPNO + "' and XFCLTP='2' ";
                    } else
                        sql[4] = "insert into XFCLIENT (APPNO,XFCLTP,LASTMODIFIED,APPDATE,MARRIAGESTATUS,NAME,IDTYPE,ID,COMPANY," +
                                "TITLE,PHONE1,PHONE3,CLIENTTYPE,SERVFROM,MONTHLYPAY,LIVEFROM,SERVFROMMONTH,LIVEFROMMONTH) " +
                                "values ('" + XFAPPNO + "',2,SYSDATE," + APPDATE + ",'" + MARRIAGESTATUS + "','" + PNAME + "','" + PIDTYPE +
                                "','" + PID + "','" + PCOMPANY + "','" + PTITLE + "','" + PPHONE1 + "','" + PPHONE3 + "','" + PCLIENTTYPE +
                                "'," + PSERVFROM + ",'" + PMONTHLYPAY + "'," + PLIVEFROM + "," +
                                PSERVFROMMONTH + "," + PLIVEFROMMONTH + ")";
                } else {
                    sql[4] = "delete from XFCLIENT where APPNO='" + APPNO + "' and XFCLTP='2' ";
                }
                OperatorManager opm = (OperatorManager) session.getAttribute(SystemAttributeNames.USER_INFO_NAME);
                sql[5] = " update XFAPPREPAYMENT set ACTOPENINGBANK='" + ACTOPENINGBANK + "',BANKACTNO='" + BANKACTNO + "'," +
                        "ACTOPENINGBANK_UD='" + ACTOPENINGBANK_UD + "',BANKACTNAME='" + BANKACTNAME + "',CHANNEL='" + UNIONCHANNEL + "'," +
                        "OPERID='" + opm.getOperatorId() + "',OPERDATE=SYSDATE,PROVINCE='" + PROVINCE + "',CITY='" + CITY + "'" +
                        ",APPNO='" + XFAPPNO + "'" +
                        " where APPNO='" + APPNO + "'";
            }
            /*
             * ������Ʒ�� ��ɾ���*/
            String COMMNO = "";
            String COMMNMTYPE = "";
            String NUM = "";
            String AMT = "";
            String chRECEIVEAMT = "";
            String vaAPPTYPE = "";
//            String request.getParameter("BZ");
            sql[6] = "delete from xfappcommbatch where APPNO='" + APPNO + "'";
            for (int i = 0; i < commCnt; i++) {
                COMMNMTYPE = COMMNMTYPEARY[i].trim();
                if (COMMNMTYPE != null && !StringUtils.isEmpty(COMMNMTYPE)) {
                    COMMNO = COMMNOARY[i].trim();
                    NUM = NUMARY[i].trim();
                    AMT = AMTARY[i].trim();
                    chRECEIVEAMT = RECEIVEAMTARY[i].trim();
                    String strAPPTYPE = "APPTYPE" + (i+1);
                    vaAPPTYPE = request.getParameter(strAPPTYPE);
                    sql[(7 + i)] = "insert into xfappcommbatch (appno, commnmtype, num, amt, receiveamt, recversion, commno,apptype)" +
                            " values('" + XFAPPNO + "','" + COMMNMTYPE + "'," + NUM + "," + AMT + "," + chRECEIVEAMT + ",0,'" + COMMNO + "','" + vaAPPTYPE + "')";
                }
            }
            /*
            * SOCIALSECURITYID,ct.ANNUALINCOME,ct.ETPSCOPTYPE*/
            sql[7 + commCnt] = "delete from CMINDVCLIENT  where IDTYPE='" + IDTYPE + "' and ID='" + ID + "'";
            sql[8 + commCnt] = "insert into CMINDVCLIENT (LASTMODIFIED,BIRTHDAY,GENDER,NATIONALITY,MARRIAGESTATUS,HUKOUADDRESS," +
                    "CURRENTADDRESS,COMPANY,TITLE,QUALIFICATION,EDULEVEL,PHONE1,PHONE2,PHONE3,NAME,CLIENTTYPE,DEGREETYPE," +
                    "COMADDR,SERVFROM,RESIDENCEADR,HOUSINGSTS,HEALTHSTATUS,MONTHLYPAY,BURDENSTATUS,SOCIALSECURITY,LIVEFROM," +
                    "PC,COMPC,RESDPC,RESDADDR,EMAIL,actopeningbank,bankactno,SOCIALSECURITYID,ANNUALINCOME,ETPSCOPTYPE,IDTYPE,ID,APPDATE) " +
                    "values(SYSDATE,to_date('" + BIRTHDAY + "','YYYY-MM-DD'),'" + GENDER + "','" + NATIONALITY + "','" + MARRIAGESTATUS + "'," +
                    "'" + HUKOUADDRESS + "','" + CURRENTADDRESS + "','" + COMPANY + "','" + TITLE + "','" + QUALIFICATION + "','" + EDULEVEL + "'," +
                    "'" + PHONE1 + "','" + PHONE2 + "','" + PHONE3 + "','" + NAME + "','" + CLIENTTYPE + "','" + DEGREETYPE + "'," +
                    "'" + COMADDR + "','" + SERVFROM + "','" + RESIDENCEADR + "','" + HOUSINGSTS + "','" + HEALTHSTATUS + "'," +
                    "'" + MONTHLYPAY + "','" + BURDENSTATUS + "','" + SOCIALSECURITY + "','" + LIVEFROM + "','" + PC + "'," +
                    "'" + COMPC + "','" + RESDPC + "','" + RESDADDR + "','" + EMAIL + "','" + ACTOPENINGBANK + "','" + BANKACTNO + "'," +
                    "'" + SOCIALSECURITYID + "'," + ANNUALINCOME + ",'" + ETPSCOPTYPE + "','" + IDTYPE + "','" + ID + "',SYSDATE)";
            for (int sqli = 0; sqli < sql.length; sqli++) {
                System.out.println(sql[sqli]);
            }
            temp = manager.execBatch(sql);
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("application_start.jsp?sendflag=3&appno=" + XFAPPNO);
        }
        if (temp) {
            response.sendRedirect("application_start.jsp?sendflag=2&appno=" + XFAPPNO);
        }
    }
%>