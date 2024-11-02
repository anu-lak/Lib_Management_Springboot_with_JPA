package learning.lib_management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.transaction.Transactional;
import learning.lib_management.model.Book;
import learning.lib_management.model.Issued;
import learning.lib_management.repository.BookRepo;
import learning.lib_management.repository.IssuedRepo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;


@RestController
@RequestMapping("/lib")
public class MainController {

    @Autowired
    private BookRepo bookRepo;

    @Autowired
    private IssuedRepo issuedRepo;
    
    @PostMapping("/addnewbook")
    public String addBook(@RequestBody Book book){
        bookRepo.save(book);
        return("successfully added");
    }

    @GetMapping("/explorebook")
    public Optional<Book> getBook(@RequestParam int id){
        return bookRepo.findById(id);
    }
    
    @GetMapping("/explore")
    public List<Book> getAllBooks() {
        return bookRepo.findAll();
    }

    @PutMapping("/borrowbook")
    public ResponseEntity<?> borrowBook(@RequestParam int id) {
        try{

            Optional<Book> bookOptional = bookRepo.findById(id);
            if(bookOptional.isPresent()){
                Book book = bookOptional.get();
                if(issuedRepo.existsByBook_BookId(book.getBookId())){
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("book is already issued");
                }else{
                    Issued issued = new Issued(book, LocalDate.now());
                    issued.setBookId(book.getBookId());
                    issuedRepo.save(issued);
                    return ResponseEntity.status(HttpStatus.OK).body("Book with ID " + book.getBookId() + " borrowed successfully");
                }
            }else{
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Book not found");
                } 
        }catch (IllegalArgumentException e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("invalid book_id");
        }
    }

    @Transactional
    @PutMapping("/returnbook")
    public ResponseEntity<?> returnBook(@RequestParam int id){
        if(issuedRepo.existsByBook_BookId(id)){
            issuedRepo.deleteByBookId(id);
            return ResponseEntity.status(HttpStatus.OK).body("book with id " + id + "successfully returned");
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid id");
        }
    }
    
}
