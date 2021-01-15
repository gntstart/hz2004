package com.hzjc.hz2004.service.impl;

import java.util.ArrayList;

import org.hibernate.Session;
import org.springframework.stereotype.Service;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.ServiceImpl;
import com.hzjc.hz2004.exception.ServiceException;
import com.hzjc.hz2004.service.YzsydqcService;
import com.hzjc.hz2004.util.ExtUtils;

@Service(value = "yzsydqcService")
public class YzsydqcServiceImpl extends ServiceImpl implements YzsydqcService {
	public Page queryKDQHjspyw(ExtMap<String, Object> params) {
		String hsql = "from PoHJSP_HJSPSQB a "
				+ " where a.kdqqy_fkzt='0' and a.spjg='1' and a.kdqqy_qcdqbm is not null "
				+ " and a.djrid=" + super.getUser().getYhid() + " order by a.slrq asc";
		
		Session session = null;
		int pageIndex = params.getInteger(ExtUtils.pageIndex);
		int pageSize = params.getInteger(ExtUtils.pageSize);
		try {
			Page page = super.getPageRecords(hsql, new ArrayList(), pageIndex, pageSize);
			return page;
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServiceException(e);
		} finally {
			if (session != null)
				session.close();
		}
	}
}
