package com.koreait.beans;

public class MemberVO {
	private String name;
	private String birthday;
	
	//자바빈즈를 사용하면 기본 생성자를 호출할 수 있습니다.
	//따라서 명시적으로 표시하기 위해서 ;를 사용
	public MemberVO() {
		this.name ="이름이 없습니다.";
		this.birthday = "생일이 등록되지 않았습니다.";
	}

	public MemberVO(String name, String birthday) {
		this.name = name;
		this.birthday = birthday;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return this.name + "\n" + this.birthday;
	}
}
