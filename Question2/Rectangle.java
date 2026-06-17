package Question2;

    public class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height, String color, boolean filled) {
        super(color, filled);
        if (width <= 0 || height <= 0) {
            throw new InvalidShapeException(String.format(
                "Dimensions must be positive. Width: %.2f, Height: %.2f", width, height));
        }
        this.width = width;
        this.height = height;
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public double getPerimeter() {
        return 2 * (width + height);
    }

    @Override
    public void resize(double factor) {
        if (factor <= 0) {
            throw new InvalidShapeException("Resize factor must be positive. Provided: " + factor);
        }
        this.width *= factor;
        this.height *= factor;
    }

    @Override
    public String toString() {
        return String.format("Rectangle[%s, width=%.2f, height=%.2f]", super.toString(), width, height);
    }
}
    
