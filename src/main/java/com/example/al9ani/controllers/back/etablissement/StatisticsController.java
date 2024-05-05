package com.example.al9ani.controllers.back.etablissement;

import com.example.al9ani.entities.Etablissement;
import com.example.al9ani.services.EtablissementService;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class StatisticsController implements Initializable {

    @FXML
    public PieChart mostUsedCategoriesPieChart;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Etablissement> listReservation = EtablissementService.getInstance().getAll();

        List<String> categories = new ArrayList<>();

        for (Etablissement etablissement : listReservation) {
            if (!categories.contains(etablissement.getCategorie().toLowerCase())) {
                categories.add(etablissement.getCategorie());
            }
        }

        List<PieChart.Data> pieChartData = new ArrayList<>();

        for (String category : categories) {
            int count = 0;
            for (Etablissement etablissement : listReservation) {
                if (etablissement.getCategorie().equalsIgnoreCase(category)) {
                    count++;
                }
            }
            pieChartData.add(new PieChart.Data(category, count));
        }

        mostUsedCategoriesPieChart.getData().addAll(pieChartData);
    }
}
