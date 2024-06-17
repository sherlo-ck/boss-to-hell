
package org.sherlock.common.entiry;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.sherlock.common.enums.ErrorCodeEnum;

/**
 * 结果集模型，用于封装API的返回结果。
 * @param <T> 返回的具体数据类型。
 */
@Data
@Schema(description = "结果集模型")
public class Result<T> {
    /**
     * 状态码，用于表示操作的结果状态。
     */
    @Schema(description = "状态码")
    private int code;
    /**
     * 操作消息，成功时可为空，失败时携带错误信息。
     */
    @Schema(description = "操作消息，成功时可为空，失败时携带错误信息")
    private String msg;
    /**
     * 成功标志，用于简洁表示操作是否成功。
     */
    @Schema(description = "成功标志")
    private String success;
    /**
     * 返回的具体数据。
     */
    @Schema(description = "返回的具体数据")
    private T data;

    /**
     * 私有构造方法，用于禁止外部直接实例化对象。
     */
    private Result() {
    }
    /**
     * 带参数的构造方法，用于通过Builder模式构建Result对象。
     * @param builder ResultBuilder的实例，包含构建Result所需的所有信息。
     */
    private Result(ResultBuilder<T> builder) {
        this.code = builder.code;
        this.msg = builder.msg;
        this.success = builder.success;
        this.data = builder.data;
    }

    /**
     * 构建一个表示成功的Result对象。
     * @param data 返回的具体数据。
     * @param msg 操作成功时的消息。
     * @return 构建好的Result对象。
     */
    public static <T> Result<T> success(T data, String msg) {
        Result<T> result = new Result<>();
        result.setData(data);
        result.setMsg(msg);
        result.setSuccess(ErrorCodeEnum.ResultCode.SUCCESS.getMessage());
        result.setCode(ErrorCodeEnum.ResultCode.SUCCESS.getCode());
        return result;
    }

    public static <T> Result<T> success(String msg) {
        Result<T> result = new Result<>();
        result.setMsg(msg);
        result.setSuccess(ErrorCodeEnum.ResultCode.SUCCESS.getMessage());
        result.setCode(ErrorCodeEnum.ResultCode.SUCCESS.getCode());
        return result;
    }

    /**
     * 构建一个表示失败的Result对象。
     * @param msg 操作失败时的错误信息。
     * @return 构建好的Result对象。
     */
    public static <T> Result<T> failure(String msg) {
        Result<T> result = new Result<>();
        result.setSuccess(ErrorCodeEnum.ResultCode.FAIL.getMessage());
        result.setMsg(msg);
        result.setCode(ErrorCodeEnum.ResultCode.FAIL.getCode());
        return result;
    }

    /**
     * Result的Builder模式类，用于简化Result对象的构建过程。
     * @param <T> 返回的具体数据类型。
     */
    public static class ResultBuilder<T> {
        private int code;
        private String msg;
        private String success;
        private T data;

        /**
         * 设置状态码。
         * @param code 操作的状态码。
         * @return 当前ResultBuilder实例。
         */
        public ResultBuilder code(int code) {
            this.code = code;
            return this;
        }

        /**
         * 设置消息。
         * @param msg 操作的消息。
         * @return 当前ResultBuilder实例。
         */
        public ResultBuilder message(String msg) {
            this.msg = msg;
            return this;
        }

        /**
         * 设置成功标志。
         * @param success 操作的成功标志。
         * @return 当前ResultBuilder实例。
         */
        public ResultBuilder success(String success) {
            this.success = success;
            return this;
        }

        /**
         * 设置返回数据。
         * @param data 返回的具体数据。
         * @return 当前ResultBuilder实例。
         */
        public ResultBuilder data(T data) {
            this.data = data;
            return this;
        }

        /**
         * 构建Result对象。
         * @return 构建好的Result对象。
         */
        public Result<T> build() {
            return new Result<T>(this);
        }
    }
}