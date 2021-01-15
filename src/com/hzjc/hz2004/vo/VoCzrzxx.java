package com.hzjc.hz2004.vo;

import com.hzjc.wsstruts.vo.DefaultVO;

/**
 * 系统用户操作日志表VO
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口管理系统Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: 浙江金铖华元新技术有限公司</p>
 * @author kgb_hz@126.com,kgb@primetech.com.cn
 * @version 1.0
 */

public class VoCzrzxx
    extends DefaultVO {
  private String ywbz; //业务ID
  private String khdip; //用户IP
  private String czkssj; //开始时间
  private String czjssj; //结束时间
  private String rzlx; //日志类型
  private String rznr;//日志内容
  private Long czyid;//用户ID
  private Long zxsj; //执行时间
  private String organization;// ORGANIZATION;
  private String organizationId;//ORGANIZATION_ID;
  private String operateType;//OPERATE_TYPE;
  private String operateResult;//OPERATE_RESULT;
  private String errorCode;//ERROR_CODE;
  private String operateName;//OPERATE_NAME;
  private String resourceType;//RESOURCE_TYPE;
  private String resourceName;//RESOURCE_NAME;
  private String czyxm;

  public void setCzyxm(String czyxm) {
        this.czyxm = czyxm;
    }

    public String getCzyxm() {
        return czyxm;
    }


  public void setResourceName(String resourceName) {
        this.resourceName = resourceName;
    }

    public String getResourceName() {
        return resourceName;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public String getResourceType() {
        return resourceType;
    }


    public void setOperateName(String operateName) {
        this.operateName = operateName;
    }

    public String getOperateName() {
        return operateName;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
      }

    public String getErrorCode() {
        return errorCode;
    }

    public void setOperateResult(String operateResult) {
      this.operateResult = operateResult;
    }

    public String getOperateResult() {
      return operateResult;
    }

    public void setOperateType(String operateType) {
      this.operateType = operateType;
    }

    public String getOperateType() {
      return operateType;
    }

    public String getOrganizationId() {
      return organizationId;
    }

    public void setOrganizationId(String organizationId) {
      this.organizationId = organizationId;
    }

    public String getOrganization() {
      return organization;
    }
    public void setOrganization(String organization) {
      this.organization = organization;
  }

  public VoCzrzxx() {
  }

  public Long getCzyid() {
    return czyid;
  }

  public void setCzyid(Long czyid) {
    this.czyid = czyid;
  }

  public String getYwbz() {
    return ywbz;
  }

  public void setYwbz(String ywbz) {
    this.ywbz = ywbz;
  }

  public String getKhdip() {
    return khdip;
  }

  public void setKhdip(String khdip) {
    this.khdip = khdip;
  }

  public String getCzkssj() {
    return czkssj;
  }

  public void setCzkssj(String czkssj) {
    this.czkssj = czkssj;
  }

  public String getCzjssj() {
    return czjssj;
  }

  public void setCzjssj(String czjssj) {
    this.czjssj = czjssj;
  }

  public String getRzlx() {
    return rzlx;
  }

  public void setRzlx(String rzlx) {
    this.rzlx = rzlx;
  }

  public String getRznr() {
    return rznr;
  }

  public void setRznr(String rznr) {
    this.rznr = rznr;
  }
  public Long getZxsj() {
    return zxsj;
  }
  public void setZxsj(Long zxsj) {
    this.zxsj = zxsj;
  }

}
