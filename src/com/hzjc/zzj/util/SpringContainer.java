package com.hzjc.zzj.util;

import com.hzjc.hz2004.base.login.BaseContext;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * 
 * @author caesar
 * @version 1.0 2007-11-12
 * @功能描述: 保存beanFactory 运行时对象
 *           方便系统任何类的引用工厂
 *
 */
public class SpringContainer {
	    private static BeanFactory beanfactory;
	    private SpringContainer(){
	    	
	    }
	    /**
	     * @param factory
	     * 使用举例:
	     * 
	     *  if(SpringContainer.isEmpty()){
	    	 synchronized(SpringContainer.class){
		        ClassPathXmlApplicationContext applicatioContext = new ClassPathXmlApplicationContext(getConfigLocation());
		        SpringContainer.setFactory(applicatioContext);
		     }
    	  	}
	     */
	    public static  void setFactory(BeanFactory factory)
	    {
	        beanfactory = factory;
	    }
	    
	    public static  boolean isEmpty(){
		      return  beanfactory==null?true:false;
		}
	    
	    public static Object getObject(String str)
	    {
	    	if(beanfactory==null)
	    		beanfactory =
	    			WebApplicationContextUtils.getWebApplicationContext(BaseContext.getContext().getServletContext());
			
	        return beanfactory.getBean(str);
	    }
	    
        public synchronized static BeanFactory getBeanFactory(){
        	return  beanfactory;
        }
}
