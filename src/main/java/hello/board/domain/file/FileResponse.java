package hello.board.domain.file;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class FileResponse {
    
    private Long id; //파일번호(PK)
    private Long postId; //게시글 번호(FK)
    private String originalName; //원본 파일명
    private String saveName; //저장 파일명
    private long size; //파일 크기
    private Boolean deleteYn; //삭제 여부
    private LocalDateTime createdDate; //생성 일시
    private LocalDateTime deletedDate; //삭제 일시
}
