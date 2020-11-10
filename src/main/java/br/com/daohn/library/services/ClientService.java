package br.com.daohn.library.services;

import br.com.daohn.library.models.Client;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author daohn on 02/11/2020
 * @project Library Manager
 */
public class ClientService {

    private final String         databasePath;
    private final StorageService storageService;

    public ClientService() {
        this("client.txt", new StorageService());
    }

    public ClientService(String databasePath,
                         StorageService storageService) {
        this.databasePath   = databasePath;
        this.storageService = storageService;
    }

    /**
     * Realiza a leitura do arquivo de armazenamento e devolve um array de {@link Client}
     *
     * @return {@code Client[]} retorna uma lista de {@link Client}
     */
    public Client[] load() {
        var clientsFile = new File(databasePath);
        var clients = new Client[storageService.countLines(clientsFile)];
        var item = 0;

        try(var buffer = Files.newBufferedReader(clientsFile.toPath())) {
            while(buffer.ready()) {
                var line = buffer.readLine();
                var nonParsedData = line.split(";");
                var client = new Client();
                client.setId(Integer.parseInt(nonParsedData[0]));
                client.setName(nonParsedData[1]);
                clients[item] = client;
                item++;
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return clients;
    }
}
