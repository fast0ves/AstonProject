package entity;

import java.util.Comparator;
import java.util.Objects;

/**
 * Класс, представляющий книгу.
 * Содержит информацию об авторе, названии и количестве страниц.
 * Реализует паттерн Builder для создания объектов.
 */
public class Book {

    /** Имя автора книги */
    private String author;

    /** Название книги */
    private String title;

    /** Количество страниц */
    private int quantityPage;

    /** Компаратор для сортировки по автору */
    private static final Comparator<Book> BY_AUTHOR = Comparator.nullsFirst(Comparator.comparing(Book::getAuthor));

    /** Компаратор для сортировки по названию */
    private static final Comparator<Book> BY_TITLE = Comparator.nullsFirst(Comparator.comparing(Book::getTitle));

    /** Компаратор для сортировки по количеству страниц */
    private static final Comparator<Book> BY_PAGE = Comparator.nullsFirst(Comparator.comparing(Book::getQuantityPage));

    /** Компаратор для комплексной сортировки */
    private static final Comparator<Book> BY_ALL_FIELDS = BY_AUTHOR.thenComparing(BY_TITLE).thenComparing(BY_PAGE);

    /**
     * Конструктор класса.
     * Использует паттерн Builder для инициализации объекта.
     *
     * @param bookBuilder объект построителя книги
     */
    public Book(BookBuilder bookBuilder) {
        this.author = bookBuilder.author;
        this.title = bookBuilder.title;
        this.quantityPage = bookBuilder.quantityPage;
    }

    /**
     * Возвращает компаратор для сортировки книг.
     *
     * @return компаратор по всем полям
     */
    public static Comparator<Book> getComparator(){
        return BY_ALL_FIELDS;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public int getQuantityPage() {
        return quantityPage;
    }

    @Override
    public String toString() {

        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", quantityPage=" + quantityPage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;

        return quantityPage == book.getQuantityPage()
                && Objects.equals(author, book.getAuthor())
                && Objects.equals(title, book.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title, quantityPage);
    }

    /**
     * Вложенный класс-строитель для создания объектов Book.
     */
    public static class BookBuilder {
        private String author = "Author";
        private String title = "Title";
        private int quantityPage = 150;

        public BookBuilder() {
        }

        /**
         * Устанавливает автора книги.
         *
         * @param author имя автора
         * @return текущий построитель
         */
        public BookBuilder setAuthor(String author) {
            if (author != null && !author.trim().isEmpty()) {
                this.author = author;
            }

            return this;
        }

        /**
         * Устанавливает название книги.
         *
         * @param title название
         * @return текущий построитель
         */
        public BookBuilder setTitle(String title) {
            if (title != null && !title.trim().isEmpty()) {
                this.title = title;
            }

            return this;
        }

        /**
         * Устанавливает количество страниц.
         *
         * @param quantityPage количество страниц
         * @return текущий построитель
         */
        public BookBuilder setQuantityPage(int quantityPage) {
            if (quantityPage > 0) {
                this.quantityPage = quantityPage;
            }

            return this;
        }

        /**
         * Создает и возвращает готовый объект книги.
         * Выполняет валидацию всех полей перед созданием объекта.
         *
         * @return готовый объект Book
         * @throws IllegalArgumentException если данные не прошли валидацию
         */
        public Book build() {
            if (author == null || author.trim().isEmpty()) {
                throw new IllegalArgumentException("Author cannot be null or empty");
            }
            if (title == null || title.trim().isEmpty()) {
                throw new IllegalArgumentException("Title cannot be null or empty");
            }
            if (quantityPage <= 0) {
                throw new IllegalArgumentException("Quantity page must be positive");
            }

            return new Book(this);
        }
    }
}
