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
    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday; // 생년월일

    public void encodingPassword(PasswordEncoder passwordEncoder) {
        if(StringUtils.isEmpty(password)){
            return;
        }
        password = passwordEncoder.encode(password);
    }

}
