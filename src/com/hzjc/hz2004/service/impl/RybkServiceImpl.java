package com.hzjc.hz2004.service.impl;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.ServiceImpl;
import com.hzjc.hz2004.base.login.AuthToken;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.po.PoHJYW_RKBKXXB;
import com.hzjc.hz2004.po.PoHJYW_RKBKXXB_GJ;
import com.hzjc.hz2004.service.RybkService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.DateHelper;
import com.hzjc.wsstruts.exception.ServiceException;

@Service(value = "rybkService")
public class RybkServiceImpl extends ServiceImpl implements RybkService{
	@Override
	public Page queryRybk(ExtMap<String, Object> params) {
		Page page = super.getPageRecords("/conf/segment/common", "queryRybk", params);
		return page;
	}

	@Override
	public Page queryRybkgjxx(ExtMap<String, Object> params) {
		Page page = super.getPageRecords("/conf/segment/common", "queryRybkgjxx", params);
		return page;
	}

	@Override
	public void delRybk(ExtMap<String, Object> params) {
		String idString = params.getString("ids");
		if(CommonUtil.isNotEmpty(idString)) {
			String[] id = idString.split(",");
			for(String ids:id) {
				if(CommonUtil.isEmpty(ids))
					continue;
				
				PoHJYW_RKBKXXB ry = super.get(PoHJYW_RKBKXXB.class, ids);
				if(ry!=null) {
					super.delete(ry);
				}
			}
		}
	}

	@Override
	public PoHJYW_RKBKXXB saveRybk(PoHJYW_RKBKXXB bkry) {
		if(bkry!=null) {
			if(CommonUtil.isEmpty(bkry.getYwid())) {
				PoHJYW_RKBKXXB ry1 = (PoHJYW_RKBKXXB)super.getObject("from PoHJYW_RKBKXXB where gmsfhm=?", new Object[] {bkry.getGmsfhm()});
				if(ry1!=null) {
					throw new ServiceException("此身份证号码已经布控！");
				}
				bkry.setYwid(null);
				bkry.setBksj(DateHelper.formateDate("yyyyMMddHHmmss"));
				super.create(bkry);
			}else {
				PoHJYW_RKBKXXB old = super.get(PoHJYW_RKBKXXB.class, bkry.getYwid());
				if(old!=null) {
					if(!old.getGmsfhm().equals(bkry.getGmsfhm())) {
						throw new ServiceException("修改布控人员不能修改身份证号码！");
					}
					old.setBklx(bkry.getBklx());
					old.setBklxmc(bkry.getBklxmc());
					old.setBktx(bkry.getBktx());
					old.setXm(bkry.getXm());
					old.setBksj(DateHelper.formateDate("yyyyMMddHHmmss"));
					super.update(old);
				}
			}
		}
		
		return bkry;
	}
	
	public String saveRybkgjToString(String hjywlx, String hjywlxmc, String gmsfhm) {
		 List<PoHJYW_RKBKXXB> list = this.saveRybkgj(hjywlx, hjywlxmc, gmsfhm);
		 String str = "";
		 if(list!=null) {
			 for(int i=0;i<list.size();i++) {
				 PoHJYW_RKBKXXB tt = list.get(i);
				 if(!str.equals("")) {
					 str += "\n\r";
				 }
				 str += "身份证：" + tt.getGmsfhm() + "," + tt.getBktx();
			 }
		 }
		 
		 return str;
	}
	
	public List<PoHJYW_RKBKXXB> saveRybkgj(String hjywlx, String hjywlxmc, String gmsfhm){
		List<PoHJYW_RKBKXXB> list = new ArrayList<PoHJYW_RKBKXXB>();
		
		if(CommonUtil.isNotEmpty(gmsfhm)) {
			AuthToken user = BaseContext.getBaseUser();
			String[] sfz = gmsfhm.split(",");
			for(String sfzString:sfz) {
				PoHJYW_RKBKXXB ryHjyw_RKBKXXB = (PoHJYW_RKBKXXB)super.getObject("from PoHJYW_RKBKXXB where gmsfhm=?", new Object[] {sfzString});
				if(ryHjyw_RKBKXXB!=null) {
					list.add(ryHjyw_RKBKXXB);
					PoHJYW_RKBKXXB_GJ gj = new PoHJYW_RKBKXXB_GJ();
					gj.setXm(ryHjyw_RKBKXXB.getXm());
					gj.setGmsfhm(ryHjyw_RKBKXXB.getGmsfhm());
					gj.setBklx(ryHjyw_RKBKXXB.getBklx());
					gj.setBklxmc(ryHjyw_RKBKXXB.getBklxmc());
					gj.setBkmjxm(ryHjyw_RKBKXXB.getBkmjxm());
					gj.setBksj(ryHjyw_RKBKXXB.getBksj());
					gj.setBktx(ryHjyw_RKBKXXB.getBktx());
					gj.setHjywlx(hjywlx);
					gj.setHjywlxmc(hjywlxmc);
					gj.setHjywmj(user.getYhdlm());
					gj.setHjywmjxm(user.getUser().getYhxm());
					gj.setHjywsj(DateHelper.formateDate("yyyyMMddHHmmss"));
					super.create(gj);
				}
			}
		}
		return list;
	}
}
