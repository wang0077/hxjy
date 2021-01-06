package com.lcy.springboot.filter;

import com.google.gson.JsonObject;
import com.lcy.util.common.GsonUtils;
import com.lcy.util.common.IpUtil;
import jodd.io.StreamUtil;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BehaviorHttpServletRequestWrapper extends
        HttpServletRequestWrapper {

    private final byte[] body; // 报文

    private boolean error = false;

    private int errorCode;

    private String errorMessage = null;

    private byte[] getBody(HttpServletRequest request) throws IOException {

        String uri = request.getRequestURI();

        byte[] temBody = StreamUtil.readBytes(request.getInputStream());

        try {

            JsonObject jsonObject = GsonUtils.getJsonObject(new String(temBody, "utf-8"));

            if (jsonObject != null && jsonObject.has("clientParams")) {
                JsonObject clientParamsJsonObject = jsonObject.get("clientParams").getAsJsonObject();
                clientParamsJsonObject.addProperty("ip", IpUtil.getLocalIP(request));
                jsonObject.add("clientParams", clientParamsJsonObject);
            }

            String jsonStr = jsonObject.toString();

            return jsonStr.getBytes("utf-8");

        } catch (Exception ex) {
            System.out.print("post 不是json");
        }


        return temBody;
    }

    public BehaviorHttpServletRequestWrapper(HttpServletRequest request)
            throws IOException {

        super(request);
        body = getBody(request);

    }

    @Override
    public BufferedReader getReader() throws IOException {
        return new BufferedReader(new InputStreamReader(getInputStream()));
    }

    @Override
    public ServletInputStream getInputStream() throws IOException {
        final ByteArrayInputStream bais = new ByteArrayInputStream(body);

        return new ServletInputStream() {

            @Override
            public int read() throws IOException {
                return bais.read();
            }

            @Override
            public boolean isFinished() {
                return false;
            }

            @Override
            public boolean isReady() {
                return false;
            }

            @Override
            public void setReadListener(ReadListener arg0) {

            }

        };
    }

    /**
     * 获取errorMessage
     *
     * @return the errorMessage
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * 获取error
     *
     * @return the error
     */
    public boolean isError() {
        return error;
    }

    /**
     * 获取errorCode
     *
     * @return the errorCode
     */
    public int getErrorCode() {
        return errorCode;
    }

}
