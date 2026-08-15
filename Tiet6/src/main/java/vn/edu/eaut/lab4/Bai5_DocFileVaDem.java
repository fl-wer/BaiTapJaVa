package vn.edu.eaut.lab4;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.io.BufferedReader;
import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

public class Bai5_DocFileVaDem extends JFrame {
    private JButton btnChooseFile;
    private JButton btnCount;
    private JLabel lblFile;
    private JLabel lblResult;
    private JProgressBar progressBar;
    private File selectedFile;

    public Bai5_DocFileVaDem() {
        setTitle("Bai 5 - Doc file va dem dong");
        setSize(600, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 15));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        // Top panel: Choose file button and file path
        JPanel topPanel = new JPanel(new BorderLayout(10, 5));
        
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btnChooseFile = new JButton("Chon file");
        btnChooseFile.setFont(new Font("Arial", Font.BOLD, 14));
        btnChooseFile.setPreferredSize(new Dimension(120, 35));
        buttonPanel.add(btnChooseFile);
        
        btnCount = new JButton("Dem dong");
        btnCount.setFont(new Font("Arial", Font.BOLD, 14));
        btnCount.setPreferredSize(new Dimension(120, 35));
        btnCount.setEnabled(false);
        buttonPanel.add(btnCount);
        
        topPanel.add(buttonPanel, BorderLayout.NORTH);
        
        lblFile = new JLabel("Chua chon file");
        lblFile.setFont(new Font("Arial", Font.PLAIN, 12));
        lblFile.setForeground(Color.GRAY);
        lblFile.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        topPanel.add(lblFile, BorderLayout.CENTER);

        // Progress bar
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Arial", Font.BOLD, 12));

        // Result label
        lblResult = new JLabel("So dong: ", SwingConstants.CENTER);
        lblResult.setFont(new Font("Arial", Font.BOLD, 16));

        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(progressBar, BorderLayout.CENTER);
        mainPanel.add(lblResult, BorderLayout.SOUTH);

        add(mainPanel);

        // Event listeners
        btnChooseFile.addActionListener(e -> chooseFile());
        btnCount.addActionListener(e -> countLines());
    }

    /**
     * Chon file bang JFileChooser
     */
    private void chooseFile() {
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Chon file van ban");
        int result = chooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = chooser.getSelectedFile();
            lblFile.setText("File: " + selectedFile.getAbsolutePath());
            lblFile.setForeground(Color.BLACK);
            btnCount.setEnabled(true);
            progressBar.setValue(0);
            lblResult.setText("So dong: ");
        }
    }

    /**
     * Dem so dong trong file bang SwingWorker
     */
    private void countLines() {
        if (selectedFile == null) {
            JOptionPane.showMessageDialog(this, "Vui long chon file truoc");
            return;
        }

        btnCount.setEnabled(false);
        btnChooseFile.setEnabled(false);
        progressBar.setValue(0);
        lblResult.setText("Dang doc file...");

        SwingWorker<Long, Void> worker = new SwingWorker<>() {
            @Override
            protected Long doInBackground() throws Exception {
                long totalBytes = Files.size(selectedFile.toPath());
                long readBytes = 0;
                long lines = 0;

                try (BufferedReader reader = Files.newBufferedReader(
                        selectedFile.toPath(), StandardCharsets.UTF_8)) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        lines++;
                        readBytes += line.getBytes(StandardCharsets.UTF_8).length + 1;
                        
                        int progress = totalBytes == 0 
                            ? 100 
                            : (int) Math.min(100, (readBytes * 100 / totalBytes));
                        setProgress(progress);
                    }
                }
                return lines;
            }

            @Override
            protected void done() {
                try {
                    long lineCount = get();
                    lblResult.setText("So dong: " + lineCount);
                } catch (Exception ex) {
                    lblResult.setText("Loi khi doc file");
                    JOptionPane.showMessageDialog(Bai5_DocFileVaDem.this, 
                        "Khong the doc file: " + ex.getMessage(), 
                        "Loi", JOptionPane.ERROR_MESSAGE);
                }
                progressBar.setValue(100);
                btnCount.setEnabled(true);
                btnChooseFile.setEnabled(true);
            }
        };

        worker.addPropertyChangeListener(evt -> {
            if ("progress".equals(evt.getPropertyName())) {
                progressBar.setValue((int) evt.getNewValue());
            }
        });

        worker.execute();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Bai5_DocFileVaDem().setVisible(true);
        });
    }
}