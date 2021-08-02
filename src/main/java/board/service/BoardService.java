package board.service;

import java.util.List;

import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.dto.BoardDto;
import board.dto.BoardFileDto;

public interface BoardService {
	
	List<BoardDto> selectBoardList() throws Exception;

	void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpsServletRequest) throws Exception;

	BoardDto selectBoardDetail(int id) throws Exception;

	void updateBoard(BoardDto board);

	void deleteBoard(int id);
	
	BoardFileDto selectBoardFileInfo(int id, int board_id) throws Exception;
}
