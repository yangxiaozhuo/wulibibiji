package com.yxz.wulibibiji.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Boolean success;
    private int code;
    private String errorMsg;
    private Object data;

    public static Result ok(){
        return new Result(true, 200,null, null);
    }
    public static Result ok(Object data){
        return new Result(true, 200,null, data);
    }
    public static Result fail(String errorMsg){
        return new Result(false, 500, errorMsg, null);
    }
}
