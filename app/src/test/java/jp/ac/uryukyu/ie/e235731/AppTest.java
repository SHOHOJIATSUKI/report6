package jp.ac.uryukyu.ie.e235731;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
    public void testGetGreeting() {
        App app = new App();
        String greeting = app.getGreeting();

        // 期待される挙動として "Hello World!" を返すか確認
        assertEquals("Hello World!", greeting);
    }
}
