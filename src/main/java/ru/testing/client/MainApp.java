package ru.testing.client;

import com.beust.jcommander.JCommander;
import ru.testing.client.config.ApplicationType;
import ru.testing.client.config.Configuration;
import ru.testing.client.console.ConsoleApp;
import ru.testing.client.gui.JavaFxApp;

/**
 * Main application class
 */
public class MainApp {

    /**
     * Entry point to application
     * @param args String[]
     */
    public static void main(String[] args) {
        Configuration config = new Configuration();
        JCommander parser = new JCommander();
        parser.setProgramName("java -jar ws.client.jar");
        parser.addObject(config);
        try {
            parser.parse(args);
        } catch (Exception e) {
            parser.usage();
            System.exit(1);
        }

        // show help application option
        if (config.isHelp()) {
            parser.usage();
            System.exit(0);
        }

        // select application type
        if (config.getType() == ApplicationType.console) {
            ConsoleApp.main(args);
        } else {
            JavaFxApp.main(args);
        }
    }
}
