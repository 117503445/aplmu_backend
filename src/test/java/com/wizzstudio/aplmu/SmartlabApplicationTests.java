package com.wizzstudio.aplmu;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

@SpringBootTest
@ContextConfiguration(classes = AplmuApplication.class)
class AplmuApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("Test : contextLoads ");
    }

}
