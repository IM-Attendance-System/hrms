package com.im.hrms.Controller;

import com.im.hrms.Entity.Employee;
import com.im.hrms.Entity.LeaveEntity;
import com.im.hrms.Model.LeaveModel;
import com.im.hrms.Service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/leave")
public class LeaveController {

    @Autowired
    public LeaveService leaveService;

    @PostMapping("/leaveRequest")
    public LeaveEntity leaveRequest(@ModelAttribute LeaveModel leaveModel) {

        return this.leaveService.leaveRequest(leaveModel);
    }

    @GetMapping("/leaveHistory")
    public List<LeaveEntity> leaveHistory() {

        return this.leaveService.leaveHistory();
    }
}
