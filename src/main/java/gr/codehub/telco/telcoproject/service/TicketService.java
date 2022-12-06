package gr.codehub.telco.telcoproject.service;

import gr.codehub.telco.telcoproject.dto.CustomerDto;
import gr.codehub.telco.telcoproject.dto.TicketDto;
import gr.codehub.telco.telcoproject.model.Ticket;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Transactional
public interface TicketService {
    TicketDto create(TicketDto ticketDto);
    List<TicketDto> findAll();
    TicketDto findByTicketId(Long id);
    TicketDto update(TicketDto ticketDto);
    boolean delete(Long id);
    List<TicketDto> findByDate(LocalDate date);
    List<TicketDto> findByDateRange(LocalDate dateFrom, LocalDate dateTo);
    List<TicketDto> getTicketsByCustomerId(Long id);

}
