package com.koreait.app.member;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.member.dao.MemberDAO;
//컨트롤러(Controller)
//로그인 처리, 로그인 완료 버튼 클릭시 실행할 로직 구현
public class MemberLoginOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		MemberDAO m_dao = new MemberDAO();
		Map<String, String> user = new HashMap<>();
		user.put("member_id", request.getParameter("member_id"));
		user.put("member_pw", request.getParameter("member_pw"));
		
		String login_id = m_dao.login(user);
		//DB조회시 사용자가 입력한 아이디 및 패스워드가 일치하지 않는다면 
		//login_id에 null이 담깁니다.
		if(login_id == null) {
			//로그인 실패
			request.setAttribute("login", "false");
			forward.setRedirect(false);	//forward
			//로그인 페이지로 응답 시, 두 가지 경우가 있습니다.
			//1. 로그인 페이지로 이동(경고메세지 불필요)
			//2. 로그인 실패 시 로그인 페이지로 이동(경고메세지 필요)
			//login=false를 GET방식으로 전송하여 위 두 가지의 경우를 구분할 수 있게 합니다.
			//loginForm이 있으니까 이미 이 영역에 들어왔기 때문에 다시 FrontController에 접근할 필요가 없습니다.
			//따라서 .me가 아닌 해당 JSP 페이지인 loginForm.jsp로 보내도 됩니다.
			//forward.setPath("/member/loginForm.jsp?login=false");
			forward.setPath("/member/MemberLogin.me");
			//1. forward와 redirect 방식에 큰 차이가 없으므로 성능이 좋은 forward를 선택합니다.
			//2. 로그인 실패 시 이전에 입력한 정보를 유지시켜야 한다면 반드시 forward 방식으로 설정합니다.
		}else {
			//로그인 성공
			request.getSession().setAttribute("session_id", login_id);	//세션에 로그인된 아이디 등록
			//사용자가 입력한 로그인 정보는 응답페이지에서는 필요로 하지 않으므로 redirect로 전송합니다.
			forward.setRedirect(true);	//redirect
			//리다이렉트이므로 contextPath를 임의로 붙여서 URL을 보내줘야합니다.
			forward.setPath(request.getContextPath() + "/board/BoardList.bo");			
		}
		return forward;
	}
}
