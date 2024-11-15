package model;

public class Author {
	private int author_id;
	private String author_name;
	private String author_biography;
	
	public Author() {
	}
	
	public Author(int author_id, String author_name, String author_biography) {
		super();
		this.author_id = author_id;
		this.author_name = author_name;
		this.author_biography = author_biography;
	}
	public Author( String author_name, String author_biography) {
		super();
		this.author_name = author_name;
		this.author_biography = author_biography;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public String getAuthor_biography() {
		return author_biography;
	}
	public void setAuthor_biography(String author_biography) {
		this.author_biography = author_biography;
	}
	@Override
	public String toString() {
		return "Author [author_id=" + author_id + ", author_name=" + author_name + ", author_biography="
				+ author_biography + "]";
	}
	
	
}
