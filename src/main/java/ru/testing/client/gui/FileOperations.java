package ru.testing.client.gui;

import javafx.application.Platform;
import javafx.scene.control.ListView;
import ru.testing.client.message.OutputMessage;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileOperations {

    private static final String LOG_FILE = "output.txt";

    public static void logListViewIntoFile(ListView<OutputMessage> listView) {
        File log = new File(LOG_FILE);
        BufferedWriter bufferedWriter = null;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(log);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fileOutputStream, StandardCharsets.UTF_8);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.flush();
            for (int i = 0; i < listView.getItems().size(); i++) {
                OutputMessage m = listView.getItems().get(i);
                bufferedWriter.write(String.format("%s %s\n", m.getFormattedTime(), m.getMessage()));
            }
            Dialogs.getInfoDialog("Saved successfully!");
        } catch (Exception e) {
            Platform.runLater(() -> Dialogs.getExceptionDialog(e));
        } finally {
            if (bufferedWriter != null) {
                try {
                    bufferedWriter.close();
                } catch (Exception e) {
                    Platform.runLater(() -> Dialogs.getExceptionDialog(e));
                }
            }
        }
    }
}
