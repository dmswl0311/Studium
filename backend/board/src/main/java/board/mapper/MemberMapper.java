package board.mapper;

import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import board.dto.MemberDto;
import board.dto.StudyDto;

@Mapper
public interface MemberMapper {
	String loginCheck(MemberDto member) throws Exception;
	
	//------은지
	void insertMember(MemberDto member) throws Exception;		//회원가입	
	int idChk(String memberId) throws Exception;		//회원가입 아이디 중복 체크
	int studyJoinChk(String memberId,int studyId) throws Exception; //스터디 중복가입 체크
	
	void studyJoin(MemberDto member) throws Exception;		//스터디 가입
	
	String login(String MemberId) throws Exception;
	
	List<MemberDto> selectStudyMember(int studyId) throws Exception; //스터디 멤버
	
	MemberDto selectStudyMemberDetail(String memberId) throws Exception; //스터디 멤버 상세보기
	String loginName(String memberId) throws Exception;

}
