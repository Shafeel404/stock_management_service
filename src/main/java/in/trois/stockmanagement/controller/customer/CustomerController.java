package in.trois.stockmanagement.controller.customer;

import in.trois.stockmanagement.request.CustomerAddressDto;
import in.trois.stockmanagement.request.CustomerDto;
import in.trois.stockmanagement.request.Request;
import in.trois.stockmanagement.response.AbstractResponse;
import in.trois.stockmanagement.service.CustomerAddressService;
import in.trois.stockmanagement.service.CustomerService;
import in.trois.stockmanagement.utils.ResponseBuilder;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<AbstractResponse> signup(@RequestBody Request<CustomerDto> request) {
        if (!(request.isValid() && request.getPayLoad().isValid(HttpMethod.POST))) {
            return new ResponseBuilder().withError("Invalid request").build();
        }
        return new ResponseBuilder().withData(customerService.saveCustomer(request.getPayLoad())).build();
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