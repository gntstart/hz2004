package com.hzjc.hz2004.base;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import com.hzjc.hz2004.appbase.SysParam;
import com.hzjc.hz2004.service.CommonService;

/**
 * 用于持有ApplicationContext,
 * 可以使用SpringContextHolder.getBean('xxxx')的静态方法得到spring bean对象
 *
 * @author hasee
 */
public class SpringContextHolder implements ApplicationContextAware {
    public SpringContextHolder() {
        System.out.println("--------create SpringContextHolder-----");
    }

    /**
     * 以静态变量保存ApplicationContext,可在任意代码中取出ApplicaitonContext.
     */
    private static ApplicationContext context;

    /**
     * 实现ApplicationContextAware接口的context注入函数, 将其存入静态变量.
     */
    public void setApplicationContext(ApplicationContext context) {
        SpringContextHolder.context = context;

        SysParam.loadAllParamCode();
    }

    public static ApplicationContext getApplicationContext() {
        return context;
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     */
    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) context.getBean(name);
    }

    /**
     * 通过class获取Bean.
     *
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name,以及Clazz返回指定的Bean
     *
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 获取通用服务
     *
     * @return
     */
    public static CommonService getCommonService() {
        return (CommonService) getBean("commonService");
    }
}

