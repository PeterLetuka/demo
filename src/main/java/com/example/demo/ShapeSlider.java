import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;

public class ShapeSlider extends Application {
    private int currentIndex = 0;
    private Shape[] shapes;
    private Pane pane;

    @Override
    public void start(Stage primaryStage) {
        // Create shapes
        Circle circle = new Circle(100, 100, 50);
        circle.setFill(Color.BLUE);

        Rectangle rectangle = new Rectangle(50, 50, 100, 100);
        rectangle.setFill(Color.RED);

        Polygon triangle = new Polygon();
        triangle.getPoints().addAll(new Double[]{
                100.0, 50.0,
                50.0, 150.0,
                150.0, 150.0});
        triangle.setFill(Color.GREEN);

        shapes = new Shape[]{circle, rectangle, triangle};

        // Initialize pane
        pane = new Pane();
        pane.getChildren().add(shapes[currentIndex]);

        // Create buttons
        Button previousButton = new Button("Previous");
        previousButton.setOnAction(e -> showPreviousShape());

        Button nextButton = new Button("Next");
        nextButton.setOnAction(e -> showNextShape());

        Button changeBackgroundButton = new Button("Change Background");
        changeBackgroundButton.setOnAction(e -> changeBackground());

        // Create layout
        HBox buttonsLayout = new HBox(10);
        buttonsLayout.getChildren().addAll(previousButton, nextButton, changeBackgroundButton);

        HBox.setMargin(previousButton, new javafx.geometry.Insets(10, 10, 10, 10));
        HBox.setMargin(nextButton, new javafx.geometry.Insets(10, 10, 10, 10));
        HBox.setMargin(changeBackgroundButton, new javafx.geometry.Insets(10, 10, 10, 10));

        HBox mainLayout = new HBox();
        mainLayout.getChildren().addAll(pane, buttonsLayout);

        // Set up scene
        Scene scene = new Scene(mainLayout, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Shape Slider");
        primaryStage.show();
    }

    private void showPreviousShape() {
        pane.getChildren().clear();
        currentIndex = (currentIndex - 1 + shapes.length) % shapes.length;
        pane.getChildren().add(shapes[currentIndex]);
    }

    private void showNextShape() {
        pane.getChildren().clear();
        currentIndex = (currentIndex + 1) % shapes.length;
        pane.getChildren().add(shapes[currentIndex]);
    }

    private void changeBackground() {
        Color newColor = Color.rgb((int) (Math.random() * 255), (int) (Math.random() * 255), (int) (Math.random() * 255));
        shapes[currentIndex].setFill(newColor);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
