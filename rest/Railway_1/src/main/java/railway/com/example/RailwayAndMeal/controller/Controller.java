package railway.com.example.RailwayAndMeal.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import railway.com.example.RailwayAndMeal.Entity.Ticket;
import railway.com.example.RailwayAndMeal.service.RailwayService;

@RestController
@RequestMapping("/railway")
public class Controller {

    @Autowired
    private RailwayService railwayservice;

    // a. POST "/railway/ticket": It saves a railway ticket.
    @PostMapping("/ticket")
    public void addTicket(@RequestBody Ticket ticket) {

        railwayservice.addTicket(ticket);

    }

    // b. GET "/railway/tickets": It fetches the list of all tickets.
    @GetMapping("/tickets")
    public List<Ticket> getAllTickets() {

        return railwayservice.getAllTickets();

    }

    // c. GET "/railway/ticket/{pnr}"
    @GetMapping("/ticket/{pnr}")
    public Ticket getTicketByPnr(@PathVariable long pnr) {

        return railwayservice.getTicketByPnr(pnr);

    }

}