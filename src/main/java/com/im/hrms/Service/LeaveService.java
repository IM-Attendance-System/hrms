package com.im.hrms.Service;

import com.im.hrms.Entity.LeaveEntity;
import com.im.hrms.Entity.ListedHolidayEntity;
import com.im.hrms.Model.LeaveModel;
import com.im.hrms.Repository.LeaveRepo;
import com.im.hrms.Repository.ListedHolidayRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.List;
import java.util.Locale;

@Service
public class LeaveService {

    @Autowired
    LeaveRepo leaveRepo;

    @Autowired
    ListedHolidayRepo listedHolidayRepo;


    public LeaveEntity leaveRequest(LeaveModel leaveModel) {
        LeaveEntity leaveEntity = new LeaveEntity();
        leaveEntity.setLeaveType(leaveModel.getLeaveType());
        leaveEntity.setFromDate(leaveModel.getFrom());
        leaveEntity.setToDate(leaveModel.getTo());
        leaveEntity.setReason(leaveModel.getReason());
        leaveEntity.setLeaveDuration(leaveEntity.getLeaveDuration());

        String from = leaveModel.getFrom().getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        String to = leaveModel.getTo().getDayOfWeek()
                .getDisplayName(TextStyle.FULL, Locale.ENGLISH);

        if (from.equals("Friday") && to.equals("Monday")) {
            throw new RuntimeException("Sandwich leave detected: Saturday and Sunday will also be counted.");
        }


        leaveRepo.save(leaveEntity);

        return leaveEntity;
    }

    public List<LeaveEntity> leaveHistory() {
        return leaveRepo.findAll();
    }

    public ListedHolidayEntity listHolidays(LocalDate date) {
        ListedHolidayEntity listedHolidayEntity = new ListedHolidayEntity();
        listedHolidayEntity.setDate(date);
        return listedHolidayRepo.save(listedHolidayEntity);

    }

    public List<ListedHolidayEntity> getListedHolidays() {
        return listedHolidayRepo.findAll();
    }
}
