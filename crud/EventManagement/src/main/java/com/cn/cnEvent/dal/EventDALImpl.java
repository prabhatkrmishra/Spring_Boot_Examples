package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.Event;
import com.cn.cnEvent.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public class EventDALImpl implements EventDAL {

    @Autowired
    private EventRepository eventRepository;

    @Override
    public Event getById(Long id) {
        Optional<Event> event = eventRepository.findById(id);
        return event.orElse(null);
    }

    @Override
    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    @Override
    public String save(Event event) {
        eventRepository.save(event);
        return "The event was saved successfully.";
    }

    @Override
    public String delete(Long id) {
        if (eventRepository.existsById(id)) {
            eventRepository.deleteById(id);
            return "The event was deleted successfully";
        }
        return "Event with id " + id + " not found";
    }

    @Override
    public String update(Event updateEvent) {
        Optional<Event> existingEventOpt = eventRepository.findById(updateEvent.getId());

        if (existingEventOpt.isPresent()) {
            Event existingEvent = existingEventOpt.get();

            if (updateEvent.getName() != null) {
                existingEvent.setName(updateEvent.getName());
            }
            if (updateEvent.getDescription() != null) {
                existingEvent.setDescription(updateEvent.getDescription());
            }

            eventRepository.save(existingEvent);
            return "Event is updated successfully";
        }

        return "Event with id " + updateEvent.getId() + " not found";
    }
}