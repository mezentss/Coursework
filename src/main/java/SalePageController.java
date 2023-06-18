import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import javax.swing.*;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class SalePageController {
    public SalePageController(Sale sale){_sale = sale;}
    public SalePageController(){}
    private static Sale _sale;
    @FXML
    private TableColumn<Sale, Integer> ID, ServiceID, PartID;
    @FXML
    private TableView<Sale> Table;
    @FXML
    private Button MenuBotton, show;
    public TextField txt_ServiceID, txt_PartID, txt_ID, assembling, assemblingView;
    ObservableList <Sale> list;
    int index = -1;
    private Stage mainStage;
    PreparedStatement pst = null;
    public void setMainStage(Stage primaryStage) {mainStage = primaryStage;}
    public void initialize(){
        ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        ServiceID.setCellValueFactory(new PropertyValueFactory<>("ServiceID"));
        PartID.setCellValueFactory(new PropertyValueFactory<>("partID"));
        show.setOnAction(this::assemblingButton);

        list = SaleController.getSales();
        Table.setItems(list);

        MenuBotton.setOnAction(actionEvent -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("MainPage.fxml"));
                Parent root = loader.load();
                MainPageController controller = loader.getController();
                controller.setMainStage(mainStage);
                Scene scene = new Scene(root);
                mainStage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void getSelected(javafx.scene.input.MouseEvent mouseEvent){
        index = Table.getSelectionModel().getSelectedIndex();
        if (index <=-1){
            return;
        }
        txt_ID.setText(ID.getCellData(index).toString());
        txt_ServiceID.setText(ServiceID.getCellData(index).toString());
        txt_PartID.setText(PartID.getCellData(index).toString());

    }

    public void addSale() {
        int ID = Integer.parseInt(txt_ID.getText());
        int ServiceID = Integer.parseInt(txt_ServiceID.getText());
        int PartID = Integer.parseInt(txt_PartID.getText());
        Sale sale = new Sale(ID, ServiceID, PartID);
        SaleController.addSale(sale);
        list.add(sale);
        JOptionPane.showMessageDialog(null, "Сборка добавлена");
    }

    public void Edit(){
        int ID = Integer.parseInt(txt_ID.getText());
        int ServiceID = Integer.parseInt(txt_ServiceID.getText());
        int PartID = Integer.parseInt(txt_PartID.getText());
        Sale sale = new Sale(ID, ServiceID, PartID);
        SaleController.updateSale(sale);
        list.set(index, sale);
        JOptionPane.showMessageDialog(null, "Информация обновлена");
    }

    public void Delete(){
        int id = Integer.parseInt(txt_ID.getText());
        SaleController.deleteSale(id);
        list.remove(index);
        //clearFields();
        JOptionPane.showMessageDialog(null, "Информация удалена");
    }

    @FXML
    void assemblingButton(ActionEvent actionEvent) {
        try {
            String id = assembling.getText();
            String sql = "SELECT SUM(Price) " +
                    "FROM Sales " +
                    "JOIN Parts ON Sales.PartID = Parts.ID " +
                    "WHERE Sales.ServiceID = " + id;

            pst = _sale.getConnection().prepareStatement(sql);
            pst.execute();
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                String result = rs.getString(1);
                assemblingView.setText("Стоимость услуги: \n" + result );
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
