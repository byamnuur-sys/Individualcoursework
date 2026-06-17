package Question2;

public class Triangle extends Shape {
    private double side1;
    private double side2;
    private double side3;

    public Triangle(double side1, double side2, double side3, String color, boolean filled) {
        super(color, filled);
        
        // Basic positivity check
        if (side1 <= 0 || side2 <= 0 || side3 <= 0) {
            throw new InvalidShapeException(String.format(
                "Sides must be positive. Provided: %.2f, %.2f, %.2f", side1, side2, side3));
        }
        
        // Triangle inequality theorem validation
        if ((side1 + side2 <= side3) || (side1 + side3 <= side2) || (side2 + side3 <= side1)) {
            throw new InvalidShapeException(String.format(
                "The given sides (%.2f, %.2f, %.2f) violate the triangle inequality theorem.", 
                side1, side2, side3));
        }

        this.side1 = side1;
        this.side2 = side2;
        this.side3 = side3;
    }

    public double getSide1() { return side1; }
    public double getSide2() { return side2; }
    public double getSide3() { return side3; }

    @Override
    public double getArea() {
        // Heron's Formula
        double s = getPerimeter() / 2.0;
        return Math.sqrt(s * (s - side1) * (s - side2) * (s - side3));
    }

    @Override
    public double getPerimeter() {
        return side1 + side2 + side3;
    }

    @Override
    public void resize(double factor) {
        if (factor <= 0) {
            throw new InvalidShapeException("Resize factor must be positive. Provided: " + factor);
        }
        this.side1 *= factor;
        this.side2 *= factor;
        this.side3 *= factor;
    }

    @Override
    public String toString() {
        return String.format("Triangle[%s, sides=(%.2f, %.2f, %.2f)]", super.toString(), side1, side2, side3);
    }
}