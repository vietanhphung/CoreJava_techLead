import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class JavaCore1 {
    
// Level 1

//1
    public static <T extends Number> void  sum(T a, T b) {
        System.out.println(a.doubleValue() + b.doubleValue());
    }

    //2
    public static void len(String s) {
        System.out.println(s.length());
    }


    //3
    public static <T extends Number> void sqr(T a) {
        System.out.println(a.doubleValue() * a.doubleValue());
    }


    //4
    public static <T extends Number> void largestNum(ArrayList<T> list){
        T max = list.get(0);
        for(T i : list){
            if(i.doubleValue() > max.doubleValue()){
                max = i;
        }
        }
    }


    //5
    public static void shortestString(ArrayList<String> list){
        String min = list.get(0);
        for(String i : list){
            if(i.length() < min.length()){
                min = i;
            }
        }
        System.out.println(min);
    }


    //6
    public static ArrayList<Number> sortList(ArrayList<Number> list){
        list.sort(null);
        return list;
    }

    //7
    public static ArrayList<String> sortString(ArrayList<String> list){
        list.sort(null);
        return list;
    }

    //8
    public static Number median(ArrayList<Number> list){
        list.sort(null);
        int s = list.size();
        if (s %2 == 0){
            return ( list.get(s/2).doubleValue() + list.get(s/2 - 1).doubleValue() ) / 2;
        }
        return list.get(list.size()/2);
    }

    //9
    public static Integer wordCount(String s){
        String[] w = s.split("\s");
        return w.length;
    }

    //10
    public static Integer aCount(ArrayList<String> list){
        int count = 0;
        for(String i : list){
            if(i.contains("a")){
                count++;
            }
        }
        return count;
    }

    //Level 2


    //1
    public static Double secondLargest(ArrayList<Number> list){
        list.sort(null);
        return list.get(list.size() - 2).doubleValue();
    }


    //2
    public static String longestString(ArrayList<String> list){
        String max = list.get(0);
        for(String i : list){
            if(i.length() > max.length()){
                max = i;
            }
        }
        return max;
    }


    //3
    public static String longestCommonSubString(ArrayList<String> list){
        String max = "";
        String s = "";
        String l = "";
        if (list.get(0).length() > list.get(1).length()){
            s = list.get(1);
            l = list.get(0);
        } else {
            s = list.get(0);
            l = list.get(1);
        }   
        for (int i = 0; i < s.length(); i++){
            if (l.contains(Character.toString(s.charAt(i)))){
                for (int j = i; j < s.length(); j++){
                    if (l.contains(s.substring(i, j))){
                        if (s.substring(i, j).length() > max.length()){
                            max = s.substring(i, j);
                        }
                    }
                }
            }
        }
        
        return max;
    } // naive method, possible to optimize using recursion



    //4
    public static Integer sumOfDivisible5(ArrayList<Number> list) {
        int sum = 0;
        for (Number i : list){
            if (i.intValue() % 5 == 0 && i.intValue() % 3 == 0 && i.intValue()!= 0){
                sum += i.intValue();
            }
        }
        return sum;
    }


    //5
    public static Integer maxContArraySum(ArrayList<Integer> list){
        int max = 0;
        int sum ;
        for (int i = 0; i < list.size();i++){
            sum = 0;
            for (int j = list.size(); j > i; j--){
                for (int k : list.subList(i, j)){
                    sum += k;
                }
                if (sum > max){
                    max = sum;
                }
            }
        }
        return max;
    } // naive method, possible to optimize using recursion



// Level 3


//1
    public static Double secondSmallest(ArrayList<Number> list){
        list.sort(null);
        return list.get(1).doubleValue();
    }


    //2
    public static Integer maxDifference(ArrayList<Integer> list){
        list.sort(null);
        return list.get(list.size() - 1) - list.get(0);
    }


    //3
    public static Integer longestIncreasingSub(ArrayList<Integer> list){
        int max = 0;
        ArrayList<Integer> ans = new ArrayList<Integer>();
        ans.add(list.get(0));
        for (int i  : list){
            if (i > ans.get(ans.size() - 1)){
                ans.add(i);
            } 
            else {
                int pt1 = 0;
                int pt2 = ans.size();
                while(pt1 < pt2){
                    int mid = ((pt2 - pt1)/2) + pt1;
                    if (ans.get(mid) < i){
                        pt1 = mid + 1;
                    }
                    else {
                        pt2 = mid;

                    }
                }
                
            }  
        }
        return ans.size();
    } //binary search


    //4
    public static ArrayList<String> largestOverlap(ArrayList<String> list){
        int maxOverlap = 0;
        ArrayList<String> ans = new ArrayList<String>();
        for (int i = 0; i < list.size(); i++){
            for (int j = 0; j < list.size(); j++){
                if (i !=j){
                    int overlap = characterOverLap(list.get(i), list.get(j));
                    if (overlap > maxOverlap){
                        maxOverlap = overlap;
                        ans.clear();
                        ans.add(list.get(i));
                        ans.add(list.get(j));
                    }
                }
            }
        }
        return ans;
    }

    public static int characterOverLap(String list1, String list2){
        Map<Character, Integer> map1 = new HashMap<Character, Integer>();
        Map<Character, Integer> map2 = new HashMap<Character, Integer>();
        for (Character c : list1.toCharArray()){
            if (map1.containsKey(c)){
                map1.put(c, map1.get(c) + 1);
            } else {
                map1.put(c, 1);
            }
        }
        for (Character c : list2.toCharArray()){
            if (map2.containsKey(c)){
                map2.put(c, map2.get(c) + 1);
            } else {
                map2.put(c, 1);
            }
        }
        int count = 0;
        for (Character c : map1.keySet()){
            if (map2.containsKey(c)){
                count += Math.min(map1.get(c), map2.get(c));
            }
        }
        return count;
    }



    //5
    public static int smallestNotOfSum(ArrayList<Integer> list){
       list.sort(null);
       int n = list.size();
       int ans = 1;

        for (int i = 0; i < n ; i++){
            if (list.get(i) <= ans){
                ans += list.get(i);
            } else {
                break;
            }
        }
        return ans;
    }

    //6
    public static int medianOf2(ArrayList<Integer> list1, ArrayList<Integer> list2){
        list1.addAll(list2);
        list1.sort(null);
        int s = list1.size();
        if (s %2 == 0){
            return ( list1.get(s/2) + list1.get(s/2 - 1) ) / 2;
        }
        return list1.get(list1.size()/2);
    }

    //7 
    public static int longestPossiblePalindrome(String s){
        ArrayList<Character> arr = new ArrayList<>();
        for (char c : s.toCharArray()) {
            arr.add(c);
        }
        arr.sort(null);

        int length = 0;
        boolean hasMiddle = false;
        for (int i = 0; i < arr.size()-1; i++){
            if (arr.get(i) == arr.get(i+1)){
                length += 2;
                i++;
            } else {
                if(hasMiddle == false){
                    hasMiddle = true;
                    length++;
                } // add middle character for max length palindrome
            }
        }
        return length;

    }


//8
//9
//10
    public static ArrayList<String> sortedDistinc(ArrayList<String> list){
        ArrayList<Set<Character>> set = new ArrayList<Set<Character>>();
        for (String s : list){
            
        }
        return list;
    }




// Level 4

    //1
    public static int minStepsBBSort(ArrayList<Integer> list){
        int steps = 0;
        boolean swapped;
        do {
            swapped = false;
            for (int i = 0; i < list.size() - 1; i++) {
                if (list.get(i) > list.get(i + 1)) {
                    int temp = list.get(i);
                    list.set(i, list.get(i + 1));
                    list.set(i + 1, temp);
                    swapped = true;
                    steps++;
                }
            }
        } while (swapped);

        return steps;
    }


    // 2
    public static int sumSubsequencetCount(ArrayList<Integer> list, int k){
        int count = 0;
        int sum;
        for(int i= 0; i < list.size(); i++){
            sum = list.get(i);
            for (int j = i; j < list.size(); j++){
                sum = sum + list.get(j);
                if (sum == k){
                    count++;
                }
                else if (sum > k){
                    break;
                }
                else {
                    continue;
                }
            }
        }

        return count;
    }
    

    //3
    
    public static int longestCommonSubStringLength(ArrayList<String> list){
    
        return 0;
    }









    // -------Main Method-------


    public static void main(String[] args) {
        
    }
}