package board.dto;

import lombok.Data;

@Data
public class MemberDto {
	private String memberId;
	private String memberPw;
	private String memberName;
	
	// s_host 테이블 
	// study에 가입된 member list
	private int studyId;
	
	//추가
	private String memberPassword;
	private String memberImage;
	private String memberAddress;
	private String memberGender;
	private String memberRePassword;
}
