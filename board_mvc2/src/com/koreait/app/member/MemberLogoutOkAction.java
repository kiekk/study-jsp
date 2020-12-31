package com.koreait.app.member;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;

public class MemberLogoutOkAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ActionForward forward = new ActionForward();
		
		request.getSession().invalidate();
		
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/board/BoardList.bo");
		
		return forward;
	}
}
