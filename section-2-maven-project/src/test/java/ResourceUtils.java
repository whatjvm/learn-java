import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URL;

public class ResourceUtils {

    @Test
    public void test() {
        URL resource =
                this.getClass().getResource("user01.txt");
        Assertions.assertNotNull(resource);
    }

}
