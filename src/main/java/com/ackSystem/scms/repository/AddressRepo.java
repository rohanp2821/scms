package com.ackSystem.scms.repository;

import com.ackSystem.scms.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepo extends JpaRepository<Address, String> {
}
