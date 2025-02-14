import java.util.ArrayList;

public class B5 {
    public static void main(String[] args) {
        // Your code here
    }

    public ArrayList<Integer> sumOf2(ArrayList<Integer> list, int target){
        ArrayList<Integer> output = new ArrayList<>();
        for(int i = 0; i < list.size(); i++){
            for(int j = i+1; j < list.size(); j++){
                if(list.get(i) + list.get(j) == target){
                    output.add(list.get(i));
                    output.add(list.get(j));
                    return output;
                }
            }
        }
        return output;
    }
}