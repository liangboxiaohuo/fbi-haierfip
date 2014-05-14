package fip.gateway.sbs;

import fip.gateway.JmsManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * ͨ��DEP�շ�SBS����
 * User: zhanrui
 * Date: 20120831
 * Time: 20:58:47
 * To change this template use File | Settings | File Templates.
 */
public class DepCtgManager {

    private static Logger logger = LoggerFactory.getLogger(DepCtgManager.class);

    private static boolean bDataConv = true;
    private static String strDataConv = "ASCII";
    private static int iCommareaSize = 32000;

    /**
     * ��������ѯ�����
     *
     */
    public static byte[] processSingleResponsePkg(String txnCode, List paramList) {

        try {
            //ͨѶ��ʱ
            String requestBuffer = "";
            byte[] abytCommarea = new byte[iCommareaSize];

            //��ͷ���ݣ�xxxx���ף�010���㣬MPC1�նˣ�MPC1��Ա����ͷ����51���ַ�
            requestBuffer = "TPEI" + txnCode + "  010       MT01MT01";
            //�����ͷ
            System.arraycopy(getBytes(requestBuffer), 0, abytCommarea, 0, requestBuffer.length());

            //�������
            setBufferValues(paramList, abytCommarea);

            String sendTime = new SimpleDateFormat("HH:mm:ss:SSS").format(new Date());
            logger.info("����" + txnCode + " ���ͱ���: " + sendTime + format16(truncBuffer(abytCommarea)));

            long starttime = System.currentTimeMillis();

            byte[] toabuf = JmsManager.getInstance().sendAndRecvForDepCoreInterface(abytCommarea);

            long endtime = System.currentTimeMillis();

            logger.info("===����ͨѶ��ʱ:" + (endtime - starttime) + "ms.");
            return toabuf;
        } catch (Exception e) {
            logger.error("��SBSͨѶ�������⣺", e);
            throw new RuntimeException(e.getMessage());
        } finally {
            ;
        }
    }

    //===================================================================================================
    private static byte[]  getBytes(String source) throws java.io.UnsupportedEncodingException {
        if (bDataConv) {
            return source.getBytes(strDataConv);
        } else {
            return source.getBytes();
        }
    }

    private static void setBufferValues(List list, byte[] bb) throws UnsupportedEncodingException {
        int start = 51;
        for (int i = 1; i <= list.size(); i++) {
            String value = list.get(i - 1).toString();
            setVarData(start, value, bb);
            start = start + value.getBytes("GBK").length + 2;
        }
    }


    private static void setVarData(int pos, String data, byte[] aa) throws UnsupportedEncodingException {
        short len = (short) data.getBytes("GBK").length;

        byte[] slen = new byte[2];
        slen[0] = (byte) (len >> 8);
        slen[1] = (byte) (len);
        System.arraycopy(slen, 0, aa, pos, 2);
        System.arraycopy(data.getBytes(), 0, aa, pos + 2, len);
    }


    /**
     * 16���Ƹ�ʽ�����
     *
     * @param buffer
     * @return
     */
    private static String format16(byte[] buffer) {
        StringBuilder result = new StringBuilder();
        result.append("\n");
        int n = 0;
        byte[] lineBuffer = new byte[16];
        for (byte b : buffer) {
            if (n % 16 == 0) {
                result.append(String.format("%05x: ", n));
                lineBuffer = new byte[16];
            }
            result.append(String.format("%02x ", b));
            lineBuffer[n % 16] = b;
            n++;
            if (n % 16 == 0) {
                result.append(new String(lineBuffer));
                result.append("\n");
            }

            //TODO
            if (n >= 1024) {
                result.append("���Ĺ����ѽض�...");
                break;
            }

        }
        for (int k = 0; k < (16 - n % 16); k++) {
            result.append("   ");
        }
        result.append(new String(lineBuffer));
        result.append("\n");
        return result.toString();
    }

    /**
     * @param buffer
     * @return
     */
    private static byte[] truncBuffer(byte[] buffer) {
        int count = 0;
        for (int i = 0; i < iCommareaSize; i++) {
            if (buffer[iCommareaSize - 1 - i] == 0x00) {
                count++;
            } else {
                break;
            }
        }
        byte[] outBuffer = new byte[iCommareaSize - count];
        System.arraycopy(buffer, 0, outBuffer, 0, outBuffer.length);
        return outBuffer;
    }

}
