package com.cm.domain;

import java.io.Serializable;
/**
 * 	����Ա��
 * @author Huangjiping
 *
 */
public class Ads implements Serializable {

	private int adsId ;
	private String adsName ;
	private int userId ;  //���ڴ���ǹ���Ա���û���ID
	
	

	public int getAdsId() {
		return adsId;
	}
	public void setAdsId(int adsId) {
		this.adsId = adsId;
	}
	public String getAdsName() {
		return adsName;
	}
	public void setAdsName(String adsName) {
		this.adsName = adsName;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return "Ads [adsId=" + adsId + ", adsName=" + adsName + ", userId=" + userId + "]";
	}

	
	
	
	
}
