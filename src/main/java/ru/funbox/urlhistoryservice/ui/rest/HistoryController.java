package ru.funbox.urlhistoryservice.ui.rest;

import java.util.List;

public interface HistoryController {
    String saveVisitedLinks(List<String> links);
    String getVisitedDomainsBetweenTime(long from, long to);
}
