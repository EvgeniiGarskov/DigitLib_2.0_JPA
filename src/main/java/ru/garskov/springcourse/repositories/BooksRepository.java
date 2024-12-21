package ru.garskov.springcourse.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.garskov.springcourse.models.Book;
import ru.garskov.springcourse.models.Person;

import java.util.List;
import java.util.Optional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findByPerson_Id(Integer personId);

    @Query("SELECT b.person FROM Book b WHERE b.id = :id")
    Optional<Person> findBookOwnerById(@Param("id") int id);

    List<Book> findByTitleContaining(String substring); //StartingWith
}
