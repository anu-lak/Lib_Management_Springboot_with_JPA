package learning.lib_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import learning.lib_management.model.Book;

@Repository
public interface BookRepo extends JpaRepository<Book, Integer> {
    
}
