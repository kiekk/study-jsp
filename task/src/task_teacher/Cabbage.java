package task_teacher;

public class Cabbage extends Plant{
	public Cabbage(int cnt, String name, long time, int cost) {
		super(cnt, name, time, cost);
	}
	
	
	@Override
	public boolean check(Plant plant) {
		if(plant instanceof Potato) {
			if(plant.cnt >= this.cost) {
				return true;
			}
		}
		return false;
	}
	
}
