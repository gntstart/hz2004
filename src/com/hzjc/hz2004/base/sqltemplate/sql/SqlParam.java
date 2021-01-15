package com.hzjc.hz2004.base.sqltemplate.sql;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SqlParam implements Serializable {
    private static final long serialVersionUID = 1L;

    private String sql;
    private List<Object> params;
    private List<Integer> paramTypes;

    public SqlParam(String sql, List<Object> params) {

        super();
        this.sql = sql;
        if(params==null){
            params=new ArrayList<Object>();
        }
        this.params = params;
    }

    public SqlParam(String sql, List<Object> params, List<Integer> paramTypes) {
        super();
        this.sql = sql;
        this.params = params;
        this.paramTypes = paramTypes;
    }


    public String getSql() {
        return sql;
    }

    public List<Object> getParams() {
        return params;
    }

    public Object[] getParamsArrays() {
        return params.toArray();
    }

    public Integer[] getParamTypes() {
        Integer[] rs = new Integer[this.paramTypes.size()];
        paramTypes.toArray(rs);
        return rs;
    }
}
