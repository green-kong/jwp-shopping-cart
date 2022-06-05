package woowacourse.shoppingcart.dto;

import javax.validation.constraints.NotBlank;
import woowacourse.shoppingcart.domain.Customer;

public class CustomerRequest {

    @NotBlank
    private final String email;
    @NotBlank
    private final String password;
    @NotBlank
    private final String name;
    @NotBlank
    private final String phone;
    @NotBlank
    private final String address;

    public CustomerRequest(String email, String password, String name, String phone, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public Customer createCustomer() {
        return createCustomer(null);
    }

    public Customer createCustomer(Long id) {
        return new Customer(id, email, password, name, phone, address);
    }
}