package com.lcy.controller.common;


/**
 * 服务异常类
 *
 * @author tjianqun@linewell.com
 * @date 2017年5月15日 下午4:06:59
 */
public class ServiceException extends RuntimeException {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -3112363444339436808L;

    /**
     * 异常信息
     */
    private String msg;

    /**
     * 异常编码
     */
    private int code;

    public ServiceException() {
    }

    public ServiceException(int code) {
        this.code = code;
    }

    /**
     * 异常信息
     *
     * @param message
     */
    public ServiceException(String message) {
        this.msg = message;
    }

    /**
     * @param message 异常信息
     * @param code    异常编码
     */
    public ServiceException(String message, int code) {
        this.code = code;
        this.msg = message;
    }

    /**
     * 获取异常信息
     *
     * @return
     */
    public String getMsg() {
        return msg;
    }

    /**
     * 设置异常信息
     *
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 获取异常编码
     *
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * 设置异常编码
     *
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }

}
