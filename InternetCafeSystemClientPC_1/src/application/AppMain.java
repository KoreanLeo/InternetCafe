package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class AppMain extends Application{
	public static Stage stage;
	public static void main(String[] args) {
		launch(args);
	}
	@Override

	public void start(Stage stage) throws Exception {
		this.stage=stage;
			try {
			FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../view/root.fxml"));
			Parent root =fxmlLoader.load();
			//RootController rootController =fxmlLoader.getController();
			//rootController.setStage(stage);
			Scene scene=new Scene(root);
			stage.setScene(scene);
			
			stage.show();
			}catch(Exception e) {
				callAlert("오류창:창을닫습니다");
			}
	}
	//알림창 함수
	public static void callAlert(String contentText) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("알림창");
			alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
			alert.setContentText(contentText.substring( contentText.lastIndexOf(":")+1));
			alert.showAndWait();
		}
}

