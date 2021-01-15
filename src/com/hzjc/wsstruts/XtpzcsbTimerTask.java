package com.hzjc.wsstruts;

import java.util.List;
import com.hzjc.hz2004.base.Constants;
import com.hzjc.hz2004.service.DictService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.MemcachedUtil;

//CommonController调用
public class XtpzcsbTimerTask extends java.util.TimerTask {
	private boolean isrun = false;

	public XtpzcsbTimerTask() {
	}

	public void run() {
		if (isrun)
			return;

		isrun = true;

		try {
			DictService dictService = (DictService) com.hzjc.hz2004.base.SpringContextHolder.getBean("dictService");
			List<?> list = dictService.querySjpzbUpdate();
			for(Object obj:list){
				Object[] objs = (Object[])obj;
				String pzlb = (String)objs[0];
				String bdsj = (String)objs[1];
				
				String returnUpdateKey = Constants.MEMCACHED_ATTR.DICT_SJPZB_ATTR  + "_maxbdsj_" + pzlb;
				String returnKey = Constants.MEMCACHED_ATTR.DICT_SJPZB_ATTR  + "_" + pzlb;
				String oldvalue = (String)MemcachedUtil.get(returnUpdateKey);
				if(CommonUtil.isNotEmpty(oldvalue)){
					//如果存在，并且更新，那么删除配置缓存
					if(oldvalue.compareTo(bdsj) < 0){
						MemcachedUtil.delete(returnKey);
						MemcachedUtil.delete(returnUpdateKey);
					}
				}
			}
		} catch (Throwable e) {
			e.printStackTrace();
		} finally {
			isrun = false;
		}
	}
}
