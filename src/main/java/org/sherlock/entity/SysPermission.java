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
 * 权限表
 * </p>
 *
 * @author sherlock
 * @since 2024-07-08
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("sys_permission")
@Schema(name = "SysPermission", description = "权限表")
public class SysPermission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    @Schema(description = "权限名称")
    @TableField("permission_name")
    private String permissionName;

    @Schema(description = "后端uri")
    @TableField("path")
    private String path;

    @Schema(description = "前端路由")
    @TableField("route")
    private String route;

    @Schema(description = "菜单状态（0 显示 1隐藏）")
    @TableField("visible")
    private String visible;

    @Schema(description = "菜单状态（0 正常 1停用）")
    @TableField("status")
    private String status;

    @Schema(description = "权限标识")
    @TableField("perms")
    private String perms;

    @Schema(description = "菜单图标")
    @TableField("icon")
    private String icon;

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
    @TableField("delete_flag")
    private String deleteFlag;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;
}
