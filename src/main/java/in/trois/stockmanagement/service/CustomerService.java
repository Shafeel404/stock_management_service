package in.trois.stockmanagement.service;

import in.trois.stockmanagement.entity.Customer;
import in.trois.stockmanagement.repository.CustomerRepository;
import in.trois.stockmanagement.request.CustomerDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    @Autowired
    private CustomerRepository repository;
    
    @Autowired
    private PasswordEncoder passwordEncoder;

    public CustomerDto saveCustomer(CustomerDto dto) {
        // Check if email already exists
        if (repository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        Customer customer = new Customer();
        customer.setId(UUID.randomUUID());
        customer.setName(dto.getName());
        customer.setEmail(dto.getEmail());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        customer.setIsActive(true);
        customer.setCreatedAt(LocalDateTime.now());
        customer.setUpdatedAt(LocalDateTime.now());
        
        Customer savedCustomer = repository.save(customer);
        return savedCustomer.toDto();
    }

    public Optional<CustomerDto> findByEmail(String email) {
        return repository.findByEmail(email).map(Customer::toDto);
    }

    public List<CustomerDto> getAllCustomers() {
        return repository.findAll().stream()
                .map(Customer::toDto)
                .collect(Collectors.toList());
    }

    public CustomerDto updateCustomer(UUID id, CustomerDto dto) {
        Customer customer = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found"));
        
        customer.setName(dto.getName());
        customer.setPhoneNumber(dto.getPhoneNumber());
        if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
            customer.setPassword(passwordEncoder.encode(dto.getPassword()));
        }
        customer.setUpdatedAt(LocalDateTime.now());
        
        Customer updatedCustomer = repository.save(customer);
        return updatedCustomer.toDto();
    }

    
} 