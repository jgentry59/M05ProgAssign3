import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Colors extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Text Color Selector");

        // Makes sliders for red, green, blue, and opacity percentages
        Slider redSlider = createSlider("Red", 0, 100);
        Slider greenSlider = createSlider("Green", 0, 100);
        Slider blueSlider = createSlider("Blue", 0, 100);
        Slider opacitySlider = createSlider("Opacity", 0, 100);

        // Makes a label to display the selected color
        Label colorLabel = new Label("Selected Color: ");

        // Makes a text element to display colored text
        Text coloredText = new Text("Color me!");
        coloredText.setFont(Font.font("Verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
        coloredText.setFill(Color.rgb(0, 0, 0, 1)); // Initial color (black)

        // Associates the text color to the slider values
        redSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateTextColor(coloredText, redSlider, greenSlider, blueSlider, opacitySlider));
        greenSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateTextColor(coloredText, redSlider, greenSlider, blueSlider, opacitySlider));
        blueSlider.valueProperty().addListener((observable, oldValue, newValue) -> updateTextColor(coloredText, redSlider, greenSlider, blueSlider, opacitySlider));
        opacitySlider.valueProperty().addListener((observable, oldValue, newValue) -> updateTextColor(coloredText, redSlider, greenSlider, blueSlider, opacitySlider));

        // Makes a VBox to arrange the UI elements
        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(redSlider, greenSlider, blueSlider, opacitySlider, colorLabel, coloredText);
        vbox.setSpacing(20);
        vbox.setPadding(new javafx.geometry.Insets(20));

        // Makes a scene and set it on the stage
        Scene scene = new Scene(vbox, 400, 300);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private Slider createSlider(String label, double min, double max) {
        Slider slider = new Slider(min, max, 0);
        slider.setShowTickLabels(true);
        slider.setShowTickMarks(true);
        slider.setMajorTickUnit(25);
        slider.setBlockIncrement(10);
        slider.setPrefWidth(300);
        slider.setLabelFormatter(new Slider.LabelFormatter() {
            @Override
            public String toString(double value) {
                return String.format("%.0f%%", value);
            }
        });
        Label sliderLabel = new Label(label + ": ");
        VBox.setMargin(sliderLabel, new javafx.geometry.Insets(0, 0, 0, 10));
        VBox.setMargin(slider, new javafx.geometry.Insets(0, 0, 10, 0));
        return slider;
    }

    private void updateTextColor(Text text, Slider redSlider, Slider greenSlider, Slider blueSlider, Slider opacitySlider) {
        double red = redSlider.getValue() / 100.0;
        double green = greenSlider.getValue() / 100.0;
        double blue = blueSlider.getValue() / 100.0;
        double opacity = opacitySlider.getValue() / 100.0;

        Color textColor = Color.rgb((int) (red * 255), (int) (green * 255), (int) (blue * 255), opacity);
        text.setFill(textColor);
    }
}
