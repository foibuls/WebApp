package hello;

public class Baton {
    private int id;
    private String text;

    public Baton(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }
}
