package com.koreait.app.board;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.ActionForward;
import com.koreait.app.member.MemberCheckIdOkAction;
import com.koreait.app.member.MemberJoinOkAction;
import com.koreait.app.member.MemberLoginOkAction;

public class BoardFrontController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = requestURI.substring(contextPath.length());
		ActionForward forward = null;
		
		switch(command) {
		case "/board/BoardList.bo":		//게시판 이동 전 게시판 페이징 처리
			try {
				forward = new BoardListAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardWrite.bo":	//게시판 글쓰기 페이지
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/board/boardWrite.jsp");
			break;
		case "/board/BoardWriteOk.bo":	//게시판 글쓰기 처리
			try {
				forward = new BoardWriteOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardView.bo" :	//게시판 상세보기
			try {
				forward = new BoardViewAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/FileDownload.bo":	//파일 다운로드
			try {
				forward = new FileDownloadAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardReplyOk.bo" :	//댓글 추가 처리
			try {
				forward = new BoardReplyOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardReplyModifyOk.bo" :	//댓글 수정 처리
			try {
				forward = new BoardReplyModifyOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardReplyDeleteOk.bo":
			try {
				forward = new BoardReplyDeleteOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardModify.bo":	//게시판 수정 페이지 이동
			try {
				forward = new BoardModifyAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardModifyOk.bo":	//게시글 수정 처리
			try {
				forward = new BoardModifyOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		case "/board/BoardDeleteOk.bo":
			try {
				forward = new BoardDeleteOkAction().execute(request, response);
			} catch (Exception e) {
				e.printStackTrace();
			}
			break;
		default : 
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/app/error/404.jsp");
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher(forward.getPath());
				dispatcher.forward(request, response);
			}
		}
	}

}
