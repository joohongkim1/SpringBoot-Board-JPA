package board.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import board.dto.BoardDto;
import board.dto.BoardFileDto;

@Mapper
public interface BoardMapper {
	List<BoardDto> selectBoardList() throws Exception;
	
	void insertBoard(BoardDto board) throws Exception;

	void updateHit(int id);

	BoardDto selectBoardDetail(int id);

	void updateBoard(BoardDto board);

	void deleteBoard(int id);

	void insertBoardFileList(List<BoardFileDto> list);
	
	List<BoardFileDto> selectBoardFileList(int id);
	
	BoardFileDto selectBoardFileInfo(@Param("id") int id, @Param("boardId") int boardId);
}
