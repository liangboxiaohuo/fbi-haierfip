package fip.gateway.sbs.txn.Ta543;

/**
 * ���ۻ���ӿ�.
 * User: zhanrui
 * Date: 11-6-14
 * Time: ����4:31
 * To change this template use File | Settings | File Templates.
 */
public class Ta543TiaRecord {
    /*
        CTF-TXNDAT	��������	X(8)	�̶�ֵ
        CTF-PASSNO	��Χϵͳ��ˮ��	X(18)
        CTF-IPTAC1	�����˺�	X(22)	����룬����ʱ�Ҳ��ո�
        CTF-TXNAMT	������	S9(13).99	�Ҷ��룬����ʱ����
        CTF-ADVAMT	ΥԼ�����Ϣ/Ӧ��Ϣ��	S9(13).99	�Ҷ��룬����ʱ����

        CTF-STNAMT	���ɽ����Ϣ��	S9(13).99	�Ҷ��룬����ʱ����
        CTF-DEB-LIMNUM	�����ѽ�������	S9(13).99	�Ҷ��룬����ʱ����
        CTF-REMARK	ժҪ	X(30)	������Ҳ��ո�
        CTF-IPTAC2	���𻹿��˺�	X(22)	���뻹������˺Ż�����ͬҵ�˺�
        CTF-DPTTYP	��������	X(2)	������Ҳ��ո�	"01�Թ��Ƿ��ز�����
                                                    02�Թ����ز���������
                                                    03ת����
                                                    04��˽���Ѵ���
                                                    05��˽���Ҵ���
                                                    06ί�д���
                                                    07����
                                                    08�Ŵ��ʲ�ת��"
        CTF-DSUUSE-IPTACT	��Ϣ�����˺�	X(22)	���뻹������˺Ż�����ͬҵ�˺�
     */
    private String  TXNDAT;
    private String  PASSNO;
    private String  IPTAC1;
    private String  TXNAMT;
    private String  ADVAMT;

    private String  STNAMT;
    private String  DEBLIMNUM;
    private String  REMARK;
    private String  IPTAC2;
    private String  DPTTYP;

    private String  DSUUSEIPTACT;

    public String getTXNDAT() {
        return TXNDAT;
    }

    public void setTXNDAT(String TXNDAT) {
        this.TXNDAT = TXNDAT;
    }

    public String getPASSNO() {
        return PASSNO;
    }

    public void setPASSNO(String PASSNO) {
        this.PASSNO = PASSNO;
    }

    public String getIPTAC1() {
        return IPTAC1;
    }

    public void setIPTAC1(String IPTAC1) {
        this.IPTAC1 = IPTAC1;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getADVAMT() {
        return ADVAMT;
    }

    public void setADVAMT(String ADVAMT) {
        this.ADVAMT = ADVAMT;
    }

    public String getSTNAMT() {
        return STNAMT;
    }

    public void setSTNAMT(String STNAMT) {
        this.STNAMT = STNAMT;
    }

    public String getDEBLIMNUM() {
        return DEBLIMNUM;
    }

    public void setDEBLIMNUM(String DEBLIMNUM) {
        this.DEBLIMNUM = DEBLIMNUM;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getIPTAC2() {
        return IPTAC2;
    }

    public void setIPTAC2(String IPTAC2) {
        this.IPTAC2 = IPTAC2;
    }

    public String getDPTTYP() {
        return DPTTYP;
    }

    public void setDPTTYP(String DPTTYP) {
        this.DPTTYP = DPTTYP;
    }

    public String getDSUUSEIPTACT() {
        return DSUUSEIPTACT;
    }

    public void setDSUUSEIPTACT(String DSUUSEIPTACT) {
        this.DSUUSEIPTACT = DSUUSEIPTACT;
    }
}
