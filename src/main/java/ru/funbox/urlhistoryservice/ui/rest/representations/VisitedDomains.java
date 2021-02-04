package ru.funbox.urlhistoryservice.ui.rest.representations;

import java.util.List;

public class VisitedDomains {
    private List<String> domains;
    private String status;

    public VisitedDomains() {
    }

    public VisitedDomains(List<String> domains, String status) {
        this.domains = domains;
        this.status = status;
    }

    public List<String> getDomains() {
        return domains;
    }

    public void setDomains(List<String> domains) {
        this.domains = domains;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
