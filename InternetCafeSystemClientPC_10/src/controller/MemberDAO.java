package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import application.AppMain;
import model.Finalorder2;
import model.Member;


public class MemberDAO {
	public static ArrayList<Member> db1MemberList= new ArrayList<>();
	
	//로그인 하기+
	public static Member login(String id, String password) {
			String command = "select userID,password from member where userID like ";
			command+= "'"+id+"' and password like '"+password+"'";
			Connection con = null; 
			Statement stmt = null;
	    	Member user = null;
	      try {
	         con = DBUtility.getConnetction();
	         stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(command);
	         while(rs.next()){
	        	 user = new Member(rs.getString("userID"), rs.getString("password"));
	            }
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	      return user;
	}
	//아이디(회원가입창 아이디확인)
	public static Member identifyID() {
		String command = "select userID from member where userID like ";
	      command+= "'"+RootController.joinID.getText()+"'";
	      Connection con = null; 
	      Statement stmt = null;
	      Member memberID = null;
	      try {
	         con = DBUtility.getConnetction();
	         stmt = con.createStatement();
	         ResultSet rs = stmt.executeQuery(command);
	         while(rs.next()){
	            memberID = new Member(rs.getString("userID"));
	            }
	      }catch (Exception e) {
	         e.printStackTrace();
	      }
	      return memberID;
	}
	//주문디비
	public static int insertorderDate(Finalorder2 finalorder2) {
		StringBuffer insertfinalorder2= new StringBuffer();
		insertfinalorder2.append("insert into finalorder2 ");
		insertfinalorder2.append("(num,userID,menu,amount,fice,mway,unit,ask,mdate) ");
		insertfinalorder2.append("values ");
		insertfinalorder2.append("(?,?,?,?,?,?,?,?,?) ");
		Connection con=null;
		PreparedStatement psmt=null;
		int count =0;
			try {
				con=DBUtility.getConnetction();
				psmt=con.prepareStatement(insertfinalorder2.toString());
				psmt.setString(1, finalorder2.getNumber());
				psmt.setString(2, finalorder2.getId());
				psmt.setString(3, finalorder2.getMenu());
				psmt.setString(4, finalorder2.getAmount());
				psmt.setString(5, finalorder2.getFprice());
				psmt.setString(6, finalorder2.getWay());
				psmt.setString(7, finalorder2.getUnit());
				psmt.setString(8, finalorder2.getAsk());
				psmt.setString(9, finalorder2.getDate());
				count=psmt.executeUpdate();
				//RootController.joinSystem.close();
				if(count==0) {
					AppMain.callAlert("주문실패:주문할 수 없습니다.");
				return count;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				try {
					if(psmt!=null)
						psmt.close();
					if(con !=null)
						con.close();
				}catch(SQLException e) {
					AppMain.callAlert("닫기 실패 : psmt,con점검바람");
					e.printStackTrace();
				}
			}
		return count;
	}
	//회원가입 진행
	public static int insertMemberDate(Member member) {
		StringBuffer insertMember= new StringBuffer();
		insertMember.append("insert into member ");
		insertMember.append("(name,userID,password,gender,age,phoneNumber,email,mdate) ");
		insertMember.append("values ");
		insertMember.append("(?,?,?,?,?,?,?,?) ");
		// insert into member (name,userID,gender,age,phoneNumber,email,mdate) values('1','2','3','4','5','6','7'); 
		Connection con=null;
		PreparedStatement psmt=null;
		int count =0;
			try {
				con=DBUtility.getConnetction();
				psmt=con.prepareStatement(insertMember.toString());
				psmt.setString(1, member.getName());
				psmt.setString(2, member.getId());
				psmt.setString(3, member.getPassword());
				psmt.setString(4, member.getGender());
				psmt.setString(5, member.getAge());
				psmt.setString(6, member.getPhonNumber());
				psmt.setString(7, member.getE_mail());
				psmt.setString(8,member.getDate());
				count=psmt.executeUpdate();
				RootController.joinSystem.close();
				if(count==0) {
					AppMain.callAlert("회원가입 실패:디비연결 확인바람");
				return count;
				}
			} catch (SQLException e) {
				AppMain.callAlert("사용불가능:중복된 아이디입니다.");
				e.printStackTrace();
			}finally {
				try {
					if(psmt!=null)
						psmt.close();
					if(con !=null)
						con.close();
				}catch(SQLException e) {
					AppMain.callAlert("닫기 실패 : psmt,con점검바람");
					e.printStackTrace();
				}
			}
		return count;
	}



}
