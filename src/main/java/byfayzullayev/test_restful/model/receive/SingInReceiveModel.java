package byfayzullayev.test_restful.model.receive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class SingInReceiveModel {
    @NotBlank(message = "username bo`sh bo`lishi kerak emas")
    private String username;

    @NotBlank(message = "password bo`sh bo`lishi kerak emas")
    private String password;
}