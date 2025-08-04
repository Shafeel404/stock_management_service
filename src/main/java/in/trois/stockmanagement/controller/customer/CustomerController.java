package in.trois.stockmanagement.controller.customer;

import in.trois.stockmanagement.request.CustomerAddressDto;
import in.trois.stockmanagement.service.CustomerAddressService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/customer")
@Tag(name = "Customer", description = "Customer management APIs")
@SecurityRequirement(name = "bearerAuth")
public class CustomerController {

    private final CustomerAddressService customerAddressService;

    @PostMapping("/address")
    @Operation(summary = "Save customer address", description = "Save a new customer address")
    public CustomerAddressDto saveAddress(@RequestBody CustomerAddressDto dto) {
        return customerAddressService.saveAddress(dto);
    }

    @GetMapping("/address/{customerId}")
    @Operation(summary = "Get customer addresses", description = "Get all addresses for a customer")
    public List<CustomerAddressDto> getAddressesByCustomerId(@PathVariable UUID customerId) {
        return customerAddressService.getAddressesByCustomerId(customerId);
    }
} 