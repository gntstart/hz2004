package com.hzjc.hz2004.service;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.bean.TreeNode;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.po.PoHJXX_MLPXXXXB;
import com.hzjc.hz2004.po.PoHJXX_ZPLSB;
import com.hzjc.hz2004.po.PoXT_DWXXB;
import com.hzjc.hz2004.po.PoXT_JSXXB;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.vo.VoDzzjywfhxx;
import com.hzjc.hz2004.vo.VoFxjsfxxb;
import com.hzjc.hz2004.vo.VoMessageRtxx;
import com.hzjc.hz2004.vo.VoQhtzywfhxx;
import com.hzjc.hz2004.vo.VoYdzZjslywfhxx;
import com.hzjc.hz2004.vo.VoZjdbzfywfhxx;
import com.hzjc.hz2004.vo.VoZjlqffywfhxx;
import com.hzjc.hz2004.vo.VoZjshfhxx;
import com.hzjc.hz2004.vo.VoZjshywfhxx;
import com.hzjc.hz2004.vo.VoZjsjywfhxx;
import com.hzjc.hz2004.vo.VoZjslywfhxx;
import com.hzjc.hz2004.vo.VoZjslzfywfhxx;
import com.hzjc.menu.Menu;
import com.hzjc.wsstruts.exception.DAOException;
import com.hzjc.wsstruts.exception.ServiceException;

/**
 * 查询类
 * <p>Title: hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) GNT 2004</p>
 * <p>Company: </p>
 * @author bini_min@hotmail.com
 * @version 1.0
 */
public interface QueryService {
	/**拍照日志信息查询
	 * 
	 * @param params
	 * @return
	 */
	public Page queryPzxx(ExtMap<String,Object> params);
	
	/**查询业务报表类别
	 * 
	 * @param params
	 * @return
	 */
	public Page querybblb(ExtMap<String,Object> params);
	
	/**查询制式报表类别
	 * 
	 * @param params
	 * @return
	 */
	public Page queryzsbblb(ExtMap<String,Object> params);
	
	public Page querybblbxx(ExtMap<String,Object> params);
	
	public Page querycldxx(ExtMap<String,Object> params);
	
	public Page getzpxx(ExtMap<String,Object> params);
	
	public Page queryTjbbsjb(ExtMap<String,Object> params);
	public List queryDssc(ExtMap<String,Object> params);
	
	public Page querycbddy(ExtMap<String,Object> params);
	
	public Page querylzbl(ExtMap<String,Object> params);
	
	public Page querylzdy(ExtMap<String,Object> params);
	
	public Page querylztj(ExtMap<String,Object> params);
	
	public Page queryyhdghz(ExtMap<String,Object> params);
	
	public Page queryzjfj(ExtMap<String,Object> params);
	
	public Page queryzsbblbxx(ExtMap<String,Object> params);
	
	public Page queryzjcx(ExtMap<String,Object> params);
		
	/**判断派出所是否存在
	 * 
	 * @param params
	 * @return
	 */
	public PoXT_DWXXB getpcsxx(String dm);
	
	/**获取当前登陆用户pcs信息
	 * 
	 * @param params
	 * @return
	 */
	public List querypcs(ExtMap<String,Object> params);
	
	public Page querypcs_ws(ExtMap<String,Object> params);
	
	/**
	 * 获取尾数段列表
	 */
	public Page gewslist(ExtMap<String,Object> params);
	
	public Map getwsd(String dwdm);
	
	/**业务报表生成
	 * 
	 * @param params
	 * @return
	 */
	public List querybbsc(ExtMap<String,Object> params);
	
	public Page delbblbxx(ExtMap<String,Object> params);
	
	/**删除制式模板信息
	 * 
	 * @param params
	 * @return
	 */
	public int delzsbbmbxx(ExtMap<String,Object> params);
	
	/**新增业务报表模板
	 * 
	 * @param params
	 * @return
	 */
	public List addbblbxx(ExtMap<String,Object> params);
	
	/**新增制式报表模板
	 * 
	 * @param params
	 * @return
	 */
	public List addzsbbmbxx(ExtMap<String,Object> params);
	
	public List updatebblbxx(ExtMap<String,Object> params);
	
	/**修改制式报表模板
	 * 
	 * @param params
	 * @return
	 */
	public List updatezsbbmbxx(ExtMap<String,Object> params);
	
	/**新增制式报表
	 * 
	 * @param params
	 * @return
	 */
	public List addzsbbxx(ExtMap<String,Object> params);
	
	
	public List updatezsbbxx(ExtMap<String,Object> params);
	
	public int delzsbbxx(ExtMap<String,Object> params);
	
	/**查询制式报表
	 * 
	 * @param params
	 * @return
	 */
	public Page queryzsbbxx(ExtMap<String,Object> params);
	
	public Page getbbxx(ExtMap<String,Object> params);
	
	/**已处理拍照日志信息查询
	 * 
	 * @param params
	 * @return
	 */
	public Page queryPzxxycl(ExtMap<String,Object> params);
	
	public Page deletePzlogxx(ExtMap<String,Object> params);
	
	public Page updateSfzxx(ExtMap<String,Object> params);
	
	
	/**临证查询
	 * 
	 * @param params
	 * @return
	 */
	public Page querySfzxxcx(ExtMap<String,Object> params);
	
	/**追加住址查询
	 * 
	 * @param params
	 * @return
	 */
	public Page queryzjAdresscx(ExtMap<String,Object> params);
	
	/**操作员信息查询
	 * 
	 * @param params
	 * @return
	 */
	public Page queryczyxxcx(ExtMap<String,Object> params);
	
    /**住址追加日志信息查询
     * 
     * @param params
     * @return
     */
	public Page queryrzxxcx(ExtMap<String,Object> params);
	
	/**逃犯比对信息查询
	 * 
	 * @param params
	 * @return
	 */
	public Page querytfbdcx(ExtMap<String,Object> params);
	
	/**读卡信息日志查询
	 * 
	 * @param params
	 * @return
	 */
	public Page querydkxxrzcx(ExtMap<String,Object> params);
	
	/**写卡信息日志查询
	 * 
	 * @param params
	 * @return
	 */
	public Page queryxkxxrzcx(ExtMap<String,Object> params);	
	/**
	 *	现住地查询
	 * @param strHQL
	 * @param voPage
	 * @return
	 * @throws ServiceException
	 * @throws DAOException
	 */
	public Page queryRhflxx(ExtMap<String,Object> params) ;
	
