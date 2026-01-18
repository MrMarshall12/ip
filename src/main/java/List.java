/** A class representing a list */
public class List {
    private String[] strings;

    public List() {
        strings = new String[100];
    }

    public void add(String string) {
        strings[strings.length] = string;
    }

    public void display() {
        for (int i = 0; i < strings.length; i++) {
            System.out.println((i + 1) + ". " + strings[i]);
        }
    }
}
