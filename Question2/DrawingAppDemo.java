package Question2;

public class DrawingAppDemo {

    public static void main(String[] args) {
        System.out.println("=== 1. Demonstrating Exception Handling ===");
        try {
            // Attempting to create an invalid triangle (violating inequality theorem)
            System.out.println("Attempting to create Triangle(1, 2, 10)...");
            Shape invalidTriangle = new Triangle(1, 2, 10, "Red", true);
        } catch (InvalidShapeException e) {
            System.err.println("Caught Expected Exception: " + e.getMessage());
        }

        System.out.println("\n=== 2. Instantiating Valid Polymorphic Shapes ===");
        Shape[] shapes = new Shape[3];
        shapes[0] = new Circle(3.5, "Blue", true);
        shapes[1] = new Rectangle(4.0, 5.5, "Green", false);
        shapes[2] = new Triangle(3.0, 4.0, 5.0, "Yellow", true);

        System.out.println("\n=== 3. Calling printAreas Method (Dynamic Binding) ===");
        printAreas(shapes);

        System.out.println("\n=== 4. Identifying Largest Shape ===");
        Shape largestShape = largest(shapes);
        if (largestShape != null) {
            System.out.printf("The shape with the largest area is: %s with an Area of %.2f\n", 
                largestShape.getClass().getSimpleName(), largestShape.getArea());
        }

        System.out.println("\n=== 5. Resizing the Shapes Collection ===");
        for (Shape shape : shapes) {
            System.out.printf("Before Resize: %s | Area: %.2f\n", shape.getClass().getSimpleName(), shape.getArea());
            shape.resize(2.0); // Double linear dimensions
            System.out.printf("After Resize (Factor 2): Area scales to: %.2f\n\n", shape.getArea());
        }
    }

    public static void printAreas(Shape[] shapes) {
        for (Shape shape : shapes) {
            System.out.printf("Shape Type: %-10s | Color: %-6s | Area: %-7.2f | Perimeter: %.2f\n",
                    shape.getClass().getSimpleName(), shape.getColor(), shape.getArea(), shape.getPerimeter());
        }
    }

    public static Shape largest(Shape[] shapes) {
        if (shapes == null || shapes.length == 0) return null;
        Shape largest = shapes[0];
        for (int i = 1; i < shapes.length; i++) {
            if (shapes[i].getArea() > largest.getArea()) {
                largest = shapes[i];
            }
        }
        return largest;
    }
}
