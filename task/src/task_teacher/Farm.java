package task_teacher;

import java.util.Scanner;

public class Farm {
	public static void main(String[] args) {
		Flower flower = new Flower(0,"진달래",3000);
		Tomato tomato = new Tomato(0,"토마토",5000,2);
		Potato potato = new Potato(0,"감자",10000,5);
		Cabbage cabbage = new Cabbage(0, "배추", 100000, 20);
		
		//위에 생성한 객체들은 규칙성이 없기 때문에, 규칙성을 부여하기 위해 배열에 저장
		//배열에 저장하기 위해 upcasting 사용
		Plant[] plants = {flower, tomato, potato, cabbage};
		int choice = 0;
		
		while(true) {
			System.out.println("1.진달래\n2.토마토\n3.감자\n4.배추");
			choice = new Scanner(System.in).nextInt();
			
			//진달래일 때(check()가 없기 때문에 바로 구현)
			if(choice == 1) {
				System.out.println("...재배중");
				try {Thread.sleep(flower.time);} catch (InterruptedException e) {e.printStackTrace();}
				flower.grow();
				System.out.println(flower.name + "완성!" + "현재" + flower.name + "개수 : " + flower.cnt);
				continue;
			}
			//그외 다른 작물일 때(for문)
			for (int i = 0; i < plants.length; i++) {
				if((choice - 1) == i) {
					//재배할 때 필요한 재료작물의 개수 체크
					if(plants[i].check(plants[i-1])) {
						System.out.println("...재배중");
						//먼저 해당 작물 개수를 증가 시킵니다.
						plants[i].grow();
						//작물 재배 시간만큼 기다려줍니다.
						try {Thread.sleep(plants[i].time);} catch (InterruptedException e) {e.printStackTrace();}
						//재배작물에서 필요한 재료 개수만큼 해당 작물에서 차감해줍니다.
						plants[i-1].cnt -= plants[i].cost;
						System.out.println(plants[i].name + "완성!" + "현재" + plants[i].name + "개수 : " + plants[i].cnt);
					}else {
						System.out.println(plants[i-1].name + "개수 혹은 재료의 종류를 다시 확인해주세요.");
					}
					break;
				}
			}
		}
	}
}
