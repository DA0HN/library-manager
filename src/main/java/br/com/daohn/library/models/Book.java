package br.com.daohn.library.models;

/**
 * @author daohn on 02/11/2020
 * @project Library Manager
 */
public class Book {
    private int    id;
    private String title;
    private String author;
    private String publishingCompany;

    public Book() {
    }

    public Book(int id, String title, String author, String publishingCompany) {
        this.id                = id;
        this.title             = title;
        this.author            = author;
        this.publishingCompany = publishingCompany;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }
}
