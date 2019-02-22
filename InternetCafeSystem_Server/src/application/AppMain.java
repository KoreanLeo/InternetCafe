package application;
import controller.RootController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
public class AppMain extends Application{
	public static Stage stage;
	public static void main(String[] args) {
		launch(args);
	}
		//메인
	@Override
		public void start(Stage stage) throws Exception {
		Stage stage1=new Stage(StageStyle.DECORATED);
		this.stage =stage1;//(StageStyle.UNIFIED);
		try {
			FXMLLoader fxmlLoader=new FXMLLoader(getClass().getResource("../view/root.fxml"));
			Parent root =fxmlLoader.load();
			RootController rootController =fxmlLoader.getController();
			rootController.setStage(stage1);
			Scene scene=new Scene(root);
			stage1.setScene(scene);
			stage1.setX((0));
			stage1.setY(0);
			stage1.show();
			/*this.stage.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent
					eClose)->{if(eClose.getCode()==KeyCode.ESCAPE) this.stage.close();});*/
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

