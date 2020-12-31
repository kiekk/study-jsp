package com.koreait.vo;

public class MemberVO {
	private String name;
	private String id;
	private String pw;
	private String gender;
	private String zipcode;
	private String address;
	private String address_detail;
	private String address_etc;
	private String hp;
	private String[] hobby;
	
	/*
	@Override
	public String toString() {
		return "MemberVO [name=" + name + ", id=" + id + ", gender=" + gender + ", zipcode=" + zipcode
				+ ", address=" + address + ", address_detail=" + address_detail + ", address_etc=" + address_etc
				+ ", hp=" + hp + ", hobby=" + Arrays.toString(hobby) + "]";
	}
	*/
	@Override
	public String toString() {
		String str = this.name + ", " + this.id + ", " + this.gender + ", " + this.zipcode + ", " + this.address + ", " + this.address_detail
				+ ", " + this.address_etc + ", "+ this.hp + ", ";
	      
	      for (int i = 0; i < hobby.length; i++) {
	         str += hobby[i] + " ";
	      }
	      return str;
	}

	public MemberVO(){;}

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

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress_detail() {
		return address_detail;
	}

	public void setAddress_detail(String address_detail) {
		this.address_detail = address_detail;
	}

	public String getAddress_etc() {
		return address_etc;
	}

	public void setAddress_etc(String address_etc) {
		this.address_etc = address_etc;
	}

	public String getHp() {
		return hp;
	}

	public void setHp(String hp) {
		this.hp = hp;
	}

	public String[] getHobby() {
		return hobby;
	}

	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}
	
	
}
