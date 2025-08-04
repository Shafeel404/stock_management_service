package in.trois.stockmanagement.repository;

import in.trois.stockmanagement.entity.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, UUID> {
    List<CustomerAddress> findByCustomerId(UUID customerId);
    List<CustomerAddress> findByCustomerIdAndIsDefaultTrue(UUID customerId);
} 