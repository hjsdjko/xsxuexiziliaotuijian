package com.entity;

import com.annotation.ColumnInfo;
import javax.validation.constraints.*;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.lang.reflect.InvocationTargetException;
import java.io.Serializable;
import java.util.*;
import org.apache.tools.ant.util.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.beanutils.BeanUtils;
import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.enums.FieldFill;
import com.utils.DateUtil;


/**
 * 学习资料
 *
 * @author 
 * @email
 */
@TableName("ziliao")
public class ZiliaoEntity<T> implements Serializable {
    private static final long serialVersionUID = 1L;


	public ZiliaoEntity() {

	}

	public ZiliaoEntity(T t) {
		try {
			BeanUtils.copyProperties(this, t);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


    /**
     * 主键
     */
    @TableId(type = IdType.AUTO)
    @ColumnInfo(comment="主键",type="int(11)")
    @TableField(value = "id")

    private Integer id;


    /**
     * 学习资料标题
     */
    @ColumnInfo(comment="学习资料标题",type="varchar(200)")
    @TableField(value = "ziliao_name")

    private String ziliaoName;


    /**
     * 学习资料照片
     */
    @ColumnInfo(comment="学习资料照片",type="varchar(200)")
    @TableField(value = "ziliao_photo")

    private String ziliaoPhoto;


    /**
     * 学习资料文件
     */
    @ColumnInfo(comment="学习资料文件",type="varchar(200)")
    @TableField(value = "ziliao_file")

    private String ziliaoFile;


    /**
     * 教师
     */
    @ColumnInfo(comment="教师",type="int(11)")
    @TableField(value = "jiaoshi_id")

    private Integer jiaoshiId;


    /**
     * 赞
     */
    @ColumnInfo(comment="赞",type="int(11)")
    @TableField(value = "zan_number")

    private Integer zanNumber;


    /**
     * 踩
     */
    @ColumnInfo(comment="踩",type="int(11)")
    @TableField(value = "cai_number")

    private Integer caiNumber;


    /**
     * 学习资料类型
     */
    @ColumnInfo(comment="学习资料类型",type="int(11)")
    @TableField(value = "ziliao_types")

    private Integer ziliaoTypes;


    /**
     * 详情
     */
    @ColumnInfo(comment="详情",type="text")
    @TableField(value = "ziliao_content")

    private String ziliaoContent;


    /**
     * 学习资料审核
     */
    @ColumnInfo(comment="学习资料审核",type="int(11)")
    @TableField(value = "ziliao_yesno_types")

    private Integer ziliaoYesnoTypes;


    /**
     * 审核回复
     */
    @ColumnInfo(comment="审核回复",type="text")
    @TableField(value = "ziliao_yesno_text")

    private String ziliaoYesnoText;


    /**
     * 逻辑删除
     */
    @ColumnInfo(comment="逻辑删除",type="int(11)")
    @TableField(value = "ziliao_delete")

    private Integer ziliaoDelete;


    /**
     * 录入时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="录入时间",type="timestamp")
    @TableField(value = "insert_time",fill = FieldFill.INSERT)

    private Date insertTime;


    /**
     * 创建时间
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat
    @ColumnInfo(comment="创建时间",type="timestamp")
    @TableField(value = "create_time",fill = FieldFill.INSERT)

    private Date createTime;


    /**
	 * 获取：主键
	 */
    public Integer getId() {
        return id;
    }
    /**
	 * 设置：主键
	 */

    public void setId(Integer id) {
        this.id = id;
    }
    /**
	 * 获取：学习资料标题
	 */
    public String getZiliaoName() {
        return ziliaoName;
    }
    /**
	 * 设置：学习资料标题
	 */

    public void setZiliaoName(String ziliaoName) {
        this.ziliaoName = ziliaoName;
    }
    /**
	 * 获取：学习资料照片
	 */
    public String getZiliaoPhoto() {
        return ziliaoPhoto;
    }
    /**
	 * 设置：学习资料照片
	 */

    public void setZiliaoPhoto(String ziliaoPhoto) {
        this.ziliaoPhoto = ziliaoPhoto;
    }
    /**
	 * 获取：学习资料文件
	 */
    public String getZiliaoFile() {
        return ziliaoFile;
    }
    /**
	 * 设置：学习资料文件
	 */

    public void setZiliaoFile(String ziliaoFile) {
        this.ziliaoFile = ziliaoFile;
    }
    /**
	 * 获取：教师
	 */
    public Integer getJiaoshiId() {
        return jiaoshiId;
    }
    /**
	 * 设置：教师
	 */

    public void setJiaoshiId(Integer jiaoshiId) {
        this.jiaoshiId = jiaoshiId;
    }
    /**
	 * 获取：赞
	 */
    public Integer getZanNumber() {
        return zanNumber;
    }
    /**
	 * 设置：赞
	 */

    public void setZanNumber(Integer zanNumber) {
        this.zanNumber = zanNumber;
    }
    /**
	 * 获取：踩
	 */
    public Integer getCaiNumber() {
        return caiNumber;
    }
    /**
	 * 设置：踩
	 */

    public void setCaiNumber(Integer caiNumber) {
        this.caiNumber = caiNumber;
    }
    /**
	 * 获取：学习资料类型
	 */
    public Integer getZiliaoTypes() {
        return ziliaoTypes;
    }
    /**
	 * 设置：学习资料类型
	 */

    public void setZiliaoTypes(Integer ziliaoTypes) {
        this.ziliaoTypes = ziliaoTypes;
    }
    /**
	 * 获取：详情
	 */
    public String getZiliaoContent() {
        return ziliaoContent;
    }
    /**
	 * 设置：详情
	 */

    public void setZiliaoContent(String ziliaoContent) {
        this.ziliaoContent = ziliaoContent;
    }
    /**
	 * 获取：学习资料审核
	 */
    public Integer getZiliaoYesnoTypes() {
        return ziliaoYesnoTypes;
    }
    /**
	 * 设置：学习资料审核
	 */

    public void setZiliaoYesnoTypes(Integer ziliaoYesnoTypes) {
        this.ziliaoYesnoTypes = ziliaoYesnoTypes;
    }
    /**
	 * 获取：审核回复
	 */
    public String getZiliaoYesnoText() {
        return ziliaoYesnoText;
    }
    /**
	 * 设置：审核回复
	 */

    public void setZiliaoYesnoText(String ziliaoYesnoText) {
        this.ziliaoYesnoText = ziliaoYesnoText;
    }
    /**
	 * 获取：逻辑删除
	 */
    public Integer getZiliaoDelete() {
        return ziliaoDelete;
    }
    /**
	 * 设置：逻辑删除
	 */

    public void setZiliaoDelete(Integer ziliaoDelete) {
        this.ziliaoDelete = ziliaoDelete;
    }
    /**
	 * 获取：录入时间
	 */
    public Date getInsertTime() {
        return insertTime;
    }
    /**
	 * 设置：录入时间
	 */

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
    /**
	 * 获取：创建时间
	 */
    public Date getCreateTime() {
        return createTime;
    }
    /**
	 * 设置：创建时间
	 */

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Ziliao{" +
            ", id=" + id +
            ", ziliaoName=" + ziliaoName +
            ", ziliaoPhoto=" + ziliaoPhoto +
            ", ziliaoFile=" + ziliaoFile +
            ", jiaoshiId=" + jiaoshiId +
            ", zanNumber=" + zanNumber +
            ", caiNumber=" + caiNumber +
            ", ziliaoTypes=" + ziliaoTypes +
            ", ziliaoContent=" + ziliaoContent +
            ", ziliaoYesnoTypes=" + ziliaoYesnoTypes +
            ", ziliaoYesnoText=" + ziliaoYesnoText +
            ", ziliaoDelete=" + ziliaoDelete +
            ", insertTime=" + DateUtil.convertString(insertTime,"yyyy-MM-dd") +
            ", createTime=" + DateUtil.convertString(createTime,"yyyy-MM-dd") +
        "}";
    }
}
