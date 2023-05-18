package io.github.marcperez06.link_checker_web_service.domain.errors;

import lombok.Data;

public class LinkCheckerError {

	private String field;
	private String message;
	
	public LinkCheckerError(String field, String message) {
		this.field = field;
		this.message = message;
	}

	public String getField() {
		return this.field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public String getMessage() {
		return this.message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
}