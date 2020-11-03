package br.com.daohn.library.services;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

/**
 * @author daohn on 02/11/2020
 * @project Library Manager
 */
public class StorageService {

    public int countLines(File file) {
        int total = 0;
        try(var reader = Files.newBufferedReader(file.toPath())) {
            total = (int) reader.lines()
                    .map(line -> line.split("\n"))
                    .count();
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return total;
    }
}
