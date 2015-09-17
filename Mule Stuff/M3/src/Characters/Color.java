import javafx.scene.paint.Color;


public enum Color {
    BLUE("Blue", Color.BLUE);
    RED("Red", Color.RED);
    GREEN("Green", Color.GREEN);
    PINK("Pink", Color.PINK);

    private String color;
    private Color paint;


    Color(String color, Color paint) {
        this.color = color;
        this.paint = paint;
    }

    public color() {
        return color;
    }
}