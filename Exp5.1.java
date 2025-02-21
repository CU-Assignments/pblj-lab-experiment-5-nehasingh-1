import java.util.ArrayList;
import java.util.List;
public class SimpleSumCalculator {
    public static Integer parseStringToInteger(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format: " + str);
            return null; 
        }
    }
    public static int calculateSum(List<Integer> integers) {
        int sum = 0;
        for (Integer number : integers) {
            if (number != null) { 
                sum += number; 
            }
        }
        return sum;
    }
    public static void main(String[] args) {
        List<Integer> numbers1 = new ArrayList<>();
        numbers1.add(10); 
        numbers1.add(20); 
        numbers1.add(30); 
        numbers1.add(parseStringToInteger("40")); 
        numbers1.add(parseStringToInteger("50")); 
        System.out.println("The sum of the list is: " + calculateSum(numbers1)); 
        List<Integer> numbers2 = new ArrayList<>();
        numbers2.add(parseStringToInteger("100")); 
        numbers2.add(parseStringToInteger("200"));
        numbers2.add(parseStringToInteger("300")); 
        System.out.println("The sum of the list is: " + calculateSum(numbers2)); 
        List<Integer> numbers3 = new ArrayList<>();
        numbers3.add(parseStringToInteger("50")); 
        numbers3.add(parseStringToInteger("invalid"));
        numbers3.add(parseStringToInteger("70"));
        System.out.println("The sum of the list is: " + calculateSum(numbers3)); 
    }
}
