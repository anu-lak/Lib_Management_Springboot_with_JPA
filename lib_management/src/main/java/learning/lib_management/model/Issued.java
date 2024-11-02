package learning.lib_management.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "issued")
public class Issued {
    
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int index_no;

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "bookId",insertable=false, updatable=false)
    private Book book;

    private int bookId;
    private LocalDate issued_date;


    
    public Issued() {
    }
    public Issued(Book book, LocalDate issued_date) {
        this.book = book;
        this.issued_date = issued_date;
    }
    public int getIndex_no() {
        return index_no;
    }
    public void setIndex_no(int index_no) {
        this.index_no = index_no;
    }
    public int getBookId() {
        return bookId;
    }
    public void setBookId(int book_id) {
        this.bookId = book_id;
    }
    public LocalDate getIssued_date() {
        return issued_date;
    }
    public void setIssued_date(LocalDate issued_date) {
        this.issued_date = issued_date;
    }
    
}
