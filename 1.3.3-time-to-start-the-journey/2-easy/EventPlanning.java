import java.util.Scanner;

/**
 * 11559 - Event Planning
 */
public class EventPlanning {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNext()) {
            int minimumCost = Integer.MAX_VALUE;

            int n = sc.nextInt();
            int budget = sc.nextInt();
            int hotelCount = sc.nextInt();
            int weekendCount = sc.nextInt();

            for (int i = 0; i < hotelCount; i++) {
                int price = sc.nextInt();
                for (int j = 0; j < weekendCount; j++) {
                    int bedCount = sc.nextInt();
                    if (bedCount >= n) {
                        int totalCost = price * n;
                        if (totalCost <= budget) {
                            if (totalCost < minimumCost) {
                                minimumCost = totalCost;
                                break;
                            }
                        }
                    }
                }
            }
            if (minimumCost == Integer.MAX_VALUE) {
                System.out.println("stay home");
            } else {
                System.out.println(minimumCost);
            }
        }
    }
}
