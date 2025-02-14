import java.util.Map;
import java.util.HashMap;


public class B10 {
    public static void main(String[] args) {
        
    }

    public static Map<String, Integer> gradeDistribution(Map<String, Double> grades) {
        Map<String, Integer> output = new HashMap<String, Integer>();
        int high = 0;
        int faill = 0;
        int pass = 0;

        for (Map.Entry<String, Double> entry : grades.entrySet()) {
            if (entry.getValue() >= 8.0) {
                high++;
            } else if (entry.getValue() >= 5.0 && entry.getValue() < 8.0) {
                pass++;
            } else {
                faill++;
            }
        }
        output.put("pass with high score", high);
        output.put("pass", pass);
        output.put("faill", faill);
        return output;
    }

}