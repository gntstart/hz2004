package com.hzjc.hz2004.service.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.stereotype.Service;
import com.hzjc.hz2004.base.Constants;
import com.hzjc.hz2004.base.ExtMap;
import com.hzjc.hz2004.base.Page;
import com.hzjc.hz2004.base.ServiceImpl;
import com.hzjc.hz2004.base.bean.Code;
import com.hzjc.hz2004.base.bean.ExtField;
import com.hzjc.hz2004.base.bean.SjpzBean;
import com.hzjc.hz2004.base.bean.SysCode;
import com.hzjc.hz2004.base.bean.TreeNode;
import com.hzjc.hz2004.base.dict.DictData;
import com.hzjc.hz2004.base.login.BaseContext;
import com.hzjc.hz2004.base.sqltemplate.Config;
import com.hzjc.hz2004.base.sqltemplate.sql.SqlParam;
import com.hzjc.hz2004.base.sqltemplate.sql.SqlParse;
import com.hzjc.hz2004.exception.ServiceException;
import com.hzjc.hz2004.po.PoXT_DWXXB;
import com.hzjc.hz2004.po.PoXT_JLXXXB;
import com.hzjc.hz2004.po.PoXT_JWHXXB;
import com.hzjc.hz2004.po.PoXT_JWZRQXXB;
import com.hzjc.hz2004.po.PoXT_SJPZB;
import com.hzjc.hz2004.po.PoXT_SPDZB;
import com.hzjc.hz2004.po.PoXT_XTCSB;
import com.hzjc.hz2004.po.PoXT_XTKZCSB;
import com.hzjc.hz2004.po.PoXT_XZJDXXB;
import com.hzjc.hz2004.po.PoXT_XZQHB;
import com.hzjc.hz2004.po.PoXT_YHXXB;
import com.hzjc.hz2004.service.DictService;
import com.hzjc.hz2004.util.CommonUtil;
import com.hzjc.hz2004.util.MemcachedUtil;
import com.hzjc.hz2004.util.ObjectUtil;
import com.hzjc.hz2004.util.QxglUtils;
import com.hzjc.hz2004.vo.VoXT_SJPZB;
import com.hzjc.util.StringUtils;

@Service(value = "dictService")
public class DictServiceImpl  extends ServiceImpl implements DictService {
	//地区编码，没有实际含义
	private static final String DQBM = "DEFAULT";
	
	//key=dqbm,Map<key=xzjdbm，value=obj>>
	//地区乡镇街道
	static public final Map<String,Map<String,PoXT_XZJDXXB>> xzjdMap = new HashMap<String,Map<String,PoXT_XZJDXXB>>();
	//地区单位
	static public final Map<String,Map<String,PoXT_DWXXB>> dwMap = new HashMap<String,Map<String,PoXT_DWXXB>>();
	//地区控制参数
	static public final Map<String,Map<String,PoXT_XTKZCSB>> kzcsMap = new HashMap<String,Map<String,PoXT_XTKZCSB>>();
	
	//行政区划，省库获取
	static public final Map<String,PoXT_XZQHB> xzqhMap = new HashMap<String,PoXT_XZQHB>();
	//行政区划树结构，省库获取
	static public final Map<String,List<PoXT_XZQHB>> xzqhTreeData = new HashMap<String,List<PoXT_XZQHB>>();
	
	public List<TreeNode> getTreeXzqh(String parent_dm){
		String isnew = BaseContext.getContext().getRequest().getParameter("isnew");
		
		List<TreeNode> list = new ArrayList<TreeNode>();
		if(xzqhMap.size()==0){
			realoadXzqh();
		}
		
		if(CommonUtil.isEmpty(parent_dm))
			parent_dm = Constants.ROOT_XZQH;
		else
			parent_dm = parent_dm.trim();
		
		List<PoXT_XZQHB> childs = xzqhTreeData.get(parent_dm);
		//xzqhTreeData.get(Constants.ROOT_XZQH_GJ);
		if(childs != null && childs.size() > 0) {
			for(PoXT_XZQHB child:childs){
				if("0".equals(child.getQybz()) && !child.getDm().endsWith("0000")){
					continue;
				}
				
				//只保留启用记录
				if("1".equals(isnew) && "0".equals(child.getQybz())){
					continue;
				}
				
				TreeNode node = new TreeNode();
				node.setChecked(false);
				node.setText(child.getMc());
				node.setLeaf(!child.getDm().endsWith("00"));
				node.setXzqh(child);
				node.setCodevalue(child.getDm());
				
				if(child.getDm().equals(Constants.ROOT_XZQH_GJ)){
					//国家特殊父亲节点
					node.setLeaf(false);
					node.setChecked(null);
				}
				
				//国家节点，是最后子节点
				if(child.getDm().startsWith("010")){
					node.setLeaf(true);
				}
				
				list.add(node);
			}
		}
		
		return list;
	}
	
	public java.util.Collection<PoXT_XZQHB> getAllXzqh(){
		if(xzqhMap.size()==0){
			realoadXzqh();
		}
		
		return xzqhMap.values();
	}
	
