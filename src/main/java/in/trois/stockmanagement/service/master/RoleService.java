package in.trois.stockmanagement.service.master;

import in.trois.stockmanagement.entity.master.Role;
import in.trois.stockmanagement.repository.master.RoleRepository;
import in.trois.stockmanagement.request.master.RoleDto;

import in.trois.stockmanagement.service.AbstractJpaService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RoleService extends AbstractJpaService<RoleDto, UUID, RoleRepository, Role> {}
