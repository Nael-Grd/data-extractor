package concr;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

import java.util.List;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import abs.DataMiner;

public class DocxDataMiner extends DataMiner {

    private XWPFDocument docx;

    public DocxDataMiner(String path) {
        super(path);
    }

    public File open() {
        File file = new File(this.path);
        if (!file.exists()) {
            System.err.println("Erreur : Fichier introuvable");
            return null;
        }
        this.file = file;
        return file;
    }

    public List<String> extract() {
        this.parsed_file.clear();   
        try {
            FileInputStream fis = new FileInputStream(this.file);  // tuyau pour lire le fichier physique
            docx = new XWPFDocument(fis);
            XWPFWordExtractor extractor = new XWPFWordExtractor(docx);  // aspirateur a texte
            String text = extractor.getText();
            String[] lignes = text.split("\\r?\\n");
            for (String ligne : lignes) {
                this.parsed_file.add(ligne);
            }
        }
        catch (IOException e) {
           System.err.println(e.getMessage());
        }
        return this.parsed_file;
    }

    public void close() {
        try { 
            if (docx != null) {
                docx.close(); 
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}