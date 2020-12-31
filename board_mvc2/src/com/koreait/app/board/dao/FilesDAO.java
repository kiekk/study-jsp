package com.koreait.app.board.dao;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.koreait.app.board.vo.FilesVO;
import com.koreait.mybatis.config.SqlMapConfig;
import com.oreilly.servlet.MultipartRequest;

public class FilesDAO {
	SqlSessionFactory sessionf = SqlMapConfig.getSqlMapInstance();
	SqlSession sqlsession;
	
	public FilesDAO() {
		sqlsession = sessionf.openSession(true);
	}
	//첨부파일이 여러 개일 수 있기 때문에 list로 받습니다.
	public List<FilesVO> getDetail(int board_num) {
		return sqlsession.selectList("Files.getDetail",board_num);
	}
	public boolean insertFiles(int board_num, MultipartRequest multi) {
		Enumeration<String> files = multi.getFileNames();
		Map<String, Object> fileMap = new HashMap<String, Object>();
		fileMap.put("board_num", board_num);
		
		boolean check = true;
		
		while(files.hasMoreElements()) {
			String data = files.nextElement();
			if(multi.getFilesystemName(data) == null) {continue;}
			//hashMap에 file_name으로 시스템 이름을 넣어줍니다.	
			fileMap.put("file_name", multi.getFilesystemName(data));
			//DB에 해당 파일 시스템 이름 추가
			if(sqlsession.insert("Files.insertFile",fileMap) != 1) {
				check = false;
				break;
				//return false;도 가능
			}
		}
		return check;
	}
	public void deleteFiles(int board_num) {
		sqlsession.delete("Files.deleteFiles",board_num);
	}
}
