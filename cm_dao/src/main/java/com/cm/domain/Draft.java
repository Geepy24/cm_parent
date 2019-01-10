package com.cm.domain;

import java.io.Serializable;
/**
 * 文章草稿表
 * @author mac
 *
 */
public class Draft implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private int draId ;
	private String artTitle ;
	private String artContent ;
	private String lastMod ;
	
	//外键字段
	private User user ;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public int getDraId() {
		return draId;
	}
	public void setDraId(int draId) {
		this.draId = draId;
	}
	public String getArtTitle() {
		return artTitle;
	}
	public void setArtTitle(String artTitle) {
		this.artTitle = artTitle;
	}
	public String getArtContent() {
		return artContent;
	}
	public void setArtContent(String artContent) {
		this.artContent = artContent;
	}
	
	public String getLastMod() {
		return lastMod;
	}
	public void setLastMod(String lastMod) {
		this.lastMod = lastMod;
	}

	@Override
	public String toString() {
		return "Draft [draId=" + draId + ", artTitle=" + artTitle + ", artContent=" + artContent + ", authorId="
				 + ", lastMod=" + lastMod + "]";
	}
	

	
	
	
	
}
