public class Book {
    private String title;
    private String author;
    private Student borrower;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.borrower = null;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Student getBorrower() {
        return borrower;
    }

    public boolean isBorrowed() {
        return borrower != null;
    }

    public String borrowBy(Student student) {
        if (this.borrower != null) {
            return "Book \"" + title + "\" is already borrowed by " + borrower.getName() + ".";
        }
        if (student.hasBorrowed()) {
            return student.getName() + " has already borrowed \"" + student.getBorrowedBook().getTitle() + "\".";
        }
        this.borrower = student;
        student.setBorrowedBook(this);
        return student.getName() + " with matric number " + student.getMatric()
                + " has borrow a book with title " + title + ".";
    }

    @Override
    public String toString() {
        return title + " (" + author + ")";
    }
}
