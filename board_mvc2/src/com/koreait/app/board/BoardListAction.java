package com.koreait.app.board;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.BoardDAO;
import com.koreait.app.board.vo.BoardVO;

public class BoardListAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		ActionForward forward = new ActionForward();
		BoardDAO b_dao = new BoardDAO();
		
		//사용자가 요청한 페이지 번호
		String temp = request.getParameter("page");
		//요청한 페이지가 없다면 default로 1을 설정
		//요청한 페이지가 있다면 해당 페이지로 응답해줍니다.
		int page = temp == null ? 1 : Integer.parseInt(temp);
		//한 페이지당 10개의 글이 보이도록 설정
		int pageSize = 10;
		//한 페이지에서 가장 마지막 글 번호
		int endRow = page * pageSize;
		//한 페이지에서 첫번째 글 번호
		int startRow = endRow - (pageSize-1);
		List<BoardVO> boardList = b_dao.getBoardList(startRow, endRow); 
		/*
		 	1. board.xml에 전체 게시글 개수를 가져오는 쿼리문 작성
		 	2. BoardDAO에 위에서 작성한 쿼리문을 사용하는 메소드 구현
		 	3. Controller(현재 페이지)에서 DAO메소드 사용
		 */
		int totalCount = b_dao.getBoardListCount();
		//전체 게시글의 개수를 페이지의 사이즈(10)을 나눈 값이 페이지의 개수, 여기서 나머지가 0보다 크다면 페이지가 하나 더 필요하기 때문에 페이지의 개수를 1 증가시킵니다.
		//int totalPage = totalCount / pageSize + (totalCount % pageSize > 0 ? 1 : 0);
		int totalPage = (totalCount - 1)/ pageSize + 1;//이렇게 작성하면 if문을 작성하지 않아도 됩니다.
		//페이지는 5개씩 묶음으로 보여줍니다.
		int pageBlock = 10;
		//하나의 응답 페이지에서 보여질 페이지 수(1~10)까지 보여줍니다.
		int startPage = ((page-1) / pageSize) * pageBlock + 1;
		int endPage = startPage + pageBlock - 1;
		
		//실제 마지막 페이지 수와 연산으로 구한 마지막 페이지 수를 비교하여 일치하도록 해줍니다.
		endPage = (endPage > totalPage) ? totalPage : endPage;
		//응답 페이지로 전달할 결과값들
		request.setAttribute("boardList", boardList);
		request.setAttribute("startPage", startPage);
		request.setAttribute("endPage", endPage);
		request.setAttribute("totalCount", totalCount);
		request.setAttribute("nowPage", page);
		request.setAttribute("totalPage", totalPage);
		
		//forward 방식으로 전송해야 request 객체에 담긴 정보를 응답 페이지에서 사용할 수 있습니다.
		forward.setRedirect(false);
		forward.setPath("/app/board/boardList.jsp");
		return forward;
	}
}
