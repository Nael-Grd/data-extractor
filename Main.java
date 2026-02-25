import concr.PDFDataMiner;
import abs.DataMiner;
import java.util.List;

public class Main {
    
    public static void main(String[] args) {
        DataMiner miner = new PDFDataMiner("test/test.pdf");

        List<String> lines = miner.mine("Dummy");
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