package com.koreait.app.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.BoardDAO;
import com.koreait.app.board.dao.FilesDAO;
import com.koreait.app.board.vo.BoardReplyVO;
import com.koreait.app.board.vo.BoardVO;
import com.koreait.app.board.vo.FilesVO;

public class BoardViewAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		BoardDAO b_dao = new BoardDAO();
		FilesDAO f_dao = new FilesDAO();
		BoardVO b_vo = new BoardVO();
		
		int board_num = Integer.parseInt(request.getParameter("seq"));
		//사용자가 요청한 게시글의 번호를 통해 게시글 정보를 모두 가져옵니다.
		b_vo = b_dao.getDetail(board_num);
		//해당 게시글에 첨부된 파일정보를 모두 가져옵니다.
		List<FilesVO> filesList = f_dao.getDetail(board_num);
		List<BoardReplyVO> replyBeanList = b_dao.getReplyList(board_num);
		ActionForward forward = null;
		if(b_vo != null) {
			request.setAttribute("boardBean", b_vo);	
			request.setAttribute("replyBeanList", replyBeanList);
			if(filesList != null) {
				request.setAttribute("filesBeanList", filesList);
				//현재 로그인된 id와 게시글의 id가 일치하지 않을 경우에만 조회수를 증가시킵니다.
				if(!b_vo.getBoard_id().equals(request.getSession().getAttribute("session_id"))) {
					//조회수 증가가 성공하면 다시 해당 페이지로 이동합니다.
					if(b_dao.updateReadCount(board_num)) {
						forward = new ActionForward();
						forward.setRedirect(false);
						forward.setPath("/app/board/boardView.jsp");
					}
				}else {
					//로그인된 id와 게시글의 id가 일치하면 조회수 증가없이 해당 페이지로 이동합니다.
					forward = new ActionForward();
					forward.setRedirect(false);
					forward.setPath("/app/board/boardView.jsp");		
				}
			}
		}
		
		return forward;
	}
}
