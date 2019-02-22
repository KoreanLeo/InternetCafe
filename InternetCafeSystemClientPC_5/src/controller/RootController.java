package controller;
import java.awt.Toolkit;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import com.sun.javafx.image.impl.ByteIndexed.Getter;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Stop;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Finalorder;
import model.Finalorder2;
import model.Member;
public class RootController implements Initializable { 

	//소켓
	private Socket socket;
	//시간
	int s=0;
	int m=0;
	int h=0;
	//메세지 세팅
	Thread thread1;
	Thread thread2;
	Toolkit tookit;
	Button mesageSend;
	Button mesageCancel;
	TextArea mesageArea;
	TextField measage1Field;
	@FXML TextField logtxtID;
	@FXML PasswordField logtxtPassword;
	@FXML Label logAd;
	@FXML TextField logCard;
	@FXML Label logBtn2;
	@FXML Button logBtn1;
	@FXML Button logJoin;
	@FXML Button logsearchID;
	@FXML Button logExit;
	//세트메뉴콤보박스
	ComboBox<String> ramenSet1;
	//세트메뉴 리스트
	private ObservableList<Finalorder>OB=FXCollections.observableArrayList();
	private ObservableList<Finalorder2>OB2=FXCollections.observableArrayList();
	Button btnP[][]=new Button[6][100];
	Button btnM[][]=new Button[6][100];
	Label name[][]=new Label[6][100];
	Label price[][]=new Label[6][100];
	Label amount[][]=new Label[6][100];
	private boolean flag[][]=new boolean[6][100];
	Finalorder finalorder[][]=new Finalorder[6][100];
	int i=0;
	int b=0;
	int sum=0;
	boolean setmenu =false;
	ObservableList<String>moneyWaylist=FXCollections.observableArrayList();//현금결제방식
	ObservableList<String>cardWaylist=FXCollections.observableArrayList();//카드결제방식
	private Member member=null;
	Finalorder2 fi2=null;
	//회원가입창 콤보박스
	ComboBox<String> joinGender;
	ComboBox<Integer> joinYear;
	ComboBox<String> joinMonth;
	ComboBox<String> joinDay;
	ComboBox<String> joinE_mailAdressAuto;
	ComboBox<String> joinSelectTime;
	//회원가입 콤보박스 세팅
	private ObservableList<String>genderList=FXCollections.observableArrayList();//성별
	private ObservableList<Integer>yearList=FXCollections.observableArrayList();//년도
	private ObservableList<String>monthList=FXCollections.observableArrayList();//월
	private ObservableList<String>dayList=FXCollections.observableArrayList();//일
	private ObservableList<String>e_mailList=FXCollections.observableArrayList();//이메일
	private ObservableList<String>chargeList=FXCollections.observableArrayList();//요금
	
	TextField joinName;
	TextField joinPhoenumber;
	TextField joinE_mailID;
	TextField joinE_mailAdressWrite;
	//디비연동 어레이 리스트
	public static ArrayList<Member>db1MemberList = new ArrayList<>();
	//-----------------------------------------------
	//불리언값들
	boolean  identifyId = false; // 아이디 중복체크
	boolean  emailCheck = false; // 이메일 오토//직접입력
	//화면이 끄고켜질때 쓰는 불리언 값
	boolean joinSystemvalue = false; 
	boolean foodStageLabel = true;
	boolean MeasageClick = false;
	boolean AddChargeClick = false;
	boolean measageValue=false;
	//스테이지들
	private Stage stage;
	private Stage foodSystemStage;
	private Stage foodSystemOrder;
	private Stage timeSystem;
	static Stage joinSystem;
	private Stage Measage;
	private Stage AddCharge;
	//음식창
	Label foodSystemOn;
	//회원가입창
	static PasswordField  joinPaword1;
	static PasswordField  joinPaword2;
	static TextField joinID;
	public void setStage(Stage stage) {
		this.stage = stage;
	}
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		logSetting();//로그인창 세팅
		//클라이언트 세팅
	}
///////////////////클라이언트관리
	private void clientServer() {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				socket=new Socket();
	            try {   
	               socket.connect(new InetSocketAddress("192.168.0.230", 5000));
	            } catch (Exception e) {
	            	Platform.runLater(()->{
	            		callAlert("오류:접속실패");
	                 });
	            	try {
						socket.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
	               return;
	            }
	            //데이타 읽기
	            while(true) {
	            	try {
						InputStream is=socket.getInputStream();
						InputStreamReader isr=new InputStreamReader(is);
						BufferedReader br=new BufferedReader(isr);
						String receiveMessage =br.readLine();
							Platform.runLater(()->{
									mesageArea.appendText(receiveMessage+"\n");
							});
					} catch (IOException e) {
						Platform.runLater(()->{
							mesageArea.appendText("서버와 통신이 안됩니다.");
						});
						break;
					}
	            }//end of while
			}
		};
		thread1 = new Thread(runnable);
		thread1.start();
	}
	private void send() {
		//데이타 보내기
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				try {
					OutputStream os = socket.getOutputStream();
					PrintWriter pw = new PrintWriter(os);
					pw.println("5번자리:"+measage1Field.getText()+"\n");
					pw.flush();
				} catch (IOException e) {
					callAlert("오류:메세지를보낼 수 없습니다.");
				}
			}
		};
		thread2 = new Thread(runnable);
		thread2.start();
	}
	
	private void send2() {
		//데이타 보내기
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				try {
					OutputStream os = socket.getOutputStream();
					PrintWriter pw = new PrintWriter(os);
					pw.println("5번자리에서 주문을 시켰습니다.");
					pw.flush();
				} catch (IOException e) {
					callAlert("오류:주문이 들어가지 않았습니다..");
				}
			}
		};
		thread2 = new Thread(runnable);
		thread2.start();
	}
