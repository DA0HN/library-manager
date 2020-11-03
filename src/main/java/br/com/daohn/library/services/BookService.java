package br.com.daohn.library.services;

import br.com.daohn.library.models.Book;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @author daohn on 02/11/2020
 * @project Library Manager
 */
public class BookService {

    private final String         databasePath;
    private final StorageService storageService;

    public BookService() {
        this("book.txt", new StorageService());
    }

    public BookService(String databasePath,
                       StorageService storageService) {
        this.databasePath   = databasePath;
        this.storageService = storageService;
    }

    /**
     * Realiza leitura do arquivo de armazenamento e devolve um array de {@link Book}
     *
     * @return {@code Book[]} retorna uma lista de {@link Book}
     */
    public Book[] load() {
        var booksFile = new File(databasePath);
        var books = new Book[storageService.countLines(booksFile)];
        var item = 0;
        try(var buffer = Files.newBufferedReader(booksFile.toPath())) {
            while(buffer.ready()) {
                var line = buffer.readLine();
                var nonParsedData = line.split(";");
                var book = new Book();
                book.setId(Integer.parseInt(nonParsedData[0]));
                book.setTitle(nonParsedData[1]);
                book.setAuthor(nonParsedData[2]);
                book.setPublishingCompany(nonParsedData[3]);
                books[item] = book;
                item++;
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * Apresenta todos os dados dos livros armazenados.
     *
     * @param books lista de livros
     * @return {@code Integer} total A quantidade de livros armazenados no array
     */
    public Integer list(Book[] books) {
        int total = 0;
        System.out.println("Listing all books");
        for(Book book : books) {
            print(book);
            System.out.println("-".repeat(13));
            total++;
        }
        return total;
    }

    /**
     * Exibe na tela os dados do livro
     * @param book livro que será apresentado na tela
     */
    private void print(Book book) {
        System.out.println("Id.........: " + book.getId());
        System.out.println("Title......: " + book.getTitle());
        System.out.println("Author.....: " + book.getAuthor());
        System.out.println("Publisher..: " + book.getPublishingCompany());
    }

    /**
     * Recebe uma lista de {@link Book} e um critério de ordenação e retorna a mesma lista ordenada
     * pelo critério.
     *
     * @param books    lista de {@link Book}
     * @param criteria critério da ordenação da lista de {@link Book}
     * @return {@code Book[]} lista ordenada pelo critério
     */
    private Book[] sort(Book[] books, Comparator<Book> criteria) {
        Arrays.sort(books, criteria);
        return books;
    }

    /**
     * Recebe uma lista de {@link Book} e retorna a mesma lista ordenada pelo título.
     *
     * @param books lista de {@link Book}
     * @return {@code Book[]} retorna a lista ordenada
     */
    public Book[] sortByTitle(Book[] books) {
        return this.sort(books,
                         (Book b1, Book b2) -> {
                             return b1.getTitle()
                                     .transform(String::trim)
                                     .compareToIgnoreCase(b2.getTitle());
                         }
        );
    }

    /**
     * Recebe uma lista de {@link Book} e retorna a mesma lista ordenada pelo autor.
     *
     * @param books lista de {@link Book}
     * @return {@code Book[]} retorna a lista ordenada
     */
    public Book[] sortByAuthor(Book[] books) {
        return this.sort(books,
                         (Book b1, Book b2) -> {
                             return b1.getAuthor()
                                     .transform(String::trim)
                                     .compareToIgnoreCase(b2.getTitle());
                         }
        );
    }

    /**
     * Realiza uma consulta por nome de livro ou nome do autor na lista de livros
     * @param books lista de livros que será consultada
     * @param name nome do autor ou nome do livro que será consultado
     */
    public Book[] findByAuthorOrBookName(Book[] books, String name) {
        var foundBooks = new Book[books.length];
        for(Book book : books) {
            if(book.getTitle().toLowerCase().contains(name.toLowerCase())
                    || book.getAuthor().toLowerCase().contains(name.toLowerCase())) {
                print(book);
                createBooks(foundBooks, book);
            }
            System.out.println("-".repeat(13));
        }
        return foundBooks;
    }

    /**
     * Método auxiliar para criar lista de livros da consulta do método {@code
     * findByAuthorOrBookName}
     * @param foundBooks lista que será populada
     * @param book livro que será adicionado na lista
     */
    private void createBooks(Book[] foundBooks, Book book) {
        for(int i = 0; i< foundBooks.length; i++) {
            if(foundBooks[i] == null) {
                foundBooks[i] = book;
                return;
            }
        }
    }

    /**
     * Exibe e retorna os dados do {@link Book}
     * @param books lista de livros que será consultado
     * @param id identificador que será utilizado na consulta
     * @return {@code Book} livro localizado
     * @throws IllegalStateException caso nenhum livro seja encontrado
     */
    public Book findById(Book[] books, int id) {
        for(Book book : books) {
            if(book.getId() == id) {
                print(book);
                return book;
            }
        }
        throw new IllegalStateException("Livro não encontrado");
    }
}
