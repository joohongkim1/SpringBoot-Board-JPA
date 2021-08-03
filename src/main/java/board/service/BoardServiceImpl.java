package board.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.common.FileUtils;
import board.dto.BoardDto;
import board.dto.BoardFileDto;
import board.mapper.BoardMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {
	
	// DB 에 접근하는 DAO Bean 
	@Autowired 
	private BoardMapper boardMapper;
	
	@Autowired
	private FileUtils fileUtils;
	
	@Override
	public List<BoardDto> selectBoardList() throws Exception {
		return boardMapper.selectBoardList();
	}

	@Override
	public void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		boardMapper.insertBoard(board);
		
		List<BoardFileDto> list = fileUtils.parseFileInfo(board.getId(), multipartHttpServletRequest);
		log.debug("list : ", list);
		if(CollectionUtils.isEmpty(list) == false) {
			boardMapper.insertBoardFileList(list);
		}
	}

	@Override
	public BoardDto selectBoardDetail(int id) throws Exception {
		boardMapper.updateHit(id);
		List<BoardFileDto> fileList = boardMapper.selectBoardFileList(id);
		BoardDto board = boardMapper.selectBoardDetail(id);
		board.setFileList(fileList);
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

	@Override
	public BoardFileDto selectBoardFileInfo(int id, int boardId) throws Exception {
		return boardMapper.selectBoardFileInfo(id, boardId);
	}

	@Override
	public void deleteBoardFile(int id, int boardId) {
		boardMapper.deleteBoardFile(id, boardId);
	}
	
	
	

}
