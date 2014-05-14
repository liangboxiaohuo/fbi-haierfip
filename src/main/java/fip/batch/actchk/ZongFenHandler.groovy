package fip.batch.actchk

import fip.common.utils.sms.SmsTool
import fip.gateway.JmsManager
import groovy.sql.Sql
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import pub.platform.advance.utils.PropertyManager

/**
 * �ܷ��˻�����.
 * User: zhanrui
 * Date: 12-7-26
 * Time: ����5:06
 * To change this template use File | Settings | File Templates.
 */
class ZongFenHandler {
    private static final Logger logger = LoggerFactory.getLogger(ZongFenHandler.class)

    //String txn_date = "19991231"
    String txn_date = "20130506"
    def dbparam
    def db
    def eventId = "ZF001001"
    def ftpmain = "D:/ftpmain/mbp"

    static void main(args) {

        ZongFenHandler handler = new ZongFenHandler()
        handler.processCCBMsg_test()
        //handler.startActChk(false)
        //handler.notifyResult()
    }

    //WEB����ýӿ�
    public void startActChk4Web(String yyyymmdd) {
        if (!isCronTaskOpen()) {
            logger.info("�Զ������������ѹرա�");
            return;
        }

        this.txn_date = yyyymmdd;
        startActChk(false)
    }
    //��ʱ������ýӿ�
    public void startActChk4Cron() {
        if (!isCronTaskOpen()) {
            logger.info("�Զ������������ѹرա�");
            return;
        }

        txn_date = (new Date() - 1).format('yyyyMMdd');
        startActChk(true)
    }

    // isCheckSuccessFlag  true:���ж���ǰ�ȼ���ѳɹ���־ false:���ж���ǰ������ѳɹ���־
    private void startActChk(boolean isCheckSuccessFlag) {
        //1.��ʼ������
        try {
            println "=============" + txn_date
            initDB()
        } catch (Exception e) {
            logger.error("����ʱ����ϵͳ����", e)
            return
        }

        try {
            def db_txn_date = db.firstRow("select txn_date  from EVT_MAININFO where evt_id = ${this.eventId}").txn_date
            def succ_flag = db.firstRow("select succ_flag  from EVT_MAININFO where evt_id = ${this.eventId}").succ_flag
            if (db_txn_date == txn_date) {
                if (isCheckSuccessFlag) {
                    if (succ_flag == '1') { //�Ѳ����ɹ���������ȡ���ݺ�У�����ݣ�
                        logger.info("�ܷ��˻������ѳɹ���")
                        return
                    }
                }
                db.execute("delete from chk_zongfen_txn where txn_date = ${txn_date}")
            } else {
                updateOperationEventMainInfo(this.eventId, false, "��ʼ����", "��ʼ����")
                db.execute("update EVT_MAININFO set evt_msg_code= '1000' where evt_id = ${eventId}")
                db.execute("delete from chk_zongfen_txn where txn_date = ${txn_date}")
            }
        } catch (Exception e) {
            logger.error("����ʱ����ϵͳ����", e)
            return
        }

        def start = System.currentTimeMillis()

        //2.��ȡ���ж�������
        try {
            processCCBMsg()
        } catch (Exception e) {
            logger.error("������ش������", e)
            def msg = "�ܷ��˻������쳣, ҵ������:${txn_date}, ������Ϣ:" + e.getMessage()
            msg = msg.size() <= 70 ? msg : msg.substring(0, 70)
            updateOperationEventMainInfo(this.eventId, false, msg, msg)
            return
        }

        //3.��ȡSBS��������
        try {
            processSBSMsg()
        } catch (Exception sbsex) {
            logger.error("��ȡSBS�������ݴ���", sbsex)
            def msg = "�ܷ��˻������쳣, ҵ������:${txn_date}, ������Ϣ:" + sbsex.getMessage()
            msg = msg.size() <= 70 ? msg : msg.substring(0, 70)
            updateOperationEventMainInfo(this.eventId, false, msg, msg)
            return
        }

        updateOperationEventMainInfo(this.eventId, true, "��ȡ�����������.", "��ȡ�����������.")

        //4.��ʼУ��
        verifyData()

        def end = System.currentTimeMillis()

        println "---end---" + (end - start) / 1000
//        db.close()
    }

