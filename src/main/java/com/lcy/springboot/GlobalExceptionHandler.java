package com.lcy.springboot;

import com.lcy.contant.InnoPlatformConstants;
import com.lcy.controller.common.BaseController;
import com.lcy.controller.common.ServiceException;
import com.lcy.dto.common.ResponseResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 统一异常处理
 * @author cjianyan@linewell.com
 *
 */
@ControllerAdvice
public class GlobalExceptionHandler extends BaseController {
	
	@ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public ResponseResult<String> serviceExcptionHandler(HttpServletRequest req, ServiceException e) throws Exception {
		
		// 未登录
		if(e.getCode() == InnoPlatformConstants.UNLOGIN_EXCEPTION_CODE) {
			return unloginInvalid();
		} else {
			 return this.renderError(e);
		}
    }
	
	@ExceptionHandler(value = MethodArgumentNotValidException.class)
    @ResponseBody
    public ResponseResult<String> exceptionHandler(HttpServletRequest req, MethodArgumentNotValidException e) throws Exception {
	        List<ObjectError> errors =e.getBindingResult().getAllErrors();
		return this.renderError(errors.get(0).getDefaultMessage());
    }
	
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseResult<String> exceptionHandler(HttpServletRequest req, Exception e) throws Exception {
        e.printStackTrace();
		return this.renderError("有点忙开个小差，稍后再试~");
    }
	

}
