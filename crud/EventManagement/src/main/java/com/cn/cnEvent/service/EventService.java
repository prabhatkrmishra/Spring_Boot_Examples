package com.cn.cnEvent.service;

import com.cn.cnEvent.dal.EventDAL;
import com.cn.cnEvent.entity.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@Transactional
public class EventService {

    @Autowired
    private EventDAL eventDAL;

    @Transactional(readOnly = true)
    public Event getEventById(Long id) {
        return eventDAL.getById(id);
    }

    @Transactional(readOnly = true)
    public List<Event> getAllEvents() {
        return eventDAL.getAllEvents();
    }

    public String saveEvent(Event event) {
        return eventDAL.save(event);
    }

    @Transactional
    public String deleteEvent(Long id) {
        return eventDAL.delete(id);
    }

    @Transactional
    public String updateEvent(Event updateEvent) {
        return eventDAL.update(updateEvent);
    }
}