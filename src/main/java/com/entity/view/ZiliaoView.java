package com.entity.view;

import org.apache.tools.ant.util.DateUtils;
import com.annotation.ColumnInfo;
import com.entity.ZiliaoEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.commons.beanutils.BeanUtils;
import java.lang.reflect.InvocationTargetException;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import com.utils.DateUtil;

/**
* 学习资料
* 后端返回视图实体辅助类
* （通常后端关联的表或者自定义的字段需要返回使用）
*/
@TableName("ziliao")
public class ZiliaoView extends ZiliaoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	//当前表
	/**
	* 学习资料类型的值
	*/
	@ColumnInfo(comment="学习资料类型的字典表值",type="varchar(200)")
	private String ziliaoValue;
	/**
	* 学习资料审核的值
	*/
	@ColumnInfo(comment="学习资料审核的字典表值",type="varchar(200)")
	private String ziliaoYesnoValue;

	//级联表 教师
		/**
		* 教师姓名
		*/

		@ColumnInfo(comment="教师姓名",type="varchar(200)")
		private String jiaoshiName;
		/**
		* 教师手机号
		*/

		@ColumnInfo(comment="教师手机号",type="varchar(200)")
		private String jiaoshiPhone;
		/**
		* 教师身份证号
		*/

		@ColumnInfo(comment="教师身份证号",type="varchar(200)")
		private String jiaoshiIdNumber;
		/**
		* 教师头像
		*/

		@ColumnInfo(comment="教师头像",type="varchar(200)")
		private String jiaoshiPhoto;
		/**
		* 电子邮箱
		*/

		@ColumnInfo(comment="电子邮箱",type="varchar(200)")
		private String jiaoshiEmail;



	public ZiliaoView() {

	}

	public ZiliaoView(ZiliaoEntity ziliaoEntity) {
		try {
			BeanUtils.copyProperties(this, ziliaoEntity);
		} catch (IllegalAccessException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	//当前表的
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
	//当前表的
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


	//级联表的get和set 教师

		/**
		* 获取： 教师姓名
		*/
		public String getJiaoshiName() {
			return jiaoshiName;
		}
		/**
		* 设置： 教师姓名
		*/
		public void setJiaoshiName(String jiaoshiName) {
			this.jiaoshiName = jiaoshiName;
		}

		/**
		* 获取： 教师手机号
		*/
		public String getJiaoshiPhone() {
			return jiaoshiPhone;
		}
		/**
		* 设置： 教师手机号
		*/
		public void setJiaoshiPhone(String jiaoshiPhone) {
			this.jiaoshiPhone = jiaoshiPhone;
		}

		/**
		* 获取： 教师身份证号
		*/
		public String getJiaoshiIdNumber() {
			return jiaoshiIdNumber;
		}
		/**
		* 设置： 教师身份证号
		*/
		public void setJiaoshiIdNumber(String jiaoshiIdNumber) {
			this.jiaoshiIdNumber = jiaoshiIdNumber;
		}

		/**
		* 获取： 教师头像
		*/
		public String getJiaoshiPhoto() {
			return jiaoshiPhoto;
		}
		/**
		* 设置： 教师头像
		*/
		public void setJiaoshiPhoto(String jiaoshiPhoto) {
			this.jiaoshiPhoto = jiaoshiPhoto;
		}

		/**
		* 获取： 电子邮箱
		*/
		public String getJiaoshiEmail() {
			return jiaoshiEmail;
		}
		/**
		* 设置： 电子邮箱
		*/
		public void setJiaoshiEmail(String jiaoshiEmail) {
			this.jiaoshiEmail = jiaoshiEmail;
		}


	@Override
	public String toString() {
		return "ZiliaoView{" +
			", ziliaoValue=" + ziliaoValue +
			", ziliaoYesnoValue=" + ziliaoYesnoValue +
			", jiaoshiName=" + jiaoshiName +
			", jiaoshiPhone=" + jiaoshiPhone +
			", jiaoshiIdNumber=" + jiaoshiIdNumber +
			", jiaoshiPhoto=" + jiaoshiPhoto +
			", jiaoshiEmail=" + jiaoshiEmail +
			"} " + super.toString();
	}
}
