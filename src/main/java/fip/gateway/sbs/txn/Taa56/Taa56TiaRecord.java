package fip.gateway.sbs.txn.Taa56;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 11-6-14
 * Time: ����4:31
 * To change this template use File | Settings | File Templates.
 */
public class Taa56TiaRecord {
    /*
    CTF-ACTTY1	ת���ʻ�����	X(2)	�̶�ֵ	01
    CTF-IPTAC1	ת���ʻ�	X(22)	ͬҵ�˺�	22		801000026131041001
    CTF-DRAMD1	ȡ�ʽ	X(1)	�̶�ֵ	3
    CTF-ACTNM1	ת���ʻ�����	X(72)	�̶�ֵ	�ո�
    CTF-CUSPW1	ȡ������	X(6)	�̶�ֵ	�ո�

    CTF-PASTYP	֤������	X(1)	�̶�ֵ	N
    CTF-PASSNO	��Χϵͳ��ˮ��	X(18)	�̶�ֵ
    CTF-PAPTYP	֧Ʊ����	X(1)	�̶�ֵ	�ո�
    CTF-PAPCDE	֧Ʊ��	X(10)	�̶�ֵ	�ո�
    CTF-PAPMAC	֧Ʊ����	X(12)	�̶�ֵ	�ո�

    CTF-SGNDAT	ǩ������	X(8)	�̶�ֵ	�ո�
    CTF-NBKFL1	���۱�ʶ	X(1)	�̶�ֵ	3
    CTF-AUTSEQ	�����ֶ�	X(8)	�̶�ֵ	�ո�
    CTF-AUTDAT	�����ֶ�	X(4)	�̶�ֵ	�ո�
    CTF-TXNAMT	���׽��	S9(13).99	�Ҷ��룬��0				S��ʾ�����ţ�ռһλ

    CTF-ACTTY2	ת���ʻ�����	X(2)	�̶�ֵ	01
    CTF-IPTAC2	ת���ʻ�	X(22)	������Ҳ��ո�
    CTF-ACTNM2	ת���ʻ�����	X(72)	�̶�ֵ	�ո�
    CTF-NBKFL2	���۱�ʶ	X(1)	�̶�ֵ	�ո�
    CTF-TXNDAT	��������	X(8)	YYYYMMDD

    CTF-REMARK	ժҪ	X(30)	������Ҳ��ո�
    CTF-ANACDE	��Ʒ��	X(4)	�̶�ֵ	�ո�
    CTF-MAGFL1		X(1)	�̶�ֵ	�ո�
    CTF-MAGFL2		X(1)	�̶�ֵ	�ո�
    CTF-DEVTYP	��������	X(3)	�̶�ֵ	�ո�
     */
    private String  ACTTY1;
    private String  IPTAC1;
    private String  DRAMD1;
    private String  ACTNM1;
    private String  CUSPW1;

    private String  PASTYP;
    private String  PASSNO;
    private String  PAPTYP;
    private String  PAPCDE;
    private String  PAPMAC;

    private String  SGNDAT;
    private String  NBKFL1;
    private String  AUTSEQ;
    private String  AUTDAT;
    private String  TXNAMT;

    private String  ACTTY2;
    private String  IPTAC2;
    private String  ACTNM2;
    private String  NBKFL2;
    private String  TXNDAT;

    private String  REMARK;
    private String  ANACDE;
    private String  MAGFL1;
    private String  MAGFL2;
    private String  DEVTYP;


    public String getACTTY1() {
        return ACTTY1;
    }

    public void setACTTY1(String ACTTY1) {
        this.ACTTY1 = ACTTY1;
    }

    public String getIPTAC1() {
        return IPTAC1;
    }

    public void setIPTAC1(String IPTAC1) {
        this.IPTAC1 = IPTAC1;
    }

    public String getDRAMD1() {
        return DRAMD1;
    }

    public void setDRAMD1(String DRAMD1) {
        this.DRAMD1 = DRAMD1;
    }

    public String getACTNM1() {
        return ACTNM1;
    }

    public void setACTNM1(String ACTNM1) {
        this.ACTNM1 = ACTNM1;
    }

    public String getCUSPW1() {
        return CUSPW1;
    }

    public void setCUSPW1(String CUSPW1) {
        this.CUSPW1 = CUSPW1;
    }

    public String getPASTYP() {
        return PASTYP;
    }

    public void setPASTYP(String PASTYP) {
        this.PASTYP = PASTYP;
    }

    public String getPASSNO() {
        return PASSNO;
    }

    public void setPASSNO(String PASSNO) {
        this.PASSNO = PASSNO;
    }

    public String getPAPTYP() {
        return PAPTYP;
    }

    public void setPAPTYP(String PAPTYP) {
        this.PAPTYP = PAPTYP;
    }

    public String getPAPCDE() {
        return PAPCDE;
    }

    public void setPAPCDE(String PAPCDE) {
        this.PAPCDE = PAPCDE;
    }

    public String getPAPMAC() {
        return PAPMAC;
    }

    public void setPAPMAC(String PAPMAC) {
        this.PAPMAC = PAPMAC;
    }

    public String getSGNDAT() {
        return SGNDAT;
    }

    public void setSGNDAT(String SGNDAT) {
        this.SGNDAT = SGNDAT;
    }

    public String getNBKFL1() {
        return NBKFL1;
    }

    public void setNBKFL1(String NBKFL1) {
        this.NBKFL1 = NBKFL1;
    }

    public String getAUTSEQ() {
        return AUTSEQ;
    }

    public void setAUTSEQ(String AUTSEQ) {
        this.AUTSEQ = AUTSEQ;
    }

    public String getAUTDAT() {
        return AUTDAT;
    }

    public void setAUTDAT(String AUTDAT) {
        this.AUTDAT = AUTDAT;
    }

    public String getTXNAMT() {
        return TXNAMT;
    }

    public void setTXNAMT(String TXNAMT) {
        this.TXNAMT = TXNAMT;
    }

    public String getACTTY2() {
        return ACTTY2;
    }

    public void setACTTY2(String ACTTY2) {
        this.ACTTY2 = ACTTY2;
    }

    public String getIPTAC2() {
        return IPTAC2;
    }

    public void setIPTAC2(String IPTAC2) {
        this.IPTAC2 = IPTAC2;
    }

    public String getACTNM2() {
        return ACTNM2;
    }

    public void setACTNM2(String ACTNM2) {
        this.ACTNM2 = ACTNM2;
    }

    public String getNBKFL2() {
        return NBKFL2;
    }

    public void setNBKFL2(String NBKFL2) {
        this.NBKFL2 = NBKFL2;
    }

    public String getTXNDAT() {
        return TXNDAT;
    }

    public void setTXNDAT(String TXNDAT) {
        this.TXNDAT = TXNDAT;
    }

    public String getREMARK() {
        return REMARK;
    }

    public void setREMARK(String REMARK) {
        this.REMARK = REMARK;
    }

    public String getANACDE() {
        return ANACDE;
    }

    public void setANACDE(String ANACDE) {
        this.ANACDE = ANACDE;
    }

    public String getMAGFL1() {
        return MAGFL1;
    }

    public void setMAGFL1(String MAGFL1) {
        this.MAGFL1 = MAGFL1;
    }

    public String getMAGFL2() {
        return MAGFL2;
    }

    public void setMAGFL2(String MAGFL2) {
        this.MAGFL2 = MAGFL2;
    }

    public String getDEVTYP() {
        return DEVTYP;
    }

    public void setDEVTYP(String DEVTYP) {
        this.DEVTYP = DEVTYP;
    }
}
