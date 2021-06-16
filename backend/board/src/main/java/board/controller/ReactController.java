package board.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import board.dto.BoardDto;
import board.dto.BoardFileDto;
import board.dto.MemberDto;
import board.dto.StudyDto;
import board.service.BoardService;
import board.service.MemberService;
import board.service.StudyService;

@CrossOrigin(origins="http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ReactController {
	
	int glo_studyId;
	
	@Autowired
	private BoardService boardService;
	
	@Autowired
	private MemberService memberService;
	
	@Autowired
	private StudyService studyService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	// Main ----------------------------
	@GetMapping(value = {"/home", "/"})
	public List<StudyDto> selectStudyList() throws Exception{
		
		List<StudyDto> list = studyService.selectStudyList();
		
		for(int i=0; i<list.size(); i++) {
			if (list.get(i).getMemberId()==null) {
				continue;
			}
			String[] memberList = list.get(i).getMemberId().split(",");
			int memberCount = memberList.length;
			list.get(i).setMemberCnt(memberCount);
	}
		
		return list;
	}
	
	// 회원가입 ----------------------------
	@RequestMapping("/signup.do") 
	public String signUp(@RequestParam(defaultValue="memberId")String memberId,@RequestParam(defaultValue="memberPassword")String memberPassword,
			@RequestParam(defaultValue="memberName")String memberName,@RequestParam(defaultValue="memberAddress")String memberAddress,
			@RequestParam(defaultValue="memberGender")String memberGender,MemberDto member) throws Exception {

		int result=memberService.idChk(memberId);
		
		System.out.println("memberDto 내용 :: " + member);
	
		if(result==1) {
			// 아이디가 중복이면 
			System.out.println("아이디가 중복입니다.");
			//return "redirect:/user/join.do";
			return "404";
		}
		else if (result==0){
			// 아이디가 중복이 아니면 db에 insert
			
			String password=passwordEncoder.encode(memberPassword);
			
			member.setMemberPassword(password);
			memberService.insertMember(member);
		}
		
		//return "redirect:/user/login.do";
		return "ok";
	}

	// 로그인 ----------------------------
	@GetMapping("/login.do")
	public String login(@RequestParam(defaultValue="memberId")String memberId,@RequestParam(defaultValue="memberPassword")String memberPassword,
			MemberDto member,HttpServletRequest request) throws Exception{
		
		  String DBPassword = memberService.login(memberId); 
		  System.out.println(memberId+memberPassword); 
		  
		  //사용자에게 받은 값:: member.getMemberPw()
		  //db에서 받은 값:: dbPwd
		  if (DBPassword !=null) {
			  if (BCrypt.checkpw(memberPassword, DBPassword) == true) {
				  System.out.println("로그인 성공"); 
				  
				  // 서버에 세션 id 발급, 서버에서 받은 세션을 클라이언트 브라우저의 쿠키가 저장하여 가지고 있어야 함
				  //RequestAttributes requestAttribute = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
				  //requestAttribute.setAttribute("login", null, RequestAttributes.SCOPE_SESSION);
				  
				  HttpSession session = (HttpSession)request.getSession();
				  session.setAttribute("loginUser", member);

				  // 세션 아이디 가져오기
				  //System.out.println("세션 아이디 :: "+requestAttribute.getSessionId());
				  System.out.println("세션 아이디2 :: "+session.getId());
				  //return "redirect:/home";
				  return "ok";
			  }
			  else {
				  System.out.println("로그인 실패"); 
				
			  }
		  }
		  else {
			  System.out.println("해당하는 아이디가 없습니다");
			  System.out.println("로그인 실패");
		  }
		  //return "redirect:/user/login.do";
		  return "404";
	}

	
	// StudyDetail----------------------------
	@GetMapping("/study")
    public ArrayList StudyDetail(@RequestParam(defaultValue="studyId")int studyId) throws Exception{		
 
		ArrayList studyDetail=new ArrayList();
		ArrayList<MemberDto> memberList = new ArrayList<>();

    	StudyDto study = studyService.selectStudyDetail(studyId);
    	
    	//현재 스터디 전역변수로 저장
    	glo_studyId=studyId;
    	
    	//멤버 리스트
    	String[] member=study.getMemberId().split(",");

    	//System.out.println("멤버 리스트 :: "+Arrays.toString(memberList));
    	//System.out.println("멤버 리스트 길이 :: "+memberList.length);
    	
    	for (int i=0; i<member.length; i++) {
    		//스터디 멤버
        	MemberDto mem= memberService.selectStudyMemberDetail(String.valueOf(member[i]));
        	memberList.add(mem);
    	}
    	
    	studyDetail.add(study);
    	studyDetail.add(memberList);
    	
    	return studyDetail;
    }
	
	
	
	
//	@GetMapping("/study")
//	public List<StudyDto> seletTest(@RequestParam("no") int no) throws Exception{
//		System.out.println("받은 값 :: "+no);
//		return null;
//		
//	}
	

}
