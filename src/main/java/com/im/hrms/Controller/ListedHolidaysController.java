package com.im.hrms.Controller;

import com.im.hrms.Entity.LeaveEntity;
import com.im.hrms.Entity.ListedHolidayEntity;
import com.im.hrms.Model.LeaveModel;
import com.im.hrms.Service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/listedHoliday")
public class ListedHolidaysController {

    @Autowired
    public LeaveService leaveService;

    @PostMapping("/listHolidays")
    public ListedHolidayEntity listHolidays(@RequestParam LocalDate date) {

        return this.leaveService.listHolidays(date);
    }

    @GetMapping("/getListedHolidays")
    public List<ListedHolidayEntity> getListedHolidays() {

        return this.leaveService.getListedHolidays();
    }

}
