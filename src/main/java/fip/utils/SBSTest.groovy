package fip.utils
import com.ibm.ctg.client.ECIRequest
import com.ibm.ctg.client.JavaGateway

import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.atomic.AtomicInteger
/**
 * Created with IntelliJ IDEA.
 * User: zhanrui
 * Date: 12-12-26
 * Time: ����12:56
 * To change this template use File | Settings | File Templates.
 */
class SBSTest {
    def private static final AtomicInteger threadcount = new AtomicInteger(0)
    def private static final AtomicInteger failcount = new AtomicInteger(0)
    def private static final AtomicInteger txnerr = new AtomicInteger(0)
    def private static final AtomicInteger txnsucc = new AtomicInteger(0)
    def private static ConcurrentHashMap<Long, Long> elapsedMap = new ConcurrentHashMap<Long, Long>();

    def txn_date = "20121224"

    static void main(args) {
        int threadsNum = 10, repeats = 5, separateTime = 1

        SBSTest tester = new SBSTest()
        if (args.size() > 0) {
            tester.txn_date = args[0]
            threadsNum = args[1].toInteger()
            repeats = args[2].toInteger()
        }else{
            println "Usage: java -jar sbstest.jar txndate threads repeats"
            return
        }

        def suminfo = {
            def i = 0, total = 0
            elapsedMap.each {
                i++
                total += it.value
            }
            return "�ɹ����ף�${i}, ��ʱ��${Math.round(total / 1000)}��, ƽ��ÿ�ʺ�ʱ: ${Math.round(total / i)} ms"
        }

        def begin = System.currentTimeMillis();
        repeats.times {
            def threads = []
            threadsNum.times {
                threads.add(Thread.start { tester.process("n022", suminfo) })
                threads.add(Thread.start { tester.process("n024", suminfo) })
            }
            threads.each { it.join() }
            Thread.sleep(separateTime * 1000)
        }

        def end = System.currentTimeMillis();
        def time = Math.round((end - begin) / 1000)

        threadsNum = threadsNum * 2 * repeats;
        println("�ܽ�������${threadsNum}��, �ܺ�ʱ��${time}��, ÿ�뽻����(�ɹ�)��${(threadsNum - failcount) / time}")
        println("ϵͳʧ�ܽ�������${failcount.get()}��")
        println("����ҵ����ɹ�����${txnsucc.get()}��, ��ˮ�Ų�����������${txnerr.get()}��")

        println suminfo()
    }

    def process(String txn_code, suminfo) {
        int txn_sn = threadcount.incrementAndGet();
        def begin = System.currentTimeMillis();
        try {
            def  beginTime = new Date().format("HH:mm:ss:SSS")
            def rtnmsg = processOneSbsMsg(txn_code, txn_sn, "getTxn_${txn_code}_TIA"(txn_sn))
            def end = System.currentTimeMillis();
            elapsedMap.put(Thread.currentThread().getId(), end - begin);
            println "[${txn_code}:${end-begin}]${beginTime}->${rtnmsg}  ${suminfo()}"
        } catch (Exception e) {
            failcount.incrementAndGet();
            e.printStackTrace();
            println("�����쳣:" + e.stackTrace);
        }
    }

