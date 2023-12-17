package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextProcessorUI extends JFrame {
    private JTextArea inputTextArea;
    private JButton processButton;
    private JTextArea outputTextArea;

    public TextProcessorUI() {
        createUI();
    }

    private void createUI() {
        setTitle("Text Processor");
        setSize(500, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        inputTextArea = new JTextArea(10, 40);
        JScrollPane inputScrollPane = new JScrollPane(inputTextArea);

        processButton = new JButton("Remove Digits Outside Quotes");
        processButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String processedText = processText(inputTextArea.getText());
                outputTextArea.setText(processedText);
            }
        });

        outputTextArea = new JTextArea(10, 40);
        outputTextArea.setEditable(false);
        JScrollPane outputScrollPane = new JScrollPane(outputTextArea);

        add(inputScrollPane);
        add(processButton);
        add(outputScrollPane);
    }

    private String processText(String text) {
        Pattern pattern = Pattern.compile("(\"[^\"]*\")|\\b\\w+\\b");
        Matcher matcher = pattern.matcher(text);

        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String match = matcher.group();
            if (match == null || match.startsWith("\"")) {
                // Если это строка в кавычках, оставляем её как есть
                matcher.appendReplacement(sb, match);
            } else {
                // Удаляем цифры из идентификатора
                String modifiedIdentifier = match.replaceAll("\\d", "");
                matcher.appendReplacement(sb, modifiedIdentifier);
            }
        }
        matcher.appendTail(sb);

        return sb.toString();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TextProcessorUI().setVisible(true);
            }
        });
    }
}
