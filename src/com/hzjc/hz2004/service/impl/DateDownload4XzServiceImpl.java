package com.hzjc.hz2004.service.impl;

import com.hzjc.hz2004.service.*;
import com.hzjc.wsstruts.exception.*;
import org.apache.commons.logging.LogFactory;
import org.apache.commons.logging.Log;
import com.hzjc.hz2004.vo.VoDateDownloadxx;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.io.FileWriter;
import java.io.FileInputStream;
import java.sql.SQLException;
import java.util.Date;
import java.sql.Connection;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import com.hzjc.hz2004.constant.PublicConstant;
/**
 * <p>Title: </p>
 *
 * <p>Description: 常住人口二代证Hz2004版</p>
 *
 * <p>Copyright: Copyright (c) 2006</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
@Service
public class DateDownload4XzServiceImpl extends Hz2004BaseService implements
        DateDownload4XzService {

    //日志处理
    protected static Log _log = LogFactory.getLog(DateDownload4XzServiceImpl.class);

    public DateDownload4XzServiceImpl() {
    }

    /**
     * 数据导出
     * @param vo VoDateDownloadxx
     * @return String[]
     */
    public String[] dateDownload(VoDateDownloadxx vo) throws ServiceException {

        final String separator = File.separator; //取得系统分隔符,以适应各种系统

        String Cxdctj = vo.getCxdctj(); //查询导出条件
        String Dczdlb = vo.getDczdlb(); //导出定段列表
        String Mwjdx = vo.getMwjdx(); //每文件的大小
        String Yhid = vo.getYhid(); //用户ID
        String YhidTwo = vo.getYhidTwo();//真正的用户ID
        String ip = vo.getIp();//用户IP
        String dw = vo.getDw();//受理人单位代码
        DateDownload4QxService service = vo.getService(); //权限服务

        int iMwjdx = Integer.parseInt(Mwjdx); //每文件存放的记录数
        String[] strDczdlb = Dczdlb.split(","); //导出定段列表

        String path = this.getXtkzcsAsStr(PublicConstant.XTKZCS_PLSJDCML) +
            separator + Yhid + separator;
//        String path = "D:" + separator + "Download" + separator + Yhid +
//                      separator; //请求用户的目录
//        String logPath = "D:" + separator + "Download" + separator + Yhid +
//                         "_log.xls"; //日志文件
        String[] DcwjlbList = null; //导出的文件列表
        StringBuffer sql = null; //JDBC所要执得的SQL语句

        Connection con = null; //数据库连接
        Statement st = null; //静态容器
        ResultSet result = null; //结果集
        File file = null; //需要导出的目标文件
        FileWriter writer = null; //需要写入文件的内容

        //数据导出的核心方法,主要完成以下功能
        //1.完成连接,取得结果集,生成文件
        //2.完成代码转换,实现信息有效
        //3.用异常机制保证释放连接,关闭容器
        try {

            //由传入的条件组成基础SELECT
            sql = new StringBuffer();
            sql.append(" SELECT ");
            sql.append(" " + Dczdlb + " "); //ROWNUM RN
            sql.append(" FROM XT_XZJDXXB ");
            if (!(Cxdctj.equalsIgnoreCase("") || Cxdctj == null)) {
                sql.append(" WHERE ");
                sql.append(" " + Cxdctj + " ");
            }

            //取得连接,得到容器

            con = super.getConnection();
            st = con.createStatement();

            //方法一:
            log_insert("0", "正在执行查询", YhidTwo, ip, dw, sql.toString());

            //与数据库第一次握手
            //1.目的是得到总记录数和总文件数
            //2.生成目标文件,用于存放导出记录

            //方法二:

            //1.于数据库交互一次
            //2.用算法控制生成文件及每文件的大小
            //3.写日志
            result = st.executeQuery(sql.toString()); //取得结果集
            int iCount = 0; //记录数标致位
            int iFile = 0; //文件数标致位
            List fileList = new ArrayList(); //生成的文件列表
            while (true) {
                if (result.next()) { //结果集取数据
                    if (iCount % 5000 == 0) { //生成文件
                        if (writer != null) { //结束上次的文件流
                            writer.close();
                        }
                        fileList.add(path + (new Integer(iFile + 1)).toString() +
                                     ".xls"); //生成文件列表
                        iCount = 0; //记录数标致位清0
                        iFile = iFile + 1; //文件数标致位递增
                        file = new File(path + (new Integer(iFile)).toString() +
                                        ".xls");
                        if (!file.exists()) { //生成文件
                            file.createNewFile();
                        } else if (file.exists()) {
                            file.delete();
                            file.createNewFile();
                        }
                        writer = new FileWriter(file); //获得最新的文件流
//                        this.log(logPath, "XT_XZJDXXB", Cxdctj,
//                                 "成功新建文件,正在进行数据导入:" +
//                                 (new Integer(iFile)).toString() +
//                                 ".xls");
                        log_update("1", "成功生成文件,正在写数据", iFile + "", YhidTwo);
                    }

                    for (int temp = 0; temp < strDczdlb.length; temp++) { //将记录写入文件
                        writer.write("\'");
                        writer.write((result.getString(strDczdlb[temp]) == null) ?
                                     "" : result.getString(strDczdlb[temp]));
                        writer.write("\t");
                    }
                    writer.write("\n");

                    iCount = iCount + 1; //记录数标致位递增
                } else { //结果集取尽时
                    if (writer != null) { //关闭文件流
                        writer.close();
                    }
                    if (result != null) { //关闭结果集
                        result.close();
                    }
                    if (fileList.size() > 0) {
                        DcwjlbList = new String[iFile];
                        DcwjlbList = (String[]) fileList.toArray(DcwjlbList); //生成导出文件列表
                    } else {
                        DcwjlbList = null;
                    }
                    break; //退出
                }
            }

            //执行完毕后,做相应的关闭和连接计数操作
            if (writer != null) {
                writer.close();
            }

            if (result != null) {
                result.close();
            }

            if (st != null) {
                st.close();
            }

            if (con != null) {
                con.close();
            }



//            this.log(logPath, "XT_XZJDXXB", Cxdctj, "全部数据导出完毕,所有连接正确释放.");

//            this.ftp(Yhid, DcwjlbList);

            log_update("2", "数据导出成功,请及时下载", YhidTwo);

//            this.log(logPath, "XT_XZJDXXB", Cxdctj, "全部文件上传FTP成功,服务器端数据文件回滚.");

        }
        //异常机制,确保连接不被泄漏
        catch (Exception e) {

            //已导出文件回滚
            for (int i = 0; DcwjlbList != null && i < DcwjlbList.length; i++) {
                file = new File(DcwjlbList[i]);
                if (file.exists()) {
                    file.delete();
                }
            }

            //文件列表回滚
            DcwjlbList = null;

            service.getAccess(Yhid, "dec");

            log_update("3", "数据导出发生异常", YhidTwo);

            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException ex4) {
                    ex4.printStackTrace();
                    throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                               "文件流关闭异常", ex4);
                }
            }

            if (result != null) {
                try {
                    result.close();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                               "结果集关闭异常", ex);
                }
            }

            if (st != null) {
                try {
                    st.close();
                } catch (SQLException ex1) {
                    ex1.printStackTrace();
                    throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                               "SQL容器关闭异常", ex1);
                }
            }

            if (con != null) {
                try {
                    con.close();
                } catch (SQLException ex2) {
                    ex2.printStackTrace();
                    throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER,
                                               "数据库连接关闭异常", ex2);
                }
            }



