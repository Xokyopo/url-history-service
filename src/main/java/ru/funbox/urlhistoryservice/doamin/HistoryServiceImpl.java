package ru.funbox.urlhistoryservice.doamin;

import ru.funbox.urlhistoryservice.dao.HistoryRepository;
import ru.funbox.urlhistoryservice.utils.TimeUtils;
import ru.funbox.urlhistoryservice.utils.UrlUtils;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class HistoryServiceImpl implements HistoryService {
    private final TimeUtils timeUtils;
    private final UrlUtils urlUtils;
    private final HistoryRepository historyRepository;

    @Inject
    public HistoryServiceImpl(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
        this.urlUtils = new UrlUtils();
        this.timeUtils = new TimeUtils();
    }

    @Override
    public void saveVisitedLinks(List<String> links) {
        this.validateLinks(links);

        List<String> domains = links.stream().map(this.urlUtils::extractDomain).collect(Collectors.toList());

        this.historyRepository.saveVisitedDomainsInTime(domains, this.timeUtils.getCurrentTime());
    }

    @Override
    public List<String> getVisitedDomainsBetweenTime(long from, long to) {
        this.validateTimeRange(from, to);

        return this.historyRepository.getVisitedDomainsBetweenTime(from, to).values().stream()
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    private void validateTimeRange(long from, long to) {
        if (!this.timeUtils.isValidTimeRange(from, to)) throw new DomainException(String.format("Invalid time range from = %s to = %s", from , to));
    }

    private void validateLinks(List<String> links) {
        if (!links.stream().allMatch(this.urlUtils::isUrlLink)) throw new DomainException("Оne of your strings is not a url");
    }
}
