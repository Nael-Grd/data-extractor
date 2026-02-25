package concr;

import java.io.File;
import java.util.List;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.Loader;

import abs.DataMiner;

public class PDFDataMiner extends DataMiner {

    private PDDocument pdf;

    public PDFDataMiner(String path) {
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
            pdf = Loader.loadPDF(this.file);
            PDFTextStripper stripper = new PDFTextStripper();  // aspirateur a text
            String text = stripper.getText(pdf);
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
            if (pdf != null) {
                pdf.close(); 
            }
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}