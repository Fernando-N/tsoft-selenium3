package dev.fneira.business;

public class BookData {

    private String isbn;
    private String title;
    private String subTitle;
    private String author;
    private String publisher;
    private String totalPages;
    private String website;

    public BookData() {
    }

    public String getIsbn() {
        return isbn;
    }

    public BookData setIsbn(String isbn) {
        this.isbn = isbn;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public BookData setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getSubTitle() {
        return subTitle;
    }

    public BookData setSubTitle(String subTitle) {
        this.subTitle = subTitle;
        return this;
    }

    public String getAuthor() {
        return author;
    }

    public BookData setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getPublisher() {
        return publisher;
    }

    public BookData setPublisher(String publisher) {
        this.publisher = publisher;
        return this;
    }

    public String getTotalPages() {
        return totalPages;
    }

    public BookData setTotalPages(String totalPages) {
        this.totalPages = totalPages;
        return this;
    }

    public String getWebsite() {
        return website;
    }

    public BookData setWebsite(String website) {
        this.website = website;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
                "isbn='" + isbn + '\'' +
                ", title='" + title + '\'' +
                ", subTitle='" + subTitle + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", totalPages='" + totalPages + '\'' +
                ", website='" + website + '\'' +
                '}';
    }
}
