import java.util.Scanner;

/**
 * 11799 - Horror Dash
 */
class HorrorDash {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int t = scanner.nextInt();
        int c = 1;
        while(t-- > 0) {
            int n = scanner.nextInt();
            int max = 1;
            for (int i = 0; i < n; i++) {
                int value = scanner.nextInt();
                if (value > max) {
                    max = value;
                }
            }

            System.out.printf("Case %d: %d%n", c++, max);
        }

        scanner.close();
    }
}
