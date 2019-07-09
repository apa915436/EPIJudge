package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;
import java.util.Arrays;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class IntAsArrayMultiply {
  @EpiTest(testDataFile = "int_as_array_multiply.tsv")
  public static List<Integer> multiply(List<Integer> num1, List<Integer> num2) {
    // TODO - you fill in here.

    final int sign = num1.get(0) > 0 ^ num2.get(0) > 0 ? -1 : 1;
    num1.set(0, Math.abs(num1.get(0)));
    num2.set(0, Math.abs(num2.get(0)));

    List<Integer> result = new ArrayList<>(Collections.nCopies(num1.size() + num2.size(), 0));
    for(int i = num1.size()-1; i >= 0; i--){
      for(int j = num2.size()-1; j >= 0; j--){
        result.set(i + j + 1, num1.get(i) * num2.get(j) + result.get(i + j + 1));
        result.set(i + j, result.get(i+j+1) / 10 + result.get(i+j));
        result.set(i + j + 1, result.get(i + j + 1) % 10);
      }
    }
    int firstNonZero = 0;
    while(result.size() > firstNonZero && result.get(firstNonZero) == 0){
      firstNonZero++;
    }
    if(firstNonZero == result.size()){
      return Arrays.asList(0);
    }
    result = result.subList(firstNonZero, result.size());
    result.set(0, result.get(0) * sign);
    return result;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "IntAsArrayMultiply.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
