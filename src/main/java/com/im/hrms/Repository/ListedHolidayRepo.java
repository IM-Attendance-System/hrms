package com.im.hrms.Repository;

import com.im.hrms.Entity.ListedHolidayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListedHolidayRepo extends JpaRepository<ListedHolidayEntity, Long> {
}
