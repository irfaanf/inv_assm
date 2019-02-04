import java.util.*;

public class Factorization {

    public static List<Integer> getFactors(int n){
        List<Integer> factors = new ArrayList<Integer>();

        for(int i=1; i<=n; i++){
            if(n%i == 0){
                factors.add(new Integer(i));
            }
        }

        return factors;
    }

    public static int highestCommonFactor(int[] numbers){
        List<Integer> commonFactors = new ArrayList<Integer>();

        for (int i=0; i<numbers.length; i++){
            List<Integer> factors = getFactors(numbers[i]);

            if (commonFactors.size() == 0){
                commonFactors = factors;
            } else {
                commonFactors.retainAll(factors);
            }
        }

        return Collections.max(commonFactors);
    }
}
