package kz.jihc.registration.models;

public class Book {
    String name;
    String author;
    int pageNumber;
    String photo;

    public Book(){}

    public Book(String name, String author, int pageNumber, String photo) {
        this.name = name;
        this.author = author;
        this.pageNumber = pageNumber;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
