package ru.funbox.urlhistoryservice.ui.rest.representations;

import java.util.List;

public class VisitedLinks {
    private List<String> links;

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
