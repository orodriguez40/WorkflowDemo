// App Test

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    @Test
    void greetReturnsCorrectMessage() {
        assertEquals("Hello, Professor!", App.greet("Professor"));
    }
}
