package br.com.daohn.library.models;

/**
 * @author daohn on 02/11/2020
 * @project Library Manager
 */
public class Loan {
    private int id;
    private int clientId;
    private int bookId;
    private Status status;

    public enum Status {
        LOANED, RETURNED;
    }

    public Loan() {
    }

    public Loan(int id, int clientId, int bookId, Status status) {
        this.id       = id;
        this.clientId = clientId;
        this.bookId   = bookId;
        this.status   = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
