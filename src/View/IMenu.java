package View;

import java.util.List;
import java.util.Map;

public interface IMenu {

        enum MenuInd {
            Initial,
            Categories,
            Binary,
            Save,
            Load,
            Static,
            Dynamic,
            Q1,
            Q2,
            Q3,
            Q4,
            Q5,
            Q6,
            Q7,
            Q8,
            Q9,
            Q10,
            Q1_1,
            Q1_2
        }

        boolean getRun();

        MenuInd getMenu();

        int getInputInt(String error, String text);

        String getInputString(String error, String text);

        Menu parser();

        Menu back();

        void showQ1(List<String> notBought, String time);

        void showQ2(Map.Entry<Integer, Integer> sales, int mesSales, int filialSales, String time);

        void showQ3(String client, int mes, Map.Entry<Integer, Map.Entry<Integer, Double>> cliStats, String time);

        void showQ4(String produto, int mes, Map.Entry<Integer, Map.Entry<Integer, Double>> prodStats, String time);

        void showQ5(List<String> prodsCli, String client, String time);

        void showQ6(List<List<String>> prodsM, String time);

        void showQ7(List<String> clis, String time);

        void showQ8(List<String> clis, String time);

        void showQ9(List<List<String>> clis, String time);

        void showQ10(Map<String, Double> fatTotal, int mes, int filial, String time);

        void showQ11(List<List<String>> val, String time);

        void showQ12(String time, List<List<String>> monthly, int nMeses, int nFiliais);


        String toString();
}
