package ru.funbox.urlhistoryservice.ui.rest;

import ru.funbox.urlhistoryservice.ui.rest.representations.Status;
import ru.funbox.urlhistoryservice.ui.rest.representations.VisitedDomains;
import ru.funbox.urlhistoryservice.ui.rest.representations.VisitedLinks;

public interface HistoryController {
    Status saveVisitedLinks(VisitedLinks visitedLinks);
    VisitedDomains getVisitedDomainsBetweenTime(long from, long to);
}
