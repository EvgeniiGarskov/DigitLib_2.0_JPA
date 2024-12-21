package ru.garskov.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.garskov.springcourse.models.Book;
import ru.garskov.springcourse.models.Person;
import ru.garskov.springcourse.repositories.BooksRepository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {

    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll(Sort sort) {
        return booksRepository.findAll(sort);
    }

    public List<Book> findAll(int page, int books_per_page, Sort sort) {
        return booksRepository.findAll(PageRequest.of(page, books_per_page, sort)).getContent();
    }

    public Book findOne(int id) {
        Optional<Book> foundPerson = booksRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Book book) {
//        // Получаем текущую дату и время
//        LocalDateTime currentDateTime = LocalDateTime.now();
//        // Уменьшаем дату на 10 дней
//        LocalDateTime tenDaysEarlier = currentDateTime.minus(10, ChronoUnit.DAYS);
//        // Преобразуем в Timestamp
//        Timestamp tenDaysEarlierTimestamp = Timestamp.valueOf(tenDaysEarlier);
//        book.setCreatedAt(tenDaysEarlierTimestamp);
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book updateBook) {
        Book bookToBeUpdated = booksRepository.findById(id).get();
        //Добавляем по сути новую книгу (которая не находится в Persistence context), поэтому нужен save()
        updateBook.setId(id);
        /*Что бы не терялась связь при обновлении, что бы владелец сохранялся. Ещё раз назначаем владельца, потому что та сущность, которая пришла с формы
        в поле person лежит null потому что в форме это поле никак не назначается.
         */
        updateBook.setPerson(bookToBeUpdated.getPerson());
        booksRepository.save(updateBook);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Optional<Person> getBookOwner(int id) {
        return booksRepository.findBookOwnerById(id);
    }

    @Transactional
    public void assignPerson(int id, Person person) {
        try {
            Book book = booksRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
            book.setPerson(person);
            book.setCreatedAt(new Date());
            booksRepository.save(book);
        } catch (RuntimeException e) {
            System.err.println("Ошибка при назначении человека книге: " + e.getMessage());
        }
    }

    @Transactional
    public void releaseBook(int id) {
        try {
            Book book = booksRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
            book.setPerson(null);
            book.setCreatedAt(null);
            booksRepository.save(book);
        } catch (RuntimeException e) {
            System.err.println("Ошибка при удалении человека у книги: " + e.getMessage());
        }
    }

    public List<Book> searchBook(String substring) {
        return booksRepository.findByTitleContaining(substring);
    }
}
