import java.util.Scanner;

/**
 * 10114 - Loansome Car Buyer
 */
public class LoansomeCarBuyer {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int term = scanner.nextInt();
        
        while (term >= 0) {
            
            int result = 0;            

            float downPayment = scanner.nextFloat();
            float original = scanner.nextFloat();
            int numRecords = scanner.nextInt();
            
            float depValue = original;
            float paid = downPayment;

            while (numRecords > 0) {
                int month = scanner.nextInt();
                float percent = scanner.nextFloat();

                while ()
                depValue -= depValue * percent;

            }

            term = scanner.nextInt();
        }
        scanner.close();
    }

    private static int findMid(int a, int b, int c) {
        return Math.max(Math.min(a, b), Math.min(Math.max(a, b), c));
    }
}