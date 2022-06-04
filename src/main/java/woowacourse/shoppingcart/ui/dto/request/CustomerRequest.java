package woowacourse.shoppingcart.ui.dto.request;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class CustomerRequest {
    @NotBlank
    private String name;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;

    public CustomerRequest() {
    }

    public CustomerRequest(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}