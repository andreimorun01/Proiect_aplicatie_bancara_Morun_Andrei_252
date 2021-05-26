package frames;

import files.HistoryElement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HistoryFrame {
    public static void open(List<HistoryElement> history) {
        JButton mainButton = new JButton("main");
        mainButton.setBounds(475, 500, 100, 50);


        List<String> list = new ArrayList<>();
        for (HistoryElement historyElement : history) {
            list.add(historyElement.getAction() + "," +
                    historyElement.getTimestamp() + "," +
                    historyElement.getThreadName());
        }

        String string = String.join("\n", list);

        JTextArea textArea = new JTextArea(string);
        textArea.setEditable(false);
        JScrollPane jScrollPane = new JScrollPane(textArea);
        jScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jScrollPane.setBounds(50, 50, 400, 400);

        JFrame historyFrame = new JFrame();
        historyFrame.add(mainButton);
        historyFrame.add(jScrollPane);

        historyFrame.setSize(600, 600);
        historyFrame.setLayout(null);
        historyFrame.setVisible(true);

        mainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                historyFrame.dispose();
                MainFrame.open(history);
            }
        });
    }
}
