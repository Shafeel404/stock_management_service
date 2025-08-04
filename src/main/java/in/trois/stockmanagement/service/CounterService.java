package in.trois.stockmanagement.service;

import in.trois.stock.auth.lib.service.service.AbstractJpaService;
import in.trois.stockmanagement.entity.Counter;
import in.trois.stockmanagement.repository.CounterRepository;
import in.trois.stockmanagement.request.CounterDto;

import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CounterService
        extends AbstractJpaService<CounterDto, UUID, CounterRepository, Counter> {}
