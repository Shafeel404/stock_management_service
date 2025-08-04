package in.trois.stockmanagement.controller.customer;

import in.trois.stockmanagement.request.CustomerAddressDto;
import in.trois.stockmanagement.request.CustomerDto;
import in.trois.stockmanagement.service.CustomerAddressService;
import in.trois.stockmanagement.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/no-auth/customer")
@Tag(name = "Customer", description = "Customer management APIs")
public class CustomerController {

    private final CustomerAddressService customerAddressService;
    private final CustomerService customerService;

    @PostMapping("/signup")
    public CustomerDto signup(@RequestBody CustomerDto dto) {
        return customerService.saveCustomer(dto);
    }

    @PostMapping("/address")
    @SecurityRequirement(name = "bearerAuth")
    public CustomerAddressDto saveAddress(@RequestBody CustomerAddressDto dto) {
        return customerAddressService.saveAddress(dto);
    }

    @GetMapping("/address/{customerId}")
    @SecurityRequirement(name = "bearerAuth")
    public List<CustomerAddressDto> getAddressesByCustomerId(@PathVariable UUID customerId) {
        return customerAddressService.getAddressesByCustomerId(customerId);
    }
} 