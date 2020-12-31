package com.koreait.app.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.BoardDAO;

public class BoardReplyDeleteOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		BoardDAO b_dao = new BoardDAO();
		ActionForward forward = new ActionForward();
		
		int reply_num = Integer.parseInt(request.getParameter("reply_num"));
		int board_num = Integer.parseInt(request.getParameter("seq"));
		
		//댓글 삭제
		b_dao.deleteBoardReply(reply_num);
		
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/board/BoardView.bo?seq=" + board_num);
		return forward;
	}
}
