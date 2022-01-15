package de.benkan.settings;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest(classes = Application.class)
@TestPropertySource(properties = {
        "POSTGRES_HOST=localhost",
        "POSTGRES_USER=admin",
        "POSTGRES_PASSWORD=p4ssw0rd"
})
class ApplicationTests {

    @Test
    void contextLoads() { // FIXME how to make this test case working w/o running into connection refused error on build?
    }

}

