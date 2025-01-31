package board.service;

import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import board.common.FileUtils;
import board.dto.BoardDto;
import board.dto.BoardFileDto;
import board.mapper.BoardMapper;

@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private FileUtils fileUtils;
	
	@Autowired
	private BoardMapper boardMapper;	//데이터베이스에 접근하는 dao빈 선언
	
	@Override
	public List<BoardDto> selectBoardList(int studyId) throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.selectBoardList(studyId);
	}

	@Override
	public void insertBoard(BoardDto board, MultipartHttpServletRequest multipartHttpServletRequest) throws Exception {
		// TODO Auto-generated method stub
		boardMapper.insertBoard(board);
		List<BoardFileDto> list = fileUtils.parseFileInfo(board.getBoardIdx(), multipartHttpServletRequest);
		if(CollectionUtils.isEmpty(list) == false) {
			boardMapper.insertBoardFileList(list);
		}
		
//		if(ObjectUtils.isEmpty(multipartHttpServletRequest) == false) {
//			Iterator<String> iterator = multipartHttpServletRequest.getFileNames();
//			String name;
//			while(iterator.hasNext()) {
//				name = iterator.next();
//				System.out.println("file tag name : "+name);
//				List<MultipartFile> list = multipartHttpServletRequest.getFiles(name);
//				for(MultipartFile multipartFile : list) {
//					System.out.println("start file information");
//					System.out.println("file name :"+multipartFile.getOriginalFilename());
//					System.out.println("file size :"+multipartFile.getSize());
//					System.out.println("file content type :"+multipartFile.getContentType());
//					System.out.println("end file information.\n");
//				}
//			}
//		}
	}

	@Override
	public BoardDto selectBoardDetail(int boardIdx) throws Exception {
		// TODO Auto-generated method stub
		BoardDto board = boardMapper.selectBoardDetail(boardIdx);
		List<BoardFileDto> fileList = boardMapper.selectBoardFileList(boardIdx);
		board.setFileList(fileList);
		boardMapper.updateHitCount(boardIdx);
		
		return board;
	}
	
	@Override
	public void updateBoard(BoardDto board) throws Exception{
		// TODO Auto-generated method stub
		boardMapper.updateBoard(board);
	}

	@Override
	public void deleteBoard(int boardIdx) throws Exception {
		// TODO Auto-generated method stub
		boardMapper.deleteBoard(boardIdx);
	}

	@Override
	public BoardFileDto selectBoardFileInformation(int boardIdx) throws Exception {
		// TODO Auto-generated method stub
		return boardMapper.selectBoardFileInformation(boardIdx);
	}
}
