package com.cm.domain;

import java.io.Serializable;
/**
 * 视频表
 * @author mac
 *
 */
public class Movie implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer movId ;
	private String movUri ;
	private String movName ;
	
	private MediaPreview mediaPreview ;
	public Integer getMovId() {
		return movId;
	}

	public void setMovId(Integer movId) {
		this.movId = movId;
	}

	public String getMovUri() {
		return movUri;
	}

	public void setMovUri(String movUri) {
		this.movUri = movUri;
	}

	public String getMovName() {
		return movName;
	}

	public void setMovName(String movName) {
		this.movName = movName;
	}

	public MediaPreview getMediaPreview() {
		return mediaPreview;
	}

	public void setMediaPreview(MediaPreview mediaPreview) {
		this.mediaPreview = mediaPreview;
	}



	@Override
	public String toString() {
		return "Movie [movId=" + movId + ", movUri=" + movUri + ", movName=" + movName + ", mediaPreview="
				+ mediaPreview + "]";
	}
	
	
	
	
}
