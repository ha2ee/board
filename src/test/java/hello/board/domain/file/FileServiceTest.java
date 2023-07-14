package hello.board.domain.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class FileServiceTest {

    @Mock
    private FileMapper fileMapper;

    @InjectMocks
    private FileService fileService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveFilesTest() {
        // given
        Long postId = 1L;
        List<FileRequest> files = Collections.singletonList(
                FileRequest.builder()
                        .originalName("test.jpg")
                        .saveName("test123.jpg")
                        .size(1024L)
                        .build()
        );

        // when
        fileService.saveFiles(postId, files);

        // then
        verify(fileMapper).saveAll(files);
    }

    @Test
    void findAllFileByPostIdTest() {
        // given
        Long postId = 1L;
        List<FileResponse> expectedFiles = Collections.singletonList(
                FileResponse.builder()
                        .id(1L)
                        .postId(postId)
                        .originalName("test.jpg")
                        .saveName("test123.jpg")
                        .size(1024L)
                        .build()
        );
        when(fileMapper.findAllByPostId(postId)).thenReturn(expectedFiles);

        // when
        List<FileResponse> result = fileService.findAllFileByPostId(postId);

        // then
        verify(fileMapper).findAllByPostId(postId);
        assertThat(result).isEqualTo(expectedFiles);
    }

    @Test
    void findAllFileByIdsTest() {
        // given
        List<Long> ids = Collections.singletonList(1L);
        List<FileResponse> expectedFiles = Collections.singletonList(
                FileResponse.builder()
                        .id(1L)
                        .postId(1L)
                        .originalName("test.jpg")
                        .saveName("test123.jpg")
                        .size(1024L)
                        .build()
        );
        when(fileMapper.findAllByIds(ids)).thenReturn(expectedFiles);

        // when
        List<FileResponse> result = fileService.findAllFileByIds(ids);

        // then
        verify(fileMapper).findAllByIds(ids);
        assertThat(result).isEqualTo(expectedFiles);
    }

    @Test
    void deleteAllFileByIdsTest() {
        // given
        List<Long> ids = Collections.singletonList(1L);

        // when
        fileService.deleteAllFileByIds(ids);

        // then
        verify(fileMapper).deleteAllByIds(ids);
    }

    @Test
    void findFileByIdTest() {
        // given
        Long id = 1L;
        FileResponse expectedFile = FileResponse.builder()
                .id(id)
                .postId(1L)
                .originalName("test.jpg")
                .saveName("test123.jpg")
                .size(1024L)
                .build();
        when(fileMapper.findById(id)).thenReturn(expectedFile);

        // when
        FileResponse result = fileService.findFileById(id);

        // then
        verify(fileMapper).findById(id);
        assertThat(result).isEqualTo(expectedFile);
    }
}
