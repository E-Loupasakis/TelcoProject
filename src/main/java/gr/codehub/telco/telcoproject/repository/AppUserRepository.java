package gr.codehub.telco.telcoproject.repository;

import gr.codehub.telco.telcoproject.model.AppUser;

public interface AppUserRepository extends Repository<AppUser, Long>{

    String checkRole(String username, String password);
}
