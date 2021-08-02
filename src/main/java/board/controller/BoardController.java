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

// 컨트롤러 : 클라이언트의 요청을 받아 필요한 비즈니스 로직을 호출하고 결과를 포함해 응답을 주는 디스패처 역할
/* 프로세스
 * 1. @Controller 어노테이션 적용
 * 2. @RequestMapping 으로 요청에 대한 주소 지정
 * 3. 요청에 필요한 비즈니스 로직 호출
 * 4. 실행된 비즈니스 로직의 결과를 view 로 리턴
 */
@Controller
@Slf4j
public class BoardController {
	
	// Slf4j 어노테이션 선언 시 log 변수 필요없음.
//	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	// 비즈니스 로직을 처리하는 서비스 Bean 연결
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/board/openBoardList.do")
	public ModelAndView openBoardList() throws Exception {
//		int i = 10 / 0; // 에러 발생용
		
		log.debug("openBoardList");
		// 호출된 요청의 결과를 보여줄 뷰 지정
		// templates 폴더 아래의 board/boardList.html 파일을 의미
		ModelAndView mv = new ModelAndView("/board/boardList");
		
		// 비즈니스 로직의 결과 값을 뷰에 list 라는 이름으로 저장
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
