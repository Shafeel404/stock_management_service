package in.trois.stockmanagement.service.master;

import in.trois.stock.auth.lib.service.service.AbstractJpaService;
import in.trois.stockmanagement.entity.master.Category;
import in.trois.stockmanagement.repository.master.CategoryRepository;
import in.trois.stockmanagement.request.master.CategoryDto;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryService
        extends AbstractJpaService<CategoryDto, UUID, CategoryRepository, Category> {}