	/**
	 *	同户人员查询
	 * @param strHQL
	 * @param voPage
	 * @return
	 * @throws ServiceException
	 * @throws DAOException
	 */
	public Page queryThry(ExtMap<String,Object> params) ;
	
	/**
	 *	门楼牌信息查询
	 * @param strHQL
	 * @param voPage
	 * @return
	 * @throws ServiceException
	 * @throws DAOException
	 */
	public Page queryMlpxxcx(ExtMap<String,Object> params) ;
	
	/**
	 *	详细信息查询
	 * @param params
	 * @return
	 * @throws ServiceException
	 * @throws DAOException
	 */
	public Page queryXxxx(ExtMap<String,Object> params) ;
	
	/**
	 *	迁出处理查询
	 * @param params
	 * @return
	 * @throws ServiceException
	 * @throws DAOException
	 */
	public Page queryQcclxx(ExtMap<String,Object> params) ;
	
	/**
	 *	常住人口查询
	 * @param strHQL
	 * @param voPage
	 * @return
	 * @throws ServiceException
	 * @throws DAOException
	 */
	public Page queryRkxx(ExtMap<String,Object> params) ;
	
	/**
	 *	户地信息查询
	 * @param strHQL
	 * @param voPage
	 * @return
	 * @throws ServiceException
	 * @throws DAOException
	 */
	public Page queryHdxx(ExtMap<String,Object> params) ;
	
	/**
	 *	迁出注销信息查询
	 * @param strHQL
	 * @param voPage
	 * @return
	 * @throws ServiceException
	 * @throws DAOException
	 */
	public Page queryQcxx(ExtMap<String,Object> params) ;
	
	/**
	 * 迁入审批
	 * @param params
	 * @return
	 */
	public Page queryQrsp(ExtMap<String,Object> params) ;
	public Page queryQrspzb(ExtMap<String,Object> params) ;
	public Page queryQrsplc(ExtMap<String,Object> params) ;
	/**
	 * 变更审批
	 * @param params
	 * @return
	 */
	public Page queryBgsp(ExtMap<String, Object> params);
	public Page queryBgspzb(ExtMap<String, Object> params);
	public Page queryBgsplc(ExtMap<String,Object> params) ;
	public Page queryVoHxxHqFhxx(ExtMap<String,Object> params);
	/**
	 * 户别变更审批
	 * @param params
	 * @return
	 */
	public Page queryHbbgsp(ExtMap<String, Object> params);
	public Page queryHbbgsplc(ExtMap<String, Object> params);
	/**
	 * 户籍补录审批查询
	 * @param params
	 * @return
	 */
	public Page queryHjblsp(ExtMap<String, Object> params);
	public Page queryHjblsplc(ExtMap<String, Object> params);
	
	/**
	 * 户籍删除审批查询
	 * @param params
	 * @return
	 */
	public Page queryHjscsp(ExtMap<String, Object> params);
//	public Page queryHjscsp(ExtMap<String, Object> params);
	public Page queryHjscsplc(ExtMap<String, Object> params);
	/**
	 * 户信息查询
	 * @param params
	 * @return
	 */
	public Page getHxx(ExtMap<String, Object> params);

	/**
	 * 门楼牌信息
	 * @param params
	 * @return
	 */
	public Page getMlpxx(ExtMap<String, Object> params);

	/**
	 *	迁入登记信息查询
	 * @param params
	 * @return
	 * @throws ServiceException
	 * @throws DAOException
	 */
	public Page queryQrxx(ExtMap<String, Object> params);

	/**
	 * 出生登记信息查询
	 * @param params
	 * @return
	 */
	public Page queryCsdjxx(ExtMap<String, Object> params);

	/**
	 * 死亡注销信息查询
	 * @param params
	 * @return
	 */
	public Page getSwzxxx(ExtMap<String, Object> params);

	/**
	 * 住址变动信息查询
	 * @param params
	 * @return
	 */
	public Page getZzbdxx(ExtMap<String, Object> params);

	/**
	 * 变更更正信息查询
	 * @param params
	 * @return
	 */
	public Page getBggzxx(ExtMap<String, Object> params);

	/**
	 * 户别变更信息查询
	 * @param params
	 * @return
	 */
	public Page getHbbgxx(ExtMap<String, Object> params);

	/**
	 * 户籍补录信息查询
	 * @param params
	 * @return
	 */
	public Page getHjblxx(ExtMap<String, Object> params);

	/**
	 * 户籍删除信息查询
	 * @param params
	 * @return
	 */
	public Page getHjscxx(ExtMap<String, Object> params);

	/**
	 * 户成员变动信息查询
	 * @param params
	 * @return
	 */
	public Page getHcybdxx(ExtMap<String, Object> params);

	/**
	 * 变动情况查询（四变）
	 * @param params
	 * @return
	 */
	public Page getBdqkxx(ExtMap<String, Object> params);

	/**
	 * 打印信息查询
	 * @param params
	 * @return
	 */
	public Page getDyxx(ExtMap<String, Object> params);

	/**
	 * 二代证受理信息传查询
	 * @param params
	 * @return
	 */
	public Page getEdzslxx(ExtMap<String, Object> params);
	
	/**受理日志查询信息
	 * 
	 * @param params
	 * @return
	 */
	public Page getEdzslrzxx(ExtMap<String, Object> params);

	/**
	 * 收交信息查询
	 * @param params
	 * @return
	 */
	public Page getSjxx(ExtMap<String, Object> params);

	/**
	 * 验收信息查询
	 * @param params
	 * @return
	 */
	public Page getYsxx(ExtMap<String, Object> params);

	/**
	 * 领证发放信息查询
	 * @param params
	 * @return
	 */
	public Page getLqffxx(ExtMap<String, Object> params);

	/**
	 * 挂失信息查询
	 * @param params
	 * @return
	 */
	public Page getGsxx(ExtMap<String, Object> params);

	/**
	 * 投递信息查询
	 * @param params
	 * @return
	 */
	public Page getTdxx(ExtMap<String, Object> params);

	/**
	 * @param params
	 * @return
	 */
	public Page getZzfkxx(ExtMap<String, Object> params);

