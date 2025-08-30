/*
created by Ivanov Nikita
*/
package entity;

import java.util.Objects;

public class Book {

    private String author;
    private String title;
    private int quantityPage;

    public Book(BookBuilder bookBuilder) {
        this.author = bookBuilder.author;
        this.title = bookBuilder.title;
        this.quantityPage = bookBuilder.quantityPage;
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
        return quantityPage == book.getQuantityPage() && Objects.equals(author, book.getAuthor())
                && Objects.equals(title, book.getTitle());
    }

    @Override
    public int hashCode() {
        return Objects.hash(author, title, quantityPage);
    }

    public static class BookBuilder {
        private String author = "Author";
        private String title = "Title";
        private int quantityPage = 150;

        public BookBuilder() {
        }

        public BookBuilder setAuthor(String author) {
            if (author != null && !author.trim().isEmpty()) {
                this.author = author;
            }
            return this;
        }

        public BookBuilder setTitle(String title) {
            if (title != null && !title.trim().isEmpty()) {
                this.title = title;
            }
            return this;
        }

        public BookBuilder setQuantityPage(int quantityPage) {
            if (quantityPage > 0) {
                this.quantityPage = quantityPage;
            }
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }
}
