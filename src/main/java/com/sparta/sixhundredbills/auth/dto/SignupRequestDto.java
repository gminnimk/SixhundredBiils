package com.sparta.sixhundredbills.auth.dto;

import com.sparta.sixhundredbills.auth.entity.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 회원가입 요청을 위한 데이터 전송 객체 (DTO).
 * 각 필드는 유효성 검사를 포함하며, Lombok을 사용하여 getter와 builder 메소드를 자동 생성.
 */

@Getter
@NoArgsConstructor
public class SignupRequestDto {

    /**
     * 사용자 ID
     * - 공백 불가
     * - 길이: 최소 4자, 최대 10자
     * - 소문자 영문자와 숫자만 허용
     */
    @NotBlank(message = "email은 공백일 수 없습니다.")
    @Size(min = 10, max = 50, message = "이메일 형식으로 작성해주세요.")
    @Email(message = "이메일 형식으로 작성해주세요.")  // @Email 어노테이션을 사용하여 이메일 형식을 검증합니다.
    private String email;

    /**
     * 사용자 비밀번호
     * - 공백 불가
     * - 길이: 최소 8자, 최대 15자
     * - 대소문자 영문자, 숫자, 특수문자 각 1자 이상 포함해ㅇ야 함
     */
    @NotBlank(message = "비밀번호는 공백일 수 없습니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 최소 8자 이상, 15자 이하로 작성해주세요.")
    @Pattern(regexp = "^(?=.*?[A-Za-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{10,}$",
            message = "비밀번호는 대소문자 포함 영문 + 숫자 + 특수문자를 최소 1글자씩 포함해야합니다.")
    private String password;

    /**
     * 사용자 이름
     * - 공백 불가
     */
    @NotBlank(message = "이름은 공백일 수 없습니다.")
    private String name;

    /**
     * 회원 권한
     * - 기본값: USER
     */
    private Role role;

    @Builder
    public SignupRequestDto(String email, String password, String name, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
    }
}
