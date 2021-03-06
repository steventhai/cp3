
import java.io.*;
import java.util.InputMismatchException;

/**
 * Find next lexicographic permutation of a string.
 * 
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=24&page=show_problem&problem=82
 */
class IdCodes {

    public static void main(String[] args) throws Throwable {

        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);

        // InputReader in = new InputReader(new FileInputStream("IdCodes.inp"));
        // OutputWriter out = new OutputWriter(new FileOutputStream("IdCodes.out"));

        String line = in.readString();

        while (!line.startsWith("#")) {

            char[] chs = line.toCharArray();

            // Find k that chs[k] < chs[k + 1];
            int k = findK(chs);

            // Find l that chs[l] > chs[k] and l > k
            int l = findL(k, chs);

            // Swap chs[l] and chs[k]
            if (k != -1) {
                char temp = chs[k];
                chs[k] = chs[l];
                chs[l] = temp;

                // Reverse chs[k + 1] to chs[length - 1];
                reverse(chs, k + 1, chs.length - 1);

                out.printLine(String.valueOf(chs));
            } else {
                out.printLine("No Successor");
            }

            line = in.readString();
        }

        out.flush();
        out.close();
    }

    /**
     * Find k such that seq[k] < seq[k + 1]. Return -1 if no such k exists.
     */
    private static int findK(char[] seq) {
        for (int k = seq.length - 2; k >= 0; k--) {
            if (seq[k] < seq[k + 1]) {
                return k;
            }
        }

        return -1;
    }

    /**
     * Find l > k such that a[k] < a[l].
     */
    private static int findL(int k, char[] seq) {
        if (k != -1) {
            for (int l = seq.length - 1; l > k; l--) {
                if (seq[l] > seq[k])
                    return l;
            }
        }

        return -1;
    }

    /**
     * Reverse a character sequence.
     */
    private static void reverse(char[] seq, int left, int right) {
        while (left < right) {
            char temp = seq[left];
            seq[left] = seq[right];
            seq[right] = temp;
            left++;
            right--;
        }
    }

    //FAST IO
    private static class InputReader {
        private InputStream stream;
        private byte[] buf = new byte[1024];
        private int curChar;
        private int numChars;
        private SpaceCharFilter filter;

        public InputReader(InputStream stream) {
            this.stream = stream;
        }

        public int read() {
            if (numChars == -1)
                throw new InputMismatchException();
            if (curChar >= numChars) {
                curChar = 0;
                try {
                    numChars = stream.read(buf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (numChars <= 0)
                    return -1;
            }
            return buf[curChar++];
        }

        public int readInt() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            int res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public String readString() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            StringBuilder res = new StringBuilder();
            do {
                res.appendCodePoint(c);
                c = read();
            } while (!isSpaceChar(c));
            return res.toString();
        }

        public double readDouble() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            double res = 0;
            while (!isSpaceChar(c) && c != '.') {
                if (c == 'e' || c == 'E')
                    return res * Math.pow(10, readInt());
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            }
            if (c == '.') {
                c = read();
                double m = 1;
                while (!isSpaceChar(c)) {
                    if (c == 'e' || c == 'E')
                        return res * Math.pow(10, readInt());
                    if (c < '0' || c > '9')
                        throw new InputMismatchException();
                    m /= 10;
                    res += (c - '0') * m;
                    c = read();
                }
            }
            return res * sgn;
        }

        public long readLong() {
            int c = read();
            while (isSpaceChar(c))
                c = read();
            int sgn = 1;
            if (c == '-') {
                sgn = -1;
                c = read();
            }
            long res = 0;
            do {
                if (c < '0' || c > '9')
                    throw new InputMismatchException();
                res *= 10;
                res += c - '0';
                c = read();
            } while (!isSpaceChar(c));
            return res * sgn;
        }

        public boolean isSpaceChar(int c) {
            if (filter != null)
                return filter.isSpaceChar(c);
            return c == ' ' || c == '\n' || c == '\r' || c == '\t' || c == -1;
        }

        public String next() {
            return readString();
        }

        public interface SpaceCharFilter {
            public boolean isSpaceChar(int ch);
        }
    }

    private static class OutputWriter {
        private final PrintWriter writer;

        public OutputWriter(OutputStream outputStream) {
            writer = new PrintWriter(new BufferedWriter(new OutputStreamWriter(outputStream)));
        }

        public OutputWriter(Writer writer) {
            this.writer = new PrintWriter(writer);
        }

        public void print(Object... objects) {
            for (int i = 0; i < objects.length; i++) {
                if (i != 0)
                    writer.print(' ');
                writer.print(objects[i]);
            }
        }

        public void printLine(Object... objects) {
            print(objects);
            writer.println();
        }

        public void close() {
            writer.close();
        }

        public void flush() {
            writer.flush();
        }

    }

    private static class IOUtils {
        public static int[] readIntArray(InputReader in, int size) {
            int[] array = new int[size];
            for (int i = 0; i < size; i++)
                array[i] = in.readInt();
            return array;
        }
    }
}