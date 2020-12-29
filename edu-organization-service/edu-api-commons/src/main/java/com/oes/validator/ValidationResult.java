package com.oes.validator;

import java.util.HashMap;
import java.util.Map;

public class ValidationResult {

    //校验结果是否正确
    //返回true校验通过没有错误
    //返回false校验没通过有错误
    private boolean hasErrors = false;

    private Map<String, String> errorMsgMap = new HashMap<>();

    public boolean isHasErrors() {
        return hasErrors;
    }

    public void setHasErrors(boolean hasErrors) {
        this.hasErrors = hasErrors;
    }

    public Map<String, String> getErrorMsgMap() {
        return errorMsgMap;
    }

    public void setErrorMsgMap(Map<String, String> errorMsgMap) {
        this.errorMsgMap = errorMsgMap;
    }

    //实现通用的通过格式化字符串信息获取错误结果的msg方法
    public String getErrMsg(){
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> stringStringEntry : errorMsgMap.entrySet()) {
            sb.append(stringStringEntry.getValue()).append(":");
            sb.append(stringStringEntry.getKey()).append(";");
        }
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
