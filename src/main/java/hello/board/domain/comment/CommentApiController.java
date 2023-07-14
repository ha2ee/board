package hello.board.domain.comment;

import hello.board.common.paging.PagingResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CommentApiController {

    private final CommentService commentService;

    // 신규 댓글 생성
    @PostMapping("/posts/{postId}/comments")
    public CommentResponse saveComment(@PathVariable final Long postId,
                                       @Validated @RequestBody final CommentRequest params,
                                       BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            log.info("댓글작성에러 ={}",bindingResult);
            return null;
        }
        Long id = commentService.saveComment(params);
        return commentService.findCommentById(id);
    }

    //댓글 리스트 조회
    @GetMapping("/posts/{postId}/comments")
    public PagingResponse<CommentResponse> findAllComment(@PathVariable final Long postId, final CommentSearchDto params) {
        log.info("commentSearchDto = {}",params);
        PagingResponse<CommentResponse> allComment = commentService.findAllComment(params);
        log.info("리스트조회 allComment = {}", allComment);
        return allComment;
    }

    // 댓글 상세정보 조회
    @GetMapping("/posts/{postId}/comments/{id}")
    public CommentResponse findCommentById(@PathVariable final Long postId, @PathVariable final Long id){
        return commentService.findCommentById(id);
    }

    //기존 댓글 수정
    @PatchMapping("/posts/{postId}/comments/{id}")
    public CommentResponse updateComment(@PathVariable final Long postId,
                                         @PathVariable final Long id,
                                         @Validated @RequestBody final CommentRequest params,
                                         BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            log.info("update Error!",bindingResult);
            return null;
        }
        commentService.updateComment(params);
        return commentService.findCommentById(id);
    }

    //댓글 삭제
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public Long deleteComment(@PathVariable final Long postId, @PathVariable final Long id) {
        return commentService.deleteComment(id);
    }
}
