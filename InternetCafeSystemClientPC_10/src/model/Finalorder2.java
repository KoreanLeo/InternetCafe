package model;

public class Finalorder2 {
	private String number;
	private String id;
	private String menu;
	private String amount;
	private String fprice;
	private String way;
	private String unit;
	private String date;
	private String ask;
	
	
	
	public Finalorder2(String number, String id, String menu, String amount, String fprice, String way, String unit,
			String date, String ask) {
		super();
		this.number = number;
		this.id = id;
		this.menu = menu;
		this.amount = amount;
		this.fprice = fprice;
		this.way = way;
		this.unit = unit;
		this.date = date;
		this.ask = ask;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
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
	public String getWay() {
		return way;
	}
	public void setWay(String way) {
		this.way = way;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAsk() {
		return ask;
	}
	public void setAsk(String ask) {
		this.ask = ask;
	}
	@Override
	public String toString() {
		return "Finalorder2 [number=" + number + ", id=" + id + ", menu=" + menu + ", amount=" + amount + ", fprice="
				+ fprice + ", way=" + way + ", unit=" + unit + ", date=" + date + ", ask=" + ask + "]";
	}
	
}
