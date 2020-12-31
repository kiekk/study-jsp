package com.koreait.app.member;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;

//사용자의 요청에 따라 서블릿을 통해 오게 되는 클래스
//확장자가 .me라면 MemberFrontController로 오게됩니다.(web.xml 참고)
//FrontController에서 사용자의 요청에 맞는 응답을 하게 되며, 사용자의 요청 전송 방식(GET, POST)에 따라
//로직을 실행해야 하므로 HttpServlet을 상속받아 doGet(), doPost()를 구현합니다.
//전송방식에 상관없이, 실행되는 로직은 동일하므로 doProcess()를 구현하여 각 메소드에 사용해줍니다.

public class MemberFrontController extends HttpServlet {
	/**
	 * 직렬화, 역직렬화
	 * JVM의 메모리에서만 머물러 있던 객체를 시스템이 종료되거나 네트워크로 통신을 할 때에도
	 * 그대로 사용할 수 있도록 영구화 목적으로 직렬화를 사용합니다.
	 * 직렬화된 객체는 byte형태로 변환되어 있으며, 다시 사용하고 싶을 때에는 역직렬화를 통해서 객체로 변환됩니다.
	 * 이 때 SUID(serialVersionUID)라는 값을 고정 시켜 구분점으로 사용해서 사용자가 원하는 객체가 맞는 지 판단하게 됩니다.
	 * 자주 변경되는 클래스 객체에는 사용하지 않는 것이 좋습니다.
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청한 URI
		String requestURI = request.getRequestURI();
		//반복되는 기본 경로(www.naver.com, board_mvc2 등)
		String contextPath = request.getContextPath();
		//요청한 URI에서 반복되는 기본 경로를 제외한 경로(사용자 요청의 종류를 확인할 수 있습니다.)
		String command = requestURI.substring(contextPath.length());
		//전송방식, 응답경로가 선언된 클래스
		ActionForward forward = null;
		//분기 처리(사용자의 요청에 맞는 응답을 위함)
		switch(command) {
		case "/member/MemberJoin.me":		//회원가입 페이지 이동
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/member/joinForm.jsp");
			break;
		case "/member/MemberJoinOk.me":		//회원가입 처리
			try {
				forward = new MemberJoinOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/member/MemberCheckId.me":	//아이디 중복 처리
			try {
				forward = new MemberCheckIdOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/member/MemberLogin.me":		//로그인 페이지 이동
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/member/loginForm.jsp");
			break;
		case "/member/MemberLoginOk.me":	//로그인 처리
			try {
				forward = new MemberLoginOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/member/MemberLogOut.me" :	//로그아웃 처리
			try {
				forward = new MemberLogoutOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}	
			break;
		default : 		//잘못된 경로일 경우
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/error/404.jsp");
		}
		//forward가 null이 아니라면, 어떠한 이동을 이 곳에서 처리하겠다는 의미
		if(forward != null) {
			if(forward.isRedirect()) {
				//전송 방식이 redirect일 경우
				response.sendRedirect(forward.getPath());
			}else {
				//전송방식이 forward일 경우
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}
}
