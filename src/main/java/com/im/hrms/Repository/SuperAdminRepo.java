package com.im.hrms.Repository;

import com.im.hrms.Entity.SuperAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperAdminRepo extends JpaRepository<SuperAdmin, Long> {
}
