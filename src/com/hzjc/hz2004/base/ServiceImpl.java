package com.hzjc.hz2004.base;

import javax.annotation.Resource;
import org.hibernate.SessionFactory;

/**
 * 基础库DAO，所有继承库必须继承此ServiceImpl
 * @author Administrator
 *
 */
public abstract class ServiceImpl extends ServiceImplBase{
	//必须通过@Resource指定SessionFactory名称，Autowired会按类型注入，导致错误
	@Resource(name="sessionFactory") 
	private SessionFactory sessionFactory;

	public SessionFactory getCurrentSessionFactory(){
			return sessionFactory;
	}
}
