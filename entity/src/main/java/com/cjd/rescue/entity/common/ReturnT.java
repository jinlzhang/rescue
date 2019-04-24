package com.cjd.rescue.entity.common;

import java.util.HashMap;
import java.util.Map;

public class ReturnT {

    private String code;

    private String msg;

    private  Map resultMap;



    private  Err err;

    public ReturnT(Err err) {
        this.code = err.getCode();
        this.msg = err.getMsg();

    }


    public static ReturnT result(Err err){
        return new ReturnT(err);
    }

    public  ReturnT dataMap(String key,Object content){
        if(null == resultMap){
            resultMap = new HashMap();
        }
        this.resultMap.put(key,content);
        return this;
    }


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }


    public Map getResultMap() {
        return resultMap;
    }

    public void setResultMap(Map resultMap) {
        this.resultMap = resultMap;
    }
}
