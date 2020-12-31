package com.koreait.app.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.member.dao.MemberDAO;
//컨트롤러(Controller)
//회원가입 시, 아이디 중복검사
public class MemberCheckIdOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		MemberDAO m_dao = new MemberDAO();
		
		String member_id = request.getParameter("member_id");
		//outㅇ로 작성한 문자열 환경을 text,html로 설정합니다.
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		//DB 처리
		if(m_dao.checkId(member_id)){
			out.println("not-ok");
		}else {
			out.println("ok");
		}
		request.setAttribute("check", "안녕하세요");
		out.close();
		//비동기 통신으로 요청하기 때문에 응답 페이지는 필요하지 않습니다.
		return null;
	}
}
