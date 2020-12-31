package task;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;
import java.util.Set;

public class MyFarm {
	//작물, 작물개수를 저장
	Map<String, Integer> myPlantList = new HashMap<>();	//내 작물 리스트 -> 작물 이름, 작물 개수를 저장
	Map<String, Integer> myItemList = new HashMap<>();	//내 상품 리스트 -> 상품명, 상품 개수를 저장
	
	//작물 선택
	public void selectPlant(MyFarm myfarm) {
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		System.out.println("작물\t재배시간\t비용\n1.꽃\t3초\t무료\n2.땅콩\t5초\t꽃 2개\n3.배추\t10초\t땅콩 5개\n4.인삼\t100초\t배추 20개");
		System.out.println("작물을 선택해주세요(뒤로가기 : 0)");
		choice = sc.nextInt();
		if(choice == 0) return;
		
		switch(choice) {
		case 1: addPlantList(new Flower(), myfarm.myPlantList); break;
		case 2: addPlantList(new Peanut(), myfarm.myPlantList); break;
		case 3: addPlantList(new Cabbage(), myfarm.myPlantList); break;
		case 4: addPlantList(new Ginseng(), myfarm.myPlantList); break;
		default: System.out.println("1~4번 중에서 입력해주세요.");
		}
	}
	//작물 구입
	public void addPlantList(Plant plant, Map<String, Integer> list) {
		//각 작물을 교환하기 위한 하위 작물의 개수 확인하고 true이면 차감
		if(!changePlant(plant, list)) {
			System.out.println("작물이 부족합니다.");
			return;
		}
		try {
			//작물마다 지정된 시간초 만큼 sleep
			Thread.sleep(plant.getCultivationTime());
			//리스트에 추가
			list.put(plant.getName(), (int)list.get(plant.getName()) + 2);
			System.out.println(plant.getName() + " 구입 성공");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	//작물 개수 확인
	public boolean changePlant(Plant plant, Map<String, Integer> list) {
		boolean check = false;
		//꽃
		if(plant instanceof Flower) {
			check = true;
		//땅콩
		}else if(plant instanceof Peanut) {
			if((int)list.get("꽃") >= 2){
				list.put("꽃", (int)list.get("꽃") - 2);
				check = true;
			}
		//배추
		}else if(plant instanceof Cabbage) {
			if((int)list.get("땅콩") >= 5){
				list.put("땅콩", (int)list.get("땅콩") - 5);
				check = true;
			}
		//인삼
		}else if(plant instanceof Ginseng) {
			if((int)list.get("배추") >= 20){
				list.put("배추", (int)list.get("배추") - 20);
				check = true;
			}
		}
		return check;
	}

	//내 아이템 보기
	public void showMyItem(MyFarm myfarm) {
		Set<Entry<String,Integer>> set = myfarm.myItemList.entrySet();
		Iterator<Entry<String,Integer>> itr = set.iterator();
		System.out.println("[내 상품 보기]");
		while(itr.hasNext()) {
			Entry<String,Integer> tmp = itr.next();
			System.out.println(tmp.getKey() +"\t"+tmp.getValue()+"개");
		}
	}
	//내 작물 보기
	public void showMyPlant(MyFarm myfarm) {
		Set<Entry<String,Integer>> set = myfarm.myPlantList.entrySet();
		Iterator<Entry<String,Integer>> itr = set.iterator();
		System.out.println("[내 작물 보기]");
		while(itr.hasNext()) {
			Entry<String,Integer> tmp = itr.next();
			System.out.println(tmp.getKey() +"\t"+tmp.getValue()+"개");
		}
	}
}
