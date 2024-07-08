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
 * 角色表
 * </p>
 *
 * @author sherlock
 * @since 2024-07-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_role")
@Schema(name = "SysRole", description = "角色表")
public class SysRole implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "角色名称")
    @TableField("name")
    private String name;

    @Schema(description = "角色权限字符")
    @TableField("role_key")
    private String roleKey;

    @Schema(description = "角色状态(0 正常 1 停用)")
    @TableField("status")
    private String status;

    @Schema(description = "删除状态(0 未删除 1 已删除)")
    @TableField("delete_flag")
    private String deleteFlag;

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

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;
}
