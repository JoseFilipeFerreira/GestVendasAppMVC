import View.Menu;
import View.Navigator;
import View.Table;

import java.util.ArrayList;
import java.util.Scanner;

import static java.lang.System.out;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<ArrayList<String>> matrix = new ArrayList<>();
        ArrayList<String> line  = new ArrayList<>();
        ArrayList<String> linLabel = new ArrayList<>();
        ArrayList<String> colLabel = new ArrayList<>();
        for(int i = 0; i < 12; i++) {
            line.add("1234");
            colLabel.add("label");
        }
        for(int i = 0; i < 3; i++) {
            matrix.add(line);
            linLabel.add("ola");
        }
        Table<String> tab = new Table<>(matrix, linLabel, colLabel );

        ArrayList<String> strings = new ArrayList<>();
        for(int i = 0; i < 2000; i++) {
            strings.add("olaa");
            strings.add("aí");
            strings.add("general");
            strings.add("robertoni");
        }
        Navigator<String> nav = new Navigator<>(strings,20);

        Menu menu = new Menu();
        while(true) {
            out.print("\033\143");
            out.println(menu);
            menu.parser(scanner.nextLine());
        }
    }
}
