package controller;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Vector;

import application.AppMain;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Finalorder2;
import model.Member;
public class RootController implements Initializable { 
	//메인스레드
	private Thread mainThread;
	//서버 
	private ServerSocket serverSocket;
	Thread threadS1;Thread threadR1;
	Thread threadS2;Thread threadR2;
	Thread threadS3;Thread threadR3;
	Thread threadS4;Thread threadR4;
	Thread threadS5;Thread threadR5;
	Thread threadS6;Thread threadR6;
	Thread threadS7;Thread threadR7;
	Thread threadS8;Thread threadR8;
	Thread threadS9;Thread threadR9;
	Thread threadS10;Thread threadR10;
	Thread threadS11;Thread threadR11;
	Thread threadS12;Thread threadR12;
	Thread threadS13;Thread threadR13;
	Thread threadS14;Thread threadR14;
	Thread threadS15;Thread threadR15;
	Thread threadS16;Thread threadR16;
	//클라이언트
	Client1 client;
	Client2 client2;
	Client3 client3;
	Client4 client4;
	Client5 client5;
	Client6 client6;
	Client7 client7;
	Client8 client8;
	Client9 client9;
	Client10 client10;
	Client11 client11;
	Client12 client12;
	Client13 client13;
	Client14 client14;
	Client15 client15;
	Client16 client16;
	//자리 1 메세지창 세팅
	Button measage1Send;Button measage1Cancel;TextField measage1Field;TextArea measage1Area;
	//자리2 메세지창 세팅
	Button measage2Send;Button measage2Cancel;TextField measage2Field;TextArea measage2Area;
	//자리3 메세지장 세팅
	Button measage3Send;Button measage3Cancel;TextField measage3Field;TextArea measage3Area;
	//자리4 메세지장 세팅
	Button measage4Send;Button measage4Cancel;TextField measage4Field;TextArea measage4Area;
	//자리5 메세지장 세팅
	Button measage5Send;Button measage5Cancel;TextField measage5Field;TextArea measage5Area;
	//자리6 메세지장 세팅
	Button measage6Send;Button measage6Cancel;TextField measage6Field;TextArea measage6Area;
	//자리7 메세지장 세팅
	Button measage7Send;Button measage7Cancel;TextField measage7Field;TextArea measage7Area;
	//자리8 메세지장 세팅
	Button measage8Send;Button measage8Cancel;TextField measage8Field;TextArea measage8Area;
	//자리9 메세지장 세팅
	Button measage9Send;Button measage9Cancel;TextField measage9Field;TextArea measage9Area;
	//자리10 메세지장 세팅
	Button measage10Send;Button measage10Cancel;TextField measage10Field;TextArea measage10Area;
	//자리11 메세지장 세팅
	Button measage11Send;Button measage11Cancel;TextField measage11Field;TextArea measage11Area;
	//자리12 메세지장 세팅
	Button measage12Send;Button measage12Cancel;TextField measage12Field;TextArea measage12Area;
	//자리13 메세지장 세팅
	Button measage13Send;Button measage13Cancel;TextField measage13Field;TextArea measage13Area;
	//자리14 메세지장 세팅
	Button measage14Send;Button measage14Cancel;TextField measage14Field;TextArea measage14Area;
	//자리15 메세지장 세팅
	Button measage15Send;Button measage15Cancel;TextField measage15Field;TextArea measage15Area;
	//자리16 메세지장 세팅
	Button measage16Send;Button measage16Cancel;TextField measage16Field;TextArea measage16Area;
	boolean MeasageClick1[]=new boolean[16];//메세지창 불리언값
	String a1;//아이피 확인 스트링
	//클라이언트 리스트
	List<Client1> list1 = new Vector<Client1>();
	List<Client2> list2 = new Vector<Client2>();
	List<Client3> list3 = new Vector<Client3>();
	List<Client4> list4 = new Vector<Client4>();
	List<Client5> list5 = new Vector<Client5>();
	List<Client6> list6 = new Vector<Client6>();
	List<Client7> list7 = new Vector<Client7>();
	List<Client8> list8 = new Vector<Client8>();
	List<Client9> list9 = new Vector<Client9>();
	List<Client10> list10 = new Vector<Client10>();
	List<Client11> list11 = new Vector<Client11>();
	List<Client12> list12 = new Vector<Client12>();
	List<Client13> list13 = new Vector<Client13>();
	List<Client14> list14 = new Vector<Client14>();
	List<Client15> list15 = new Vector<Client15>();
	List<Client16> list16 = new Vector<Client16>();
	//로그아웃창
	@FXML private ImageView t1Logout1;
	@FXML private ImageView t1Logout2;
	@FXML private ImageView t1Logout3;
	@FXML private ImageView t1Logout4;
	@FXML private ImageView t1Logout5;
	@FXML private ImageView t1Logout6;
	@FXML private ImageView t1Logout7;
	@FXML private ImageView t1Logout8;
	@FXML private ImageView t1Logout9;
	@FXML private ImageView t1Logout10;
	@FXML private ImageView t1Logout11;
	@FXML private ImageView t1Logout12;
	@FXML private ImageView t1Logout13;
	@FXML private ImageView t1Logout14;
	@FXML private ImageView t1Logout15;
	@FXML private ImageView t1Logout16;
	//로그인창
	@FXML private ImageView t1Logon1;
	@FXML private ImageView t1Logon2;
	@FXML private ImageView t1Logon3;
	@FXML private ImageView t1Logon4;
	@FXML private ImageView t1Logon5;
	@FXML private ImageView t1Logon6;
	@FXML private ImageView t1Logon7;
	@FXML private ImageView t1Logon8;
	@FXML private ImageView t1Logon9;
	@FXML private ImageView t1Logon10;
	@FXML private ImageView t1Logon11;
	@FXML private ImageView t1Logon12;
	@FXML private ImageView t1Logon13;
	@FXML private ImageView t1Logon14;
	@FXML private ImageView t1Logon15;
	@FXML private ImageView t1Logon16;
	////////////////////////////테이블 컬럼 FXML
	private ObservableList<Member> t2TableViewList = FXCollections.observableArrayList();//회원관리 리스트
	private ObservableList<Finalorder2> t1finalorder2TableViewList = FXCollections.observableArrayList();//회원관리 리스트
	private ObservableList<Finalorder2> t1sales2TableViewList = FXCollections.observableArrayList();//매출관리 리스트
	private ObservableList<String> salesComboxList = FXCollections.observableArrayList();//매출관리 리스트
	public static ArrayList<Finalorder2> db1FinalorderList2= new ArrayList<>(); 
	public static ArrayList<Finalorder2> db1salesList= new ArrayList<>();
	private ArrayList<Member> dbt2TableViewList = new ArrayList<>();
	private ArrayList<Finalorder2> dbtFinalorder2TableViewList = new ArrayList<>();
	@FXML private TableView<Member> t2TableView; //t2테이블뷰 
	@FXML private TableColumn<Member, String>t2name;;
	@FXML private TableColumn<Member, String>t2Id;;
	@FXML private TableColumn<Member, String>t2IPassword;;
	@FXML private TableColumn<Member, String>t2Gender;;
	@FXML private TableColumn<Member, String>t2age;;
	@FXML private TableColumn<Member, String>t2PhoneNumber;;
	@FXML private TableColumn<Member, String>t2Email;;
	@FXML private TableColumn<Member, String>t2Date;;
	//회원정보 검색 수정 삭제 새로고침
	private ObservableList<String> membercombobox = FXCollections.observableArrayList();//회원관리 리스트
	@FXML private Button t2SearchButton;
	@FXML private TextField t2SearchTxt;
	@FXML private ComboBox<String> t2SearchCombo;
	@FXML private Button t2SUpdateButton;
	@FXML private Button t2SDeleteButton;
	@FXML private Button t2NewSence;
	//매출관리
	private  ComboBox<String> salesCombo;
	private Label slaesTotal;
	String moneyValue;
	boolean salesComboBoolean=false;
	TableView<Finalorder2>t1orderTableView;
	//프로그램종료버튼
	@FXML private  Button programExit;
	//Memo장에사용
	static TextArea  t1Memo;
	String memo;
	Scanner s;
	BufferedWriter bufferedWriter;
	@FXML private Button t1BtnMemo;
	//주문현황
	@FXML private Button t1BtnOrder;
	//관리자모드
	@FXML private Button t1BtnManager;
	//로그인창
	private ImageView mlsAuto;
	private Button mlslog1;
	private Button mlslog2;
	private TextField mlsId;
	private TextField mlsPassword;
	private Button mlsExit;
	//스테이지
	private Stage stage;
	private Stage memoStage;
	private Stage measage1;
	private Stage measage2;
	private Stage measage3;
	private Stage measage4;
	private Stage measage5;
	private Stage measage6;
	private Stage measage7;
	private Stage measage8;
	private Stage measage9;
	private Stage measage10;
	private Stage measage11;
	private Stage measage12;
	private Stage measage13;
	private Stage measage14;
	private Stage measage15;
	private Stage measage16;
	private Stage stockmanagement;
	//회원 정보 수정창
	private Button updateBtn;
	private Button updateCance;
	private TextField updatetName;
	private TextField updateID;
	private TextField updatePassword;
	private TextField updateAge;
	private TextField updatePhoneNumber;
	private TextField updateE_mail;
	private Member member;
	private int memberIndex;
//============================================================================
	//이니셜라이즈
	public void initialize(URL arg0, ResourceBundle arg1) {
		//버튼색깔 세팅
		//주문현황세팅
		orderSetting();
		//서버
		serverControl();
		//로그인조절
		loinControl();
		//작업사항(메모장)
		t1BtnMemo.setOnAction(e->{try {memoControl();}catch(Exception e1) {}});
		//주문현현황
		t1BtnOrder.setOnAction(e->{t1BtnOrderButton();});
		//관리자실행
		t1BtnManager.setOnAction(e->{t1BtnManager();});
		//프로그램종료
		programExit.setOnAction(e->{
			AppMain.stage.close();
		 System.exit(0);});
		 t2TableviewSet();
		 t2TableView.setOnMouseClicked(e->{
				if(e.getClickCount()==2) {
					
				}
		 });
		 //회원관리 검색 수정 삭제 새로고침
		 membercontrol();
		 //회원검색 콤보박스 세팅
		 memberComboboxSeting();
}
	//주문창 버튼 색깔
	private void setcolor() {
		t1BtnOrder.setStyle("-fx-background-color: #FF0000");
	
}
	//스테이지set
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	//자리 스테이지화면
	private  void  Clientmesage1() {
		measage1=new Stage(StageStyle.UTILITY);
		measage1.setTitle("1번자리");
		measage1.initModality(Modality.NONE);
		measage1.initOwner(stage);
		Parent rootMesage1 =null;
		try{
		}catch(Exception e1) {}
		measage1Send=(Button)rootMesage1.lookup("#measage1Send");
		measage1Cancel=(Button)rootMesage1.lookup("#measage1Cancel");
		measage1Field=(TextField)rootMesage1.lookup("#measage1Field");
		measage1Area=(TextArea)rootMesage1.lookup("#measage1Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage1.setScene(scenerootMesage1);
		measage1.show();
		measage1Send.setOnAction(e->{
			client.send();
			measage1Area.appendText("카운터:"+measage1Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage1Field.setText(null);
		});
		measage1Area.setEditable(false);
		measage1Cancel.setOnAction(e->{
			measage1.close();
			MeasageClick1[0]=false;
		});
		measage1Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client.send();
					measage1Area.appendText("카운터:"+measage1Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage1Field.setText(null);
					MeasageClick1[0]=false;
				}
			}

		});
}
	private  void  Clientmesage2() {
		measage2=new Stage(StageStyle.UTILITY);
		measage2.setTitle("2번자리");
		measage2.initModality(Modality.NONE);
		measage2.initOwner(stage);
		Parent rootMesage1 =null;
		try{
			rootMesage1=FXMLLoader.load(getClass().getResource("../view/measage2.fxml"));
		}catch(Exception e1) {}
		measage2Send=(Button)rootMesage1.lookup("#measage2Send");
		measage2Cancel=(Button)rootMesage1.lookup("#measage2Cancel");
		measage2Field=(TextField)rootMesage1.lookup("#measage2Field");
		measage2Area=(TextArea)rootMesage1.lookup("#measage2Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage2.setScene(scenerootMesage1);
		measage2.show();
		measage2Send.setOnAction(e->{
			client2.send();
			measage2Area.appendText("카운터:"+measage2Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage2Field.setText(null);
		});
		measage2Area.setEditable(false);
		measage2Cancel.setOnAction(e->{
			measage2.close();
			MeasageClick1[1]=false;
		});
		measage2Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client2.send();
					measage2Area.appendText("카운터:"+measage2Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage2Field.setText(null);
				}
			}

		});
	}
	private  void  Clientmesage3() {
		measage3=new Stage(StageStyle.UTILITY);
		measage3.setTitle("3번자리");
		measage3.initModality(Modality.NONE);
		measage3.initOwner(stage);
		Parent rootMesage1 =null;
		try{
			rootMesage1=FXMLLoader.load(getClass().getResource("../view/measage3.fxml"));
		}catch(Exception e1) {}
		measage3Send=(Button)rootMesage1.lookup("#measage3Send");
		measage3Cancel=(Button)rootMesage1.lookup("#measage3Cancel");
		measage3Field=(TextField)rootMesage1.lookup("#measage3Field");
		measage3Area=(TextArea)rootMesage1.lookup("#measage3Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage3.setScene(scenerootMesage1);
		measage3.show();
		measage3Send.setOnAction(e->{
			client3.send();
			measage3Area.appendText("카운터:"+measage3Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage3Field.setText(null);
		});
		measage3Area.setEditable(false);
		measage3Cancel.setOnAction(e->{
			measage3.close();
			MeasageClick1[2]=false;
		});
		measage3Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client3.send();
					measage3Area.appendText("카운터:"+measage3Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage3Field.setText(null);
				}
			}

		});
	}
	private  void  Clientmesage4() {
		measage4=new Stage(StageStyle.UTILITY);
		measage4.setTitle("4번자리");
		measage4.initModality(Modality.NONE);
		measage4.initOwner(stage);
		Parent rootMesage1 =null;
		try{
			rootMesage1=FXMLLoader.load(getClass().getResource("../view/measage4.fxml"));
		}catch(Exception e1) {}
		measage4Send=(Button)rootMesage1.lookup("#measage4Send");
		measage4Cancel=(Button)rootMesage1.lookup("#measage4Cancel");
		measage4Field=(TextField)rootMesage1.lookup("#measage4Field");
		measage4Area=(TextArea)rootMesage1.lookup("#measage4Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage4.setScene(scenerootMesage1);
		measage4.show();
		measage4Send.setOnAction(e->{
			client4.send();
			measage4Area.appendText("카운터:"+measage4Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage4Field.setText(null);
		});
		measage4Area.setEditable(false);
		measage4Cancel.setOnAction(e->{
			measage4.close();
			MeasageClick1[3]=false;
		});
		measage4Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client4.send();
					measage4Area.appendText("카운터:"+measage4Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage4Field.setText(null);
				}
			}

		});
	}
	private  void  Clientmesage5() {
		measage5=new Stage(StageStyle.UTILITY);
		measage5.setTitle("5번자리");
		measage5.initModality(Modality.NONE);
		measage5.initOwner(stage);
		Parent rootMesage1 =null;
		try{
			rootMesage1=FXMLLoader.load(getClass().getResource("../view/measage5.fxml"));
		}catch(Exception e1) {}
		measage5Send=(Button)rootMesage1.lookup("#measage5Send");
		measage5Cancel=(Button)rootMesage1.lookup("#measage5Cancel");
		measage5Field=(TextField)rootMesage1.lookup("#measage5Field");
		measage5Area=(TextArea)rootMesage1.lookup("#measage5Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage5.setScene(scenerootMesage1);
		measage5.show();
		measage5Send.setOnAction(e->{
			client5.send();
			measage5Area.appendText("카운터:"+measage5Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage5Field.setText(null);
		});
		measage5Area.setEditable(false);
		measage5Cancel.setOnAction(e->{
			measage5.close();
			MeasageClick1[4]=false;
		});
		measage5Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client5.send();
					measage5Area.appendText("카운터:"+measage5Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage5Field.setText(null);
				}
			}

		});
	}
	private  void  Clientmesage6() {
		measage6=new Stage(StageStyle.UTILITY);
		measage6.setTitle("6번자리");
		measage6.initModality(Modality.NONE);
		measage6.initOwner(stage);
		Parent rootMesage1 =null;
		try{
			rootMesage1=FXMLLoader.load(getClass().getResource("../view/measage6.fxml"));
		}catch(Exception e1) {}
		measage6Send=(Button)rootMesage1.lookup("#measage6Send");
		measage6Cancel=(Button)rootMesage1.lookup("#measage6Cancel");
		measage6Field=(TextField)rootMesage1.lookup("#measage6Field");
		measage6Area=(TextArea)rootMesage1.lookup("#measage6Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage6.setScene(scenerootMesage1);
		measage6.show();
		measage6Send.setOnAction(e->{
			client6.send();
			measage6Area.appendText("카운터:"+measage6Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage6Field.setText(null);
		});
		measage6Area.setEditable(false);
		measage6Cancel.setOnAction(e->{
			measage6.close();
			MeasageClick1[5]=false;
		});
		measage6Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client6.send();
					measage6Area.appendText("카운터:"+measage6Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage6Field.setText(null);
				}
			}

		});
	}
	private  void  Clientmesage7() {
		measage7=new Stage(StageStyle.UTILITY);
		measage7.setTitle("7번자리");
		measage7.initModality(Modality.NONE);
		measage7.initOwner(stage);
		Parent rootMesage1 =null;
		try{
			rootMesage1=FXMLLoader.load(getClass().getResource("../view/measage7.fxml"));
		}catch(Exception e1) {}
		measage7Send=(Button)rootMesage1.lookup("#measage7Send");
		measage7Cancel=(Button)rootMesage1.lookup("#measage7Cancel");
		measage7Field=(TextField)rootMesage1.lookup("#measage7Field");
		measage7Area=(TextArea)rootMesage1.lookup("#measage7Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage7.setScene(scenerootMesage1);
		measage7.show();
		measage7Send.setOnAction(e->{
			client7.send();
			measage7Area.appendText("카운터:"+measage7Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage7Field.setText(null);
		});
		measage7Area.setEditable(false);
		measage7Cancel.setOnAction(e->{
			measage7.close();
			MeasageClick1[6]=false;
		});
		measage7Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client7.send();
					measage7Area.appendText("카운터:"+measage7Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage7Field.setText(null);
				}
			}

		});
	}
	private  void  Clientmesage8() {
		measage8=new Stage(StageStyle.UTILITY);
		measage8.setTitle("8번자리");
		measage8.initModality(Modality.NONE);
		measage8.initOwner(stage);
		Parent rootMesage1 =null;
		try{
			rootMesage1=FXMLLoader.load(getClass().getResource("../view/measage8.fxml"));
		}catch(Exception e1) {}
		measage8Send=(Button)rootMesage1.lookup("#measage8Send");
		measage8Cancel=(Button)rootMesage1.lookup("#measage8Cancel");
		measage8Field=(TextField)rootMesage1.lookup("#measage8Field");
		measage8Area=(TextArea)rootMesage1.lookup("#measage8Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage8.setScene(scenerootMesage1);
		measage8.show();
		measage8Send.setOnAction(e->{
			client8.send();
			measage8Area.appendText("카운터:"+measage8Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage8Field.setText(null);
		});
		measage8Area.setEditable(false);
		measage8Cancel.setOnAction(e->{
			measage8.close();
			MeasageClick1[7]=false;
		});
		measage8Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client8.send();
					measage8Area.appendText("카운터:"+measage8Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage8Field.setText(null);
				}
			}

		});
	}
	private  void  Clientmesage9() {
		measage9=new Stage(StageStyle.UTILITY);
		measage9.setTitle("9번자리");
		measage9.initModality(Modality.NONE);
		measage9.initOwner(stage);
		Parent rootMesage1 =null;
		try{
			rootMesage1=FXMLLoader.load(getClass().getResource("../view/measage9.fxml"));
		}catch(Exception e1) {}
		measage9Send=(Button)rootMesage1.lookup("#measage9Send");
		measage9Cancel=(Button)rootMesage1.lookup("#measage9Cancel");
		measage9Field=(TextField)rootMesage1.lookup("#measage9Field");
		measage9Area=(TextArea)rootMesage1.lookup("#measage9Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage9.setScene(scenerootMesage1);
		measage9.show();
		measage9Send.setOnAction(e->{
			client9.send();
			measage9Area.appendText("카운터:"+measage9Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage9Field.setText(null);
		});
		measage9Area.setEditable(false);
		measage9Cancel.setOnAction(e->{
			measage9.close();
			MeasageClick1[8]=false;
		});
		measage9Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client9.send();
					measage9Area.appendText("카운터:"+measage9Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage9Field.setText(null);
				}
			}

		});
	}
	private  void  Clientmesage10() {
		measage10=new Stage(StageStyle.UTILITY);
		measage10.setTitle("10번자리");
		measage10.initModality(Modality.NONE);
		measage10.initOwner(stage);
		Parent rootMesage1 =null;
		try{
			rootMesage1=FXMLLoader.load(getClass().getResource("../view/measage10.fxml"));
		}catch(Exception e1) {}
		measage10Send=(Button)rootMesage1.lookup("#measage10Send");
		measage10Cancel=(Button)rootMesage1.lookup("#measage10Cancel");
		measage10Field=(TextField)rootMesage1.lookup("#measage10Field");
		measage10Area=(TextArea)rootMesage1.lookup("#measage10Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage10.setScene(scenerootMesage1);
		measage10.show();
		measage10Send.setOnAction(e->{
			client10.send();
			measage10Area.appendText("카운터:"+measage10Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage10Field.setText(null);
		});
		measage10Area.setEditable(false);
		measage10Cancel.setOnAction(e->{
			measage10.close();
			MeasageClick1[9]=false;
		});
		measage10Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client10.send();
					measage10Area.appendText("카운터:"+measage10Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage10Field.setText(null);
				}
			}

		});
	}
	private  void  Clientmesage11() {
		measage11=new Stage(StageStyle.UTILITY);
		measage11.setTitle("11번자리");
		measage11.initModality(Modality.NONE);
		measage11.initOwner(stage);
		Parent rootMesage1 =null;
		try{
			rootMesage1=FXMLLoader.load(getClass().getResource("../view/measage11.fxml"));
		}catch(Exception e1) {}
		measage11Send=(Button)rootMesage1.lookup("#measage11Send");
		measage11Cancel=(Button)rootMesage1.lookup("#measage11Cancel");
		measage11Field=(TextField)rootMesage1.lookup("#measage11Field");
		measage11Area=(TextArea)rootMesage1.lookup("#measage11Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage11.setScene(scenerootMesage1);
		measage11.show();
		measage11Send.setOnAction(e->{
			client11.send();
			measage11Area.appendText("카운터:"+measage11Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage11Field.setText(null);
		});
		measage11Area.setEditable(false);
		measage11Cancel.setOnAction(e->{
			measage11.close();
			MeasageClick1[10]=false;
		});
		measage11Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client11.send();
					measage11Area.appendText("카운터:"+measage11Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage11Field.setText(null);
				}
			}

		});
	}
	private  void  Clientmesage12() {
		measage12=new Stage(StageStyle.UTILITY);
		measage12.setTitle("12번자리");
		measage12.initModality(Modality.NONE);
		measage12.initOwner(stage);
		Parent rootMesage1 =null;
		try{
			rootMesage1=FXMLLoader.load(getClass().getResource("../view/measage12.fxml"));
		}catch(Exception e1) {}
		measage12Send=(Button)rootMesage1.lookup("#measage12Send");
		measage12Cancel=(Button)rootMesage1.lookup("#measage12Cancel");
		measage12Field=(TextField)rootMesage1.lookup("#measage12Field");
		measage12Area=(TextArea)rootMesage1.lookup("#measage12Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage12.setScene(scenerootMesage1);
		measage12.show();
		measage12Send.setOnAction(e->{
			client12.send();
			measage12Area.appendText("카운터:"+measage12Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage12Field.setText(null);
		});
		measage12Area.setEditable(false);
		measage12Cancel.setOnAction(e->{
			measage12.close();
			MeasageClick1[11]=false;
		});
		measage12Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client12.send();
					measage12Area.appendText("카운터:"+measage12Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage12Field.setText(null);
				}
			}

		});
	}
	private  void  Clientmesage13() {
		measage13=new Stage(StageStyle.UTILITY);
		measage13.setTitle("13번자리");
		measage13.initModality(Modality.NONE);
		measage13.initOwner(stage);
		Parent rootMesage1 =null;
		try{
			rootMesage1=FXMLLoader.load(getClass().getResource("../view/measage13.fxml"));
		}catch(Exception e1) {}
		measage13Send=(Button)rootMesage1.lookup("#measage13Send");
		measage13Cancel=(Button)rootMesage1.lookup("#measage13Cancel");
		measage13Field=(TextField)rootMesage1.lookup("#measage13Field");
		measage13Area=(TextArea)rootMesage1.lookup("#measage13Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage13.setScene(scenerootMesage1);
		measage13.show();
		measage13Send.setOnAction(e->{
			client13.send();
			measage13Area.appendText("카운터:"+measage13Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage13Field.setText(null);
		});
		measage13Area.setEditable(false);
		measage13Cancel.setOnAction(e->{
			measage13.close();
			MeasageClick1[12]=false;
		});
		measage13Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client13.send();
					measage13Area.appendText("카운터:"+measage13Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage13Field.setText(null);
				}
			}

		});
	}
	private  void  Clientmesage14() {
		measage14=new Stage(StageStyle.UTILITY);
		measage14.setTitle("14번자리");
		measage14.initModality(Modality.NONE);
		measage14.initOwner(stage);
		Parent rootMesage1 =null;
		try{
			rootMesage1=FXMLLoader.load(getClass().getResource("../view/measage14.fxml"));
		}catch(Exception e1) {}
		measage14Send=(Button)rootMesage1.lookup("#measage14Send");
		measage14Cancel=(Button)rootMesage1.lookup("#measage14Cancel");
		measage14Field=(TextField)rootMesage1.lookup("#measage14Field");
		measage14Area=(TextArea)rootMesage1.lookup("#measage14Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage14.setScene(scenerootMesage1);
		measage14.show();
		measage14Send.setOnAction(e->{
			client14.send();
			measage14Area.appendText("카운터:"+measage14Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage14Field.setText(null);
		});
		measage14Area.setEditable(false);
		measage14Cancel.setOnAction(e->{
			measage14.close();
			MeasageClick1[13]=false;
		});
		measage14Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client14.send();
					measage14Area.appendText("카운터:"+measage14Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage14Field.setText(null);
				}
			}

		});
	}
	private  void  Clientmesage15() {
		measage15=new Stage(StageStyle.UTILITY);
		measage15.setTitle("15번자리");
		measage15.initModality(Modality.NONE);
		measage15.initOwner(stage);
		Parent rootMesage1 =null;
		try{
			rootMesage1=FXMLLoader.load(getClass().getResource("../view/measage15.fxml"));
		}catch(Exception e1) {}
		measage15Send=(Button)rootMesage1.lookup("#measage15Send");
		measage15Cancel=(Button)rootMesage1.lookup("#measage15Cancel");
		measage15Field=(TextField)rootMesage1.lookup("#measage15Field");
		measage15Area=(TextArea)rootMesage1.lookup("#measage15Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage15.setScene(scenerootMesage1);
		measage15.show();
		measage15Send.setOnAction(e->{
			client15.send();
			measage15Area.appendText("카운터:"+measage15Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage15Field.setText(null);
		});
		measage15Area.setEditable(false);
		measage15Cancel.setOnAction(e->{
			measage15.close();
			MeasageClick1[14]=false;
		});
		measage15Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client15.send();
					measage15Area.appendText("카운터:"+measage15Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage15Field.setText(null);
				}
			}

		});
	}
	private  void  Clientmesage16() {
		measage16=new Stage(StageStyle.UTILITY);
		measage16.setTitle("16번자리");
		measage16.initModality(Modality.NONE);
		measage16.initOwner(stage);
		Parent rootMesage1 =null;
		try{
			rootMesage1=FXMLLoader.load(getClass().getResource("../view/measage16.fxml"));
		}catch(Exception e1) {}
		measage16Send=(Button)rootMesage1.lookup("#measage16Send");
		measage16Cancel=(Button)rootMesage1.lookup("#measage16Cancel");
		measage16Field=(TextField)rootMesage1.lookup("#measage16Field");
		measage16Area=(TextArea)rootMesage1.lookup("#measage16Area");
		Scene scenerootMesage1 = new Scene(rootMesage1);
		measage16.setScene(scenerootMesage1);
		measage16.show();
		measage16Send.setOnAction(e->{
			client16.send();
			measage16Area.appendText("카운터:"+measage16Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage16Field.setText(null);
		});
		measage16Area.setEditable(false);
		measage16Cancel.setOnAction(e->{
			measage16.close();
			MeasageClick1[15]=false;
		});
		measage16Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					client16.send();
					measage16Area.appendText("카운터:"+measage16Field.getText()+"\n");
					try {
						Thread.sleep(10);
					} catch (InterruptedException e1) {}
					measage16Field.setText(null);
				}
			}

		});
	}
	//////////////////////서버관리
	//서버열기
	private void serverControl() {
		try {
			serverSocket =new ServerSocket();
			serverSocket.bind(new InetSocketAddress("192.168.123", 5000)); //교수님 192.168.0.230 //내아이피 192.168.0.181
			acceptClient();
		} catch (IOException e) {
			callAlert("서버접속오류: 서버에 접속할 수 없습니다.");
			stopServer();
			AppMain.stage.close();
			}
	}
	//클라이언트 받기(서버관리실)
	private void acceptClient() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Socket socket = serverSocket.accept();
						String a=socket.getRemoteSocketAddress().toString();
						a1=a.substring(1, a.lastIndexOf(":"));
						if(a1.equals("192.168.123")) {//1번 교수님 192.168.0.230 //내아이피 192.168.0.181
							Platform.runLater(()->{
									Clientmesage1();
									measage1.close();
				            	   t1Logon1.setVisible(true);
				                });
							client= new Client1(socket);
							list1.add(client);
						}else if(a1.equals("192.168.0.209")) {//2번자리 혜지누나 192.168.0.209
							Platform.runLater(()->{
								Clientmesage2();
								measage2.close();
				            	   t1Logon2.setVisible(true);
				                });
							client2= new Client2(socket);
							list2.add(client2);
						}else if(a1.equals("192.168.0.194")) {//3번자리 승국이형자리
							Platform.runLater(()->{
								Clientmesage3();
								measage3.close();
				            	   t1Logon3.setVisible(true);
				                });
							client3= new Client3(socket);
							list3.add(client3);
						}else if(a1.equals("192.168.0.188")) {//4번승현 192.168.0.188
							Platform.runLater(()->{
								Clientmesage4();
								measage4.close();
				            	   t1Logon4.setVisible(true);
				                });
							client4= new Client4(socket);
							list4.add(client4);
						}else if(a1.equals("192.168.0.195")) {//5번자리 경덕192.168.0.195
							Platform.runLater(()->{
								Clientmesage5();
								measage5.close();
				            	   t1Logon5.setVisible(true);
				                });
							client5= new Client5(socket);
							list5.add(client5);
						}else if(a1.equals("192.168.1.7")) {//6번자리 건희 192.168.1.7
							Platform.runLater(()->{
								Clientmesage6();
								measage6.close();
				            	   t1Logon6.setVisible(true);
				                });
							client6= new Client6(socket);
							list6.add(client6);
						}else if(a1.equals("192.168.0.207")) {//7번자리 우주192.168.0.207
							Platform.runLater(()->{
								Clientmesage7();
								measage7.close();
				            	   t1Logon7.setVisible(true);
				                });
							client7= new Client7(socket);
							list7.add(client7);
						}else if(a1.equals("192.168.0.180")) {//8번자리 동현 192.168.0.180
							Platform.runLater(()->{
								Clientmesage8();
								measage8.close();
				            	   t1Logon8.setVisible(true);
				                });
							client8= new Client8(socket);
							list8.add(client8);
						}else if(a1.equals("192.168.1.112")) {//9번자리 용수 192.168.1.112
							Platform.runLater(()->{
								Clientmesage9();
								measage9.close();
				            	   t1Logon9.setVisible(true);
				                });
							client9= new Client9(socket);
							list9.add(client9);
						}else if(a1.equals("192.168.0.205")) {//10번자리 재록 192.168.0.205
							Platform.runLater(()->{
								Clientmesage10();
								measage10.close();
				            	   t1Logon10.setVisible(true);
				                });
							client10= new Client10(socket);
							list10.add(client10);
						}else if(a1.equals("192.168.0.221")) {//11번자리 찬우 192.168.0.221
							Platform.runLater(()->{
								Clientmesage11();
								measage11.close();
				            	   t1Logon11.setVisible(true);
				                });
							client11= new Client11(socket);
							list11.add(client11);
						}else if(a1.equals("192.168.0.174")) {//12번자리 하영 192.168.0.174
							Platform.runLater(()->{
								Clientmesage12();
								measage12.close();
				            	   t1Logon12.setVisible(true);
				                });
							client12= new Client12(socket);
							list12.add(client12);
						}else if(a1.equals("192.168.1.17")) {//13번자리 민지 192.168.1.17
							Platform.runLater(()->{
								Clientmesage13();
								measage13.close();
				            	   t1Logon13.setVisible(true);
				                });
							client13= new Client13(socket);
							list13.add(client13);
						}else if(a1.equals("192.168.0.204")) {//14번자리 승환 192.168.0.204
							Platform.runLater(()->{
								Clientmesage14();
								measage14.close();
				            	   t1Logon14.setVisible(true);
				                });
							client14= new Client14(socket);
							list14.add(client14);
						}else if(a1.equals("192.168.0.189")) {//15번자리 민주 192.168.0.189
							Platform.runLater(()->{
								Clientmesage15();
								measage15.close();
				            	   t1Logon15.setVisible(true);
				                });
							client15= new Client15(socket);
							list15.add(client15);
						}else if(a1.equals("192.168.0.190")) {//16번자리  영민 192.168.0.190
							Platform.runLater(()->{
								Clientmesage16();
								measage16.close();
				            	   t1Logon16.setVisible(true);
				                });
							client16= new Client16(socket);
							list16.add(client16);
					}
					}catch (IOException e) {
						if (!serverSocket.isClosed()) {stopServer();}
						break;
					}
				} // end of while
			}// end of run
		};// end of runnalbe
		mainThread = new Thread(runnable);
		mainThread.start();
	}	//스탑서버(오류발생시정지)
	//서버 정지관리
	private void stopServer() {
		try {
			for(Client1 client: list1) {
					client.socket.close();
					list1.remove(client);
				}
			}catch (IOException e) {
				if(!serverSocket.isClosed()&& serverSocket!=null) {
					try {
						serverSocket.close();
					} catch (IOException e1) {}
				}
					if(mainThread.isAlive()) {
						mainThread.stop();
					}
			}
	}
	//내부 클라이언트들
	class Client1{
		Socket socket;
		public Client1(Socket socket) {
			this.socket = socket;
			receive();
		}
		private void receive() {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						//bug[0]==false
						while (true) {
							InputStream is = socket.getInputStream();
							InputStreamReader isr = new InputStreamReader(is);
							BufferedReader br = new BufferedReader(isr);
							String receiveMessage = br.readLine();
							Platform.runLater(()->{
								try {
									if(receiveMessage.equals("1번자리에서 주문을 시켰습니다.")) {
										t1BtnOrder.setStyle("-fx-background-color: #00ff00");
									}
								}catch(Exception e) {
									callAlert("사용종료:1번자리에서 사용을 종료합니다.");
									return;
								}
								if(MeasageClick1[0]==false){
									Clientmesage1();
									MeasageClick1[0]=true;//여기
								}
								measage1Area.appendText(receiveMessage+"\n");
							});
							if(receiveMessage==null) {
								try {
									list1.remove(Client1.this);
									socket.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
								t1Logon1.setVisible(false);
								return;
							}
						} // end of while
					}catch (Exception e) {
						try {
							socket.close();
						} catch (IOException e1) {
							e.printStackTrace();
						}
						list1.remove(Client1.this);
						t1Logon1.setVisible(false);
						threadS1.stop();
						threadR1.stop();
					}
				}
			};
			threadR1 = new Thread(runnable);
			threadR1.start();
		}
		private void send() {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						OutputStream os = socket.getOutputStream();
						PrintWriter pw = new PrintWriter(os);
						pw.println("카운터:"+measage1Field.getText());
						pw.flush();
					} catch (IOException e) {
						list1.remove(Client1.this);
						try {
							socket.close();
						} catch (IOException e1) {
						}
					}
				}
			};
			threadS1 = new Thread(runnable);
			threadS1.start();
		}
	}
	class Client2{
Socket socket2;
		public Client2(Socket socket) {
			this.socket2 = socket;
			receive();
		}
		private void receive() {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							InputStream is = socket2.getInputStream();
							InputStreamReader isr = new InputStreamReader(is);
							BufferedReader br = new BufferedReader(isr);
							String receiveMessage = br.readLine();
							Platform.runLater(()->{
								try {
									if(receiveMessage.equals("2번자리에서 주문을 시켰습니다.")) {
										t1BtnOrder.setStyle("-fx-background-color: #00ff00");
									}
								}catch(Exception e) {
									callAlert("사용종료:2번자리에서 사용을 종료합니다.");
									return;
								}
								if(MeasageClick1[1]==false) {
									Clientmesage2();
									MeasageClick1[1]=true;//여기
								}
								measage2Area.appendText(receiveMessage+"\n");
							});
							if(receiveMessage==null) {
								try {
									socket2.close();
									list2.remove(Client2.this);
								} catch (IOException e) {
									e.printStackTrace();
								}
								t1Logon2.setVisible(false);
								return;
							}
						} // end of while
					}catch (Exception e) {
						try {
							socket2.close();
						} catch (IOException e1) {
							e.printStackTrace();
						}
						list2.remove(Client2.this);
						t1Logon2.setVisible(false);
						threadS2.stop();
						threadR2.stop();
					}
				}
			};
			threadR2 = new Thread(runnable);
			threadR2.start();
		}
		private void send() {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						OutputStream os = socket2.getOutputStream();
						PrintWriter pw = new PrintWriter(os);
						pw.println("카운터:"+measage2Field.getText());
						pw.flush();
					} catch (IOException e) {
						list2.remove(Client2.this);
						try {
							socket2.close();
						} catch (IOException e1) {
						}
					}
				}
			};
			threadS2 = new Thread(runnable);
			threadS2.start();
		}
	}
	class Client3{
Socket socket3;
		public Client3(Socket socket) {
			this.socket3 = socket;
			receive();
		}
		private void receive() {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							InputStream is = socket3.getInputStream();
							InputStreamReader isr = new InputStreamReader(is);
							BufferedReader br = new BufferedReader(isr);
							String receiveMessage = br.readLine();
							Platform.runLater(()->{
								try {
									if(receiveMessage.equals("3번자리에서 주문을 시켰습니다.")) {
										t1BtnOrder.setStyle("-fx-background-color: #00ff00");
									}
								}catch(Exception e) {
									callAlert("사용종료:3번자리에서 사용을 종료합니다.");
									return;
								}
								if(MeasageClick1[2]==false) {
									Clientmesage3();
									//list1.remove(Client1.this);
									MeasageClick1[2]=true;
								}
								measage3Area.appendText(receiveMessage+"\n");
							});
							if(receiveMessage==null) {
								try {
									socket3.close();
									list3.remove(Client3.this);
								} catch (IOException e) {
									e.printStackTrace();
								}
								t1Logon3.setVisible(false);
								return;
							}
						} // end of while
					}catch (Exception e) {
						try {
							socket3.close();
						} catch (IOException e1) {
							e.printStackTrace();
						}
						list3.remove(Client3.this);
						t1Logon3.setVisible(false);
						threadS3.stop();
						threadR3.stop();
					}
				}
			};
			threadR3 = new Thread(runnable);
			threadR3.start();
		}
		private void send() {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						OutputStream os = socket3.getOutputStream();
						PrintWriter pw = new PrintWriter(os);
						pw.println("카운터:"+measage3Field.getText());
						pw.flush();
					} catch (IOException e) {
						list3.remove(Client3.this);
						try {
							socket3.close();
						} catch (IOException e1) {
						}
					}
				}
			};
			threadS3 = new Thread(runnable);
			threadS3.start();
		}
	}
	class Client4{
Socket socket4;
		public Client4(Socket socket) {
			this.socket4 = socket;
			receive();
		}
		private void receive() {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							InputStream is = socket4.getInputStream();
							InputStreamReader isr = new InputStreamReader(is);
							BufferedReader br = new BufferedReader(isr);
							String receiveMessage = br.readLine();
							Platform.runLater(()->{
								try {
									if(receiveMessage.equals("4번자리에서 주문을 시켰습니다.")) {
										t1BtnOrder.setStyle("-fx-background-color: #00ff00");
									}
								}catch(Exception e) {
									callAlert("사용종료:4번자리에서 사용을 종료합니다.");
									return;
								}
								if(MeasageClick1[3]==false) {
									Clientmesage4();
									//list1.remove(Client1.this);
									MeasageClick1[3]=true;//여기
								}
								measage4Area.appendText(receiveMessage+"\n");
							});
							if(receiveMessage==null) {
								try {
									socket4.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
								t1Logon4.setVisible(false);
								list4.remove(Client4.this);
								return;
							}
						} // end of while
					}catch (Exception e) {
						try {
							socket4.close();
						} catch (IOException e1) {
							e.printStackTrace();
						}
						list4.remove(Client4.this);
						t1Logon4.setVisible(false);
						threadS4.stop();
						threadR4.stop();
					}
				}
			};
			threadR4 = new Thread(runnable);
			threadR4.start();
		}
		private void send() {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						OutputStream os = socket4.getOutputStream();
						PrintWriter pw = new PrintWriter(os);
						pw.println("카운터:"+measage4Field.getText());
						pw.flush();
					} catch (IOException e) {
						list4.remove(Client4.this);
						try {
							socket4.close();
						} catch (IOException e1) {
						}
					}
				}
			};
			threadS4 = new Thread(runnable);
			threadS4.start();
		}
	}
	class Client5{
Socket socket5;
		public Client5(Socket socket) {
			this.socket5 = socket;
			receive();
		}
		private void receive() {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						while (true) {
							InputStream is = socket5.getInputStream();
							InputStreamReader isr = new InputStreamReader(is);
							BufferedReader br = new BufferedReader(isr);
							String receiveMessage = br.readLine();
							Platform.runLater(()->{
								try {
									if(receiveMessage.equals("5번자리에서 주문을 시켰습니다.")) {
										t1BtnOrder.setStyle("-fx-background-color: #00ff00");
									}
								}catch(Exception e) {
									callAlert("사용종료:5번자리에서 사용을 종료합니다.");
									return;
								}
								if(MeasageClick1[4]==false) {
									Clientmesage5();
									//list1.remove(Client1.this);
									MeasageClick1[4]=true;//여기
								}
								measage5Area.appendText(receiveMessage+"\n");
							});
							if(receiveMessage==null) {
								try {
									socket5.close();
								} catch (IOException e) {
									e.printStackTrace();
								}
								t1Logon5.setVisible(false);
								list5.remove(Client5.this);
								return;
							}
						} // end of while
					}catch (Exception e) {
						try {
							socket5.close();
						} catch (IOException e1) {
							e.printStackTrace();
						}
						list5.remove(Client5.this);
						t1Logon5.setVisible(false);
						threadS5.stop();
						threadR5.stop();
					}
				}
			};
			threadR5 = new Thread(runnable);
			threadR5.start();
		}
		private void send() {
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						OutputStream os = socket5.getOutputStream();
						PrintWriter pw = new PrintWriter(os);
						pw.println("카운터:"+measage5Field.getText());
						pw.flush();
					} catch (IOException e) {
						list5.remove(Client5.this);
						try {
							socket5.close();
						} catch (IOException e1) {
						}
					}
				}
			};
			threadS5 = new Thread(runnable);
			threadS5.start();
		}
	}
	class Client6{
		Socket socket6;
				public Client6(Socket socket) {
					this.socket6 = socket;
					receive();
				}
				private void receive() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								while (true) {
									InputStream is = socket6.getInputStream();
									InputStreamReader isr = new InputStreamReader(is);
									BufferedReader br = new BufferedReader(isr);
									String receiveMessage = br.readLine();
									Platform.runLater(()->{
										try {
											if(receiveMessage.equals("6번자리에서 주문을 시켰습니다.")) {
												t1BtnOrder.setStyle("-fx-background-color: #00ff00");
											}
										}catch(Exception e) {
											callAlert("사용종료:6번자리에서 사용을 종료합니다.");
											return;
										}
										if(MeasageClick1[5]==false) {
											Clientmesage6();
											//list1.remove(Client1.this);
											MeasageClick1[5]=true;//여기
										}
										measage6Area.appendText(receiveMessage+"\n");
									});
									if(receiveMessage==null) {
										try {
											socket6.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
										t1Logon6.setVisible(false);
										list6.remove(Client6.this);
										return;
									}
								} // end of while
							}catch (Exception e) {
								try {
									socket6.close();
								} catch (IOException e1) {
									e.printStackTrace();
								}
								list6.remove(Client6.this);
								t1Logon6.setVisible(false);
								threadS6.stop();
								threadR6.stop();
							}
						}
					};
					threadR6 = new Thread(runnable);
					threadR6.start();
				}
				private void send() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								OutputStream os = socket6.getOutputStream();
								PrintWriter pw = new PrintWriter(os);
								pw.println("카운터:"+measage6Field.getText());
								pw.flush();
							} catch (IOException e) {
								list6.remove(Client6.this);
								try {
									socket6.close();
								} catch (IOException e1) {
								}
							}
						}
					};
					threadS6 = new Thread(runnable);
					threadS6.start();
				}
			}
	class Client7{
		Socket socket7;
				public Client7(Socket socket) {
					this.socket7 = socket;
					receive();
				}
				private void receive() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								while (true) {
									InputStream is = socket7.getInputStream();
									InputStreamReader isr = new InputStreamReader(is);
									BufferedReader br = new BufferedReader(isr);
									String receiveMessage = br.readLine();
									Platform.runLater(()->{
										try {
											if(receiveMessage.equals("7번자리에서 주문을 시켰습니다.")) {
												t1BtnOrder.setStyle("-fx-background-color: #00ff00");
											}
										}catch(Exception e) {
											callAlert("사용종료:7번자리에서 사용을 종료합니다.");
											return;
										}
										if(MeasageClick1[6]==false) {
											Clientmesage7();
											//list1.remove(Client1.this);
											MeasageClick1[6]=true;//여기
										}
										measage7Area.appendText(receiveMessage+"\n");
									});
									if(receiveMessage==null) {
										try {
											socket7.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
										t1Logon7.setVisible(false);
										list7.remove(Client7.this);
										return;
									}
								} // end of while
							}catch (Exception e) {
								try {
									socket7.close();
								} catch (IOException e1) {
									e.printStackTrace();
								}
								list7.remove(Client7.this);
								t1Logon7.setVisible(false);
								threadS7.stop();
								threadR7.stop();
							}
						}
					};
					threadR7 = new Thread(runnable);
					threadR7.start();
				}
				private void send() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								OutputStream os = socket7.getOutputStream();
								PrintWriter pw = new PrintWriter(os);
								pw.println("카운터:"+measage7Field.getText());
								pw.flush();
							} catch (IOException e) {
								list7.remove(Client7.this);
								try {
									socket7.close();
								} catch (IOException e1) {
								}
							}
						}
					};
					threadS7 = new Thread(runnable);
					threadS7.start();
				}
			}
	class Client8{
		Socket socket8;
				public Client8(Socket socket) {
					this.socket8 = socket;
					receive();
				}
				private void receive() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								while (true) {
									InputStream is = socket8.getInputStream();
									InputStreamReader isr = new InputStreamReader(is);
									BufferedReader br = new BufferedReader(isr);
									String receiveMessage = br.readLine();
									Platform.runLater(()->{
										try {
											if(receiveMessage.equals("8번자리에서 주문을 시켰습니다.")) {
												t1BtnOrder.setStyle("-fx-background-color: #00ff00");
											}
										}catch(Exception e) {
											callAlert("사용종료:8번자리에서 사용을 종료합니다.");
											return;
										}
										if(MeasageClick1[7]==false) {
											Clientmesage8();
											//list1.remove(Client1.this);
											MeasageClick1[7]=true;//여기
										}
										measage8Area.appendText(receiveMessage+"\n");
									});
									if(receiveMessage==null) {
										try {
											socket8.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
										t1Logon8.setVisible(false);
										list8.remove(Client8.this);
										return;
									}
								} // end of while
							}catch (Exception e) {
								try {
									socket8.close();
								} catch (IOException e1) {
									e.printStackTrace();
								}
								list8.remove(Client8.this);
								t1Logon8.setVisible(false);
								threadS8.stop();
								threadR8.stop();
							}
						}
					};
					threadR8 = new Thread(runnable);
					threadR8.start();
				}
				private void send() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								OutputStream os = socket8.getOutputStream();
								PrintWriter pw = new PrintWriter(os);
								pw.println("카운터:"+measage8Field.getText());
								pw.flush();
							} catch (IOException e) {
								list8.remove(Client8.this);
								try {
									socket8.close();
								} catch (IOException e1) {
								}
							}
						}
					};
					threadS8 = new Thread(runnable);
					threadS8.start();
				}
			}
	class Client9{
		Socket socket9;
				public Client9(Socket socket) {
					this.socket9 = socket;
					receive();
				}
				private void receive() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								while (true) {
									InputStream is = socket9.getInputStream();
									InputStreamReader isr = new InputStreamReader(is);
									BufferedReader br = new BufferedReader(isr);
									String receiveMessage = br.readLine();
									Platform.runLater(()->{
										try {
											if(receiveMessage.equals("9번자리에서 주문을 시켰습니다.")) {
												t1BtnOrder.setStyle("-fx-background-color: #00ff00");
											}
										}catch(Exception e) {
											callAlert("사용종료:9번자리에서 사용을 종료합니다.");
											return;
										}
										if(MeasageClick1[8]==false) {
											Clientmesage9();
											//list1.remove(Client1.this);
											MeasageClick1[8]=true;//여기
										}
										measage9Area.appendText(receiveMessage+"\n");
									});
									if(receiveMessage==null) {
										try {
											socket9.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
										t1Logon9.setVisible(false);
										list9.remove(Client9.this);
										return;
									}
								} // end of while
							}catch (Exception e) {
								try {
									socket9.close();
								} catch (IOException e1) {
									e.printStackTrace();
								}
								list9.remove(Client9.this);
								t1Logon9.setVisible(false);
								threadS9.stop();
								threadR9.stop();
							}
						}
					};
					threadR9 = new Thread(runnable);
					threadR9.start();
				}
				private void send() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								OutputStream os = socket9.getOutputStream();
								PrintWriter pw = new PrintWriter(os);
								pw.println("카운터:"+measage9Field.getText());
								pw.flush();
							} catch (IOException e) {
								list9.remove(Client9.this);
								try {
									socket9.close();
								} catch (IOException e1) {
								}
							}
						}
					};
					threadS9 = new Thread(runnable);
					threadS9.start();
				}
			}
	class Client10{
		Socket socket10;
				public Client10(Socket socket) {
					this.socket10 = socket;
					receive();
				}
				private void receive() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								while (true) {
									InputStream is = socket10.getInputStream();
									InputStreamReader isr = new InputStreamReader(is);
									BufferedReader br = new BufferedReader(isr);
									String receiveMessage = br.readLine();
									Platform.runLater(()->{
										try {
											if(receiveMessage.equals("10번자리에서 주문을 시켰습니다.")) {
												t1BtnOrder.setStyle("-fx-background-color: #00ff00");
											}
										}catch(Exception e) {
											callAlert("사용종료:10번자리에서 사용을 종료합니다.");
											return;
										}
										if(MeasageClick1[9]==false) {
											Clientmesage10();
											//list1.remove(Client1.this);
											MeasageClick1[9]=true;//여기
										}
										measage10Area.appendText(receiveMessage+"\n");
									});
									if(receiveMessage==null) {
										try {
											socket10.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
										t1Logon10.setVisible(false);
										list10.remove(Client10.this);
										return;
									}
								} // end of while
							}catch (Exception e) {
								try {
									socket10.close();
								} catch (IOException e1) {
									e.printStackTrace();
								}
								list10.remove(Client10.this);
								t1Logon10.setVisible(false);
								threadS10.stop();
								threadR10.stop();
							}
						}
					};
					threadR10 = new Thread(runnable);
					threadR10.start();
				}
				private void send() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								OutputStream os = socket10.getOutputStream();
								PrintWriter pw = new PrintWriter(os);
								pw.println("카운터:"+measage10Field.getText());
								pw.flush();
							} catch (IOException e) {
								list10.remove(Client10.this);
								try {
									socket10.close();
								} catch (IOException e1) {
								}
							}
						}
					};
					threadS10 = new Thread(runnable);
					threadS10.start();
				}
			}
	class Client11{
		Socket socket11;
				public Client11(Socket socket) {
					this.socket11 = socket;
					receive();
				}
				private void receive() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								while (true) {
									InputStream is = socket11.getInputStream();
									InputStreamReader isr = new InputStreamReader(is);
									BufferedReader br = new BufferedReader(isr);
									String receiveMessage = br.readLine();
									Platform.runLater(()->{
										try {
											if(receiveMessage.equals("11번자리에서 주문을 시켰습니다.")) {
												t1BtnOrder.setStyle("-fx-background-color: #00ff00");
											}
										}catch(Exception e) {
											callAlert("사용종료:11번자리에서 사용을 종료합니다.");
											return;
										}
										if(MeasageClick1[10]==false) {
											Clientmesage11();
											MeasageClick1[10]=true;//여기
										}
										measage11Area.appendText(receiveMessage+"\n");
									});
									if(receiveMessage==null) {
										try {
											socket11.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
										t1Logon11.setVisible(false);
										list11.remove(Client11.this);
										return;
									}
								} // end of while
							}catch (Exception e) {
								try {
									socket11.close();
								} catch (IOException e1) {
									e.printStackTrace();
								}
								list11.remove(Client11.this);
								t1Logon11.setVisible(false);
								threadS11.stop();
								threadR11.stop();
							}
						}
					};
					threadR11 = new Thread(runnable);
					threadR11.start();
				}
				private void send() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								OutputStream os = socket11.getOutputStream();
								PrintWriter pw = new PrintWriter(os);
								pw.println("카운터:"+measage11Field.getText());
								pw.flush();
							} catch (IOException e) {
								list11.remove(Client11.this);
								try {
									socket11.close();
								} catch (IOException e1) {
								}
							}
						}
					};
					threadS11 = new Thread(runnable);
					threadS11.start();
				}
			}
	class Client12{
		Socket socket12;
				public Client12(Socket socket) {
					this.socket12 = socket;
					receive();
				}
				private void receive() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								while (true) {
									InputStream is = socket12.getInputStream();
									InputStreamReader isr = new InputStreamReader(is);
									BufferedReader br = new BufferedReader(isr);
									String receiveMessage = br.readLine();
									Platform.runLater(()->{
										try {
											if(receiveMessage.equals("12번자리에서 주문을 시켰습니다.")) {
												t1BtnOrder.setStyle("-fx-background-color: #00ff00");
											}
										}catch(Exception e) {
											callAlert("사용종료:12번자리에서 사용을 종료합니다.");
											return;
										}
										if(MeasageClick1[11]==false) {
											Clientmesage12();
											MeasageClick1[11]=true;//여기
										}
										measage12Area.appendText(receiveMessage+"\n");
									});
									if(receiveMessage==null) {
										try {
											socket12.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
										t1Logon12.setVisible(false);
										list12.remove(Client12.this);
										return;
									}
								} // end of while
							}catch (Exception e) {
								try {
									socket12.close();
								} catch (IOException e1) {
									e.printStackTrace();
								}
								list12.remove(Client12.this);
								t1Logon12.setVisible(false);
								threadS12.stop();
								threadR12.stop();
							}
						}
					};
					threadR12 = new Thread(runnable);
					threadR12.start();
				}
				private void send() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								OutputStream os = socket12.getOutputStream();
								PrintWriter pw = new PrintWriter(os);
								pw.println("카운터:"+measage12Field.getText());
								pw.flush();
							} catch (IOException e) {
								list12.remove(Client12.this);
								try {
									socket12.close();
								} catch (IOException e1) {
								}
							}
						}
					};
					threadS12 = new Thread(runnable);
					threadS12.start();
				}
			}
	class Client13{
		Socket socket13;
				public Client13(Socket socket) {
					this.socket13 = socket;
					receive();
				}
				private void receive() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								while (true) {
									InputStream is = socket13.getInputStream();
									InputStreamReader isr = new InputStreamReader(is);
									BufferedReader br = new BufferedReader(isr);
									String receiveMessage = br.readLine();
									Platform.runLater(()->{
										try {
											if(receiveMessage.equals("13번자리에서 주문을 시켰습니다.")) {
												t1BtnOrder.setStyle("-fx-background-color: #00ff00");
											}
										}catch(Exception e) {
											callAlert("사용종료:13번자리에서 사용을 종료합니다.");
											return;
										}
										if(MeasageClick1[12]==false) {
											Clientmesage13();
											MeasageClick1[12]=true;//여기
										}
										measage13Area.appendText(receiveMessage+"\n");
									});
									if(receiveMessage==null) {
										try {
											socket13.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
										t1Logon13.setVisible(false);
										list13.remove(Client13.this);
										return;
									}
								} // end of while
							}catch (Exception e) {
								try {
									socket13.close();
								} catch (IOException e1) {
									e.printStackTrace();
								}
								list13.remove(Client13.this);
								t1Logon13.setVisible(false);
								threadS13.stop();
								threadR13.stop();
							}
						}
					};
					threadR13 = new Thread(runnable);
					threadR13.start();
				}
				private void send() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								OutputStream os = socket13.getOutputStream();
								PrintWriter pw = new PrintWriter(os);
								pw.println("카운터:"+measage13Field.getText());
								pw.flush();
							} catch (IOException e) {
								list13.remove(Client13.this);
								try {
									socket13.close();
								} catch (IOException e1) {
								}
							}
						}
					};
					threadS13 = new Thread(runnable);
					threadS13.start();
				}
			}
	class Client14{
		Socket socket14;
				public Client14(Socket socket) {
					this.socket14 = socket;
					receive();
				}
				private void receive() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								while (true) {
									InputStream is = socket14.getInputStream();
									InputStreamReader isr = new InputStreamReader(is);
									BufferedReader br = new BufferedReader(isr);
									String receiveMessage = br.readLine();
									Platform.runLater(()->{
										try {
											if(receiveMessage.equals("14번자리에서 주문을 시켰습니다.")) {
												t1BtnOrder.setStyle("-fx-background-color: #00ff00");
											}
										}catch(Exception e) {
											callAlert("사용종료:14번자리에서 사용을 종료합니다.");
											return;
										}
										if(MeasageClick1[13]==false) {
											Clientmesage14();
											MeasageClick1[13]=true;//여기
										}
										measage14Area.appendText(receiveMessage+"\n");
									});
									if(receiveMessage==null) {
										try {
											socket14.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
										t1Logon14.setVisible(false);
										list14.remove(Client14.this);
										return;
									}
								} // end of while
							}catch (Exception e) {
								try {
									socket14.close();
								} catch (IOException e1) {
									e.printStackTrace();
								}
								list14.remove(Client14.this);
								t1Logon14.setVisible(false);
								threadS14.stop();
								threadR14.stop();
							}
						}
					};
					threadR14 = new Thread(runnable);
					threadR14.start();
				}
				private void send() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								OutputStream os = socket14.getOutputStream();
								PrintWriter pw = new PrintWriter(os);
								pw.println("카운터:"+measage14Field.getText());
								pw.flush();
							} catch (IOException e) {
								list14.remove(Client14.this);
								try {
									socket14.close();
								} catch (IOException e1) {
								}
							}
						}
					};
					threadS14 = new Thread(runnable);
					threadS14.start();
				}
			}
	class Client15{
		Socket socket15;
				public Client15(Socket socket) {
					this.socket15 = socket;
					receive();
				}
				private void receive() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								while (true) {
									InputStream is = socket15.getInputStream();
									InputStreamReader isr = new InputStreamReader(is);
									BufferedReader br = new BufferedReader(isr);
									String receiveMessage = br.readLine();
									Platform.runLater(()->{
										try {
											if(receiveMessage.equals("15번자리에서 주문을 시켰습니다.")) {
												t1BtnOrder.setStyle("-fx-background-color: #00ff00");
											}
										}catch(Exception e) {
											callAlert("사용종료:15번자리에서 사용을 종료합니다.");
											return;
										}
										if(MeasageClick1[14]==false) {
											Clientmesage15();
											MeasageClick1[14]=true;//여기
										}
										measage15Area.appendText(receiveMessage+"\n");
									});
									if(receiveMessage==null) {
										try {
											socket15.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
										t1Logon15.setVisible(false);
										list15.remove(Client15.this);
										return;
									}
								} // end of while
							}catch (Exception e) {
								try {
									socket15.close();
								} catch (IOException e1) {
									e.printStackTrace();
								}
								list15.remove(Client15.this);
								t1Logon15.setVisible(false);
								threadS15.stop();
								threadR15.stop();
							}
						}
					};
					threadR15 = new Thread(runnable);
					threadR15.start();
				}
				private void send() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								OutputStream os = socket15.getOutputStream();
								PrintWriter pw = new PrintWriter(os);
								pw.println("카운터:"+measage15Field.getText());
								pw.flush();
							} catch (IOException e) {
								list15.remove(Client15.this);
								try {
									socket15.close();
								} catch (IOException e1) {
								}
							}
						}
					};
					threadS15 = new Thread(runnable);
					threadS15.start();
				}
			}
	class Client16{
		Socket socket16;
				public Client16(Socket socket) {
					this.socket16 = socket;
					receive();
				}
				private void receive() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								while (true) {
									InputStream is = socket16.getInputStream();
									InputStreamReader isr = new InputStreamReader(is);
									BufferedReader br = new BufferedReader(isr);
									String receiveMessage = br.readLine();
									Platform.runLater(()->{
										try {
											if(receiveMessage.equals("16번자리에서 주문을 시켰습니다.")) {
												t1BtnOrder.setStyle("-fx-background-color: #00ff00");
											}
										}catch(Exception e) {
											callAlert("사용종료:16번자리에서 사용을 종료합니다.");
											return;
										}
										if(MeasageClick1[15]==false) {
											Clientmesage16();
											MeasageClick1[15]=true;//여기
										}
										measage16Area.appendText(receiveMessage+"\n");
									});
									if(receiveMessage==null) {
										try {
											socket16.close();
										} catch (IOException e) {
											e.printStackTrace();
										}
										t1Logon16.setVisible(false);
										list16.remove(Client16.this);
										return;
									}
								} // end of while
							}catch (Exception e) {
								try {
									socket16.close();
								} catch (IOException e1) {
									e.printStackTrace();
								}
								list16.remove(Client16.this);
								t1Logon16.setVisible(false);
								threadS16.stop();
								threadR16.stop();
							}
						}
					};
					threadR16 = new Thread(runnable);
					threadR16.start();
				}
				private void send() {
					Runnable runnable = new Runnable() {
						@Override
						public void run() {
							try {
								OutputStream os = socket16.getOutputStream();
								PrintWriter pw = new PrintWriter(os);
								pw.println("카운터:"+measage16Field.getText());
								pw.flush();
							} catch (IOException e) {
								list16.remove(Client16.this);
								try {
									socket16.close();
								} catch (IOException e1) {
								}
							}
						}
					};
					threadS16 = new Thread(runnable);
					threadS16.start();
				}
			}
	private void loinControl() {
		t1Logon1.setVisible(false);
		t1Logon1.setOnMouseClicked(e->{
			if(MeasageClick1[0]==false) {
				Clientmesage1();
				MeasageClick1[0]=true;
			}else {
				measage1.close();
				MeasageClick1[0]=false;
			}
		});
		t1Logon2.setVisible(false);
		t1Logon2.setOnMouseClicked(e->{
			if(MeasageClick1[1]==false) {
				Clientmesage2();
				MeasageClick1[1]=true;
			}else {
				measage2.close();
				MeasageClick1[1]=false;
			}
		});
		t1Logon3.setVisible(false);
		t1Logon3.setOnMouseClicked(e->{
			if(MeasageClick1[2]==false) {
				Clientmesage3();
				MeasageClick1[2]=true;
			}else {
				measage3.close();
				MeasageClick1[2]=false;
			}
		});
		t1Logon4.setVisible(false);
		t1Logon4.setOnMouseClicked(e->{
			if(MeasageClick1[3]==false) {
				Clientmesage4();
				MeasageClick1[3]=true;
			}else {
				measage4.close();
				MeasageClick1[3]=false;
			}
		});
		t1Logon5.setVisible(false);
		t1Logon5.setOnMouseClicked(e->{
			if(MeasageClick1[4]==false) {
				Clientmesage5();
				MeasageClick1[4]=true;
			}else {
				measage5.close();
				MeasageClick1[4]=false;
			}
		});
		t1Logon6.setVisible(false);
		t1Logon6.setOnMouseClicked(e->{
			if(MeasageClick1[5]==false) {
				Clientmesage6();
				MeasageClick1[5]=true;
			}else {
				measage6.close();
				MeasageClick1[5]=false;
			}
		});
		t1Logon7.setVisible(false);
		t1Logon7.setOnMouseClicked(e->{
			if(MeasageClick1[6]==false) {
				Clientmesage7();
				MeasageClick1[6]=true;
			}else {
				measage7.close();
				MeasageClick1[6]=false;
			}
		});
		t1Logon8.setVisible(false);
		t1Logon8.setOnMouseClicked(e->{
			if(MeasageClick1[7]==false) {
				Clientmesage8();
				MeasageClick1[7]=true;
			}else {
				measage8.close();
				MeasageClick1[7]=false;
			}
		});
		t1Logon9.setVisible(false);
		t1Logon9.setOnMouseClicked(e->{
			if(MeasageClick1[8]==false) {
				Clientmesage9();
				MeasageClick1[8]=true;
			}else {
				measage9.close();
				MeasageClick1[8]=false;
			}
		});
		t1Logon10.setVisible(false);
		t1Logon10.setOnMouseClicked(e->{
			if(MeasageClick1[9]==false) {
				Clientmesage10();
				MeasageClick1[9]=true;
			}else {
				measage10.close();
				MeasageClick1[9]=false;
			}
		});
		t1Logon11.setVisible(false);
		t1Logon11.setOnMouseClicked(e->{
			if(MeasageClick1[10]==false) {
				Clientmesage11();
				MeasageClick1[10]=true;
			}else {
				measage11.close();
				MeasageClick1[10]=false;
			}
		});
		t1Logon12.setVisible(false);
		t1Logon12.setOnMouseClicked(e->{
			if(MeasageClick1[11]==false) {
				Clientmesage12();
				MeasageClick1[11]=true;
			}else {
				measage12.close();
				MeasageClick1[11]=false;
			}
		});
		t1Logon13.setVisible(false);
		t1Logon13.setOnMouseClicked(e->{
			if(MeasageClick1[12]==false) {
				Clientmesage13();
				MeasageClick1[12]=true;
			}else {
				measage13.close();
				MeasageClick1[12]=false;
			}
		});
		t1Logon14.setVisible(false);
		t1Logon14.setOnMouseClicked(e->{
			if(MeasageClick1[13]==false) {
				Clientmesage14();
				MeasageClick1[13]=true;
			}else {
				measage14.close();
				MeasageClick1[13]=false;
			}
		});
		t1Logon15.setVisible(false);
		t1Logon15.setOnMouseClicked(e->{
			if(MeasageClick1[14]==false) {
				Clientmesage15();
				MeasageClick1[14]=true;
			}else {
				measage15.close();
				MeasageClick1[14]=false;
			}
		});
		t1Logon16.setVisible(false);
		t1Logon16.setOnMouseClicked(e->{
			if(MeasageClick1[15]==false) {
				Clientmesage16();
				MeasageClick1[15]=true;
			}else {
				measage16.close();
				MeasageClick1[15]=false;
			}
		});
	}
	///////////////////루트컨트롤관리
	//주문현황
	private void t1BtnOrderButton() {
			setcolor();
			Stage orderStage=new Stage(StageStyle.DECORATED);
			orderStage.setTitle("주문현황");
			orderStage.setX(250);
			orderStage.setY(130);
			orderStage.initModality(Modality.NONE);
			orderStage.initOwner(stage);
			Parent root =null;
			try{
				root=FXMLLoader.load(getClass().getResource("../view/t1BtnOrder.fxml"));
			}catch(Exception e) {
			}
			Button t1orderregister=(Button)root.lookup("#t1orderregister");
			Button newOrderTable=(Button)root.lookup("#newOrderTable");
			t1orderTableView =(TableView)root.lookup("#t1orderTableView");

			TableColumn t1ordernum=t1orderTableView.getColumns().get(0);
			t1ordernum.setCellValueFactory(new PropertyValueFactory<>("number"));
			t1ordernum.setStyle("-fx-alignment:CENTER;");
			TableColumn t1orderuserID=t1orderTableView.getColumns().get(1);
			t1orderuserID.setCellValueFactory(new PropertyValueFactory<>("id"));
			t1orderuserID.setStyle("-fx-alignment:CENTER;");
			TableColumn t1ordermenu=t1orderTableView.getColumns().get(2);
			t1ordermenu.setCellValueFactory(new PropertyValueFactory<>("menu"));
			t1ordermenu.setStyle("-fx-alignment:CENTER;");
			TableColumn t1orderamount=t1orderTableView.getColumns().get(3);
			t1orderamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
			t1orderamount.setStyle("-fx-alignment:CENTER;");
			TableColumn t1orderfice=t1orderTableView.getColumns().get(4);
			t1orderfice.setCellValueFactory(new PropertyValueFactory<>("fprice"));
			t1orderfice.setStyle("-fx-alignment:CENTER;");
			TableColumn t1ordermway=t1orderTableView.getColumns().get(5);
			t1ordermway.setCellValueFactory(new PropertyValueFactory<>("way"));
			t1ordermway.setStyle("-fx-alignment:CENTER;");
			TableColumn t1orderunit=t1orderTableView.getColumns().get(6);
			t1orderunit.setCellValueFactory(new PropertyValueFactory<>("unit"));
			t1orderunit.setStyle("-fx-alignment:CENTER;");
			TableColumn t1orderask=t1orderTableView.getColumns().get(7);
			t1orderask.setCellValueFactory(new PropertyValueFactory<>("ask"));
			t1orderask.setStyle("-fx-alignment:CENTER;");
			TableColumn t1ordermdate=t1orderTableView.getColumns().get(8);
			t1ordermdate.setCellValueFactory(new PropertyValueFactory<>("date"));
			t1ordermdate.setStyle("-fx-alignment:CENTER;");
			Scene scene = new Scene(root);
			orderStage.setScene(scene);
			orderStage.show();
			t1orderregister.setOnAction(e->{
					orderStage.close();
					setcolor();
			});
			newOrderTable.setOnAction(e->{
				neworderSetting();
				if(dbtFinalorder2TableViewList.isEmpty()) {
					dbtFinalorder2TableViewList=MemberDAO.finalorder2();
					for(Finalorder2 fd:dbtFinalorder2TableViewList) {
						t1finalorder2TableViewList.add(fd);
					}
				}
				//t1orderTableView.setItems(t1finalorder2TableViewList);
			});
			t1orderTableView.setItems(t1finalorder2TableViewList);
			
	}
	//작업사항(메모장)
	private void memoControl() {
		Stage memoStage=new Stage(StageStyle.DECORATED);
		memoStage.setTitle("작업사항");
		memoStage.setX(0);
		memoStage.setY(0);
		memoStage.initModality(Modality.NONE);
		memoStage.initOwner(stage);
		Parent root =null;
		try {
			root=FXMLLoader.load(getClass().getResource("../view/memo.fxml"));
			s= new Scanner(new BufferedReader(new FileReader("memo.txt")));
		} catch (IOException e) {
			createMemo();
		}finally {
			try {
				s= new Scanner(new BufferedReader(new FileReader("memo.txt")));
			} catch (Exception e) {
				callAlert("파일 생성.:작업 공간이 생성되었습니다.\n*작업사항*을 다시 눌러주세요");
			}
		}
		t1Memo=(TextArea)root.lookup("#t1Memo");
		Button t1MemoBtnSave=(Button)root.lookup("#t1MemoBtnSave");
		Button t1MemoBtnCancel=(Button)root.lookup("#t1MemoBtnCancel");
		while(s.hasNextInt()) {
			memo=s.nextLine();
		}
		t1Memo.setText(memo);
		Scene scene = new Scene(root);
		memoStage.setScene(scene);
		memoStage.show();
		//작업사항(메모장)저장
		t1MemoBtnSave.setOnAction(e->{
			t1MemoBtnCancel.setOnAction(e1->memoStage.close());
		    createMemo();
		    memoStage.close();
		});
		//작업사항(메모장)닫기
		t1MemoBtnCancel.setOnAction(e->{memoStage.close();
		});
	}
	//알림창 함수
	public static void callAlert(String contentText) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("알림창");
		alert.setHeaderText(contentText.substring(0, contentText.lastIndexOf(":")));
		alert.setContentText(contentText.substring( contentText.lastIndexOf(":")+1));
		alert.showAndWait();
	}
	//파일생성
	private void createMemo() {
		    try {
		    	memo =t1Memo.getText();
		    	//System.out.println("2 memo:"+memo);
		        bufferedWriter = new BufferedWriter(new FileWriter("memo.txt"));
		        bufferedWriter.write(memo);
		        bufferedWriter.flush();
		        bufferedWriter.close();
		    }catch(NullPointerException n) {
		    } catch (IOException e) {
		        // 파일 입출력 예외에 대한 코드는 여기에 작성하세요.
		        System.out.println("memo.txt 파일을 열 수 없습니다.");
		    }
	}
	//관리자실행
	private void t1BtnManager() {
		memoStage=new Stage(StageStyle.UNDECORATED);
		memoStage.setTitle("작업사항");
		memoStage.setX(250);
		memoStage.setY(300);
		memoStage.initModality(Modality.NONE);
		memoStage.initOwner(stage);
		Parent root =null;
		try {
			root=FXMLLoader.load(getClass().getResource("../view/managerLoginStage.fxml"));
		} catch (IOException e) {}
		ImageView mlsAuto=(ImageView)root.lookup("#mlsAuto");
		mlslog1=(Button)root.lookup("#mlslog1");
		mlslog2=(Button)root.lookup("#mlslog2");
		mlsId=(TextField)root.lookup("#mlsId");
	    mlsPassword=(TextField)root.lookup("#mlsPassword");
		mlsExit=(Button)root.lookup("#mlsExit");
		mlsExit.setOnAction(e->{
			stockmanagement.close();
		});
		Scene scene = new Scene(root);
		memoStage.setScene(scene);
		memoStage.show();
		mlsExit.setOnAction(e->{memoStage.close();});
		mlslog1.setOnAction(e->{
			loginControl();
			memoStage.close();
		});//로그인컨트롤
		mlslog2.setOnAction(e->{
			loginControl();
			memoStage.close();
		});//로그인컨트롤
	}
	//(매출관리) 스토크 매니저창
	private void loginControl() {
		if(!(mlsId.getText().equals("root")&&mlsPassword.getText().equals("123456"))) {
			callAlert("로그인실패:아이디나 패스워드가 맞지 않습니다.");
			mlsId.clear();mlsId.clear();
			return;
		}else {
			stockmanagement=new Stage(StageStyle.DECORATED);
			stockmanagement.setTitle("매출관리");
			stockmanagement.initModality(Modality.NONE);
			stockmanagement.initOwner(stage);
			Parent root =null;
			try {
				root=FXMLLoader.load(getClass().getResource("../view/stockmanagement.fxml"));
			} catch (IOException e) {}
			Button salesExit=(Button)root.lookup("#salesExit");
			Button salesMoney=(Button)root.lookup("#salesMoney");
			Button salesCard=(Button)root.lookup("#salesCard");
			Button salesSearchBtn=(Button)root.lookup("#salesSearchBtn");
			Button salesChart=(Button)root.lookup("#salesChart");
			Button slaesNew=(Button)root.lookup("#slaesNew");
			salesCombo=(ComboBox)root.lookup("#salesCombo");
			TextField salesSearchTxt=(TextField)root.lookup("#salesSearchTxt");
			slaesTotal=(Label)root.lookup("#slaesTotal");
			TableView<Finalorder2> salesView=(TableView)root.lookup("#salesView");
			TableColumn salesWay=salesView.getColumns().get(0);
			salesWay.setCellValueFactory(new PropertyValueFactory<>("way"));
			salesWay.setStyle("-fx-alignment:CENTER;");
			TableColumn salesid=salesView.getColumns().get(1);
			salesid.setCellValueFactory(new PropertyValueFactory<>("id"));
			salesid.setStyle("-fx-alignment:CENTER;");
			TableColumn salesmenu=salesView.getColumns().get(2);
			salesmenu.setCellValueFactory(new PropertyValueFactory<>("menu"));
			salesmenu.setStyle("-fx-alignment:CENTER;");
			TableColumn salesamount=salesView.getColumns().get(3);
			salesamount.setCellValueFactory(new PropertyValueFactory<>("amount"));
			salesamount.setStyle("-fx-alignment:CENTER;");
			TableColumn salesfprice=salesView.getColumns().get(4);
			salesfprice.setCellValueFactory(new PropertyValueFactory<>("fprice"));
			salesfprice.setStyle("-fx-alignment:CENTER;");
			TableColumn salesDate=salesView.getColumns().get(5);
			salesDate.setCellValueFactory(new PropertyValueFactory<>("date"));
			salesDate.setStyle("-fx-alignment:CENTER;");
			
			
			Scene scene = new Scene(root);
			stockmanagement.setScene(scene);
			stockmanagement.show();
			salesView.setItems(t1sales2TableViewList);
			db1salesList=MemberDAO.salestotal();
			for(Finalorder2 fd:db1salesList) {
				t1sales2TableViewList.add(fd);
			}
			//새로고침
			slaesNew.setOnAction(e->{
				t1sales2TableViewList.clear();
				db1salesList.clear();
				db1salesList=MemberDAO.salestotal();
				for(Finalorder2 fd:db1salesList) {
					t1sales2TableViewList.add(fd);
					moneyValue=MemberDAO.salestotaMoney();
					slaesTotal.setText(moneyValue);
				}
			});
			//첫화면 토탈금액
			moneyValue=MemberDAO.salestotaMoney();
			slaesTotal.setText(moneyValue);
			salesExit.setOnAction(e->{stockmanagement.close();});
			salesCard.setOnAction(e->{salesCard();});
			salesMoney.setOnAction(e->{salesMoney();});
				salesComboxList.clear();
				salesCombo();
				//검색 옵션(상품,id)
				salesSearchBtn.setOnAction(e->{
					try {
						if(salesCombo.getSelectionModel().getSelectedItem().toString().equals("상품명")) {
							t1sales2TableViewList.clear();
							db1salesList.clear();
							salesView.setItems(t1sales2TableViewList);
							db1salesList =MemberDAO.salesMenu(salesSearchTxt.getText());
									for (Finalorder2 fo : db1salesList) {
										t1sales2TableViewList.add(fo);
									}
									moneyValue=MemberDAO.salestmenumoney(salesSearchTxt.getText());
									slaesTotal.setText(moneyValue);
						}else if(salesCombo.getSelectionModel().getSelectedItem().toString().equals("ID")) {
							t1sales2TableViewList.clear();
							db1salesList.clear();
							salesView.setItems(t1sales2TableViewList);
							db1salesList =MemberDAO.salesId(salesSearchTxt.getText());
								for (Finalorder2 fo : db1salesList) {
									t1sales2TableViewList.add(fo);
							}
								moneyValue=MemberDAO.salestidmoney(salesSearchTxt.getText());
								slaesTotal.setText(moneyValue);
						}
					}catch(Exception e1) {
						callAlert("검색 옵션 지정:검색 옵션을 지정하세요");
					}
				});
				
				//차트
				salesSearchTxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
					@Override
					public void handle(KeyEvent event) {
						if(event.getCode().equals(KeyCode.ENTER)) {
							try {
								if(salesCombo.getSelectionModel().getSelectedItem().toString().equals("상품명")) {
									t1sales2TableViewList.clear();
									db1salesList.clear();
									salesView.setItems(t1sales2TableViewList);
									db1salesList =MemberDAO.salesMenu(salesSearchTxt.getText());
											for (Finalorder2 fo : db1salesList) {
												t1sales2TableViewList.add(fo);
											}
											moneyValue=MemberDAO.salestmenumoney(salesSearchTxt.getText());
											slaesTotal.setText(moneyValue);
								}else if(salesCombo.getSelectionModel().getSelectedItem().toString().equals("ID")) {
									t1sales2TableViewList.clear();
									db1salesList.clear();
									salesView.setItems(t1sales2TableViewList);
									db1salesList =MemberDAO.salesId(salesSearchTxt.getText());
										for (Finalorder2 fo : db1salesList) {
											t1sales2TableViewList.add(fo);
									}
										moneyValue=MemberDAO.salestidmoney(salesSearchTxt.getText());
										slaesTotal.setText(moneyValue);
								}
							}catch(Exception e1) {
								callAlert("검색 옵션 지정:검색 옵션을 지정하세요");
							}
						}
					}
				});
				salesChart.setOnAction(e->{
					waychart();
				});
				
		}
	}
	//매출관리 카드버튼
	private void salesCard() {
		t1sales2TableViewList.clear();
		db1salesList.clear();
		db1salesList=MemberDAO.salesWay("카드");
		for(Finalorder2 fd:db1salesList) {
			t1sales2TableViewList.add(fd);
		}
		moneyValue =MemberDAO.salestmwaymoney("카드");
		slaesTotal.setText(moneyValue);
		
	}
	//매출관리 현금버튼
	private void salesMoney() {
		t1sales2TableViewList.clear();
		db1salesList.clear();
		db1salesList=MemberDAO.salesWay("현금");
		for(Finalorder2 fd:db1salesList) {
			t1sales2TableViewList.add(fd);
		}
		moneyValue =MemberDAO.salestmwaymoney("현금");
		slaesTotal.setText(moneyValue);
	}
	//콤보박스세팅
	private void salesCombo() {
			salesComboxList.add("ID");
			salesComboxList.add("상품명");
			salesCombo.setItems(salesComboxList);
	}
	//회원관리 검색 수정 삭제 새로고침
	private void membercontrol() {
		//검색
		t2SearchButton.setOnAction(e->{
			try {
				if(t2SearchCombo.getSelectionModel().getSelectedItem().toString().equals("이름")) {
					t2TableViewList.clear();
					dbt2TableViewList.clear();
					t2TableView.setItems(t2TableViewList);
					dbt2TableViewList =MemberDAO.searchname(t2SearchTxt.getText());
							for (Member member : dbt2TableViewList) {
								t2TableViewList.add(member);
							}
				}else if(t2SearchCombo.getSelectionModel().getSelectedItem().toString().equals("ID")) {
					t2TableViewList.clear();
					dbt2TableViewList.clear();
					t2TableView.setItems(t2TableViewList);
					dbt2TableViewList =MemberDAO.searchID(t2SearchTxt.getText());
							for (Member member : dbt2TableViewList) {
								t2TableViewList.add(member);
							}
				}
				
			}catch(Exception e1) {
				callAlert("검색 옵션 지정:검색 옵션을 지정하세요");
			}
		});
		t2SearchTxt.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					try {
						if(t2SearchCombo.getSelectionModel().getSelectedItem().toString().equals("이름")) {
							t2TableViewList.clear();
							dbt2TableViewList.clear();
							t2TableView.setItems(t2TableViewList);
							dbt2TableViewList =MemberDAO.searchname(t2SearchTxt.getText());
									for (Member member : dbt2TableViewList) {
										t2TableViewList.add(member);
									}
						}else if(t2SearchCombo.getSelectionModel().getSelectedItem().toString().equals("ID")) {
							t2TableViewList.clear();
							dbt2TableViewList.clear();
							t2TableView.setItems(t2TableViewList);
							dbt2TableViewList =MemberDAO.searchID(t2SearchTxt.getText());
									for (Member member : dbt2TableViewList) {
										t2TableViewList.add(member);
									}
						}
						
					}catch(Exception e1) {
						callAlert("검색 옵션 지정:검색 옵션을 지정하세요");
					}
				}
			}
		});
		//새로고침
		t2NewSence.setOnAction(e->{
			t2TableViewList.clear();
			dbt2TableViewList.clear();
			t2TableView.setItems(t2TableViewList);
			dbt2TableViewList =MemberDAO.getMemberTotalData();
					for (Member member : dbt2TableViewList) {
						t2TableViewList.add(member);
					}
		});
		//수정
		t2SUpdateButton.setOnAction(e->{
			memberUpdateStage();
		});
		//삭제
		t2SDeleteButton.setOnAction(e->{
			member = t2TableView.getSelectionModel().getSelectedItem();
			MemberDAO.deleteMembertData(member.getId());
			callAlert("삭제완료: 새로고침을 눌러서 확인하세요");
		});
	}
	//회원관리 검색 시스템 콤보박스
	private void memberComboboxSeting() {
		membercombobox.add("이름");
		membercombobox.add("ID");
		t2SearchCombo.setItems(membercombobox);
	}
	//회원관리 수정창
	private void memberUpdateStage() {
		member = t2TableView.getSelectionModel().getSelectedItem();
		memberIndex = t2TableView.getSelectionModel().getSelectedIndex();
		Stage memberUpdateStage=new Stage(StageStyle.DECORATED);
		memberUpdateStage.setTitle("회원정보 수정창");
		memberUpdateStage.initModality(Modality.NONE);
		memberUpdateStage.initOwner(stage);
		Parent root =null; 
		try {
			root=FXMLLoader.load(getClass().getResource("../view/memberUpdateStage.fxml"));
		}catch (Exception e) {}
		Button updateBtn=(Button)root.lookup("#updateBtn");
		Button updateCancel=(Button)root.lookup("#updateCancel");
		TextField updatetName=(TextField)root.lookup("#updatetName");
		TextField updateID=(TextField)root.lookup("#updateID");
		TextField updatePassword=(TextField)root.lookup("#updatePassword");
		TextField updateAge=(TextField)root.lookup("#updateAge");
		TextField updatePhoneNumber=(TextField)root.lookup("#updatePhoneNumber");
		TextField updateE_mail=(TextField)root.lookup("#updateE_mail");

		updatetName.setText(member.getName());
		updateID.setText(member.getId());
		updateID.setEditable(false);
		updatePassword.setText(member.getPassword());
		updateAge.setText(member.getAge());
		updatePhoneNumber.setText(member.getPhonNumber());
		updateE_mail.setText(member.getE_mail());
	
		updateBtn.setOnAction(e->{
			member=new Member(updatetName.getText(), updateID.getText(), updatePassword.getText(), updateAge.getText(), updatePhoneNumber.getText(), updateE_mail.getText());
			MemberDAO.updateMemberData(member);
			neworderSetting();
			dbtFinalorder2TableViewList=MemberDAO.finalorder2();
			for(Finalorder2 fd:dbtFinalorder2TableViewList) {
				t1finalorder2TableViewList.add(fd);
			}
			callAlert("수정완료: 새로고침을 눌러서 확인하세요");
			memberUpdateStage.close();
		});
		updateCancel.setOnAction(e->{
			memberUpdateStage.close();
		});
		Scene scene = new Scene(root);
		memberUpdateStage.setScene(scene);
		memberUpdateStage.show();

	}
	//회원관리창 테이블뷰 세팅
	private void t2TableviewSet() {
		t2name.setCellValueFactory(new PropertyValueFactory<>("name"));
		t2name.setStyle("-fx-alignment:CENTER;");
		t2Id.setCellValueFactory(new PropertyValueFactory<>("id"));
		t2Id.setStyle("-fx-alignment:CENTER;");
		t2IPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
		t2Id.setStyle("-fx-alignment:CENTER;");
		t2Gender.setCellValueFactory(new PropertyValueFactory<>("gender"));
		t2Gender.setStyle("-fx-alignment:CENTER;");
		t2age.setCellValueFactory(new PropertyValueFactory<>("age"));
		t2age.setStyle("-fx-alignment:CENTER;");
		t2PhoneNumber.setCellValueFactory(new PropertyValueFactory<>("phonNumber"));
		t2PhoneNumber.setStyle("-fx-alignment:CENTER;");
		t2Email.setCellValueFactory(new PropertyValueFactory<>("e_mail"));
		t2Email.setStyle("-fx-alignment:CENTER;");
		t2Date.setCellValueFactory(new PropertyValueFactory<>("date"));
		t2Date.setStyle("-fx-alignment:CENTER;");
		
		t2TableView.setItems(t2TableViewList);
		dbt2TableViewList =MemberDAO.getMemberTotalData();
				for (Member member : dbt2TableViewList) {
					t2TableViewList.add(member);
				}
	}
	//주문현황 세팅
	private void orderSetting() {
		dbtFinalorder2TableViewList=MemberDAO.finalorder2();
		for(Finalorder2 fd:dbtFinalorder2TableViewList) {
			t1finalorder2TableViewList.add(fd);
		}
}
	//주문현황 새로고침
	private void neworderSetting() {
					dbtFinalorder2TableViewList.clear();
					t1finalorder2TableViewList.clear();		
	}
	//차트
	private void waychart() {
		Stage barchartStage=new Stage(StageStyle.UTILITY);
		barchartStage.initModality(Modality.WINDOW_MODAL);
		barchartStage.initOwner(stage);
		Parent root =null;
		try {
			root = FXMLLoader.load(getClass().getResource("../View/chart.fxml"));
		}catch(Exception e) {}
			BarChart barchart=(BarChart)root.lookup("#barchart");
			Button barchartExit=(Button)root.lookup("#chartExit");
			String cardvalue =MemberDAO.salestmwaymoney("현금");
			String realmoneyvalue =MemberDAO.salestmwaymoney("카드");
			XYChart.Series<String, Integer> series1=new XYChart.Series();
			series1.setName("매출표");
			ObservableList list1 = FXCollections.observableArrayList();
			list1.add(new XYChart.Data<>("현금", Integer.parseInt(cardvalue)));
			list1.add(new XYChart.Data<>("카드", Integer.parseInt(realmoneyvalue)));
			series1.setData(list1);
			barchart.getData().add(series1);
			Scene scene=new Scene(root);
			barchartStage.setScene(scene);
			barchartStage.show();
			
			barchartExit.setOnAction(e->{barchartStage.close();});
	}
}