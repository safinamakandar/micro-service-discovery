import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ConfigAuthorMSPrinter implements CommandLineRunner {
    @Value("${server.port}")
    private int serverPort;

    @Value("${spring.datasource.url}")
    private String datasourceUrl;

    @Override
    public void run(String... args) {
        System.out.println("ConfigAuthorMSPrinter is running...");
        System.out.println("Server Port: " + serverPort);
        System.out.println("Datasource URL: " + datasourceUrl);
    }
}
