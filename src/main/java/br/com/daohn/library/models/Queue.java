package br.com.daohn.library.models;

/**
 * @author daohn on 02/11/2020
 * @project Library Manager
 */
public class Queue {
    private int id;
    private int clientId;
    private int bookId;

    public Queue() {
    }

    public Queue(int id, int clientId, int bookId) {
        this.id       = id;
        this.clientId = clientId;
        this.bookId   = bookId;
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
}
