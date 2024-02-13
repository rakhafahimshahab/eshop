package id.ac.ui.cs.advprog.eshop;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
@SpringBootTest
class EshopApplicationTests {

    @Test
    void testMainApplication() {
        Assertions.assertDoesNotThrow(() -> EshopApplication.main(new String[]{}));
    }

}
