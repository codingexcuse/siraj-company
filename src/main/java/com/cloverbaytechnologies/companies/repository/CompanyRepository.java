package com.cloverbaytechnologies.companies.repository;

import com.cloverbaytechnologies.companies.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company,String> {
    Optional<Company> findCompanyByEmail(String email);
}
