package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Confirm
{
    static boolean answer;

    public static boolean display(String title, String message)
    {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setMinWidth(200);
        window.setMinHeight(100);

        Label label1 = new Label(message);

        Button yesBtn = new Button("Yes");
        Button noBtn = new Button("No");

        yesBtn.setOnAction(event ->
        {
            answer = true;
            window.close();
        });

        noBtn.setOnAction(event ->
        {
            answer = false;
            window.close();

        });

        VBox layout = new VBox(15);
        layout.getChildren().addAll(label1, yesBtn, noBtn);
        layout.setAlignment(Pos.CENTER);
        layout.setPadding(new Insets(10,10,20,10));

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
