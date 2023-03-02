import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.*;
import java.util.ResourceBundle;

public class AffichageVoituresFrontOffice implements Initializable {

    @FXML
    private GridPane carGrid;

    @FXML
    private Button reserveButton;

    @FXML
    private Button detailsButton;

    @FXML
    private Text carName;

    @FXML
    private Text carDescription;

    @FXML
    private Text carPrice;

    @FXML
    private ImageView carImage;

    private Connection connection;

    private PreparedStatement preparedStatement;

    private ResultSet resultSet;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            // Connect to the database
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/carrental", "root", "");

            // Query to get all cars
            String query = "SELECT * FROM cars";

            // Prepare the query
            preparedStatement = connection.prepareStatement(query);

            // Execute the query and get the result set
            resultSet = preparedStatement.executeQuery();

            int row = 0;
            int col = 0;

            // Loop through the result set and create car items in the grid pane
            while (resultSet.next()) {

                // Get the car data from the result set
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String imageUrl = resultSet.getString("image");
                double price = resultSet.getDouble("price");

                // Create the car item components
                ImageView carImageView = new ImageView(new Image(imageUrl));
                carImageView.getStyleClass().add("car-image");
                Text carNameText = new Text(name);
                carNameText.getStyleClass().add("car-name");
                Text carDescriptionText = new Text(description);
                carDescriptionText.getStyleClass().add("car-description");
                Text carPriceText = new Text("$" + String.format("%.2f", price));
                carPriceText.getStyleClass().add("car-price");
                Button reserveButton = new Button("Reserve");
                reserveButton.getStyleClass().add("reserve-button");
                Button detailsButton = new Button("Details");
                detailsButton.getStyleClass().add("details-button");

                // Add the car item components to the grid pane
                carGrid.add(carImageView, col, row);
                carGrid.add(carNameText, col, row + 1);
                carGrid.add(carDescriptionText, col, row + 2);
                carGrid.add(carPriceText, col, row + 3);
                carGrid.add(reserveButton, col, row + 4);
                carGrid.add(detailsButton, col + 1, row + 4);

                // Increment the column counter
                col += 2;

                // If the last column is reached, move to the next row
                if (col >= 6) {
                    col = 0;
                    row += 5;
                }

                // Set the button actions
                reserveButton.setOnAction(event -> {
                    System.out.println("Reserve button clicked for car id: " + id);
                });

                detailsButton.setOnAction(event -> {
                    carName.setText(name);
                    carDescription.setText(description);
                    carPrice.setText("$" + String.format("%.2f", price));
                   
                carImage.setImage(new Image(imageUrl));
            });
        }

    } catch (SQLException e) {
        System.out.println("Error connecting to database: " + e.getMessage());
    }
}

public void close() {
    // Close the result set, prepared statement, and database connection
    try {
        if (resultSet != null) {
            resultSet.close();
        }
        if (preparedStatement != null) {
            preparedStatement.close();
        }
        if (connection != null) {
            connection.close();
        }
    } catch (SQLException e) {
        System.out.println("Error closing database connection: " + e.getMessage());
    }
}
}