	/**
	 * 重载行政区划
	 */
	static public void realoadXzqh(){
		if(xzqhMap.size()==0){
			synchronized(xzqhMap){
				if(xzqhMap.size()==0){
						Connection conn = null;
						Statement stmt = null;
						ResultSet res = null;
						
						try{
							conn = QxglUtils.getQxxtConnection();
							if(conn==null)
								throw new Exception("字典库连接异常");
							
							stmt = conn.createStatement();
							res = stmt.executeQuery("select * from XT_XZQHB order by dm");
							//1	110000	北京市	BJS			1		
							//2	110100	北京市市辖区	BJSSXQ			1		
							//3	110101	北京市东城区	BJSDCQ			1		
							Map<String,List<PoXT_XZQHB>> xzqhTreeData_tmp = new HashMap<String,List<PoXT_XZQHB>>();
							while(res.next()){
								PoXT_XZQHB qh = ObjectUtil.copyInfo(PoXT_XZQHB.class, null, res);
								xzqhMap.put(qh.getDm(), qh);
																
								String parentKey = qh.getDm();
								if(parentKey.endsWith("0000")){
									parentKey = Constants.ROOT_XZQH;
								}else{
									if(parentKey.endsWith("00"))
										parentKey = parentKey.substring(0, 2) + "0000";
									else
										parentKey = parentKey.substring(0, 4) + "00";
								}
								
								if(qh.getDm().startsWith("010")){
									//国家
									parentKey = Constants.ROOT_XZQH_GJ;
								}
								
								List<PoXT_XZQHB> list = xzqhTreeData_tmp.get(parentKey);
								if(list==null){
									list = new ArrayList<PoXT_XZQHB>();
									xzqhTreeData_tmp.put(parentKey,list);
								}
								
								list.add(qh);
							}
							
							//将国家代码，添加到根
							List<PoXT_XZQHB> list = xzqhTreeData_tmp.get(Constants.ROOT_XZQH);
							if(list!=null){
								PoXT_XZQHB gjqh = new PoXT_XZQHB();
								gjqh.setDm(Constants.ROOT_XZQH_GJ);
								gjqh.setMc("国家");
								list.add(gjqh);
							}
							xzqhTreeData.clear();
							xzqhTreeData.putAll(xzqhTreeData_tmp);
							xzqhTreeData_tmp.clear();
						}catch(Exception ex){
							ex.printStackTrace();
							throw new ServiceException(ex);
						}finally{
							if(res!=null) try{res.close();}catch(Exception e){;}
							res = null;
							
							if(stmt!=null) try{stmt.close();}catch(Exception e){;}
							stmt = null;
							
							if(conn!=null) try{conn.close();}catch(Exception e){;}
							conn = null;
						}
				}
			}
		}
	}
	
	/**
	 * 依据编码，获取行政区划
	 * @param dm
	 * @return
	 */
	static PoXT_XZQHB getXzqh(String dm){
		if(xzqhMap.size()==0){
			realoadXzqh();
		}
		
		PoXT_XZQHB qh = xzqhMap.get(dm);
		if(qh==null)
			;//throw new ServiceException("行政区划[" + dm + "]不存在！");
		
		return qh;
	}
	
	public PoXT_XZJDXXB getXzjd(String dqbm,String xzjdbm){
		if(!xzjdMap.containsKey(dqbm)){
			synchronized(xzjdMap){
				if(!xzjdMap.containsKey(dqbm)){
					try{
						@SuppressWarnings("unchecked")
						List<PoXT_XZJDXXB> list = (List<PoXT_XZJDXXB>)super.getObjectListByHql("from PoXT_XZJDXXB", new Object[]{});
						Map<String,PoXT_XZJDXXB> map = new HashMap<String,PoXT_XZJDXXB>();
						for(PoXT_XZJDXXB xz:list){
							map.put(xz.getDm(), xz);
						}
						xzjdMap.put(dqbm, map);
					}catch(Exception e){
						e.printStackTrace();
						throw new ServiceException(e);
					}finally{
					}	
				}
			}
		}
		
		Map<String,PoXT_XZJDXXB> map = xzjdMap.get(dqbm);
		if(!map.containsKey(xzjdbm)){
			synchronized(xzjdMap){
				try{
					@SuppressWarnings("unchecked")
					List<PoXT_XZJDXXB> list = (List<PoXT_XZJDXXB>)super.getObjectListByHql("from PoXT_XZJDXXB where dm=?", new Object[]{xzjdbm});
					for(PoXT_XZJDXXB xz:list){
						map.put(xz.getDm(), xz);
					}
				}catch(Exception e){
					e.printStackTrace();
					throw new ServiceException(e);
				}finally{
				}
			}
		}
		
		PoXT_XZJDXXB xzjd = map.get(xzjdbm);
		if(xzjd==null)
			throw new ServiceException("乡镇街道编码[" + xzjdbm + "]不存在！");
		
		return xzjd;
	}
	
	public PoXT_DWXXB getDwxx(String dqbm,String dwdm){
		if(!dwMap.containsKey(dqbm)){
			synchronized(dwMap){
				if(!dwMap.containsKey(dqbm)){
					try{
						@SuppressWarnings("unchecked")
						List<PoXT_DWXXB> list = (List<PoXT_DWXXB>)super.getObjectListByHql("from PoXT_DWXXB", new Object[]{});
						Map<String,PoXT_DWXXB> map = new HashMap<String,PoXT_DWXXB>();
						for(PoXT_DWXXB xz:list){
							map.put(xz.getDm(), xz);
						}
						dwMap.put(dqbm, map);
					}catch(Exception e){
						e.printStackTrace();
						throw new ServiceException(e);
					}finally{
					}	
				}
			}
		}
		
		Map<String,PoXT_DWXXB> map = dwMap.get(dqbm);
		if(!map.containsKey(dwdm)){
			synchronized(dwMap){
				try{
					@SuppressWarnings("unchecked")
					List<PoXT_DWXXB> list = (List<PoXT_DWXXB>)super.getObjectListByHql("from PoXT_DWXXB where dm=?", new Object[]{dwdm});
					for(PoXT_DWXXB xz:list){
						map.put(xz.getDm(), xz);
					}
				}catch(Exception e){
					e.printStackTrace();
					throw new ServiceException(e);
				}finally{
				}
			}
		}
		
		PoXT_DWXXB dw = map.get(dwdm);
		if(dw==null)
			throw new ServiceException("单位编码[" + dwdm + "]不存在！");
		
		return dw;
	}
	
