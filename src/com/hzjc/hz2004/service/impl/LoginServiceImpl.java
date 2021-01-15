package com.hzjc.hz2004.service.impl;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;

import org.apache.axis.encoding.Base64;
import org.springframework.stereotype.Service;

import com.hzjc.hz2004.base.Constants;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.ServiceImpl;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.base.login.HSession;
import com.hzjc.hz2004.base.login.LoginFilter;
import com.hzjc.hz2004.constant.PublicConstant;
import com.hzjc.hz2004.exception.ServiceException;
import com.hzjc.hz2004.po.PoDW_DY_SET;
import com.hzjc.hz2004.po.PoPERSON_DY_SET;
import com.hzjc.hz2004.po.PoXT_DWXXB;
import com.hzjc.hz2004.po.PoXT_JSXXB;
import com.hzjc.hz2004.po.PoXT_XTCSB;
import com.hzjc.hz2004.po.PoXT_XTGNB;
import com.hzjc.hz2004.po.PoXT_XTGNCDB;
import com.hzjc.hz2004.po.PoXT_XTKZCSB;
import com.hzjc.hz2004.po.PoXT_YHHHXXB;
import com.hzjc.hz2004.po.PoXT_YHSJFWB;
import com.hzjc.hz2004.po.PoXT_YHTXB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.po.PoXT_YWBBMBXXB;
import com.hzjc.hz2004.po.PoXT_ZSBBMBXXB;
import com.hzjc.hz2004.service.LoginService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateHelper;
import com.hzjc.hz2004.util.MemcachedUtil;
import com.hzjc.menu.Menu;
import com.hzjc.menu.MenuData;
import com.hzjc.wsstruts.session.httpsession.SessionUtils;

@Service(value = "loginService")
public class LoginServiceImpl extends ServiceImpl implements LoginService {
	public static String txmsg = null;

