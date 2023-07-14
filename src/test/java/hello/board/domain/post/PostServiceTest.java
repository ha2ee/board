package hello.board.domain.post;

import hello.board.common.dto.SearchDto;
import hello.board.common.paging.Pagination;
import hello.board.common.paging.PagingResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class PostServiceTest {

    @Mock
    private PostMapper postMapper;

    @InjectMocks
    private PostService postService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void savePostTest() {
        // given
        PostRequest params = new PostRequest();
        params.setId(1L);
        doNothing().when(postMapper).save(params);

        // when
        Long result = postService.savePost(params);

        // then
        verify(postMapper).save(params);
        assertThat(result).isEqualTo(1L);
    }

    @Test
    void findPostByIdTest() {
        // given
        Long id = 1L;
        PostResponse expectedResponse = new PostResponse();
        expectedResponse.setId(id);
        when(postMapper.findById(id)).thenReturn(expectedResponse);

        // when
        PostResponse result = postService.findPostById(id);

        // then
        verify(postMapper).findById(id);
        verify(postMapper).increaseViewCount(id);
        assertThat(result).isEqualTo(expectedResponse);
    }

    @Test
    void updatePostTest() {
        // given
        PostRequest params = new PostRequest();
        params.setId(1L);

        // when
        Long result = postService.updatePost(params);

        // then
        verify(postMapper).update(params);
        assertThat(result).isEqualTo(1L);
    }

    @Test
    void deletePostTest() {
        // given
        Long id = 1L;

        // when
        Long result = postService.deletePost(id);

        // then
        verify(postMapper).deleteById(id);
        assertThat(result).isEqualTo(1L);
    }

    @Test
    void findAllPostTest() {
        // given
        SearchDto params = new SearchDto();
        params.setKeyword("test");
        Integer count = 2;
        List<PostResponse> expectedList = Collections.emptyList();
        Pagination pagination = new Pagination(count, params);
        PagingResponse<PostResponse> expectedResponse = new PagingResponse<>(expectedList, pagination);
        when(postMapper.count(params)).thenReturn(count);
        when(postMapper.findAll(params)).thenReturn(expectedList);


        // when
        PagingResponse<PostResponse> result = postService.findAllPost(params);

        // then
        verify(postMapper).count(params);
        verify(postMapper).findAll(params);
        assertThat(result).isEqualTo(expectedResponse);
    }
}
