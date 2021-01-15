package com.hzjc.hz2004.base.sqltemplate;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Administrator on 2016-09-30.
 */
public class GenSqlTemplateIF {


    public static String template = "#if(!$ut.isns($%s))\n" +
            "\tand %s=:%s\n" +
            "#end\n";

    public static String genSqlTemlate(Class<?> target) {

        StringBuffer colums = new StringBuffer();

        StringBuffer condions = new StringBuffer();

        colums.append("select ");
        Field[] fields = target.getDeclaredFields();
        final AtomicInteger count = new AtomicInteger(0);
        Arrays.stream(fields).parallel().forEach(field -> {

            if (field.getName().equals("serialVersionUID") == false) {
                count.incrementAndGet();
                if (count.intValue() == 8) {
                    colums.append("\n");
                    count.set(0);
                }
                colums.append(field.getName()).append(",");
                condions.append(String.format(template, field.getName(), field.getName(), field.getName()));
            }
        });

        colums.deleteCharAt(colums.lastIndexOf(","));
        colums.append("\n from ").append(target.getSimpleName()).append(" where 1=1 \n").append(condions);

        return colums.toString();
    }
}
