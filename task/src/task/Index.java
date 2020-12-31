package task;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Index {
	static Scanner sc = new Scanner(System.in);

	//시작 화면
	public void mainPage() {
		MyFarm myfarm = new MyFarm();
		Shop shop = new Shop();
		//메뉴 선택값 저장
		int choice = -1;
		//종료시 확인 여부 저장하기 위한 플래그 변수
		boolean flag = false;
		//작물 리스트 초기화
		myfarm.myPlantList.put(new Flower().getName(), 0);
		myfarm.myPlantList.put(new Peanut().getName(), 0);
		myfarm.myPlantList.put(new Cabbage().getName(), 0);
		myfarm.myPlantList.put(new Ginseng().getName(), 0);
		//아이템 리스트 초기화
		myfarm.myItemList.put(shop.items[0], 0);
		myfarm.myItemList.put(shop.items[1], 0);
		myfarm.myItemList.put(shop.items[2], 0);
		myfarm.myItemList.put(shop.items[3], 0);
		try {
			while(!flag) {
				System.out.println("KOREA 농장에 오신 것을 환영합니다.");
				System.out.println("[작물을 재배해서 다양한 상품으로 교환하자!]");
				System.out.println("1. 작물 선택\n2. 내 작물 보기\n3. 상점 쇼핑\n4. 내 상품 보기\n5. 그만 하기");
				
				choice = sc.nextInt();
				
				switch(choice) {
				case 1: myfarm.selectPlant(myfarm); break;
				case 2: myfarm.showMyPlant(myfarm); break;
				case 3: shop.shopping(myfarm); break;
				case 4: myfarm.showMyItem(myfarm);; break;
				case 5: flag = quit(); break;
				default: System.out.println("1~5 중에서 입력해주세요");
				}//switch		
			}//while
		} catch (InputMismatchException ime) {
			System.out.println("잘 못 입력하셨습니다.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//start
	
	//종료 물어보기
	public boolean quit() {
		int choice = 0;
		System.out.println("데이터는 저장되지 않습니다.\n정말 종료하시겠습니까?\n1.예\t2.아니오");
		choice = sc.nextInt();
			switch(choice) {
			case 1:	System.out.println("종료합니다.\n안녕히 가세요");
					return true;
			case 2: System.out.println("취소합니다."); break;
			default : System.out.println("1 ~ 2번 중에서 입력해주세요"); break;
		}
		return false;
	}//quit
	public static void main(String[] args) {
		new Index().mainPage();
	}//main
}
