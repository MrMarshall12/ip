package erika.entities;

public class Events extends Task {
    private String begin;
    private String end;

    public Events(String taskName, String begin, String end) {
        super(taskName);
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + begin
                + " to: " + end + ")";
    }
}
