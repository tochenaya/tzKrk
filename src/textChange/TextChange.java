package textChange;

import java.util.Scanner;

public class TextChange {
    private static char[] in;
    private static char[] out;
    private static int[] pos;


    public static void main(String args[]) throws Exception {

        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        scanner.nextLine();
        String text = scanner.nextLine();
        in = text.toCharArray();
        pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[i] = scanner.nextInt();
        }

        out = new char[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                out[pos[j] - 1] = in[j];
            }
            if (i == m - 1) continue;
            System.arraycopy(out, 0, in, 0, n);
        }

        for (char c : out) {
            System.out.print(c);
        }
    }
}
