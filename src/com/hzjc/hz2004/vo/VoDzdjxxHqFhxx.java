package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoHJXZ_MLPXXDJB;
import com.hzjc.wsstruts.vo.*;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * 地址冻结信息获取返回信息
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoDzdjxxHqFhxx
    extends DefaultVO {

  private Long mlpdjid; //门（楼）牌冻结ID
  private String djfs; //冻结方式
  private String djlx; //冻结类型
  private String ssxq; //省市县（区）
  private String jlx; //街路巷
  private String jcwh; //居（村）委会
  private String mlph; //门（楼）牌号
  private String mlxz; //门（楼）详址
  private String qyrq; //启用日期
  private String jzrq; //截止日期
  private Long cjhjywid; //创建户籍业务ID
  private Long xghjywid; //修改户籍业务ID

  public VoDzdjxxHqFhxx() {
  }

  public VoDzdjxxHqFhxx(PoHJXZ_MLPXXDJB poHJXZ_MLPXXDJB) {
    try {
      BeanUtils.copyProperties(this, poHJXZ_MLPXXDJB);
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }
  }

  public Long getCjhjywid() {
    return cjhjywid;
  }

  public void setCjhjywid(Long cjhjywid) {
    this.cjhjywid = cjhjywid;
  }

  public String getDjfs() {
    return djfs;
  }

  public void setDjfs(String djfs) {
    this.djfs = djfs;
  }

  public String getDjlx() {
    return djlx;
  }

  public void setDjlx(String djlx) {
    this.djlx = djlx;
  }

  public String getJcwh() {
    return jcwh;
  }

  public void setJcwh(String jcwh) {
    this.jcwh = jcwh;
  }

  public String getJlx() {
    return jlx;
  }

  public void setJlx(String jlx) {
    this.jlx = jlx;
  }

  public String getJzrq() {
    return jzrq;
  }

  public void setJzrq(String jzrq) {
    this.jzrq = jzrq;
  }

  public Long getMlpdjid() {
    return mlpdjid;
  }

  public void setMlpdjid(Long mlpdjid) {
    this.mlpdjid = mlpdjid;
  }

  public String getMlph() {
    return mlph;
  }

  public void setMlph(String mlph) {
    this.mlph = mlph;
  }

  public String getMlxz() {
    return mlxz;
  }

  public void setMlxz(String mlxz) {
    this.mlxz = mlxz;
  }

  public String getQyrq() {
    return qyrq;
  }

  public void setQyrq(String qyrq) {
    this.qyrq = qyrq;
  }

  public String getSsxq() {
    return ssxq;
  }

  public void setSsxq(String ssxq) {
    this.ssxq = ssxq;
  }

  public Long getXghjywid() {
    return xghjywid;
  }

  public void setXghjywid(Long xghjywid) {
    this.xghjywid = xghjywid;
  }
}