    public void verifyData() {
        //�鿴�¼�����״̬��
        def succ_flag = db.firstRow("select succ_flag  from EVT_MAININFO where evt_id = ${this.eventId}").succ_flag
        if (succ_flag == '0') {  //����ʧ��
            return
        }

        //У����˽��
        verify(txn_date, "SBS", "CCB")

        def ccbCount = db.firstRow("select count(*) as cnt from CHK_ZONGFEN_TXN where txn_date = ${this.txn_date} and send_sys_id = 'CCB'").cnt
        def sbsCount = db.firstRow("select count(*) as cnt from CHK_ZONGFEN_TXN where txn_date = ${this.txn_date} and send_sys_id = 'SBS'").cnt
        def totalCount = db.firstRow("select count(*) as cnt from CHK_ZONGFEN_TXN where txn_date = ${this.txn_date}").cnt
        def totalFailCount = db.firstRow("select count(*) as cnt from CHK_ZONGFEN_TXN where txn_date = ${this.txn_date} and (chksts is null or chksts != '0')").cnt

        if (totalFailCount == 0) {
            def msg = "�ܷ��˻����˽��:ƽ��, ҵ������:${txn_date}, CCB:${ccbCount}��,SBS:${sbsCount}��."
            updateTxnEventMainInfo(this.eventId, "0000", msg, msg)
        } else {
            def errmsg = "�ܷ��˻����˽��:��ƽ, ҵ������:${txn_date}, CCB:${ccbCount}��,SBS:${sbsCount}��, ��ƽ����:${totalFailCount}��."
            updateTxnEventMainInfo(this.eventId, "1000", errmsg, errmsg)
        }
    }

    //=============
    //WEB����ýӿ�
    public void notifyResult4Web(String yyyymmdd) {
        if (!isCronTaskOpen()) {
            logger.info("�Զ������������ѹرա�");
            return;
        }

        this.txn_date = yyyymmdd;
        notifyResult()
    }
    //��ʱ������ýӿ�
    public void notifyResult4Cron() {
        if (!isCronTaskOpen()) {
            logger.info("�Զ������������ѹرա�");
            return;
        }

        txn_date = (new Date() - 1).format('yyyyMMdd');
        notifyResult()
    }

    private void notifyResult() {
        initDB()
        //�鿴�¼�����״̬��
        notifyInfo()
    }

    //================================================================================================
    private void initDB() {
        try {
            def dbdrv = PropertyManager.getProperty("pub.platform.db.ConnectionManager.sDBDriver")
            def dburl = PropertyManager.getProperty("pub.platform.db.ConnectionManager.sConnStr")
            def dbuser = PropertyManager.getProperty("pub.platform.db.ConnectionManager.user")
            def dbpwd = PropertyManager.getProperty("pub.platform.db.ConnectionManager.passwd")

            dbparam = [url: dburl, user: dbuser, password: dbpwd, driver: dbdrv]
            db = Sql.newInstance(dbparam.url, dbparam.user, dbparam.password, dbparam.driver)
        } catch (Exception e) {
            logger.error("���ݿ����Ӵ���", e)
            throw new RuntimeException("���ݿ����Ӵ���" + e.getMessage(), e)
        }
    }

    //���²����¼���Ϣ
    private void updateOperationEventMainInfo(String evt_id, boolean isSuccess, String sms_info, String mail_info) {
        def evt_date = new Date().format('yyyyMMdd')
        def evt_time = new Date().format('HH:mm:ss')
        def evt_succ_flag
        if (isSuccess) evt_succ_flag = "1"
        else evt_succ_flag = "0"
        db.execute("""update EVT_MAININFO set
                        evt_date = ${evt_date},
                        evt_time = ${evt_time},
                        txn_date = ${this.txn_date},
                        txn_time = '00:00:00',
                        succ_flag = ${evt_succ_flag},
                        succ_count = 0,
                        fail_count = 0,
                        sms_info=${sms_info},
                        mail_info=${mail_info}
                        where evt_id = ${evt_id}""")
    }
    //����ҵ���¼���Ϣ
    private void updateTxnEventMainInfo(String evt_id, String evt_msg_code, String sms_info, String mail_info) {
        db.execute("""update EVT_MAININFO set
                        evt_msg_code = ${evt_msg_code},
                        sms_info=${sms_info},
                        mail_info=${mail_info}
                        where evt_id = ${evt_id}""")
    }


