package in.trois.stockmanagement.repository.mapping;

import in.trois.stockmanagement.entity.mapping.InventoryCommercialsMapping;
import in.trois.stockmanagement.repository.AbstractRepository;

import java.util.Optional;
import java.util.UUID;

public interface InventoryCommercialMappingRepository
        extends AbstractRepository<InventoryCommercialsMapping, UUID> {

    Optional<InventoryCommercialsMapping> findByInventoryId(UUID inventoryId);
}
