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
		//����Ʈ
		public static ArrayList<Member> db1MemberList= new ArrayList<>();
		public static ArrayList<Finalorder2> db1Finalorder2List= new ArrayList<>();
		public static ArrayList<Finalorder2> db1Finalorder2List2= new ArrayList<>();
		public static String money;
		//ȸ�������� ������������
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
				AppMain.callAlert("select ���� : select ������ ���� ���˹ٶ�");
				return null;
			}
			while (rs.next()) {
				Member member = new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8));
				db1MemberList.add(member);
			}
		} catch (SQLException e) {
			AppMain.callAlert("���� ���� : ������ ���̽��� �ڷ� ���� ����\n���˹ٶ�");
			e.printStackTrace();
		} finally {
			// 1.6 �ڿ���ü�� �ݾƼ� �ݳ�. ���ϸ���(con) �θ����� ���� ���߿� �׷��� ���� ����(psmt)�� ���� ����� ���� �� ����
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AppMain.callAlert("�ݱ� ���� : �ڿ� �ڷ� psmt, con �ݱ⿡ ����\n���˹ٶ�");
				e.printStackTrace();
			}
		}
return db1MemberList;
}
		//ȸ������ �˻��� �̸� ��������
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
					AppMain.callAlert("�˻� ����:�ش��ϴ� �̸��� �����ϴ�.");
					return null;
				}
				while (rs.next()) {
					Member member = new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8));
					db1MemberList.add(member);
				}
			} catch (SQLException e) {
				AppMain.callAlert("����:������ �ҷ��� �� �����ϴ�.");
				e.printStackTrace();
			} finally {
				// 1.6 �ڿ���ü�� �ݾƼ� �ݳ�. ���ϸ���(con) �θ����� ���� ���߿� �׷��� ���� ����(psmt)�� ���� ����� ���� �� ����
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("����:������ �ҷ��� �� �����ϴ�.");
					e.printStackTrace();
				}
			}
	return db1MemberList;
	}
		//ȸ������ �˻��� ���̵� ��������
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
					AppMain.callAlert("�˻� ����:�ش��ϴ� id�� �����ϴ�.");
					return null;
				}
				while (rs.next()) {
					Member member = new Member(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5), rs.getString(6),rs.getString(7),rs.getString(8));
					db1MemberList.add(member);
				}
			} catch (SQLException e) {
				AppMain.callAlert("�˻� ����:�ش��ϴ� �̸��� �����ϴ�.");
				e.printStackTrace();
			} finally {
				// 1.6 �ڿ���ü�� �ݾƼ� �ݳ�. ���ϸ���(con) �θ����� ���� ���߿� �׷��� ���� ����(psmt)�� ���� ����� ���� �� ����
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("����:������ �ҷ��� �� �����ϴ�.");
					e.printStackTrace();
				}
			}
	return db1MemberList;
	}
		//�ֹ�â�� �ֱ�
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
				AppMain.callAlert("����:������ �ҷ��� �� �����ϴ�.");
				return null;
			}
			while (rs.next()) {
				Finalorder2 finalorder2 = new Finalorder2(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), 
						rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(9), rs.getString(8));
				db1Finalorder2List.add(finalorder2);
			}
		} catch (SQLException e) {
			AppMain.callAlert("����:������ �ҷ��� �� �����ϴ�.");
			e.printStackTrace();
		} finally {
			// 1.6 �ڿ���ü�� �ݾƼ� �ݳ�. ���ϸ���(con) �θ����� ���� ���߿� �׷��� ���� ����(psmt)�� ���� ����� ���� �� ����
			try {
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				AppMain.callAlert("����:������ �ҷ��� �� �����ϴ�.");
				e.printStackTrace();
			}
		}
