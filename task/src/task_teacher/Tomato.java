package task_teacher;

public class Tomato extends Plant {
	public Tomato(int cnt, String name, long time, int cost) {
		super(cnt, name, time, cost);
	}
	
	
	@Override
	public boolean check(Plant plant) {
		if(plant instanceof Flower) {
			if(plant.cnt >= this.cost) {
				return true;
			}
		}
		return false;
	}
	
}
