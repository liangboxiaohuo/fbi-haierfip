package fip.gateway.sbs.txn.Ta541;


import fip.gateway.sbs.CtgManager;
import fip.gateway.sbs.core.SBSRequest;
import fip.gateway.sbs.core.SBSResponse4SingleRecord;

/**
 * �����Ŵ����۳ɹ������ʽ���
 * User: zhanrui
 * Date: 11-6-14
 * Time: ����3:00
 * To change this template use File | Settings | File Templates.
 */
public class Ta541Handler {

    public void run(SBSRequest request, SBSResponse4SingleRecord response) {
        CtgManager ctgManager = new CtgManager();
        ctgManager.processSingleResponsePkg(request, response);
    }
}
