import java.io.FileInputStream;
import java.util.Scanner;

/**
 * 573 - The Snail
 */
class TheSnail {

    public static void main(String[] args) throws Throwable {
        Scanner scanner = new Scanner(new FileInputStream("TheSnail.txt"));
        
        int h = scanner.nextInt(); // height of the well.
        
        while (h != 0) {

            int u = scanner.nextInt(); // distance the snail climbs up during the day.
            int d = scanner.nextInt(); // distance the snail slides down during the night.
            int f = scanner.nextInt(); // fatigue factor as a percentage.
        
            process(h, u, d, f);

            h = scanner.nextInt(); // next height of the well.
        }
        
        scanner.close();
    }

    private static void process(int h, int u, int d, int f) {
        int day = 1;
        double distanceClimbed = u;
        boolean success = false;
        double percentSlide = u * f / 100d;
        double climbingHeight = 0;

        while (climbingHeight <= h || climbingHeight >= 0) {

            if (day != 1) {
                distanceClimbed -= percentSlide;
            }

            if (distanceClimbed < 0) distanceClimbed = 0;

            climbingHeight += distanceClimbed;

            if (climbingHeight > h) {
                success = true;
                break;
            }

            // Sliding at night.
            climbingHeight -= d;

            if (climbingHeight < 0) {
                success = false;
                break;
            }

            day++;         
        }

        System.out.printf("%s on day %d%n", success ? "success" : "failure", day);
    }
}
