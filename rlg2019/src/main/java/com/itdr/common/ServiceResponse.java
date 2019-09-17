package com.itdr.common;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ServiceResponse<T> implements Serializable {
    private Integer status;
    private T data;
    private String msg;

    //请求成功，返回三种信息
    private ServiceResponse(Integer status, T data, String msg) {
        this.status = status;
        this.data = data;
        this.msg = msg;

    }

    //请求成功，返回状态码和数据
    private ServiceResponse(Integer status, T data) {
        this.status = status;
        this.data = data;
    }

    //请求成功，返回数据和默认状态码
    private ServiceResponse(T data) {
        this.status = 0;
        this.data = data;
    }

    //请求失败，返回状态码和失败信息
    private ServiceResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }


    //请求成功，返回三种信息
    public static <T> ServiceResponse successSR(Integer status, T data, String msg){
        return new ServiceResponse(status,data,msg);
    }
    //请求成功，返回状态码和数据
    public static <T> ServiceResponse successSR(Integer status, T data){
        return new ServiceResponse(status,data);
    }
    //请求成功，返回数据和默认状态码
    public static <T> ServiceResponse successSR(T data){
        return new ServiceResponse(data);
    }
    //请求失败，返回状态码和失败信息
    public static <T> ServiceResponse defeatedSR(Integer status,String msg){
        return new ServiceResponse(status,msg);
    }

    //查看请求是否成功
}
