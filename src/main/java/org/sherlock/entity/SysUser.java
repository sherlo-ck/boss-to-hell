package org.sherlock.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author sherlock
 * @since 2024-06-16
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_user")
@Schema(name = "SysUser", description = "")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "序号")
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "用户id")
    @TableField("user_id")
    private Long userId;

    @Schema(description = "用户名")
    @TableField("user_name")
    private String userName;

    @Schema(description = "用户昵称")
    @TableField("nick_name")
    private String nickName;

    @Schema(description = "用户密码")
    @TableField("password")
    private String password;

    @Schema(description = "性别 0 男 1 女 2 未知")
    @TableField("sex")
    private String sex;

    @Schema(description = "用户手机号")
    @TableField("phone_number")
    private Integer phoneNumber;

    @Schema(description = "用户邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "用户类型 0 管理员 1 普通用户 2 vip用户")
    @TableField("user_type")
    private String userType;

    @Schema(description = "头像")
    @TableField("avatar")
    private String avatar;

    @Schema(description = "用户账号状态 0 正常 1 停用")
    @TableField("user_status")
    private String userStatus;

    @Schema(description = "创建人")
    @TableField("create_by")
    private String createBy;

    @Schema(description = "创建时间")
    @TableField("create_time")
    private Date createTime;

    @Schema(description = "更新人")
    @TableField("update_by")
    private String updateBy;

    @Schema(description = "更新时间")
    @TableField("update_time")
    private Date updateTime;

    @Schema(description = "删除标志 0 未删除 1已删除")
    @TableField("deleted")
    private String deleted;
}