    def processOneSbsMsg(String txn_code, int txn_sn, List reqList) {
        def rtnmsg = ""
        int iCommareaSize = 32000;
        byte[] tiabuf = new byte[iCommareaSize];

        String buff = ""
        buff = "TPEI" + txn_code + "  010       MT01MT01" //��ͷ���ݶ���51���ַ�
        System.arraycopy(buff.getBytes(), 0, tiabuf, 0, buff.length()) //�����ͷ

        //�������
        setBufferValues(reqList, tiabuf)

        //logger.info("===TIA:" + new String(tiabuf))
        def toabuf = processTxn(tiabuf)

        //logger.info("===TOA:" + new String(toabuf))
        short msgLen = byteToShort(toabuf[28], toabuf[27])
        def formcode = new String((byte[]) toabuf[21..24])
        def req_txnsn = 'MPC' + ('' + txn_sn).padLeft(15, '0')
        if (msgLen > 0) {
            if (formcode == 'T543' || formcode == 'T531' || formcode == 'T999') {
                txnsucc.incrementAndGet()
                def txnsnOffset = getTxnsnOffset(txn_code);
                def FBTIDX = new String((byte[]) toabuf[txnsnOffset..(txnsnOffset + 17)]).trim()
                rtnmsg =  "[${formcode}]${new Date().format("HH:mm:ss:SSS")} ��ˮ��:${req_txnsn}. ��ˮ�Ų�ƥ�䣺${txnerr}��. ���߳�${threadcount},ʧ��:${failcount}"
                if (FBTIDX != req_txnsn) {
                    txnerr.incrementAndGet()
                }
            }else{
                rtnmsg =  "[${formcode}]${new Date().format("HH:mm:ss:SSS")} ������ˮ�ţ�${req_txnsn}"
            }
        }else{
            //rtnmsg = "���ر��ĳ���${msgLen}"
            rtnmsg =  "[${formcode}]${new Date().format("HH:mm:ss:SSS")} ������ˮ�ţ�${req_txnsn}"
        }
        rtnmsg
    }

