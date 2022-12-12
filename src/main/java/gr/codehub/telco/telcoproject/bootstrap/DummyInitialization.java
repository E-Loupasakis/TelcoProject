package gr.codehub.telco.telcoproject.bootstrap;


import gr.codehub.telco.telcoproject.repository.impl.CustomerRepositoryImpl;
import gr.codehub.telco.telcoproject.repository.impl.TicketRepositoryImpl;
import gr.codehub.telco.telcoproject.service.impl.TicketServiceImpl;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;


@Singleton
@Startup
public class DummyInitialization {

    @Inject
    TicketRepositoryImpl ticketRepositoryImpl;
    @Inject
    CustomerRepositoryImpl cstimpl;

    @Inject
    TicketServiceImpl ticketServiceimpl;

    @PostConstruct
    public void test() {


}}



