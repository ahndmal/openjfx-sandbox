import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import javafx.util.StringConverter;

import java.util.ArrayList;
import java.util.List;

import static javafx.geometry.Pos.CENTER;

public class HelloFX extends Application {

    private DoubleProperty startX = new SimpleDoubleProperty();
    private DoubleProperty startY = new SimpleDoubleProperty();
    private DoubleProperty shownX = new SimpleDoubleProperty();
    private DoubleProperty shownY = new SimpleDoubleProperty();

    private final ChoiceBox<Pair<String, String>> assetClass = new ChoiceBox<>();
    private final static Pair<String, String> EMPTY_PAIR = new Pair<>("", "");

    @Override
    public void start(Stage primaryStage) {
        Label label = new Label("Asset Class:");
        assetClass.setPrefWidth(200);
        Button saveButton = new Button("Save");

        HBox hbox = new HBox(
                label,
                assetClass,
                saveButton);
        hbox.setSpacing(10.0d);
        hbox.setAlignment(Pos.CENTER);
        hbox.setPadding(new Insets(40));

        Scene scene = new Scene(hbox);

        initChoice();
//        assetClass.getItems().add(new Pair<>("AAA", "AAA"));
//        assetClass.getItems().add(new Pair<>("BBB", "BBB"));

        saveButton.setOnAction(
                (evt) -> {
                    System.out.println(evt.getTarget());
                    System.out.println("saving " + assetClass.getValue());
                }
        );

        primaryStage.setTitle("ChoicesApp");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void initChoice() {

        List<Pair<String, String>> assetClasses = new ArrayList<>();
        assetClasses.add(new Pair("Equipment", "20000"));
        assetClasses.add(new Pair("Furniture", "21000"));
        assetClasses.add(new Pair("Investment", "22000"));

        assetClass.setConverter(new StringConverter<Pair<String, String>>() {
            @Override
            public String toString(Pair<String, String> pair) {
                return pair.getKey();
            }

            @Override
            public Pair<String, String> fromString(String string) {
                return null;
            }
        });

        assetClass.getItems().add(EMPTY_PAIR);
        assetClass.getItems().addAll(assetClasses);
        assetClass.setValue(EMPTY_PAIR);

    }

    private void grid(Stage primaryStage) {
        Label startLabel = new Label("Start Dimensions");
        TextField startTF = new TextField();
        startTF.textProperty().bind(
                Bindings.format("(%.1f, %.1f)", startX, startY)
        );

        Label shownLabel = new Label("Shown Dimensions");
        TextField shownTF = new TextField();
        shownTF.textProperty().bind(
                Bindings.format("(%.1f, %.1f)", shownX, shownY)
        );

        GridPane gp = new GridPane();
        gp.add(startLabel, 0, 0);
        gp.add(startTF, 1, 0);
        gp.add(shownLabel, 0, 1);
        gp.add(shownTF, 1, 1);
        gp.setHgap(10);
        gp.setVgap(10);

        HBox hbox = new HBox(gp);
        hbox.setAlignment(CENTER);

        VBox vbox = new VBox(hbox);
        vbox.setAlignment(CENTER);

        Scene scene = new Scene(vbox, 480, 320);

        primaryStage.setScene(scene);

        // before show()...I just set this to 480x320, right?
        startX.set(primaryStage.getWidth());
        startY.set(primaryStage.getHeight());

        primaryStage.setOnShown((evt) -> {
            shownX.set(primaryStage.getWidth());
            shownY.set(primaryStage.getHeight());  // all available now
        });

        primaryStage.setTitle("Start Vs. Shown");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}