	private AuthToken makeAuthToken(PoXT_YHXXB po, String strIp) {
		AuthToken u = new AuthToken();
		u.setUser(po);
		if (po.getGmsfhm() != null) {
			PoXT_YHTXB txb = (PoXT_YHTXB) super.getObject("from PoXT_YHTXB where sfzh=?", new Object[] { po.getGmsfhm() });
			if (txb != null) {
				if (txmsg == null) {
					List<?> obj2 = super.findAllByHQL("from PoXT_XTKZCSB a where a.kzlb='10015'");
					if (obj2 != null && obj2.size() > 0) {
						PoXT_XTKZCSB pz = (PoXT_XTKZCSB) obj2.get(0);
						txmsg = pz.getBz();
					}
				}
			}
		}
		u.setKhmsg(txmsg);
		
		String hql = "from PoXT_YHHHXXB where yhid=" + u.getUser().getYhid();
		PoXT_YHHHXXB log = (PoXT_YHHHXXB)super.getObject(hql, new Object[]{});
		if(log==null){
			//接着处理
			Long id = SessionUtils.generateSession();
			String strSessionId = String.valueOf(id);
			String strAuthInfo = Base64.encode(strSessionId.getBytes());
			
			//PojoInfo dao = DAOFactory.createXT_YHHHXXBDAO();
			log = new PoXT_YHHHXXB();
			Long lYhhhid = (Long) super.getIdBySequence(PoXT_YHHHXXB.class);
			log.setYhhhid(lYhhhid);
			log.setHhid(strAuthInfo);
			log.setYhid(u.getUser().getYhid());
			log.setYhip(strIp);
			super.create(log);
		}
		u.setAuthToken(log.getHhid());
		u.setIp(strIp);
		u.setLastaccesstime(DateHelper.getNowDate(DateHelper.PRINT_DATETIME_STYLE2));
		PoXT_DWXXB dw = super.get(PoXT_DWXXB.class,po.getDwdm());
		if(dw!=null){
			u.setOrganize(dw);
		}
		String perspnHsql = "from PoPERSON_DY_SET where yhid="+ u.getUser().getYhid();
		PoPERSON_DY_SET personSet = (PoPERSON_DY_SET)super.getObject(perspnHsql, new Object[]{});
		if(personSet!=null) {
			u.setPersonDySet(personSet);
		}else {
			PoPERSON_DY_SET morenPersonDySet = new PoPERSON_DY_SET();
			morenPersonDySet.setYhid(u.getUser().getYhid());
			morenPersonDySet.setDyyl_dysz("1");
			morenPersonDySet.setTcdysz_dysz("0");
			morenPersonDySet.setDyzp_cbsz("1");
			morenPersonDySet.setJth_syksz("0");
			morenPersonDySet.setHkbsy_hkbsz("0");
			morenPersonDySet.setHkbbm_hkbsz("0");
			morenPersonDySet.setJthfshksy_hkbsz("0");
			morenPersonDySet.setJthfshky_hkbsz("0");
			morenPersonDySet.setCsyy_hkbsz("0");
			morenPersonDySet.setDyzp_hjzmsz("1");
			morenPersonDySet.setHcyxx_hjzmsz("1");
			morenPersonDySet.setBdyy_hjzmsz("1");
			morenPersonDySet.setBdxx_hjzmsz("1");
			morenPersonDySet.setZxryxx_hjzmsz("1");
			morenPersonDySet.setDydw_hjzmsz("1");
			morenPersonDySet.setDyhh_hjzmsz("0");
			morenPersonDySet.setDyhyzk_hjzmsz("1");
			morenPersonDySet.setDybyqk_hjzmsz("0");
			morenPersonDySet.setDywhcd_hjzmsz("0");
			morenPersonDySet.setYxts("30");
			morenPersonDySet.setDyjmsfzsqb_lsbz("0");
			morenPersonDySet.setSfzlqdxgnr_lsbz("请于10天后到8～9号窗口领取身份证。");
			super.create(morenPersonDySet);
			u.setPersonDySet(morenPersonDySet);
		}
		String dw_dy_hsql = " from PoDW_DY_SET where dwdm="+dw.getDm();
		PoDW_DY_SET dw_dy_set = (PoDW_DY_SET)super.getObject(dw_dy_hsql, new Object[]{});
		if(dw_dy_set!=null) {
			u.setDwDySet(dw_dy_set);
		}else {
			PoDW_DY_SET morenDwDySet = new PoDW_DY_SET();
			morenDwDySet.setDwdm(dw.getDm());
			morenDwDySet.setCbtzd("请您重新办理身份证！");
			morenDwDySet.setKzlzrq("七个工作日");
			morenDwDySet.setMzlzrq("两个月");
			morenDwDySet.setPcslxdh("");
			morenDwDySet.setDkqckh("1");
			morenDwDySet.setMndk("0");
			morenDwDySet.setYwlimit(80);
			morenDwDySet.setXkjdk("0");
			morenDwDySet.setPcsmc("");
			morenDwDySet.setPcsyb("");
			morenDwDySet.setPcsdz("");
			morenDwDySet.setPcsdh("");
			morenDwDySet.setLxdh_ydbz("");
			morenDwDySet.setLzrq_ydbz("两个月");
			super.create(morenDwDySet);
			u.setDwDySet(morenDwDySet);
		}
		String hsql = "from PoXT_YHSJFWB where yhid=?";
		@SuppressWarnings("unchecked")
		List<PoXT_YHSJFWB> list = (List<PoXT_YHSJFWB>)super.getObjectListByHql(hsql, new Object[]{po.getYhid()});
		u.setSjfw(list);
		if(u.getUser().getYhdlm().equalsIgnoreCase("hzadmin")){
			u.setAdmin(true);
    	}else{
			hsql = "from PoXT_JSXXB where jsid in (select jsid from PoXT_YHJSXXB where yhid=?)";
			List<PoXT_JSXXB> jslist = (List<PoXT_JSXXB>)super.getObjectListByHql(hsql, new Object[]{po.getYhid()});
			for(PoXT_JSXXB js:jslist){
				if(js.getJsmc().equals("超级用户")){
					u.setAdmin(true);
					break;
				}
			}
    	}
		String sql="select distinct t.gnid from XT_JSGNQXB t where t.jsid "+
    	" in (select t1.jsid from XT_YHJSXXB t1 where t1.yhid = ?)";
		List gnidList = super.executeSqlQuery(sql, new Object[]{po.getYhid()});
		String gnidBuffer = "";
		List<PoXT_XTGNB> xtgnList = new ArrayList<PoXT_XTGNB>();
		for(int i=0;i<gnidList.size();i++) {
			String gnidtemp = gnidList.get(i)+"";
			gnidBuffer = gnidBuffer+gnidtemp+",";
		}
		if(CommonUtil.isNotEmpty(gnidBuffer)) {
			gnidBuffer = gnidBuffer.substring(0, gnidBuffer.length()-1);
			String xtgnHql = " from PoXT_XTGNB where gnid in ("+gnidBuffer+") ";
			xtgnList = (List<PoXT_XTGNB>)super.getObjectListByHql(xtgnHql, null);
		}
		u.setXtgn(xtgnList);
		return u;
	}
	
