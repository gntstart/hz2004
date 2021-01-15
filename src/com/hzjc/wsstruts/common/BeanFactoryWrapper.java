/* Generated by Together */
package com.hzjc.wsstruts.common;

import java.io.InputStream;
import org.springframework.beans.factory.BeanFactory;
import com.hzjc.wsstruts.exception.*;
import org.springframework.beans.BeansException;
import org.apache.commons.logging.*;
import com.hzjc.wsstruts.common.config.*;

/**
 * 调用方式BeanFactoryWrapper.getInstance()
 * 包装J2EE各层框架SpringFramework（web-control-action-bo-dao层可以动态配置）。
 * IoC和AOP设计模式，动态组装、插入组件Bean
 * 使读取XML文件仅一次，实例化XmlBeanFactory仅一次
 * <p>Title: </p>
 * <p>Description: </p>
 * <p>Copyright: Copyright (c) 2003</p>
 * <p>Company: </p>
 * @Kansanku(kgb_hz@126.com)
 * @version 1.0
 */
public class BeanFactoryWrapper {
  private static Log _log = LogFactory.getLog(BeanFactoryWrapper.class);
  private static String _configName = "conf/hz2004.application.bean.xml";
  private static BeanFactory _springfactory = null;
  private static BeanFactoryWrapper _wsstrutsfactory = null;

  /**
   * 保护构造方法，确保该工厂不被实例化
   */
  protected BeanFactoryWrapper() {
    //保证该工厂只被实例化一次
    initSpringFactory();
  }

  /**
   *
   */
  private void initXmlResource() {
    _configName = WSConfig.getInsance().getXmlSpringAppBean();
  }

  /**
   * 初始化Spring-J2EE框架
   */
  private void initSpringFactory() {
    //得到配置文件
    //InputStream is = BeanFactoryWrapper.class.getClassLoader().
    //getResourceAsStream(_configName);
    //创建工厂Lazy Create
    if (_springfactory == null) {
      //_log.info("ClassLoader=" + BeanFactoryWrapper.class.getClassLoader().getResource(_configName).getPath());
      //_log.info("class=" + BeanFactoryWrapper.class.getResource("/").getPath());
      initXmlResource();
      InputStream is = BeanFactoryWrapper.class.getClassLoader().
          getResourceAsStream(_configName);
      //_springfactory = new XmlBeanFactory(is);
    }
  }

  /**
   * 得到BeanFactory创建工厂单例
   * ?多线程在此是否有影响???
   * @return
   */
  public static synchronized BeanFactoryWrapper getInstance() {
    //public static BeanFactoryWrapper getInstance() {
    //Lazy Create
    if (_wsstrutsfactory == null) {
      _wsstrutsfactory = new BeanFactoryWrapper();
    }
    return _wsstrutsfactory;
  }

  /**
   * 根据用户名称创建返回实例
   * @param beanName：BeanID
   * @return:Bean Insance实例
   */
  public Object getBean(String beanName) throws BeanCreateException {
    Object bean = null;
    try {
      bean = _springfactory.getBean(beanName);
      if (bean == null) {
        throw new BeanCreateException("<请求创建Bean".concat(beanName).concat(
            "的实例不存在>"));
      }
    }
    catch (BeansException ex) {
      throw new BeanCreateException("<从配置文件加载创建".concat(beanName).concat(
          "发生异常>"), ex);
    }
    return bean;
  }

}
