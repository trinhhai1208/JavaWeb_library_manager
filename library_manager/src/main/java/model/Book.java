package model;

public class Book {
	private int book_id;
    private String book_title;
    private String book_isbn;
    private int author_id;
    private int category_id;
    private int book_published_year;
    private int book_number_of_copies;
    private int book_quantity;
    private String author_name;



	public Book() {
    }

    public Book(String book_title, String book_isbn, int author_id, int category_id, int book_published_year, int book_number_of_copies) {
        this.book_title = book_title;
        this.book_isbn = book_isbn;
        this.author_id = author_id;
        this.category_id = category_id;
        this.book_published_year = book_published_year;
        this.book_number_of_copies = book_number_of_copies;
    }
    
    public Book(String book_title, String book_isbn, int author_id, int category_id, int book_published_year, int book_number_of_copies, int book_quantity) {
        this.book_title = book_title;
        this.book_isbn = book_isbn;
        this.author_id = author_id;
        this.category_id = category_id;
        this.book_published_year = book_published_year;
        this.book_number_of_copies = book_number_of_copies;
        this.book_quantity = book_quantity;
    }
    
    public Book(String book_title, String book_isbn, int author_id, int category_id, int book_published_year, int book_number_of_copies, int book_quantity, String author_name) {
        this.book_title = book_title;
        this.book_isbn = book_isbn;
        this.author_id = author_id;
        this.category_id = category_id;
        this.book_published_year = book_published_year;
        this.book_number_of_copies = book_number_of_copies;
        this.author_name = author_name;
        this.book_quantity = book_quantity;
    }
    
    public String getAuthor_name() {
		return author_name;
	}

	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}

	public Book(int book_id, String book_title, String book_isbn, int author_id, int category_id,
			int book_published_year, int book_number_of_copies) {
		super();
		this.book_id = book_id;
		this.book_title = book_title;
		this.book_isbn = book_isbn;
		this.author_id = author_id;
		this.category_id = category_id;
		this.book_published_year = book_published_year;
		this.book_number_of_copies = book_number_of_copies;
	}
    
    public int getBook_quantity() {
		return book_quantity;
	}

	public void setBook_quantity(int book_quantity) {
		this.book_quantity = book_quantity;
	}

	public int getBook_id() {
        return this.book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_title() {
        return this.book_title;
    }

    public void setBook_title(String book_title) {
        this.book_title = book_title;
    }

    public String getBook_isbn() {
        return this.book_isbn;
    }

    public void setBook_isbn(String book_isbn) {
        this.book_isbn = book_isbn;
    }

    public int getAuthor_id() {
        return this.author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getCategory_id() {
        return this.category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }

    public int getBook_published_year() {
        return this.book_published_year;
    }

    public void setBook_published_year(int book_published_year) {
        this.book_published_year = book_published_year;
    }

    public int getBook_number_of_copies() {
        return this.book_number_of_copies;
    }

    public void setBook_number_of_copies(int book_number_of_copies) {
        this.book_number_of_copies = book_number_of_copies;
    }
}
