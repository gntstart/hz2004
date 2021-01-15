package com.hzjc.hz2004.service.impl;

import com.hzjc.hz2004.service.*;
import com.hzjc.wsstruts.exception.*;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.apache.commons.logging.Log;
import java.util.Map;
import java.util.HashMap;
import com.hzjc.hz2004.constant.PublicConstant;
import java.io.File;
import java.io.*;

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
public class DateDownload4QxServiceImpl extends Hz2004BaseService implements DateDownload4QxService {

    //日志处理
    protected static Log _log = LogFactory.getLog(DateDownload4QxServiceImpl.class);

    //用户令牌
    protected static Map mapYhlb = new HashMap();

    //在线用户数
//    protected static int iCount = 0;

    public DateDownload4QxServiceImpl() {
    }

    public String getAccess(String iYhid, String strAnt){

        //确保线程安全
        synchronized(PublicConstant.objReport){

            //取得系统分隔符,以适应各种系统
            final String separator = File.separator;

            //确保FTP中转目录存在
            String path = null;
            File file = null;
            path = this.getXtkzcsAsStr(PublicConstant.XTKZCS_PLSJDCML);
            //path = "D:" + separator + "Download";
//            path = "Download";
            file = new File(path);
            if (!file.exists()){
                file.mkdirs();
            }

            //生成日志文件
//            file = new File(path + separator + iYhid + "_log.xls");
//            if (!file.exists()) {
//                try {
//                    file.createNewFile();
//                } catch (IOException ex) {
//                    return "error5";
//                }
//            }
//            else if (file.length() > 1024*1024*1024*8){
//                try {
//                    file.delete();
//                    file.createNewFile();
//                } catch (IOException ex1) {
//                    return "error5";
//                }
//            }

            //返回参数
            String strParam = null;

            //确保参数的正确性
            if(strAnt == null || strAnt.equalsIgnoreCase("")){
                strParam = "error5";
            }

            //判断要求的业务操作，进行业务分发
            //赋予权限
            if(strAnt.equalsIgnoreCase("add")){

                if (mapYhlb.get(iYhid) != null){
                    strParam = "error2";
                }
                else if (mapYhlb.size() >= 10){
                    strParam = "error1";
                }
                else{
                    mapYhlb.put(iYhid, "EDUCE");
//                    iCount = iCount + 1;
                    strParam = "SUCCESS";

                    //用户FTP中转目录,并清空前次导出的数据
                    path = path + separator + iYhid;
                    file = new File(path);
                    if (!file.exists()) {
                        file.mkdirs();
                    } else if (file.exists() && file.isDirectory()) {
                        File[] delete = file.listFiles();
                        for (int i = 0; i < delete.length; i++) {
                            if (delete[i].isFile()) {
                                delete[i].delete();
                            }
                        }
                    }
                }

            }
            //收回权限
            else if(strAnt.equalsIgnoreCase("dec")){

                if (mapYhlb.get(iYhid) == null){
                    strParam = "error3";
                }
                else if (mapYhlb.size() <= 0){
                    strParam = "error4";
                }
                else{
                    mapYhlb.remove(iYhid);
//                    iCount = iCount - 1;
                    strParam = "SUCCESS";
                }

            }

            //对令牌操作的结果返回
            return strParam;

        }

    }
}
