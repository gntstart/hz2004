package com.hzjc.hz2004.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.axis.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.constant.PublicConstant;
import com.hzjc.hz2004.po.PoHJXX_CZRKJBXXB;
import com.hzjc.hz2004.po.PoHJXX_RYZPXXB;
import com.hzjc.hz2004.po.PoHJXX_ZPLSB;
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.HjService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.vo.VoHJXX_RYZPXXB;
import com.hzjc.wsstruts.exception.ServiceException;
import com.hzjc.wsstruts.exception.WSErrCode;
import com.hzjc.wsstruts.vo.VoPage;
import com.hzjc.wsstruts.vo.VoQueryResult;

@Controller
@RequestMapping("/yw/fjgl/fjsh")
public class FjshController extends BaseController{
	
	@Autowired
	private QueryService queryService;
	@Autowired
	private CommonService commonService;
	@Autowired
	private HjService hjService;	
	@RequestMapping(value = { "/", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/yw/fjgl/fjsh";
	}
	@RequestMapping(value = { "/queryFjshxx" }, method = RequestMethod.POST)
	public ModelAndView queryFjshxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.queryFjshxx(params));
	}
	@RequestMapping(value = {"/img/render/{zpid}"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
	@ResponseBody
	public String showImg(@PathVariable("zpid") Long zpid) {
	    // img为图片的二进制流: 3401000001001467693
		try{
			PoHJXX_ZPLSB zp = queryService.queryZplsb(zpid);
		    if(zp!=null && zp.getZp()!=null){
			    BaseContext.getContext().getResponse().setContentType("image/png");
			    OutputStream os =  BaseContext.getContext().getResponse().getOutputStream();
			    os.write(zp.getZp());
			    os.flush();
			    os.close();
		    }
		    return "success";
		}catch(Exception e){
			
		}finally{
			;
		}
		
		return "error";
	}
	@RequestMapping(value = { "/queryZp" }, method = RequestMethod.POST)
	public ModelAndView queryZp() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		String[] rynbidArray = params.getString("rynbid").split(",");
		String[] zpidArray = params.getString("zpid").split(",");
		String[] gmsfhmArray = params.getString("gmsfhm").split(",");
		String[] xmArray = params.getString("xm").split(",");
		List<VoQueryResult> voQueryResultList= new ArrayList<VoQueryResult>();
		List<VoHJXX_RYZPXXB> poHJXX_RYZPXXBResultList= new ArrayList<VoHJXX_RYZPXXB>();
		if(rynbidArray.length==zpidArray.length) {
			for(int i = 0;i<zpidArray.length;i++) {
				String strHQL = " zpid ='"+zpidArray[i]+"'";
				List<VoHJXX_RYZPXXB> poHJXX_RYZPXXBList= hjService.queryRyzpxx(strHQL, new VoPage()).getPagelist();
				VoHJXX_RYZPXXB poHJXX_RYZPXXB = new VoHJXX_RYZPXXB();
				if(poHJXX_RYZPXXBList!=null&&poHJXX_RYZPXXBList.size()>0) {
					poHJXX_RYZPXXB =(VoHJXX_RYZPXXB)poHJXX_RYZPXXBList.get(0) ;
					poHJXX_RYZPXXBResultList.add(poHJXX_RYZPXXB);
				}else {
					strHQL = "from PoHJXX_CZRKJBXXB where rynbid='" + rynbidArray[i] + "' ";
					List listczrk = commonService.queryAll(strHQL);
					VoHJXX_RYZPXXB poHJXX_RYZPXXBTemp = new VoHJXX_RYZPXXB();
					if(listczrk.size()>0) {
						PoHJXX_CZRKJBXXB czrk = (PoHJXX_CZRKJBXXB) listczrk.get(0);
						poHJXX_RYZPXXBTemp.setGmsfhm(czrk.getGmsfhm());
						poHJXX_RYZPXXBTemp.setXm(czrk.getXm());
						poHJXX_RYZPXXBResultList.add(poHJXX_RYZPXXBTemp);
					}else {
						poHJXX_RYZPXXBTemp.setGmsfhm(gmsfhmArray[i]);
						poHJXX_RYZPXXBTemp.setXm(xmArray[i]);
						poHJXX_RYZPXXBResultList.add(poHJXX_RYZPXXBTemp);
					}
					//PoHJXX_CZRKJBXXB czrk = super.super.get(PoHJXX_CZRKJBXXB.class,Long.parseLong(rynbidArray[i]));
					//tempVoQueryResult.se
				}
			}
		}else {
			throw new ServiceException(WSErrCode.ERR_SERVICE_DATAINVALATE,
                    "数据数目不对。", null);
		}
//		String strHQL = " HJXX_RYZPXXB.ryid in ( " + params.getString("ryid")+ " )";
//		if(!StringUtils.isEmpty(params.getString("zpid"))) {
//			strHQL +=" and zpid not in ("+params.getString("zpid")+ " )";
//		}
//		strHQL += " order by HJXX_RYZPXXB.lrrq desc,HJXX_RYZPXXB.zpid";
		
//		return toJson(hjService.queryRyzpxx(strHQL, new VoPage()).getPagelist());
		return toJson(poHJXX_RYZPXXBResultList);
	}
	//queryLsshxx
	@RequestMapping(value = { "/queryLsshxx" }, method = RequestMethod.POST)
	public ModelAndView queryLsshxx() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.queryLsshxx(params));
	}
	@RequestMapping(value = { "/processZjshyw" }, method = RequestMethod.POST)
	public ModelAndView processZjshyw() {
		
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.processZjshyw(params));
	}
}
