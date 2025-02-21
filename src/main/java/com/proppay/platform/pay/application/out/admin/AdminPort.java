package com.proppay.platform.pay.application.out.admin;

import com.proppay.platform.pay.domain.admin.Admin;

import java.util.Optional;

public interface AdminPort {

    Admin SaveAdmin(Admin admin);

    Optional<Admin> loadAdmin(Long id);

    void deleteAdmin(Long id);

}
