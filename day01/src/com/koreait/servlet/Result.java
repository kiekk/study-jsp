package com.koreait.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Result
 */
@WebServlet("/Result")
public class Result extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Result() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//응답할때 contentType을 전달한 값으로 셋팅
		response.setContentType("text/html; charset=UTF-8");
		//전달받은 한글을 깨지지 않도록 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		//Calc 객체 생성(사칙연산 메소드 호출 목적)
		Calc calc = new Calc();
		//error메세지 출력용 객체
		Messages msg = new Messages();
		//사칙연산 연산자를 문자열 배열로 저장
		String operators = "+-/*";
		//입력한 수식의 연산자를 저장
		char operator = ' ';
		//입력한 수식의 값을 저장
		String answer = request.getParameter("calc");
		//입력한 정수값 2개를 저장
		String[] twoNumbers = {};
		//사칙연산 실행 후 결과 값 저장
		int result = 0;
		//출력할 메세지의 내용을 저장 -> result는 아직 연산 전이기 때문에 저장 X
		String message = "";
		//html코드를 작성하기 위해서 getWriter()를 out에 담아줍니다.
		PrintWriter out = response.getWriter();
		boolean check = false;
		//입력한 수식에서 어떤 연산자가 있는지 확인
		for(int i=0;i<operators.length();i++) {
			//answer에 +=/*가 없다면 indexof(연산자)의 값이 -1이 반환되기 때문에 아닐 경우에만 if문 안의 내용이 실행
			if((answer.indexOf(operators.charAt(i)) != -1)) {
				check = true;
				//해당 연산자를 operator 변수에 저장
				operator = operators.charAt(i);
				//answer를 연산자로 split을 한 후 반환되는 문자열 타입의 두 정수를 저장
				twoNumbers = answer.split("\\" + operator);
				break;
			}
		}
		try {
			if(!check) {throw new ArrayIndexOutOfBoundsException();}
			//각 연산자에 맞게 해당 메소드를 호출하여 문자열 타입이기 떄문에 int형으로 형변환 후 전달
			int num1 = Integer.parseInt(twoNumbers[0]);
			int num2 = Integer.parseInt(twoNumbers[1]);
			
			switch(operator) {
			case '+': 
				result = calc.addTwoNumbers(num1, num2); break;
			case '-': 
				result = calc.subtractTwoNumbers(num1, num2); break;
			case '/': 
				result = calc.divideTwoNumbers(num1, num2); break;
			case '*': 
				result = calc.multiplyTwoNumbers(num1, num2); break;
			}
			//연산이 정상적으로 실행됐다면 message에 result값을 추가
			message = num1 + " " + operator + " " + num2 + " = " + result;
		}catch (NumberFormatException e) {
			//수식이 올바르지 않은 경우
			//한글입력 or 연산자 중복 사용
			message = msg.errorMsg1;
		}catch (ArithmeticException e1) {
			//0으로 나눈 경우
			message = msg.errorMsg2;
		}catch (ArrayIndexOutOfBoundsException e2) {
			//연산자가 없는 경우
			message = msg.errorMsg3;
		}finally {
			//최종 결과를 출력
			out.println("<html><body><h1>계산기에서 입력한 수식의 결과</h1>");
			out.println("<textarea row='1' readonly style='resize:none'> " + message + "</textarea>");
			out.println("<a href='calc'>다시 계산하기</a></body></html>");
			if(out != null) {out.close();}	
		}

	}


}
