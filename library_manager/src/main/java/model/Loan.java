package model;

import java.sql.Date;

public class Loan {
	
	private int loan_id;
	private int book_id;
	private int member_id;
	private Date loan_date;
	private Date loan_due_date;
	private Date loan_return_date;
	private String book_title;
	private String member_name;
	
	public Loan() {
		
	}
	
	public Loan(int book_id, int member_id, Date loan_date, Date loan_due_date, Date loan_return_date) {
		super();
		this.book_id = book_id;
		this.member_id = member_id;
		this.loan_date = loan_date;
		this.loan_due_date = loan_due_date;
		this.loan_return_date = loan_return_date;

	}
	
	public Loan(int loan_id, int book_id, int member_id, Date loan_date, Date loan_due_date, Date loan_return_date,
			String book_title, String member_name) {
		super();
		this.loan_id = loan_id;
		this.book_id = book_id;
		this.member_id = member_id;
		this.loan_date = loan_date;
		this.loan_due_date = loan_due_date;
		this.loan_return_date = loan_return_date;
		this.book_title = book_title;
		this.member_name = member_name;
	}



	public String getBook_title() {
		return book_title;
	}


	public void setBook_title(String book_title) {
		this.book_title = book_title;
	}


	public String getMember_name() {
		return member_name;
	}


	public void setMember_name(String member_name) {
		this.member_name = member_name;
	}


	public Loan(int loan_id, int book_id, int member_id, Date loan_date, Date loan_due_date, Date loan_return_date) {
		super();
		this.loan_id = loan_id;
		this.book_id = book_id;
		this.member_id = member_id;
		this.loan_date = loan_date;
		this.loan_due_date = loan_due_date;
		this.loan_return_date = loan_return_date;
	}

	public int getLoan_id() {
		return loan_id;
	}

	public void setLoan_id(int loan_id) {
		this.loan_id = loan_id;
	}

	public int getBook_id() {
		return book_id;
	}

	public void setBook_id(int book_id) {
		this.book_id = book_id;
	}

	public int getMember_id() {
		return member_id;
	}

	public void setMember_id(int member_id) {
		this.member_id = member_id;
	}

	public Date getLoan_date() {
		return loan_date;
	}

	public void setLoan_date(Date loan_date) {
		this.loan_date = loan_date;
	}

	public Date getLoan_due_date() {
		return loan_due_date;
	}

	public void setLoan_due_date(Date loan_due_date) {
		this.loan_due_date = loan_due_date;
	}

	public Date getLoan_return_date() {
		return loan_return_date;
	}

	public void setLoan_return_date(Date loan_return_date) {
		this.loan_return_date = loan_return_date;
	}

	
}
