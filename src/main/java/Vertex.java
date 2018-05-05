/**
 * Created by pante on 05.05.2018.
 */
public class Vertex {
    private int id;
    private int color;

    public Vertex(int id, int color) {
        this.id = id;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", color=" + color +
                '}';
    }
}
