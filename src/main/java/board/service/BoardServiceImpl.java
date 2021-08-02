package board.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import board.dto.BoardDto;
import board.mapper.BoardMapper;

@Service
@Transactional
public class BoardServiceImpl implements BoardService {
	
	// DB 에 접근하는 DAO Bean 
	@Autowired 
	private BoardMapper boardMapper;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	@Override
	public void insertBoard(BoardDto board) throws Exception {
		boardMapper.insertBoard(board);
	}

	@Override
	public BoardDto selectBoardDetail(int id) throws Exception {
		boardMapper.updateHit(id);
		BoardDto board = boardMapper.selectBoardDetail(id);
		return board;
	}

	@Override
	public void updateBoard(BoardDto board) {
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(int id) {
		boardMapper.deleteBoard(id);
	}
	
	

}
