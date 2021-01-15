package com.hzjc.hz2004.controller;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.color.ColorSpace;
import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.awt.image.RescaleOp;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.imageio.ImageIO;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.controller.BaseController;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.po.PoHJXX_RYZPXXB;
import com.hzjc.hz2004.service.CommonService;
import com.hzjc.hz2004.service.QueryService;
import com.hzjc.hz2004.util.CommonUtil;

@Controller
@RequestMapping("/yw/lsjmsfzgl")
public class LsjmsfzglController extends BaseController{
	@Resource
	private QueryService queryService;
	
	@Resource
	private CommonService commonService;
	
	@RequestMapping(value = { "/lzbl", "" }, method = RequestMethod.GET)
	public String index() {
		
		return "/yw/lsjmsfzgl/lzbl";
	}
	
	@RequestMapping(value = { "/lzdy", "" }, method = RequestMethod.GET)
	public String index2() {
		
		return "/yw/lsjmsfzgl/lzdy";
	}
	
	
	@RequestMapping(value = { "/lztj", "" }, method = RequestMethod.GET)
	public String index3() {
		
		return "/yw/lsjmsfzgl/lztj";
	}
	
	@RequestMapping(value = { "/querylzbl"}, method = RequestMethod.POST)
	public ModelAndView querylzbl() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.querylzbl(params));
	}
	
	@RequestMapping(value = { "/querylzdy"}, method = RequestMethod.POST)
	public ModelAndView querylzdy() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.querylzdy(params));
	}
	
	@RequestMapping(value = { "/querylztj"}, method = RequestMethod.POST)
	public ModelAndView querylztj() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.querylztj(params));
	}
	@RequestMapping(value = { "/checkslxx"}, method = RequestMethod.POST)
	public ModelAndView checkslxx() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.checkslxx(params));
	}	
	@RequestMapping(value = { "/lssfzSlyw"}, method = RequestMethod.POST)
	public ModelAndView lssfzSlyw() {
		ExtMap<String, Object> params = CommonUtil.getRequestParamesObject(BaseContext.getContext().getRequest());
		return toJson(queryService.lssfzSlyw(params));
	}		
	@RequestMapping(value = {"/img/render2/{zpid}/{ld}/{dbd}/{isblack}/{issubmit}"}, method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.OPTIONS})
	@ResponseBody
	public String showImg(@PathVariable("zpid") Long zpid,@PathVariable("ld") String ld,@PathVariable("dbd") String dbd,@PathVariable("isblack") int isblack,@PathVariable("issubmit") String issubmit) {
	    // img为图片的二进制流: 3401000001001467693
		try{
		    PoHJXX_RYZPXXB zp = commonService.getByID(PoHJXX_RYZPXXB.class, zpid);
		   
		    if(zp!=null && zp.getZp()!=null){
			    BaseContext.getContext().getResponse().setContentType("image/png");
			    OutputStream os =  BaseContext.getContext().getResponse().getOutputStream();
			    ByteArrayInputStream in = new ByteArrayInputStream(zp.getZp());    
			    Image image = ImageIO.read(in);
				int srcH = image.getHeight(null);
		    	int srcW = image.getWidth(null);
		    	BufferedImage bufferedImage=new BufferedImage(srcW, srcH,BufferedImage.TYPE_INT_RGB);
		    	BufferedImage originalPic=new BufferedImage(srcW, srcH,BufferedImage.TYPE_INT_RGB);
		    	
		    	bufferedImage.getGraphics().drawImage(image, 0,0, srcW, srcH, null);
	    		bufferedImage.getGraphics().dispose();
	    		float brightness = Float.valueOf(ld); 
	    		float contrast = Float.valueOf(dbd); 
	    	    RescaleOp op = new RescaleOp(brightness, contrast, null); 
		    	if(isblack==1){//黑白
		    		//Graphics2D		  
				    bufferedImage=new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY),null).filter(bufferedImage,originalPic);
				    bufferedImage = op.filter(bufferedImage, null); 
		    	}else{ 
		    	   /* Graphics2D g2d =(Graphics2D) bufferedImage.getGraphics();
		    		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		    		RenderingHints.VALUE_ANTIALIAS_ON);
		    		 g2d.drawImage(bufferedImage, op, 0, 0);*/
		    		
		    	    bufferedImage = op.filter(bufferedImage, null); 
		    	}
		    	/*bufferedImage.getGraphics().drawImage(image, 0,0, srcW, srcH, null);
			    bufferedImage=new ColorConvertOp(ColorSpace.getInstance(ColorSpace.CS_GRAY),null).filter(bufferedImage,null);*/
		    	ByteArrayOutputStream baos = new ByteArrayOutputStream();
		    	ImageIO.write( bufferedImage, "jpg", baos );
		    	baos.flush();
		    	byte[] imageInByte = baos.toByteArray();
		    	
		    	if(issubmit.equals("yes")){
		    		zp.setZp(imageInByte);
		    		commonService.updateObject(zp);
		    	}
		    	
		    	os.write(imageInByte);
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
}
