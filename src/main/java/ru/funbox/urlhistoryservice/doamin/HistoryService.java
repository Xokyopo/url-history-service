package ru.funbox.urlhistoryservice.doamin;

import java.util.List;

public interface HistoryService {
    void saveVisitedLinks(List<String> links);
    List<String> getVisitedDomainsBetweenTime(long from, long to);
}
