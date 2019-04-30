import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
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
        System.out.println(tab);


        ArrayList<String> strings = new ArrayList<>();
        for(int i = 0; i < 2000; i++)
            strings.add("olaaa");
        Navigator<String> nav = new Navigator<>(strings, 20, 3);
        System.out.println(nav);
        nav.next();
        System.out.println(nav);
    }
}
