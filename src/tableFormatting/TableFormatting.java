package tableFormatting;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class TableFormatting {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        List<List<String>> table = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            table.add(Arrays.stream(line.replaceFirst("[|]", "").split("[|]")).map(String::trim).collect(Collectors.toList()));
        }

        int[] widths = new int[table.get(0).size()];
        for (List<String> line : table) {
            for (int i = 0; i < line.size(); i++) {
                if (widths[i] < line.get(i).length()) {
                    widths[i] = line.get(i).length();
                }
            }
        }

        for (List<String> line : table) {
            System.out.print("|");
            for (int i = 0; i < line.size(); i++) {
                int space = widths[i] - line.get(i).length();
                int pos = space/2;

                StringBuilder s = new StringBuilder();
                if(space == 0){
                    s.append(line.get(i));
                }
                for (int j = 0; j < space; j++){
                    if(j == pos){
                        s.append(line.get(i));
                    }
                    s.append(" ");
                }

                System.out.print(s + "|");
            }
            System.out.println();
        }

    }
}