return db1Finalorder2List;
}
		//ȸ������ �����ϱ�
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
						// 1.4 �������� ���������͸� �����Ѵ�.
						psmt.setString(1, member.getName());
						psmt.setString(2, member.getPassword());
						psmt.setString(3, member.getAge());
						psmt.setString(4, member.getPhonNumber());
						psmt.setString(5, member.getE_mail());
						psmt.setString(6, member.getId());
						// 1.5 ���� ������ ������ ���� �������� �����϶�. executeUpdate()�� �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������
						count = psmt.executeUpdate();
						if (count == 0) {
							AppMain.callAlert("���� ���� : ���� ����1");
							return count;
						}
					} catch (SQLException e) {
						AppMain.callAlert("���� ���� : ���� ����2");
						e.printStackTrace();
					} finally {
						try {
							if (psmt != null)
								psmt.close();
							if (con != null)
								con.close();
						} catch (SQLException e) {
							AppMain.callAlert("�ݱ� ���� : �ڿ� �ڷ� psmt, con �ݱ⿡ ����\n���˹ٶ�");
							e.printStackTrace();
						}
					}
					return count;
		}
		//ȸ������ �����ϱ�
		public static int deleteMembertData(String id) {
					String deleteStudent ="delete from member where userID = ? ";
					Connection con = null;
					// 1.3 �������� �����ؾ� �� Statement�� �����.
					PreparedStatement psmt = null;
					// 1.4 �������� �����ϰ� ���� ���� ���ڵ带 ��� �ִ� ���ڱ� ��ü
					int count =0;
					try {
						con = DBUtility.getConnetction();
						psmt = con.prepareStatement(deleteStudent);
						psmt.setString(1, id);
						// 1.5 ���� ������ ������ ���� �������� �����϶�.(���� ġ��)
						// executeQuery()�� �������� �����ؼ� ����� ������ �� ����ϴ� ������
						// executeUpdate()�� �������� �����ؼ� ���̺� ���� �� �� ����ϴ� ������
						count = psmt.executeUpdate();
						if (count == 0) {
							AppMain.callAlert("���� ���� : ������ �� �����ϴ�");
							return count;
						}
					} catch (SQLException e) {
						AppMain.callAlert("���� ���� : ������ ���̽��� �ڷ� ���� ����\n���˹ٶ�");
						e.printStackTrace();
					} finally {
						// 1.6 �ڿ���ü�� �ݾƼ� �ݳ�. ���ϸ���(con) �θ����� ���� ���߿� �׷��� ���� ����(psmt)�� ���� ����� ���� �� ����
						try {
							if (psmt != null)
								psmt.close();
							if (con != null)
								con.close();
						} catch (SQLException e) {
							AppMain.callAlert("�ݱ� ���� : �ڿ� �ڷ� psmt, con �ݱ⿡ ����\n���˹ٶ�");
							e.printStackTrace();
						}

					}
					return count;
				}
		//�Ǹ����� ��������
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
					AppMain.callAlert("�˻� ����:�ش��ϴ� id�� �����ϴ�.");
					return null;
				}
				while (rs.next()) {
					Finalorder2 finalorder2 = new Finalorder2(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5),rs.getString(6));
					db1Finalorder2List2.add(finalorder2);
				}
			} catch (SQLException e) {
				AppMain.callAlert("�˻� ����:�ش��ϴ� �̸��� �����ϴ�.");
				e.printStackTrace();
			} finally {
				// 1.6 �ڿ���ü�� �ݾƼ� �ݳ�. ���ϸ���(con) �θ����� ���� ���߿� �׷��� ���� ����(psmt)�� ���� ����� ���� �� ����
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("����:������ �ҷ��� �� �����ϴ�.");
					e.printStackTrace();
				}
			}
	return db1Finalorder2List2;
	}
		//������� ��ǰ�� �˻�
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
					AppMain.callAlert("�˻� ����:�ش��ϴ� ��ǰ���� �����ϴ�.");
					return null;
				}
				while (rs.next()) {
					Finalorder2 finalorder2 = new Finalorder2(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5),rs.getString(6));
					db1Finalorder2List2.add(finalorder2);
				}
			} catch (SQLException e) {
				AppMain.callAlert("�˻� ����:�ش��ϴ� �̸��� �����ϴ�.");
				e.printStackTrace();
			} finally {
				// 1.6 �ڿ���ü�� �ݾƼ� �ݳ�. ���ϸ���(con) �θ����� ���� ���߿� �׷��� ���� ����(psmt)�� ���� ����� ���� �� ����
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("����:������ �ҷ��� �� �����ϴ�.");
					e.printStackTrace();
				}
			}
	return db1Finalorder2List2;
	}
		//������� id�˻�
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
					AppMain.callAlert("�˻� ����:�ش��ϴ� id�� �����ϴ�.");
					return null;
				}
				while (rs.next()) {
					Finalorder2 finalorder2 = new Finalorder2(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5),rs.getString(6));
					db1Finalorder2List2.add(finalorder2);
				}
			} catch (SQLException e) {
				AppMain.callAlert("�˻� ����:�ش��ϴ� �̸��� �����ϴ�.");
				e.printStackTrace();
			} finally {
				// 1.6 �ڿ���ü�� �ݾƼ� �ݳ�. ���ϸ���(con) �θ����� ���� ���߿� �׷��� ���� ����(psmt)�� ���� ����� ���� �� ����
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("����:������ �ҷ��� �� �����ϴ�.");
					e.printStackTrace();
				}
			}
	return db1Finalorder2List2;
	}
		//�������� �˻� �������� 
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
					AppMain.callAlert("�˻� ����:�ش��ϴ� id�� �����ϴ�.");
					return null;
				}
				while (rs.next()) {
					Finalorder2 finalorder2 = new Finalorder2(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5),rs.getString(6));
					db1Finalorder2List2.add(finalorder2);
				}
			} catch (SQLException e) {
				AppMain.callAlert("�˻� ����:�ش��ϴ� �̸��� �����ϴ�.");
				e.printStackTrace();
			} finally {
				// 1.6 �ڿ���ü�� �ݾƼ� �ݳ�. ���ϸ���(con) �θ����� ���� ���߿� �׷��� ���� ����(psmt)�� ���� ����� ���� �� ����
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("����:������ �ҷ��� �� �����ϴ�.");
					e.printStackTrace();
				}
			}
	return db1Finalorder2List2;
	}
		//������� ��Ż �ݾ�
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
					AppMain.callAlert("�˻� ����:�ش��ϴ� id�� �����ϴ�.");
					return null;
				}
				while (rs.next()) {
					money=rs.getString(1);
				}
			} catch (SQLException e) {
				AppMain.callAlert("�˻� ����:�ش��ϴ� �̸��� �����ϴ�.");
				e.printStackTrace();
			} finally {
				// 1.6 �ڿ���ü�� �ݾƼ� �ݳ�. ���ϸ���(con) �θ����� ���� ���߿� �׷��� ���� ����(psmt)�� ���� ����� ���� �� ����
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("����:������ �ҷ��� �� �����ϴ�.");
					e.printStackTrace();
				}
			}
			System.out.println(money+"here");
	return money;
	}
		//���� (ī�� ����)  �ݾ�
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
					AppMain.callAlert("�˻� ����:�ش��ϴ� id�� �����ϴ�.");
					return null;
				}
				while (rs.next()) {
					money=rs.getString(1);
				}
			} catch (SQLException e) {
				AppMain.callAlert("�˻� ����:�ش��ϴ� �̸��� �����ϴ�.");
				e.printStackTrace();
			} finally {
				// 1.6 �ڿ���ü�� �ݾƼ� �ݳ�. ���ϸ���(con) �θ����� ���� ���߿� �׷��� ���� ����(psmt)�� ���� ����� ���� �� ����
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("����:������ �ҷ��� �� �����ϴ�.");
					e.printStackTrace();
				}
			}
			System.out.println(money+"here");
	return money;
	}
		//���� ��ǰ�� �ݾ�
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
					AppMain.callAlert("�˻� ����:�ش��ϴ� id�� �����ϴ�.");
					return null;
				}
				while (rs.next()) {
					money=rs.getString(1);
				}
			} catch (SQLException e) {
				AppMain.callAlert("�˻� ����:�ش��ϴ� �̸��� �����ϴ�.");
				e.printStackTrace();
			} finally {
				// 1.6 �ڿ���ü�� �ݾƼ� �ݳ�. ���ϸ���(con) �θ����� ���� ���߿� �׷��� ���� ����(psmt)�� ���� ����� ���� �� ����
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("����:������ �ҷ��� �� �����ϴ�.");
					e.printStackTrace();
				}
			}
			System.out.println(money+"here");
	return money;
	}
		//���� id�� �ݾ�
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
					AppMain.callAlert("�˻� ����:�ش��ϴ� id�� �����ϴ�.");
					return null;
				}
				while (rs.next()) {
					money=rs.getString(1);
				}
			} catch (SQLException e) {
				AppMain.callAlert("�˻� ����:�ش��ϴ� �̸��� �����ϴ�.");
				e.printStackTrace();
			} finally {
				// 1.6 �ڿ���ü�� �ݾƼ� �ݳ�. ���ϸ���(con) �θ����� ���� ���߿� �׷��� ���� ����(psmt)�� ���� ����� ���� �� ����
				try {
					if (psmt != null)
						psmt.close();
					if (con != null)
						con.close();
				} catch (SQLException e) {
					AppMain.callAlert("����:������ �ҷ��� �� �����ϴ�.");
					e.printStackTrace();
				}
			}
			System.out.println(money+"here");
	return money;
	}
}
