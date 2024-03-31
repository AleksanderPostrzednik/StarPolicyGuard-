import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class IAMPolicyCheckerTest {

    @Test
    void testPolicyWithAsterisk() {
        IAMPolicyChecker checker = new IAMPolicyChecker();
        File jsonFile = new File("path_to_policy_with_asterisk.json");
        boolean result = checker.verifyPolicyResource(jsonFile);
        assertFalse(result);
    }

    @Test
    void testPolicyWithoutAsterisk() {
        IAMPolicyChecker checker = new IAMPolicyChecker();
        File jsonFile = new File("path_to_policy_without_asterisk.json");
        boolean result = checker.verifyPolicyResource(jsonFile);
        assertTrue(result);
    }
}
