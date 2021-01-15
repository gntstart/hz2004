package com.hzjc.wsstruts.common;

import java.lang.reflect.*;

/**
 * 创建对象类
 * <p>Title: Hz2004</p>
 * <p>Description: 常住人口二代证Hz2004版</p>
 * <p>Copyright: Copyright (c) 2004</p>
 * <p>Company: HZJC</p>
 * @author Kansan Ku(kgb_hz@126.com)
 * @version 1.0
 */

public class ObjectCreator {

  /**
   *
   */
  private ObjectCreator() {
  }

  /**
   * 根据Java对象的类名创建该对象
   * @param strClassName 对象的类名称
   * @return 返回该对象
   * @throws Exception
   */
  public static Object createObject(String strClassName) throws Exception {
    return createObject(Class.forName(strClassName));
  }

  /**
   * 根据Java对象的类创建该对象
   * @param clazzObject 要创建类的Class
   * @return 返回创建的对象
   * @throws Exception
   */
  public static Object createObject(Class clazzObject) throws Exception {
    //com.hzjc.hz2004.vo.VoXT_DWXXB.class.newInstance();

    return clazzObject.newInstance();
  }

  /**
   * 根据要创建的对象的类名称和构造子参数创建对象
   * @param strClassName 类名称
   * @param params 构造子参数数组
   * @return 返回创建的对象
   * @throws Exception
   */
  public static Object createObject(String strClassName, Object[] params) throws
      Exception {
    return createObject(Class.forName(strClassName), params);
  }

  /**
   * 根据要创建的类的class和构造子参数创建类
   * @param clazzObject 类对应的Class
   * @param params 构造子参数
   * @return 返回创建的对象
   * @throws Exception
   */
  public static Object createObject(Class clazzObject, Object[] params) throws
      Exception {
    Constructor[] constructors = clazzObject.getConstructors();
    Object object = null;
    /**
     * 循环遍历所有构造方法，根据参数创建对象，如果没有该异常循环处理下一个构造方法
     */
    for (int counter = 0; counter < constructors.length; counter++) {
      try {
        object = constructors[counter].newInstance(params);
      }
      catch (Exception ex) {
        if (ex instanceof InvocationTargetException) {
          ( (InvocationTargetException) ex).getTargetException().
              printStackTrace();
          //不做处理进行下一个构造子
        }
      }
    }
    //空抛出异常
    if (object == null) {
      throw new InstantiationException();
    }
    return object;
  }

}
