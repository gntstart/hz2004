package com.hzjc.wsstruts;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.hzjc.hz2004.po.PoLODOP;
import com.hzjc.hz2004.service.SpService;

//CommonController里面调用
public class EchartsTask extends java.util.TimerTask{

  public EchartsTask() {
  }

  public void run() {
   try {
     SpService spService = (SpService)com.hzjc.hz2004.base.SpringContextHolder.getBean("spServiceImpl");
//     List list = spService.queryEchartsDateList();
//     //删除所有记录
//     spService.deleteAllEchartDate();
//     //插入新的list
//     spService.insertAllEchartDate(list);
     spService.queryEchartsByProcedure();
   }
   catch (Throwable e) {
     e.printStackTrace();
   }
   finally {
   }
  }
}
