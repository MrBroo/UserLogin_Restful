package byfayzullayev.test_restful.model.receive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
@Data
public class SingUpReceiveModel {

    @JsonProperty("full_name")
    @NotBlank(message = "Ism Familya bo`sh bo`lmasligi kerak")
    private String fullName;

    @JsonProperty("username")
    @NotEmpty(message = "userName bo`sh bo`lmasligi kerak")
    private String username;

    @NotEmpty(message = "password bo'sh bo'lmasligi kerak")
    private String password;

    @JsonProperty("phone_number")
    private String phoneNumber;

}
