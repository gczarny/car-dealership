package pl.car_dealership.integration;

import lombok.RequiredArgsConstructor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import pl.car_dealership.integration.configuration.AbstractIT;

@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MechanicControllerIT extends AbstractIT {

    @LocalServerPort
    private int port;

    @Value("${server.servlet.context-path}")
    private String basePath;

    private final TestRestTemplate restTemplate; //pretends to be a browser

    @Test
    void homePageWorksCorrectly() {
        // when
        String response = String.format("http://localhost:%s%s/mechanic",port, basePath);
        String page = this.restTemplate.getForObject(response, String.class);

        Assertions.assertThat(page).contains("Check the following service requests and work on them!");

        // then
        // some assertions
    }
}
