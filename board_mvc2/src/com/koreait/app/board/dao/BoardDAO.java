package com.koreait.app.board.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.app.board.vo.BoardReplyVO;
import com.koreait.app.board.vo.BoardVO;
import com.koreait.mybatis.config.SqlMapConfig;

public class BoardDAO {
	SqlSessionFactory sessionFactory = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public BoardDAO() {
		sqlsession = sessionFactory.openSession(true);
	}
	//게시판에 작성된 모든 게시글을 가져오는 메소드
	public List<BoardVO> getBoardList(int startRow, int endRow){
		Map<String, Integer> pageMap = new HashMap<>();
		//DB조회시 필요한 시작글 번호와 끝 번호를 Map에 담아서 전달해줍니다.
		//전체 게시글을 가져온 후 연산을 하면 느리기 때문에 
		//필요한 만큼의 게시글을 가져오도록 합니다.
		pageMap.put("startRow", startRow);
		pageMap.put("endRow", endRow);
		
		List<BoardVO> boardList = sqlsession.selectList("Board.listAll",pageMap);
		return boardList;
	}
	//작성된 게시글의 총 개수를 가져옵니다.
	public int getBoardListCount() {
		return sqlsession.selectOne("Board.boardListCount");
	}
	//사용자가 작성한 게시글 내용을 BoardVO 객체에 담아서 전달받습니다.
	public boolean insertBoard(BoardVO board) {
		return (sqlsession.insert("Board.insertBoard",board) == 1) ? true : false;
	}
	//게시판의 현재 게시글 번호 가져오기
	public int getCurrentBoardSeq() {
		return sqlsession.selectOne("Board.getCurrentBoardSeq");
	}
	//게시글 상세보기를 사용자가 요청했을 때 해당 게시글의 정보를 반환
	//사용자가 요청한 게시글 번호를 전달받습니다.
	public BoardVO getDetail(int board_num) {
		return sqlsession.selectOne("Board.getDetail",board_num);
	}
	//게시글 상세보기 시, 해당 게시글의 조회수를 증가시켜줍니다.
	//상세보기를 요청할 때 마다 증가하도록 구현합니다.
	public boolean updateReadCount(int board_num) {
		return (sqlsession.update("Board.updateReadCount", board_num) == 1) ? true : false;
	}
	public void updateBoard(BoardVO b_vo) {
		sqlsession.update("Board.updateBoard",b_vo);
	}
	//게시글 삭제
	public void deleteBoard(int board_num) {
		sqlsession.delete("Board.deleteBoard",board_num);
	}
	//댓글 추가
	public boolean insertBoardReply(BoardReplyVO boardReply) {
		return (sqlsession.insert("Board.insertBoardReply", boardReply) == 1) ? true : false;
	}
	//댓글 가져오기
	public List<BoardReplyVO> getReplyList(int board_num){
		return sqlsession.selectList("Board.getReply",board_num);
	}
	//댓글 수정
	public boolean updateBoardReply(Map<String,Object> editReply) {
		return (sqlsession.update("Board.updateBoardReply", editReply) == 1) ? true : false;
	}
	//댓글 삭제
	public void deleteBoardReply(int reply_num) {
		sqlsession.delete("Board.deleteBoardReply",reply_num);
	}
	//해당 게시글의 모든 댓글 삭제
	public void deleteBoardReplyAll(int board_num) {
		sqlsession.delete("Board.deleteBoardReplyAll",board_num);
	}
	
}

