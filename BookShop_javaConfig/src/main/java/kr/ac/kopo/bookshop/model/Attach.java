package kr.ac.kopo.bookshop.model;

public class Attach{
	private Long code;
	private Long bookCode;
	private String filename;
	private String uuid;
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public Long getBookCode() {
		return bookCode;
	}
	public void setBookCode(Long bookCode) {
		this.bookCode = bookCode;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
}
