package board.controller;

import java.util.List;
import java.io.File;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;


import board.dto.BoardDto;
import board.dto.BoardFileDto;
import board.service.BoardService;
import lombok.extern.slf4j.Slf4j;

// ��Ʈ�ѷ� : Ŭ���̾�Ʈ�� ��û�� �޾� �ʿ��� ����Ͻ� ������ ȣ���ϰ� ����� ������ ������ �ִ� ����ó ����
/* ���μ���
 * 1. @Controller ������̼� ����
 * 2. @RequestMapping ���� ��û�� ���� �ּ� ����
 * 3. ��û�� �ʿ��� ����Ͻ� ���� ȣ��
 * 4. ����� ����Ͻ� ������ ����� view �� ����
 */
@Controller
@Slf4j
public class BoardController {
	
	// Slf4j ������̼� ���� �� log ���� �ʿ����.
//	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	// ����Ͻ� ������ ó���ϴ� ���� Bean ����
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception {
//		int i = 10 / 0; // ���� �߻���
		
		log.debug("openBoardList");
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
	public String insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		boardService.insertBoard(board, multipartHttpServletRequest);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("/board/openBoardDetail.do")
	public ModelAndView openBoardDetail(@RequestParam int id) throws Exception {
		ModelAndView mv = new ModelAndView("/board/boardDetail");
		BoardDto board = boardService.selectBoardDetail(id);
		mv.addObject("board", board);
		return mv;
	}
	
	@RequestMapping("/board/updateBoard.do")
	public String updateBoard(BoardDto board) throws Exception {
		boardService.updateBoard(board);
		return "redirect:/board/openBoardList.do";
				
	}
	
	@RequestMapping("/board/deleteBoard.do")
	public String deleteBoard(int id) throws Exception {
		boardService.deleteBoard(id);
		return "redirect:/board/openBoardList.do";
	}
	
	@RequestMapping("board/downloadBoardFile.do")
	public void downloadBoardFile(@RequestParam int id, @RequestParam int boardId, HttpServletResponse response) throws Exception {
		BoardFileDto boardFile = boardService.selectBoardFileInfo(id, boardId);
		if(ObjectUtils.isEmpty(boardFile) == false) {
			String fileName = boardFile.getOriginalFileName();
			
			byte[] files = FileUtils.readFileToByteArray(new File(boardFile.getStoredFilePath()));
			
			response.setContentType("application/octet-stream");
			response.setContentLength(files.length);
			response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(fileName, "UTF-8")+"\";");
			
			response.getOutputStream().write(files);
			response.getOutputStream().flush();
			response.getOutputStream().close();
		}
	}
	

}
