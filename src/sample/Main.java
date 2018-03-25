package sample;


import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application
{
    Stage window;
    Button button;
    TableView<Product> table;
    TextField nameInp, priceInp, quantityInp;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        window = primaryStage;
        window.setTitle("Login");
        window.setOnCloseRequest(event ->
        {
            event.consume();
            this.closeWindow();
        });

        TableColumn<Product, String>  nameCol = new TableColumn<>("Name");
        TableColumn<Product, Double>  priceCol = new TableColumn<>("Price");
        TableColumn<Product, Integer>  quantityCol = new TableColumn<>("Quantity");

        nameCol.setMinWidth(200);
        priceCol.setMinWidth(100);
        quantityCol.setMinWidth(100);

        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("price"));
        quantityCol.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        table = new TableView<>();
        table.setItems(createList());
        table.getColumns().addAll(nameCol,priceCol,quantityCol);

        nameInp = new TextField();
        nameInp.setPromptText("Name");
        nameInp.setMaxWidth(200);

        priceInp = new TextField();
        priceInp.setPromptText("Price");
        priceInp.setMaxWidth(80);

        quantityInp = new TextField();
        quantityInp.setPromptText("Quantity");
        quantityInp.setMaxWidth(80);

        Button addBtn = new Button("Add");
        Button delBtn = new Button("Delete");

        addBtn.setOnAction(event -> addButtonClicked());
        delBtn.setOnAction(event -> delButtonClicked());
        

        HBox hbox = new HBox(5);
        hbox.setPadding(new Insets(10,10,10,10));
        hbox.getChildren().addAll(nameInp,priceInp,quantityInp, addBtn, delBtn);

        VBox vbox = new VBox(10);
        vbox.getChildren().addAll(table);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(50,50,50,50));
        vbox.setMaxHeight(300);

        BorderPane layout = new BorderPane();
        layout.setTop(vbox);
        layout.setBottom(hbox);

        Scene scene = new Scene(layout,502,400);
        window.setScene(scene);
        window.show();
    }

    private void delButtonClicked()
    {
        ObservableList<Product> productSelected, allProducts;
        allProducts = table.getItems();
        productSelected = table.getSelectionModel().getSelectedItems();

        allProducts.removeAll(productSelected);
    }

    private void addButtonClicked()
    {
        String name = nameInp.getText();
        String price = priceInp.getText();
        String quantity = quantityInp.getText();

        if(name.length() > 5 && isDouble(price) && isInt(quantity))
        {
            Product product = new Product(name, Double.parseDouble(price), Integer.parseInt(quantity));
            table.getItems().add(product);
            nameInp.clear();
            priceInp.clear();
            quantityInp.clear();
        }
        else return;
    }

    private boolean isInt(String check)
    {
        try
        {
            Integer.parseInt(check);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    private boolean isDouble(String check)
    {
        try
        {
            Double.parseDouble(check);
        }
        catch(NumberFormatException e)
        {
            return false;
        }
        return true;
    }

    private void closeWindow()
    {
        boolean answer = Confirm.display("Confirm", "Are you sure you want to exit?");
        if(answer)
        {
            window.close();
        }
    }

    public ObservableList<Product> createList()
    {
        ObservableList<Product> products = FXCollections.observableArrayList();
        products.addAll(new Product("Margheritta", 15, 20),
                new Product("Pepperoni", 17, 20));
        return products;
    }

}
