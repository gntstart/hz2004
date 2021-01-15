package com.hzjc.wsstruts;

import com.hzjc.hz2004.service.SpService;
import java.util.List;

import com.hzjc.hz2004.base.SpringContextHolder;
import com.hzjc.hz2004.po.PoPOST_KDQCFKB;

//CommonController里面调用
public class KDSTimerTask extends java.util.TimerTask{
  private boolean isrun = false;

  public KDSTimerTask() {
  }

  public void run() {
    if(isrun)
      return;

    isrun = true;

    /*
    //处理跨地区迁移，本地审批迁入同步到远程
    try{
      SpService spService = Hz2004ServiceLocator.getInstance().getSpService();
      List list = spService.findKDQSpywList();
      if (list != null && list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
          PoHJSP_HJSPSQB sq = (PoHJSP_HJSPSQB) list.get(i);
          try {
            //spService.postKDQSpyw(sq);
          }
          catch (Exception e) {
            System.out.println("同步[" + sq.getSpywid() + "]审批业务失败！");
            e.printStackTrace();
          }
          finally {
            ;
          }
        }
      }
    }catch(Throwable e){
      e.printStackTrace();
    }finally{

    }

    //处理跨地区迁移，处理迁出后反馈
    try {
      SpService spService = Hz2004ServiceLocator.getInstance().getSpService();
      List list = spService.findKDQHzZjSb();
      if (list != null && list.size() > 0) {
        for (int i = 0; i < list.size(); i++) {
          HzZjSb sq = (HzZjSb) list.get(i);

          try {
            //spService.postKDQFk(sq);
          }
          catch (Exception e) {
            System.out.println("反馈[" + sq.getKdqqy_qrdqbm() + "," + sq.getKdqqy_qrywid() + "]跨地区迁出业务失败！");
            e.printStackTrace();
          }
          finally {
            ;
          }
        }
      }
    }
    catch (Throwable e) {
      e.printStackTrace();
    }
    finally {
      isrun = false;
    }
    */

   try {
     SpService spService = (SpService)com.hzjc.hz2004.base.SpringContextHolder.getBean("spServiceImpl");
     List list = spService.findKDQYZSFklist();
     
//     PoPOST_KDQCFKB p = new PoPOST_KDQCFKB();
//     p.setQrdq("3407");
//     list.add(p);
     
     if (list != null && list.size() > 0) {
       for (int i = 0; i < list.size(); i++) {
         PoPOST_KDQCFKB sq = (PoPOST_KDQCFKB) list.get(i);
         try {
           spService.postKDQCFKB(sq);
         }
         catch (Exception e) {
           System.out.println("同步[" + sq.getSpywid() + "]审批业务失败！");
           e.printStackTrace();
         }
         finally {
           ;
         }
       }
     }
   }
   catch (Throwable e) {
     e.printStackTrace();
   }
   finally {
     isrun = false;
   }
  }
}
