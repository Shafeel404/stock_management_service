package in.trois.stockmanagement.repository;

import in.trois.stock.auth.lib.service.repository.AbstractRepository;
import in.trois.stockmanagement.entity.Counter;

import java.util.UUID;

public interface CounterRepository extends AbstractRepository<Counter, UUID> {}
