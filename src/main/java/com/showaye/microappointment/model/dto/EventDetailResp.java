package com.showaye.microappointment.model.dto;

import com.showaye.microappointment.model.entity.Comment;
import com.showaye.microappointment.model.entity.EventAttend;

import java.util.List;

public class EventDetailResp extends EventResp {

    private static final long serialVersionUID = -1799326800903586223L;
    private List<EventAttend> contracts;
    private List<Comment> comments;

    public List<EventAttend> getContracts() {
        return contracts;
    }

    public void setContracts(List<EventAttend> contracts) {
        this.contracts = contracts;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }
}