	/**
	 * 质量反馈查询
	 * @param params
	 * @return
	 */
	public Page getZlfkxx(ExtMap<String, Object> params);

	/**
	 * 分检结果查询
	 * @param params
	 * @return
	 */
	public Page getFjjgxx(ExtMap<String, Object> params);

	/**
	 * 分发接收信息查询
	 * @param params
	 * @return
	 */
	public Page getFfjsxx(ExtMap<String, Object> params);

	/**
	 * 身份证信息查询
	 * @param params
	 * @return
	 */
	public Page getSfzxx(ExtMap<String, Object> params);

	/**
	 * 重号信息查询
	 * @param params
	 * @return
	 */
	public Page getChxx(ExtMap<String, Object> params);

	/**
	 * 身份号分配信息查询
	 * @param params
	 * @return
	 */
	public Page getSfhfpxx(ExtMap<String, Object> params);

	/**
	 * 迁移证查询
	 * @param params
	 * @return
	 */
	public Page getQyzxx(ExtMap<String, Object> params);

	/**
	 * 一代证受理信息查询
	 * 准迁证查询
	 * @param params
	 * @return
	 */
	public Page getZqzxx(ExtMap<String, Object> params);

	/**
	 * 一代证受理信息查询
	 * @param params
	 * @return
	 */
	public Page getYdzslxx(ExtMap<String, Object> params);

	/**
	 * 删除证件信息
	 * 2019/3/15
	 * zjm
	 * @param params
	 * @return
	 */
	public void delBbzslid(ExtMap<String, Object> params); 
	
	/**删除受理信息
	 * 
	 * @param params
	 * @return
	 */
	public Page delYdzslxx(ExtMap<String, Object> params);

	/**
	 * 审核信息查询
	 * @param params
	 * @return
	 */
	public Page getShxx(ExtMap<String, Object> params);
	
	/**
	 * 销毁信息查询
	 * @param params
	 * @return
	 */
	public Page getXhxx(ExtMap<String, Object> params);
	
	/**
	 * 打印模板查询
	 * @param params
	 * @return
	 */
	public Page queryDymbcl(ExtMap<String, Object> params);
	
	public Page getQhxxFromHhid(ExtMap<String, Object> params);

	/**
	 * 一户一本查询
	 * 2018/10/16
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getYhybList(ExtMap<String, Object> params);
	/**
	 * 常表成批查询
	 * 2018/10/29
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getCbcpList(ExtMap<String, Object> params);
	/**
	 * 受理信息查询
	 * 2018/10/29
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getSlxxtjList(ExtMap<String, Object> params);
	/**
	 * 数据字典信息查询
	 * 2018/11/02
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getSjzdInfo(ExtMap<String, Object> params);
	/**
	 * 数据字典信息修改
	 * 2018/11/02
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifySizd(ExtMap<String, Object> params);

	/**
	 * 数据字典删除
	 * 2018/11/06
	 * zjm
	 * @param params
	 * @return
	 */
	public int delSizd(ExtMap<String, Object> params);
	/**
	 * 数据字典新增
	 * 2018/11/06
	 * zjm
	 * @param params
	 * @return
	 */
	public List addSizd(ExtMap<String, Object> params); 
	/**
	 * 系统参数维护
	 * 2018/11/06
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getXtcswhInfo(ExtMap<String, Object> params);
	/**
	 * 根据cslb查询系统参数
	 * 2018/11/06
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getXtcsInfoByCslb(ExtMap<String, Object> params);
	/**
	 * 查询行政区
	 * 2018/11/07
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getXzqhInfo(ExtMap<String, Object> params);
	
	/**
	 * 根据dm修改行政区
	 * 2018/11/07
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyXzqhDm(ExtMap<String, Object> params); 
	/**
	 * 根据dm增加行政区划
	 * 2018/11/07
	 * zjm
	 * @param params
	 * @return
	 */
	public List addXzqhDm(ExtMap<String, Object> params);
	/**
	 * 根据dm删除行政区划
	 * 2018/11/07
	 * zjm 
	 * @param params
	 * @return
	 */
	public List delXzqhDm(ExtMap<String, Object> params);
	/**
	 * 根据dm恢复行政区划
	 * 2018/12/03
	 * zjm 
	 * @param params
	 * @return
	 */
	public List resumeXzqhDm(ExtMap<String, Object> params);
	
	/**
	 * 户籍审批信息 变更审批查询
	 * 2018/11/08
	 * zjm 
	 * @param params
	 * @return
	 */
	public Page getHjspBggzsp(ExtMap<String, Object> params);
	/**
	 * 户籍审批信息 户籍删除审批查询
	 * 2018/11/08
	 * zjm 
	 * @param params
	 * @return
	 */
	public Page getHjspHjsc(ExtMap<String, Object> params);
	/**
	 * 户籍审批信息 户籍补录审批查询
	 * 2018/11/08
	 * zjm 
	 * @param params
	 * @return
	 */
	public Page getHjspHjbl(ExtMap<String, Object> params);
	/**
	 * 户籍审批信息 户别变更审批查询
	 * 2018/11/08
	 * zjm 
	 * @param params
	 * @return
	 */
	public Page getHjspHbbg(ExtMap<String, Object> params);
	/**
	 * 户籍审批信息 迁入审批审批查询
	 * 2018/11/08
	 * zjm 
	 * @param params
	 * @return
	 */
	public Page getHjspQrsp(ExtMap<String, Object> params);
	/**
	 * 查询单位信息
	 * 2018/11/09
	 * zjm 
	 * @param params
	 * @return
	 */
	public Page getDwxxInfo(ExtMap<String, Object> params);
	/**
	 * 根据dm修改单位信息
	 * 2018/11/09
	 * zjm 
	 * @param params
	 * @return
	 */
	public void modifyDwDm(MultipartHttpServletRequest logoFile,ExtMap<String, Object> params);
	/**
	 * 增加单位信息
	 * 2018/11/09
	 * zjm 
	 * @param logoFile 
	 * @param params
	 * @return
	 */
	public void addDwDm(MultipartHttpServletRequest logoFile, ExtMap<String, Object> params);
	/**
	 * 根据dm删除单位信息
	 * 2018/11/09
	 * zjm 
	 * @param params
	 * @return
	 */
	public List delDwDm(ExtMap<String, Object> params);
	/**
	 * 恢复单位
	 * 2018/12/03
	 * zjm 
	 * @param params
	 * @return
	 */
	public List resumeDwDm(ExtMap<String, Object> params);
	/**
	 * 查询警务责任区信息
	 * 2018/11/20
	 * zjm 
	 * @param params
	 * @return
	 */
	
