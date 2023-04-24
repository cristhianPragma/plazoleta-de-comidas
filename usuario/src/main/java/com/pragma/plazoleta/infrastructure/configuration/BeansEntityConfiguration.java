package com.pragma.plazoleta.infrastructure.configuration;

import com.pragma.plazoleta.domain.api.IRoleServicePort;
import com.pragma.plazoleta.domain.api.IUserServicePort;
import com.pragma.plazoleta.domain.spi.IRolePersistencePort;
import com.pragma.plazoleta.domain.spi.IUserPersistencePort;
import com.pragma.plazoleta.domain.usecase.RoleUseCase;
import com.pragma.plazoleta.domain.usecase.UserUseCase;
import com.pragma.plazoleta.infrastructure.out.jpa.adapter.RoleJpaAdapter;
import com.pragma.plazoleta.infrastructure.out.jpa.adapter.UserJpaAdapter;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IRoleEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.mapper.IUserEntityMapper;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IRoleEntityRepository;
import com.pragma.plazoleta.infrastructure.out.jpa.repository.IUserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeansEntityConfiguration {
    private final IUserEntityRepository userEntityRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IRoleEntityMapper roleEntityMapper;
    private final IRoleEntityRepository roleEntityRepository;
    @Bean
    public IUserPersistencePort userPersistencePort(){
        return new UserJpaAdapter(userEntityRepository, userEntityMapper);
    }
    @Bean
    public IRolePersistencePort rolePersistencePort(){
        return new RoleJpaAdapter(roleEntityRepository, roleEntityMapper);
    }
    @Bean
    public IRoleServicePort roleServicePort(){
        return new RoleUseCase(rolePersistencePort());
    }
    @Bean
    public IUserServicePort userServicePort(){
        return new UserUseCase(userPersistencePort(), roleServicePort());
    }
}
