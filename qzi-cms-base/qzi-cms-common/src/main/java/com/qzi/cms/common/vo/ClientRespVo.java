/* 
 * 文件名：ClientRespVo.java  
 * 版权：Copyright 2016-2017 炎宝网络科技  All Rights Reserved by
 * 修改人：邱深友  
 * 创建时间：2017年7月25日
 * 版本号：v1.0
*/
package com.qzi.cms.common.vo;

/**
 * 云之讯返回vo对象
 * @author qsy
 * @version v1.0
 * @date 2017年7月25日
 */
public class ClientRespVo {
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
		private Client client;

		/**
		 * @return the client
		 */
		public Client getClient() {
			return client;
		}

		/**
		 * @param client the client to set
		 */
		public void setClient(Client client) {
			this.client = client;
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
		public static class Client{
			private String clientNumber;
			private String clientPwd;
			private String createDate;
			private String loginToken;
			private String userId;
			/**
			 * @return the clientNumber
			 */
			public String getClientNumber() {
				return clientNumber;
			}
			/**
			 * @param clientNumber the clientNumber to set
			 */
			public void setClientNumber(String clientNumber) {
				this.clientNumber = clientNumber;
			}
			/**
			 * @return the clientPwd
			 */
			public String getClientPwd() {
				return clientPwd;
			}
			/**
			 * @param clientPwd the clientPwd to set
			 */
			public void setClientPwd(String clientPwd) {
				this.clientPwd = clientPwd;
			}
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
			 * @return the loginToken
			 */
			public String getLoginToken() {
				return loginToken;
			}
			/**
			 * @param loginToken the loginToken to set
			 */
			public void setLoginToken(String loginToken) {
				this.loginToken = loginToken;
			}
			/**
			 * @return the userId
			 */
			public String getUserId() {
				return userId;
			}
			/**
			 * @param userId the userId to set
			 */
			public void setUserId(String userId) {
				this.userId = userId;
			}
		}
		
	}
	
}
