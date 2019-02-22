package model;

public class Finalorder {

	private String menu;
	private String price;
	private String amount;
	private String fprice;
	
	
	
	public Finalorder(String menu, String price, String amount) {
		super();
		this.menu = menu;
		this.price = price;
		this.amount = amount;
		int a=Integer.parseInt(price);
		int b=Integer.parseInt(amount);
		
		fprice=String.valueOf(a*b);
	}

	public String getMenu() {
		return menu;
	}



	public void setMenu(String menu) {
		this.menu = menu;
	}



	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getFprice() {
		return fprice;
	}
	public void setFprice(String fprice) {
		this.fprice = fprice;
	}
	
	
	public static void main(String[] args) {
		
		
		
		
		
	}
	
}
