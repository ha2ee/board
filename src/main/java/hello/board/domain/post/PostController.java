package hello.board.domain.post;

import hello.board.common.dto.SearchDto;
import hello.board.common.paging.PagingResponse;
import hello.board.domain.file.FileRequest;
import hello.board.domain.file.FileResponse;
import hello.board.domain.file.FileService;
import hello.board.domain.file.FileUtils;
import hello.board.domain.member.MemberResponse;
import hello.board.domain.member.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;
    private final FileService fileService;
    private final FileUtils fileUtils;


    //게시글 리스트 페이지
    @GetMapping
    public String list(@ModelAttribute final SearchDto searchDto, Model model) {
        PagingResponse<PostResponse> allPost = postService.findAllPost(searchDto);
        model.addAttribute("params", allPost);
        return "page/board/list";
    }

    @GetMapping("/write")
    public String writeForm(@ModelAttribute("params") PostRequest params) {
        if(Optional.ofNullable(params.getId()).isPresent()){
            PostResponse postById = postService.findPostById(params.getId());
            params.setId(postById.getId());
            params.setTitle(postById.getTitle());
            params.setContent(postById.getContent());
            params.setWriter(postById.getWriter());
        }
        return "page/board/write";
    }

    @PostMapping("/write")
    public String write(@Validated @ModelAttribute("params") PostRequest params, BindingResult bindingResult,
                        RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.info("/write error ={}", bindingResult);
            return "page/board/write";
        }

        Long savedId = postService.savePost(params);//게시글 INSERT
        List<FileRequest> files = fileUtils.uploadFiles(params.getFiles());//파일저장(물리경로)
        fileService.saveFiles(savedId, files);

        String Message = "게시글 생성이 완료 되었습니다.";
        redirectMessage(redirectAttributes, Message);

        return "redirect:/board";
    }

    @GetMapping("/{postId}")
    public String read(@PathVariable long postId, Model model,
                       @SessionAttribute(name = SessionConst.LOGIN_MEMBER, required = false) MemberResponse loginMember) {
        PostResponse postById = postService.findPostById(postId);
        model.addAttribute("params",postById);
        return "page/board/read";
    }

    @PostMapping("/{postId}/edit")
    public String edit(@PathVariable long postId,
                       @Validated @ModelAttribute PostRequest params, BindingResult bindingResult,
                       RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            log.info("/edit error ={}", bindingResult);
            return "page/board/write";
        }
        // 1. 게시글 정보 수정
        postService.updatePost(params);

        // 2. 파일 업로드 (to disk)
        List<FileRequest> uploadFiles = fileUtils.uploadFiles(params.getFiles());

        // 3. 파일 정보 저장 (to database)
        fileService.saveFiles(params.getId(), uploadFiles);

        // 4. 삭제할 파일 정보 조회 (from database)
        List<FileResponse> deleteFiles = fileService.findAllFileByIds(params.getRemoveFileIds());

        // 5. 파일 삭제 (from disk)
        fileUtils.deleteFiles(deleteFiles);

        // 6. 파일 삭제 (from database)
        fileService.deleteAllFileByIds(params.getRemoveFileIds());

        String Message = "게시글이 수정 되었습니다.";
        redirectMessage(redirectAttributes, Message);
        return "redirect:/board/{postId}";
    }

    @PostMapping("/{postId}/delete")
    public String delete(@PathVariable long postId, RedirectAttributes redirectAttributes){

        postService.deletePost(postId);

        String Message = "게시글이 삭제 되었습니다.";
        redirectMessage(redirectAttributes, Message);
        return "redirect:/board";
    }

    private static void redirectMessage(RedirectAttributes redirectAttributes, String Message) {
        redirectAttributes.addFlashAttribute("message", Message);
        redirectAttributes.addFlashAttribute("status",true);
    }


}
