package com.im.hrms.Service;

import com.im.hrms.Entity.Employee;
import com.im.hrms.Entity.LeaveEntity;
import com.im.hrms.Model.LeaveModel;
import com.im.hrms.Repository.LeaveRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveService {

    @Autowired
    LeaveRepo leaveRepo;


    public LeaveEntity leaveRequest(LeaveModel leaveModel) {
        LeaveEntity leaveEntity = new LeaveEntity();
        leaveEntity.setLeaveType(leaveModel.getLeaveType());
        leaveEntity.setFromDate(leaveModel.getFrom());
        leaveEntity.setToDate(leaveModel.getTo());
        leaveEntity.setReason(leaveModel.getReason());
        leaveRepo.save(leaveEntity);

        return leaveEntity;
    }

    public List<LeaveEntity> leaveHistory() {
        return leaveRepo.findAll();
    }
}
