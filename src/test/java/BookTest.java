/*
created by Ivanov Nikita
*/
import entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BookTest {
    @Test
    public void createDefaultBook() {
        Book book = new Book.BookBuilder().build();
        Assertions.assertEquals("Author", book.getAuthor());
        Assertions.assertEquals("Title", book.getTitle());
        Assertions.assertEquals(150, book.getQuantityPage());
    }

    @Test
    public void createBookWithCustomTitle() {
        Book book = new Book.BookBuilder().setTitle("Онегин").build();
        Assertions.assertEquals("Онегин", book.getTitle());
        Assertions.assertEquals("Author", book.getAuthor());
        Assertions.assertEquals(150, book.getQuantityPage());
    }

    @Test
    public void createBookWithCustomAuthor() {
        Book book = new Book.BookBuilder().setAuthor("Пушкин").build();
        Assertions.assertEquals("Title", book.getTitle());
        Assertions.assertEquals("Пушкин", book.getAuthor());
        Assertions.assertEquals(150, book.getQuantityPage());
    }

    @Test
    public void createBookWithCustomQuantityPage() {
        Book book = new Book.BookBuilder().setQuantityPage(230).build();
        Assertions.assertEquals("Title", book.getTitle());
        Assertions.assertEquals("Author", book.getAuthor());
        Assertions.assertEquals(230, book.getQuantityPage());
    }

    @Test
    public void createBookWithTwoCustomParameters() {
        Book book = new Book.BookBuilder()
                .setQuantityPage(333)
                .setTitle("Война и мир")
                .build();
        Assertions.assertEquals("Война и мир", book.getTitle());
        Assertions.assertEquals("Author", book.getAuthor());
        Assertions.assertEquals(333, book.getQuantityPage());
    }

    @Test
    public void createBookWithAllCustomParameters() {
        Book book = new Book.BookBuilder()
                .setAuthor("Толстой")
                .setQuantityPage(900)
                .setTitle("Война и мир")
                .build();
        Assertions.assertEquals("Война и мир", book.getTitle());
        Assertions.assertEquals("Толстой", book.getAuthor());
        Assertions.assertEquals(900, book.getQuantityPage());
    }

    @Test
    public void createBookWithIncorrectParameters() {
        Book book = new Book.BookBuilder()
                .setTitle("")
                .setAuthor("")
                .setQuantityPage(-132)
                .build();
        Assertions.assertEquals("Author", book.getAuthor());
        Assertions.assertEquals("Title", book.getTitle());
        Assertions.assertEquals(150, book.getQuantityPage());
    }

}
