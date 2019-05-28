package com.citizen.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.citizen.entity.UserReport;

@Repository
public interface UserReportRepository extends CrudRepository<UserReport,Long>  {

}
