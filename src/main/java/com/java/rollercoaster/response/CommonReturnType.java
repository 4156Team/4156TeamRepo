package com.java.rollercoaster.response;

import com.java.rollercoaster.errorEnum.ErrorEnum;

public class CommonReturnType {
    //response status success/fail
    private String status;
    //if status=success，return data needed by front-end in json format
    //otherwise，return error code in data
    private Object data;

    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result,"success");
    }
    public static CommonReturnType create(Object result, String status) {
        CommonReturnType type = new CommonReturnType();
        type.setStatus(status);
        type.setData(result);
        return type;
    }
    public static CommonReturnType autoCreate(ErrorEnum errorEnum){
        if (ErrorEnum.OK == errorEnum){
            return create(errorEnum);
        } else{
            return create(errorEnum, "fail");
        }
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
