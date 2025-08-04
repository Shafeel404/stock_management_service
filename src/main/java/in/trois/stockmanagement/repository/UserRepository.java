package in.trois.stockmanagement.repository;

import in.trois.stock.auth.lib.service.repository.AbstractRepository;
import in.trois.stockmanagement.entity.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends AbstractRepository<User, UUID> {
    Optional<User> findByUsername(String username);

    Page<User> findByUsernameNot(String username, Pageable pageable);
}
