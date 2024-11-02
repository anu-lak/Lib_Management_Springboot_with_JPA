package learning.lib_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import learning.lib_management.model.Issued;

@Repository
public interface IssuedRepo extends JpaRepository<Issued, Integer> {

    boolean existsByBook_BookId(int bookId);

    void deleteByBookId(int id);

}
