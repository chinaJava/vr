package io.renren.entity;

import java.io.Serializable;

public class TGameLabelTempEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private Long gameid;
	
	private int labelid;
	private String labelName;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGameid() {
		return gameid;
	}

	public void setGameid(Long gameid) {
		this.gameid = gameid;
	}

	public int getLabelid() {
		return labelid;
	}

	public void setLabelid(int labelid) {
		this.labelid = labelid;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}
	
}