	public SjpzBean querySjpzb(ExtMap<String,Object> params){
		SjpzBean bean = new SjpzBean();
		
		String str = params.getString("pzlb");
		if(CommonUtil.isEmpty(str))
			throw new ServiceException("参数有错误！");
		
		try{
			Map<String,List<PoXT_SJPZB>> map = new HashMap<String,List<PoXT_SJPZB>>();
			
			String[] ids = str.split(",");
			for(String pzlb:ids){
				String returnKey = Constants.MEMCACHED_ATTR.DICT_SJPZB_ATTR  + "_" + pzlb;
				@SuppressWarnings("unchecked")
				List<PoXT_SJPZB> list = (List<PoXT_SJPZB>)MemcachedUtil.get(returnKey);
				if(list!=null){
					map.put(pzlb, list);
					continue;
				}

				String hql = "from PoXT_SJPZB t where pzlb=? order by id asc";
				@SuppressWarnings("unchecked")
				List<PoXT_SJPZB> list2 = (List<PoXT_SJPZB>)super.getObjectListByHql(hql, new Object[]{pzlb});
				if(CommonUtil.isEmpty(list2))
					throw new ServiceException("配置" + pzlb + "没有找到!");
				
				map.put(pzlb, list2);
				MemcachedUtil.set(returnKey, list2);
				
				//设置更新时间
				String max = null;
				for(PoXT_SJPZB pz:list2){
					if(max==null)
						max = pz.getBdsj();
					else if(max.compareTo(pz.getBdsj()) < 0 )
						max = pz.getBdsj();
				}
				String returnUpdateKey = Constants.MEMCACHED_ATTR.DICT_SJPZB_ATTR  + "_maxbdsj_" + pzlb;
				MemcachedUtil.set(returnUpdateKey, max);
			}
			
			Set<String> dictSet= new HashSet<String>();
			Map<String,List<SysCode>> dictmap = new HashMap<String,List<SysCode>>();
			for(List<PoXT_SJPZB> pzlist:map.values()){
				for(PoXT_SJPZB pz:pzlist){
					if(pz.getFieldtypename()!=null && pz.getFieldtypename().equals("codeedit")){
						if(CommonUtil.isEmpty(pz.getDsname()))
							continue;
						
						String dsname = pz.getDsname().trim().toUpperCase();
						if(dictSet.contains(dsname))
							continue;

						List<SysCode> list2 = DictData.getSysCodeList(dsname);
						if(list2!=null && list2.size()>0)
							dictmap.put(dsname, list2);
						
						dictSet.add(dsname);
					}
				}
			}
			
			bean.setPzMap(map);
			bean.setDictMap(dictmap);
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e);
		}finally{
		}
		
