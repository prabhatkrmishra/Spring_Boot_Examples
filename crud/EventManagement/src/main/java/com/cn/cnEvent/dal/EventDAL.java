package com.cn.cnEvent.dal;

import com.cn.cnEvent.entity.Event;
import java.util.List;

public interface EventDAL {
    Event getById(Long id);
    List<Event> getAllEvents();
    String save(Event event);
    String delete(Long id);
    String update(Event event);
}