	public Page getJwzrqxxInfo(ExtMap<String, Object> params);
	/**
	 * 修改警务责任区信息
	 * 2018/11/20
	 * zjm 
	 * @param params
	 * @return
	 */
	public List modifyJwzrq(ExtMap<String, Object> params);
	/**
	 * 新增警务责任区信息
	 * 2018/11/20
	 * zjm
	 * @param params
	 * @return
	 */
	public List addJwzrq(ExtMap<String, Object> params);
	/**
	 * 删除警务责任区信息
	 * 2018/11/20
	 * zjm
	 * @param params
	 * @return
	 */
	public List delJwzrq(ExtMap<String, Object> params);
	/**
	 * 恢复警务责任区信息
	 * 2018/12/03
	 * zjm
	 * @param params
	 * @return
	 */
	public List resumeJwzrq(ExtMap<String, Object> params);
	/**
	 * 查询乡镇街道信息
	 * 2018/11/21
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getXzjdInfo(ExtMap<String, Object> params);
	/**
	 * 修改乡镇街道信息
	 * 2018/11/21
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyXzjd(ExtMap<String, Object> params);
	/**
	 * 增加乡镇街道信息
	 * 2018/11/21
	 * zjm
	 * @param params
	 * @return
	 */
	public List addXzjd(ExtMap<String, Object> params);
	/**
	 * 删除乡镇街道信息
	 * 2018/11/21
	 * zjm
	 * @param params
	 * @return
	 */
	public List delXzjd(ExtMap<String, Object> params);
	/**
	 * @param params
	 * @return
	 */
	public List resumeXzjd(ExtMap<String, Object> params);
	/**
	 * 恢复乡镇街道信息
	 * 2018/12/203
	 * zjm
	 * 查询居委会信息
	 * 2018/11/22
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getJwhxxInfo(ExtMap<String, Object> params);
	/**
	 * 修改居委会信息
	 * 2018/11/22
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyJwh(ExtMap<String, Object> params);
	/**
	 * 增加居委会信息
	 * 2018/11/22
	 * zjm
	 * @param params
	 * @return
	 */
	public List addJwh(ExtMap<String, Object> params);
	/**
	 * 删除居委会信息
	 * 2018/11/22
	 * zjm
	 * @param params
	 * @return
	 */
	public List delJwh(ExtMap<String, Object> params);
	/**
	 * 删除居委会信息
	 * 2018/12/03
	 * zjm
	 * @param params
	 * @return
	 */
	public List resumeJwh(ExtMap<String, Object> params);
	/**
	 *  根据居委会dm查询下面的街路巷信息
	 * 2018/11/23
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getJlxByJwhdm(ExtMap<String, Object> params);

	/**
	 * 修改街路巷居委会信息表
	 * 2018/11/27
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyjlx(ExtMap<String, Object> params);

	/**
	 * 街路巷居委会信息表增加街路巷
	 * 2018/11/27
	 * zjm
	 * @param params
	 * @return
	 */
	public List addJlx(ExtMap<String, Object> params);

	/**
	 * 查询街路巷信息
	 * 2018/11/27
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getJlxxxInfo(ExtMap<String, Object> params);
	/**
	 * 增加街路巷信息
	 * 2018/11/27
	 * zjm
	 * @param params
	 * @return
	 */
	public List addJlxxx(ExtMap<String, Object> params);
	/**
	 * 增加街路巷信息
	 * 2018/11/27
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyJlxxx(ExtMap<String, Object> params);

	/**
	 * 修改街路巷信息
	 * 2018/11/27
	 * zjm
	 * @param params
	 * @return
	 */
	public List delJlxxx(ExtMap<String, Object> params);
	/**
	 * 恢复街路巷信息
	 * 2018/12/03
	 * zjm
	 * @param params
	 * @return
	 */
	public List resumeJlxxx(ExtMap<String, Object> params);

	/**
	 * 查询街路巷居委会对照表信息
	 * 2018/11/28
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getJlxjwhdzInfo(ExtMap<String, Object> params);

	/**
	 * 修改街路巷居委会对照表信息
	 * 2018/11/28
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyJlxjwhdz(ExtMap<String, Object> params);

	/**
	 * 增加街路巷居委会对照表信息
	 * 2018/11/28
	 * zjm
	 * @param params
	 * @return
	 */
	public List addJlxjwhdz(ExtMap<String, Object> params);

	/**
	 * 删除街路巷居委会对照表信息
	 * 2018/11/28
	 * zjm
	 * @param params
	 * @return
	 */
	public List delJlxjwhdz(ExtMap<String, Object> params);

	/**
	 * 恢复街路巷居委会对照表信息
	 * 2018/12/03
	 * zjm
	 * @param params
	 * @return
	 */
	public List resumeJlxjwhdz(ExtMap<String, Object> params);

	/**
	 * 查询控制参数列表
	 * 2018/12/04
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getKzcsInfo(ExtMap<String, Object> params);

	/**
	 * 修改控制参数
	 * 2018/12/04
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyKzcs(ExtMap<String, Object> params);

	/**
	 * 查询审批模板数据
	 * 2018/12/04
	 * zjm
	 * @param params
	 * @return
	 */
	public List getSpmbInfo(ExtMap<String, Object> params);

	/**
	 * 查询审批动作数据
	 * 2018/12/05
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getSpdzInfo(ExtMap<String, Object> params);

	/**
	 * 查询模板审批流程数据
	 * 2018/12/05
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getMbsplcInfo(ExtMap<String, Object> params);

	/**
	 * 增加审批模板
	 * 2018/12/07
	 * zjm
	 * @param params
	 * @return
	 */
	public List addSpmb(ExtMap<String, Object> params);

	/**
	 * 删除审批模板
	 * 2018/12/07
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifySpmb(ExtMap<String, Object> params);

	/**
	 * 恢复审批模板
	 * 2018/12/07
	 * zjm
	 * @param params
	 * @return
	 */
	public List resumeSpmb(ExtMap<String, Object> params);

