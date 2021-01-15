package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;
import com.hzjc.hz2004.po.PoXT_ZSBBMBXXB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.util.StringUtils;
import java.sql.Blob;
/**
 * <p>Title: HZ2004</p>
 * <p>Description: 常住人口二代证Hz2004版
 *                 制式报表操作VO</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author huhua(hh_hz@126.com)
 * @version 1.0
 */


public class VoXtzsbbmbxx
extends DefaultVO {
 //制式模板报表
 private Long zsbbmbid;
 private String zsbblb;
 private String bbmbmc;
 private String bbmb;
 private String jlsj;
 private Long scrid;
 private String xgsj;
 private Long xgrid;
  //用户信息---创建人信息
  private String scryhdlm;
  private String scryhxm;
  //用户信息---修改人信息
  private String xgryhdlm;
  private String xgryhxm;

 public VoXtzsbbmbxx(){
 }
  public VoXtzsbbmbxx(VoXT_ZSBBMBXXB voXT_ZSBBMBXXB,PoXT_YHXXB poXTYHXXBA,PoXT_YHXXB poXTYHXXBB) {
    try {
      BeanUtils.copyProperties(this, voXT_ZSBBMBXXB);
      this.jlsj=StringUtils.formatDateBy(StringUtils.strToDate(voXT_ZSBBMBXXB.getJlsj(),"yyyyMMddHHmmss"),"yyyy-MM-dd HH:mm:ss");
      this.xgsj=StringUtils.formatDateBy(StringUtils.strToDate(voXT_ZSBBMBXXB.getXgsj(),"yyyyMMddHHmmss"),"yyyy-MM-dd HH:mm:ss");
      this.setScryhdlm(poXTYHXXBA.getYhdlm());
      this.setScryhxm(poXTYHXXBA.getYhxm());
      if(poXTYHXXBB != null){
        this.setXgryhdlm(poXTYHXXBB.getYhdlm());
        this.setXgryhxm(poXTYHXXBB.getYhxm());
      }

    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }
  }

  public void setZsbbmbid(Long zsbbmbid) {
    this.zsbbmbid = zsbbmbid;
  }

  public Long getZsbbmbid() {
    return zsbbmbid;
  }

  public void setZsbblb(String zsbblb) {
    this.zsbblb = zsbblb;
  }

  public String getZsbblb() {
    return zsbblb;
  }

  public void setBbmbmc(String bbmbmc) {
    this.bbmbmc = bbmbmc;
  }

  public String getBbmbmc() {
    return bbmbmc;
  }

  public void setBbmb(String bbmb) {
    this.bbmb = bbmb;
  }

  public String getBbmb() {
    return bbmb;
  }

  public void setJlsj(String jlsj) {
    this.jlsj = jlsj;
  }

  public String getJlsj() {
    return jlsj;
  }

  public void setScrid(Long scrid) {
    this.scrid = scrid;
  }

  public Long getScrid() {
    return scrid;
  }

  public void setXgsj(String xgsj) {
    this.xgsj = xgsj;
  }

  public String getXgsj() {
    return xgsj;
  }

  public void setXgrid(Long xgrid) {
    this.xgrid = xgrid;
  }

  public Long getXgrid() {
    return xgrid;
  }

  //用户信息
   public void setScryhdlm(String yhdlm) {
     this.scryhdlm = yhdlm;
   }

   public String getScryhdlm() {
     return scryhdlm;
   }

   public void setScryhxm(String yhxm) {
     this.scryhxm = yhxm;
   }

   public String getScryhxm() {
     return scryhxm;
   }


   //修改人
   public void setXgryhdlm(String yhdlm) {
     this.xgryhdlm = yhdlm;
   }

   public String getXgryhdlm() {
     return xgryhdlm;
   }
   public void setXgryhxm(String yhxm) {
     this.xgryhxm = yhxm;
   }

   public String getXgryhxm() {
     return xgryhxm;
   }

}
