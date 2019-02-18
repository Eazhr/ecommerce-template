package com.sdata.ecommerce.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyDescription;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.sdata.ecommerce.exception.ErrorCode;

/**
 * @author nedli
 */
@JsonPropertyOrder({"code", "subCode", "message", "data"})
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse<T> {
    @JsonProperty("code")
    @JsonPropertyDescription("http code")
    protected Integer code;

    @JsonProperty("subCode")
    @JsonPropertyDescription("sub code")
    protected String subCode;

    @JsonProperty("message")
    @JsonPropertyDescription("message")
    protected String message;

    @JsonProperty("data")
    @JsonPropertyDescription("payload of response")
    protected T data;

    public BaseResponse() {
    }

    public BaseResponse(T data) {
        this.data = data;
        composeMessage(ErrorCode.SUCCESS);
    }

    public BaseResponse(ErrorCode errorCode) {
        composeMessage(errorCode);
    }

    public void composeMessage(ErrorCode errorCode) {
        this.setCode(errorCode.getHttpCode());
        this.setSubCode(errorCode.getCode());
        this.setMessage(errorCode.getErrorMsg());
    }

    /**
     * Gets code
     *
     * @return value of code
     */
    public Integer getCode() {
        return code;
    }

    /**
     * Sets code
     */
    public void setCode(Integer code) {
        this.code = code;
    }

    /**
     * Gets subCode
     *
     * @return value of subCode
     */
    public String getSubCode() {
        return subCode;
    }

    /**
     * Sets subCode
     */
    public void setSubCode(String subCode) {
        this.subCode = subCode;
    }

    /**
     * Gets message
     *
     * @return value of message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets message
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Gets data
     *
     * @return value of data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets data
     */
    public void setData(T data) {
        this.data = data;
    }
}
