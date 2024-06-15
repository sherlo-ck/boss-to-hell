package org.sherlock.common.enums;

import lombok.Data;

public enum ErrorCodeEnum {
    ENUM;
    public enum ResultCode {
        SUCCESS(200, "成功"),
        FAIL(500, "失败");

        private int code;
        private String success;

        ResultCode(int code, String success) {
            this.code = code;
            this.success = success;
        }
        public int getCode() {
            return code;
        }
        public String getMessage() {
            return success;
        }
    }
}