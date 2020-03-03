import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StringUtilsTest {
    @Test
    public void trimToEmpty() {
        String testString = "   3234234   234234234   ";
        Assertions.assertEquals("3234234   234234234", StringUtils.trimToEmpty(testString));
    }

}
