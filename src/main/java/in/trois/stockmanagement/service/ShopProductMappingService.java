package in.trois.stockmanagement.service;

import in.trois.stockmanagement.entity.ShopProductMapping;
import in.trois.stockmanagement.repository.ShopProductMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopProductMappingService {
    @Autowired
    private ShopProductMappingRepository repository;

    public List<ShopProductMapping> getByProductCode(String productCode) {
        return repository.findByProductCode(productCode);
    }

    public List<ShopProductMapping> getByProductName(String productName) {
        return repository.findByProductName(productName);
    }
} 