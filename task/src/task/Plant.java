package task;

public class Plant {
	private String name;			//작물명
	private int cultivationTime;	//재배시간

	public Plant() {;}
	
	public Plant(String name, int cultivationTime) {
		this.name = name;
		this.cultivationTime = cultivationTime;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCultivationTime() {
		return cultivationTime;
	}

	public void setCultivationTime(int cultivationTime) {
		this.cultivationTime = cultivationTime;
	}
}

class Flower extends Plant {
	public Flower() {
		super("꽃",500);
	}
}
class Peanut extends Plant {
	public Peanut() {
		super("땅콩",500);
	}
}
class Cabbage extends Plant {
	public Cabbage () {
		super("배추",500);
	}
}
class Ginseng extends Plant {
	public Ginseng  () {
		super("인삼",500);
	}
}
