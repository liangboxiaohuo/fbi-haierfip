package fip.batch.crontask;

/**
 * ��̨�Զ����۴���.
 * User: zhanrui
 * Date: 12-12-3
 * Time: ����4:11
 */
public interface AutoCutpayManager {
    void obtainBills();
    void performCutpayTxn();
    void performResultQueryTxn();
    void writebackBills();
    void archiveBills();
}
