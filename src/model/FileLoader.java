/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 *
 * @author development
 */
public abstract class FileLoader {

    protected List<String> getFileContent(String filename) throws IOException {
        if ((filename == null) || filename.equals("")) throw new IOException();
        
        String workDir = System.getProperty("user.dir");
        File file = new File(workDir, filename);
        Path path = file.toPath();
        return Files.readAllLines(path);
    }
}