	public AuthToken loginAuthToken(String strUserId, String strPassword, String strYzm, String strIp) {
		PoXT_YHXXB po = (PoXT_YHXXB) super.getObject("from PoXT_YHXXB where yhdlm=? or gmsfhm=?",
				new String[] { strUserId, strUserId });
		if (po == null)
			throw new ServiceException("用户不存在！");
		
		////////////////////////////////////////////////////////////////////
		// 在此判断用户是否存在，区分是登录名称错还是口令错
		////////////////////////////////////////////////////////////////////
		if (po.getKhmsg() != null && !po.getKhmsg().equals(""))
			throw new ServiceException(po.getKhmsg());
		
		if (!po.getYhzt().trim().equals(PublicConstant.YHZT_ZC)) {
			throw new ServiceException("该操作用户已经冻结或注销！");
		}
		
		if (!po.getDlkl().equals(strPassword)) {
			throw new ServiceException("用户口令错误！");
		}
		
		return makeAuthToken(po, strIp);
	}
	
	public AuthToken loginAuthToken(String yhdlm, String dqbm) {
		String strIp = LoginFilter.getIpAddr(BaseContext.getContext().getRequest());
		PoXT_YHXXB po = (PoXT_YHXXB) super.getObject("from PoXT_YHXXB where yhdlm = ? and dqbm = ? ",
				new String[] { yhdlm, dqbm });
		if (po == null)
			throw new ServiceException("用户不存在！");

		if (!po.getYhzt().trim().equals(PublicConstant.YHZT_ZC)) {
			throw new ServiceException("该操作用户已经冻结或注销！");
		}
		
		return makeAuthToken(po, strIp);
	}