    def getTxnsnOffset = { txn_code ->
        switch (txn_code) {
            case 'n022':
                return 71
            case 'n024':
                return 72
        }
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

    //===
    public static byte[] processTxn(byte[] tiaBytes) {
        String strUrl = "10.143.20.130";
        int iPort = 2006;

        int iCommareaSize = 32000;
        String strChosenServer = "haier";
        String strProgram = "SCLMPC";
        String CICS_USERID = "CICSUSER";
        String CICS_PWD = "";

        ECIRequest eciRequestObject = null;
        JavaGateway javaGatewayObject = null;

        javaGatewayObject = new JavaGateway(strUrl, iPort);

        eciRequestObject = ECIRequest.listSystems(20);
        flowRequest(javaGatewayObject, eciRequestObject);

        if (eciRequestObject.SystemList.isEmpty()) {
            System.out.println("No CICS servers have been defined.");
            if (javaGatewayObject.isOpen()) {
                javaGatewayObject.close();
            }
            throw new Exception("δ���� CICS ����������ȷ�ϣ�");
        }

        try {
            byte[] abytCommarea = new byte[iCommareaSize];

            //javaGatewayObject = getGateWay(strUrl, iPort)
            //���
            byte[] headBytes = tiaBytes;
            System.arraycopy(headBytes, 0, abytCommarea, 0, headBytes.length); //�����ͷ

            //logger.info("���Ͱ�����:\n" + new String(tiaBytes));

            //���Ͱ�
            eciRequestObject = new ECIRequest(ECIRequest.ECI_SYNC, //ECI call type
                    strChosenServer, //CICS server
                    "1", //CICS userid
                    "1", //CICS password
                    strProgram, //CICS program to be run
                    null, //CICS transid to be run
                    abytCommarea, //Byte array containing the
                    // COMMAREA
                    iCommareaSize, //COMMAREA length
                    ECIRequest.ECI_NO_EXTEND, //ECI extend mode
                    0);                       //ECI LUW token

            //��ȡ���ر���
            flowRequest(javaGatewayObject, eciRequestObject);

            //logger.info("��CtgManager�����ذ�����:" + format16(abytCommarea));
            return abytCommarea;
        } catch (Exception e) {
            println("��SBSͨѶ�������⣺" + e.stackTrace);
            throw new RuntimeException(e);
        } finally {
            if (javaGatewayObject != null) {
                if (javaGatewayObject.isOpen()) {
                    try {
                        javaGatewayObject.close();
                    } catch (IOException e) {
                        println("��SBSͨѶ�������⣺" + + e.message);
                    }
                }
            }
        }
    }

    private static JavaGateway getGateWay(String strUrl, int iPort) {
        JavaGateway javaGatewayObject
        ECIRequest eciRequestObject
        javaGatewayObject = new JavaGateway(strUrl, iPort);
        eciRequestObject = ECIRequest.listSystems(20);
        flowRequest(javaGatewayObject, eciRequestObject);

        if (eciRequestObject.SystemList.isEmpty()) {
            System.out.println("No CICS servers have been defined.");
            if (javaGatewayObject.isOpen()) {
                javaGatewayObject.close();
            }
        }
        javaGatewayObject
    }

    private static boolean flowRequest(JavaGateway javaGatewayObject, ECIRequest requestObject) throws Exception {
        int iRc = javaGatewayObject.flow(requestObject);
        String msg = null;
        switch (requestObject.getCicsRc()) {
            case ECIRequest.ECI_NO_ERROR:
                if (iRc == 0) {
                    return true;
                } else {
                    if (javaGatewayObject.isOpen()) {
                        javaGatewayObject.close();
                    }
                    throw new Exception("SBS Gateway ���ִ���("
                            + requestObject.getRcString()
                            + "), �����ԭ�����·�����");
                }
            case ECIRequest.ECI_ERR_SECURITY_ERROR:
                msg = "SBS CICS: �û������������";
                break;
            case ECIRequest.ECI_ERR_TRANSACTION_ABEND:
                msg = "SBS CICS : û��Ȩ�����д˱�CICS����";
                break;
            default:
                msg = "SBS CICS : ���ִ��������ԭ��" + requestObject.getCicsRcString();
        }
        //logger.info("ECI returned: " + requestObject.getCicsRcString());
        //logger.info("Abend code was " + requestObject.Abend_Code + " ");
        if (javaGatewayObject.isOpen()) {
            javaGatewayObject.close();
        }
        throw new Exception(msg);
    }

    //==
    List getTxn_n024_TIA(int txn_sn) {
        List list = new ArrayList();
        //n020 CTY ������
        //list.add("MPC1000147231420  ");     //MPC��ˮ��===================>ÿ�η����仯
        list.add("MPC" + ('0' + txn_sn).padLeft(15, '0'));                       //  MPC��ˮ��         18λ
        list.add("010");                    //���׻���
        list.add(txn_date);                                //  ��������      8λ
        //list.add("20130121");               //ί������
        list.add("010104");                 //�ͻ���
        list.add("CTY");                    //��������
        list.add("001");                    //���׻���
        list.add("+0000000000000.14");      //���׽��
        list.add("T");                      //�������
        list.add("01");                     //����ʻ�����
        list.add("801000010002011001    ");//����ʻ�
        list.add("1");                     //�����ʻ�����
        list.add("                      ");//�����ʻ�
        list.add("37101985510051005301-9999    ");  //�տ����ʺ�
        list.add("���캣��Ͷ�ʷ�չ���޹�˾      ");//�տ�������
        list.add("�й��������йɷ����޹�˾Ȫ�ݷ���                                                    ");//�տ�������
        list.add("103452006018                                                                    ");//������
        list.add("                                        ");                                        //������
        list.add("1                                                                               ");//���������150
        list.add("GE����չ̨����ά�޷�                                                            ");//�����;150
        list.add(" ");                                            //������
        list.add("109452006018"); //�տ��л����š��к�
        list.add("������    ");   //������
        list.add(" ");           //������
        list.add("FSPP293062000001");   //FS��ˮ��
        list.add("2100000147259198");   //������ˮ��
        return list;
    }

    List getTxn_n022_TIA(int txn_sn) {
        List list = new ArrayList();
        //n022       �Թ�����֧�������ѯ
        //list.add("MPC" + (threadcount.get().toString()).padLeft(15, '0'));                       //  MPC��ˮ��         18λ
        list.add("MPC" + ('0' + txn_sn).padLeft(15, '0'));                       //  MPC��ˮ��         18λ
        list.add("2100000147259198");                         //  ������ˮ��    16λ
        list.add(txn_date);                                //  ��������      8λ
        list.add("105452006018");                             //  �տ����к�   12λ
        return list;
    }
}