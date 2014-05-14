package fip.gateway.ccms.domain.T200101;

import com.thoughtworks.xstream.annotations.XStreamImplicit;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: zhanrui
 * Date: 2010-10-10
 * Time: 17:32:16
 * To change this template use File | Settings | File Templates.
 */
public class T200101ResponseList {
    @XStreamImplicit(itemFieldName="ROWS")
	private List<T200101ResponseRecord> content= new ArrayList();

    public List getContent() {
        return this.content;
    }

    public void add(T200101ResponseRecord record) {
        this.content.add(record);
    }

}
