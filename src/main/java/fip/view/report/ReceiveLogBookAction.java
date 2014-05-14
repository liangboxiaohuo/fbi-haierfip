package fip.view.report;

import fip.common.SystemService;
import net.sf.jxls.report.ReportManager;
import net.sf.jxls.report.ReportManagerImpl;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pub.platform.advance.utils.PropertyManager;
import pub.platform.db.ConnectionManager;
import pub.platform.db.DatabaseConnection;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: haiyuhuang
 * Date: 11-9-2
 * Time: ����11:17
 * To change this template use File | Settings | File Templates.
 */
@ManagedBean (name="receiveLogBookAction")
@RequestScoped
public class ReceiveLogBookAction {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private String sysDate;
    private String sysTime;
    private Date begindate;
    private Date enddate;

    @PostConstruct
    public void init() {
        begindate = new Date();
        enddate = new Date();
        this.sysDate = sdf.format(new Date());
        this.sysTime = new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    public void Excelexport() {
        try {
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            ServletOutputStream os = response.getOutputStream();
            response.reset();
            String begindt = sdf.format(begindate);
            String enddt = sdf.format(enddate);
            String fileName = "logbook" + begindt + "��" + enddt + ".xls";
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            response.setContentType("application/msexcel");
            //ģ��
            String modelPath = PropertyManager.getProperty("REPORT_ROOTPATH") + "receiveInfoModel.xls";
            Map beans = new HashMap();
            beans.put("begindate",begindt);
            beans.put("enddate",enddt);
            DatabaseConnection conn = ConnectionManager.getInstance().get();
            ReportManager reportManager = new ReportManagerImpl(conn.getConnection(), beans);
            beans.put("rm", reportManager);
            XLSTransformer transformer = new XLSTransformer();
            InputStream is = new BufferedInputStream(new FileInputStream(modelPath));
            Workbook wb = transformer.transformXLS(is, beans);
            wb.write(os);
            os.flush();
            os.close();
            is.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionManager.getInstance().release();
        }

    }
    public void ExcelexportForconsumedetl() {
        try {
            HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
            ServletOutputStream os = response.getOutputStream();
            response.reset();
            String begindt = sdf.format(begindate);
            String enddt = sdf.format(enddate);
            String fileName = "consumedetlBook" + begindt + "��" + enddt + ".xls";
            response.setHeader("Content-disposition", "attachment; filename=" + fileName);
            response.setContentType("application/msexcel");
            //ģ��
            String modelPath = PropertyManager.getProperty("REPORT_ROOTPATH") + "consumeDetlBookModel.xls";
            Map beans = new HashMap();
            beans.put("begindate",begindt);
            beans.put("enddate",enddt);
            DatabaseConnection conn = ConnectionManager.getInstance().get();
            ReportManager reportManager = new ReportManagerImpl(conn.getConnection(), beans);
            beans.put("rm", reportManager);
            XLSTransformer transformer = new XLSTransformer();
            InputStream is = new BufferedInputStream(new FileInputStream(modelPath));
            Workbook wb = transformer.transformXLS(is, beans);
            wb.write(os);
            os.flush();
            os.close();
            is.close();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            ConnectionManager.getInstance().release();
        }
    }

    public String getSysDate() {
        return sysDate;
    }

    public void setSysDate(String sysDate) {
        this.sysDate = sysDate;
    }

    public String getSysTime() {
        return sysTime;
    }

    public void setSysTime(String sysTime) {
        this.sysTime = sysTime;
    }

    public Date getBegindate() {
        return begindate;
    }

    public void setBegindate(Date begindate) {
        this.begindate = begindate;
    }

    public Date getEnddate() {
        return enddate;
    }

    public void setEnddate(Date enddate) {
        this.enddate = enddate;
    }
}
