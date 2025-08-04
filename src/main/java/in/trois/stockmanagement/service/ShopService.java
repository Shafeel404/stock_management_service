package in.trois.stockmanagement.service;

import in.trois.stockmanagement.entity.Shop;
import in.trois.stockmanagement.repository.ShopRepository;
import in.trois.stockmanagement.request.ShopDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ShopService {
    @Autowired
    private ShopRepository shopRepository;

    public ShopDto createShop(ShopDto dto) {
        Shop shop = new Shop(UUID.randomUUID(), dto.getName(), dto.getAddress(), dto.getPhoneNumber(), dto.getEmail());
        shopRepository.save(shop);
        dto.setId(shop.getId());
        return dto;
    }

    public List<ShopDto> getAllShops() {
        return shopRepository.findAll().stream()
                .map(shop -> new ShopDto(shop.getId(), shop.getName(), shop.getAddress(), shop.getPhoneNumber(), shop.getEmail()))
                .collect(Collectors.toList());
    }
} 