package board.service;

import java.util.List;

import board.dto.BoardDto;

public interface BoardService {
	
	List<BoardDto> selectBoardList() throws Exception;

	void insertBoard(BoardDto board) throws Exception;

	BoardDto selectBoardDetail(int id) throws Exception;

	void updateBoard(BoardDto board);

	void deleteBoard(int id);
}
