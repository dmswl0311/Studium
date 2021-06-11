package board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import board.dto.BoardDto;
import board.dto.StudyDto;
import board.service.BoardService;
import board.service.StudyService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ReactController {
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private StudyService studyService;

	// get all board 
	@GetMapping("/board")
	public List<BoardDto> getAllBoards() throws Exception {
		// 임시로 넣어둔 studyId 값
		int studyId=1;
		return boardService.selectBoardList(studyId);
	}
	
	@GetMapping("/home.do")
	public List<StudyDto> selectStudyList() throws Exception{
		System.out.println("값 :: "+studyService.selectStudyList());
		return studyService.selectStudyList();
		
	}
}