    private void notifyInfo() {
        def row = db.firstRow("select * from EVT_MAININFO where evt_id = ${this.eventId}")
        def smsList = row.sms_list
        def smsInfo = row.sms_info

        boolean isTxnSucc = false
        if (row.evt_msg_code == '0000') {
            isTxnSucc = true
        }
        switch (row.notify_type) {
            case "1":  //ʧ��ʱ֪ͨ
                if (!isTxnSucc) {
                    notifySms(smsList, smsInfo)
                }
                break
            case "2":  //�ɹ�ʱ֪ͨ
                if (isTxnSucc) {
                    notifySms(smsList, smsInfo)
                }
                break
            case "3":  //ȫ��֪ͨ
                notifySms(smsList, smsInfo)
                break
            case "0":  //��֪ͨ
            default:
                break
        }
    }

    private void notifySms(String receivers, String msg) {
        try {
            receivers.split(";").each {
                SmsTool.sendMessage(it, msg)
            }
        } catch (Exception e) {
            db.execute("update EVT_MAININFO set sms_err_msg = '�뺣��������������ʧ��.' where evt_id = ${eventId}")
            logger.error("�뺣��������������ʧ��.", e)
        }

    }


    def processCCBMsg() {
        def file
        def Root
        try {
            file = new File(ftpmain + "/${txn_date}.xml")
        } catch (Exception e) {
            def errmsg = "���ж����ļ�:${txn_date}.xml�����ڡ�"
            throw new RuntimeException(errmsg, e)
        }

        //20130819 zr
        println("====" + file.length())
        boolean isNullFile = false;
        if (file.length() < 100) {
            file.eachLine {
                if (it.contains("MADE BY MBP")) {  //���ļ� ��ʾ���з����Ķ����ļ�Ϊ��
                    logger.info("���з����Ķ����ļ�Ϊ��.")
                    isNullFile = true
                }
            }
        }
        if (isNullFile) return;

        //20130507 zr  �����ļ��� ���ֺ��ִ���������
        StringBuffer sb = new StringBuffer();
        file.eachLine {
            if (!it.contains("AbstractStr")) {
                sb.append(it)
            }
        }

        try {
            Root = new XmlSlurper().parseText(sb.toString())
        } catch (Exception e) {
            def errmsg = "���������ж����ļ�:${txn_date}.xmlʱ�����쳣��"
            throw new RuntimeException(errmsg, e)
        }

        db.withBatch(500, """insert into chk_zongfen_txn
                (pkid, txn_date, send_sys_id, actno_in, actno_out, txnamt, dc_flag, msg_sn, chksts)
                values
                (:pkid, :txn_date, :send_sys_id, :actno_in,:actno_out, :txnamt, :dc_flag, :msg_sn, :chksts) """) { ps ->
            Root.Body.Record.each() {
                BigDecimal amt = new BigDecimal(((String) it.TxAmount));
                //String txdate = it.TxDate
                String txdate = this.txn_date
                String dcflag = it.DCFlag
                String msgsn = it.BankVoucherId
                //20121029 �˺�Ϊ��ʱĬ��Ϊ 40����0'
                String outAcctId = it.OutAcctId
                outAcctId = outAcctId.trim() ?: '00000000000000000000000000000000'
                String memo = it.Memo
                memo = memo.trim() ?: '00000000000000000000000000000000'
                if (dcflag == "C") {
                    ps.addBatch(pkid: UUID.randomUUID().toString(),
                            txn_date: txdate,
                            send_sys_id: "CCB",
                            actno_in: memo,
                            actno_out: outAcctId,
                            txnamt: amt,
                            dc_flag: dcflag,
                            msg_sn: msgsn.trim(),
                            chksts: "-")
                }
            }
        }
    }

