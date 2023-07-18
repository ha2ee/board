package hello.board.domain.member;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberRequest {

    private Long id; //회원 번호 (PK)
    @NotBlank
    private String loginId; //로그인 ID
    @NotBlank
    private String password; //비밀번호
    @NotBlank
    private String name; //이름
    @NotNull
    private Gender gender; //성별
    @NotNull(message = "생년월일을 입력해주세요.")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Past(message = "오늘보다 이후 날짜는 입력 할 수 없습니다.")
    private LocalDate birthday; // 생년월일

    public void encodingPassword(PasswordEncoder passwordEncoder) {
        if(StringUtils.isEmpty(password)){
            return;
        }
        password = passwordEncoder.encode(password);
    }

}
