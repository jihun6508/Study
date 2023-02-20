package com.saamz.web.biz.board;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.saamz.web.biz.util.JDBCUtil;

public class BoardDAO {
	//JDBC ���� ����
	private Connection conn;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	//SQL ��ɾ�
	private static String BOARD_INSERT= "insert into board(seq, title, writer, content) values((select nvl(max(seq), 0) +1 from board), ?,?,?";//nvl =>null�̸� 0, �ƴϸ� max(seq)�� ��ȯ
	private static String BOARD_UPDATE ="update board set title=?, content=? where seq=?";
	private static String BOARD_DELETE = "delete board where seq=?";
	private static String BOARD_GET = "select * from board where seq=?";
	private static String BOARD_LIST = "select * from board order by seq desc";
	
	//CRUD ��� �޼ҵ�
	//�� ���
	public void insertBoard(BoardVO vo) {
		try {
			conn = JDBCUtil.getConection();
			stmt = conn.prepareStatement(BOARD_INSERT);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//�� ����
	public void updateBoard(BoardVO vo) {
		try {
			conn = JDBCUtil.getConection();
			stmt = conn.prepareStatement(BOARD_UPDATE);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getSeq());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
		
	//�� ����
	public void deleteBoard(BoardVO vo) {
		try {
			conn = JDBCUtil.getConection();
			stmt = conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, vo.getSeq());
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//�� ����ȸ
	public BoardVO getBoard(BoardVO vo) {
		BoardVO board = null;
		try {
			conn = JDBCUtil.getConection();
			stmt = conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, vo.getSeq());
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(stmt, conn);
		}
		return board;
	}
	
	//�� ��� �˻�
	public List<BoardVO> getBoardList(BoardVO vo){
		List<BoardVO> boardList = new ArrayList<BoardVO>();
		try {
			conn = JDBCUtil.getConection();
			stmt = conn.prepareStatement(BOARD_LIST);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				BoardVO board =  new BoardVO();
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
				boardList.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(rs, stmt, conn);
		}
		return boardList;
	}
		
		
}
