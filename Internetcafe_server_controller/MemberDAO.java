package controller;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import application.AppMain;
import model.Finalorder2;
import model.Member;
public class MemberDAO {
		//리스트
		public static ArrayList<Member> db1MemberList= new ArrayList<>();
		public static ArrayList<Finalorder2> db1Finalorder2List= new ArrayList<>();
		public static ArrayList<Finalorder2> db1Finalorder2List2= new ArrayList<>();
		public static String money;
		//회원관리에 정보가져오기
		public static ArrayList<Member>getMemberTotalData(){
		String selectMember= "select * from member ";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnetction();
			psmt=con.prepareStatement(selectMember);
			rs = psmt.executeQuery();
			if (rs == null) {
				AppMain.callAlert("select 실패 : select 쿼리문 실패 점검바람");
				return null;
			}
			while (rs.next()) {
				Member member = new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8));
				db1MemberList.add(member);
			}
		} catch (SQLException e) {
			AppMain.callAlert("삽입 실패 : 데이터 베이스에 자료 삽입 실패\n점검바람");
			e.printStackTrace();
		} finally {
			// 1.6 자원객체를 닫아서 반납. 제일먼저(con) 부른것을 제일 나중에 그래야 안의 내용(psmt)을 먼저 지우고 나갈 수 있음
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AppMain.callAlert("닫기 실패 : 자원 자료 psmt, con 닫기에 실패\n점검바람");
				e.printStackTrace();
			}
		}
return db1MemberList;
}
		//회원정보 검색된 이름 가져오기
		public static ArrayList<Member> searchname(String name){
			String name1 = name;
			String selectFinalorder2= "select * from member where name=";
			selectFinalorder2=selectFinalorder2+ "'"+name1+"'";
			Connection con = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				con = DBUtility.getConnetction();
				psmt=con.prepareStatement(selectFinalorder2);
				rs = psmt.executeQuery();
				if (rs == null) {
					AppMain.callAlert("검색 실패:해당하는 이름이 없습니다.");
					return null;
				}
				while (rs.next()) {
					Member member = new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8));
					db1MemberList.add(member);
				}
			} catch (SQLException e) {
				AppMain.callAlert("오류:정보를 불러올 수 없습니다.");
				e.printStackTrace();
			} finally {
				// 1.6 자원객체를 닫아서 반납. 제일먼저(con) 부른것을 제일 나중에 그래야 안의 내용(psmt)을 먼저 지우고 나갈 수 있음
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("오류:정보를 불러올 수 없습니다.");
					e.printStackTrace();
				}
			}
	return db1MemberList;
	}
		//회원정보 검색된 아이디 가져오기
		public static ArrayList<Member> searchID(String ID){
			String userID = ID;
			String selectFinalorder2= "select * from member where userID=";
			selectFinalorder2=selectFinalorder2+ "'"+userID+"'";
			Connection con = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				con = DBUtility.getConnetction();
				psmt=con.prepareStatement(selectFinalorder2);
				rs = psmt.executeQuery();
				if (rs == null) {
					AppMain.callAlert("검색 실패:해당하는 id가 없습니다.");
					return null;
				}
				while (rs.next()) {
					Member member = new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8));
					db1MemberList.add(member);
				}
			} catch (SQLException e) {
				AppMain.callAlert("검색 실패:해당하는 이름이 없습니다.");
				e.printStackTrace();
			} finally {
				// 1.6 자원객체를 닫아서 반납. 제일먼저(con) 부른것을 제일 나중에 그래야 안의 내용(psmt)을 먼저 지우고 나갈 수 있음
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("오류:정보를 불러올 수 없습니다.");
					e.printStackTrace();
				}
			}
	return db1MemberList;
	}
		//주문창에 넣기
		public static ArrayList<Finalorder2>finalorder2(){
		String selectFinalorder2= "select * from finalorder2 ";
		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;
		try {
			con = DBUtility.getConnetction();
			psmt=con.prepareStatement(selectFinalorder2);
			rs = psmt.executeQuery();
			if (rs == null) {
				AppMain.callAlert("오류:정보를 불러올 수 없습니다.");
				return null;
			}
			while (rs.next()) {
				Finalorder2 finalorder2 = new Finalorder2(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getString(8));
				db1Finalorder2List.add(finalorder2);
			}
		} catch (SQLException e) {
			AppMain.callAlert("오류:정보를 불러올 수 없습니다.");
			e.printStackTrace();
		} finally {
			// 1.6 자원객체를 닫아서 반납. 제일먼저(con) 부른것을 제일 나중에 그래야 안의 내용(psmt)을 먼저 지우고 나갈 수 있음
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AppMain.callAlert("오류:정보를 불러올 수 없습니다.");
				e.printStackTrace();
			}
		}
