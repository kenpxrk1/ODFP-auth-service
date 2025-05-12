package fast.delivery.auth.service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {
    private String phoneNumber;
    private String password;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
}
