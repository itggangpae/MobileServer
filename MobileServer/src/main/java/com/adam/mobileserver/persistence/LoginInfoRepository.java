package com.adam.mobileserver.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adam.mobileserver.model.LoginInfo;

public interface LoginInfoRepository extends JpaRepository<LoginInfo, Long>{

}
