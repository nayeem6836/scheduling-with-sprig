package com.example.shedulingbackend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "Availability")
public class Availability {
     @Id
    private String id;
    private String user;
    private Date start;
    private Date end;
    private int duration;

        // Default constructor
        public Availability() {}

        // Parameterized constructor
        public Availability(String id, String user, Date start, Date end, int duration) {
            this.id = id;
            this.user = user;
            this.start = start;
            this.end = end;
            this.duration = duration;
        }
    
        // Getters
        public String getId() {
            return id;
        }
    
        public String getUser() {
            return user;
        }
    
        public Date getStart() {
            return start;
        }
    
        public Date getEnd() {
            return end;
        }
    
        public int getDuration() {
            return duration;
        }
    
        // Setters
        public void setId(String id) {
            this.id = id;
        }
    
        public void setUser(String user) {
            this.user = user;
        }
    
        public void setStart(Date start) {
            this.start = start;
        }
    
        public void setEnd(Date end) {
            this.end = end;
        }
    
        public void setDuration(int duration) {
            this.duration = duration;
        }
}
