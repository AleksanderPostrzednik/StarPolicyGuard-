import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class IAMPolicyChecker {

    public boolean verifyPolicyResource(File jsonFile) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> policyMap = objectMapper.readValue(jsonFile, new TypeReference<Map<String, Object>>() {});

        List<String> resources = (List<String>) ((Map<String, Object>) policyMap.get("Statement")).get("Resource");
        for (String resource : resources) {
            if ("*".equals(resource)) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        IAMPolicyChecker checker = new IAMPolicyChecker();
        try {
            boolean result = checker.verifyPolicyResource(new File("path_to_policy_file.json"));
            System.out.println("Verification result: " + result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
