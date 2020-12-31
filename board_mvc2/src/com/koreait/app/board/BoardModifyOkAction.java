package com.koreait.app.board;

import java.io.File;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.BoardDAO;
import com.koreait.app.board.dao.FilesDAO;
import com.koreait.app.board.vo.BoardVO;
import com.koreait.app.board.vo.FilesVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardModifyOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		BoardVO b_vo = new BoardVO();
		BoardDAO b_dao = new BoardDAO();
		FilesDAO f_dao = new FilesDAO();
		ActionForward forward = new ActionForward();
		String saveFolder = "C:\\Users\\soonho\\Desktop\\it\\국비교육\\JSP\\workspace\\board_mvc2\\WebContent\\app\\upload";
		int fileSize = 5 * 1024 * 1024;
		
		MultipartRequest multi = null;
		try {
			multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
			
			int board_num = Integer.parseInt(multi.getParameter("seq"));
			//경로에 있는 파일 삭제
			for(FilesVO file : f_dao.getDetail(board_num)) {
				File f = new File(saveFolder + "\\" + file.getFile_name()); 
				if(f.exists()) {
					f.delete();
				}
			}
			//DB삭제
			f_dao.deleteFiles(board_num);
			f_dao.insertFiles(board_num, multi);
			
			b_vo.setBoard_num(board_num);
			b_vo.setBoard_title(multi.getParameter("board_title"));
			b_vo.setBoard_contents(multi.getParameter("board_contents"));
			
			b_dao.updateBoard(b_vo);
			
			forward.setRedirect(true);
			forward.setPath(request.getContextPath() + "/board/BoardView.bo?seq=" + board_num);
			
			return forward;
		} catch (Exception e) {
			PrintWriter out = response.getWriter();
			response.setContentType("text/html; charset=UTF-8");
			out.println("<script>");
			out.println("alert('게시글 수정 실패. 다시 시도해주세요.);");
			out.println("</script>");
			out.close();
		}
		
		
		return null;
	}
}
