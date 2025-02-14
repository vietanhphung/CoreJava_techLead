import java.util.Map;

public class B7 {
    public static void main(String[] args) {
       
    }

    public static void averageGrade(Map<String, Integer[]> grades) {
        for (Map.Entry<String, Integer[]> entry : grades.entrySet()) {
            String name = entry.getKey();
            Integer[] grade = entry.getValue();
            int sum = 0;
            for (int i = 0; i < grade.length; i++) {
                sum += grade[i];
            }
            if (grade.length == 0) System.out.println(name + " n/a"); else System.out.println(name + " " + sum / grade.length);
        }
    }
}