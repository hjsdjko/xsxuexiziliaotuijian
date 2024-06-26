package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ZiliaoCollectionEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 学习资料收藏
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("ziliao_collection")
public class ZiliaoCollectionView extends ZiliaoCollectionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 类型的值
	*/
	@ColumnInfo(comment="类型的字典表值",type="varchar(200)")
	private String ziliaoCollectionValue;

	//级联表 学生
		/**
		* 学生姓名
		*/

		@ColumnInfo(comment="学生姓名",type="varchar(200)")
		private String yonghuName;
		/**
		* 学生手机号
		*/

		@ColumnInfo(comment="学生手机号",type="varchar(200)")
		private String yonghuPhone;
		/**
		* 学生身份证号
		*/

		@ColumnInfo(comment="学生身份证号",type="varchar(200)")
		private String yonghuIdNumber;
		/**
		* 学生头像
		*/

		@ColumnInfo(comment="学生头像",type="varchar(200)")
		private String yonghuPhoto;
		/**
		* 电子邮箱
		*/

		@ColumnInfo(comment="电子邮箱",type="varchar(200)")
		private String yonghuEmail;
	//级联表 学习资料
		/**
		* 学习资料标题
		*/

		@ColumnInfo(comment="学习资料标题",type="varchar(200)")
		private String ziliaoName;
		/**
		* 学习资料照片
		*/

		@ColumnInfo(comment="学习资料照片",type="varchar(200)")
		private String ziliaoPhoto;
		/**
		* 学习资料文件
		*/

		@ColumnInfo(comment="学习资料文件",type="varchar(200)")
		private String ziliaoFile;
					 
		/**
		* 学习资料 的 教师
		*/
		@ColumnInfo(comment="教师",type="int(11)")
		private Integer ziliaoJiaoshiId;
		/**
		* 赞
		*/

		@ColumnInfo(comment="赞",type="int(11)")
		private Integer zanNumber;
		/**
		* 踩
		*/

		@ColumnInfo(comment="踩",type="int(11)")
		private Integer caiNumber;
		/**
		* 学习资料类型
		*/
		@ColumnInfo(comment="学习资料类型",type="int(11)")
		private Integer ziliaoTypes;
			/**
			* 学习资料类型的值
			*/
			@ColumnInfo(comment="学习资料类型的字典表值",type="varchar(200)")
			private String ziliaoValue;
		/**
		* 详情
		*/

		@ColumnInfo(comment="详情",type="text")
		private String ziliaoContent;
		/**
		* 学习资料审核
		*/
		@ColumnInfo(comment="学习资料审核",type="int(11)")
		private Integer ziliaoYesnoTypes;
			/**
			* 学习资料审核的值
			*/
			@ColumnInfo(comment="学习资料审核的字典表值",type="varchar(200)")
			private String ziliaoYesnoValue;
		/**
		* 审核回复
		*/

		@ColumnInfo(comment="审核回复",type="text")
		private String ziliaoYesnoText;
		/**
		* 逻辑删除
		*/

		@ColumnInfo(comment="逻辑删除",type="int(11)")
		private Integer ziliaoDelete;



	public ZiliaoCollectionView() {

	}

	public ZiliaoCollectionView(ZiliaoCollectionEntity ziliaoCollectionEntity) {
		try {
			BeanUtils.copyProperties(this, ziliaoCollectionEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
	/**
	* 获取： 类型的值
	*/
	public String getZiliaoCollectionValue() {
		return ziliaoCollectionValue;
	}
	/**
	* 设置： 类型的值
	*/
	public void setZiliaoCollectionValue(String ziliaoCollectionValue) {
		this.ziliaoCollectionValue = ziliaoCollectionValue;
	}


	//级联表的get和set 学生

		/**
		* 获取： 学生姓名
		*/
		public String getYonghuName() {
			return yonghuName;
		}
		/**
		* 设置： 学生姓名
		*/
		public void setYonghuName(String yonghuName) {
			this.yonghuName = yonghuName;
		}

		/**
		* 获取： 学生手机号
		*/
		public String getYonghuPhone() {
			return yonghuPhone;
		}
		/**
		* 设置： 学生手机号
		*/
		public void setYonghuPhone(String yonghuPhone) {
			this.yonghuPhone = yonghuPhone;
		}

		/**
		* 获取： 学生身份证号
		*/
		public String getYonghuIdNumber() {
			return yonghuIdNumber;
		}
		/**
		* 设置： 学生身份证号
		*/
		public void setYonghuIdNumber(String yonghuIdNumber) {
			this.yonghuIdNumber = yonghuIdNumber;
		}

		/**
		* 获取： 学生头像
		*/
		public String getYonghuPhoto() {
			return yonghuPhoto;
		}
		/**
		* 设置： 学生头像
		*/
		public void setYonghuPhoto(String yonghuPhoto) {
			this.yonghuPhoto = yonghuPhoto;
		}

		/**
		* 获取： 电子邮箱
		*/
		public String getYonghuEmail() {
			return yonghuEmail;
		}
		/**
		* 设置： 电子邮箱
		*/
		public void setYonghuEmail(String yonghuEmail) {
			this.yonghuEmail = yonghuEmail;
		}
	//级联表的get和set 学习资料

		/**
		* 获取： 学习资料标题
		*/
		public String getZiliaoName() {
			return ziliaoName;
		}
		/**
		* 设置： 学习资料标题
		*/
		public void setZiliaoName(String ziliaoName) {
			this.ziliaoName = ziliaoName;
		}

		/**
		* 获取： 学习资料照片
		*/
		public String getZiliaoPhoto() {
			return ziliaoPhoto;
		}
		/**
		* 设置： 学习资料照片
		*/
		public void setZiliaoPhoto(String ziliaoPhoto) {
			this.ziliaoPhoto = ziliaoPhoto;
		}

		/**
		* 获取： 学习资料文件
		*/
		public String getZiliaoFile() {
			return ziliaoFile;
		}
		/**
		* 设置： 学习资料文件
		*/
		public void setZiliaoFile(String ziliaoFile) {
			this.ziliaoFile = ziliaoFile;
		}
		/**
		* 获取：学习资料 的 教师
		*/
		public Integer getZiliaoJiaoshiId() {
			return ziliaoJiaoshiId;
		}
		/**
		* 设置：学习资料 的 教师
		*/
		public void setZiliaoJiaoshiId(Integer ziliaoJiaoshiId) {
			this.ziliaoJiaoshiId = ziliaoJiaoshiId;
		}

		/**
		* 获取： 赞
		*/
		public Integer getZanNumber() {
			return zanNumber;
		}
		/**
		* 设置： 赞
		*/
		public void setZanNumber(Integer zanNumber) {
			this.zanNumber = zanNumber;
		}

		/**
		* 获取： 踩
		*/
		public Integer getCaiNumber() {
			return caiNumber;
		}
		/**
		* 设置： 踩
		*/
		public void setCaiNumber(Integer caiNumber) {
			this.caiNumber = caiNumber;
		}
		/**
		* 获取： 学习资料类型
		*/
		public Integer getZiliaoTypes() {
			return ziliaoTypes;
		}
		/**
		* 设置： 学习资料类型
		*/
		public void setZiliaoTypes(Integer ziliaoTypes) {
			this.ziliaoTypes = ziliaoTypes;
		}


			/**
			* 获取： 学习资料类型的值
			*/
			public String getZiliaoValue() {
				return ziliaoValue;
			}
			/**
			* 设置： 学习资料类型的值
			*/
			public void setZiliaoValue(String ziliaoValue) {
				this.ziliaoValue = ziliaoValue;
			}

		/**
		* 获取： 详情
		*/
		public String getZiliaoContent() {
			return ziliaoContent;
		}
		/**
		* 设置： 详情
		*/
		public void setZiliaoContent(String ziliaoContent) {
			this.ziliaoContent = ziliaoContent;
		}
		/**
		* 获取： 学习资料审核
		*/
		public Integer getZiliaoYesnoTypes() {
			return ziliaoYesnoTypes;
		}
		/**
		* 设置： 学习资料审核
		*/
		public void setZiliaoYesnoTypes(Integer ziliaoYesnoTypes) {
			this.ziliaoYesnoTypes = ziliaoYesnoTypes;
		}


			/**
			* 获取： 学习资料审核的值
			*/
			public String getZiliaoYesnoValue() {
				return ziliaoYesnoValue;
			}
			/**
			* 设置： 学习资料审核的值
			*/
			public void setZiliaoYesnoValue(String ziliaoYesnoValue) {
				this.ziliaoYesnoValue = ziliaoYesnoValue;
			}

		/**
		* 获取： 审核回复
		*/
		public String getZiliaoYesnoText() {
			return ziliaoYesnoText;
		}
		/**
		* 设置： 审核回复
		*/
		public void setZiliaoYesnoText(String ziliaoYesnoText) {
			this.ziliaoYesnoText = ziliaoYesnoText;
		}

		/**
		* 获取： 逻辑删除
		*/
		public Integer getZiliaoDelete() {
			return ziliaoDelete;
		}
		/**
		* 设置： 逻辑删除
		*/
		public void setZiliaoDelete(Integer ziliaoDelete) {
			this.ziliaoDelete = ziliaoDelete;
		}


	@Override
	public String toString() {
		return "ZiliaoCollectionView{" +
			", ziliaoCollectionValue=" + ziliaoCollectionValue +
			", ziliaoName=" + ziliaoName +
			", ziliaoPhoto=" + ziliaoPhoto +
			", ziliaoFile=" + ziliaoFile +
			", zanNumber=" + zanNumber +
			", caiNumber=" + caiNumber +
			", ziliaoContent=" + ziliaoContent +
			", ziliaoYesnoText=" + ziliaoYesnoText +
			", ziliaoDelete=" + ziliaoDelete +
			", yonghuName=" + yonghuName +
			", yonghuPhone=" + yonghuPhone +
			", yonghuIdNumber=" + yonghuIdNumber +
			", yonghuPhoto=" + yonghuPhoto +
			", yonghuEmail=" + yonghuEmail +
			"} " + super.toString();
	}
}
