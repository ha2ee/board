package hello.board.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class MemberServiceTest {

    @Mock
    private MemberMapper memberMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void saveMemberTest() {
        // given
        MemberRequest params = new MemberRequest();
        params.setLoginId("test");
        params.setPassword("password");

        // when
        Long result = memberService.saveMember(params);

        // then
        verify(passwordEncoder).encode("password");
        verify(memberMapper).save(params);
        assertThat(params.getId()).isEqualTo(result);
    }

    @Test
    void findMemberByLoginIdTest() {
        // given
        String loginId = "test";
        MemberResponse expectedResponse = MemberResponse.builder()
                .loginId(loginId)
                .build();

        // when
        when(memberMapper.findByLoginId(loginId)).thenReturn(expectedResponse);
        MemberResponse result = memberService.findMemberByLoginId(loginId);

        // then
        verify(memberMapper).findByLoginId(loginId);
        assertThat(expectedResponse).isEqualTo(result);
    }

    @Test
    void updateMemberTest() {
        // given
        MemberRequest params = new MemberRequest();
        params.setLoginId("test");
        params.setPassword("password");

        // when
        Long result = memberService.updateMember(params);

        // then
        verify(passwordEncoder).encode("password");
        verify(memberMapper).update(params);
        assertThat(params.getId()).isEqualTo(result);
    }

    @Test
    void deleteMemberByIdTest() {
        // given
        Long id = 1L;

        // when
        Long result = memberService.deleteMemberById(id);

        // then
        verify(memberMapper).deleteById(id);
        assertThat(id).isEqualTo(result);
    }

    @Test
    void countMemberByLoginIdTest() {
        // given
        String loginId = "test";
        int expectedCount = 1;

        // when
        when(memberMapper.countByLoginId(loginId)).thenReturn(expectedCount);
        int result = memberService.countMemberByLoginId(loginId);

        // then
        verify(memberMapper).countByLoginId(loginId);
        assertThat(expectedCount).isEqualTo(result);
    }

    @Test
    void loginTest() {
        // given
        String loginId = "test";
        String password = "";
        MemberResponse expectedResponse = MemberResponse.builder()
                .loginId(loginId)
                .password("")
                .build();

        // when
        when(memberMapper.findByLoginId(loginId)).thenReturn(expectedResponse);
        when(passwordEncoder.matches(password, expectedResponse.getPassword())).thenReturn(true);
        MemberResponse result = memberService.login(loginId, password);

        // then
        verify(memberMapper).findByLoginId(loginId);
        verify(passwordEncoder).matches(password, expectedResponse.getPassword());
        assertThat(expectedResponse).isEqualTo(result);
    }
}
