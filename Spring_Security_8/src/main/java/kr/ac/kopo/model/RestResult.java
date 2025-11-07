package kr.ac.kopo.model;

import java.util.List;

import org.springframework.validation.ObjectError;

public class RestResult<T> {
	private boolean result;
	private String message;
	private T body;
	private List<ObjectError> errors;
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}
	public List<ObjectError> getErrors() {
		return errors;
	}
	public void setErrors(List<ObjectError> errors) {
		this.errors = errors;
	}
	
	
}
