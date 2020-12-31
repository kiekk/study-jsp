package task_teacher;

public class Plant {
	public int cnt;
	public String name;
	public long time;
	int cost;
	
	public Plant() {;}

	public Plant(int cnt, String name, long time) {
		super();
		this.cnt = cnt;
		this.name = name;
		this.time = time;
	}
	
	public Plant(int cnt, String name, long time, int cost) {
		super();
		this.cnt = cnt;
		this.name = name;
		this.time = time;
		this.cost = cost;
	}

	//재료 유무 검사
	public boolean check(Plant plant) {return false;}
	//재배 성공시 작물 개수 1증가
	public void grow() {cnt++;}
}