//////루트컨트롤러 관리	
	//로그인세팅
	private void logSetting() {
		logCard.setEditable(false);//카드 비지원
		logExit.setOnAction(e->{AppMain.stage.close();
			;});//Exit눌렀을때 종료
		//--------------로그인버튼눌렀을때
		//로그인 디비
		logBtn1.setOnAction(e->{
			if(MemberDAO.login(logtxtID.getText(), logtxtPassword.getText())==null) {
				AppMain.callAlert("오류:아이디와 비밀번호를확인하세요");
			}else {
				mesageSystem();
				clientServer();
				foodSystem();
				timeSystem();
			}
		});
		//로그인 디비
		logBtn2.setOnMouseClicked(e->{
			if(MemberDAO.login(logtxtID.getText(), logtxtPassword.getText())==null) {
				AppMain.callAlert("오류:아이디와 비밀번호를확인하세요");
			}else {
				mesageSystem();
				clientServer();
				foodSystem();
				timeSystem();
			}
		});
		logtxtPassword.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
					if(MemberDAO.login(logtxtID.getText(), logtxtPassword.getText())==null) {
						AppMain.callAlert("오류:아이디와 비밀번호를확인하세요");
					}else {
						mesageSystem();
						clientServer();
						foodSystem();
						timeSystem();
					}
				}
			}
		});
		//--------------로그인버튼눌렀을때
		logJoin.setOnAction(e->{joinSystem();});//회원가입 창
		logsearchID.setOnAction(e->{callAlert("카운터문의:아이디,비밀번호 분실시 카운터에 문의하세요");});
		moneyWay();//음식창 카드현금 리스트 삽입 한번만하기위해서 여기에 정의
		//foodSystem();
		//timeSystem();
		//gameSystem();
	}
	//결제방식 콤보박스 세팅
	private void moneyWay() {
		moneyWaylist.add("1000원");
		moneyWaylist.add("5000원");
		moneyWaylist.add("10000원");
		moneyWaylist.add("50000원");
		cardWaylist.add("카드결제");
	}
	//Food Stage(음식버튼창)
	private void foodSystem() {
		foodSystemStage=new Stage(StageStyle.UNDECORATED);
		foodSystemStage.setX(750);
		foodSystemStage.setY(0);
		foodSystemStage.initModality(Modality.NONE);
		foodSystemStage.initOwner(stage);
		Parent root =null;
		try{
			root=FXMLLoader.load(getClass().getResource("../view/foodSystem.fxml"));
		}catch(Exception e) {
		}
		Button foodSystem1 =(Button)root.lookup("#foodSystem1");
		ImageView foodSystem2=(ImageView)root.lookup("#foodSystem2");
		foodSystemOn=(Label)root.lookup("#foodSystemOn");
		foodSystemOn.setVisible(true);
		Scene scene = new Scene(root);
		foodSystemStage.setScene(scene);
		foodSystemStage.show();
		AppMain.stage.close();
		foodSystem1.setOnAction(e->{foodSystemOrder();});
		foodSystem2.setOnMouseClicked(e->{foodSystemOrder();});
	}
	//FoodOrderStage(음식 주문창)
	private void foodSystemOrder() {
		if(foodStageLabel) {
			OB.clear();
			foodSystemOn.setVisible(false);
			foodSystemOrder=new Stage(StageStyle.UNDECORATED);
			foodSystemOrder.setX(500);
			foodSystemOrder.setY(150);
			foodSystemOrder.initModality(Modality.NONE);
			foodSystemOrder.initOwner(stage);
			Parent root1 =null;
			foodStageLabel=false;
			try{
				root1=FXMLLoader.load(getClass().getResource("../view/foodSystemOrder.fxml"));
			}catch(Exception e1) {}
			
			//버튼 정의
			Label menuDrink =(Label)root1.lookup("#menuDrink");
			Label menuBop =(Label)root1.lookup("#menuBop");
			Label menuRamen =(Label)root1.lookup("#menuRamen");
			Label menuHotdog =(Label)root1.lookup("#menuHotdog");
			Label menuSnack =(Label)root1.lookup("#menuSnack");
			Label menuSet =(Label)root1.lookup("#menuSet");
			//스크롤페인정의
			ScrollPane menuDrinkSP=(ScrollPane)root1.lookup("#menuDrinkSP");
			ScrollPane menuBopSP=(ScrollPane)root1.lookup("#menuBopSP");
			ScrollPane menuRamenSP=(ScrollPane)root1.lookup("#menuRamenSP");
			ScrollPane menuHotdogSP=(ScrollPane)root1.lookup("#menuHotdogSP");
			ScrollPane menuSnackSP=(ScrollPane)root1.lookup("#menuSnackSP");
			ScrollPane menuSetSP=(ScrollPane)root1.lookup("#menuSetSP");
			//상품주문하기
			Button btnOrder=(Button)root1.lookup("#btnOrder");
			//테이블뷰 정의
			TableView<Finalorder> foodTable =(TableView)root1.lookup("#foodTable");
			TableColumn menuT=foodTable.getColumns().get(0);
			menuT.setCellValueFactory(new PropertyValueFactory<>("menu"));
			menuT.setStyle("-fx-alignment:CENTER;");
			TableColumn priceT=foodTable.getColumns().get(1);
			priceT.setCellValueFactory(new PropertyValueFactory<>("price"));
			priceT.setStyle("-fx-alignment:CENTER;");
			TableColumn amountT=foodTable.getColumns().get(2);
			amountT.setCellValueFactory(new PropertyValueFactory<>("amount"));
			amountT.setStyle("-fx-alignment:CENTER;");
			TableColumn fprice=foodTable.getColumns().get(3);
			fprice.setCellValueFactory(new PropertyValueFactory<>("fprice"));
			TextField notice=(TextField)root1.lookup("#notice");
			//세트메뉴
			menuSet.setOnMouseClicked(e->{
				menuSetSP.setVisible(true);
				menuSnackSP.setVisible(false);
				menuDrinkSP.setVisible(false);
				menuBopSP.setVisible(false);
				menuRamenSP.setVisible(false);
				menuHotdogSP.setVisible(false);
				menuSet.setStyle("-fx-background-color: #FF0000;");
				menuDrink.setStyle("-fx-background-color: #121212;");
				menuBop.setStyle("-fx-background-color:  #121212;");
				menuRamen.setStyle("-fx-background-color: #121212;");
				menuHotdog.setStyle("-fx-background-color: #121212;");
				menuSnack.setStyle("-fx-background-color: #121212;");
			});
			//음료수
			menuDrink.setOnMouseClicked(e->{
				menuSetSP.setVisible(false);
				menuSnackSP.setVisible(false);
				menuDrinkSP.setVisible(true);
				menuBopSP.setVisible(false);
				menuRamenSP.setVisible(false);
				menuHotdogSP.setVisible(false);
				menuDrink.setStyle("-fx-background-color: #FF0000;");
				menuBop.setStyle("-fx-background-color:  #121212;");
				menuRamen.setStyle("-fx-background-color: #121212;");
				menuHotdog.setStyle("-fx-background-color: #121212;");
				menuSnack.setStyle("-fx-background-color: #121212;");
				menuSet.setStyle("-fx-background-color: #121212;");
			});
			//라면
			menuRamen.setOnMouseClicked(e->{
				menuSetSP.setVisible(false);
				menuSnackSP.setVisible(false);
				menuDrinkSP.setVisible(false);
				menuBopSP.setVisible(false);
				menuRamenSP.setVisible(true);
				menuBop.setStyle("-fx-background-color: #121212;");
				menuDrink.setStyle("-fx-background-color: #121212;");
				menuRamen.setStyle("-fx-background-color: #FF0000;");
				menuSnack.setStyle("-fx-background-color: #121212;");
				menuSet.setStyle("-fx-background-color: #121212;");
			});
			//식사류	
			menuBop.setOnMouseClicked(e->{
				menuSetSP.setVisible(false);
				menuSnackSP.setVisible(false);
				menuDrinkSP.setVisible(false);
				menuBopSP.setVisible(true);
				menuRamenSP.setVisible(false);
				menuHotdogSP.setVisible(false);
				menuBop.setStyle("-fx-background-color: #FF0000;");
				menuDrink.setStyle("-fx-background-color: #121212;");
				menuRamen.setStyle("-fx-background-color: #121212;");
				menuHotdog.setStyle("-fx-background-color: #121212;");
				menuSnack.setStyle("-fx-background-color: #121212;");
				menuSet.setStyle("-fx-background-color: #121212;");
			});
			//핫도그
			menuHotdog.setOnMouseClicked(e->{
				menuSetSP.setVisible(false);
				menuSnackSP.setVisible(false);
				menuDrinkSP.setVisible(false);
				menuBopSP.setVisible(false);
				menuRamenSP.setVisible(false);
				menuHotdogSP.setVisible(true);
				menuDrink.setStyle("-fx-background-color: #121212;");
				menuBop.setStyle("-fx-background-color:  #121212;");
				menuRamen.setStyle("-fx-background-color: #121212;");
				menuHotdog.setStyle("-fx-background-color: #FF0000;");
				menuSnack.setStyle("-fx-background-color: #121212;");
				menuSet.setStyle("-fx-background-color: #121212;");
			});
			//간식
			menuSnack.setOnMouseClicked(e->{
				menuSetSP.setVisible(false);
				menuSnackSP.setVisible(true);
				menuDrinkSP.setVisible(false);
				menuBopSP.setVisible(false);
				menuRamenSP.setVisible(false);
				menuHotdogSP.setVisible(false);	
				menuDrink.setStyle("-fx-background-color: #121212;");
				menuBop.setStyle("-fx-background-color:  #121212;");
				menuRamen.setStyle("-fx-background-color: #121212;");
				menuHotdog.setStyle("-fx-background-color: #121212;");
				menuSnack.setStyle("-fx-background-color: #FF0000;");
				menuSet.setStyle("-fx-background-color: #121212;");
			});
			//초기화면
			menuSetSP.setVisible(false);
			menuHotdogSP.setVisible(false);
			menuDrinkSP.setVisible(false);
			menuBopSP.setVisible(false);
			menuRamenSP.setVisible(false);
			menuSnackSP.setVisible(false);
			Scene scene1 = new Scene(root1);
			foodSystemOrder.setScene(scene1);
			foodSystemOrder.show();
			//세트메뉴 버튼
			name[0][0]=(Label)root1.lookup("#name00");//이름
			name[0][1]=(Label)root1.lookup("#name01");
			name[0][2]=(Label)root1.lookup("#name02");
			price[0][0]=(Label)root1.lookup("#price00");//가격
			price[0][1]=(Label)root1.lookup("#price01");
			price[0][2]=(Label)root1.lookup("#price02");
			amount[0][0]=(Label)root1.lookup("#amount00");//양
			amount[0][1]=(Label)root1.lookup("#amount01");
			amount[0][2]=(Label)root1.lookup("#amount02");
			btnM[0][0]= (Button)root1.lookup("#btnM00");//마이너스버튼
			btnM[0][1]= (Button)root1.lookup("#btnM01");
			btnM[0][2]= (Button)root1.lookup("#btnM02");
			btnP[0][0]= (Button)root1.lookup("#btnP00");//플러스버튼
			btnP[0][1]= (Button)root1.lookup("#btnP01");
			btnP[0][2]= (Button)root1.lookup("#btnP02");
			//세트메뉴 플러스 버튼
			btnP[0][0].setOnAction(e->{
				String a=amount[0][0].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[0][0].setText(a2);
		         if(flag[0][0]==true) {
		        	 OB.remove(finalorder[0][0]);
		        	 finalorder[0][0]= new Finalorder(name[0][0].getText(),price[0][0].getText(),amount[0][0].getText());
			         OB.add(finalorder[0][0]);
		         }else {
		        	 finalorder[0][0]= new Finalorder(name[0][0].getText(),price[0][0].getText(),amount[0][0].getText());
		         OB.add(finalorder[0][0]);
		         }
		           flag[0][0]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[0][1].setOnAction(e->{
				String a=amount[0][1].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
					a1=99;
					}else {
					a1++;
					}
				String a2=String.valueOf(a1);
				amount[0][1].setText(a2);
				if(flag[0][1]==true) {
		        	 OB.remove(finalorder[0][1]);
		        	 finalorder[0][1]= new Finalorder(name[0][1].getText(),price[0][1].getText(),amount[0][1].getText());
			         OB.add(finalorder[0][1]);
		         }else {
		        	 finalorder[0][1]= new Finalorder(name[0][1].getText(),price[0][1].getText(),amount[0][1].getText());
		         OB.add(finalorder[0][1]);
		         }
		           flag[0][1]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[0][2].setOnAction(e->{
				String a=amount[0][2].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
					a1=99;
					}else {
					a1++;
					}
				String a2=String.valueOf(a1);
				amount[0][2].setText(a2);
				if(flag[0][2]==true) {
		        	 OB.remove(finalorder[0][2]);
		        	 finalorder[0][2]= new Finalorder(name[0][2].getText(),price[0][2].getText(),amount[0][2].getText());
			         OB.add(finalorder[0][2]);
		         }else {
		        	 finalorder[0][2]= new Finalorder(name[0][2].getText(),price[0][2].getText(),amount[0][2].getText());
		         OB.add(finalorder[0][2]);
		         }
		           flag[0][2]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			//세트메뉴 마이너스버튼
			btnM[0][0].setOnAction(e->{
				String a=amount[0][0].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[0][0].setText(a2);
				if(flag[0][0]==true) {
		        	 OB.remove(finalorder[0][0]);
		        	 finalorder[0][0]= new Finalorder(name[0][0].getText(),price[0][0].getText(),amount[0][0].getText());
			         OB.add(finalorder[0][0]);
		         }else {
		        	 finalorder[0][0]= new Finalorder(name[0][0].getText(),price[0][0].getText(),amount[0][0].getText());
		         OB.add(finalorder[0][0]);
		         }
		           flag[0][0]=true;
		           int c= Integer.parseInt(amount[0][0].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[0][0]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[0][1].setOnAction(e->{
				String a=amount[0][1].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[0][1].setText(a2);
				if(flag[0][1]==true) {
		        	 OB.remove(finalorder[0][1]);
		        	 finalorder[0][1]= new Finalorder(name[0][1].getText(),price[0][1].getText(),amount[0][1].getText());
			         OB.add(finalorder[0][1]);
		         }else {
		        	 finalorder[0][1]= new Finalorder(name[0][1].getText(),price[0][1].getText(),amount[0][1].getText());
		         OB.add(finalorder[0][1]);
		         }
		           flag[0][1]=true;
		           int c= Integer.parseInt(amount[0][1].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[0][1]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[0][2].setOnAction(e->{
				String a=amount[0][2].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[0][2].setText(a2);
				if(flag[0][2]==true) {
		        	 OB.remove(finalorder[0][2]);
		        	 finalorder[0][2]= new Finalorder(name[0][2].getText(),price[0][2].getText(),amount[0][2].getText());
			         OB.add(finalorder[0][2]);
		         }else {
		        	 finalorder[0][2]= new Finalorder(name[0][2].getText(),price[0][2].getText(),amount[0][2].getText());
		         OB.add(finalorder[0][2]);
		         }
				int c= Integer.parseInt(amount[0][2].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[0][2]);
		           }
		           flag[0][2]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			//음료류
			name[1][0]=(Label)root1.lookup("#name10");//이름
			name[1][1]=(Label)root1.lookup("#name11");
			name[1][2]=(Label)root1.lookup("#name12");
			name[1][3]=(Label)root1.lookup("#name13");
			name[1][4]=(Label)root1.lookup("#name14");
			name[1][5]=(Label)root1.lookup("#name15");
			name[1][6]=(Label)root1.lookup("#name16");
			name[1][7]=(Label)root1.lookup("#name17");
			name[1][8]=(Label)root1.lookup("#name18");
			name[1][9]=(Label)root1.lookup("#name19");
			name[1][10]=(Label)root1.lookup("#name110");
			name[1][11]=(Label)root1.lookup("#name111");
			name[1][12]=(Label)root1.lookup("#name112");
			name[1][13]=(Label)root1.lookup("#name113");			
			price[1][0]=(Label)root1.lookup("#price10");//가격
			price[1][1]=(Label)root1.lookup("#price11");
			price[1][2]=(Label)root1.lookup("#price12");
			price[1][3]=(Label)root1.lookup("#price13");
			price[1][4]=(Label)root1.lookup("#price14");
			price[1][5]=(Label)root1.lookup("#price15");
			price[1][6]=(Label)root1.lookup("#price16");
			price[1][7]=(Label)root1.lookup("#price17");
			price[1][8]=(Label)root1.lookup("#price18");
			price[1][9]=(Label)root1.lookup("#price19");
			price[1][10]=(Label)root1.lookup("#price110");
			price[1][11]=(Label)root1.lookup("#price111");
			price[1][12]=(Label)root1.lookup("#price112");
			price[1][13]=(Label)root1.lookup("#price113");
			amount[1][0]=(Label)root1.lookup("#amount10");//양
			amount[1][1]=(Label)root1.lookup("#amount11");
			amount[1][2]=(Label)root1.lookup("#amount12");
			amount[1][3]=(Label)root1.lookup("#amount13");
			amount[1][4]=(Label)root1.lookup("#amount14");
			amount[1][5]=(Label)root1.lookup("#amount15");
			amount[1][6]=(Label)root1.lookup("#amount16");
			amount[1][7]=(Label)root1.lookup("#amount17");
			amount[1][8]=(Label)root1.lookup("#amount18");
			amount[1][9]=(Label)root1.lookup("#amount19");
			amount[1][10]=(Label)root1.lookup("#amount110");
			amount[1][11]=(Label)root1.lookup("#amount111");
			amount[1][12]=(Label)root1.lookup("#amount112");
			amount[1][13]=(Label)root1.lookup("#amount113");
			btnM[1][0]= (Button)root1.lookup("#btnM10");//마이너스버튼
			btnM[1][1]= (Button)root1.lookup("#btnM11");
			btnM[1][2]= (Button)root1.lookup("#btnM12");
			btnM[1][3]= (Button)root1.lookup("#btnM13");
			btnM[1][4]= (Button)root1.lookup("#btnM14");
			btnM[1][5]= (Button)root1.lookup("#btnM15");
			btnM[1][6]= (Button)root1.lookup("#btnM16");
			btnM[1][7]= (Button)root1.lookup("#btnM17");
			btnM[1][8]= (Button)root1.lookup("#btnM18");
			btnM[1][9]= (Button)root1.lookup("#btnM19");
			btnM[1][10]= (Button)root1.lookup("#btnM110");
			btnM[1][11]= (Button)root1.lookup("#btnM111");
			btnM[1][12]= (Button)root1.lookup("#btnM112");
			btnM[1][13]= (Button)root1.lookup("#btnM113");
			btnP[1][0]= (Button)root1.lookup("#btnP10");//플러스버튼
			btnP[1][1]= (Button)root1.lookup("#btnP11");
			btnP[1][2]= (Button)root1.lookup("#btnP12");
			btnP[1][3]= (Button)root1.lookup("#btnP13");
			btnP[1][4]= (Button)root1.lookup("#btnP14");
			btnP[1][5]= (Button)root1.lookup("#btnP15");
			btnP[1][6]= (Button)root1.lookup("#btnP16");
			btnP[1][7]= (Button)root1.lookup("#btnP17");
			btnP[1][8]= (Button)root1.lookup("#btnP18");
			btnP[1][9]= (Button)root1.lookup("#btnP19");
			btnP[1][10]= (Button)root1.lookup("#btnP110");
			btnP[1][11]= (Button)root1.lookup("#btnP111");
			btnP[1][12]= (Button)root1.lookup("#btnP112");
			btnP[1][13]= (Button)root1.lookup("#btnP113");
			//음료류 플러스버튼
			btnP[1][0].setOnAction(e->{
				String a=amount[1][0].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[1][0].setText(a2);
		         if(flag[1][0]==true) {
		        	 OB.remove(finalorder[1][0]);
		        	 finalorder[1][0]= new Finalorder(name[1][0].getText(),price[1][0].getText(),amount[1][0].getText());
			         OB.add(finalorder[1][0]);
		         }else {
		        	 finalorder[1][0]= new Finalorder(name[1][0].getText(),price[1][0].getText(),amount[1][0].getText());
		         OB.add(finalorder[1][0]);
		         }
		           flag[1][0]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[1][1].setOnAction(e->{
				String a=amount[1][1].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[1][1].setText(a2);
		         if(flag[1][1]==true) {
		        	 OB.remove(finalorder[1][1]);
		        	 finalorder[1][1]= new Finalorder(name[1][1].getText(),price[1][1].getText(),amount[1][1].getText());
			         OB.add(finalorder[1][1]);
		         }else {
		        	 finalorder[1][1]= new Finalorder(name[1][1].getText(),price[1][1].getText(),amount[1][1].getText());
		         OB.add(finalorder[1][1]);
		         }
		           flag[1][1]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[1][2].setOnAction(e->{
				String a=amount[1][2].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[1][2].setText(a2);
		         if(flag[1][2]==true) {
		        	 OB.remove(finalorder[1][2]);
		        	 finalorder[1][2]= new Finalorder(name[1][2].getText(),price[1][2].getText(),amount[1][2].getText());
			         OB.add(finalorder[1][2]);
		         }else {
		        	 finalorder[1][2]= new Finalorder(name[1][2].getText(),price[1][2].getText(),amount[1][2].getText());
		         OB.add(finalorder[1][2]);
		         }
		           flag[1][2]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[1][3].setOnAction(e->{
				String a=amount[1][3].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[1][3].setText(a2);
		         if(flag[1][3]==true) {
		        	 OB.remove(finalorder[1][3]);
		        	 finalorder[1][3]= new Finalorder(name[1][3].getText(),price[1][3].getText(),amount[1][3].getText());
			         OB.add(finalorder[1][3]);
		         }else {
		        	 finalorder[1][3]= new Finalorder(name[1][3].getText(),price[1][3].getText(),amount[1][3].getText());
		         OB.add(finalorder[1][3]);
		         }
		           flag[1][3]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[1][4].setOnAction(e->{
				String a=amount[1][4].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[1][4].setText(a2);
		         if(flag[1][4]==true) {
		        	 OB.remove(finalorder[1][4]);
		        	 finalorder[1][4]= new Finalorder(name[1][4].getText(),price[1][4].getText(),amount[1][4].getText());
			         OB.add(finalorder[1][4]);
		         }else {
		        	 finalorder[1][4]= new Finalorder(name[1][4].getText(),price[1][4].getText(),amount[1][4].getText());
		         OB.add(finalorder[1][4]);
		         }
		           flag[1][4]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[1][5].setOnAction(e->{
				String a=amount[1][5].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[1][5].setText(a2);
		         if(flag[1][5]==true) {
		        	 OB.remove(finalorder[1][5]);
		        	 finalorder[1][5]= new Finalorder(name[1][5].getText(),price[1][5].getText(),amount[1][5].getText());
			         OB.add(finalorder[1][5]);
		         }else {
		        	 finalorder[1][5]= new Finalorder(name[1][5].getText(),price[1][5].getText(),amount[1][5].getText());
		         OB.add(finalorder[1][5]);
		         }
		           flag[1][5]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[1][6].setOnAction(e->{
				String a=amount[1][6].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[1][6].setText(a2);
		         if(flag[1][6]==true) {
		        	 OB.remove(finalorder[1][6]);
		        	 finalorder[1][6]= new Finalorder(name[1][6].getText(),price[1][6].getText(),amount[1][6].getText());
			         OB.add(finalorder[1][6]);
		         }else {
		        	 finalorder[1][6]= new Finalorder(name[1][6].getText(),price[1][6].getText(),amount[1][6].getText());
		         OB.add(finalorder[1][6]);
		         }
		           flag[1][6]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[1][7].setOnAction(e->{
				String a=amount[1][7].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[1][7].setText(a2);
		         if(flag[1][7]==true) {
		        	 OB.remove(finalorder[1][7]);
		        	 finalorder[1][7]= new Finalorder(name[1][7].getText(),price[1][7].getText(),amount[1][7].getText());
			         OB.add(finalorder[1][7]);
		         }else {
		        	 finalorder[1][7]= new Finalorder(name[1][7].getText(),price[1][7].getText(),amount[1][7].getText());
		         OB.add(finalorder[1][7]);
		         }
		           flag[1][7]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[1][8].setOnAction(e->{
				String a=amount[1][8].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[1][8].setText(a2);
		         if(flag[1][8]==true) {
		        	 OB.remove(finalorder[1][8]);
		        	 finalorder[1][8]= new Finalorder(name[1][8].getText(),price[1][8].getText(),amount[1][8].getText());
			         OB.add(finalorder[1][8]);
		         }else {
		        	 finalorder[1][8]= new Finalorder(name[1][8].getText(),price[1][8].getText(),amount[1][8].getText());
		         OB.add(finalorder[1][8]);
		         }
		           flag[1][8]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[1][9].setOnAction(e->{
				String a=amount[1][9].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[1][9].setText(a2);
		         if(flag[1][9]==true) {
		        	 OB.remove(finalorder[1][9]);
		        	 finalorder[1][9]= new Finalorder(name[1][9].getText(),price[1][9].getText(),amount[1][9].getText());
			         OB.add(finalorder[1][9]);
		         }else {
		        	 finalorder[1][9]= new Finalorder(name[1][9].getText(),price[1][9].getText(),amount[1][9].getText());
		         OB.add(finalorder[1][9]);
		         }
		           flag[1][9]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[1][10].setOnAction(e->{
				String a=amount[1][10].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[1][10].setText(a2);
		         if(flag[1][10]==true) {
		        	 OB.remove(finalorder[1][10]);
		        	 finalorder[1][10]= new Finalorder(name[1][10].getText(),price[1][10].getText(),amount[1][10].getText());
			         OB.add(finalorder[1][10]);
		         }else {
		        	 finalorder[1][10]= new Finalorder(name[1][10].getText(),price[1][10].getText(),amount[1][10].getText());
		         OB.add(finalorder[1][10]);
		         }
		           flag[1][10]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[1][11].setOnAction(e->{
				String a=amount[1][11].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[1][11].setText(a2);
		         if(flag[1][11]==true) {
		        	 OB.remove(finalorder[1][11]);
		        	 finalorder[1][11]= new Finalorder(name[1][11].getText(),price[1][11].getText(),amount[1][11].getText());
			         OB.add(finalorder[1][11]);
		         }else {
		        	 finalorder[1][11]= new Finalorder(name[1][11].getText(),price[1][11].getText(),amount[1][11].getText());
		         OB.add(finalorder[1][11]);
		         }
		           flag[1][11]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[1][12].setOnAction(e->{
				String a=amount[1][12].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[1][12].setText(a2);
		         if(flag[1][12]==true) {
		        	 OB.remove(finalorder[1][12]);
		        	 finalorder[1][12]= new Finalorder(name[1][12].getText(),price[1][12].getText(),amount[1][12].getText());
			         OB.add(finalorder[1][12]);
		         }else {
		        	 finalorder[1][12]= new Finalorder(name[1][12].getText(),price[1][12].getText(),amount[1][12].getText());
		         OB.add(finalorder[1][12]);
		         }
		           flag[1][12]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[1][13].setOnAction(e->{
				String a=amount[1][13].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[1][13].setText(a2);
		         if(flag[1][13]==true) {
		        	 OB.remove(finalorder[1][13]);
		        	 finalorder[1][13]= new Finalorder(name[1][13].getText(),price[1][13].getText(),amount[1][13].getText());
			         OB.add(finalorder[1][13]);
		         }else {
		        	 finalorder[1][13]= new Finalorder(name[1][13].getText(),price[1][13].getText(),amount[1][13].getText());
		         OB.add(finalorder[1][13]);
		         }
		           flag[1][13]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			//음료류 마이너스버튼
			btnM[1][0].setOnAction(e->{
				String a=amount[1][0].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[1][0].setText(a2);
				if(flag[1][0]==true) {
		        	 OB.remove(finalorder[1][0]);
		        	 finalorder[1][0]= new Finalorder(name[1][0].getText(),price[1][0].getText(),amount[1][0].getText());
			         OB.add(finalorder[1][0]);
		         }else {
		        	 finalorder[1][0]= new Finalorder(name[1][0].getText(),price[1][0].getText(),amount[1][0].getText());
		         OB.add(finalorder[1][0]);
		         }
		           flag[1][0]=true;
		           int c= Integer.parseInt(amount[1][0].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[1][0]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[1][1].setOnAction(e->{
				String a=amount[1][1].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[1][1].setText(a2);
				if(flag[1][1]==true) {
		        	 OB.remove(finalorder[1][1]);
		        	 finalorder[1][1]= new Finalorder(name[1][1].getText(),price[1][1].getText(),amount[1][1].getText());
			         OB.add(finalorder[1][1]);
		         }else {
		        	 finalorder[1][1]= new Finalorder(name[1][1].getText(),price[1][1].getText(),amount[1][1].getText());
		         OB.add(finalorder[1][1]);
		         }
		           flag[1][1]=true;
		           int c= Integer.parseInt(amount[1][1].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[1][1]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[1][2].setOnAction(e->{
				String a=amount[1][2].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[1][2].setText(a2);
				if(flag[1][2]==true) {
		        	 OB.remove(finalorder[1][2]);
		        	 finalorder[1][2]= new Finalorder(name[1][2].getText(),price[1][2].getText(),amount[1][2].getText());
			         OB.add(finalorder[1][2]);
		         }else {
		        	 finalorder[1][2]= new Finalorder(name[1][2].getText(),price[1][2].getText(),amount[1][2].getText());
		         OB.add(finalorder[1][2]);
		         }
		           flag[1][2]=true;
		           int c= Integer.parseInt(amount[1][2].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[1][2]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[1][3].setOnAction(e->{
				String a=amount[1][3].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[1][3].setText(a2);
				if(flag[1][3]==true) {
		        	 OB.remove(finalorder[1][3]);
		        	 finalorder[1][3]= new Finalorder(name[1][3].getText(),price[1][3].getText(),amount[1][3].getText());
			         OB.add(finalorder[1][3]);
		         }else {
		        	 finalorder[1][3]= new Finalorder(name[1][3].getText(),price[1][3].getText(),amount[1][3].getText());
		         OB.add(finalorder[1][3]);
		         }
		           flag[1][3]=true;
		           int c= Integer.parseInt(amount[1][3].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[1][3]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[1][4].setOnAction(e->{
				String a=amount[1][4].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[1][4].setText(a2);
				if(flag[1][4]==true) {
		        	 OB.remove(finalorder[1][4]);
		        	 finalorder[1][4]= new Finalorder(name[1][4].getText(),price[1][4].getText(),amount[1][4].getText());
			         OB.add(finalorder[1][4]);
		         }else {
		        	 finalorder[1][4]= new Finalorder(name[1][4].getText(),price[1][4].getText(),amount[1][4].getText());
		         OB.add(finalorder[1][4]);
		         }
		           flag[1][4]=true;
		           int c= Integer.parseInt(amount[1][4].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[1][4]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[1][5].setOnAction(e->{
				String a=amount[1][5].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[1][5].setText(a2);
				if(flag[1][5]==true) {
		        	 OB.remove(finalorder[1][5]);
		        	 finalorder[1][5]= new Finalorder(name[1][5].getText(),price[1][5].getText(),amount[1][5].getText());
			         OB.add(finalorder[1][5]);
		         }else {
		        	 finalorder[1][5]= new Finalorder(name[1][5].getText(),price[1][5].getText(),amount[1][5].getText());
		         OB.add(finalorder[1][5]);
		         }
		           flag[1][5]=true;
		           int c= Integer.parseInt(amount[1][5].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[1][5]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[1][6].setOnAction(e->{
				String a=amount[1][6].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[1][6].setText(a2);
				if(flag[1][6]==true) {
		        	 OB.remove(finalorder[1][6]);
		        	 finalorder[1][6]= new Finalorder(name[1][6].getText(),price[1][6].getText(),amount[1][6].getText());
			         OB.add(finalorder[1][6]);
		         }else {
		        	 finalorder[1][6]= new Finalorder(name[1][6].getText(),price[1][6].getText(),amount[1][6].getText());
		         OB.add(finalorder[1][6]);
		         }
		           flag[1][6]=true;
		           int c= Integer.parseInt(amount[1][6].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[1][6]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[1][7].setOnAction(e->{
				String a=amount[1][7].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[1][7].setText(a2);
				if(flag[1][7]==true) {
		        	 OB.remove(finalorder[1][7]);
		        	 finalorder[1][7]= new Finalorder(name[1][7].getText(),price[1][7].getText(),amount[1][7].getText());
			         OB.add(finalorder[1][7]);
		         }else {
		        	 finalorder[1][7]= new Finalorder(name[1][7].getText(),price[1][7].getText(),amount[1][7].getText());
		         OB.add(finalorder[1][7]);
		         }
		           flag[1][7]=true;
		           int c= Integer.parseInt(amount[1][7].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[1][7]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[1][8].setOnAction(e->{
				String a=amount[1][8].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[1][8].setText(a2);
				if(flag[1][8]==true) {
		        	 OB.remove(finalorder[1][8]);
		        	 finalorder[1][8]= new Finalorder(name[1][8].getText(),price[1][8].getText(),amount[1][8].getText());
			         OB.add(finalorder[1][8]);
		         }else {
		        	 finalorder[1][8]= new Finalorder(name[1][8].getText(),price[1][8].getText(),amount[1][8].getText());
		         OB.add(finalorder[1][8]);
		         }
		           flag[1][8]=true;
		           int c= Integer.parseInt(amount[1][8].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[1][8]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[1][9].setOnAction(e->{
				String a=amount[1][9].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[1][9].setText(a2);
				if(flag[1][9]==true) {
		        	 OB.remove(finalorder[1][9]);
		        	 finalorder[1][9]= new Finalorder(name[1][9].getText(),price[1][9].getText(),amount[1][9].getText());
			         OB.add(finalorder[1][9]);
		         }else {
		        	 finalorder[1][9]= new Finalorder(name[1][9].getText(),price[1][9].getText(),amount[1][9].getText());
		         OB.add(finalorder[1][9]);
		         }
		           flag[1][9]=true;
		           int c= Integer.parseInt(amount[1][9].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[1][9]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[1][10].setOnAction(e->{
				String a=amount[1][10].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[1][10].setText(a2);
				if(flag[1][10]==true) {
		        	 OB.remove(finalorder[1][10]);
		        	 finalorder[1][10]= new Finalorder(name[1][10].getText(),price[1][10].getText(),amount[1][10].getText());
			         OB.add(finalorder[1][10]);
		         }else {
		        	 finalorder[1][10]= new Finalorder(name[1][10].getText(),price[1][10].getText(),amount[1][10].getText());
		         OB.add(finalorder[1][10]);
		         }
		           flag[1][10]=true;
		           int c= Integer.parseInt(amount[1][10].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[1][10]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[1][11].setOnAction(e->{
				String a=amount[1][11].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[1][11].setText(a2);
				if(flag[1][11]==true) {
		        	 OB.remove(finalorder[1][11]);
		        	 finalorder[1][11]= new Finalorder(name[1][11].getText(),price[1][11].getText(),amount[1][11].getText());
			         OB.add(finalorder[1][11]);
		         }else {
		        	 finalorder[1][11]= new Finalorder(name[1][11].getText(),price[1][11].getText(),amount[1][11].getText());
		         OB.add(finalorder[1][11]);
		         }
		           flag[1][11]=true;
		           int c= Integer.parseInt(amount[1][11].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[1][11]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[1][12].setOnAction(e->{
				String a=amount[1][12].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[1][12].setText(a2);
				if(flag[1][12]==true) {
		        	 OB.remove(finalorder[1][12]);
		        	 finalorder[1][12]= new Finalorder(name[1][12].getText(),price[1][12].getText(),amount[1][12].getText());
			         OB.add(finalorder[1][12]);
		         }else {
		        	 finalorder[1][12]= new Finalorder(name[1][12].getText(),price[1][12].getText(),amount[1][12].getText());
		         OB.add(finalorder[1][12]);
		         }
		           flag[1][12]=true;
		           int c= Integer.parseInt(amount[1][12].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[1][12]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[1][13].setOnAction(e->{
				String a=amount[1][13].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[1][13].setText(a2);
				if(flag[1][13]==true) {
		        	 OB.remove(finalorder[1][13]);
		        	 finalorder[1][13]= new Finalorder(name[1][13].getText(),price[1][13].getText(),amount[1][13].getText());
			         OB.add(finalorder[1][13]);
		         }else {
		        	 finalorder[1][13]= new Finalorder(name[1][13].getText(),price[1][13].getText(),amount[1][13].getText());
		         OB.add(finalorder[1][13]);
		         }
		           flag[1][13]=true;
		           int c= Integer.parseInt(amount[1][13].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[1][13]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			//라면류
			name[2][0]=(Label)root1.lookup("#name20");//이름
			name[2][1]=(Label)root1.lookup("#name21");
			name[2][2]=(Label)root1.lookup("#name22");
			name[2][3]=(Label)root1.lookup("#name23");
			name[2][4]=(Label)root1.lookup("#name24");
			name[2][5]=(Label)root1.lookup("#name25");
			name[2][6]=(Label)root1.lookup("#name26");
			name[2][7]=(Label)root1.lookup("#name27");	
			price[2][0]=(Label)root1.lookup("#price20");//가격
			price[2][1]=(Label)root1.lookup("#price21");
			price[2][2]=(Label)root1.lookup("#price22");
			price[2][3]=(Label)root1.lookup("#price23");
			price[2][4]=(Label)root1.lookup("#price24");
			price[2][5]=(Label)root1.lookup("#price25");
			price[2][6]=(Label)root1.lookup("#price26");
			price[2][7]=(Label)root1.lookup("#price27");
			amount[2][0]=(Label)root1.lookup("#amount20");//양
			amount[2][1]=(Label)root1.lookup("#amount21");
			amount[2][2]=(Label)root1.lookup("#amount22");
			amount[2][3]=(Label)root1.lookup("#amount23");
			amount[2][4]=(Label)root1.lookup("#amount24");
			amount[2][5]=(Label)root1.lookup("#amount25");
			amount[2][6]=(Label)root1.lookup("#amount26");
			amount[2][7]=(Label)root1.lookup("#amount27");
			btnM[2][0]= (Button)root1.lookup("#btnM20");//마이너스버튼
			btnM[2][1]= (Button)root1.lookup("#btnM21");
			btnM[2][2]= (Button)root1.lookup("#btnM22");
			btnM[2][3]= (Button)root1.lookup("#btnM23");
			btnM[2][4]= (Button)root1.lookup("#btnM24");
			btnM[2][5]= (Button)root1.lookup("#btnM25");
			btnM[2][6]= (Button)root1.lookup("#btnM26");
			btnM[2][7]= (Button)root1.lookup("#btnM27");
			btnP[2][0]= (Button)root1.lookup("#btnP20");//플러스버튼
			btnP[2][1]= (Button)root1.lookup("#btnP21");
			btnP[2][2]= (Button)root1.lookup("#btnP22");
			btnP[2][3]= (Button)root1.lookup("#btnP23");
			btnP[2][4]= (Button)root1.lookup("#btnP24");
			btnP[2][5]= (Button)root1.lookup("#btnP25");
			btnP[2][6]= (Button)root1.lookup("#btnP26");
			btnP[2][7]= (Button)root1.lookup("#btnP27");
			//라면류플러스버튼
			btnP[2][0].setOnAction(e->{
				String a=amount[2][0].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[2][0].setText(a2);
		         if(flag[2][0]==true) {
		        	 OB.remove(finalorder[2][0]);
		        	 finalorder[2][0]= new Finalorder(name[2][0].getText(),price[2][0].getText(),amount[2][0].getText());
			         OB.add(finalorder[2][0]);
		         }else {
		        	 finalorder[2][0]= new Finalorder(name[2][0].getText(),price[2][0].getText(),amount[2][0].getText());
		         OB.add(finalorder[2][0]);
		         }
		           flag[2][0]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[2][1].setOnAction(e->{
				String a=amount[2][1].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[2][1].setText(a2);
		         if(flag[2][1]==true) {
		        	 OB.remove(finalorder[2][1]);
		        	 finalorder[2][1]= new Finalorder(name[2][1].getText(),price[2][1].getText(),amount[2][1].getText());
			         OB.add(finalorder[2][1]);
		         }else {
		        	 finalorder[2][1]= new Finalorder(name[2][1].getText(),price[2][1].getText(),amount[2][1].getText());
		         OB.add(finalorder[2][1]);
		         }
		           flag[2][1]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[2][2].setOnAction(e->{
				String a=amount[2][2].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[2][2].setText(a2);
		         if(flag[2][2]==true) {
		        	 OB.remove(finalorder[2][2]);
		        	 finalorder[2][2]= new Finalorder(name[2][2].getText(),price[2][2].getText(),amount[2][2].getText());
			         OB.add(finalorder[2][2]);
		         }else {
		        	 finalorder[2][2]= new Finalorder(name[2][2].getText(),price[2][2].getText(),amount[2][2].getText());
		         OB.add(finalorder[2][2]);
		         }
		           flag[2][2]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[2][3].setOnAction(e->{
				String a=amount[2][3].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[2][3].setText(a2);
		         if(flag[2][3]==true) {
		        	 OB.remove(finalorder[2][3]);
		        	 finalorder[2][3]= new Finalorder(name[2][3].getText(),price[2][3].getText(),amount[2][3].getText());
			         OB.add(finalorder[2][3]);
		         }else {
		        	 finalorder[2][3]= new Finalorder(name[2][3].getText(),price[2][3].getText(),amount[2][3].getText());
		         OB.add(finalorder[2][3]);
		         }
		           flag[2][3]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[2][4].setOnAction(e->{
				String a=amount[2][4].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[2][4].setText(a2);
		         if(flag[2][4]==true) {
		        	 OB.remove(finalorder[2][4]);
		        	 finalorder[2][4]= new Finalorder(name[2][4].getText(),price[2][4].getText(),amount[2][4].getText());
			         OB.add(finalorder[2][4]);
		         }else {
		        	 finalorder[2][4]= new Finalorder(name[2][4].getText(),price[2][4].getText(),amount[2][4].getText());
		         OB.add(finalorder[2][4]);
		         }
		           flag[2][4]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[2][5].setOnAction(e->{
				String a=amount[2][5].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[2][5].setText(a2);
		         if(flag[2][5]==true) {
		        	 OB.remove(finalorder[2][5]);
		        	 finalorder[2][5]= new Finalorder(name[2][5].getText(),price[2][5].getText(),amount[2][5].getText());
			         OB.add(finalorder[2][5]);
		         }else {
		        	 finalorder[2][5]= new Finalorder(name[2][5].getText(),price[2][5].getText(),amount[2][5].getText());
		         OB.add(finalorder[2][5]);
		         }
		           flag[2][5]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[2][6].setOnAction(e->{
				String a=amount[2][6].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[2][6].setText(a2);
		         if(flag[2][6]==true) {
		        	 OB.remove(finalorder[2][6]);
		        	 finalorder[2][6]= new Finalorder(name[2][6].getText(),price[2][6].getText(),amount[2][6].getText());
			         OB.add(finalorder[2][6]);
		         }else {
		        	 finalorder[2][6]= new Finalorder(name[2][6].getText(),price[2][6].getText(),amount[2][6].getText());
		         OB.add(finalorder[2][6]);
		         }
		           flag[2][6]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[2][7].setOnAction(e->{
				String a=amount[2][7].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[2][7].setText(a2);
		         if(flag[2][7]==true) {
		        	 OB.remove(finalorder[2][7]);
		        	 finalorder[2][7]= new Finalorder(name[2][7].getText(),price[2][7].getText(),amount[2][7].getText());
			         OB.add(finalorder[2][7]);
		         }else {
		        	 finalorder[2][7]= new Finalorder(name[2][7].getText(),price[2][7].getText(),amount[2][7].getText());
		         OB.add(finalorder[2][7]);
		         }
		           flag[2][7]=true;
		           foodTable.setItems(OB);     
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			//라면류 마이너스 버튼
			btnM[2][0].setOnAction(e->{
				String a=amount[2][0].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[2][0].setText(a2);
				if(flag[2][0]==true) {
		        	 OB.remove(finalorder[2][0]);
		        	 finalorder[2][0]= new Finalorder(name[2][0].getText(),price[2][0].getText(),amount[2][0].getText());
			         OB.add(finalorder[2][0]);
		         }else {
		        	 finalorder[2][0]= new Finalorder(name[2][0].getText(),price[2][0].getText(),amount[2][0].getText());
		         OB.add(finalorder[2][0]);
		         }
		           flag[2][0]=true;
		           int c= Integer.parseInt(amount[2][0].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[2][0]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[2][1].setOnAction(e->{
				String a=amount[2][1].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[2][1].setText(a2);
				if(flag[2][1]==true) {
		        	 OB.remove(finalorder[2][1]);
		        	 finalorder[2][1]= new Finalorder(name[2][1].getText(),price[2][1].getText(),amount[2][1].getText());
			         OB.add(finalorder[2][1]);
		         }else {
		        	 finalorder[2][1]= new Finalorder(name[2][1].getText(),price[2][1].getText(),amount[2][1].getText());
		         OB.add(finalorder[2][1]);
		         }
		           flag[2][1]=true;
		           int c= Integer.parseInt(amount[2][1].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[2][1]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[2][2].setOnAction(e->{
				String a=amount[2][2].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[2][2].setText(a2);
				if(flag[2][2]==true) {
		        	 OB.remove(finalorder[2][2]);
		        	 finalorder[2][2]= new Finalorder(name[2][2].getText(),price[2][2].getText(),amount[2][2].getText());
			         OB.add(finalorder[2][2]);
		         }else {
		        	 finalorder[2][2]= new Finalorder(name[2][2].getText(),price[2][2].getText(),amount[2][2].getText());
		         OB.add(finalorder[2][2]);
		         }
		           flag[2][2]=true;
		           int c= Integer.parseInt(amount[2][2].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[2][2]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[2][3].setOnAction(e->{
				String a=amount[2][3].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[2][3].setText(a2);
				if(flag[2][3]==true) {
		        	 OB.remove(finalorder[2][3]);
		        	 finalorder[2][3]= new Finalorder(name[2][3].getText(),price[2][3].getText(),amount[2][3].getText());
			         OB.add(finalorder[2][3]);
		         }else {
		        	 finalorder[2][3]= new Finalorder(name[2][3].getText(),price[2][3].getText(),amount[2][3].getText());
		         OB.add(finalorder[2][3]);
		         }
		           flag[2][3]=true;
		           int c= Integer.parseInt(amount[2][3].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[2][3]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[2][4].setOnAction(e->{
				String a=amount[2][4].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[2][4].setText(a2);
				if(flag[2][4]==true) {
		        	 OB.remove(finalorder[2][4]);
		        	 finalorder[2][4]= new Finalorder(name[2][4].getText(),price[2][4].getText(),amount[2][4].getText());
			         OB.add(finalorder[2][4]);
		         }else {
		        	 finalorder[2][4]= new Finalorder(name[2][4].getText(),price[2][4].getText(),amount[2][4].getText());
		         OB.add(finalorder[2][4]);
		         }
		           flag[2][4]=true;
		           int c= Integer.parseInt(amount[2][4].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[2][4]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[2][5].setOnAction(e->{
				String a=amount[2][5].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[2][5].setText(a2);
				if(flag[2][5]==true) {
		        	 OB.remove(finalorder[2][5]);
		        	 finalorder[2][5]= new Finalorder(name[2][5].getText(),price[2][5].getText(),amount[2][5].getText());
			         OB.add(finalorder[2][5]);
		         }else {
		        	 finalorder[2][5]= new Finalorder(name[2][5].getText(),price[2][5].getText(),amount[2][5].getText());
		         OB.add(finalorder[2][5]);
		         }
		           flag[2][5]=true;
		           int c= Integer.parseInt(amount[2][5].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[2][5]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[2][6].setOnAction(e->{
				String a=amount[2][6].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[2][6].setText(a2);
				if(flag[2][6]==true) {
		        	 OB.remove(finalorder[2][6]);
		        	 finalorder[2][6]= new Finalorder(name[2][6].getText(),price[2][6].getText(),amount[2][6].getText());
			         OB.add(finalorder[2][6]);
		         }else {
		        	 finalorder[2][6]= new Finalorder(name[2][6].getText(),price[2][6].getText(),amount[2][6].getText());
		         OB.add(finalorder[2][6]);
		         }
		           flag[2][6]=true;
		           int c= Integer.parseInt(amount[2][6].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[2][6]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[2][7].setOnAction(e->{
				String a=amount[2][7].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[2][7].setText(a2);
				if(flag[2][7]==true) {
		        	 OB.remove(finalorder[2][7]);
		        	 finalorder[2][7]= new Finalorder(name[2][7].getText(),price[2][7].getText(),amount[2][7].getText());
			         OB.add(finalorder[2][7]);
		         }else {
		        	 finalorder[2][7]= new Finalorder(name[2][7].getText(),price[2][7].getText(),amount[2][7].getText());
		         OB.add(finalorder[2][7]);
		         }
		           flag[2][7]=true;
		           int c= Integer.parseInt(amount[2][7].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[2][7]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			//식사류
			name[3][0]=(Label)root1.lookup("#name30");//이름
			name[3][1]=(Label)root1.lookup("#name31");
			name[3][2]=(Label)root1.lookup("#name32");
			name[3][3]=(Label)root1.lookup("#name33");
			name[3][4]=(Label)root1.lookup("#name34");
			name[3][5]=(Label)root1.lookup("#name35");
			name[3][6]=(Label)root1.lookup("#name36");
			price[3][0]=(Label)root1.lookup("#price30");//가격
			price[3][1]=(Label)root1.lookup("#price31");
			price[3][2]=(Label)root1.lookup("#price32");
			price[3][3]=(Label)root1.lookup("#price33");
			price[3][4]=(Label)root1.lookup("#price34");
			price[3][5]=(Label)root1.lookup("#price35");
			price[3][6]=(Label)root1.lookup("#price36");
			amount[3][0]=(Label)root1.lookup("#amount30");//양
			amount[3][1]=(Label)root1.lookup("#amount31");
			amount[3][2]=(Label)root1.lookup("#amount32");
			amount[3][3]=(Label)root1.lookup("#amount33");
			amount[3][4]=(Label)root1.lookup("#amount34");
			amount[3][5]=(Label)root1.lookup("#amount35");
			amount[3][6]=(Label)root1.lookup("#amount36");
			btnM[3][0]= (Button)root1.lookup("#btnM30");//마이너스버튼
			btnM[3][1]= (Button)root1.lookup("#btnM31");
			btnM[3][2]= (Button)root1.lookup("#btnM32");
			btnM[3][3]= (Button)root1.lookup("#btnM33");
			btnM[3][4]= (Button)root1.lookup("#btnM34");
			btnM[3][5]= (Button)root1.lookup("#btnM35");
			btnM[3][6]= (Button)root1.lookup("#btnM36");			
			btnP[3][0]= (Button)root1.lookup("#btnP30");//플러스버튼
			btnP[3][1]= (Button)root1.lookup("#btnP31");
			btnP[3][2]= (Button)root1.lookup("#btnP32");
			btnP[3][3]= (Button)root1.lookup("#btnP33");
			btnP[3][4]= (Button)root1.lookup("#btnP34");
			btnP[3][5]= (Button)root1.lookup("#btnP35");
			btnP[3][6]= (Button)root1.lookup("#btnP36");
			//식사류 플러스 버튼
			btnP[3][0].setOnAction(e->{
				String a=amount[3][0].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[3][0].setText(a2);
		         if(flag[3][0]==true) {
		        	 OB.remove(finalorder[3][0]);
		        	 finalorder[3][0]= new Finalorder(name[3][0].getText(),price[3][0].getText(),amount[3][0].getText());
			         OB.add(finalorder[3][0]);
		         }else {
		        	 finalorder[3][0]= new Finalorder(name[3][0].getText(),price[3][0].getText(),amount[3][0].getText());
		         OB.add(finalorder[3][0]);
		         }
		           flag[3][0]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[3][1].setOnAction(e->{
				String a=amount[3][1].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[3][1].setText(a2);
		         if(flag[3][1]==true) {
		        	 OB.remove(finalorder[3][1]);
		        	 finalorder[3][1]= new Finalorder(name[3][1].getText(),price[3][1].getText(),amount[3][1].getText());
			         OB.add(finalorder[3][1]);
		         }else {
		        	 finalorder[3][1]= new Finalorder(name[3][1].getText(),price[3][1].getText(),amount[3][1].getText());
		         OB.add(finalorder[3][1]);
		         }
		           flag[3][1]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[3][2].setOnAction(e->{
				String a=amount[3][2].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[3][2].setText(a2);
		         if(flag[3][2]==true) {
		        	 OB.remove(finalorder[3][2]);
		        	 finalorder[3][2]= new Finalorder(name[3][2].getText(),price[3][2].getText(),amount[3][2].getText());
			         OB.add(finalorder[3][2]);
		         }else {
		        	 finalorder[3][2]= new Finalorder(name[3][2].getText(),price[3][2].getText(),amount[3][2].getText());
		         OB.add(finalorder[3][2]);
		         }
		           flag[3][2]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[3][3].setOnAction(e->{
				String a=amount[3][3].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[3][3].setText(a2);
		         if(flag[3][3]==true) {
		        	 OB.remove(finalorder[3][3]);
		        	 finalorder[3][3]= new Finalorder(name[3][3].getText(),price[3][3].getText(),amount[3][3].getText());
			         OB.add(finalorder[3][3]);
		         }else {
		        	 finalorder[3][3]= new Finalorder(name[3][3].getText(),price[3][3].getText(),amount[3][3].getText());
		         OB.add(finalorder[3][3]);
		         }
		           flag[3][3]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[3][4].setOnAction(e->{
				String a=amount[3][4].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[3][4].setText(a2);
		         if(flag[3][4]==true) {
		        	 OB.remove(finalorder[3][4]);
		        	 finalorder[3][4]= new Finalorder(name[3][4].getText(),price[3][4].getText(),amount[3][4].getText());
			         OB.add(finalorder[3][4]);
		         }else {
		        	 finalorder[3][4]= new Finalorder(name[3][4].getText(),price[3][4].getText(),amount[3][4].getText());
		         OB.add(finalorder[3][4]);
		         }
		           flag[3][4]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[3][5].setOnAction(e->{
				String a=amount[3][5].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[3][5].setText(a2);
		         if(flag[3][5]==true) {
		        	 OB.remove(finalorder[3][5]);
		        	 finalorder[3][5]= new Finalorder(name[3][5].getText(),price[3][5].getText(),amount[3][5].getText());
			         OB.add(finalorder[3][5]);
		         }else {
		        	 finalorder[3][5]= new Finalorder(name[3][5].getText(),price[3][5].getText(),amount[3][5].getText());
		         OB.add(finalorder[3][5]);
		         }
		           flag[3][5]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[3][6].setOnAction(e->{
				String a=amount[3][6].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[3][6].setText(a2);
		         if(flag[3][6]==true) {
		        	 OB.remove(finalorder[3][6]);
		        	 finalorder[3][6]= new Finalorder(name[3][6].getText(),price[3][6].getText(),amount[3][6].getText());
			         OB.add(finalorder[3][6]);
		         }else {
		        	 finalorder[3][6]= new Finalorder(name[3][6].getText(),price[3][6].getText(),amount[3][6].getText());
		         OB.add(finalorder[3][6]);
		         }
		           flag[3][6]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			//식사류 마이너스버튼
			btnM[3][0].setOnAction(e->{
				String a=amount[3][0].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[3][0].setText(a2);
				if(flag[3][0]==true) {
		        	 OB.remove(finalorder[3][0]);
		        	 finalorder[3][0]= new Finalorder(name[3][0].getText(),price[3][0].getText(),amount[3][0].getText());
			         OB.add(finalorder[3][0]);
		         }else {
		        	 finalorder[3][0]= new Finalorder(name[3][0].getText(),price[3][0].getText(),amount[3][0].getText());
		         OB.add(finalorder[3][0]);
		         }
		           flag[3][0]=true;
		           int c= Integer.parseInt(amount[3][0].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[3][0]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[3][1].setOnAction(e->{
				String a=amount[3][1].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[3][1].setText(a2);
				if(flag[3][1]==true) {
		        	 OB.remove(finalorder[3][1]);
		        	 finalorder[3][1]= new Finalorder(name[3][1].getText(),price[3][1].getText(),amount[3][1].getText());
			         OB.add(finalorder[3][1]);
		         }else {
		        	 finalorder[3][1]= new Finalorder(name[3][1].getText(),price[3][1].getText(),amount[3][1].getText());
		         OB.add(finalorder[3][1]);
		         }
		           flag[3][1]=true;
		           int c= Integer.parseInt(amount[3][1].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[3][1]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[3][2].setOnAction(e->{
				String a=amount[3][2].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[3][2].setText(a2);
				if(flag[3][2]==true) {
		        	 OB.remove(finalorder[3][2]);
		        	 finalorder[3][2]= new Finalorder(name[3][2].getText(),price[3][2].getText(),amount[3][2].getText());
			         OB.add(finalorder[3][2]);
		         }else {
		        	 finalorder[3][2]= new Finalorder(name[3][2].getText(),price[3][2].getText(),amount[3][2].getText());
		         OB.add(finalorder[3][2]);
		         }
		           flag[3][2]=true;
		           int c= Integer.parseInt(amount[3][2].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[3][2]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[3][3].setOnAction(e->{
				String a=amount[3][3].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[3][3].setText(a2);
				if(flag[3][3]==true) {
		        	 OB.remove(finalorder[3][3]);
		        	 finalorder[3][3]= new Finalorder(name[3][3].getText(),price[3][3].getText(),amount[3][3].getText());
			         OB.add(finalorder[3][3]);
		         }else {
		        	 finalorder[3][3]= new Finalorder(name[3][3].getText(),price[3][3].getText(),amount[3][3].getText());
		         OB.add(finalorder[3][3]);
		         }
		           flag[3][3]=true;
		           int c= Integer.parseInt(amount[3][3].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[3][3]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[3][4].setOnAction(e->{
				String a=amount[3][4].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[3][4].setText(a2);
				if(flag[3][4]==true) {
		        	 OB.remove(finalorder[3][4]);
		        	 finalorder[3][4]= new Finalorder(name[3][4].getText(),price[3][4].getText(),amount[3][4].getText());
			         OB.add(finalorder[3][4]);
		         }else {
		        	 finalorder[3][4]= new Finalorder(name[3][4].getText(),price[3][4].getText(),amount[3][4].getText());
		         OB.add(finalorder[3][4]);
		         }
		           flag[3][4]=true;
		           int c= Integer.parseInt(amount[3][4].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[3][4]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[3][5].setOnAction(e->{
				String a=amount[3][5].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[3][5].setText(a2);
				if(flag[3][5]==true) {
		        	 OB.remove(finalorder[3][5]);
		        	 finalorder[3][5]= new Finalorder(name[3][5].getText(),price[3][5].getText(),amount[3][5].getText());
			         OB.add(finalorder[3][5]);
		         }else {
		        	 finalorder[3][5]= new Finalorder(name[3][5].getText(),price[3][5].getText(),amount[3][5].getText());
		         OB.add(finalorder[3][5]);
		         }
		           flag[3][5]=true;
		           int c= Integer.parseInt(amount[3][5].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[3][5]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[3][6].setOnAction(e->{
				String a=amount[3][6].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[3][6].setText(a2);
				if(flag[3][6]==true) {
		        	 OB.remove(finalorder[3][6]);
		        	 finalorder[3][6]= new Finalorder(name[3][6].getText(),price[3][6].getText(),amount[3][6].getText());
			         OB.add(finalorder[3][6]);
		         }else {
		        	 finalorder[3][6]= new Finalorder(name[3][6].getText(),price[3][6].getText(),amount[3][6].getText());
		         OB.add(finalorder[3][6]);
		         }
		           flag[3][6]=true;
		           int c= Integer.parseInt(amount[3][6].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[3][6]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			//핫도그
			name[4][0]=(Label)root1.lookup("#name40");//이름
			name[4][1]=(Label)root1.lookup("#name41");
			name[4][2]=(Label)root1.lookup("#name42");
			name[4][3]=(Label)root1.lookup("#name43");
			name[4][4]=(Label)root1.lookup("#name44");
			name[4][5]=(Label)root1.lookup("#name45");
			price[4][0]=(Label)root1.lookup("#price40");//가격
			price[4][1]=(Label)root1.lookup("#price41");
			price[4][2]=(Label)root1.lookup("#price42");
			price[4][3]=(Label)root1.lookup("#price43");
			price[4][4]=(Label)root1.lookup("#price44");
			price[4][5]=(Label)root1.lookup("#price45");
			amount[4][0]=(Label)root1.lookup("#amount40");//양
			amount[4][1]=(Label)root1.lookup("#amount41");
			amount[4][2]=(Label)root1.lookup("#amount42");
			amount[4][3]=(Label)root1.lookup("#amount43");
			amount[4][4]=(Label)root1.lookup("#amount44");
			amount[4][5]=(Label)root1.lookup("#amount45");
			btnM[4][0]= (Button)root1.lookup("#btnM40");//마이너스버튼
			btnM[4][1]= (Button)root1.lookup("#btnM41");
			btnM[4][2]= (Button)root1.lookup("#btnM42");
			btnM[4][3]= (Button)root1.lookup("#btnM43");
			btnM[4][4]= (Button)root1.lookup("#btnM44");
			btnM[4][5]= (Button)root1.lookup("#btnM45");
			btnP[4][0]= (Button)root1.lookup("#btnP40");//플러스버튼
			btnP[4][1]= (Button)root1.lookup("#btnP41");
			btnP[4][2]= (Button)root1.lookup("#btnP42");
			btnP[4][3]= (Button)root1.lookup("#btnP43");
			btnP[4][4]= (Button)root1.lookup("#btnP44");
			btnP[4][5]= (Button)root1.lookup("#btnP45");
			//핫도그 플러스버튼
			btnP[4][0].setOnAction(e->{
				String a=amount[4][0].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[4][0].setText(a2);
		         if(flag[4][0]==true) {
		        	 OB.remove(finalorder[4][0]);
		        	 finalorder[4][0]= new Finalorder(name[4][0].getText(),price[4][0].getText(),amount[4][0].getText());
			         OB.add(finalorder[4][0]);
		         }else {
		        	 finalorder[4][0]= new Finalorder(name[4][0].getText(),price[4][0].getText(),amount[4][0].getText());
		         OB.add(finalorder[4][0]);
		         }
		           flag[4][0]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[4][1].setOnAction(e->{
				String a=amount[4][1].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[4][1].setText(a2);
		         if(flag[4][1]==true) {
		        	 OB.remove(finalorder[4][1]);
		        	 finalorder[4][1]= new Finalorder(name[4][1].getText(),price[4][1].getText(),amount[4][1].getText());
			         OB.add(finalorder[4][1]);
		         }else {
		        	 finalorder[4][1]= new Finalorder(name[4][1].getText(),price[4][1].getText(),amount[4][1].getText());
		         OB.add(finalorder[4][1]);
		         }
		           flag[4][1]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[4][2].setOnAction(e->{
				String a=amount[4][2].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[4][2].setText(a2);
		         if(flag[4][2]==true) {
		        	 OB.remove(finalorder[4][2]);
		        	 finalorder[4][2]= new Finalorder(name[4][2].getText(),price[4][2].getText(),amount[4][2].getText());
			         OB.add(finalorder[4][2]);
		         }else {
		        	 finalorder[4][2]= new Finalorder(name[4][2].getText(),price[4][2].getText(),amount[4][2].getText());
		         OB.add(finalorder[4][2]);
		         }
		           flag[4][2]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[4][3].setOnAction(e->{
				String a=amount[4][3].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[4][3].setText(a2);
		         if(flag[4][3]==true) {
		        	 OB.remove(finalorder[4][3]);
		        	 finalorder[4][3]= new Finalorder(name[4][3].getText(),price[4][3].getText(),amount[4][3].getText());
			         OB.add(finalorder[4][3]);
		         }else {
		        	 finalorder[4][3]= new Finalorder(name[4][3].getText(),price[4][3].getText(),amount[4][3].getText());
		         OB.add(finalorder[4][3]);
		         }
		           flag[4][3]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[4][4].setOnAction(e->{
				String a=amount[4][4].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[4][4].setText(a2);
		         if(flag[4][4]==true) {
		        	 OB.remove(finalorder[4][4]);
		        	 finalorder[4][4]= new Finalorder(name[4][4].getText(),price[4][4].getText(),amount[4][4].getText());
			         OB.add(finalorder[4][4]);
		         }else {
		        	 finalorder[4][4]= new Finalorder(name[4][4].getText(),price[4][4].getText(),amount[4][4].getText());
		         OB.add(finalorder[4][4]);
		         }
		           flag[4][4]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[4][5].setOnAction(e->{
				String a=amount[4][5].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[4][5].setText(a2);
		         if(flag[4][5]==true) {
		        	 OB.remove(finalorder[4][5]);
		        	 finalorder[4][5]= new Finalorder(name[4][5].getText(),price[4][5].getText(),amount[4][5].getText());
			         OB.add(finalorder[4][5]);
		         }else {
		        	 finalorder[4][5]= new Finalorder(name[4][5].getText(),price[4][5].getText(),amount[4][5].getText());
		         OB.add(finalorder[4][5]);
		         }
		           flag[4][5]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			//핫도그 마이너스 버튼
			btnM[4][0].setOnAction(e->{
				String a=amount[4][0].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[4][0].setText(a2);
				if(flag[4][0]==true) {
		        	 OB.remove(finalorder[4][0]);
		        	 finalorder[4][0]= new Finalorder(name[4][0].getText(),price[4][0].getText(),amount[4][0].getText());
			         OB.add(finalorder[4][0]);
		         }else {
		        	 finalorder[4][0]= new Finalorder(name[4][0].getText(),price[4][0].getText(),amount[4][0].getText());
		         OB.add(finalorder[4][0]);
		         }
		           flag[4][0]=true;
		           int c= Integer.parseInt(amount[4][0].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[4][0]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[4][1].setOnAction(e->{
				String a=amount[4][1].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[4][1].setText(a2);
				if(flag[4][1]==true) {
		        	 OB.remove(finalorder[4][1]);
		        	 finalorder[4][1]= new Finalorder(name[4][1].getText(),price[4][1].getText(),amount[4][1].getText());
			         OB.add(finalorder[4][1]);
		         }else {
		        	 finalorder[4][1]= new Finalorder(name[4][1].getText(),price[4][1].getText(),amount[4][1].getText());
		         OB.add(finalorder[4][1]);
		         }
		           flag[4][1]=true;
		           int c= Integer.parseInt(amount[4][1].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[4][1]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[4][2].setOnAction(e->{
				String a=amount[4][2].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[4][2].setText(a2);
				if(flag[4][2]==true) {
		        	 OB.remove(finalorder[4][2]);
		        	 finalorder[4][2]= new Finalorder(name[4][2].getText(),price[4][2].getText(),amount[4][2].getText());
			         OB.add(finalorder[4][2]);
		         }else {
		        	 finalorder[4][2]= new Finalorder(name[4][2].getText(),price[4][2].getText(),amount[4][2].getText());
		         OB.add(finalorder[4][2]);
		         }
		           flag[4][2]=true;
		           int c= Integer.parseInt(amount[4][2].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[4][2]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[4][3].setOnAction(e->{
				String a=amount[4][3].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[4][3].setText(a2);
				if(flag[4][3]==true) {
		        	 OB.remove(finalorder[4][3]);
		        	 finalorder[4][3]= new Finalorder(name[4][3].getText(),price[4][3].getText(),amount[4][3].getText());
			         OB.add(finalorder[4][3]);
		         }else {
		        	 finalorder[4][3]= new Finalorder(name[4][3].getText(),price[4][3].getText(),amount[4][3].getText());
		         OB.add(finalorder[4][3]);
		         }
		           flag[4][3]=true;
		           int c= Integer.parseInt(amount[4][3].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[4][3]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[4][4].setOnAction(e->{
				String a=amount[4][4].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[4][4].setText(a2);
				if(flag[4][4]==true) {
		        	 OB.remove(finalorder[4][4]);
		        	 finalorder[4][4]= new Finalorder(name[4][4].getText(),price[4][4].getText(),amount[4][4].getText());
			         OB.add(finalorder[4][4]);
		         }else {
		        	 finalorder[4][4]= new Finalorder(name[4][4].getText(),price[4][4].getText(),amount[4][4].getText());
		         OB.add(finalorder[4][4]);
		         }
		           flag[4][4]=true;
		           int c= Integer.parseInt(amount[4][4].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[4][4]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[4][5].setOnAction(e->{
				String a=amount[4][5].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[4][5].setText(a2);
				if(flag[4][5]==true) {
		        	 OB.remove(finalorder[4][5]);
		        	 finalorder[4][5]= new Finalorder(name[4][5].getText(),price[4][5].getText(),amount[4][5].getText());
			         OB.add(finalorder[4][5]);
		         }else {
		        	 finalorder[4][5]= new Finalorder(name[4][5].getText(),price[4][5].getText(),amount[4][5].getText());
		         OB.add(finalorder[4][5]);
		         }
		           flag[4][5]=true;
		           int c= Integer.parseInt(amount[4][5].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[4][5]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			//간식
		    name[5][0]=(Label)root1.lookup("#name50");//이름
			name[5][1]=(Label)root1.lookup("#name51");
			name[5][2]=(Label)root1.lookup("#name52");
			name[5][3]=(Label)root1.lookup("#name53");
			name[5][4]=(Label)root1.lookup("#name54");
			name[5][5]=(Label)root1.lookup("#name55");
			name[5][6]=(Label)root1.lookup("#name56");
			name[5][7]=(Label)root1.lookup("#name57");
			name[5][8]=(Label)root1.lookup("#name58");
			name[5][9]=(Label)root1.lookup("#name59");		
			price[5][0]=(Label)root1.lookup("#price50");//가격
			price[5][1]=(Label)root1.lookup("#price51");
			price[5][2]=(Label)root1.lookup("#price52");
			price[5][3]=(Label)root1.lookup("#price53");
			price[5][4]=(Label)root1.lookup("#price54");
			price[5][5]=(Label)root1.lookup("#price55");
			price[5][6]=(Label)root1.lookup("#price56");
			price[5][7]=(Label)root1.lookup("#price57");
		    price[5][8]=(Label)root1.lookup("#price58");
			price[5][9]=(Label)root1.lookup("#price59");
			amount[5][0]=(Label)root1.lookup("#amount50");//양
			amount[5][1]=(Label)root1.lookup("#amount51");
			amount[5][2]=(Label)root1.lookup("#amount52");
			amount[5][3]=(Label)root1.lookup("#amount53");
			amount[5][4]=(Label)root1.lookup("#amount54");
			amount[5][5]=(Label)root1.lookup("#amount55");
			amount[5][6]=(Label)root1.lookup("#amount56");
			amount[5][7]=(Label)root1.lookup("#amount57");
			amount[5][8]=(Label)root1.lookup("#amount58");
			amount[5][9]=(Label)root1.lookup("#amount59");
			btnM[5][0]= (Button)root1.lookup("#btnM50");//마이너스버튼
			btnM[5][1]= (Button)root1.lookup("#btnM51");
			btnM[5][2]= (Button)root1.lookup("#btnM52");
			btnM[5][3]= (Button)root1.lookup("#btnM53");
			btnM[5][4]= (Button)root1.lookup("#btnM54");
			btnM[5][5]= (Button)root1.lookup("#btnM55");
			btnM[5][6]= (Button)root1.lookup("#btnM56");
			btnM[5][7]= (Button)root1.lookup("#btnM57");
			btnM[5][8]= (Button)root1.lookup("#btnM58");
			btnM[5][9]= (Button)root1.lookup("#btnM59");
			btnP[5][0]= (Button)root1.lookup("#btnP50");//플러스버튼
			btnP[5][1]= (Button)root1.lookup("#btnP51");
			btnP[5][2]= (Button)root1.lookup("#btnP52");
			btnP[5][3]= (Button)root1.lookup("#btnP53");
			btnP[5][4]= (Button)root1.lookup("#btnP54");
			btnP[5][5]= (Button)root1.lookup("#btnP55");
			btnP[5][6]= (Button)root1.lookup("#btnP56");
			btnP[5][7]= (Button)root1.lookup("#btnP57");
			btnP[5][8]= (Button)root1.lookup("#btnP58");
			btnP[5][9]= (Button)root1.lookup("#btnP59");
			//간식 플러스 버튼
			btnP[5][0].setOnAction(e->{
				String a=amount[5][0].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[5][0].setText(a2);
		         if(flag[5][0]==true) {
		        	 OB.remove(finalorder[5][0]);
		        	 finalorder[5][0]= new Finalorder(name[5][0].getText(),price[5][0].getText(),amount[5][0].getText());
			         OB.add(finalorder[5][0]);
		         }else {
		        	 finalorder[5][0]= new Finalorder(name[5][0].getText(),price[5][0].getText(),amount[5][0].getText());
		         OB.add(finalorder[5][0]);
		         }
		           flag[5][0]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[5][1].setOnAction(e->{
				String a=amount[5][1].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[5][1].setText(a2);
		         if(flag[5][1]==true) {
		        	 OB.remove(finalorder[5][1]);
		        	 finalorder[5][1]= new Finalorder(name[5][1].getText(),price[5][1].getText(),amount[5][1].getText());
			         OB.add(finalorder[5][1]);
		         }else {
		        	 finalorder[5][1]= new Finalorder(name[5][1].getText(),price[5][1].getText(),amount[5][1].getText());
		         OB.add(finalorder[5][1]);
		         }
		           flag[5][1]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[5][2].setOnAction(e->{
				String a=amount[5][2].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[5][2].setText(a2);
		         if(flag[5][2]==true) {
		        	 OB.remove(finalorder[5][2]);
		        	 finalorder[5][2]= new Finalorder(name[5][2].getText(),price[5][2].getText(),amount[5][2].getText());
			         OB.add(finalorder[5][2]);
		         }else {
		        	 finalorder[5][2]= new Finalorder(name[5][2].getText(),price[5][2].getText(),amount[5][2].getText());
		         OB.add(finalorder[5][2]);
		         }
		           flag[5][2]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[5][3].setOnAction(e->{
				String a=amount[5][3].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[5][3].setText(a2);
		         if(flag[5][3]==true) {
		        	 OB.remove(finalorder[5][3]);
		        	 finalorder[5][3]= new Finalorder(name[5][3].getText(),price[5][3].getText(),amount[5][3].getText());
			         OB.add(finalorder[5][3]);
		         }else {
		        	 finalorder[5][3]= new Finalorder(name[5][3].getText(),price[5][3].getText(),amount[5][3].getText());
		         OB.add(finalorder[5][3]);
		         }
		           flag[5][3]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[5][4].setOnAction(e->{
				String a=amount[5][4].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[5][4].setText(a2);
		         if(flag[5][4]==true) {
		        	 OB.remove(finalorder[5][4]);
		        	 finalorder[5][4]= new Finalorder(name[5][4].getText(),price[5][4].getText(),amount[5][4].getText());
			         OB.add(finalorder[5][4]);
		         }else {
		        	 finalorder[5][4]= new Finalorder(name[5][4].getText(),price[5][4].getText(),amount[5][4].getText());
		         OB.add(finalorder[5][4]);
		         }
		           flag[5][4]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[5][5].setOnAction(e->{
				String a=amount[5][5].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[5][5].setText(a2);
		         if(flag[5][5]==true) {
		        	 OB.remove(finalorder[5][5]);
		        	 finalorder[5][5]= new Finalorder(name[5][5].getText(),price[5][5].getText(),amount[5][5].getText());
			         OB.add(finalorder[5][5]);
		         }else {
		        	 finalorder[5][5]= new Finalorder(name[5][5].getText(),price[5][5].getText(),amount[5][5].getText());
		         OB.add(finalorder[5][5]);
		         }
		           flag[5][5]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[5][6].setOnAction(e->{
				String a=amount[5][6].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[5][6].setText(a2);
		         if(flag[5][6]==true) {
		        	 OB.remove(finalorder[5][6]);
		        	 finalorder[5][6]= new Finalorder(name[5][6].getText(),price[5][6].getText(),amount[5][6].getText());
			         OB.add(finalorder[5][6]);
		         }else {
		        	 finalorder[5][6]= new Finalorder(name[5][6].getText(),price[5][6].getText(),amount[5][6].getText());
		         OB.add(finalorder[5][6]);
		         }
		           flag[5][6]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[5][7].setOnAction(e->{
				String a=amount[5][7].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[5][7].setText(a2);
		         if(flag[5][7]==true) {
		        	 OB.remove(finalorder[5][7]);
		        	 finalorder[5][7]= new Finalorder(name[5][7].getText(),price[5][7].getText(),amount[5][7].getText());
			         OB.add(finalorder[5][7]);
		         }else {
		        	 finalorder[5][7]= new Finalorder(name[5][7].getText(),price[5][7].getText(),amount[5][7].getText());
		         OB.add(finalorder[5][7]);
		         }
		           flag[5][7]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[5][8].setOnAction(e->{
				String a=amount[5][8].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[5][8].setText(a2);
		         if(flag[5][8]==true) {
		        	 OB.remove(finalorder[5][8]);
		        	 finalorder[5][8]= new Finalorder(name[5][8].getText(),price[5][8].getText(),amount[5][8].getText());
			         OB.add(finalorder[5][8]);
		         }else {
		        	 finalorder[5][8]= new Finalorder(name[5][8].getText(),price[5][8].getText(),amount[5][8].getText());
		         OB.add(finalorder[5][8]);
		         }
		           flag[5][8]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnP[5][9].setOnAction(e->{
				String a=amount[5][9].getText();
				int a1=Integer.parseInt(a);
				if(a1==99) {
				a1=99;
				}else {
				a1++;
				}
				String a2=String.valueOf(a1);
				amount[5][9].setText(a2);
		         if(flag[5][9]==true) {
		        	 OB.remove(finalorder[5][9]);
		        	 finalorder[5][9]= new Finalorder(name[5][9].getText(),price[5][9].getText(),amount[5][9].getText());
			         OB.add(finalorder[5][9]);
		         }else {
		        	 finalorder[5][9]= new Finalorder(name[5][9].getText(),price[5][9].getText(),amount[5][9].getText());
		         OB.add(finalorder[5][9]);
		         }
		           flag[5][9]=true;
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			//간식 마이너스 버튼
			btnM[5][0].setOnAction(e->{
				String a=amount[5][0].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[5][0].setText(a2);
				if(flag[5][0]==true) {
		        	 OB.remove(finalorder[5][0]);
		        	 finalorder[5][0]= new Finalorder(name[5][0].getText(),price[5][0].getText(),amount[5][0].getText());
			         OB.add(finalorder[5][0]);
		         }else {
		        	 finalorder[5][0]= new Finalorder(name[5][0].getText(),price[5][0].getText(),amount[5][0].getText());
		         OB.add(finalorder[5][0]);
		         }
		           flag[5][0]=true;
		           int c= Integer.parseInt(amount[5][0].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[5][0]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[5][1].setOnAction(e->{
				String a=amount[5][1].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[5][1].setText(a2);
				if(flag[5][1]==true) {
		        	 OB.remove(finalorder[5][1]);
		        	 finalorder[5][1]= new Finalorder(name[5][1].getText(),price[5][1].getText(),amount[5][1].getText());
			         OB.add(finalorder[5][1]);
		         }else {
		        	 finalorder[5][1]= new Finalorder(name[5][1].getText(),price[5][1].getText(),amount[5][1].getText());
		         OB.add(finalorder[5][1]);
		         }
		           flag[5][1]=true;
		           int c= Integer.parseInt(amount[5][1].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[5][1]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[5][2].setOnAction(e->{
				String a=amount[5][2].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[5][2].setText(a2);
				if(flag[5][2]==true) {
		        	 OB.remove(finalorder[5][2]);
		        	 finalorder[5][2]= new Finalorder(name[5][2].getText(),price[5][2].getText(),amount[5][2].getText());
			         OB.add(finalorder[5][2]);
		         }else {
		        	 finalorder[5][2]= new Finalorder(name[5][2].getText(),price[5][2].getText(),amount[5][2].getText());
		         OB.add(finalorder[5][2]);
		         }
		           flag[5][2]=true;
		           int c= Integer.parseInt(amount[5][2].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[5][2]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[5][3].setOnAction(e->{
				String a=amount[5][3].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[5][3].setText(a2);
				if(flag[5][3]==true) {
		        	 OB.remove(finalorder[5][3]);
		        	 finalorder[5][3]= new Finalorder(name[5][3].getText(),price[5][3].getText(),amount[5][3].getText());
			         OB.add(finalorder[5][3]);
		         }else {
		        	 finalorder[5][3]= new Finalorder(name[5][3].getText(),price[5][3].getText(),amount[5][3].getText());
		         OB.add(finalorder[5][3]);
		         }
		           flag[5][3]=true;
		           int c= Integer.parseInt(amount[5][3].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[5][3]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[5][4].setOnAction(e->{
				String a=amount[5][4].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[5][4].setText(a2);
				if(flag[5][4]==true) {
		        	 OB.remove(finalorder[5][4]);
		        	 finalorder[5][4]= new Finalorder(name[5][4].getText(),price[5][4].getText(),amount[5][4].getText());
			         OB.add(finalorder[5][4]);
		         }else {
		        	 finalorder[5][4]= new Finalorder(name[5][4].getText(),price[5][4].getText(),amount[5][4].getText());
		         OB.add(finalorder[5][4]);
		         }
		           flag[5][4]=true;
		           int c= Integer.parseInt(amount[5][4].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[5][4]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[5][5].setOnAction(e->{
				String a=amount[5][5].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[5][5].setText(a2);
				if(flag[5][5]==true) {
		        	 OB.remove(finalorder[5][5]);
		        	 finalorder[5][5]= new Finalorder(name[5][5].getText(),price[5][5].getText(),amount[5][5].getText());
			         OB.add(finalorder[5][5]);
		         }else {
		        	 finalorder[5][5]= new Finalorder(name[5][5].getText(),price[5][5].getText(),amount[5][5].getText());
		         OB.add(finalorder[5][5]);
		         }
		           flag[5][5]=true;
		           int c= Integer.parseInt(amount[5][5].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[5][5]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[5][6].setOnAction(e->{
				String a=amount[5][6].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[5][6].setText(a2);
				if(flag[5][6]==true) {
		        	 OB.remove(finalorder[5][6]);
		        	 finalorder[5][6]= new Finalorder(name[5][6].getText(),price[5][6].getText(),amount[5][6].getText());
			         OB.add(finalorder[5][6]);
		         }else {
		        	 finalorder[5][6]= new Finalorder(name[5][6].getText(),price[5][6].getText(),amount[5][6].getText());
		         OB.add(finalorder[5][6]);
		         }
		           flag[5][6]=true;
		           int c= Integer.parseInt(amount[5][6].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[5][6]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[5][7].setOnAction(e->{
				String a=amount[5][7].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[5][7].setText(a2);
				if(flag[5][7]==true) {
		        	 OB.remove(finalorder[5][7]);
		        	 finalorder[5][7]= new Finalorder(name[5][7].getText(),price[5][7].getText(),amount[5][7].getText());
			         OB.add(finalorder[5][7]);
		         }else {
		        	 finalorder[5][7]= new Finalorder(name[5][7].getText(),price[5][7].getText(),amount[5][7].getText());
		         OB.add(finalorder[5][7]);
		         }
		           flag[5][7]=true;
		           int c= Integer.parseInt(amount[5][7].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[5][7]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[5][8].setOnAction(e->{
				String a=amount[5][8].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[5][8].setText(a2);
				if(flag[5][8]==true) {
		        	 OB.remove(finalorder[5][8]);
		        	 finalorder[5][8]= new Finalorder(name[5][8].getText(),price[5][8].getText(),amount[5][8].getText());
			         OB.add(finalorder[5][8]);
		         }else {
		        	 finalorder[5][8]= new Finalorder(name[5][8].getText(),price[5][8].getText(),amount[5][8].getText());
		         OB.add(finalorder[5][8]);
		         }
		           flag[5][8]=true;
		           int c= Integer.parseInt(amount[5][8].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[5][8]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			btnM[5][9].setOnAction(e->{
				String a=amount[5][9].getText();
				int a1=Integer.parseInt(a);
				if(a1==0) {
					a1=0;
				}else {
					a1--;
				}
				String a2=String.valueOf(a1);
				amount[5][9].setText(a2);
				if(flag[5][9]==true) {
		        	 OB.remove(finalorder[5][9]);
		        	 finalorder[5][9]= new Finalorder(name[5][9].getText(),price[5][9].getText(),amount[5][9].getText());
			         OB.add(finalorder[5][9]);
		         }else {
		        	 finalorder[5][9]= new Finalorder(name[5][9].getText(),price[5][9].getText(),amount[5][9].getText());
		         OB.add(finalorder[5][9]);
		         }
		           flag[5][9]=true;
		           int c= Integer.parseInt(amount[5][9].getText()); 
		           if(c==0) {
		        	   OB.remove(finalorder[5][9]);
		           }
		           foodTable.setItems(OB);
		           for(Finalorder fi:OB) {
						String a11=fi.getFprice();
						sum=Integer.parseInt(a11)+sum;	
					}
					notice.setText(String.valueOf(sum));
					sum=0;
			});
			//결제방법//주문시 요청사항 
			RadioButton realmoney=(RadioButton)root1.lookup("#realmoney");
			RadioButton cardmoney=(RadioButton)root1.lookup("#cardmoney");
			ComboBox moneyWay=(ComboBox)root1.lookup("#moneyWay");
			TextArea ordermesage=(TextArea)root1.lookup("#ordermesage");
			realmoney.setOnAction(e->{
			moneyWay.setItems(moneyWaylist);
			});
			cardmoney.setOnAction(e->{
				moneyWaylist.removeAll();
				moneyWay.setItems(cardWaylist);
			});
			notice.setEditable(false);
		btnOrder.setOnAction(e->{
			for(Finalorder fi:OB) {
				String a=fi.getFprice();
				sum=Integer.parseInt(a)+sum;	
			}
			notice.setText(String.valueOf(sum));
			sum=0;
		});
		btnOrder.setOnAction(e->{
			if((cardmoney.getUserData()==null&&realmoney.getUserData()==null)||moneyWay.getSelectionModel().getSelectedItem()==null) {
				callAlert("결제방식 선택 바람:결제 방법을 선택해주세요");
			}else {
				if(cardmoney.selectedProperty().getValue().equals(true)) {
					for(int i=0;i<OB.size();i++) {
						String menu=OB.get(i).getMenu();
						String amount=OB.get(i).getAmount();
						String fprice1=OB.get(i).getFprice();
						SimpleDateFormat fm2= new SimpleDateFormat(
					            "yyyy년MM월dd일HH시mm분ss초");
					    String date = fm2.format(new Date());
					    	fi2=new Finalorder2("5",logtxtID.getText(), menu, amount, fprice1,cardmoney.getUserData().toString() , moneyWay.getSelectionModel().getSelectedItem().toString(),date,ordermesage.getText());
							int count = MemberDAO.insertorderDate(fi2);
							
					}
					OB.clear();
					moneyWay.getSelectionModel().clearSelection();
					cardmoney.setSelected(false);
					ordermesage.setText(null);
					notice.setText(null);
					callAlert("상품주문 완료:주문이 완료되었습니다.");
					send2();
				}else if(realmoney.selectedProperty().getValue().equals(true)){
					for(int i=0;i<OB.size();i++) {
						String menu=OB.get(i).getMenu();
						String amount=OB.get(i).getAmount();
						String fprice1=OB.get(i).getFprice();
						SimpleDateFormat fm2= new SimpleDateFormat(
					            "yyyy년MM월dd일HH시mm분ss초");
					    String date = fm2.format(new Date());
					    	fi2=new Finalorder2("5",logtxtID.getText(), menu, amount, fprice1, realmoney.getUserData().toString(), moneyWay.getSelectionModel().getSelectedItem().toString(),date,ordermesage.getText());
							int count = MemberDAO.insertorderDate(fi2);
					}
					OB.clear();
					moneyWay.getSelectionModel().clearSelection();
					realmoney.setSelected(false);
					ordermesage.setText(null);
					notice.setText(null);
					callAlert("상품주문 완료:주문이 완료되었습니다.");
					send2();
				}
			}
			
			
			
		});
		}else {
			foodSystemOn.setVisible(true);
			foodSystemOrder.close();
			foodStageLabel=true;
		}
		 
	}
	//타이머창
	private void timeSystem() {
		timeSystem=new Stage(StageStyle.UNDECORATED);
		timeSystem.setX(1420);
		timeSystem.setY(0);
		timeSystem.initModality(Modality.NONE);
		timeSystem.initOwner(stage);
		Parent root2 =null;
		try{
			root2=FXMLLoader.load(getClass().getResource("../view/timeSystem.fxml"));
		}catch(Exception e) {
		}
		Button timeSystemExit=(Button)root2.lookup("#timeSystemExit");
		Button timeSystemMeasage=(Button)root2.lookup("#timeSystemMeasage");
		Button timeSystemAddCharge=(Button)root2.lookup("#timeSystemAddCharge");
		Label timeSystemRemainTime=(Label)root2.lookup("#timeSystemRemainTime");
		Label timeSystemStartTime=(Label)root2.lookup("#timeSystemStartTime");
		new Thread(()-> {
			while(true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {}
				i++;
				if(i==60) {
					i=00;
					m++;
				if(m==60) {
					m=00;
					h++;
				}
				}
				String is=String.valueOf(i);
				String ms=String.valueOf(m);
				String hs=String.valueOf(h);
				Platform.runLater(()->{
					timeSystemRemainTime.setText(hs+":"+ms+":"+is);
	                });
			}
      }).start();
		SimpleDateFormat fm2= new SimpleDateFormat(
	            "HH시mm분ss초");
	    String date1 = fm2.format(new Date());
	    timeSystemStartTime.setText(date1);
		
		Scene scene = new Scene(root2);
		timeSystem.setScene(scene);
		timeSystem.show();
		timeSystemExit.setOnAction(e->{
			if(foodStageLabel==false) {
				foodSystemOrder.close();
				foodStageLabel=true;
			}
			if(MeasageClick==true) {
				Measage.close();
				MeasageClick=false;
			}
			if(AddChargeClick==true) {
				AddCharge.close();
				AddChargeClick=false;
			}
			logtxtPassword.setText(null);
			logtxtID.setText(null);
			foodSystemStage.close();
			timeSystem.close();
			try {
				socket.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			AppMain.stage.show();
			 System.exit(0);
		});
		//메세지창
		//쿠폰선택창
		timeSystemAddCharge.setOnAction(e->{
			if(AddChargeClick==false) {
				AddChargeSystem();
				AddChargeClick=true;
			}else {
				AddCharge.close();
				AddChargeClick=false;
			}
		});
	}
	//쿠폰선택창
	private void AddChargeSystem() {
		AddCharge=new Stage(StageStyle.UNDECORATED);
		AddCharge.setX(1675);
		AddCharge.setY(190);
		AddCharge.initModality(Modality.NONE);
		AddCharge.initOwner(stage);
		Parent AddChargeroot =null;
		try{
			AddChargeroot=FXMLLoader.load(getClass().getResource("../view/addCharge.fxml"));
		}catch(Exception e) {}
		Scene AddChargescene = new Scene(AddChargeroot);
		AddCharge.setScene(AddChargescene);
		AddCharge.show();
		
	}
	//메세지창
	private void mesageSystem() {
		Measage=new Stage(StageStyle.UNDECORATED);
		Measage.setX(1675);
		Measage.setY(190);
		Measage.initModality(Modality.NONE);
		Measage.initOwner(stage);
		Parent Measageroot =null;
		try{
			Measageroot=FXMLLoader.load(getClass().getResource("../view/Measage1.fxml"));
		}catch(Exception e) {}
		mesageSend=(Button)Measageroot.lookup("#mesageSend");
		mesageCancel=(Button)Measageroot.lookup("#mesageCancel");
		mesageArea=(TextArea)Measageroot.lookup("#mesageArea");
		measage1Field=(TextField)Measageroot.lookup("#measage1Field");
		Scene Measagescene = new Scene(Measageroot);
		Measage.setScene(Measagescene);
		Measage.show();
		mesageArea.setEditable(false);
		mesageSend.setOnAction(e->{
			send();
			mesageArea.appendText("나:"+measage1Field.getText()+"\n");
			try {
				Thread.sleep(10);
			} catch (InterruptedException e1) {}
			measage1Field.setText(null);
		});
		measage1Field.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				if(event.getCode().equals(KeyCode.ENTER)) {
				send();
				mesageArea.appendText("나:"+measage1Field.getText()+"\n");
				try {
					Thread.sleep(10);
				} catch (InterruptedException e1) {}
				measage1Field.setText(null);
				}
			}
		});
	}
	//게임창
	private void gameSystem() {}
	//회원가입창
	private void joinSystem() {
		joinSystem = new Stage(StageStyle.DECORATED);
		joinSystem.setX(800);
		joinSystem.setY(300);
		joinSystem.setTitle("회원가입");
		joinSystem.initModality(Modality.NONE);
		joinSystem.initOwner(stage);
		Parent root3 = null;
		try {
			root3 = FXMLLoader.load(getClass().getResource("../view/joinSystem.fxml"));
		} catch (Exception e) {}
		Button joinIDCheck=(Button)root3.lookup("#joinIDCheck");
		Button joinRegister=(Button)root3.lookup("#joinRegister");
		Button joinCancel=(Button)root3.lookup("#joinCancel");
		joinName=(TextField)root3.lookup("#joinName");
		joinID=(TextField)root3.lookup("#joinID");
		joinPaword1=(PasswordField)root3.lookup("#joinPaword1");
		 joinPaword2=(PasswordField)root3.lookup("#joinPaword2");
		joinPhoenumber=(TextField)root3.lookup("#joinPhoenumber");
		joinE_mailID=(TextField)root3.lookup("#joinE_mailID");
		joinE_mailAdressWrite=(TextField)root3.lookup("#joinE_mailAdressWrite");
		joinGender=(ComboBox)root3.lookup("#joinGender");
		joinMonth=(ComboBox)root3.lookup("#joinMonth");
		joinYear=(ComboBox)root3.lookup("#joinYear");
		joinDay=(ComboBox)root3.lookup("#joinDay");
		joinE_mailAdressAuto=(ComboBox)root3.lookup("#joinE_mailAdressAuto");
	    joinSelectTime=(ComboBox)root3.lookup("#joinSelectTime"); 
		Scene scene3 = new Scene(root3);
		joinSystem.setScene(scene3);
		joinSystem.show();
		joinCancel.setOnAction(e->{joinSystem.close();});
		if(!joinSystemvalue) {
			joinComboBoxSetting();
			joinSystemvalue=true;
		}
		//콤보박스 값 넣기
		joinGender.setItems(genderList);//성별 콤보박스 
		joinYear.setItems(yearList);//년도 콤보박스
		joinMonth.setItems(monthList);//월 콤보박스
		joinDay.setItems(dayList);
		joinE_mailAdressAuto.setItems(e_mailList);//이메일 콤보박스
		joinE_mailAdressWrite.setVisible(false);//->메일 직접입력
		//직접입력 누르면 처리하는 함수
		joinE_mailAdressAuto.setOnAction(e->{

			if(joinE_mailAdressAuto.getValue()=="직접입력") {
				joinE_mailAdressWrite.setVisible(true);
			}else {
				joinE_mailAdressWrite.setVisible(false);
			}
		});
		//id 중복체크눌렀을때 처리하는 함수
		joinIDCheck.setOnAction(e1->{
			if(joinID.getText().isEmpty()==true) {
				callAlert("이름 입력:아이디를 입력하세요");
			}else{
				Member m = new Member(joinID.getText());
				try{
					MemberDAO.identifyID().getId();
					AppMain.callAlert("사용불가능:사용할 수 없는 아이디 입니다.");
				}catch(NullPointerException e) {
					AppMain.callAlert("사용가능:사용가능한 아이디입니다.");
				}
			}
			
		});
		//등록 눌렀을때 처리하는 함수
		joinRegister.setOnAction(e->{
			if(joinName.getText().length()<1) {
				AppMain.callAlert("이름 오류:이름입력하세요");
			}else if(joinID.getText().trim().length()<1) {
				AppMain.callAlert("아이디 오류:아이디를입력하세요");
			}else if(!(RootController.joinPaword1.getText().equals(RootController.joinPaword2.getText()))) {
				AppMain.callAlert("비밀번호 오류:비밀번호가 일치하지 않습니다");
			}else if(RootController.joinPaword2.getLength()<6||RootController.joinPaword2.getLength()>15){
				AppMain.callAlert("비밀번호 오류:6자리이상 15자리이하로 입력하세요.");
			} else if(joinGender.getValue()==null) {
				AppMain.callAlert("성별 오류:성별을 선택하세요");
			}else if(joinYear.getValue()==null||joinMonth.getValue()==null||joinDay.getValue()==null) {
				AppMain.callAlert("생년월일 오류:생년월일을 선택하세요");
			}else if(joinPhoenumber.getText().length()<10||joinPhoenumber.getText().length()>11) {
				AppMain.callAlert("전화번호 오류:10자리 또는 11자리를 입력하세요");
			}else if(joinE_mailID.getText().length()<1){
				AppMain.callAlert("이메일 오류:이메일을 입력하세요");
			}else if(joinE_mailAdressAuto.getValue()==null){
					AppMain.callAlert("이메일 오류:이메일을 입력하세요");
				AppMain.callAlert("이메일 오류:이메일을 입력하세요");
			}else if(joinE_mailAdressWrite.isVisible()==true&&joinE_mailAdressWrite.getText().length()<1){
				AppMain.callAlert("이메일 오류:이메일을 입력하세요");
			} else{
			String age=joinYear.getValue()+joinMonth.getValue()+joinDay.getValue();
			SimpleDateFormat fm2= new SimpleDateFormat(
		            "yyyy년MM월dd일HH시mm분ss초");
		    String date = fm2.format(new Date());
		    if(joinE_mailAdressAuto.getValue()=="직접입력") {
		    	member =new Member(joinName.getText(), joinID.getText(),joinPaword2.getText(), joinGender.getValue(), age, 
						joinPhoenumber.getText(), joinE_mailID.getText()+"@"+joinE_mailAdressWrite.getText(),date);
		    	db1MemberList.add(member);
		    }else {
		    	member =new Member(joinName.getText(), joinID.getText(), joinPaword2.getText(),joinGender.getValue(), age, 
						joinPhoenumber.getText(), joinE_mailID.getText()+"@"+joinE_mailAdressAuto.getValue(),date);
		    	db1MemberList.add(member);
		    }
			int count = MemberDAO.insertMemberDate(member);
			if (count != 0) {
				callAlert("회원가입 성공 : OSHPC의 회원이 되신 것을 축하합니다.");
				}
			}
		});
	}
	//회원가입콤보박스세팅
	private void joinComboBoxSetting() {
		//------------------------------------------콤보박스세팅------------------------------------------------------------------------
				//성별
				genderList.add("남자");
				genderList.add("여자");
				//생년월일-----
				for(int i=2019;i>=1900;i--) {
				yearList.add(i);
				}
				for(int i=1;i<=9;i++) {
					monthList.add("0"+i);
				}
				for(int i=10;i<=12;i++) {
					monthList.add(""+i);
				}
					
				for(int i=1;i<=9;i++) {
					dayList.add("0"+i);
				}
				for(int i=10;i<=31;i++) {
					dayList.add(""+i);
				}
				
				//e_mail
				e_mailList.add("직접입력");
				e_mailList.add("naver.com");
				e_mailList.add("nate.com");
				e_mailList.add("hanmail.net");
				e_mailList.add("hanmir.com");
				e_mailList.add("gmail.com");
				e_mailList.add("hitel.net");
				e_mailList.add("dreamwiz.com");
				e_mailList.add("korea.com");
				e_mailList.add("lycos.co.kr");
				e_mailList.add("intizen.com");
				//요금
				chargeList.add("1000원-  50분");
				chargeList.add("2000원-  2시간");
				chargeList.add("3000원-  3시간30분");
				chargeList.add("4000원-  5시간");
				chargeList.add("5000원-  6시간30분");
				chargeList.add("10000원-14시간");
				chargeList.add("50000원-72시간");
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