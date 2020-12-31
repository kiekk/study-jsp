package task_teacher;

public class Potato extends Plant {
	public Potato(int cnt, String name, long time, int cost) {
		super(cnt, name, time, cost);
	}
	
	
	@Override
	public boolean check(Plant plant) {
		if(plant instanceof Tomato) {
			if(plant.cnt >= this.cost) {
				return true;
			}
		}
		return false;
	}
	
}
