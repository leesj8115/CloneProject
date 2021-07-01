package the.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class MemberInputDto {
    private String emailId;     // 이메일 (앞부분)
    private String emailDomain; // 이메일 (뒷부분)
    private String password;    // 비밀번호
    private String name;        // 이름
    private String phone;       // 연락처
    private String birth;       // 생년월일
    private String ip;          // 아이피
    //private Gender gender;      // 성별
    
}