//            this.log(logPath, "XT_XZJDXXB", Cxdctj, "数据导出异常,全部数据文件回滚.");

            e.printStackTrace();

            throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, "服务器异常", e);

        }
        //执行完毕后,做相应的释放权限操作
        finally {

            //已导出文件回滚
//            for (int i = 0; DcwjlbList != null && i < DcwjlbList.length; i++) {
//                file = new File(DcwjlbList[i]);
//                if (file.exists()) {
//                    file.delete();
//                }
//            }

            service.getAccess(Yhid, "dec");

//            this.log(logPath, "XT_XZJDXXB", Cxdctj, "全部数据导出完毕,权限正确释放.");

            return DcwjlbList;

        }

    }

//    /**
//     * 记录日志文件
//     * @param path String
//     * @param message String
//     */
//    private void log(String path, String table, String where, String message) {
//
//        final String separator = File.separator;
//
//        Date today = new Date();
//        SimpleDateFormat df = new SimpleDateFormat(
//                "yyyy.MM.dd 'at' HH:mm:ss aaa");
//        String todayAsString = df.format(today);
//
//        try {
//            File file = new File(path);
//            FileWriter writer = new FileWriter(file, true);
//            writer.write(todayAsString);
//            writer.write("\t");
//            writer.write(table);
//            writer.write("\t");
//            writer.write(where);
//            writer.write("\t");
//            writer.write(message);
//            writer.write("\t");
//            writer.write("\n");
//            writer.close();
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }
//
//    }

    /**
     * 上传至FTP
     * @param Yhid String
     * @param DcwjlbList String[]
     * @throws Exception
     */
    private void ftp(String Yhid, String[] DcwjlbList) throws Exception {

        final String separator = File.separator;

        Connection con = null; //数据库连接
        Statement st = null; //静态容器
        ResultSet result = null; //结果集

        String url = null; //FTP路径
        String name = null; //FTP用户名
        String pass = null; //FTP密码

        FTPClient ftpClient = null; //FTP服务对象
        boolean flag = false; //连接是否成功标致位
        File file = null; //需要上传的目标文件
        FileInputStream iStream = null; //与FTP关联的流

        try {

            con = super.getConnection();
            st = con.createStatement();
            result = st.executeQuery(
                    "SELECT BZ, KZZ, MRZ FROM XT_XTKZCSB WHERE KZLB = '1028'");
            if (result.next()) {
                url = result.getString("BZ"); //得到FTP路径
                name = result.getString("KZZ"); //得到FTP用户名
                pass = result.getString("MRZ"); //得到FTP密码
                result.close();
                st.close();
                con.close();
            } else {
                result.close();
                st.close();
                con.close();
            }

            ftpClient = new FTPClient();
            ftpClient.connect(url); //建立FTP连接
            flag = ftpClient.login(name, pass); //登录FTP
            if (!flag) { //连接失败
                ftpClient.disconnect();
                throw new Exception("FTP连接失败");
            }

            ftpClient.makeDirectory(new String(Yhid.getBytes("gb2312"),
                                               "iso-8859-1")); //在服务器创建目录
            ftpClient.changeWorkingDirectory(new String(Yhid.getBytes("gb2312"),
                    "iso-8859-1")); //转移工作目录

            for (int i = 0; DcwjlbList != null && i < DcwjlbList.length; i++) {
                file = new File(DcwjlbList[i]);
                iStream = new FileInputStream(file);
                ftpClient.storeFile(i + 1 + ".xls", iStream); //上传文件
                iStream.close();
            }
            ftpClient.disconnect(); //断开FTP连接
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    /**
     *
     * @throws ServiceException
     * @todo Implement this com.hzjc.wsstruts.service.WSService method
     */
    public void beginTransaction() throws ServiceException {
    }

    /**
     *
     * @throws ServiceException
     * @todo Implement this com.hzjc.wsstruts.service.WSService method
     */
    public void commitTransaction() throws ServiceException {
    }


    /**
     * 记录日志
     * @param SCZT String 生成状态0查询1正在生成2完成3异常
     * @param ZTSM String 状态说明
     * @param SLRID String 受理人id
     * @param CZIP String 受理人ip
     * @param SLDM String 受理单位
     * @param CXYJ String 查询语句
     */
    private void log_insert(String SCZT, String ZTSM, String SLRID, String CZIP,
                            String SLDW, String CXYJ) throws Exception {

        String XZID = "SID_HJXX_XZXXB.NEXTVAL";
        String CXJK = "F3704";

        Date today = new Date();
        SimpleDateFormat df = new SimpleDateFormat(
                "yyyyMMddHHmmss");
        String SLSJ = df.format(today);

        StringBuffer insert = new StringBuffer();
        insert.append(" INSERT INTO HJXX_XZXXB(XZID, SCZT, ZTSM, CXJK, SLRID, SLSJ, CZIP, SLDW, CXYJ) ");
        insert.append(" VALUES( ");
        insert.append("  " + XZID + ", ");
        insert.append(" '" + SCZT + "', ");
        insert.append(" '" + ZTSM + "', ");
        insert.append(" '" + CXJK + "', ");
        insert.append(" '" + SLRID + "', ");
        insert.append(" '" + SLSJ + "', ");
        insert.append(" '" + CZIP + "', ");
        insert.append(" '" + SLDW + "', ");
        insert.append(" '" + CXYJ.replaceAll("'", "").replaceAll("\"", "") + "' ");
        insert.append(" ) ");

        try {
            Connection con = super.getConnection();
            Statement st = con.createStatement();

            st.executeUpdate(insert.toString());

            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

    }

    /**
     * 更新日志
     * @param SCZT String 生成状态0查询1正在生成2完成3异常
     * @param ZTSM String 状态说明
     * @param SCSL String 已生成文件个数
     * @param SLRID String 受理人id
     */
    private void log_update(String SCZT, String ZTSM, String SCSL, String SLRID) throws
            Exception {

        StringBuffer update = new StringBuffer();
        update.append(" UPDATE HJXX_XZXXB SET SCZT = ");
        update.append(" '" + SCZT + "', ");
        update.append(" ZTSM = ");
        update.append(" '" + ZTSM + "' ");
        if (SCSL != null && !SCSL.equalsIgnoreCase("")) {
            update.append(", SCSL = ");
            update.append(" '" + SCSL + "' ");
        }
        update.append(" WHERE SLRID = ");
        update.append(" '" + SLRID + "' ");
        update.append(
                " AND SLSJ = (SELECT MAX(SLSJ) FROM HJXX_XZXXB WHERE SLRID = ");
        update.append(" '" + SLRID + "' ");
        update.append(" ) ");

        try {

            Connection con = super.getConnection();
            Statement st = con.createStatement();

            st.executeUpdate(update.toString());

            st.close();
            con.close();
        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }

    /**
     * 更新日志
     * @param SCZT String 生成状态0查询1正在生成2完成3异常
     * @param ZTSM String 状态说明
     * @param SLRID String 受理人id
     */
    private void log_update(String SCZT, String ZTSM, String SLRID) throws
            Exception {
        this.log_update(SCZT, ZTSM, null, SLRID);
    }


}
