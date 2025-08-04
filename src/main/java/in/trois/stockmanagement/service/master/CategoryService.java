package in.trois.stockmanagement.service.master;

import in.trois.stockmanagement.entity.master.Category;
import in.trois.stockmanagement.repository.master.CategoryRepository;
import in.trois.stockmanagement.request.master.CategoryDto;

import in.trois.stockmanagement.service.AbstractJpaService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CategoryService
        extends AbstractJpaService<CategoryDto, UUID, CategoryRepository, Category> {}
