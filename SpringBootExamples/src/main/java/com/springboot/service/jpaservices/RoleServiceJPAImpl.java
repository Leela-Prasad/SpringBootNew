package com.springboot.service.jpaservices;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.springboot.domain.Role;
import com.springboot.service.CRUDServiceImpl;

@Service
@Profile("jpa")
public class RoleServiceJPAImpl extends CRUDServiceImpl<Role> implements RoleService {

}