	/**
	 * 禁用审批模板
	 * 2018/12/07
	 * zjm
	 * @param params
	 * @return
	 */
	public List forbitSpmb(ExtMap<String, Object> params);

	/**
	 * 新增审批动作
	 * 2018/12/10
	 * zjm
	 * @param params
	 * @return
	 */
	public List addSpdz(ExtMap<String, Object> params);

	/**
	 * 修改审批动作
	 * 2018/12/10
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifySpdz(ExtMap<String, Object> params);

	/**
	 * 删除审批动作
	 * 2018/12/10
	 * zjm
	 * @param params
	 * @return
	 */
	public List deleteSpzd(ExtMap<String, Object> params);

	/**
	 * 恢复审批动作
	 * 2018/12/10
	 * zjm
	 * @param params
	 * @return
	 */
	public List resumeSpdz(ExtMap<String, Object> params);

	/**
	 * 增加审批动作流节点
	 * 2018/12/11
	 * zjm
	 * @param params
	 * @return
	 */
	public List addSpdzl(ExtMap<String, Object> params);

	/**
	 * 删除审批动作流节点
	 * 2018/12/11
	 * zjm
	 * @param params
	 * @return
	 */
	public int removeSpdzl(ExtMap<String, Object> params);

	/**
	 * 删除模板下的所有动流
	 * 2018/12/11
	 * zjm
	 * @param params
	 * @return
	 */
	public int removeAllSpdzl(ExtMap<String, Object> params);

	/**
	 * 公共允许IP设置查询
	 * 2018/12/12
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getGgyxipsetInfo(ExtMap<String, Object> params);

	/**
	 * 增加公共允许IP
	 * 2018/12/12
	 * zjm
	 * @param params
	 * @return
	 */
	public List addGgyxip(ExtMap<String, Object> params);

	/**
	 * 修改公共允许IP
	 * 2018/12/12
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyGgyxip(ExtMap<String, Object> params);

	/**
	 * 删除公共允许IP
	 * 2018/12/12
	 * zjm
	 * @param params
	 * @return
	 */
	public int delGgyxip(ExtMap<String, Object> params);

	/**
	 * 查询本市市区维护信息
	 * 2018/12/13
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getBssqwhInfo(ExtMap<String, Object> params); 
	/**
	 * 增加本市市区维护信息
	 * 2018/12/13
	 * zjm
	 * @param params
	 * @return
	 */
	public List addBssqwh(ExtMap<String, Object> params);

	/**
	 * 修改本市市区维护信息
	 * 2018/12/13
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyBssqwh(ExtMap<String, Object> params);

	/**
	 * 删除本市市区维护信息
	 * 2018/12/13
	 * zjm
	 * @param params
	 * @return
	 */
	public List delBssqwh(ExtMap<String, Object> params);

	/**
	 * 恢复本市市区维护信息
	 * 2018/12/13
	 * zjm
	 * @param params
	 * @return
	 */
	public List resumeBssqwh(ExtMap<String, Object> params);

	/**
	 * 查询迁移设置维护信息
	 * 2018/12/13
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getQyszwhInfo(ExtMap<String, Object> params);

	/**
	 * 增加迁移设置维护信息
	 * 2018/12/13
	 * zjm
	 * @param params
	 * @return
	 */
	public List addQyszwh(ExtMap<String, Object> params);

	/**
	 * 修改迁移设置维护信息
	 * 2018/12/13
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyQyszwh(ExtMap<String, Object> params);

	/**
	 * 删除迁移设置维护信息
	 * 2018/12/13
	 * zjm
	 * @param params
	 * @return
	 */
	public List delQyszwh(ExtMap<String, Object> params);

	/**
	 * 修复迁移设置维护信息
	 * 2018/12/13
	 * zjm
	 * @param params
	 * @return
	 */
	public List resumeQyszwh(ExtMap<String, Object> params);

	/**
	 * 查询户号序列维护信息
	 * 2018/12/13
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getHhxlwhInfo(ExtMap<String, Object> params);

	/**
	 * 增加户号序列维护信息
	 * 2018/12/13
	 * zjm
	 * @param params
	 * @return
	 */
	public List addHhxlwh(ExtMap<String, Object> params);

	/**
	 * 修改户号序列维护信息
	 * 2018/12/13
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyHhxlwh(ExtMap<String, Object> params);

	/**
	 * 删除户号序列维护信息
	 * 2018/12/13
	 * zjm
	 * @param params
	 * @return
	 */
	public int delHhxlwh(ExtMap<String, Object> params);

	/**
	 * 查询受理号序列维护信息
	 * 2018/12/14
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getSlhxlbwhInfo(ExtMap<String, Object> params);

	/**
	 * 增加受理号序列维护信息
	 * 2018/12/14
	 * zjm
	 * @param params
	 * @return
	 */
	public List addSlhxlbwh(ExtMap<String, Object> params);

	/**
	 * 修改受理号序列维护信息
	 * 2018/12/14
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifySlhxlbwh(ExtMap<String, Object> params);

	/**
	 * 删除受理号序列维护信息
	 * 2018/12/14
	 * zjm
	 * @param params
	 * @return
	 */
	public int delSlhxlbwh(ExtMap<String, Object> params);

	/**
	 * 查询历年尾数段维护信息
	 * 2018/12/17
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getLnwsdwhInfo(ExtMap<String, Object> params);

	/**
	 * 新增历年尾数段维护信息
	 * 2018/12/17
	 * zjm
	 * @param params
	 * @return
	 */
	public List addLnwsdwhInfo(ExtMap<String, Object> params);

	/**
	 * 修改历年尾数段维护信息
	 * 2018/12/17
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyLnwsdwhInfo(ExtMap<String, Object> params);

	/**
	 * 删除历年尾数段维护信息
	 * 2018/12/17
	 * zjm
	 * @param params
	 * @return
	 */
	public int delLnwsdwhInfo(ExtMap<String, Object> params);

	/**
	 * 查询变更打印控制信息
	 * 2018/12/17
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getBgdykzInfo(ExtMap<String, Object> params);

	/**
	 * 修改变更打印控制信息
	 * 2018/12/17
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyBgdykzInfo(ExtMap<String, Object> params);

	/**
	 * 查询业务办理限制信息
	 * 2018/12/18
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getYwblxzwhInfo(ExtMap<String, Object> params);

	/**
	 * 增加业务办理限制信息
	 * 2018/12/18
	 * zjm
	 * @param params
	 * @return
	 */
	public List addYwblxzwhInfo(ExtMap<String, Object> params);