return db1Finalorder2List;
}
		//회원정보 수정하기
		public static int updateMemberData(Member member) {
			System.out.println(member.getId());
					StringBuffer updateStudent = new StringBuffer();
					updateStudent.append("update member set ");
					updateStudent.append("name=?,password=?,age=?,phoneNumber=?,email=? where userID=? ");
					Connection con = null;
					PreparedStatement psmt = null;
					int count = 0;
					try {
						con = DBUtility.getConnetction();
						psmt = con.prepareStatement(updateStudent.toString());
						// 1.4 쿼리문에 실제데이터를 연결한다.
						psmt.setString(1, member.getName());
						psmt.setString(2, member.getPassword());
						psmt.setString(3, member.getAge());
						psmt.setString(4, member.getPhonNumber());
						psmt.setString(5, member.getE_mail());
						psmt.setString(6, member.getId());
						// 1.5 실제 데이터 연결을 위한 쿼리문을 실행하라. executeUpdate()는 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문
						count = psmt.executeUpdate();
						if (count == 0) {
							AppMain.callAlert("수정 오류 : 수정 실패1");
							return count;
						}
					} catch (SQLException e) {
						AppMain.callAlert("수정 오류 : 수정 실패2");
						e.printStackTrace();
					} finally {
						try {
							if (psmt != null)
								psmt.close();
							if (con != null)
								con.close();
						} catch (SQLException e) {
							AppMain.callAlert("닫기 실패 : 자원 자료 psmt, con 닫기에 실패\n점검바람");
							e.printStackTrace();
						}
					}
					return count;
		}
		//회원정보 삭제하기
		public static int deleteMembertData(String id) {
					String deleteStudent ="delete from member where userID = ? ";
					Connection con = null;
					// 1.3 쿼리문을 실행해야 할 Statement를 만든다.
					PreparedStatement psmt = null;
					// 1.4 쿼리문을 실행하고 나서 삭제 레코드를 담고 있는 보자기 객체
					int count =0;
					try {
						con = DBUtility.getConnetction();
						psmt = con.prepareStatement(deleteStudent);
						psmt.setString(1, id);
						// 1.5 실제 데이터 연결을 위한 쿼리문을 실행하라.(번개 치기)
						// executeQuery()는 쿼리문을 실행해서 결과를 가져올 때 사용하는 번개문
						// executeUpdate()는 쿼리문을 실행해서 테이블에 저장 할 때 사용하는 번개문
						count = psmt.executeUpdate();
						if (count == 0) {
							AppMain.callAlert("삭제 실패 : 삭제할 수 없습니다");
							return count;
						}
					} catch (SQLException e) {
						AppMain.callAlert("삭제 실패 : 데이터 베이스에 자료 삭제 실패\n점검바람");
						e.printStackTrace();
					} finally {
						// 1.6 자원객체를 닫아서 반납. 제일먼저(con) 부른것을 제일 나중에 그래야 안의 내용(psmt)을 먼저 지우고 나갈 수 있음
						try {
							if (psmt != null)
								psmt.close();
							if (con != null)
								con.close();
						} catch (SQLException e) {
							AppMain.callAlert("닫기 실패 : 자원 자료 psmt, con 닫기에 실패\n점검바람");
							e.printStackTrace();
						}

					}
					return count;
				}
		//판매정보 가져오기
		public static ArrayList<Finalorder2> salestotal(){
			String selectFinalorder2= "SELECT userID,menu,amount,fice,mway,mdate FROM finalorder2 ";
			Connection con = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				con = DBUtility.getConnetction();
				psmt=con.prepareStatement(selectFinalorder2);
				rs = psmt.executeQuery();
				if (rs == null) {
					AppMain.callAlert("검색 실패:해당하는 id가 없습니다.");
					return null;
				}
				while (rs.next()) {
					Finalorder2 finalorder2 = new Finalorder2(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5),rs.getString(6));
					db1Finalorder2List2.add(finalorder2);
				}
			} catch (SQLException e) {
				AppMain.callAlert("검색 실패:해당하는 이름이 없습니다.");
				e.printStackTrace();
			} finally {
				// 1.6 자원객체를 닫아서 반납. 제일먼저(con) 부른것을 제일 나중에 그래야 안의 내용(psmt)을 먼저 지우고 나갈 수 있음
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("오류:정보를 불러올 수 없습니다.");
					e.printStackTrace();
				}
			}
	return db1Finalorder2List2;
	}
		//매출관리 상품명 검색
		public static ArrayList<Finalorder2> salesMenu(String menu){
			String selectFinalorder2= "SELECT userID,menu,amount,fice,mway,mdate FROM finalorder2 where menu= ";
			selectFinalorder2=selectFinalorder2+"'"+menu+"'";
			Connection con = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				con = DBUtility.getConnetction();
				psmt=con.prepareStatement(selectFinalorder2);
				rs = psmt.executeQuery();
				if (rs == null) {
					AppMain.callAlert("검색 실패:해당하는 상품명이 없습니다.");
					return null;
				}
				while (rs.next()) {
					Finalorder2 finalorder2 = new Finalorder2(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5),rs.getString(6));
					db1Finalorder2List2.add(finalorder2);
				}
			} catch (SQLException e) {
				AppMain.callAlert("검색 실패:해당하는 이름이 없습니다.");
				e.printStackTrace();
			} finally {
				// 1.6 자원객체를 닫아서 반납. 제일먼저(con) 부른것을 제일 나중에 그래야 안의 내용(psmt)을 먼저 지우고 나갈 수 있음
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("오류:정보를 불러올 수 없습니다.");
					e.printStackTrace();
				}
			}
	return db1Finalorder2List2;
	}
		//매출관리 id검색
		public static ArrayList<Finalorder2> salesId(String id){
			String selectFinalorder2= "SELECT userID,menu,amount,fice,mway,mdate FROM finalorder2 where userID= ";
			selectFinalorder2=selectFinalorder2+"'"+id+"'";
			Connection con = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				con = DBUtility.getConnetction();
				psmt=con.prepareStatement(selectFinalorder2);
				rs = psmt.executeQuery();
				if (rs == null) {
					AppMain.callAlert("검색 실패:해당하는 id가 없습니다.");
					return null;
				}
				while (rs.next()) {
					Finalorder2 finalorder2 = new Finalorder2(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5),rs.getString(6));
					db1Finalorder2List2.add(finalorder2);
				}
			} catch (SQLException e) {
				AppMain.callAlert("검색 실패:해당하는 이름이 없습니다.");
				e.printStackTrace();
			} finally {
				// 1.6 자원객체를 닫아서 반납. 제일먼저(con) 부른것을 제일 나중에 그래야 안의 내용(psmt)을 먼저 지우고 나갈 수 있음
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("오류:정보를 불러올 수 없습니다.");
					e.printStackTrace();
				}
			}
	return db1Finalorder2List2;
	}
		//결제수단 검색 가져오기 
		public static ArrayList<Finalorder2> salesWay(String mway){
			String selectFinalorder2= "SELECT userID,menu,amount,fice,mway,mdate FROM finalorder2 where mway=";
			selectFinalorder2=selectFinalorder2+"'"+mway+"'";
			Connection con = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				con = DBUtility.getConnetction();
				psmt=con.prepareStatement(selectFinalorder2);
				rs = psmt.executeQuery();
				if (rs == null) {
					AppMain.callAlert("검색 실패:해당하는 id가 없습니다.");
					return null;
				}
				while (rs.next()) {
					Finalorder2 finalorder2 = new Finalorder2(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5),rs.getString(6));
					db1Finalorder2List2.add(finalorder2);
				}
			} catch (SQLException e) {
				AppMain.callAlert("검색 실패:해당하는 이름이 없습니다.");
				e.printStackTrace();
			} finally {
				// 1.6 자원객체를 닫아서 반납. 제일먼저(con) 부른것을 제일 나중에 그래야 안의 내용(psmt)을 먼저 지우고 나갈 수 있음
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("오류:정보를 불러올 수 없습니다.");
					e.printStackTrace();
				}
			}
	return db1Finalorder2List2;
	}
		//매출관리 토탈 금액
		public static String salestotaMoney(){
			String selectFinalorder2= "select sum(fice) from finalorder2 ";
			Connection con = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				con = DBUtility.getConnetction();
				psmt=con.prepareStatement(selectFinalorder2);
				rs = psmt.executeQuery();
				if (rs == null) {
					AppMain.callAlert("검색 실패:해당하는 id가 없습니다.");
					return null;
				}
				while (rs.next()) {
					money=rs.getString(1);
				}
			} catch (SQLException e) {
				AppMain.callAlert("검색 실패:해당하는 이름이 없습니다.");
				e.printStackTrace();
			} finally {
				// 1.6 자원객체를 닫아서 반납. 제일먼저(con) 부른것을 제일 나중에 그래야 안의 내용(psmt)을 먼저 지우고 나갈 수 있음
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("오류:정보를 불러올 수 없습니다.");
					e.printStackTrace();
				}
			}
			System.out.println(money+"here");
	return money;
	}
		//매출 (카드 현금)  금액
		public static String salestmwaymoney(String value){
			String selectFinalorder2= "select sum(fice) from finalorder2 where mway=";
			selectFinalorder2=selectFinalorder2+"'"+value+"'";
			Connection con = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				con = DBUtility.getConnetction();
				psmt=con.prepareStatement(selectFinalorder2);
				rs = psmt.executeQuery();
				if (rs == null) {
					AppMain.callAlert("검색 실패:해당하는 id가 없습니다.");
					return null;
				}
				while (rs.next()) {
					money=rs.getString(1);
				}
			} catch (SQLException e) {
				AppMain.callAlert("검색 실패:해당하는 이름이 없습니다.");
				e.printStackTrace();
			} finally {
				// 1.6 자원객체를 닫아서 반납. 제일먼저(con) 부른것을 제일 나중에 그래야 안의 내용(psmt)을 먼저 지우고 나갈 수 있음
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("오류:정보를 불러올 수 없습니다.");
					e.printStackTrace();
				}
			}
			System.out.println(money+"here");
	return money;
	}
		//매출 상품명 금액
		public static String salestmenumoney(String menu){
			String selectFinalorder2= "select sum(fice) from finalorder2 where menu=";
			selectFinalorder2=selectFinalorder2+"'"+menu+"'";
			Connection con = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				con = DBUtility.getConnetction();
				psmt=con.prepareStatement(selectFinalorder2);
				rs = psmt.executeQuery();
				if (rs == null) {
					AppMain.callAlert("검색 실패:해당하는 id가 없습니다.");
					return null;
				}
				while (rs.next()) {
					money=rs.getString(1);
				}
			} catch (SQLException e) {
				AppMain.callAlert("검색 실패:해당하는 이름이 없습니다.");
				e.printStackTrace();
			} finally {
				// 1.6 자원객체를 닫아서 반납. 제일먼저(con) 부른것을 제일 나중에 그래야 안의 내용(psmt)을 먼저 지우고 나갈 수 있음
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("오류:정보를 불러올 수 없습니다.");
					e.printStackTrace();
				}
			}
			System.out.println(money+"here");
	return money;
	}
		//매출 id명 금액
		public static String salestidmoney(String userID){
			String selectFinalorder2= "select sum(fice) from finalorder2 where userID=";
			selectFinalorder2=selectFinalorder2+"'"+userID+"'";
			Connection con = null;
			PreparedStatement psmt = null;
			ResultSet rs = null;
			try {
				con = DBUtility.getConnetction();
				psmt=con.prepareStatement(selectFinalorder2);
				rs = psmt.executeQuery();
				if (rs == null) {
					AppMain.callAlert("검색 실패:해당하는 id가 없습니다.");
					return null;
				}
				while (rs.next()) {
					money=rs.getString(1);
				}
			} catch (SQLException e) {
				AppMain.callAlert("검색 실패:해당하는 이름이 없습니다.");
				e.printStackTrace();
			} finally {
				// 1.6 자원객체를 닫아서 반납. 제일먼저(con) 부른것을 제일 나중에 그래야 안의 내용(psmt)을 먼저 지우고 나갈 수 있음
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("오류:정보를 불러올 수 없습니다.");
					e.printStackTrace();
				}
			}
			System.out.println(money+"here");
	return money;
	}
}
