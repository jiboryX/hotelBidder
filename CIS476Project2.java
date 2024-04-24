package cis476.project.pkg2;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


interface HotelRoom {
    String handleBid(int bid);
    void setNextRoom(HotelRoom nextRoom);
}
class SuiteHandler implements HotelRoom{
    int rooms = 10;
    private HotelRoom nextRoom;
    @Override
    public void setNextRoom(HotelRoom nextRoom){
        this.nextRoom = nextRoom;
    }
    @Override
    public String handleBid(int bid){
        if (bid > 279 && rooms != 0){
            rooms--;
            return "Suite";
            //System.out.println(rooms);
        }
        else {
            return nextRoom.handleBid(bid);
        }
    }
}
class  DeluxeHandler implements HotelRoom{
    int rooms = 15;
    private HotelRoom nextRoom;
    @Override
    public void setNextRoom(HotelRoom nextRoom){
        this.nextRoom = nextRoom;
    }
    @Override
    public String handleBid(int bid){
        if (bid > 149 && rooms != 0){
            rooms--;
            return "Deluxe";
            //System.out.println(rooms);
        }
        else {
            return nextRoom.handleBid(bid);
        }
    } 
}
class StandardHandler implements HotelRoom {
    int rooms = 45;
    
    private HotelRoom nextRoom;

    @Override
    public String handleBid(int bid) {
        if (bid > 79 && rooms != 0) {
            rooms--;
            return "Standard";
            //System.out.println(rooms);
        } 
        else  if (bid < 80){
            return "bid not accepted, try again";
            //System.out.print("no more rooms ");
        }
        else return "no more rooms";
    }

    @Override
    public void setNextRoom(HotelRoom nextRoom) {
        this.nextRoom = nextRoom;
    }
}

public class CIS476Project2 extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        
        HotelRoom suiteHandling = new SuiteHandler();
        HotelRoom deluxeHandling = new DeluxeHandler();
        HotelRoom standardHandling = new StandardHandler();

        suiteHandling.setNextRoom(deluxeHandling);
        deluxeHandling.setNextRoom(standardHandling);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML.fxml"));
        loader.setController(new FXMLController(suiteHandling, deluxeHandling, standardHandling));
        Parent root = loader.load();

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
