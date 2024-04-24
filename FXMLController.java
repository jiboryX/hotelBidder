package cis476.project.pkg2;


import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class FXMLController implements Initializable {
    private final HotelRoom suiteHandling;
    private final HotelRoom deluxeHandling;
    private final HotelRoom standardHandling;
    
    public FXMLController(HotelRoom suiteHandling, HotelRoom deluxeHandling, HotelRoom standardHandling){
        this.suiteHandling = suiteHandling;
        this.deluxeHandling = deluxeHandling;
        this.standardHandling = standardHandling;
    }
    
    

    @FXML
    private Button bidButton;
    @FXML
    private TextField insertAmt;
    @FXML
    private TextField suiteAmt;
    @FXML
    private TextField DeluxeAmt;
    @FXML
    private TextField StandardAmt;
    @FXML
    private Label congrats;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        suiteAmt.setText(Integer.toString(((SuiteHandler) suiteHandling).rooms));
        DeluxeAmt.setText(Integer.toString(((DeluxeHandler) deluxeHandling).rooms));
        StandardAmt.setText(Integer.toString(((StandardHandler) standardHandling).rooms));
    }    

    @FXML
    private void bidButtonHandler(ActionEvent event) {
        String bidStr = insertAmt.getText();
        
        if (!bidStr.isEmpty()){
            int bid = Integer.parseInt(bidStr);
            String type = suiteHandling.handleBid(bid);
            
            //updating rooms
            suiteAmt.setText(Integer.toString(((SuiteHandler) suiteHandling).rooms));
            DeluxeAmt.setText(Integer.toString(((DeluxeHandler) deluxeHandling).rooms));
            StandardAmt.setText(Integer.toString(((StandardHandler) standardHandling).rooms));
                   
            if (type != "Deluxe" && type != "Suite" && type != "Standard")
                congrats.setText(type);
            else 
                congrats.setText("Purchased " + type + " room.");

        }
            else {
            congrats.setText("error"); 
        }
        
    }
    
}
