# Библиотечная система учета книг (Обновленная версия)

## Описание проекта

Этот проект представляет собой обновленное веб-приложение для цифрового учета книг в библиотеке, переписанное с использованием Hibernate и Spring Data JPA. Библиотекари могут легко управлять читателями и книгами, а также отслеживать выдачу и возврат книг.

## Функционал

В приложение добавлены следующие функции:

1. **Пагинация книг**:
  - Реализована возможность разбивки списка книг на страницы. Метод контроллера позволяет запрашивать книги с указанием номера страницы и количества книг на странице.
<details>
![Pagination](https://github.com/user-attachments/assets/38a4198a-9a39-4721-9517-9d9cc5e462cf)
</details>

2. **Сортировка книг по году**:
  - Добавлена возможность сортировки книг по году издания. Метод контроллера возвращает книги в отсортированном порядке.
<details>
![Sorting](https://github.com/user-attachments/assets/f4b8228a-3288-4b8c-b86d-eeb3d5165706)
</details>

3. **Поиск книг**:
  - Создана страница для поиска книг. Пользователь может ввести начальные буквы названия книги и получить полное название книги, имя автора, а также информацию о том, у кого в данный момент находится книга.
<details>
![Search](https://github.com/user-attachments/assets/0ec8ae0a-5d59-4693-9f7b-2755bb5cec10)
</details>

4. **Автоматическая проверка просрочки возврата книги**:
  - Реализована автоматическая проверка на просрочку возврата книги. Система уведомляет о том, если человек не вернул книгу в срок.
<details>
![Сhecking the book](https://github.com/user-attachments/assets/be760a70-7a98-4499-adab-2df0e987bb56)
</details>

## Технологии

- Java
- Spring MVC
- Spring Data JPA
- Hibernate
- HTML/Thymeleaf
- Maven
- PostgreSQL

## Установка и запуск в IntelliJ IDEA

Инструкции по установке и запуску проекта аналогичны тем, что описаны в первом проекте. Обратитесь к разделу "Установка и запуск в IntelliJ IDEA" в README первого проекта для получения подробной информации.