	@Override
	public Menu getMenu() {
		//权限验证
		Long yhid = BaseContext.getUser().getUser().getYhid();

	    //组织菜单
		String hql = "";
		if(BaseContext.getUser().isAdmin()){
			hql = "from PoXT_XTGNCDB order by cdlx,gncdid";
		}else{
			hql = "from PoXT_XTGNCDB c where c.gncdid in " +
						" (select b.gncdid from PoXT_JSCDQXB b where b.jsid in (select a.jsid from PoXT_YHJSXXB a where a.yhid = " + yhid + ")) order by cdlx, gncdid";
		}
		
	    @SuppressWarnings("unchecked")
		List<PoXT_XTGNCDB> list = (List<PoXT_XTGNCDB>)super.getObjectListByHql(hql);
		Menu root = new Menu();
		root.setExpanded(true);
		root.setLeaf(false);
		root.setText("根菜单");
		root.setChildren(new ArrayList<Menu>());
		
		if(yhid==null){
			return root;
		}
		
		String cdlx = "";
		Menu parent = root;
		
	    for(PoXT_XTGNCDB item:list){
	    	MenuData  data = new MenuData();
	    	data.setCode(item.getGncdid().toString());
	    	data.setUrl(item.getUrl());
	    	
	    	Menu m = new Menu();
	    	m.setData(data);
	    	m.setText(item.getCdmc());
	    	if(item.getCdbz().equals("0")){
	    		//父亲菜单
	    		m.setLeaf(false);
	    		m.setExpanded(false);
	    		m.setChildren(new ArrayList<Menu>());
	    	}else{
	    		//叶子菜单
	    		m.setLeaf(true);
	    	}
	    	
	    	if(!cdlx.equals(item.getCdlx())){
	    		cdlx = item.getCdlx();
	    		
	    		//菜单类型有变化，需要新增一个根类别
	    		Menu rootitem = new Menu();
	    		rootitem.setLeaf(false);
	    		rootitem.setChildren(new ArrayList<Menu>());
	    		rootitem.setExpanded(true);
	    		
	    		if(cdlx.equals("1")){
	    			rootitem.setText("业务");
	    		}else if(cdlx.equals("2")){
	    			rootitem.setText("查询");
	    		}else if(cdlx.equals("3")){
	    			rootitem.setText("管理");
	    		}else if(cdlx.equals("4")){
	    			rootitem.setText("其他功能菜单");
	    		}else {
	    			continue;
	    		}
	    		root.getChildren().add(rootitem);
	    		rootitem.getChildren().add(m);
	    		parent = m;
	    		continue;
	    	}
	    	
	    	if(item.getCdcc().equals("0")){
	    		root.getChildren().get(root.getChildren().size()-1).getChildren().add(m);
	    		parent = m;
	    		continue;
	    	}
	    	
	    	if(parent.getChildren()!=null){
	    		parent.getChildren().add(m);
	    	}
	    }
	    
	    return root;
	}
	
	@Override
	public void logout() {
		if(HSession.getBaseUser() != null){
			String hsql = "from PoXT_YHHHXXB where yhid=" + HSession.getBaseUser().getUser().getYhid();
			super.deleteAll(super.getObjectListByHql(hsql));

			MemcachedUtil.delete(HSession.getUserSessionKey(HSession.getSID()));
			Cookie cookies[] = BaseContext.getContext().getRequest().getCookies();
			 if(cookies!=null){
					for(int i=0;i<cookies.length;i++){
						   if (Constants.COOKIE_SID.equals(cookies[i].getName())) {
								  cookies[i].setMaxAge(0);
								  break;
						   }
					}
			}
		}
		BaseContext.unregisterContext();
	}

