package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.List;
import java.util.ArrayList;
public class BuyAndSellStockTwice {
  @EpiTest(testDataFile = "buy_and_sell_stock_twice.tsv")
  public static double buyAndSellStockTwice(List<Double> prices) {
    // TODO - you fill in here.
    List<Double> firstBuyProfit = new ArrayList<>();
    double minPrice = Double.MAX_VALUE, maxPrice = Double.MIN_VALUE;
    double maxProfit = 0.0;
    for(int i = 0; i < prices.size(); i++){
      minPrice = Math.min(minPrice, prices.get(i));
      maxProfit = Math.max(maxProfit, prices.get(i) - minPrice);
      firstBuyProfit.add(maxProfit);
    }
    for(int i = prices.size() - 1; i > 0; i--){
      maxPrice = Math.max(maxPrice, prices.get(i));
      maxProfit = Math.max(maxProfit, firstBuyProfit.get(i-1) + maxPrice - prices.get(i));
    }
    return maxProfit;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStockTwice.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
