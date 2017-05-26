import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class JollyJumpers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        while(scanner.hasNext()) {
            Set<Integer> set = new HashSet<>();
            int n = scanner.nextInt();

            int[] array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = scanner.nextInt();
            }

            if (n == 1) {
                System.out.println("Jolly");
                continue;
            }

            for (int i = 0; i < n - 1; i++) {
                int temp = Math.abs(array[i] - array[i + 1]);
                if (temp < n) {
                    set.add(temp);
                }
            }

            if (set.size() == n - 1) {
                System.out.println("Jolly");
            } else {
                System.out.println("Not jolly");
            }
        }
    }
}