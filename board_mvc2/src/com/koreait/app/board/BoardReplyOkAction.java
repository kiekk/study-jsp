package com.koreait.app.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.BoardDAO;
import com.koreait.app.board.vo.BoardReplyVO;

public class BoardReplyOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		BoardDAO b_dao = new BoardDAO();
		ActionForward forward = null;
		BoardReplyVO r_vo = new BoardReplyVO();
		
		//한번에 set에 담아줘도 되지만 가독성을 위해서 변수에 먼저 담아서 전달했습니다.
		int board_num = Integer.parseInt(request.getParameter("seq"));
		String member_id = (String)request.getSession().getAttribute("session_id");
		String reply_contents = request.getParameter("reply_contents");
		
		r_vo.setBoard_num(board_num);
		r_vo.setMember_id(member_id);
		r_vo.setReply_contents(reply_contents);
		
		if(b_dao.insertBoardReply(r_vo)) {
			forward = new ActionForward();
			forward.setRedirect(true);
			forward.setPath(request.getContextPath() + "/board/BoardView.bo?seq=" + board_num);
		}else {
			PrintWriter out = response.getWriter();
			out.println("<script>");
			out.println("alert('댓글 등록 실패. 다시 시도해 주세요.');");
			out.println("</script>");
			out.close();			
		}
		
		return forward;
	}
}
