package com.jordanweschler.keyboard;

import com.jordanweschler.trie.Trie;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
import javafx.scene.control.Button;

public class Keyboard extends Application {

    /**
     * Some stiff size margins. In reality these should be done way better but I just needed something
     *  that would comfortably fit the screen and look decent while avoiding magic numbers
     */
    private final int SCENE_WIDTH = 420, SCENE_HEIGHT = 180;
    private final int PREDICTIONS_PANE_WIDTH = 380, PREDICTIONS_PANE_HEIGHT = 40;
    private final int TEXT_BOX_WIDTH = 380, TEXT_BOX_HEIGHT = 120;
    private final int PREDICTION_BUTTON_WIDTH = 126, PREDICTION_BUTTON_HEIGHT = 40;
    private final double INSET_V = 5, INSET_H = 20;
    private final String STAGE_NAME = "Predictive Keyboard";

    private Scene scene;
    private BorderPane mainPane;
    private FlowPane textPane;
    private HBox predictionsPane;

    private TextArea textBox;
    private Button prediction1, prediction2, prediction3;


    @Override
    public void start(Stage stage) {
        prediction1 = createButton(PREDICTION_BUTTON_WIDTH, PREDICTION_BUTTON_HEIGHT);
        prediction2 = createButton(PREDICTION_BUTTON_WIDTH, PREDICTION_BUTTON_HEIGHT);
        prediction3 = createButton(PREDICTION_BUTTON_WIDTH, PREDICTION_BUTTON_HEIGHT);

        predictionsPane = new HBox();
        predictionsPane.setPrefWidth(PREDICTIONS_PANE_WIDTH);
        predictionsPane.setPrefHeight(PREDICTIONS_PANE_HEIGHT);
        predictionsPane.getChildren().addAll(prediction1, prediction2, prediction3);

        textBox = new TextArea();
        textBox.setPrefWidth(TEXT_BOX_WIDTH);
        textBox.setPrefHeight(TEXT_BOX_HEIGHT);

        textPane = new FlowPane(textBox);

        mainPane = new BorderPane();
        mainPane.setPadding(new Insets(INSET_V, INSET_H, INSET_V, INSET_H));
        mainPane.setTop(predictionsPane);
        mainPane.setCenter(textPane);

        scene = new Scene(mainPane, SCENE_WIDTH, SCENE_HEIGHT);

        stage.setScene(scene);
        stage.setTitle(STAGE_NAME);
        stage.setResizable(false);

        stage.show();
    }

    private Button createButton(int prefWidth, int prefHeight) {
        Button button = new Button();
        button.setPrefWidth(prefWidth);
        button.setPrefHeight(prefHeight);

        return button;
    }


}