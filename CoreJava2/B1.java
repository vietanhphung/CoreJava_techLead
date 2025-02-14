import java.lang.reflect.Array;
import java.util.*;

public class B1 {
    public static void main(String[] args) {
      
    }
    
    public static void add(ArrayList<Integer> list, int value) {
        list.add(value);
    }

    public static void display(ArrayList<Integer> list) {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }

    public static int sumList(ArrayList<Integer> list) {
        int sum = 0;
        for (int i = 0; i < list.size(); i++) {
            sum += list.get(i);
        }
        return sum;
    }

    public static ArrayList<Integer> minMax(ArrayList<Integer> list){
        int min = list.get(0);
        int max = list.get(0);
        for (int n : list){
            if (n < min){
                min = n;
            }
            if (n > max){
                max = n;
            }
        }
        ArrayList<Integer> result = new ArrayList<>();
        result.add(min);
        result.add(max);
        return result;
    }

    public static void remove(ArrayList<Integer> list, int value){
        for (int i = 0; i < list.size(); i++){
            if (list.get(i) == value){
                list.remove(i);
                i--;
            }
        }
    }

    public static void delete(ArrayList<Integer> list, int num){
        for (int i = 0; i < list.size(); i++){
            if (list.get(i) == num){
                list.remove(i);
                i--;
            }
        }
    }

    public static boolean exist(ArrayList<Integer> list, int num){
        for (int n :list){
            if (n == num) {
                return true;
            }
        }
        return false;
    }
   
}