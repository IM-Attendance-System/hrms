package com.im.hrms.Repository;

import com.im.hrms.Entity.AdminEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AdminRepo extends JpaRepository<AdminEntity, Long> {

    public Optional<AdminEntity> findByAdminEmailId(String adminEmailId);


}
