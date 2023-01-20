package com.mercadolindo.exception.handler;

import java.io.Serializable;
import java.util.List;

public class ExceptionResponseVO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String type;
	private String title;
	private List<String> detail;
	private String instance;
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<String> getDetail() {
		return detail;
	}
	public void setDetail(List<String> detail) {
		this.detail = detail;
	}
	public String getInstance() {
		return instance;
	}
	public void setInstance(String instance) {
		this.instance = instance;
	}
	
	


}
