package hello.board.domain.comment;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import hello.board.common.paging.Pagination;
import hello.board.common.paging.PagingResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class CommentServiceTest {

    @Mock
    private CommentMapper commentMapper;

    @InjectMocks
    private CommentService commentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveCommentTest() {
        //given
        CommentRequest params = new CommentRequest();
        params.setId(1L);
        doNothing().when(commentMapper).save(params);

        //when
        Long result = commentService.saveComment(params);

        //then
        verify(commentMapper).save(params);
        assertThat(1L).isEqualTo(result);
    }

    @Test
    void findCommentByIdTest() {
        //given
        Long id = 1L;
        CommentResponse expectedResponse = new CommentResponse();
        expectedResponse.setId(id);
        when(commentMapper.findById(id)).thenReturn(expectedResponse);

        //when
        CommentResponse result = commentService.findCommentById(id);

        //then
        verify(commentMapper).findById(id);
        assertThat(expectedResponse).isEqualTo(result);
    }

    @Test
    void updateCommentTest() {
        //given
        CommentRequest params = new CommentRequest();
        params.setId(1L);

        //when
        Long result = commentService.updateComment(params);

        //then
        verify(commentMapper, times(1)).update(params);
        assertThat(1L).isEqualTo(result);
    }

    @Test
    void deleteCommentTest() {
        //given
        Long id = 1L;

        //when
        Long result = commentService.deleteComment(id);

        //then
        verify(commentMapper).deleteById(id);
        assertThat(1L).isEqualTo(result);
    }

    @Test
    void findAllCommentTest() {
        //given
        CommentSearchDto params = new CommentSearchDto();
        params.setKeyword("test");
        int count = 2;
        List<CommentResponse> expectedList = Arrays.asList(new CommentResponse(), new CommentResponse());
        Pagination expectedPagination = new Pagination(count, params);
        when(commentMapper.count(params)).thenReturn(count);
        when(commentMapper.findAll(params)).thenReturn(expectedList);

        //when
        PagingResponse<CommentResponse> result = commentService.findAllComment(params);

        //then
        verify(commentMapper).count(params);
        verify(commentMapper).findAll(params);
        assertThat(expectedList).isEqualTo(result.getList());
        assertThat(expectedPagination).isEqualTo(result.getPagination());
    }
}
