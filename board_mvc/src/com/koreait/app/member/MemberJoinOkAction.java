package com.koreait.app.member;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.member.dao.MemberDAO;
import com.koreait.app.member.vo.MemberVO;

public class MemberJoinOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		
		MemberDAO m_dao = new MemberDAO();
		MemberVO m_vo = new MemberVO();
		
		m_vo.setMember_id(request.getParameter("member_id"));
		m_vo.setMember_pw(request.getParameter("member_pw"));
		m_vo.setMember_name(request.getParameter("member_name"));
		m_vo.setMember_age(Integer.parseInt(request.getParameter("member_age")));
		m_vo.setMember_gender(request.getParameter("member_gender"));
		m_vo.setMember_email(request.getParameter("member_email"));
		m_vo.setMember_zipcode(request.getParameter("member_zipcode"));
		m_vo.setMember_address(request.getParameter("member_address"));
		m_vo.setMember_address_detail(request.getParameter("member_address_detail"));
		m_vo.setMember_address_etc(request.getParameter("member_address_etc"));
		
		if(!m_dao.join(m_vo)) {
			//실패
			//경고메세지
			PrintWriter out = response.getWriter();
			response.setContentType("text/html;charset=utf-8");
			out.println("<script>");
			out.println("alert('회원가입 실패. 잠시 후 다시 시도해주세요');");
			out.println("</script>");
			out.close();
			return null;
		}
		//성공
		forward.setRedirect(false);
		forward.setPath("/member/MemberLogin.me");
		return forward;
	}
}


















