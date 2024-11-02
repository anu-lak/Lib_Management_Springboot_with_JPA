package learning.lib_management.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String name;
    private String author;
  
    public Book() {
    }

    public Book (int id, String name, String author){
        this.bookId = id;
        this.name = name;
        this.author = author;
    }

    public int getBookId() {
        return bookId;
    }

    public String getName() {
        return name;
    }
    public String getAuthor(){
        return author;
    }

    public void setBookId(int id) {
        this.bookId = id;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    @Override
    public String toString() {
        return "Book [book_id=" + bookId + ", name=" + name + ", author=" + author + "]";
    }
    
}
