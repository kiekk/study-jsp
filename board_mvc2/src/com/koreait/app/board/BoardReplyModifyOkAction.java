package com.koreait.app.board;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.BoardDAO;

public class BoardReplyModifyOkAction implements Action  {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		BoardDAO b_dao = new BoardDAO();
		ActionForward forward = null;
		
		int board_num = Integer.parseInt(request.getParameter("seq"));
		int reply_num = Integer.parseInt(request.getParameter("reply_num"));
		String reply_contents = request.getParameter("board_contents"+request.getParameter("num"));
		
		Map<String, Object> editReply = new HashMap<>();
		editReply.put("reply_num", reply_num);
		editReply.put("reply_contents", reply_contents);
		if(b_dao.updateBoardReply(editReply)) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(request.getContextPath() + "/board/BoardView.bo?seq=" + board_num);
			return forward;
		}
		PrintWriter out = response.getWriter();
		out.println("<script>");
		out.println("alert('댓글 등록 실패. 다시 시도해 주세요.');");
		out.println("</script>");
		out.close();		
		return null;
	}
}
