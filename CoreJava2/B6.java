import java.util.ArrayList;

public class B6 {
    public static void main(String[] args) {
        
    }

    public static int maxProfit(ArrayList<Integer> prices){
        int maxProfit = 0;
        for (int i = 0; i < prices.size();i++){
            for (int j = i + 1; j < prices.size(); j++){
                int profit = prices.get(j) - prices.get(i);
                if (profit > maxProfit){
                    maxProfit = profit;
                }
            }
        }
        return maxProfit;
    }
}