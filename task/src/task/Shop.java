package task;

import java.util.Map;
import java.util.Scanner;

public class Shop {
	String[] items = {"문화상품권(5000원)","문화상품권(10000원)","나이키 운동화","엄청 좋은 그래픽 카드"};

	//쇼핑 메뉴
	public void shopping(MyFarm myfarm) {
		Scanner sc = new Scanner(System.in);
		int choice = -1;
		while(true) {
			System.out.println("\t[쇼핑하기]");
			System.out.println("상품명\t\t\t비용\n1."+ items[0] + "\t배추 2개\n2."+ items[1] + 
					"\t배추 3개\n3." + items[2] + "\t\t배추 10개\n4." + items[3] + "\t인삼 1개\n(뒤로가기 : 0번)");
			choice = sc.nextInt();
			if(choice == 0) break;
			else if(choice >=1 || choice <= 4) {
				addItemList(myfarm, items[choice-1], choice);
			}
			else System.out.println("1~4번 중에서 입력해주세요");
		}
	}
	
	//상품 구입
	public void addItemList(MyFarm myfarm, String item, int choice) {
		//상품 비용 확인 후 구입 가능할 시 해당 작물 차감
		if(!changeItem(myfarm.myPlantList, choice)) {
			System.out.println("작물이 부족합니다.");
			return;
		}
		//상품 추가
		myfarm.myItemList.put(item, (int)myfarm.myItemList.get(item) + 1); 
		System.out.println(item + " 구입 성공");
		
	}
	//상품 비용 확인
	public boolean changeItem(Map<String,Integer> list, int choice) {
		boolean check = false;

		switch(choice) {
		//문화상품권(5000원)
		case 1: 
			if((int)list.get("배추") >= 2){
				list.put("배추", (int)list.get("배추") - 2);
				check = true;
			}
			break;
		//문화상품권(10000원)
		case 2:
			if((int)list.get("배추") >= 3){
				list.put("배추", (int)list.get("배추") - 3);
				check = true;
			}
			break;
		//나이키 운동화
		case 3:
			if((int)list.get("배추") >= 10){
				list.put("배추", (int)list.get("배추") - 10);
				check = true;
			}
			break;
		//엄청 좋은 그래픽 카드
		case 4:
			if((int)list.get("인삼") >= 1){
				list.put("인삼", (int)list.get("인삼") - 2);
				check = true;
			}
			break;
		}
		
		return check;
	}
}
