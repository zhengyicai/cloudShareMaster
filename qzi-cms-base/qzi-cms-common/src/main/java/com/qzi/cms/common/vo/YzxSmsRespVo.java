/* 
 * 文件名：YzxRespVo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月31日
 * 版本号：v1.0
*/
package com.qzi.cms.common.vo;

/**
 * 云之讯状态码
 * @author qsy
 * @version v1.0
 * @date 2017年7月31日
 */
public class YzxSmsRespVo {
	private RespVo resp;
	/**
	 * @return the resp
	 */
	public RespVo getResp() {
		return resp;
	}

	/**
	 * @param resp the resp to set
	 */
	public void setResp(RespVo resp) {
		this.resp = resp;
	}

	public static class RespVo{
		private String respCode;
		private TemplateSMS templateSMS;
		private String failure;
		/**
		 * @return the failure
		 */
		public String getFailure() {
			return failure;
		}

		/**
		 * @param failure the failure to set
		 */
		public void setFailure(String failure) {
			this.failure = failure;
		}

		public static class TemplateSMS{
			private String createDate;
			private String smsId;
			/**
			 * @return the createDate
			 */
			public String getCreateDate() {
				return createDate;
			}
			/**
			 * @param createDate the createDate to set
			 */
			public void setCreateDate(String createDate) {
				this.createDate = createDate;
			}
			/**
			 * @return the smsId
			 */
			public String getSmsId() {
				return smsId;
			}
			/**
			 * @param smsId the smsId to set
			 */
			public void setSmsId(String smsId) {
				this.smsId = smsId;
			}
		}

		/**
		 * @return the templateSMS
		 */
		public TemplateSMS getTemplateSMS() {
			return templateSMS;
		}

		/**
		 * @param templateSMS the templateSMS to set
		 */
		public void setTemplateSMS(TemplateSMS templateSMS) {
			this.templateSMS = templateSMS;
		}

		/**
		 * @return the respCode
		 */
		public String getRespCode() {
			return respCode;
		}

		/**
		 * @param respCode the respCode to set
		 */
		public void setRespCode(String respCode) {
			this.respCode = respCode;
		}
	}
}
