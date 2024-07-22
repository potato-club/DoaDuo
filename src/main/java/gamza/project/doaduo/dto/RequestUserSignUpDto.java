package gamza.project.doaduo.dto;

import gamza.project.doaduo.entity.UserEntity;
import gamza.project.doaduo.entity.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RequestUserSignUpDto {

    private String email;
    private String password;
    private String username;
    private UserRole userRole;
//    private String phoneNumber;
//    private boolean gender; // true = maie
//    private String address;
//    private String memo;


    @Builder
    public UserEntity toEntity() {
        return UserEntity.builder()
                .email(email)
                .password(password)
                .username(username)
                .userRole(userRole)
//                .phoneNumber(phoneNumber)
//                .gender(gender)
//                .address(address)
//                .memo(memo)
                .build();
    }

}
