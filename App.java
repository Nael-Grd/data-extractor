import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import abs.DataMiner;
import concr.*;

public class App extends JFrame {

    private JTextField searchField;
    private JTextArea resultArea;
    private String selectedFilePath = ""; 

    public App() {

        setTitle("Multi-Format Data Miner");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.WHITE);

        JLabel title = new JLabel("Extracteur de données Multi-Format");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        topPanel.add(Box.createVerticalStrut(10));  // petit espace
        topPanel.add(title);

        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 30, 10));
        logoPanel.setBackground(Color.WHITE);
        logoPanel.add(new JLabel(new ImageIcon("res/pdf_logo.png")));
        logoPanel.add(new JLabel(new ImageIcon("res/csv_logo.png")));
        logoPanel.add(new JLabel(new ImageIcon("res/word_logo.png")));
        topPanel.add(logoPanel);

        add(topPanel, BorderLayout.NORTH);

        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 50, 10, 50));

        JLabel fileLabel = new JLabel("Aucun fichier sélectionné");
        fileLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        JButton btnBrowse = new JButton("Séléctionner un fichier");
        btnBrowse.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel searchRow = new JPanel(new FlowLayout());
        searchField = new JTextField(15);
        searchRow.add(new JLabel("Mot clé :"));
        searchRow.add(searchField);

        JButton btnSearch = new JButton("Lancer la Recherche");
        btnSearch.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnSearch.setBackground(new Color(70, 130, 180)); 
        btnSearch.setForeground(Color.WHITE);

        formPanel.add(btnBrowse);
        formPanel.add(fileLabel);
        formPanel.add(Box.createVerticalStrut(15));
        formPanel.add(searchRow);
        formPanel.add(btnSearch);

        add(formPanel, BorderLayout.CENTER);

        resultArea = new JTextArea(10, 40);
        resultArea.setEditable(false);
        resultArea.setBackground(new Color(245, 245, 245)); 
        JScrollPane scrollPane = new JScrollPane(resultArea);
        add(scrollPane, BorderLayout.SOUTH);

        btnBrowse.addActionListener(e -> {
            JFileChooser fc = new JFileChooser();
            int returnVal = fc.showOpenDialog(this);
            if (returnVal == JFileChooser.APPROVE_OPTION) {
                selectedFilePath = fc.getSelectedFile().getAbsolutePath();
                fileLabel.setText("Fichier : " + fc.getSelectedFile().getName());
            }
        });
        btnSearch.addActionListener(e -> search());
    }

    private void search() {
        String word = searchField.getText();
        
        if (selectedFilePath.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Veuillez d'abord sélectionner un fichier");
            return;
        }

        DataMiner miner = null; 
        if (selectedFilePath.endsWith(".pdf")) miner = new PDFDataMiner(selectedFilePath);
        else if (selectedFilePath.endsWith(".csv")) miner = new CSVDataMiner(selectedFilePath);
        else if (selectedFilePath.endsWith(".docx")) miner = new DocxDataMiner(selectedFilePath);

        if (miner != null) {
            try {
                List<String> results = miner.mine(word);
                resultArea.setText("");
                if (results.isEmpty()) {
                    resultArea.setText("Aucune occurrence trouvée pour : " + word);
                } else {
                    for (String s : results) resultArea.append(s + "\n");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erreur : " + e.getMessage(), "Erreur Format", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Format non supporté");
        }
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new App().setVisible(true));
    }
    
}