	/**
	 * 修改业务办理限制信息
	 * 2018/12/18
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyYwblxzwhInfo(ExtMap<String, Object> params);

	/**
	 * 删除业务办理限制信息
	 * 2018/12/18
	 * zjm
	 * @param params
	 * @return
	 */
	public List delYwblxzwhInfo(ExtMap<String, Object> params);

	/**
	 * 查业务办理限制类型
	 * 2018/12/18
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getXtywbllxInfo(ExtMap<String, Object> params);

	/**
	 * 恢复业务办理限制类型
	 * 2018/12/19
	 * zjm
	 * @param params
	 * @return
	 */
	public List resumeYwblxzwhInfo(ExtMap<String, Object> params);

	/**
	 * 查询业务权限数据
	 * 2018/12/20
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getYwqxInfo(ExtMap<String, Object> params);

	/**
	 * 修改业务权限数据
	 * 2018/12/20
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyYwqxInfo(ExtMap<String, Object> params);

	/**
	 * 查询用户数据
	 * 2018/12/20
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getUserInfo(ExtMap<String, Object> params);

	/**
	 * 查询用户数据范围数据
	 * 2018/12/20
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getYhsjfwInfo(ExtMap<String, Object> params);

	/**
	 * 增加用户数据范围数据
	 * 2018/12/20
	 * zjm
	 * @param params
	 * @return
	 */
	public List addYhsjfwInfo(ExtMap<String, Object> params);

	/**
	 * 删除用户数据范围数据
	 * 2018/12/20
	 * zjm
	 * @param params
	 * @return
	 */
	public int delYhsjfwInfo(ExtMap<String, Object> params);

	/**
	 * 查询用户等同权限数据
	 * 2018/12/20
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getYhdtqxInfo(ExtMap<String, Object> params);

	/**
	 * 增加用户等同权限数据
	 * 2018/12/20
	 * zjm
	 * @param params
	 * @return
	 */
	public List addYhdtqxInfo(ExtMap<String, Object> params);

	/**
	 * 修改用户等同权限数据
	 * 2018/12/20
	 * zjm
	 * @param params
	 * @return
	 */
	public int delYhdtqxInfo(ExtMap<String, Object> params);

	/**
	 * 查询在线用户数量
	 * 2018/12/29
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryZxyh(ExtMap<String, Object> params);

	/**
	 * 停止用户的会话
	 * 2019/1/2
	 * zjm
	 * @param params
	 * @return
	 */
	public int delYhhhid(ExtMap<String, Object> params);

	/**
	 * 业务信息查询
	 * 2019/1/4
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getYwxx(ExtMap<String, Object> params);

	/**
	 * 角色查询
	 * 2019/1/7
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryJs(ExtMap<String, Object> params);

	/**
	 * 增加角色
	 * 2019/1/7
	 * zjm
	 * @param params
	 * @return
	 */
	public List addJs(ExtMap<String, Object> params);

	/**
	 * 修改角色
	 * 2019/1/7
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyJs(ExtMap<String, Object> params);

	/**
	 * 删除角色
	 * 2019/1/7
	 * zjm
	 * @param params
	 * @return
	 */
	public int delJs(ExtMap<String, Object> params);

	/**
	 * 查询用户
	 * 2019/1/8
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryYh(ExtMap<String, Object> params);

	/**
	 * 增加用户
	 * 2019/1/8
	 * zjm
	 * @param params
	 * @return
	 */
	public List addYh(ExtMap<String, Object> params);

	/**
	 * 修改用户
	 * 2019/1/8
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyYh(ExtMap<String, Object> params);

	/**
	 * 删除用户
	 * 2019/1/8
	 * zjm
	 * @param params
	 * @return
	 */
	public int delYh(ExtMap<String, Object> params);

	/**
	 * 查询用户动作权限信息
	 * 2019/1/18
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getXtyhdzqxxx(ExtMap<String, Object> params);

	/**
	 * 查询用户角色信息
	 * 2019/1/18
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getXtyhjsxx(ExtMap<String, Object> params);

	/**
	 * 查询用户数据范围信息
	 * 2019/1/18
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getXtyhsjfwxx(ExtMap<String, Object> params);

	/**
	 * 查询用户等同权限信息
	 * 2019/1/18
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getXtyhdtqxxx(ExtMap<String, Object> params);

	/**
	 * 用户动作权限增加
	 * 2019/1/18
	 * zjm
	 * @param params
	 * @return
	 */
	public Page addYhdzqx(ExtMap<String, Object> params);

	/**
	 * 用户动作权限删除
	 * 2019/1/18
	 * zjm
	 * @param params
	 * @return
	 */
	public int delYhdzqxInfo(ExtMap<String, Object> params);

	/**
	 * 区县证件接收信息查询
	 * 2019/1/25
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getQxzjjsInfo(ExtMap<String, Object> params);

	/**
	 * 区县证件接收信息保存
	 * 2019/1/25
	 * zjm
	 * @param params
	 * @return
	 */
	public List saveQxzjjsInfo(ExtMap<String, Object> params);

	/**
	 * 区县证件发放信息保存
	 * 2019/1/25
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getQxzjffInfo(ExtMap<String, Object> params);

	/**
	 * 区县证件接收信息保存
	 * 2019/1/25
	 * zjm
	 * @param params
	 * @return
	 */
	public List saveQxzjffInfo(ExtMap<String, Object> params);

	/**
	 * 地市证件分发查询
	 * 2019/1/28
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getDszjffInfo(ExtMap<String, Object> params);

	/**
	 * 地市证件分发信息保存
	 * 2019/1/25
	 * zjm
	 * @param params
	 * @return
	 */
	public List saveDszjffInfo(ExtMap<String, Object> params);

	/**
	 * 打包量控制信息查询
	 * 2019/1/29
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getDblkzxxInfo(ExtMap<String, Object> params);

	/**
	 * 打包量控制信息修改
	 * 2019/1/30
	 * zjm
	 * @param params
	 * @return
	 */
	public List modifyDblkzxx(ExtMap<String, Object> params);

