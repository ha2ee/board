package hello.board.domain.file;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FileMapper {

    /**
     * 파일 정보 저장
     * @param files - 파일 정보 리스트
     */
    void saveAll(List<FileRequest> files);

    /**
     * 파일 리스트 조회 (게시글 번호 기준으로 게시글에 등록된 모든 첨부파일을 조회)
     * @param postId - 게시글 번호(FK)
     * @return 파일 리스트
     */
    List<FileResponse> findAllByPostId(Long postId);

    /**
     * 파일 리스트 조회 (물리적 파일의 삭제에 사용)
     * @param ids - PK 리스트
     * @return 파일 리스트
     */
    List<FileResponse> findAllByIds(List<Long> ids);

    /**
     * 파일 삭제 (리스트 타입의 파일 번호를 기준으로 DB에서 첨부파일을 삭제 처리)
     * @param ids - PK 리스트
     */
    void deleteAllByIds(List<Long> ids);

    /**
     * 파일 상세정보 조회
     * @param id - PK
     * @return 파일 상세정보
     */
    FileResponse findById(Long id);
}
