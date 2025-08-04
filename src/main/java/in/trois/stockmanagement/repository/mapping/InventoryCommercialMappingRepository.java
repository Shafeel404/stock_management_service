package in.trois.stockmanagement.repository.mapping;

import in.trois.stock.auth.lib.service.repository.AbstractRepository;
import in.trois.stockmanagement.entity.mapping.InventoryCommercialsMapping;

import java.util.Optional;
import java.util.UUID;

public interface InventoryCommercialMappingRepository
        extends AbstractRepository<InventoryCommercialsMapping, UUID> {

    Optional<InventoryCommercialsMapping> findByInventoryId(UUID inventoryId);
}
