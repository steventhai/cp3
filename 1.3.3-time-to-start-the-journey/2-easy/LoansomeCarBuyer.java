import java.util.Scanner;

/**
 * 10114 - Loansome Car Buyer
 */
class LoansomeCarBuyer {
    public static void main(String[] args) {
        // Input
		Scanner scanner = new Scanner(System.in);
        int term = scanner.nextInt();
        while (term >= 0) {
            
            float downPayment = scanner.nextFloat();
            float loan = scanner.nextFloat();
            
            float initialValue = downPayment + loan;
            float payment;
            if (term != 0) {
                payment = loan / term;
            } else {
                payment = loan;
            }
            
            int numRecords = scanner.nextInt();
            
            int[] months = new int[numRecords];
            float[] values = new float[numRecords];
            
            for (int i = 0; i < numRecords; i++) {
            	months[i] = scanner.nextInt();
            	values[i] = scanner.nextFloat();
            }
            
            // Calculation.
            float prev = values[0];
            float currentValue = initialValue - initialValue * prev;
            float owe = loan;
            int currentMonth;
            
            if (currentValue > owe) {
            	currentMonth = 0;
            } else {
	            int i = 1;
	            currentMonth = 1;
	            
	            while (true) {
	            	
	            	if (i < numRecords && currentMonth == months[i]) {
	            		prev = values[i];
	            		i++;
	            	}
	            	
	            	currentValue = currentValue - currentValue * prev;
	            	owe -= payment;
	            	
	            	if (currentValue > owe) break;
	            	
	            	currentMonth++;
	            }
            }
            
            if (currentMonth == 1) {
            	System.out.println(currentMonth + " month");
            } else {
            	System.out.println(currentMonth + " months");
            }

            term = scanner.nextInt();
        }
        scanner.close();
	}
}