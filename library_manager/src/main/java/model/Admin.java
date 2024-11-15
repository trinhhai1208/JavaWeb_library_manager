package model;

public class Admin {
	private int admin_id;
	private String username;
	private String password;
	private String admin_name;
	private String admin_address;
	private String admin_phone;
	private String admin_email;
	
	public Admin() {
		
	}
	
	public Admin(int admin_id, String username, String password, String admin_name, String admin_address,
			String admin_phone, String admin_email) {
		super();
		this.admin_id = admin_id;
		this.username = username;
		this.password = password;
		this.admin_name = admin_name;
		this.admin_address = admin_address;
		this.admin_phone = admin_phone;
		this.admin_email = admin_email;
	}
	public int getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(int admin_id) {
		this.admin_id = admin_id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAdmin_name() {
		return admin_name;
	}
	public void setAdmin_name(String admin_name) {
		this.admin_name = admin_name;
	}
	public String getAdmin_address() {
		return admin_address;
	}
	public void setAdmin_address(String admin_address) {
		this.admin_address = admin_address;
	}
	public String getAdmin_phone() {
		return admin_phone;
	}
	public void setAdmin_phone(String admin_phone) {
		this.admin_phone = admin_phone;
	}
	public String getAdmin_email() {
		return admin_email;
	}
	public void setAdmin_email(String admin_email) {
		this.admin_email = admin_email;
	}
	
	
}
