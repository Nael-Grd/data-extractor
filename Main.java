import concr.CSVDataMiner;
import concr.DocxDataMiner;
import concr.PDFDataMiner;
import abs.DataMiner;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        DataMiner pdfminer = new PDFDataMiner("test/test.pdf");
        DataMiner csvminer = new CSVDataMiner("test/test.csv");
        DataMiner docxminer = new DocxDataMiner("test/test.docx");

        testFormat(csvminer, "Java", "CSV");
        testFormat(pdfminer, "Dummy", "PDF");
        testFormat(docxminer, "sample", "DOCX");
    }

    public static void testFormat(DataMiner miner, String word, String type) {
        System.out.println(type + " File :");
        List<String> lines = miner.mine(word);
        if (lines.isEmpty()) {
            System.out.println("Aucune correspondance");
        }
        else {
            for (String line : lines) {
                System.out.println(line);
            }   
        }
    }
    
}



// javac -cp ".:lib/*" abs/*.java concr/*.java Main.java
// java -cp ".:lib/*" Main