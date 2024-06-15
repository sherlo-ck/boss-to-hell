package org.sherlock.common.exception;

import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
@EqualsAndHashCode(callSuper = true)
public class BusinessExcepiton  extends RuntimeException {
    private static final long serialVersionUID = 1L;

    private Integer code;
    private static final String ERROR = "系统开小差~正在殴打程序员！";

    public BusinessExcepiton() {
        super(ERROR);
    }

    public BusinessExcepiton(Integer code, String message) {
        super(StringUtils.isEmpty(message) ? ERROR : message);
        this.code = code;
    }
    public BusinessExcepiton(String message) {
        super(message);
    }
}
