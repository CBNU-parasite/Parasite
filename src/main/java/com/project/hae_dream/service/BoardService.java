package com.project.hae_dream.service;

import com.project.hae_dream.dto.BoardDTO;
import com.project.hae_dream.entity.BoardEntity;
import com.project.hae_dream.entity.BoardFileEntity;
import com.project.hae_dream.repository.BoardFileRepository;
import com.project.hae_dream.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// dto -> entity 리파지토리로 넘겨줄때 (생성) (엔티티 클래스에서)
// entity -> dto 리파지토리에서 받아와서 컨트롤러로 넘겨줄때 (조회) (디티오 클래스에서)

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;
    private final BoardFileRepository boardFileRepository;
    public void save(BoardDTO boardDTO) throws IOException {
        // 파일 첨부 여부에 따라 로직 분리
        if(boardDTO.getBoardFile().isEmpty()){
            // 첨부 파일 없음.
            BoardEntity boardEntity = BoardEntity.toSaveEntity(boardDTO);
            boardRepository.save(boardEntity);
        }
        else{
            //첨부 파일이 있음.
            /*
            1. DTO 에 담긴 파일을 꺼냄
            2. 파일의 이름 가져옴
            3. 서버 저장용 이름은 만듦.
            // 내사진.jpg =>202023929_내사진.jpg
            4. 저장 경로 설정.
            5. 해당 경로에 파일 저장.
            6. board_table에 해당 데이터 save 처리
            7. board_file_table에 해당 데이터 save 처리
            */
            MultipartFile boardFile = boardDTO.getBoardFile();//1.
            String originalFilename = boardFile.getOriginalFilename();//2.
            String storedFileName = System.currentTimeMillis() + "_" +originalFilename; // 3.
//            String savePath = "C:/springboot_img/" + storedFileName; //C:/springboot_img/2030423041_내사진. 윈도우 ver
            String savePath = "/Users/wonsick/springboot_img/" + storedFileName; // 4. C:/springboot_img/2030423041_내사진. 맥
            boardFile.transferTo(new File(savePath)); // 5.
            BoardEntity boardEntity = BoardEntity. toSaveFileEntity(boardDTO);
            Long saveId = boardRepository.save(boardEntity).getId();
            BoardEntity board = boardRepository.findById(saveId).get();

            BoardFileEntity boardFileEntity = BoardFileEntity.toBoardFileEntity(board,originalFilename,storedFileName);
            boardFileRepository.save(boardFileEntity);
        }
    }
    // 엔티티로 담아온 객체를 dto 로 변경시켜서 컨트롤러에 전송해야한다.
    @Transactional
    public List<BoardDTO> findAll() {
        List<BoardEntity> boardEntityList = boardRepository.findAll();
        List<BoardDTO> boardDTOList = new ArrayList<>();
        for(BoardEntity boardEntity: boardEntityList){
            boardDTOList.add(BoardDTO.toBoardDTO(boardEntity));
        }
        return boardDTOList;
    }
}