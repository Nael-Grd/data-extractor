package concr;

import java.io.File;
import java.util.List;
import abs.DataMiner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVDataMiner extends DataMiner {

    private BufferedReader buf;

    public CSVDataMiner(String path) {
        super(path);
    }

    public File open() {
        File file = new File(this.path);
        if (!file.exists()) {
            System.err.println("Erreur : Fichier non-existant");
            return null;
        }
        this.file = file;
        return file;
    }

    public List<String> extract() {
        this.parsed_file.clear();    // on vide la liste pour une nouvelle extraction
        try {
            buf = new BufferedReader(new FileReader(this.file));
            String line;
            while ((line = buf.readLine()) != null) {
                this.parsed_file.add(line);
            }
        } 
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return this.parsed_file;
    }

    public void close() {
        try { 
            if (buf != null) {
                buf.close(); 
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}