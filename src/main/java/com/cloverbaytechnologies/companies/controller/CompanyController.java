package com.cloverbaytechnologies.companies.controller;

import com.cloverbaytechnologies.companies.model.Company;
import com.cloverbaytechnologies.companies.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/get")
    public List<Company> getCompany(){
        return companyService.getCompany();
    }

    @PostMapping("/add")
    public void addCompany(@RequestBody Company company){
        companyService.addCompany(company);
    }

//    @PutMapping("/edit/{companyId}")
//    public void editCompany(@PathVariable String companyId,
//                            @RequestParam(required = false) String companyName,
//                            @RequestParam(required = false) String email,
//                            @RequestParam(required = false) LocalDate validTill,
//                            @RequestParam(required = false) String orgranizationName){
//        companyService.editCompany(companyId,companyName,email,validTill,orgranizationName);
//    }

    @PutMapping("/edit/{companyPathId}")
    public void editCompany(@PathVariable String companyPathId,
                            @RequestBody Company company){
        companyService.editCompany(companyPathId,company);
    }

    @DeleteMapping("/delete/{companyId}")
    public void deleteCompany(@PathVariable String companyId){
        companyService.deleteCompany(companyId);
    }


}
