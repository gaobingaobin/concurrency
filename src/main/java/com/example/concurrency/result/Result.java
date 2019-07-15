package com.example.concurrency.result;

public class Result<T> {
    private int code;
    private String msg;
    private T data;

    public Result(T data) {
        this.code = 0;
        this.msg = "success";
        this.data = data;
    }

    public Result(CodeMsg msg) {
     if (msg==null){
         return;
     }
     this.code = msg.getCode();
     this.msg = msg.getMsg();
    }

    public static <T> Result<T> success(T data){
        return new Result<T>(data);
    }
    public static <T> Result<T> error(CodeMsg msg){
        return new Result<T>(msg);
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
