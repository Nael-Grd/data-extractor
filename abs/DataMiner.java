package abs;

import java.util.List;
import java.util.ArrayList;
import java.io.File;

public abstract class DataMiner {

    protected String path;
    protected File file;
    protected List<String> parsed_file = new ArrayList<>();

    public DataMiner(String path) {
        this.path = path;
    }

    public final List<String> mine(String word) {
        file = open();
        parsed_file = extract();
        List<String> res = analyse(word);
        close();

        return res;
    }

    public abstract File open();

    public abstract List<String> extract();

    public List<String> analyse(String word) {
        List<String> res = new ArrayList<>();
        for (int i = 0; i < this.parsed_file.size(); i++) {
            String line = this.parsed_file.get(i);
            if (line.toLowerCase().contains(word.toLowerCase())) {
                res.add("Ligne " + (i + 1) + " : " + line);
            }
        }
        return res;
    }

    public abstract void close();

}