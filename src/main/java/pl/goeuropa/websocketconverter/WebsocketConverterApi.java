package pl.goeuropa.websocketconverter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class WebsocketConverterApi {

    public static void main(String[] args) {
        SpringApplication.run(WebsocketConverterApi.class, args);
    }

}
