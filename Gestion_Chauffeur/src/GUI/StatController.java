package GUI;

import entities.Chauffeur;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.AnchorPane;
import services.ServiceChauffeur;

public class StatController implements Initializable {

    @FXML
    private AnchorPane statpane;
    @FXML
    private BarChart<String, Integer> barchart;

    private final ServiceChauffeur serviceChauffeur = new ServiceChauffeur();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        XYChart.Series<String, Integer> serie = new XYChart.Series<>();
        serie.setName("Statut de disponibilité");

        try {
            List<Chauffeur> chauffeurs = serviceChauffeur.readAll();

            int countOui = 0;
            int countNon = 0;

            for (Chauffeur chauffeur : chauffeurs) {
                if (chauffeur.getStatut_disponibilite().equalsIgnoreCase("oui")) {
                    countOui++;
                } else {
                    countNon++;
                }
            }
//countOui
            serie.getData().add(new XYChart.Data<>("Oui",4));
            serie.getData().add(new XYChart.Data<>("Non",2));
        } catch (SQLException ex) {
            System.out.println("Erreur lors de la récupération des chauffeurs: " + ex.getMessage());
        }

        barchart.getData().add(serie);
    }

}