package board.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import board.board.dto.BoardDto;
import board.board.service.BoardService;

// ��Ʈ�ѷ� : Ŭ���̾�Ʈ�� ��û�� �޾� �ʿ��� ����Ͻ� ������ ȣ���ϰ� ����� ������ ������ �ִ� ����ó ����
/* ���μ���
 * 1. @Controller ������̼� ����
 * 2. @RequestMapping ���� ��û�� ���� �ּ� ����
 * 3. ��û�� �ʿ��� ����Ͻ� ���� ȣ��
 * 4. ����� ����Ͻ� ������ ����� view �� ����
 */
@Controller
public class BoardController {
	
	// ����Ͻ� ������ ó���ϴ� ���� Bean ����
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception {
		// ȣ��� ��û�� ����� ������ �� ����
		// templates ���� �Ʒ��� board/boardList.html ������ �ǹ�
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		// ����Ͻ� ������ ��� ���� �信 list ��� �̸����� ����
		List<BoardDto> list = boardService.selectBoardList();
		mv.addObject("list", list);
		
		return mv;
	}
	
	@RequestMapping("/board/openBoardWrite.do")
	public String openBoardWrite() throws Exception {
		return "/board/boardWrite";
	}
	
	@RequestMapping("/board/insertBoard.do")
	public String insertBoard(BoardDto board) throws Exception {
		boardService.insertBoard(board);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int id) throws Exception {
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		BoardDto board = boardService.selectBoardDetail(id);
		mv.addObject("board", board);
		return mv;
	}

}
