package com.hzjc.hz2004.vo;

import com.hzjc.hz2004.po.PoHJXZ_MLPXXDJB;
import com.hzjc.wsstruts.vo.DefaultVO;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.*;

/**
 * 门楼牌信息冻结表VO
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public class VoHJXZ_MLPXXDJB
    extends DefaultVO {

  public VoHJXZ_MLPXXDJB() {
  }

  public VoHJXZ_MLPXXDJB(PoHJXZ_MLPXXDJB po) {
    try {
      if (po != null) {
        BeanUtils.copyProperties(this, po);
      }
    }
    catch (InvocationTargetException ex) {
    }
    catch (IllegalAccessException ex) {
    }

  }

  private Long mlpdjid;
  private String djfs;
  private String djlx;
  private String ssxq;
  private String jlx;
  private String jcwh;
  private String mlph;
  private String mlxz;
  private String qyrq;
  private String jzrq;
  private Long cjhjywid;
  private Long xghjywid;

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