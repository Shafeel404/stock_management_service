package in.trois.stockmanagement.service;

import in.trois.stockmanagement.entity.CustomerAddress;
import in.trois.stockmanagement.repository.CustomerAddressRepository;
import in.trois.stockmanagement.request.CustomerAddressDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerAddressService {
    @Autowired
    private CustomerAddressRepository repository;

    public CustomerAddressDto saveAddress(CustomerAddressDto dto) {
        CustomerAddress address = new CustomerAddress();
        address.setId(UUID.randomUUID());
        address.setCustomerId(dto.getCustomerId());
        address.setAddressLine1(dto.getAddressLine1());
        address.setAddressLine2(dto.getAddressLine2());
        address.setCity(dto.getCity());
        address.setState(dto.getState());
        address.setPostalCode(dto.getPostalCode());
        address.setCountry(dto.getCountry());
        address.setLatitude(dto.getLatitude());
        address.setLongitude(dto.getLongitude());
        address.setIsDefault(dto.getIsDefault() != null ? dto.getIsDefault() : false);
        
        repository.save(address);
        dto.setId(address.getId());
        return dto;
    }

    public List<CustomerAddressDto> getAddressesByCustomerId(UUID customerId) {
        return repository.findByCustomerId(customerId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    private CustomerAddressDto mapToDto(CustomerAddress address) {
        CustomerAddressDto dto = new CustomerAddressDto();
        dto.setId(address.getId());
        dto.setCustomerId(address.getCustomerId());
        dto.setAddressLine1(address.getAddressLine1());
        dto.setAddressLine2(address.getAddressLine2());
        dto.setCity(address.getCity());
        dto.setState(address.getState());
        dto.setPostalCode(address.getPostalCode());
        dto.setCountry(address.getCountry());
        dto.setLatitude(address.getLatitude());
        dto.setLongitude(address.getLongitude());
        dto.setIsDefault(address.getIsDefault());
        return dto;
    }
} 