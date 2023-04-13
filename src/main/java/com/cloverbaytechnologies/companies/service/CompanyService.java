package com.cloverbaytechnologies.companies.service;

import com.cloverbaytechnologies.companies.controller.EmailValidator;
import com.cloverbaytechnologies.companies.controller.IdValidator;
import com.cloverbaytechnologies.companies.model.Company;
import com.cloverbaytechnologies.companies.repository.CompanyRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Table;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

//    repository.findAll(Sort.by(Sort.Direction.DESC, "colName"));

    public List<Company> getCompany(){
        return companyRepository.findAll(Sort.by(Sort.Direction.ASC, "companyName"));
    }

    public void addCompany(Company company){
        boolean exists = companyRepository.existsById(company.getCompanyId());

        if(exists){
            throw new IllegalStateException("CompanyId already exists");
        }

        IdValidator idValidator = new IdValidator();
        if(!idValidator.isValidId(company.getCompanyId())){
            throw new IllegalStateException("companyid is not appropriate");
        }

        EmailValidator emailValidator = new EmailValidator();
        if(!emailValidator.isValidEmail(company.getEmail())){
            throw new IllegalStateException("Email is not valid");
        }


        companyRepository.save(company);
    }

//    @Transactional
//    public void editCompany(String companyId,String companyName, String email, LocalDate validTill, String orgranizationName)
//    {
//        Company company = companyRepository.findById(companyId)
//                .orElseThrow(()-> new EntityNotFoundException("Company with " + companyId + " is not present"));
//
////        if(changeId != null && changeId.length() > 0){
////
////            IdValidator idValidator = new IdValidator();
////            if(!idValidator.isValidId(changeId)){
////                throw new IllegalStateException("companyid is not appropriate");
////            }
////
////            boolean exists = companyRepository.existsById(changeId);
////
//////            IdValidator idValidator = new IdValidator();
//////            if(!idValidator.isValidId(changeId)){
//////                throw new IllegalStateException("companyid is not appropriate");
//////            }
////
////            if(exists){
////                throw new IllegalStateException("CompanyId alreday exists");
////            }
////
////            company.setCompanyId(changeId);
////        }
//
//        if(companyName != null && companyName.length() > 0){
//            company.setCompanyName(companyName);
//        }
//
////        if(email != null && email.length() > 0){
////            company.setEmail(email);
////        }
//
//        if(email != null && email.length() > 0){
//            EmailValidator emailValidator = new EmailValidator();
//            if(!emailValidator.isValidEmail(email)){
//                throw new IllegalStateException("Email is not valid");
//            }
//
//            company.setEmail(email);
//        }
//
//        if(validTill != null){
//            company.setValidTill(validTill);
//        }
//
//        if(orgranizationName != null && orgranizationName.length() > 0){
//            company.setOrgranizationName(orgranizationName);
//        }
//    }

    @Transactional
    public void editCompany(String companyPathId,Company company)
    {
        String companyId = company.getCompanyId();
        String companyName = company.getCompanyName();
        String email = company.getEmail();
        LocalDate validTill = company.getValidTill();
        String orgranizationName = company.getOrgranizationName();

        Company companySave = companyRepository.findById(companyPathId)
                .orElseThrow(()-> new EntityNotFoundException("Company with " + companyPathId + " is not present"));

//        IdValidator idValidator = new IdValidator();
//        if(!idValidator.isValidId(companyId)){
//                throw new IllegalStateException("companyid is not appropriate");
//        }

//        if(companyPathId == companyId){
//            throw new IllegalStateException("You cannot change the company id");
//        }


        if(companyName != null && companyName.length() > 0){
            companySave.setCompanyName(companyName);
        }


        if(email != null && email.length() > 0){
            EmailValidator emailValidator = new EmailValidator();
            if(!emailValidator.isValidEmail(email)){
                throw new IllegalStateException("Email is not valid");
            }

            companySave.setEmail(email);
        }

        if(validTill != null){
            companySave.setValidTill(validTill);
        }

        if(orgranizationName != null && orgranizationName.length() > 0){
            companySave.setOrgranizationName(orgranizationName);
        }
    }

    public void deleteCompany(String id){
        companyRepository.deleteById(id);
    }
}
