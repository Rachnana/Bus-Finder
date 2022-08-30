package com.example.BUS_FINDER_APP;

public class bus_details {
    String bus_id;
    String bus_name;
    String destination;
    String source;
    String timing;
    String std_ticket;
    String eld_ticket;

    public bus_details(String bus_id, String bus_name, String destination, String source, String timing, String std_ticket, String eld_ticket) {
        this.bus_id = bus_id;
        this.bus_name = bus_name;
        this.destination = destination;
        this.source = source;
        this.timing = timing;
        this.std_ticket = std_ticket;
        this.eld_ticket = eld_ticket;
    }

    public String getBus_id() {
        return bus_id;
    }

    public String getBus_name() {
        return bus_name;
    }

    public String getDestination() {
        return destination;
    }

    public String getSource() {
        return source;
    }

    public String getTiming() {
        return timing;
    }

    public String getStd_ticket() {
        return std_ticket;
    }

    public String getEld_ticket() {
        return eld_ticket;
    }
}
