package com.koreait.app.board;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.koreait.action.Action;
import com.koreait.action.ActionForward;
import com.koreait.app.board.dao.BoardDAO;
import com.koreait.app.board.dao.FilesDAO;
import com.koreait.app.board.vo.BoardVO;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class BoardWriteOkAction implements Action{
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("UTF-8");
		
		BoardDAO b_dao = new BoardDAO();
		BoardVO b_vo = new BoardVO();
		FilesDAO f_dao = new FilesDAO();
		ActionForward forward = new ActionForward();
		
		//첨부한 파일이 업로드 될 서버 경로 설정
		//request.getServletContext().gerRealPath("/") + "\\upload" 서버 PC 경로 구하기
		String saveFolder = "C:\\Users\\soonho\\Desktop\\it\\국비교육\\JSP\\workspace\\board_mvc2\\WebContent\\app\\upload";
		//첨부 파일의 크기 설정
		int fileSize = 5 * 1024 * 1024; //5mb
										//1024바이트 = 킬로바이트
										//1024 * 1024 = 메가바이트
		//메일 서버 객체(파일 업로드 및 다운로드)선언	//바이트 -> 킬로바이트 -> 메가바이트 -> 기가바이트 -> 테라바이트 -> 페타바이트
		MultipartRequest multi = null;
		
		//DefaultFileRenamePolicy : 파일 업로드 및 다운로드 정책(같은 이름이 존재하면 자동으로 이름이 변경되도록 합니다.)
		multi = new MultipartRequest(request, saveFolder, fileSize, "UTF-8", new DefaultFileRenamePolicy());
		//MultipartRequest에 request객체를 전달하기 때문에 요청된 파라미터는 모두
		//multi 객체를 통해서 전달받아야 합니다.
		b_vo.setBoard_title(multi.getParameter("board_title"));
		b_vo.setBoard_contents(multi.getParameter("board_contents"));
		b_vo.setBoard_id(multi.getParameter("board_id"));
		
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		//먼저 게시글을 등록해야 시퀀스가 생성되서 조회됩니다.
		//따라서 게시글을 먼저 등록한 뒤,
		//파일 생성을 위해 시퀀스를 전달해야 오류 없이 정상 작동합니다.
		//NEXTVAL 사용 후 CURRVAL을 사용합니다.
		if(b_dao.insertBoard(b_vo)) {
			if(f_dao.insertFiles(b_dao.getCurrentBoardSeq(), multi)) {
				forward.setRedirect(true);
				forward.setPath(request.getContextPath() + "/board/BoardList.bo");
				//forward로 응답을 하기 위해서는 PrintWriter로 어떠한 응답도 해서는 안됩니다.
				//응답을 반드시 딱 한 번만 할 수 있다.
				return forward;
			}
		}
		out.println("<script>");
		out.println("alert('게시글 등록 실패. 다시 시도해 주세요.');");
		out.println("</script>");
		out.close();
		return null;
	}
}
