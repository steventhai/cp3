import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 * 10141 - Request for Proposal
 * TODO: WA
 */
class RequestForProposal {

    public static void main(String[] args) throws Throwable {
        Scanner scanner = new Scanner(new FileInputStream("RequestForProposal.txt"));
        
        int n = scanner.nextInt(); // num of requirements.
        int rfp = 1;

        while (n != 0) {

            int p = scanner.nextInt(); // number of proposals.

            Set<String> requirements = new HashSet<>();
            scanner.nextLine();
            for (int i = 0; i < n; i++) {
                requirements.add(scanner.nextLine());
            }

            String[] proposalNames = new String[p];
            double[] prices = new double[p];
            int[] metReqs = new int[p];
            for (int i = 0; i < p; i++) {
                proposalNames[i] = scanner.nextLine();
                prices[i] = scanner.nextDouble();
                int r = scanner.nextInt();
                
                scanner.nextLine();

                int met = 0;
                for (int j = 0; j < r; j++) {
                    if (requirements.contains(scanner.nextLine())) {
                        met++;
                    }
                }
                metReqs[i] = met;
            }

            String proposal = process(proposalNames, prices, metReqs);

            // Output.
            if (rfp == 1) {
                System.out.printf("RFP #%d%n%s", rfp++, proposal);
            } else {
                System.out.printf("%n%nRFP #%d%n%s", rfp++, proposal);
            }

            n = scanner.nextInt(); // num of requirements.
        }
        
        scanner.close();
    }

    private static String process(String[] proposalNames, double[] prices, int[] metReqs) {
        
        List<Integer> mets = new ArrayList<>();
        String proposal = "";
        
        int max = metReqs[0];
        int length = metReqs.length;
        mets.add(0);
        for (int i = 1; i < length; i++) {
            if (max < metReqs[i]) {
                mets.clear();
                max = metReqs[i];
                mets.add(i);
            } else if (max == metReqs[i]) {
                mets.add(i);
            }
        }

        double min = prices[mets.get(0)];
        int index = mets.get(0);
        for (int i = 1; i < mets.size();i++) {
            double met = prices[mets.get(i)];
            if (met < min) {
                min = met;
                index = mets.get(i);
            }
        }

        return proposalNames[index];
    }
}
