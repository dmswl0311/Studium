package board.service;

import java.util.List;

import board.dto.StudyDto;

public interface StudyService {
	List<StudyDto> selectStudyList() throws Exception;
	List<StudyDto> searchStudy(String searchCondition,String searchKeyword) throws Exception; 	//스터디 검색
	StudyDto selectStudyDetail(int studyId) throws Exception; //스터디 상세보기
}
