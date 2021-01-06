package com.lcy.controller.common;

import com.lcy.util.file.fast.FastResult;
import com.lcy.dto.common.ResponseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * 附件
 *
 * @author: lchunyi@linewell.com
 * @since: 2019/3/8 16:43
 */
public interface IAttachmentController {

    /**
     * 获取token
     * @return
     */
    ResponseResult<Map<String,String>> getToken();

    /**
     * 批量上传图片
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return
     */
    ResponseResult<List<FastResult>> uploadPics(HttpServletRequest request, HttpServletResponse response);

    /**
     * 批量上传文件
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return
     */
    ResponseResult<List<FastResult>> uploadFiles(HttpServletRequest request, HttpServletResponse response);
}
