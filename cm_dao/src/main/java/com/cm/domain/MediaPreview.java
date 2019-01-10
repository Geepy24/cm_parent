package com.cm.domain;

import java.io.Serializable;

/**
 * 缩略图表
 * @author mac
 *
 */
public class MediaPreview implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer mpId ;
	private String mpUri ;
	private String mpName ;
	public Integer getMpId() {
		return mpId;
	}
	public void setMpId(Integer mpId) {
		this.mpId = mpId;
	}

	public String getMpUri() {
		return mpUri;
	}
	public void setMpUri(String mpUri) {
		this.mpUri = mpUri;
	}
	public String getMpName() {
		return mpName;
	}
	public void setMpName(String mpName) {
		this.mpName = mpName;
	}
	@Override
	public String toString() {
		return "MediaPreview [mpId=" + mpId + ", mpUri=" + mpUri + ", mpName=" + mpName + "]";
	}
	
	
	
	
}
