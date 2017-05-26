import java.io.*;
import java.util.InputMismatchException;

/**
 * https://uva.onlinejudge.org/index.php?option=com_onlinejudge&Itemid=8&category=24&page=show_problem&problem=1861
 * 
 * Note: find which square, position is on ( (2k - 1)^2 < position <= (2k + 1)^2);
 * - position will be on the square with length: 2K + 1.
 */
class SpiralTap {

    public static void main(String[] args) throws Throwable {

        InputReader in = new InputReader(System.in);
        OutputWriter out = new OutputWriter(System.out);

        // InputReader in = new InputReader(new FileInputStream("SpiralTap.inp"));
        // OutputWriter out = new OutputWriter(new FileOutputStream("SpiralTap.out"));

        int size = in.readInt();
        long position = in.readLong();

        while (size != 0 && position != 0) {

            long k = 0;

            while (position > (2 * k + 1) * (2 * k + 1))
                k++;

            long x = (2 * k + size + 2) / 2;
            long y = x;
            long topRight = (2 * k + 1) * (2 * k + 1);
            long length = 2 * k;

            // down.
            for (int i = 0; i < length; i++) {

                if (topRight == position) {
                    break;
                }

                x--;
                topRight--;
            }

            // left.
            for (int i = 0; i < length; i++) {

                if (topRight == position) {
                    break;
                }

                y--;
                topRight--;
            }

            // up
            for (int i = 0; i < length; i++) {

                if (topRight == position) {
                    break;
                }

                x++;
                topRight--;
            }

            // right
            for (int i = 0; i < length; i++) {

                if (topRight == position) {
                    break;
                }

                y++;
                topRight--;
            }

            StringBuilder strBuilder = new StringBuilder();
            strBuilder.append("Line = ").append(x).append(", column = ").append(y).append('.');

            // Output.
            out.printLine(strBuilder.toString());

            size = in.readInt();
            position = in.readLong();
        }

        out.flush();
        out.close();
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