package gr.codehub.telco.telcoproject.repository.impl;

import gr.codehub.telco.telcoproject.model.AppUser;
import gr.codehub.telco.telcoproject.repository.AppUserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class AppUserRepositoryImpl extends RepositoryImpl<AppUser, Long> implements AppUserRepository {

    @PersistenceContext(unitName = "Persistence")
    private EntityManager em;
    @Override
    public Class<AppUser> getClassType() {
        return AppUser.class;
    }

    @Override
    public String getClassName() {
        return AppUser.class.getSimpleName();
    }

    @Override
    public String checkRole(String username, String password) {
        try {
            return em.createQuery("select u.userCategory from AppUser u where username=:u1 and password=:u2")
                    .setParameter("u1", username)
                    .setParameter("u2", password)
                    .getSingleResult()
                    .toString();
        } catch (Exception e) {
            return "";
        }
    }
}
