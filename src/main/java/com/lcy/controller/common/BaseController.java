package com.lcy.controller.common;


import com.lcy.dto.common.ResponseResult;
import com.lcy.type.common.CommonErrorCodeType;

/**
 * 基础返回结果
 * @author Think
 *
 */
public class BaseController {

	/**
	 * 未登录代号
	 */
	public static final int UN_LOGIN_CODE = 0;

	/**
	 * 失败状态
	 */
	public static final int STATUS_ERROR = 0;

	/**
	 * 成功状态
	 */
	public static final int STATUS_SUCCESS = 1;

	/**
	 * 头部参数异常代码
	 */
	private static final int OTHER_ERROR_CODE = 88888888;

	/**
	 * 渲染失败数据
	 * 
	 * @param <T>
	 * @return result
	 */
	public static <T> ResponseResult<T> renderError(int code) {
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(STATUS_ERROR);
		result.setCode(code);
		return result;
	}

	public static <T> ResponseResult<T> renderInvalidArgument() {
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(STATUS_ERROR);
		result.setCode(CommonErrorCodeType.ILLEGAL_PARAM.getNo());
		result.setMessage(CommonErrorCodeType.ILLEGAL_PARAM.getName());
		return result;
	}

	/**
	 * 未登录
	 * 
	 * @return
	 */
	public static <T> ResponseResult<T> unloginInvalid() {
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(STATUS_ERROR);
		result.setCode(UN_LOGIN_CODE);
		result.setMessage("用户未登录");
		return result;
	}

	/**
	 * 渲染失败数据
	 *
	 * @param <T>
	 * @return result
	 */
	public static <T> ResponseResult<T> renderHeaderError() {
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(STATUS_ERROR);
		result.setCode(OTHER_ERROR_CODE);
		result.setMessage("请求头部参数格式错误");
		return result;
	}

	/**
	 * 渲染失败数据
	 * 
	 * @param <T>
	 * @return result
	 */
	public static <T> ResponseResult<T> renderError(String message) {
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(STATUS_ERROR);
		result.setMessage(message);
		return result;
	}

	/**
	 * 渲染失败数据
	 * 
	 * @param <T>
	 * @return result
	 */
	public static <T> ResponseResult<T> renderError(int code, String message) {
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(STATUS_ERROR);
		result.setCode(code);
		result.setMessage(message);
		return result;
	}

	/**
	 * 渲染失败数据
	 * 
	 * @param code 
	 * @return T
	 */
	public static <T> ResponseResult<T> renderError(int code, String message,T content) {
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(STATUS_ERROR);
		result.setCode(code);
		result.setMessage(message);
		result.setContent(content);
		return result;
	}

	/**
	 * 异常处理
	 * 
	 * @param ex
	 * @return
	 */
	public static <T> ResponseResult<T> renderError(ServiceException ex) {
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(STATUS_ERROR);
		result.setCode(ex.getCode());
		result.setMessage(ex.getMsg());
		return result;
	}

	/**
	 * 渲染失败数据
	 * 
	 * @param <T>
	 * @return result
	 */
	public static <T> ResponseResult<T> renderError(String message, T content) {
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(STATUS_ERROR);
		result.setMessage(message);
		result.setContent(content);
		return result;
	}

	/**
	 * 渲染失败数据
	 *
	 * @return result
	 */
	@SuppressWarnings("unchecked")
	public static <T> ResponseResult<T> renderSuccess() {
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(STATUS_SUCCESS);
		result.setContent((T) Boolean.TRUE);
		return result;
	}

	/**
	 * 渲染成功数据
	 *
	 * @return result
	 */
	public static <T> ResponseResult<T> renderSuccess(T content) {
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(STATUS_SUCCESS);
		result.setContent(content);
		return result;
	}

	/**
	 * 渲染失败数据
	 *
	 * @return result
	 */
	public static <T> ResponseResult<T> renderSuccess(String message, T content) {
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(STATUS_SUCCESS);
		result.setMessage(message);
		result.setContent(content);
		return result;
	}
	
	public static <T> ResponseResult<T> renderSuccess(Integer code,String message, T content) {
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(STATUS_SUCCESS);
		result.setMessage(message);
		result.setContent(content);
		result.setCode(code);
		return result;
	}
	
	public static <T> ResponseResult<T> renderSuccess(Integer code,String message) {
		ResponseResult<T> result = new ResponseResult<T>();
		result.setStatus(STATUS_SUCCESS);
		result.setMessage(message);
		result.setCode(code);
		return result;
	}

	
	
}
