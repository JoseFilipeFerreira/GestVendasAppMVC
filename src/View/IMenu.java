package View;

public interface IMenu {

    enum MenuInd{}

    Menu parser(String str);

    Menu selectOption(int i);

    Menu back();

    String toString();

}
