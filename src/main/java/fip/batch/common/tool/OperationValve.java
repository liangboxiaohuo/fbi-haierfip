package fip.batch.common.tool;

/**
 * ����բ��.
 * User: zhanrui
 * Date: 12-12-5
 * Time: ����9:16
 * To change this template use File | Settings | File Templates.
 */
public interface OperationValve {
    boolean isOpen(String valveID);
    void openValve(String valveID);
    void closeValve(String valveID);
}
