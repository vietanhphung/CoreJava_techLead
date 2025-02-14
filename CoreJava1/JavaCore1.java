import java.lang.reflect.Array;

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
    public static String longestCommonSubSequence(ArrayList<String> list){
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
    
    public static int lengthOfLongestCommonSubString(ArrayList<String> list){
        int len = 0;
        String shotestString = list.get(0);
        for (String s : list){
            if(s.length() < shotestString.length()){
                shotestString = s;
            }
        }

        for (int i = 0; i < shotestString.length(); i++){
            for (int j = i; j < shotestString.length(); j++){
                String sub = shotestString.substring(i, j);
                boolean isCommon = true;
                for (String s : list){
                    if (!s.contains(sub)){
                        isCommon = false;
                        break;
                    }
                }
                if (isCommon && sub.length() > len){
                    len = sub.length();
                }
            }
        }

        return len;
    } // potential for optimization


    //4
    //5
    //6
    public static int maxProductOf3(ArrayList<Integer> list){
        list.sort(null);
        int n = list.size();
        return Math.max(list.get(0) * list.get(1) * list.get(n-1), list.get(n-1) * list.get(n-2) * list.get(n-3));
    }

    //7
    public static ArrayList<String> sortedByNumberOfDistincWords(ArrayList<String> list){
        Map<String, Integer> wCountInEachString = new HashMap<String, Integer>();
        Map<String, Integer> distincWordInString = new HashMap<String, Integer>();
        for (String string : list){
            String[] words = string.split(" ");
            for (String w : words){
                if (wCountInEachString.containsKey(w)){
                    wCountInEachString.put(w, wCountInEachString.get(w) + 1);
                } else {
                    wCountInEachString.put(w, 1);
                }
            } // create map of words in a String and their frequency
        distincWordInString.put(string, wCountInEachString.size());                 
        }
        list.sort((a, b) -> distincWordInString.get(a) - distincWordInString.get(b));
   
        return list;
    }

    //8
    //9
    public static int longestIncreasingSubsequenceBy1(ArrayList<Integer> list){
        int n = list.size();
        int len = 0;
        int longest = 0;
        for (int i =0; i <n; i++){
            if (list.get(i) == list.get(i+1) - 1){
                len++;
            }
            if (len > longest){
                longest = len;
            }
            if (list.get(i) != list.get(i+1) - 1){
                len = 0;
            }
        }
        return longest;
    }

    //10
    public static ArrayList<String> twoLongestOverlap(ArrayList<String> list, int k){
        String overLap = longestCommonSubSequence(list);
        ArrayList<String> ans = new ArrayList<String>();
        for (String s : list){
            if (s.contains(overLap) && overLap.length() >= k){
                ans.add(s);
            }
            if (ans.size() == 2){
                break;
            }
        }
        return ans;
    }

    //Level 5

    //1
    public static <T> ArrayList<T> reverse(ArrayList<T> list){
        ArrayList<T> ans = new ArrayList<T>();
        for (int i = list.size() - 1; i >= 0; i--){
            ans.add(list.get(i));
        }
        return ans;
    }

    //2
    public static <T> ArrayList<T[]> chunk(ArrayList<T> list, int n){
        ArrayList<T[]> ans = new ArrayList<T[]>();
        ArrayList<T> tempRow = new ArrayList<T>();
        while ( list.size() > 0){
            for (int i = 0; i < n; i++){
                if (list.size() > 0){
                    tempRow.add(list.get(0));
                    list.remove(0);
                }
                else {
                    break;
                }
            }
            ans.add((T[]) tempRow.toArray());
            tempRow.clear();
        }
        return ans;
    }


    //3
    public static ArrayList<Integer> uniq(ArrayList<Integer> list){
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for (int i : list){
            if (!ans.contains(i)){
                ans.add(i);
            }
        }
        return ans;
    }

    //4
    public static ArrayList<Collection> uniqCollection(ArrayList<Collection> list) {
        ArrayList<Collection> ans = new ArrayList<Collection>();
        for (Collection c : list){
            if (!ans.contains(c)){
                ans.add(c);
            }
        }
        return ans;
    }


    //5
    public static <K, X> Map<X, ArrayList<Map<K , X>>> groupBy(ArrayList<Map<K , X>> list, K key){
        Map<X, ArrayList<Map<K , X>>> ans = new HashMap<>();
        for (Map<K, X> obj : list){
            if (obj.containsKey(key)){
                X ansKey = obj.get(key);
                if(ans.containsKey(ansKey)){
                    ans.get(ansKey).add((Map<K,X>) obj);
                } else {
                    ArrayList<Map<K,X>> temp = new ArrayList<Map<K, X>>();
                    temp.add(obj);
                    ans.put(ansKey, temp);
                }
            }
        }
        return ans;
    }


    //6
    public static String trimAll(String s){
        String[] ans = s.split("\\s+");
        return String.join(" ", ans);

    }


    //7 

    public static ArrayList<Map<String, Integer>> mapKey(ArrayList<String> keys, ArrayList<Map<String, Integer>> values){
        ArrayList<Map<String, Integer>> ans = new ArrayList<Map<String, Integer>>();
        for (int i= 0; i < values.size(); i++){
            Map<String, Integer> temp = new HashMap<String, Integer>();
            for (String k : keys){
                if (values.get(i).containsKey(k)) { 
                    temp.put(k, values.get(i).get(k));
                }
            }
            ans.add(temp);
        }
        return ans;
    }



    //8



    public static ArrayList<Map<String, Integer>> switchOrder(ArrayList<Map<String, Integer>> list, int id, int order) {
        Map<String, Integer> targetItem = null;
        Map<String, Integer> conflictingItem = null;

        // Step 1: Find the target and conflicting items
        for (Map<String, Integer> m : list) {
            if (m.get("id") == id) {
                targetItem = m;
            }
            if (m.get("order") == order) {
                conflictingItem = m;
            }
        }

        // Step 2: Swap only if both exist
        if (targetItem != null && conflictingItem != null) {
            int oldOrder = targetItem.get("order");
            targetItem.put("order", order);
            conflictingItem.put("order", oldOrder);
        }

        return list;
    }

    public static void main(String[] args) {
        ArrayList<Map<String, Integer>> arr = new ArrayList<>(Arrays.asList(
            new HashMap<>(Map.of("id", 10, "order", 0)),
            new HashMap<>(Map.of("id", 12, "order", 1)),
            new HashMap<>(Map.of("id", 9, "order", 2)),
            new HashMap<>(Map.of("id", 11, "order", 3))
        ));

        System.out.println("Before: " + arr);
        switchOrder(arr, 9, 1);
        System.out.println("After: " + arr);
    }


    //9

    public static Map<String,Integer>   sumOfKeyElement(ArrayList<Map<String, Integer>> list){
        Map<String, Integer> sum = new HashMap<String, Integer>();
        for (Map<String, Integer> m : list){
            for (String k : m.keySet()){
                if (sum.containsKey(k)){
                    sum.put(k, sum.get(k) + m.get(k).intValue());
                } else {
                    sum.put(k, m.get(k));
                }
            }
        }
        return sum;
    }


    //10

    public static void templateString(File file, Map<String, String> map){
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            StringBuilder content = new StringBuilder();  
    
            String line = reader.readLine();
            while (line != null){
                if (line.contains("{{")){
                    for(String key : map.keySet()){
                        if (line.contains("{{" + key + "}}")){
                            line = line.replace("{{" + key + "}}", map.get(key));
                        }
                    }
                }
                content.append(line).append("\n");
            }
            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(content.toString());
            writer.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }






    // -------Main Method-------


    public static void main(String[] args) {

    }
}