	@Override
	public Menu getYwbbmbMenu() {
		String hql = "from PoXT_YWBBMBXXB order by length(ywbblb),ywbblb";	
	    @SuppressWarnings("unchecked")
		List<PoXT_YWBBMBXXB> list = (List<PoXT_YWBBMBXXB>)super.getObjectListByHql(hql);
		Menu root = new Menu();
		root.setExpanded(true);
		root.setLeaf(false);
		root.setText("报表图表资源树");
		root.setChildren(new ArrayList<Menu>());
		
		int a=0,a1=0,a2=0,a3=0,a4=0,a5=0,a6=0,a7=0,a8=0,a9=0;
	    for(PoXT_YWBBMBXXB item:list){
	    	MenuData  data = new MenuData();
	    	data.setCode(item.getYwbbid().toString());//业务报表id
	    	String mbstr=null;
			try {
				if(null!=item.getBbmb()){
					mbstr = new String(item.getBbmb(),"UTF-8");
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	data.setUrl(mbstr);//模板内容
	    	
	    	Menu m = new Menu();
	    	m.setData(data);
	    	m.setText(item.getBbmc());    	
	    	m.setLeaf(true);	    	
	    		
	    		//菜单类型有变化，需要新增一个根类别
	    		Menu rootitem = new Menu();
	    		rootitem.setLeaf(false);
	    		rootitem.setChildren(new ArrayList<Menu>());
	    		rootitem.setExpanded(false);
	    		String bblb=item.getYwbblb();
	    		if(bblb.equals("1")){
	    			rootitem.setText("百岁年龄");
	    			a+=1;
	    		}else if(bblb.equals("2")){
	    			rootitem.setText("常住人口派出所四变月报表");
	    			a1+=1;
	    		}else if(bblb.equals("3")){
	    			rootitem.setText("城镇人口增减情况统计年报表(58表)");
	    			a2+=1;
	    		}else if(bblb.equals("4")){
	    			rootitem.setText("户籍业务变动信息统计表");
	    			a3+=1;
	    		}else if(bblb.equals("5")){
	    			rootitem.setText("人口变动情况");
	    			a4+=1;
	    		}else if(bblb.equals("6")){
	    			rootitem.setText("人口底数");
	    			a5+=1;
	    		}else if(bblb.equals("7")){
	    			rootitem.setText("人口及其变动情况统计报表20栏(57表)");
	    			a6+=1;
	    		}else if(bblb.equals("8")){
	    			rootitem.setText("人口及其变动情况统计报表22栏(原20栏)");
	    			a7+=1;
	    		}else if(bblb.equals("9")){
	    			rootitem.setText("省内迁移情况统计报表");
	    			a8+=1;
	    		}else if(bblb.equals("10")){
	    			rootitem.setText("办证人数统计");
	    			a9+=1;
	    		}
	    		if(bblb.equals("1")&&a>1){
	    			root.getChildren().get(0).getChildren().add(m);
	    		}else if(bblb.equals("2")&&a1>1){
	    			root.getChildren().get(1).getChildren().add(m);
	    		}else if(bblb.equals("3")&&a2>1){
	    			root.getChildren().get(2).getChildren().add(m);
	    		}else if(bblb.equals("4")&&a3>1){
	    			root.getChildren().get(3).getChildren().add(m);
	    		}else if(bblb.equals("5")&&a4>1){
	    			root.getChildren().get(4).getChildren().add(m);
	    		}else if(bblb.equals("6")&&a5>1){
	    			root.getChildren().get(5).getChildren().add(m);
	    		}else if(bblb.equals("7")&&a6>1){
	    			root.getChildren().get(6).getChildren().add(m);
	    		}else if(bblb.equals("8")&&a7>1){
	    			root.getChildren().get(7).getChildren().add(m);
	    		}else if(bblb.equals("9")&&a8>1){	    			
	    			root.getChildren().get(8).getChildren().add(m);
	    		}else if(bblb.equals("10")&&a9>1){	    			
	    			root.getChildren().get(9).getChildren().add(m);
	    		}
	    		else{
	    			root.getChildren().add(rootitem);
	    			rootitem.getChildren().add(m);
	    		}	    			    		    		    		    	
	    	
	    }
	    
	    return root;
	}

	@Override
	public Menu getZsbbmbMenu() {
		String hql = "from PoXT_ZSBBMBXXB order by length(zsbblb),zsbblb";	
	    @SuppressWarnings("unchecked")
		List<PoXT_ZSBBMBXXB> list = (List<PoXT_ZSBBMBXXB>)super.getObjectListByHql(hql);
		
		String hql2="from PoXT_XTCSB  where cslb='9023'  order by  cslb , length(dm), dm";
		List<PoXT_XTCSB> list2 = (List<PoXT_XTCSB>)super.getObjectListByHql(hql2);
		Menu root = new Menu();
		root.setExpanded(true);
		root.setLeaf(false);
		root.setText("报表图表资源树");
		root.setChildren(new ArrayList<Menu>());
		
		int a=0,a1=0,a2=0,a3=0,a4=0,a5=0,a6=0,a7=0,a8=0,a9=0;
		String str="";
	    for(PoXT_ZSBBMBXXB item:list){
	    	MenuData  data = new MenuData();
	    	data.setCode(item.getZsbbmbid().toString());//业务报表id
	    	String mbstr=null;
			try {
				if(null!=item.getBbmb()){
					mbstr = new String(item.getBbmb(),"UTF-8");
				}
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	data.setUrl(mbstr);//模板内容
	    	
	    	Menu m = new Menu();
	    	m.setData(data);
	    	m.setText(item.getBbmbmc());    	
	    	m.setLeaf(true);	    	
	    		
	    		//菜单类型有变化，需要新增一个根类别
	    		Menu rootitem = new Menu();
	    		rootitem.setLeaf(false);
	    		rootitem.setChildren(new ArrayList<Menu>());
	    		rootitem.setExpanded(false);
	    		String bblb=item.getZsbblb();
	    		if(bblb.equals("1")){
	    			rootitem.setText("百岁年龄");
	    			str+="百岁年龄"+",";
	    			a+=1;
	    		}else if(bblb.equals("2")){
	    			rootitem.setText("常住人口派出所四变月报表");
	    			str+="常住人口派出所四变月报表"+",";
	    			a1+=1;
	    		}else if(bblb.equals("3")){
	    			rootitem.setText("非农业人口增减情况统计报表");
	    			str+="非农业人口增减情况统计报表"+",";
	    			a2+=1;
	    		}else if(bblb.equals("4")){
	    			rootitem.setText("户籍业务变动信息统计表");
	    			str+="户籍业务变动信息统计表"+",";
	    			a3+=1;
	    		}else if(bblb.equals("5")){
	    			rootitem.setText("人口变动情况");
	    			str+="人口变动情况"+",";
	    			a4+=1;
	    		}else if(bblb.equals("6")){
	    			rootitem.setText("人口底数");
	    			str+="人口底数"+",";
	    			a5+=1;
	    		}else if(bblb.equals("7")){
	    			rootitem.setText("人口及其变动情况统计报表20栏(57表)");
	    			str+="人口及其变动情况统计报表20栏(57表)"+",";
	    			a6+=1;
	    		}else if(bblb.equals("8")){
	    			rootitem.setText("人口及其变动情况统计报表20栏(原20栏)");
	    			str+="人口及其变动情况统计报表20栏(原20栏)"+",";
	    			a7+=1;
	    		}else if(bblb.equals("9")){
	    			rootitem.setText("省内迁移情况统计报表");
	    			str+="省内迁移情况统计报表"+",";
	    			a8+=1;
	    		}else if(bblb.equals("10")){
	    			rootitem.setText("办证人数统计");
	    			str+="办证人数统计"+",";
	    			a9+=1;
	    		}
	    		if(bblb.equals("1")&&a>1){
	    			root.getChildren().get(0).getChildren().add(m);
	    		}else if(bblb.equals("2")&&a1>1){
	    			root.getChildren().get(1).getChildren().add(m);
	    		}else if(bblb.equals("3")&&a2>1){
	    			root.getChildren().get(2).getChildren().add(m);
	    		}else if(bblb.equals("4")&&a3>1){
	    			root.getChildren().get(3).getChildren().add(m);
	    		}else if(bblb.equals("5")&&a4>1){
	    			root.getChildren().get(4).getChildren().add(m);
	    		}else if(bblb.equals("6")&&a5>1){
	    			root.getChildren().get(5).getChildren().add(m);
	    		}else if(bblb.equals("7")&&a6>1){
	    			root.getChildren().get(6).getChildren().add(m);
	    		}else if(bblb.equals("8")&&a7>1){
	    			root.getChildren().get(7).getChildren().add(m);
	    		}else if(bblb.equals("9")&&a8>1){	    			
	    			root.getChildren().get(8).getChildren().add(m);
	    		}else if(bblb.equals("10")&&a9>1){	    			
	    			root.getChildren().get(9).getChildren().add(m);
	    		}
	    		else{
	    			root.getChildren().add(rootitem);
	    			rootitem.getChildren().add(m);
	    		}	    			    		    		    		    	
	    	
	    }
	    
	    for(PoXT_XTCSB obj:list2){
	    	if(str.contains(obj.getMc())){
	  
	    	}else{
	    		Menu rootitem = new Menu();
	    		rootitem.setLeaf(false);
	    		rootitem.setChildren(new ArrayList<Menu>());
	    		rootitem.setExpanded(false);
	    		rootitem.setText(obj.getMc());
	    		root.getChildren().add(rootitem);
	    	}
	    }
	    
	    return root;
	}

	
}
