package com.hzjc.hz2004.service.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.common.HjCommon;
import com.hzjc.hz2004.constant.DzConstant;
import com.hzjc.hz2004.constant.HjConstant;
import com.hzjc.hz2004.constant.PublicConstant;
import com.hzjc.hz2004.dao.DAOFactory;
import com.hzjc.hz2004.dao.PojoInfo;
import com.hzjc.hz2004.po.PoHJXX_HXXB;
import com.hzjc.hz2004.po.PoHJXX_MLPXXXXB;
import com.hzjc.hz2004.po.PoMESSAGEXXB;
import com.hzjc.hz2004.po.PoXT_JWHXXB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.service.MessageService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateConverter;
import com.hzjc.hz2004.vo.*;
import com.hzjc.util.StringUtils;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.WSErrCode;

/**
 * 消息业务类
 */

@Service
public class MessageServiceImpl
    extends HjCommon
    implements MessageService {

  //日志处理
  protected static Log _log = LogFactory.getLog(MessageServiceImpl.class);

  /**
   * 处理全户变更业务
   * @param voQhbgxx - 全户变更信息
   * @return
   * @throws ServiceException
   * @throws DAOException
   */
  public VoQhbgywfhxx processQhbgyw(VoSbjbxx voSbjbxx, VoQhbgxx voQhbgxx[]) throws
      ServiceException, DAOException {

    VoQhbgywfhxx voQhbgywfhxx = null;
    VoQhbgfhxx voQhbgfhxx[] = null;
    Long hjywid = null;
    String now = StringUtils.getServiceTime();

    /////////////////////////////////////
    //数据校验
    if (voQhbgxx == null || (voQhbgxx != null && voQhbgxx.length <= 0)) {
      return null;
    }

    try {
      PojoInfo  hjls_hjywlsbDAO = DAOFactory.createHJLS_HJYWLSBDAO();
      PojoInfo  hjxx_hxxbDAO = DAOFactory.createHJXX_HXXBDAO();
      PojoInfo  hjxx_mlpxxxxbDAO = DAOFactory.createHJXX_MLPXXXXBDAO();
      PojoInfo  xt_jwhxxbDAO = DAOFactory.createXT_JWHXXBDAO();

      /////////////////////////////////////
      //事务开始
    
      //////////////////////////////
      //得到户籍业务ID
      hjywid = (Long) hjls_hjywlsbDAO.getId();

      ///////////////////////////////////////
      //保存全户变更信息
      voQhbgfhxx = new VoQhbgfhxx[voQhbgxx.length];
      for (int i = 0; i < voQhbgxx.length; i++) {
        //得到户信息
        PoHJXX_HXXB poHxx = super.get(PoHJXX_HXXB.class, voQhbgxx[i].
            getHhnbid());
        if (poHxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DAO_NOTEXIST,
                                     "户信息没找到，全户变更业务无法完成。", null);
        }
        //校验户信息的时效性
        this.checkHXX(poHxx, hjxx_hxxbDAO, "全户变更业务");
        //判断户号状态
        if (!HjConstant.HHZT_ZC.equals(poHxx.getHhzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "户号状态为非正常状态，全户变更业务无法完成。", null);
        }
        //得到地信息
        PoHJXX_MLPXXXXB poMlpxxxx = super.get(PoHJXX_MLPXXXXB.class,
            poHxx.getMlpnbid());
        if (poMlpxxxx == null) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLOGIC,
                                     "户所在的地信息没找到，全户变更业务无法完成。", null);
        }
        //数据范围限制
        List sjfwList = new ArrayList();
        VoXtsjfw voXtsjfw = new VoXtsjfw();
        voXtsjfw.setSjfw(poMlpxxxx.getJcwh());
        voXtsjfw.setSjfwbz(PublicConstant.XT_QX_JWH);
        sjfwList.add(voXtsjfw);
        boolean bLimit = false;
        bLimit = XtywqxServiceImpl.VerifyDataRange(this.getUserInfo().getYhid().
            toString(),
            PublicConstant.GNBH_HJ_QHBGYW, sjfwList);
        if (!bLimit) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
        		  CommonUtil.getSjfwError(sjfwList)  + "，全户变更业务无法完成。", null);
        }

        //如果地信息存在则引用，否则创建
        VoDzzjxx voDzxx = new VoDzzjxx();
        BeanUtils.copyProperties(voDzxx, poMlpxxxx);
        BeanUtils.copyProperties(voDzxx, voQhbgxx[i]);
        PoHJXX_MLPXXXXB poMlpxxxxNew = this.getDXX(voDzxx);
        if (poMlpxxxxNew == null) {
          //保存新地信息
          poMlpxxxxNew = new PoHJXX_MLPXXXXB();
          BeanUtils.copyProperties(poMlpxxxxNew, poMlpxxxx);
          BeanUtils.copyProperties(poMlpxxxxNew, voQhbgxx[i]);
          //Begin_修改于闵红斌(2004/11/15 17:25:00)
          /*
                     poMlpxxxxNew.setXzjd(poMlpxxxxNew.getJcwh() != null &&
                               poMlpxxxxNew.getJcwh().length() > 9 ?
                               poMlpxxxxNew.getJcwh().substring(0, 9) : null);
           */
          PoXT_JWHXXB poXT_JWHXXB = (PoXT_JWHXXB) super.get(PoXT_JWHXXB.class,
              poMlpxxxxNew.getJcwh()); //通过居委会代码得到乡镇街道代码
          if (poXT_JWHXXB == null) {
            throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                       "通过居委会代码无法得到乡镇街道代码,全户变更业务无法完成。", null);
          }
          poMlpxxxxNew.setXzjd(poXT_JWHXXB.getXzjddm());
          //End_修改于闵红斌(2004/11/15 17:25:00)
          poMlpxxxxNew.setPxh(this.generatePXH(poMlpxxxxNew.getMlph(),
                                               poMlpxxxxNew.getMlxz()));
          poMlpxxxxNew.setMlpnbid( (Long) hjxx_mlpxxxxbDAO.getId());
          poMlpxxxxNew.setMlpid(poMlpxxxxNew.getMlpnbid());
          poMlpxxxxNew.setQysj(now);
          poMlpxxxxNew.setJssj(null);
          poMlpxxxxNew.setCdlb(null);
          poMlpxxxxNew.setJdlb(HjConstant.JDLB_YWJL);
          poMlpxxxxNew.setCchjywid(null);
          poMlpxxxxNew.setCjhjywid(hjywid);
          poMlpxxxxNew.setJlbz(PublicConstant.JLBZ_ZX);
          super.create(poMlpxxxxNew);
        }
        else if (!DzConstant.MLPZT_ZC.equals(poMlpxxxxNew.getMlpzt())) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_DATANOTFULL,
                                     "全户变更后的地信息已经存在，但门楼牌状态为非正常状态,全户变更业务无法完成。", null);
        }

        //将户信息置为历史记录
        poHxx.setJssj(now);
        poHxx.setChlb(HjConstant.CHLB_YWZX);
        poHxx.setCchjywid(hjywid);
        poHxx.setJlbz(PublicConstant.JLBZ_LS);
        super.update(poHxx);
        //保存户信息新记录
        PoHJXX_HXXB poHxxNew = new PoHJXX_HXXB();
        BeanUtils.copyProperties(poHxxNew, poHxx);
        poHxxNew.setHhnbid( (Long) hjxx_hxxbDAO.getId());
        poHxxNew.setHlx(voQhbgxx[i].getHlx());
        poHxxNew.setMlpnbid(poMlpxxxxNew.getMlpnbid());
        poHxxNew.setQysj(now);
        poHxxNew.setJssj(null);
        poHxxNew.setChlb(null);
        poHxxNew.setJhlb(HjConstant.JHLB_YWSC);
        poHxxNew.setCchjywid(null);
        poHxxNew.setCjhjywid(hjywid);
        poHxxNew.setJlbz(PublicConstant.JLBZ_ZX);
        super.create(poHxxNew);

        //业务限制处理
        VoRhdxx voRhdxx = new VoRhdxx(null, poHxxNew, poMlpxxxxNew,
                                      BaseContext.getUser());
        VoXtywxz voLimit = XtywqxServiceImpl.VerifyBusinessLimit(PublicConstant.
            GNBH_HJ_QHBGYW, voRhdxx);
        if (voLimit.getLimitflag()) {
          throw new ServiceException(WSErrCode.ERR_SERVICE_BUSSINESSLIMIT,
                                     "全户变更业务受限，受限信息：" + voLimit.getLimitinfo(), null);
        }
        voRhdxx = null;

        //修改户信息上的人信息
        this.changeRXX_HXX(hjywid, poHxx, poHxxNew, poMlpxxxx, poMlpxxxxNew,
                           PublicConstant.GNBH_HJ_QHBGYW, "14", now, voSbjbxx);
        //add by hh 20060323 全户信息需要保存到变更更正表中和住址变动表中
        //保存到变更更正表中
        //  saveQhBggzxx()
        //生成全户变更返回信息
        voQhbgfhxx[i] = new VoQhbgfhxx();
        voQhbgfhxx[i].setHhnbid(poHxxNew.getHhnbid());
        voQhbgfhxx[i].setOld_hhnbid(poHxx.getHhnbid());
        voQhbgfhxx[i].setMlpnbid(poMlpxxxxNew.getMlpnbid());
        voQhbgfhxx[i].setOld_mlpnbid(poMlpxxxx.getMlpnbid());
      }

      ////////////////////////////////////////
      //保存户籍业务流水信息
      this.saveHJYWLSXX(hjywid, PublicConstant.GNBH_HJ_QHBGYW,
                        PublicConstant.HJYWLS_YWLX_QH,
                        voQhbgxx.length, null, now);

      ////////////////////////////
      //生成业务返回信息
      voQhbgywfhxx = new VoQhbgywfhxx();
      voQhbgywfhxx.setHjywid(hjywid);
      voQhbgywfhxx.setVoQhbgfhxx(voQhbgfhxx);

      /////////////////////////////
      //事务提交
    }
    catch (DAOException ex) {
      //事务回滚
      throw ex;
    }
    catch (ServiceException ex) {
      //事务回滚
      throw ex;
    }
    catch (InvocationTargetException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (IllegalAccessException ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }
    catch (Exception ex) {
      //事务回滚
      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
    }

    return voQhbgywfhxx;
  }

	@Override
	public VoMessageRtxx processXxhf(VoMessagexx voMessagexx) throws ServiceException, DAOException {
		VoMessageRtxx voMessageRtxx = new VoMessageRtxx();
		PoMESSAGEXXB[] voMessagexx1 =  new PoMESSAGEXXB[1];
		if (voMessagexx == null) {
		      return null;
		}
		PojoInfo  messageDAO = DAOFactory.createMESSAGEXXBDAO();
		PoMESSAGEXXB poMessage = new PoMESSAGEXXB();
        try {
			BeanUtils.copyProperties(poMessage, voMessagexx);
			poMessage.setJssj(StringUtils.formatDateBy(new java.util.Date(), "yyyyMMddHHmmss"));
			poMessage.setReadflag(HjConstant.READ_WD);
			poMessage.setRemoveflag(HjConstant.REMOVE_WSC);
			poMessage.setMessageid((Long)messageDAO.getId());
			PoMESSAGEXXB pomessageRtn = (PoMESSAGEXXB) super.create(poMessage);
		} catch (DAOException ex) {
		      //事务回滚
		      throw ex;
	    }
	    catch (ServiceException ex) {
	      //事务回滚
	      throw ex;
	    }
	    catch (InvocationTargetException ex) {
	      //事务回滚
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
	    catch (IllegalAccessException ex) {
	      //事务回滚
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
	    catch (Exception ex) {
	      //事务回滚
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
		voMessagexx1[0] = poMessage;
		voMessageRtxx.setVoMessagexx(voMessagexx1);
		return voMessageRtxx;
	}

	@Override
	public VoMessageRtxx processXxfs(VoMessagexx voMessagexx) throws ServiceException, DAOException {
		VoMessageRtxx voMessageRtxx = new VoMessageRtxx();
		PoMESSAGEXXB[] voMessagexx1 =  new PoMESSAGEXXB[1];
		 PojoInfo  messageDAO = DAOFactory.createMESSAGEXXBDAO();
		 PoMESSAGEXXB poMessage = new PoMESSAGEXXB();
         try {
			BeanUtils.copyProperties(poMessage, voMessagexx);
			poMessage.setMessageid((Long)messageDAO.getId());
			poMessage.setJssj(StringUtils.formatDateBy(new java.util.Date(), "yyyyMMddHHmmss"));
			poMessage.setReadflag(HjConstant.READ_WD);
			poMessage.setRemoveflag(HjConstant.REMOVE_WSC);
			poMessage.setMessageid((Long)messageDAO.getId());
			PoMESSAGEXXB pomessageRtn = (PoMESSAGEXXB) super.create(poMessage);
			voMessagexx1[0] = poMessage;
			voMessageRtxx.setVoMessagexx(voMessagexx1);
		} catch (DAOException ex) {
		      //事务回滚
		      throw ex;
	    }
	    catch (ServiceException ex) {
	      //事务回滚
	      throw ex;
	    }
	    catch (InvocationTargetException ex) {
	      //事务回滚
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
	    catch (IllegalAccessException ex) {
	      //事务回滚
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
	    catch (Exception ex) {
	      //事务回滚
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
		
		return voMessageRtxx;
	}

	@Override
	public VoMessagexx updateMessage(Long messageid) {
		PoMESSAGEXXB poMessagexxb = super.get(PoMESSAGEXXB.class, messageid);
		poMessagexxb.setReadflag(HjConstant.READ_YD);
		PoMESSAGEXXB temp = (PoMESSAGEXXB) super.update(poMessagexxb);
		VoMessagexx retVo = new  VoMessagexx();
		  try {
				BeanUtils.copyProperties(retVo, temp);
			} catch (DAOException ex) {
			      //事务回滚
			      throw ex;
		    }
		    catch (ServiceException ex) {
		      //事务回滚
		      throw ex;
		    }
		    catch (InvocationTargetException ex) {
		      //事务回滚
		      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
		    }
		    catch (IllegalAccessException ex) {
		      //事务回滚
		      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
		    }
		    catch (Exception ex) {
		      //事务回滚
		      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
		    }
		return retVo;
	}

	@Override
	public VoMessagexx deleteMessage(Long messageid) {
		PoMESSAGEXXB poMessagexxb = super.get(PoMESSAGEXXB.class, messageid);
		poMessagexxb.setRemoveflag(HjConstant.REMOVE_YSC);
		PoMESSAGEXXB temp = (PoMESSAGEXXB) super.update(poMessagexxb);
		VoMessagexx retVo = new  VoMessagexx();
		  try {
				BeanUtils.copyProperties(retVo, temp);
			} catch (DAOException ex) {
			      //事务回滚
			      throw ex;
		    }
		    catch (ServiceException ex) {
		      //事务回滚
		      throw ex;
		    }
		    catch (InvocationTargetException ex) {
		      //事务回滚
		      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
		    }
		    catch (IllegalAccessException ex) {
		      //事务回滚
		      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
		    }
		    catch (Exception ex) {
		      //事务回滚
		      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
		    }
		return retVo;
	}

	@Override
	public VoMessageRtxx processFsxx(ExtMap<String, Object> params) {
		int type = params.getInteger("type");
		if(type==1) {
			VoMessagexx voMessagexx = new VoMessagexx();
			try {
				BeanUtils.copyProperties(voMessagexx, params);
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
			processXxfs(voMessagexx);
		}else if(type==2) {
			processSyyh(params);
		}
		return null;
	}
	public VoMessageRtxx processSyyh( ExtMap<String, Object> params) throws ServiceException, DAOException {
		VoMessageRtxx voMessageRtxx = new VoMessageRtxx();
		PojoInfo  messageDAO = DAOFactory.createMESSAGEXXBDAO();
		PoMESSAGEXXB[] messagexxb = new PoMESSAGEXXB[1];
		PoMESSAGEXXB poMessage = new PoMESSAGEXXB();
        try {
			BeanUtils.copyProperties(poMessage, params);
			poMessage.setMessageid((Long)messageDAO.getId());
			String dwdm = params.getString("fsdw");
			if(dwdm != null) {//所选单位的用户
				poMessage.setJsdw(dwdm);
			}else {//所有用户
				poMessage.setJsdw("all");
			}
			poMessage.setJssj(StringUtils.formatDateBy(new java.util.Date(), "yyyyMMddHHmmss"));
			poMessage.setReadflag(HjConstant.READ_WD);
			poMessage.setRemoveflag(HjConstant.REMOVE_WSC);
			PoMESSAGEXXB pomessageRtn = (PoMESSAGEXXB) super.create(poMessage);
			messagexxb[0] = poMessage;
		} catch (DAOException ex) {
		      //事务回滚
		      throw ex;
	    }
	    catch (ServiceException ex) {
	      //事务回滚
	      throw ex;
	    }
	    catch (InvocationTargetException ex) {
	      //事务回滚
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
	    catch (IllegalAccessException ex) {
	      //事务回滚
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
	    catch (Exception ex) {
	      //事务回滚
	      throw new ServiceException(WSErrCode.ERR_SERVICE_OTHER, ex);
	    }
		voMessageRtxx.setVoMessagexx(messagexxb);
		return voMessageRtxx;
	}

	@Override
	public List<?> checkUnReadMessage(Long jsryhid, String dwdm) {
		String queryHql = "  from PoMESSAGEXXB where 1=1 and (jsryhid = '"+jsryhid+"' or jsdw='all' or jsdw='"+dwdm+"') and removeflag = '0' and readflag ='0' order by messageid desc ";
		return super.findAllByHQL(queryHql);
//		ExtMap<String, Object> params = new ExtMap<String, Object>();
//		if(jsryhid!=null){
//			params.put("jsryhid", jsryhid);
//		}
//		params.put("pageSize", 9999);
//		params.put("pageIndex", 1);
//		return (List<?>) super.getPageRecords("/conf/segment/common", "queryPoMESSAGEXXB", params);
	}
}
