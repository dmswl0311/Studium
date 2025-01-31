package board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import board.dto.MemberDto;
import board.dto.StudyDto;

@Mapper
public interface StudyMapper {
	List<StudyDto> selectStudyList() throws Exception;
	List<StudyDto> searchStudy(Map<String,String> map) throws Exception; //스터디 검색
	StudyDto selectStudyDetail(int studyId) throws Exception; //스터디 상세보기
	void insertStudy(StudyDto studyDto) throws Exception;	//스터디 만들기
	void insertLocation(StudyDto study) throws Exception; //스터디 장소 예약
	List<StudyDto> selectStudyEvent(int studyId) throws Exception; //스터디 이벤트
	List<StudyDto> selectStudyMember(int studyId) throws Exception; //스터디 멤버
	StudyDto selectEventDetail(int eventId) throws Exception; //이벤트 디테일
	void studyJoin(Map<String,Object> map) throws Exception;		//스터디 가입
	String studyJoinChk(int studyId) throws Exception; //스터디 중복가입 체크
	String eventJoinChk(int eventId) throws Exception; //이벤트 참석 체크
	void eventJoin(Map<String,Object> map) throws Exception; //이벤트 참석
	List<StudyDto> selectEventList() throws Exception; //이벤트 리스트
	void insertEvent(StudyDto studyDto) throws Exception; //이벤트 추가
	
}
