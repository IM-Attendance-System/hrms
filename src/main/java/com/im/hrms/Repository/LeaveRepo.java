package com.im.hrms.Repository;

import com.im.hrms.Entity.LeaveEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaveRepo extends JpaRepository<LeaveEntity, Long> {
}
