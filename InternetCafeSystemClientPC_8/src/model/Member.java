package model;

public class Member {
	private String name;
	private String id;
	private String password;
	private String gender;
	private String age;
	private String phonNumber;
	private String e_mail;
	private String date;
	public Member(String id) {
		super();
		this.id=id;
	}
	
	public Member(String name, String id, String password, String gender, String age, String phonNumber, String e_mail,
			String date) {
		super();
		this.name = name;
		this.id = id;
		this.password = password;
		this.gender = gender;
		this.age = age;
		this.phonNumber = phonNumber;
		this.e_mail = e_mail;
		this.date = date;
	}
	public Member(String id, String password) {
		super();
		this.id=id;
		this.password=password;
	}

	@Override
	public String toString() {
		return "Member [name=" + name + ", id=" + id + ", gender=" + gender + ", age=" + age + ", phonNumber="
				+ phonNumber + ", e_mail=" + e_mail + ", date=" + date + "]";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getPhonNumber() {
		return phonNumber;
	}
	public void setPhonNumber(String phonNumber) {
		this.phonNumber = phonNumber;
	}
	public String getE_mail() {
		return e_mail;
	}
	public void setE_mail(String e_mail) {
		this.e_mail = e_mail;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
