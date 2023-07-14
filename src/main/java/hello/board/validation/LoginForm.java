package hello.board.validation;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

@Setter
@Getter
public class LoginForm {

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;
}
