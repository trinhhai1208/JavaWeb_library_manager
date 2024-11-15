package model;

public class Member {
	private int member_id;
	private String member_name;
	private String member_address;
	private String member_phone;
	private String member_email;
	private int total_loans;
	
	public Member(int member_id, String member_name, String member_address, String member_phone,
			String member_email) {
		super();
		this.member_id = member_id;
		this.member_name = member_name;
		this.member_address = member_address;
		this.member_phone = member_phone;
		this.member_email = member_email;
	}
	
	public Member(String member_name, String member_address, String member_phone,
			String member_email) {
		super();
		this.member_name = member_name;
		this.member_address = member_address;
		this.member_phone = member_phone;
		this.member_email = member_email;
	}

	public Member() {
		// TODO Auto-generated constructor stub
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public String getMember_name() {
		return member_name;
	}

	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}

	public String getMember_address() {
		return member_address;
	}

	public void setMember_address(String member_address) {
		this.member_address = member_address;
	}

	public String getMember_phone() {
		return member_phone;
	}

	public void setMember_phone(String member_phone) {
		this.member_phone = member_phone;
	}

	public String getMember_email() {
		return member_email;
	}

	public void setMember_email(String member_email) {
		this.member_email = member_email;
	}

	public int getTotal_loans() {
		return total_loans;
	}

	public void setTotal_loans(int total_loans) {
		this.total_loans = total_loans;
	}

	@Override
	public String toString() {
		return "MemberObject [member_id=" + member_id + ", member_name=" + member_name + ", member_address="
				+ member_address + ", member_phone=" + member_phone + ", member_email=" + member_email + "]";
	}

	
	
}
