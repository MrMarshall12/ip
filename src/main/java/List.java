/** A class representing a list */
public class List {
    private String[] strings;
    private int index;

    public List() {
        strings = new String[100];
        index = 0;
    }

    /**
     * Adds string to strings array
     *
     * @return the added string
     */
    public String add(String string) {
        strings[index] = string;
        index++;
        return string;
    }

    /** Prints non-empty elements of the list */
    public void display() {
        for (int i = 0; i < strings.length; i++) {
            if (strings[i] == null) {
                break;
            }
            System.out.println((i + 1) + ". " + strings[i]);
        }
    }
}