	/**
	 * 打包量控制信息增加
	 * 2019/1/30
	 * zjm
	 * @param params
	 * @return
	 */
	public List addDblkzxx(ExtMap<String, Object> params);

	/**
	 * 打包量控制信息删除
	 * 2019/1/30
	 * zjm
	 * @param params
	 * @return
	 */
	public int delDblkzxx(ExtMap<String, Object> params);

	/**
	 * 异地办证受理信息增加
	 * 2019/1/30
	 * zjm
	 * @param params
	 * @return
	 */
	public Long addYdbzslxx(ExtMap<String, Object> params);

	/**
	 * 异地办证信息查询
	 * 2019/2/2
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryYdbzxx(ExtMap<String, Object> params);

	/**
	 * 异地办证信息作废
	 * 2019/2/12
	 * zjm
	 * @param params
	 * @return
	 */
	public List xxzf(ExtMap<String, Object> params);

	/**
	 * 异地办证领证信息查询
	 * 2019/2/13
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryYdbzlzxx(ExtMap<String, Object> params);

	/**异地办证领证信息领证
	 * 2019/2/13
	 * zjm
	 * @param params
	 * @return
	 */
	public List saveLz(ExtMap<String, Object> params);

	/**
	 * 二代证受理信息查询
	 * 2019/2/14
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryZjslxx(ExtMap<String, Object> params); 
	/**
	 * 二代证证件办理
	 * 2019/2/14
	 * zjm
	 * @param params
	 * @return
	 */
	public VoZjslywfhxx saveZjsl(ExtMap<String, Object> params);

	/**
	 * 证件验收受理信息查询
	 * 2019/2/18
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryslxx(ExtMap<String, Object> params);

	/**
	 * 查询历次受理信息
	 * 2019/2/21
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryLsZjslxx(ExtMap<String, Object> params);

	/**
	 * 查询证件接收信息
	 * 2019/3/4
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryZjjsxx(ExtMap<String, Object> params);

	/**
	 * 证件接收信息保存
	 * 2019/3/4
	 * zjm
	 * @param params
	 * @return
	 */
	public List saveZjjsxx(ExtMap<String, Object> params);

	/**
	 * 证件收交信息保存
	 * 2019/3/4
	 * zjm
	 * @param params
	 * @return
	 */
	public VoZjsjywfhxx zjsjsave(ExtMap<String, Object> params);

	/**
	 * 证件领取信息查询
	 * 2019/3/5
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryZjlqxx(ExtMap<String, Object> params);

	/**
	 * 证件领取信息保存
	 * 2019/3/6
	 * zjm
	 * @param params
	 * @return
	 */
	public VoZjlqffywfhxx saveZjlqxx(ExtMap<String, Object> params);

	/**
	 * 打印设置保存
	 * 2019/03/26
	 * zjm
	 * @param params
	 */
	public void saveDysz(ExtMap<String, Object> params);

	/**
	 * 证件办理保存
	 * 2019/04/01
	 * zjm
	 * @param params
	 * @return
	 */
	public VoYdzZjslywfhxx saveZjbl(ExtMap<String, Object> params);

	/**
	 * 受理单打印前检查
	 * 2019/04/03
	 * zjm
	 * @param params
	 * @return
	 */
	public Page checkslxx(ExtMap<String, Object> params);

	/**
	 * 临时身份证受理业务
	 * 2019/04/04
	 * zjm
	 * @param params
	 * @return
	 */
	public List lssfzSlyw(ExtMap<String, Object> params);

	/**
	 * 发送信息
	 * 2019/04/09
	 * zjm
	 * @param params
	 * @return
	 */
	public VoMessageRtxx fsxx(ExtMap<String, Object> params);

	/**
	 * 总信息查询
	 * 2019/04/09
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryXx(ExtMap<String, Object> params);

	/**
	 * 销毁信息查询
	 * 2019/04/15
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryZjxhxx(ExtMap<String, Object> params);

	/**
	 * 销毁信息保存
	 * 2019/04/15
	 * zjm
	 * @param params
	 * @return
	 */
	public List saveZjxhxx(ExtMap<String, Object> params);

	/**
	 * 地址维护信息查询
	 * 2019/04/15
	 * zjm
	 * @param params
	 * @return
	 */
	public Page dzwhxxcx(ExtMap<String, Object> params);

	/**
	 * 居委会下的街路巷
	 * 2019/04/17
	 * zjm
	 * @param params
	 * @return
	 */
	public List searchJlxByJwh(ExtMap<String, Object> params);

	/**
	 * 地址维护插入新记录
	 * 2019/04/18
	 * zjm
	 * @param params
	 * @return
	 */
	public VoDzzjywfhxx insertDzwhxx(ExtMap<String, Object> params);

	/**
	 * 地址维护更新记录
	 * 2019/04/18
	 * zjm
	 * @param params
	 * @return
	 */
	public List updateDzwhxx(ExtMap<String, Object> params);

	/**
	 * 地址调整全部变更
	 * 2019/04/22
	 * zjm
	 * @param queryMlpPo1
	 * @param bgMlpPo
	 * @param bgFlagarr
	 * @return
	 */
	public VoQhtzywfhxx processQhtzywQbgg(ExtMap<String, Object> queryMlpPo1, PoHJXX_MLPXXXXB bgMlpPo, int[] bgFlagarr);

	/**
	 * 要调整的省市县区下的居委会
	 * 2019/04/22
	 * zjm
	 * @param params
	 * @return
	 */
	public List queryJwhByssxq(ExtMap<String, Object> params);

	/**
	 * 二代证打印设置
	 * 2019/04/25
	 * zjm
	 * @param params
	 */
	public void saveEdzDysz(ExtMap<String, Object> params);

	/**
	 * 二代证受理历史信息查询
	 * 2019/04/26
	 * zjm
	 * @param params
	 * @return
	 */
	public Page getEdzsllsxxlist(ExtMap<String, Object> params); 
	/**
	 * 二代证受理历史信息详情查询
	 * 2019/04/26
	 * zjm
	 * @param params
	 * @return
	 */
	public List getEdzsllsxxInfo(ExtMap<String, Object> params);

	/**
	 * 证件受理作废业务
	 * 2019/04/26
	 * zjm
	 * @param params
	 * @return
	 */
	public VoZjslzfywfhxx zjslzf(ExtMap<String, Object> params);