    void processSBSMsg() {
        def lines = []

        processOneSbsMsg(lines, 1)

        db.withBatch(500, """insert into chk_zongfen_txn
                (pkid, txn_date, send_sys_id, actno_in, actno_out, txnamt, dc_flag, msg_sn, chksts)
                values
                (:pkid, :txn_date, :send_sys_id, :actno_in,:actno_out, :txnamt, :dc_flag, :msg_sn, :chksts) """) { ps ->

            lines.each {
                println it
                def fields = it.split('\\|')

                BigDecimal amt = new BigDecimal((String) fields[4].trim());
                //String txdate = it.TxDate
                String txdate = this.txn_date
                String dcflag = fields[3].trim()
                String msgsn = fields[5].trim()
                String inAcctId = fields[1].trim()
                String outAcctId = fields[8].trim()
                ps.addBatch(pkid: UUID.randomUUID().toString(),
                        txn_date: txdate,
                        send_sys_id: "SBS",
                        actno_in: inAcctId,
                        actno_out: outAcctId,
                        txnamt: amt,
                        dc_flag: dcflag,
                        msg_sn: msgsn,
                        chksts: "-")

            }
        }
    }

    void processOneSbsMsg(lines, int startNum) {
        int iCommareaSize = 32000;
        byte[] tiabuf = new byte[iCommareaSize];

        String buff = ""
        String txncode = "n117"
        buff = "TPEI" + txncode + "  010       MT01MT01" //��ͷ���ݶ���51���ַ�
        System.arraycopy(buff.getBytes(), 0, tiabuf, 0, buff.length()) //�����ͷ

        def tiaFieldsList = [txn_date, startNum.toString().padLeft(6, '0')]
        setBufferValues(tiaFieldsList, tiabuf)
        logger.info("===TIA:" + new String(tiabuf))

        byte[] toabuf = []
        int bufp = 0
        def instance = null
        try {
            instance = JmsManager.getInstance()
            toabuf = instance.sendAndRecvForDepCoreInterface(tiabuf)
        } catch (Exception e) {
            logger.error("MQ�����쳣�����Ӵ����ʱ��", e)
            throw new RuntimeException("DEP���ݽ���ƽ̨�ӿ��쳣" + e.getMessage(), e)
        } finally {
            instance = null
        }

        logger.info("===TOA:" + new String(toabuf))

        short msgLen = byteToShort(toabuf[28], toabuf[27])


        int totcnt = 0, curcnt = 0

        bufp = 29 //SOF-DATA-LEN	������	X(2)
        if (msgLen > 0) {
            //��鷵�ؽ�����
            txncode = new String((byte[]) toabuf[21..24])
            if (txncode != 'T821') {
                def errmsg = "SBS�����쳣���ģ�${txncode} " + new String((byte[]) toabuf[29..29 + msgLen - 1]).trim()
                if (txncode == 'M104') { //����������
                    logger.info(errmsg)
                    return
                }
                throw new RuntimeException(errmsg)
            }

            byte[] b1 = toabuf[bufp..bufp + 5]
            bufp += 6
            byte[] b2 = toabuf[bufp..bufp + 5]
            bufp += 6
            totcnt = new String(b1).toInteger()
            curcnt = new String(b2).toInteger()
            println "===TOA:   ${totcnt}  ${curcnt}"

            curcnt.times {
                short recordlen = byteToShort(toabuf[bufp + 1], toabuf[bufp])
                bufp += 2
                byte[] recordbuf = toabuf[bufp..bufp + recordlen - 1]
                def record = new String(recordbuf)
                lines.add(record)
                bufp += recordlen
            }

            int recvcnt = startNum - 1
            recvcnt += curcnt
            if (recvcnt < totcnt) {
                processOneSbsMsg(lines, recvcnt + 1)
            }
        }
    }

