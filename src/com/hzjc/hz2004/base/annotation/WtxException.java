package com.hzjc.hz2004.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 异常处理于指定controller异常跳转页面
 * Created by LiuJifeng on 2016/11/13.
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface WtxException {

    /**
     * 异常处理页面
     * @return
     */
    String value() default "error/exception";
}
