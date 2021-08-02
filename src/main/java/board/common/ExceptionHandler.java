package board.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ExceptionHandler {
	
	// �ش� �޼ҵ忡�� ó���� ���� ����, Exception.class -> ��� ���� ó��
	// ���� ������Ʈ������ ��Ȳ�� �°� ���� ���� ó�� �ʿ�
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ModelAndView defaultExceptionHandler(HttpServletRequest request, Exception exception) {
		// ���� �߻� �� ������ ȭ�� ����
		ModelAndView mv = new ModelAndView("/error/error_default");
		mv.addObject("exception", exception);
		
		log.error("exception", exception);
		
		return mv;
	}
}
