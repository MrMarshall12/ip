/** A class representing a list */
public class List {
    private String[] strings;
    private int index;

    public List() {
        strings = new String[100];
        index = 0;
    }

    public void add(String string) {
        strings[index] = string;
        index++;
    }

    public void display() {
        for (int i = 0; i < strings.length; i++) {
            System.out.println((i + 1) + ". " + strings[i]);
        }
    }
}
