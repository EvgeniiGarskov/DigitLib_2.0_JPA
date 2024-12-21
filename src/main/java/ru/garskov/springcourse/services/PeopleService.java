package ru.garskov.springcourse.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.garskov.springcourse.models.Book;
import ru.garskov.springcourse.models.Person;
import ru.garskov.springcourse.repositories.BooksRepository;
import ru.garskov.springcourse.repositories.PeopleRepository;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class PeopleService {

    private final PeopleRepository peopleRepository;
    private final BooksRepository booksRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository, BooksRepository booksRepository) {
        this.peopleRepository = peopleRepository;
        this.booksRepository = booksRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person> foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatePerson) {
        updatePerson.setId(id);
        peopleRepository.save(updatePerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    public List<Book> getBooksByPersonId(int id) {
        List<Book> books = booksRepository.findByPerson_Id(id);
        for (Book book : books) {
            Date dateFromDatabase = book.getCreatedAt();
            if (dateFromDatabase != null) {
                // Получаем текущую дату и время
                LocalDateTime currentDateTime = LocalDateTime.now();
                // Преобразуем дату из базы данных в LocalDateTime
                LocalDateTime dateFromDb = new Timestamp(dateFromDatabase.getTime()).toLocalDateTime();

                // Округляем до LocalDate
                LocalDate dateFromDbOnly = dateFromDb.toLocalDate();
                LocalDate currentDateOnly = currentDateTime.toLocalDate();

                book.setDaysBetween(ChronoUnit.DAYS.between(dateFromDbOnly, currentDateOnly) > 10);
            }
        }
        return books;
    }

    public Optional<Person> findByFullName(String fullName) {
        return peopleRepository.findByFullName(fullName);
    }
}
