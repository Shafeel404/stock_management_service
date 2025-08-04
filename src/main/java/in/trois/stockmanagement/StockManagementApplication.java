package in.trois.stockmanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.elasticsearch.ElasticsearchDataAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"in.trois.stockmanagement", "in.trois.stock.auth.lib.service"})
@SpringBootApplication(exclude = {ElasticsearchDataAutoConfiguration.class})
public class StockManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockManagementApplication.class, args);
    }
}