	/**
	 * 证件打包作废业务
	 * 2019/04/26
	 * zjm
	 * @param params
	 * @return
	 */
	public VoZjdbzfywfhxx zjdbzf(ExtMap<String, Object> params);

	/**
	 * 证件挂失查询
	 * 2019/04/28
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryzjgscx(ExtMap<String, Object> params);

	/**
	 * 分局审核信息查询
	 * 2019/04/29
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryFjshxx(ExtMap<String, Object> params);

	/**
	 * HJXX_ZPLSB照片查询
	 * 2019/04/29
	 * zjm
	 * @param zpid
	 * @return
	 */
	public PoHJXX_ZPLSB queryZplsb(Long zpid);

	/**
	 * 历史受理查询
	 * 2019/05/05
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryLsshxx(ExtMap<String, Object> params);

	/**
	 * 分局合格不合格审核
	 * 2019/05/06
	 * zjm
	 * @param params
	 * @return
	 */
	public VoZjshywfhxx processZjshyw(ExtMap<String, Object> params);

	/**
	 * 分局签发信息查询
	 * 2019/05/07
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryFjqfxx(ExtMap<String, Object> params);

	/**
	 * 分局签发操作
	 * 2019/05/07
	 * zjm
	 * @param params
	 * @return
	 */
	public VoZjshfhxx[] processZjqfyw(ExtMap<String, Object> params);

	/**
	 * 查询echarts画图所需数据
	 * 2019/05/13
	 * zjm
	 * @param params
	 * @return
	 */
	public List queryEchartsData(ExtMap<String, Object> params);
	/**
	 * 市局审核信息查询
	 * 2019/06/20
	 * zjm
	 * @param params
	 * @return
	 */
	public Page querySjshxx(ExtMap<String, Object> params);

	/**
	 * 直系亲属关系查询
	 * 2019/06/28
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryZxqsgx(ExtMap<String, Object> params);

	/**
	 * 市局验收受理信息查询
	 * 2019/08/06
	 * zjm
	 * @param params
	 * @return
	 */
	public Page querySjslxx(ExtMap<String, Object> params);

	/**
	 * 分局验收受理信息查询
	 * 2019/08/06
	 * zjm
	 * @param params
	 * @return
	 */
	public Page queryFjslxx(ExtMap<String, Object> params);

	/**
	 * 查询echarts画图所需数据
	 * 2019/09/11
	 * zjm
	 * @param params
	 * @return
	 */
	public List querySsxqqrsffbbData(ExtMap<String, Object> params);

	/**
	 * 未登记户口查询
	 * zjm
	 * 20191008
	 * @param params
	 * @return
	 */
	public Page getWdjhkcx(ExtMap<String, Object> params);

	/**
	 * 分户查询
	 * zjm
	 * 20191008
	 * @param params
	 * @return
	 */
	public Page getFhcx(ExtMap<String, Object> params);

	/**
	 * 公民是否同一人的协助核查查询
	 * zjm
	 * 20191009
	 * @param params
	 * @return
	 */
	public Page queryGmsftyrxzhc(ExtMap<String, Object> params);

	/**
	 * 查询解锁锁定历史记录
	 * add by zjm 
	 * 20191021
	 * @param params
	 * @return
	 */
	public Page queryJssdls(ExtMap<String, Object> params);
	public String queryProjectTree(ExtMap<String, Object> params);
	public List<TreeNode> queryYsqJSTree(ExtMap<String, Object> params);
	public void saveJsFunc(ExtMap<String, Object> params);
	public void deleteJs(ExtMap<String, Object> params);
	public PoXT_JSXXB saveJs(ExtMap<String, Object> params);
	public Page queryFxjsktj(ExtMap<String, Object> params);
	public Page queryFxjsktjInfo(ExtMap<String, Object> params);
	public List queryQcclxxDaochu(ExtMap<String, Object> params);
	public List queryXxxxDaochu(ExtMap<String, Object> params);
	public void saveGgUrlTrans(ExtMap<String, Object> params);
	public Menu queryRzsTree(ExtMap<String, Object> params);
	/**
	 * add by zjm 
	 * 查询集体土地户信息
	 * @param params
	 * @return
	 */
	public Page queryJttdHxx(ExtMap<String, Object> params);
	/**
	 * add by zjm 20200415
	 * 更新户信息表的土地标志字段
	 * @param params
	 */
	public void updateJttdbz1(ExtMap<String, Object> params);
	/**
	 * add by zjm 20200422
	 * 批量更新户信息表的土地标志字段
	 * @param params
	 */
	public void updateJttdbz2(ExtMap<String, Object> params);

	/**
	 * add by zjm 20200423 区域冻结人员列表
	 * @param params
	 * @return
	 */
	public Page queryQydjRy(ExtMap<String, Object> params);

	/**
	 * add by zjm 20200423 区域冻结字段更新
	 * @param params
	 * @return
	 */
	public void updateDjzt1(ExtMap<String, Object> params);

	/**
	 * add by zjm 20200423 区域冻结批量更新
	 * @param params
	 * @return
	 */
	public void updateDjzt2(ExtMap<String, Object> params);

	/**
	 * add by zjm 20200424 查询居委会会章和前面列表
	 * @param params
	 * @return
	 */
	public Page queryjwhhzhqm(ExtMap<String, Object> params);

	public Page queryZmXx(ExtMap<String, Object> params);

	/**
	 * 集体土地户信息
	 * @param params
	 * @return
	 */
	public Page queryJttdHxxDaochu(ExtMap<String, Object> params);

	/**
	 * add by zjm  20200814
	 * 户籍证明律师用
	 * @param params
	 * @return
	 */
	public Page getHjzmls(ExtMap<String, Object> params);
	/**
	 * add by zjm  20201225
	 * 实缴款附件管理
	 * @param params
	 * @return
	 */
	public Page querySjkfjgl(ExtMap<String, Object> params);
	public Page querySjkxx(ExtMap<String, Object> params);

	/**
	 * 更新收费信息表审核状态
	 * @param params
	 */
	public void updateSfxxbSfzt(ExtMap<String, Object> params);

	/**
	 * 查询待审批缴费
	 * @param params
	 * @return
	 */
	public Page queryFxjshqf(ExtMap<String, Object> params);

}