		return bean;
	}
	
	public List<ExtField> queryYsjpzbJS(ExtMap<String, Object> params) {
		String pzlb = params.getString("pzlb");
		if(CommonUtil.isEmpty(pzlb))
			throw new ServiceException("配置有错误！");
		
		String returnKey = Constants.MEMCACHED_ATTR.DICT_SJPZB_ATTR  + "_js_" + pzlb;
		@SuppressWarnings("unchecked")
		List<ExtField> relist = (List<ExtField>)MemcachedUtil.get(returnKey);
		if(relist==null || relist.size()==0){
			return relist;
		}

		try{
			String hql = "from PoXT_SJPZB t where pzlb=? order by id asc";
			Set<String> dictSet= new HashSet<String>();
			@SuppressWarnings("unchecked")
			List<PoXT_SJPZB> list = (List<PoXT_SJPZB>)super.getObjectListByHql(hql, new Object[]{pzlb});
			List<ExtField> re = new ArrayList<ExtField>();
			for(PoXT_SJPZB pz :list){
				ExtField f = new ExtField(pz);
				if(f.getDsname()!=null)
					dictSet.add(f.getDsname());
				
				re.add(f);
			}
			
			MemcachedUtil.set(returnKey, re);

			return re;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e);
		}finally{
		}
	}

	public Page searchXxb(ExtMap<String,Object> params){
		String optype = params.getString("optype");
		String search_code = params.getString("search_code");
		String visiontype = params.getString("visiontype");
		List<Code> list = new ArrayList<Code>();
		if(search_code!=null){
			params.put("search_code", search_code.toUpperCase().trim());
		}
		
		try{
			if(visiontype!=null && CommonUtil.isNotEmpty(search_code)){
				if(visiontype.equals("DWXXB")){
					//相等，必须返回值，否则前台会进入死循环，反复调用
					if(optype!=null && optype.equals("eq")){
						PoXT_DWXXB dw = super.get(PoXT_DWXXB.class, search_code);
						Code code = new Code();
						if(dw==null){
							code.setCode(search_code);
							code.setName(search_code + "代码没找到！");
						}else{
							code.setCode(dw.getDm());
							code.setName(dw.getMc());
						}
						list.add(code);
					}else{
						//普通检索，查询派出所，需要配合数据范围
						List list2 = super.getObjectListByHql("/conf/segment/common", "queryPcs", params);
						if(list2.size()>0) {
							Code code = new Code();
							code.setCode("");
							code.setName("请选择");
							list.add(code);
						}
						for(Object o:list2){
							PoXT_DWXXB dw = (PoXT_DWXXB)o;
							Code code = new Code();
							code.setCode(dw.getDm());
							code.setName(dw.getMc());
							list.add(code);
						}
					}
				}
				
				if(visiontype.equals("JLXXXB")){
					if(optype!=null && optype.equals("eq")){
						//相等，必须返回值，否则前台会进入死循环，反复调用
						PoXT_JLXXXB dw = super.get(PoXT_JLXXXB.class, search_code);
						Code code = new Code();
						if(dw==null){
							code.setCode(search_code);
							code.setName(search_code + "代码没找到！");
						}else{
							code.setCode(dw.getDm());
							code.setName(dw.getMc());
						}
						list.add(code);
					}else{
						//检索
						Page p = this.searchJlx(params);
						List list2 = p.getList();//super.getObjectListByHql("/conf/segment/common", "queryJlxxxb", params);
						for(Object o:list2){
							PoXT_JLXXXB dw = (PoXT_JLXXXB)o;
							Code code = new Code();
							code.setCode(dw.getDm());
							code.setName(dw.getMc());
							list.add(code);
						}
					}
				}
				
				if(visiontype.equals("JWHXXB")){
					if(optype!=null && optype.equals("eq")){
						//相等，必须返回值，否则前台会进入死循环，反复调用
						PoXT_JWHXXB dw = super.get(PoXT_JWHXXB.class, search_code);
						Code code = new Code();
						if(dw==null){
							code.setCode(search_code);
							code.setName(search_code + "代码没找到！");
						}else{
							code.setCode(dw.getDm());
							code.setName(dw.getMc());
						}
						list.add(code);
					}else {
						//检索
						Page p = this.searchJwh(params);
						
						for(Object o:p.getList()){
							PoXT_JWHXXB dw = (PoXT_JWHXXB)o;
							Code code = new Code();
							code.setCode(dw.getDm());
							code.setName(dw.getMc());
							list.add(code);
						}
					}
				}
				
				if(visiontype.equals("XZJDXXB")){
					if(optype!=null && optype.equals("eq")){
						//相等，必须返回值，否则前台会进入死循环，反复调用
						PoXT_XZJDXXB dw = super.get(PoXT_XZJDXXB.class, search_code);
						Code code = new Code();
						if(dw==null){
							code.setCode(search_code);
							code.setName(search_code + "代码没找到！");
						}else{
							code.setCode(dw.getDm());
							code.setName(dw.getMc());
						}
						list.add(code);
					}else {
						//检索
						Page p = this.searchXzjdNew(params);
						
						for(Object o:p.getList()){
							PoXT_XZJDXXB dw = (PoXT_XZJDXXB)o;
							Code code = new Code();
							code.setCode(dw.getDm());
							code.setName(dw.getMc());
							list.add(code);
						}
					}
				}
				if(visiontype.equals("JWZRQXXB")){
					if(optype!=null && optype.equals("eq")){
						//相等，必须返回值，否则前台会进入死循环，反复调用
						PoXT_JWZRQXXB dw = super.get(PoXT_JWZRQXXB.class, search_code);
						Code code = new Code();
						if(dw==null){
							code.setCode(search_code);
							code.setName(search_code + "代码没找到！");
						}else{
							code.setCode(dw.getDm());
							code.setName(dw.getMc());
						}
						list.add(code);
					}else {
						//检索
						Page p = this.searchJwzrq(params);
						
						for(Object o:p.getList()){
							PoXT_JWZRQXXB dw = (PoXT_JWZRQXXB)o;
							Code code = new Code();
							code.setCode(dw.getDm());
							code.setName(dw.getMc());
							list.add(code);
						}
					}
				}
			}
			
			if(visiontype.equals("YHXXB")){
				//处理用户信息字典选择
				if(optype!=null && optype.equals("eq")){
					//相等，必须返回值，否则前台会进入死循环，反复调用
					PoXT_YHXXB dw = super.get(PoXT_YHXXB.class, Long.parseLong(search_code));
					Code code = new Code();
					if(dw==null){
						code.setCode(search_code);
						code.setName(search_code + "代码没找到！");
					}else{
						code.setCode(dw.getYhid().toString());
						code.setName(dw.getYhxm());
					}
					list.add(code);
				}else {
					//检索
					Page p = this.searchYhxx(params);
					if(p.getList().size()>0) {
						Code code = new Code();
						code.setCode("");
						code.setName("请选择");
						list.add(code);
					}
					for(Object o:p.getList()){
						PoXT_YHXXB dw = (PoXT_YHXXB)o;
						Code code = new Code();
						code.setCode(dw.getYhid().toString());
						code.setName(dw.getYhxm());
						list.add(code);
						
						//防止检索过多
						if(list.size()>200)
							break;
					}
				}
			}
			if(visiontype.equals("SPDZB")) {
				//检索
				Page p = this.searchSpdzb(params);
				for(Object o:p.getList()){
					PoXT_SPDZB dw = (PoXT_SPDZB)o;
					Code code = new Code();
					code.setCode(String.valueOf(dw.getDzid()));
					code.setName(dw.getDzmc());
					list.add(code);
				}
//				//相等，必须返回值，否则前台会进入死循环，反复调用
//				if(optype!=null && optype.equals("eq")){
//
//				}
			
			}
		}catch(Exception e){
			throw new ServiceException(e);
		}finally{
		}
		
		Page p = new Page();
		p.setList(list);
		return p;
	}
	
	public Page searchYhxx(ExtMap<String,Object> params){
		Page px = super.getPageRecords("/conf/segment/common", "queryPoXT_YHXXB", params);
		List<Code> list = new ArrayList<Code>();
		String optype = params.getString("optype");
		if(optype!=null && optype.equals("eq")){
			for(Object o:px.getList()){
				PoXT_YHXXB yhxx = (PoXT_YHXXB)o;
				Code code = new Code();
				code.setCode(yhxx.getYhdlm());
				code.setName(yhxx.getYhxm());
				code.setPyt(String.valueOf(yhxx.getYhid()));
				list.add(code);
			}
			Page p = new Page();
			p.setList(list);
			return p;
		}
		return px;
	}

	public Page searchJlx(ExtMap<String,Object> params){
		String search_code = params.getString("search_code");
		String uid = BaseContext.getUser().getUser().getYhid().toString();
		String returnKey = Constants.MEMCACHED_ATTR.DICT_SEARCH_ATTR + uid + "_jlx_" + search_code;
		//String jlxKey = Constants.MEMCACHED_ATTR.DICT_SEARCH_ALL_ATTR + uid + "_jlx_";
		
		//优先从缓存中搜索结果
		/*
		Object obj = MemcachedUtil.get(returnKey);
		if(obj!=null){
			Page p = new Page();
			p.setList((List<?>)obj);
			return p;
		}
		*/
		
		/**
			需要根据用户权限进行限制
		 */
		
		List<PoXT_JLXXXB> jlxList =null;
		try{
			SqlParse sqlParse = new SqlParse(Config.getSql("/conf/segment/common","queryJlxJwh"));
			sqlParse.bind(params);
			SqlParam sqlParam = sqlParse.parse();
			
			Map<String,PoXT_JLXXXB> jlxMap = new HashMap<String,PoXT_JLXXXB>();
			jlxList = new ArrayList<PoXT_JLXXXB>();
			List<?> list = super.getObjectListByHql(sqlParam.getSql(), sqlParam.getParams().toArray());
			for(Object o:list){
				Object[] objs = (Object[])o;
				PoXT_JLXXXB  jlx = (PoXT_JLXXXB)objs[0];
				PoXT_JWHXXB jwh = (PoXT_JWHXXB)objs[1];
				
				if(!jlxMap.containsKey(jlx.getDm())){
					jlx.setJwhList(new ArrayList<PoXT_JWHXXB>());
					jlxMap.put(jlx.getDm(), jlx);
					jlxList.add(jlx);
				}
				
				//冗余乡镇街道和单位名称
				if(CommonUtil.isNotEmpty(jwh.getXzjddm())){
					try {
						PoXT_XZJDXXB xzjd = this.getXzjd(DQBM, jwh.getXzjddm());
						jwh.setXzjddm_name(xzjd.getMc());
//						jlx.setQhdm2(xzjd.getQhdm());
//						jlx.setQhdm2_mc(xzjd.getMc());
					} catch (Exception e) {
						continue;
					}
					
					
				}
				
				if(CommonUtil.isNotEmpty(jwh.getDwdm())){
					jwh.setDwdm_name(this.getDwxx(DQBM, jwh.getDwdm()).getMc());
					jwh.setDwdm_bz(this.getDwxx(DQBM, jwh.getDwdm()).getBz());
					PoXT_XZJDXXB xzjd = this.getXzjd(DQBM, jwh.getXzjddm());
					jwh.setQhdm2(xzjd.getQhdm());
					jwh.setQhdm2_mc(xzjd.getMc());
				}
				
				jlx.getJwhList().add(jwh);
			}
			
			//MemcachedUtil.set(returnKey,  jlxList,  60*30);
			
			Page p = new Page();
			p.setList(jlxList);
			
			return p;
		}catch(Exception e){
			e.printStackTrace();

			throw new ServiceException(e);
		}finally{
		}
	}
	
	public Page searchXzqh(ExtMap<String,Object> params){
		String search_code = params.getString("search_code");
		if(search_code!=null){
			search_code = search_code.toUpperCase();
			params.put("search_code", search_code);
		}else{
			List<Code> list = new ArrayList<Code>();
			Code code = new Code();
			code.setCode("");
			code.setName("");
			list.add(code);
			
			Page p = new Page();
			p.setList(list);
			return p;
		}
		
		String isnew = params.getString("isnew");
		if(isnew==null) isnew = "0";		
		String returnKey = Constants.MEMCACHED_ATTR.DICT_SEARCH_ATTR  + "_all_xzqh_" + search_code + "_" + isnew;

		String optype = params.getString("optype");
		
		List<Code> list = new ArrayList<Code>();
		
		if(CommonUtil.isNotEmpty(optype)){
			//翻译
			PoXT_XZQHB qh = getXzqh(search_code);
			if(qh==null && optype!=null && optype.equals("eq")){
				qh = new PoXT_XZQHB();
				qh.setDm(search_code);
				qh.setMc(search_code + " 找不到！");
			}
			if(qh!=null){
				Code code = new Code();
				code.setCode(qh.getDm());
				code.setName(qh.getMc());
				list.add(code);
				
				Page p = new Page();
				p.setList(list);
				return p;
			}
		}
		
		//优先从缓存中搜索结果
		Object obj = MemcachedUtil.get(returnKey);
		if(obj!=null){
			Page p = new Page();
			p.setList((List<?>)obj);

			return p;
		}
		
		Page px = super.getPageRecords("/conf/segment/common", "queryPoXT_XZQHB", params);
		
		for(Object objx:px.getList()){
			PoXT_XZQHB qh = (PoXT_XZQHB)objx;

			//只保留启用记录
			if("1".equals(isnew) && "0".equals(qh.getQybz())){
				continue;
			}
			
			Code code = new Code();
			code.setCode(qh.getDm());
			code.setName(qh.getMc());
			
			list.add(code);
			
			if(list.size()>100)
				break;
		}
		
		MemcachedUtil.set(returnKey, list, 24*3600);
		
		Page p = new Page();
		p.setList(list);
		
		return p;
	}
	public Page searchXzqhNew(ExtMap<String,Object> params){
		String search_code = params.getString("search_code");
		String returnKey = Constants.MEMCACHED_ATTR.DICT_SEARCH_ATTR  + "_all_xzqh_" + search_code;
		String optype = params.getString("optype");
		List<PoXT_XZQHB> xzqhList =null;
		try{
			SqlParse sqlParse = new SqlParse(Config.getSql("/conf/segment/common","queryXzqhPcs"));
			sqlParse.bind(params);
			SqlParam sqlParam = sqlParse.parse();
			
			Map<String,PoXT_XZQHB> jlxMap = new HashMap<String,PoXT_XZQHB>();
			xzqhList = new ArrayList<PoXT_XZQHB>();
			List<?> list = super.getObjectListByHql(sqlParam.getSql(), sqlParam.getParams().toArray());
			for(Object o:list){
				Object[] objs = (Object[])o;
				PoXT_XZQHB  jxzql = (PoXT_XZQHB)objs[0];
				PoXT_DWXXB dwxx = (PoXT_DWXXB)objs[1];
				
				if(!jlxMap.containsKey(jxzql.getDm())){
					jlxMap.put(jxzql.getDm(), jxzql);
					xzqhList.add(jxzql);
				}
				
				if(CommonUtil.isNotEmpty(dwxx.getDm())){
					dwxx.setDm(this.getDwxx(DQBM, dwxx.getDm()).getDm());
					dwxx.setMc(this.getDwxx(DQBM, dwxx.getDm()).getMc());
				}
				if(CommonUtil.isEmpty(jxzql.getDwList())){
					//new List<PoXT_DWXXB>(dwxx);
					List<PoXT_DWXXB> list1 = new ArrayList();
					list1.add(dwxx);
					jxzql.setDwList(list1);
				}
				jxzql.getDwList().add(dwxx);
			}
			
			//MemcachedUtil.set(returnKey,  jlxList,  60*30);
			Page p = new Page();
			p.setList(xzqhList);
			
			return p;
		}catch(Exception e){
			e.printStackTrace();

			throw new ServiceException(e);
		}finally{
		}
	
	}	
	public SysCode getRemoteDictItem(String visiontype,String code){
		SysCode item = new SysCode();
		item.setCode(code);
		item.setVisiontype(visiontype);
		
		/**
		 * 特殊字典
		 dwxxb	111 
		99		jlxjwhdzxxb	2
		jlxxxb	63
		101	jtcy	37
		jwhxxb	63
		103	jwzrqxxb	61
		xzjdxxb	62
		xzqhb	206
		xzqhbnew	21
		yhxxb	74
		 */
		if(visiontype.equalsIgnoreCase("xzjdxxb")){
			PoXT_XZJDXXB dw = super.get(PoXT_XZJDXXB.class,code);
			if(dw!=null){
				item.setCodename(dw.getMc());
				return item;
			}
		}
		
		if(visiontype.equalsIgnoreCase("jlxxxb")){
			PoXT_JLXXXB dw = super.get(PoXT_JLXXXB.class,code);
			if(dw!=null){
				item.setCodename(dw.getMc());
				return item;
			}
		}
		
		if(visiontype.equalsIgnoreCase("jwhxxb")){
			PoXT_JWHXXB dw = super.get(PoXT_JWHXXB.class,code);
			if(dw!=null){
				item.setCodename(dw.getMc());
				return item;
			}
		}
		
		if(visiontype.equalsIgnoreCase("dwxxb")){
			PoXT_DWXXB dw = super.get(PoXT_DWXXB.class,code);
			if(dw!=null){
				item.setCodename(dw.getMc());
				return item;
			}
		}
		
		if(visiontype.equalsIgnoreCase("xzqhb") || visiontype.equalsIgnoreCase("xzqhbnew")){
			PoXT_XZQHB qh = super.get(PoXT_XZQHB.class, code);
			if(qh!=null){
				item.setCodename(qh.getMc());
				return item;
			}
		}
		
		if(visiontype.equalsIgnoreCase("yhxxb")){
			PoXT_YHXXB yh = super.get(PoXT_YHXXB.class, Long.parseLong(code));
			if(yh!=null){
				item.setCodename(yh.getYhxm());
				return item;
			}else{
				item.setCodename(code);
			}
		}
			
		return null;
	}
	
	static public void main(String arg[]) throws Exception{
		int[] list = new int[36];
		for(int i=0;i<list.length;i++){
			list[i] = i+1;
		}
		
		//一半
		int i1 = list.length/2;
		if(list.length%2!=0)
			i1++;
		
		int[] list2 = new int[36];
		for(int i=0;i<i1;i++){
			//偶数位置
			if(i*2<list.length)
				list2[i*2] = list[i];
		}

		for(int i=i1;i<list.length;i++){
			//偶数位置
			int x = (i-i1)*2+1;
			if(x<list.length)
				list2[x] = list[i];
		}
		
		for(int i=0;i<list2.length;i++){
			System.out.print(list2[i] + "\t");
			if((i+1)%2==0){
				System.out.println();
			}
		}
	}

	@Override
	public Page searchJwh(ExtMap<String, Object> params) {
//		String search_code = params.getString("search_code");
//		String uid = BaseContext.getUser().getUser().getYhid().toString();
//		String returnKey = Constants.MEMCACHED_ATTR.DICT_SEARCH_ATTR + uid + "_jlx_" + search_code;
		
		return super.getPageRecords("/conf/segment/common","queryJwh", params);
	}
	
	@SuppressWarnings("unchecked")
	public Page searchJwhPcsXzjd(ExtMap<String, Object> params) {
		
		List<PoXT_JWHXXB> jwhList =null;
		try{
			SqlParse sqlParse = new SqlParse(Config.getSql("/conf/segment/common","searchJwhPcsXzjd"));
			sqlParse.bind(params);
			SqlParam sqlParam = sqlParse.parse();
			
//			Map<String,PoXT_JWHXXB> jwhMap = new HashMap<String,PoXT_JWHXXB>();
//			jwhList = new ArrayList<PoXT_JWHXXB>();
//			List<?> list = super.getObjectListByHql(sqlParam.getSql(), sqlParam.getParams().toArray());
//			for(Object o:list){
//				Object[] objs = (Object[])o;
//				PoXT_JLXXXB  jlx = (PoXT_JLXXXB)objs[0];
//				PoXT_JWHXXB jwh = (PoXT_JWHXXB)objs[1];
//				
//				if(!jwhMap.containsKey(jwh.getDm())){
//					jwh.setJlxList(new ArrayList<PoXT_JLXXXB>());
//					jwhMap.put(jwh.getDm(), jwh);
//					jwhList.add(jwh);
//				}
//				
//				//冗余乡镇街道和单位名称
//				if(CommonUtil.isNotEmpty(jwh.getXzjddm())){
//					try {
//						PoXT_XZJDXXB xzjd = this.getXzjd(DQBM, jwh.getXzjddm());
//						jwh.setXzjddm_name(xzjd.getMc());
//						jlx.setQhdm2(xzjd.getQhdm());
//						jlx.setQhdm2_mc(xzjd.getMc());
//					} catch (Exception e) {
//						continue;
//					}
//				}
//				if(CommonUtil.isNotEmpty(jwh.getDwdm())){
//					jwh.setDwdm_name(this.getDwxx(DQBM, jwh.getDwdm()).getMc());
//					jwh.setDwdm_bz(this.getDwxx(DQBM, jwh.getDwdm()).getBz());
//				}
//				
//				jwh.getJlxList().add(jlx);
//			}
			
			
			Map<String,PoXT_JWHXXB> jwhMap = new HashMap<String,PoXT_JWHXXB>();
			jwhList = new ArrayList<PoXT_JWHXXB>();
			List<?> list = super.getObjectListByHql(sqlParam.getSql(), sqlParam.getParams().toArray());
			for(Object o:list){
				Object[] objs = (Object[])o;
				PoXT_JWHXXB jwh = (PoXT_JWHXXB)objs[0];
				PoXT_JLXXXB  jlx = (PoXT_JLXXXB)objs[1];
				
				if(!jwhMap.containsKey(jwh.getDm())){
					jwh.setJlxList(new ArrayList<PoXT_JLXXXB>());
					jwhMap.put(jwh.getDm(), jwh);
					jwhList.add(jwh);
				}
				//jlx.getdwd
				//冗余乡镇街道和单位名称
				if(CommonUtil.isNotEmpty(jwh.getXzjddm())){
					try {
						PoXT_XZJDXXB xzjd = this.getXzjd(DQBM, jwh.getXzjddm());
						jwh.setXzjddm_name(xzjd.getMc());
						jlx.setQhdm2(xzjd.getQhdm());
						jlx.setQhdm2_mc(xzjd.getMc());
					} catch (Exception e) {
						continue;
					}
					
					
				}
				
				if(CommonUtil.isNotEmpty(jwh.getDwdm())){
					jwh.setDwdm_name(this.getDwxx(DQBM, jwh.getDwdm()).getMc());
					jwh.setDwdm_bz(this.getDwxx(DQBM, jwh.getDwdm()).getBz());
					jwh.setSsxq(jwh.getSsxq());
				}
				
				jwh.getJlxList().add(jlx);
			}
			
			Page p = new Page();
			p.setList(jwhList);
			
			return p;
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e);
		}finally{
		}			
		//return super.getPageRecords("/conf/segment/common","queryJwh", params);
	}

	@Override
	public Page searchPcs(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common","queryPcs", params);
	}
	private Page searchXzjdNew(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common","queryXzjd", params);
	}
	private Page searchJwzrq(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common","queryJwzrq", params);
	}	
	public List<?> querySjpzbUpdate(){
		String sql = "select pzlb,max(bdsj) from xt_sjpzb group by pzlb";
		List<?> list = super.executeSqlQuery(sql, null);
		return list;
	}
	private Page searchSpdzb(ExtMap<String, Object> params) {
		return super.getPageRecords("/conf/segment/common","querySpdzb", params);
	}

	@Override
	public Page searchXzlx(ExtMap<String, Object> params) {
		String search_code = params.getString("search_code");
		if(search_code!=null){
			search_code = search_code.toUpperCase();
			params.put("search_code", search_code);
		}else{
			List<Code> list = new ArrayList<Code>();
			Code code = new Code();
			code.setCode("");
			code.setName("");
			list.add(code);
			
			Page p = new Page();
			p.setList(list);
			return p;
		}
		params.put("cslb", "1030");
		
		String optype = params.getString("optype");
		
		List<Code> list = new ArrayList<Code>();
		
		Page px = super.getPageRecords("/conf/segment/common", "queryXtcsxxByCslb", params);
		
		for(Object objx:px.getList()){
			PoXT_XTCSB qh = (PoXT_XTCSB)objx;

			Code code = new Code();
			code.setCode(qh.getDm());
			code.setName(qh.getMc());
			list.add(code);
			
			if(list.size()>100)
				break;
		}
		Page p = new Page();
		p.setList(list);
		
		return p;
	}

	@Override
	public Page searchSpdz(ExtMap<String, Object> params) {
		String search_code = params.getString("search_code");
		if(search_code!=null){
			search_code = search_code.toUpperCase();
			params.put("search_code", search_code);
		}else{
			List<Code> list = new ArrayList<Code>();
			Code code = new Code();
			code.setCode("");
			code.setName("");
			list.add(code);
			
			Page p = new Page();
			p.setList(list);
			return p;
		}
		
		String returnKey = Constants.MEMCACHED_ATTR.DICT_SEARCH_ATTR  + "_all_xzqh_" + search_code;
		String optype = params.getString("optype");
		
		List<Code> list = new ArrayList<Code>();
		
		if(CommonUtil.isNotEmpty(optype)){
			//翻译
			PoXT_XZQHB qh = getXzqh(search_code);
			if(qh==null && optype!=null && optype.equals("eq")){
				qh = new PoXT_XZQHB();
				qh.setDm(search_code);
				qh.setMc(search_code + " 找不到！");
			}
			if(qh!=null){
				Code code = new Code();
				code.setCode(qh.getDm());
				code.setName(qh.getMc());
				list.add(code);
				
				Page p = new Page();
				p.setList(list);
				return p;
			}
		}
		
		//优先从缓存中搜索结果
		Object obj = MemcachedUtil.get(returnKey);
		if(obj!=null){
			Page p = new Page();
			p.setList((List<?>)obj);
			return p;
		}
		
		Page px = super.getPageRecords("/conf/segment/common", "queryPoXT_XTCSB", params);
		
		for(Object objx:px.getList()){
			PoXT_XTCSB qh = (PoXT_XTCSB)objx;

			Code code = new Code();
			code.setCode(qh.getDm());
			code.setName(qh.getMc());
			list.add(code);
			
			if(list.size()>100)
				break;
		}
		
		MemcachedUtil.set(returnKey, list, 24*3600);
		
		Page p = new Page();
		p.setList(list);
		
		return p;
	}

	@Override
	public List<VoXT_SJPZB> querySjpzbGz(ExtMap<String, Object> params) {
		String pzlb = params.getString("pzlb");
		String flag = params.getString("flag");
		String hql1 = "";
		if(CommonUtil.isEmpty(pzlb))
			throw new ServiceException("参数有错误！");
		try{
			if("2".equals(flag)) {
				hql1="from PoXT_SJPZB t where pzlb='"+pzlb+"' and exists (from PoXT_SJPZB where  (visibletype is not null and length(visibletype)>=3 and substr(visibletype,3,1)=1)  and sjpzid = t.sjpzid) order by id asc";
			}else if("1".equals(flag)) {
				hql1 = "from PoXT_SJPZB t where pzlb='"+pzlb+"' and not exists (from PoXT_SJPZB where  (visibletype is not null and length(visibletype)>=3 and substr(visibletype,3,1)=1  or (visibletype='000000' and fieldlength=20 or fieldtype = 'blob' or fieldtypename = 'edit' and fieldlength=1 ))  and sjpzid = t.sjpzid) order by fieldname asc";
			}
			@SuppressWarnings("unchecked")
			List<PoXT_SJPZB> list1 = (List<PoXT_SJPZB>)super.getObjectListByHql(hql1, new Object[]{});
			List<VoXT_SJPZB> list3 = new ArrayList<>();
			for(int i = 0;i<list1.size();i++) {
				VoXT_SJPZB temp = new VoXT_SJPZB(list1.get(i));
				temp.setPxid(list1.get(i).getSjpzid()+"");
				list3.add(temp);
			}
			if(CommonUtil.isEmpty(list1))
				throw new ServiceException("配置" + pzlb + "没有找到!");
			return list3;
			
		}catch(Exception e){
			e.printStackTrace();
			throw new ServiceException(e);
		}finally{
		}
	
	}
}
