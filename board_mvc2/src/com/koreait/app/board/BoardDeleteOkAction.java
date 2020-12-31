package com.koreait.app.board;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.BoardDAO;
import com.koreait.app.board.dao.FilesDAO;
import com.koreait.app.board.vo.FilesVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardDeleteOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		FilesDAO f_dao = new FilesDAO();
		BoardDAO b_dao = new BoardDAO();
		ActionForward forward = new ActionForward();
		
		int board_num = Integer.parseInt(request.getParameter("seq"));
		String saveFolder = "C:\\Users\\soonho\\Desktop\\it\\국비교육\\JSP\\workspace\\board_mvc2\\WebContent\\app\\upload";
		
		//경로에 있는 파일 삭제
		for(FilesVO file : f_dao.getDetail(board_num)) {
			File f = new File(saveFolder + "\\" + file.getFile_name()); 
			if(f.exists()) {
				f.delete();
			}
		}
		//DB삭제
		f_dao.deleteFiles(board_num);	//파일
		b_dao.deleteBoardReplyAll(board_num);	//댓글
		b_dao.deleteBoard(board_num);	//게시글
		forward.setRedirect(true);
		forward.setPath(request.getContextPath() + "/board/BoardList.bo");
		
		return forward;
	
	}
}
