package dk.ksm.fstask.worker;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import dk.ksm.fstask.worker.queue.QueueMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class App {
    private static final Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        try {
            AppConfiguration appConfiguration = getAppConfiguration(args[0]);

            QueueMonitor queueMonitor = new QueueMonitor(appConfiguration);
            queueMonitor.start();

        } catch (Exception e) {
            log.error("Error in parsing worker config.", e);
        }
    }

    private static AppConfiguration getAppConfiguration(String configFilePath) throws YamlException, FileNotFoundException {
        YamlReader reader = new YamlReader(new FileReader(configFilePath));
        AppConfiguration appConfiguration = reader.read(AppConfiguration.class);

        return appConfiguration;
    }
}