    //У��������ݣ����ñ�־
    private void verify(txnDate, sendSysId1, sendSysId2) {
        //˫���ʻ������� �˶� ƽ��
        def sql = """
                  update CHK_ZONGFEN_TXN
                    set chksts = '0'
                  where txn_date = ${txnDate}
                    and msg_sn in (select t1.msg_sn
                                    from (select *
                                            from CHK_ZONGFEN_TXN
                                           where send_sys_id = ${sendSysId1}
                                             and txn_date = ${txnDate}) t1,
                                         (select *
                                            from CHK_ZONGFEN_TXN
                                           where send_sys_id = ${sendSysId2}
                                             and txn_date = ${txnDate}) t2
                                   where t1.msg_sn = t2.msg_sn
                                     and t1.actno_in = t2.actno_in
                                     and t1.actno_out = t2.actno_out
                                     and t1.txnamt = t2.txnamt
                                     and t1.dc_flag = t2.dc_flag)

        """
        db.execute(sql)

        //�˶���������
        sql = """
              update CHK_ZONGFEN_TXN
                set chksts = '1'
              where txn_date = ${txnDate}
                and send_sys_id = ${sendSysId1}
                and msg_sn not in (select msg_sn
                                    from CHK_ZONGFEN_TXN
                                   where send_sys_id = ${sendSysId2}
                                     and txn_date = ${txnDate})
               """
        db.execute(sql)

        //�˶���������
        sql = """
              update CHK_ZONGFEN_TXN
                set chksts = '1'
              where txn_date = ${txnDate}
                and send_sys_id = ${sendSysId2}
                and msg_sn not in (select msg_sn
                                    from CHK_ZONGFEN_TXN
                                   where send_sys_id = ${sendSysId1}
                                     and txn_date = ${txnDate})
               """
        db.execute(sql)
        //�˶���������
        sql = """
              update CHK_ZONGFEN_TXN
                set chksts = '2'
              where txn_date = ${txnDate}
                and msg_sn in (select t1.msg_sn
                                from (select *
                                        from CHK_ZONGFEN_TXN
                                       where send_sys_id = ${sendSysId1}
                                         and txn_date = ${txnDate}) t1,
                                     (select *
                                        from CHK_ZONGFEN_TXN
                                       where send_sys_id = ${sendSysId2}
                                         and txn_date = ${txnDate}) t2
                               where t1.msg_sn = t2.msg_sn
                                 and (t1.txnamt != t2.txnamt
                                 or t1.actno_in != t2.actno_in
                                 or t1.actno_out != t2.actno_out
                                 or t1.dc_flag != t2.dc_flag))
               """
        db.execute(sql)

    }

    private short byteToShort(byte high, byte low) {
        byte[] msglenBuf = [high, low]
        short tmp1 = msglenBuf[0] & 0xFF
        short tmp2 = (msglenBuf[1] << 8) & 0xFF00
        short msgLen = tmp2 | tmp1
        msgLen
    }

    //java code  for sbs msg
    private void setBufferValues(List list, byte[] bb) throws UnsupportedEncodingException {
        int start = 51;
        for (int i = 1; i <= list.size(); i++) {
            String value = list.get(i - 1).toString();
            setVarData(start, value, bb);
            start = start + value.getBytes("GBK").length + 2;
        }
    }

    private void setVarData(int pos, String data, byte[] aa) throws UnsupportedEncodingException {
        short len = (short) data.getBytes("GBK").length;

        byte[] slen = new byte[2];
        slen[0] = (byte) (len >> 8);
        slen[1] = (byte) (len);
        System.arraycopy(slen, 0, aa, pos, 2);
        System.arraycopy(data.getBytes(), 0, aa, pos + 2, len);
    }

    //=============================================
    private boolean isCronTaskOpen() {
        try {
            String debug_mode = PropertyManager.getProperty("cron_task_mode");
            return debug_mode != null && !"".equals(debug_mode) && "open".equals(debug_mode);
        } catch (Exception e) {
            logger.error("��ȡ�����ļ�����", e);
            return false;
        }
    }

    //===================================================
    def processCCBMsg_test() {
        def file
        def Root
        try {
            file = new File(ftpmain + "/${txn_date}.xml")
        } catch (Exception e) {
            def errmsg = "���ж����ļ�:${txn_date}.xml�����ڡ�"
            throw new RuntimeException(errmsg, e)
        }

        //20130507 zr  �����ļ��� ���ֺ��ִ���������
        StringBuffer sb = new StringBuffer();
        file.eachLine {
            sb.append(it)
        }

        try {
            Root = new XmlSlurper().parseText(sb.toString())
        } catch (Exception e) {
            def errmsg = "���������ж����ļ�:${txn_date}.xmlʱ�����쳣��"
            throw new RuntimeException(errmsg, e)
        }

    }

}

