package br.com.daohn.library.services;

import br.com.daohn.library.models.Loan;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static br.com.daohn.library.models.Loan.Status;

/**
 * @author daohn on 02/11/2020
 * @project Library Manager
 */
public class LoanService {

    private final String         databasePath;
    private final StorageService storageService;

    public LoanService() {
        this("loan.txt", new StorageService());
    }

    public LoanService(String databasePath, StorageService storageService) {
        this.databasePath   = databasePath;
        this.storageService = storageService;
    }

    /**
     * Realiza leitura do arquivo de armazenamento e devolve um array de {@link Loan}
     *
     * @return {@code Loan[]} retorna uma lista de {@link Loan}
     */
    public Loan[] load() {
        var loansFile = new File(databasePath);
        var loans = new Loan[storageService.countLines(loansFile)];
        var item = 0;
        try(var buffer = Files.newBufferedReader(loansFile.toPath())) {
            while(buffer.ready()) {
                var line = buffer.readLine();
                var nonParsedData = line.split(";");
                var loan = new Loan();
                loan.setId(Integer.parseInt(nonParsedData[0]));
                loan.setClientId(Integer.parseInt(nonParsedData[1]));
                loan.setBookId(Integer.parseInt(nonParsedData[2]));
                loan.setStatus(Status.fromId(Integer.parseInt(nonParsedData[3])));
                loans[item] = loan;
                item++;
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return loans;
    }




}
