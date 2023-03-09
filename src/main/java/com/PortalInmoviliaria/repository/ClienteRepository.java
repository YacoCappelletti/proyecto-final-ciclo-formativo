package com.PortalInmoviliaria.repository;

import com.PortalInmoviliaria.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
