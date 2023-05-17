package com.example.day14sol.EmployeeController;


import com.example.day14sol.APIresponse.APIresponse;
import com.example.day14sol.Model.Employee;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("api/v1/employee")
public class EmployeeController {
    ArrayList<Employee> employees=new ArrayList<>();

    @GetMapping("/get")
    public ArrayList getEmployee(){
        return employees;
    }
    @PostMapping("/add")
    public ResponseEntity addEmployee(@Valid @RequestBody Employee employee, Errors errors){

        if(errors.hasErrors()){
            String msg=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new APIresponse(msg));
        }
        employees.add(employee);
        return ResponseEntity.status(200).body(new APIresponse("user added"));

    }

    @PutMapping("/update/{indx}")
    public ResponseEntity updateEmployee(@Valid @RequestBody Employee employee, Errors errors,@PathVariable int indx){

        if(errors.hasErrors()){
            String msg=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(new APIresponse(msg));
        }
        employees.set(indx,employee);
        return ResponseEntity.status(200).body(new APIresponse("employee updated"));

    }

    @DeleteMapping("/delete/{indx}")
    public ResponseEntity deleteEmployee(@PathVariable int indx){
        employees.remove(indx);
        return ResponseEntity.status(200).body(new APIresponse("employee deleted"));
    }

    @PutMapping("/leave/{indx}")
    public ResponseEntity requestLeaveById(@PathVariable int indx) {

        for (Employee employe : employees) {
            if (employees.get(indx).getAnnualLeave() > 0) {
                int vication = employe.getAnnualLeave();
                vication -= 1;
                employe.setAnnualLeave(vication);
                employe.setOnLeave(true);
                return ResponseEntity.status(200).body(new APIresponse("your new annual leave is: "+vication+" updated"));
            } else if (employe.getAnnualLeave() == 0) {
                ResponseEntity.status(400).body(new APIresponse("You already on leave"));
            }
        }
        return ResponseEntity.status(400).body(new APIresponse("You have 0 days"));
    }


}
