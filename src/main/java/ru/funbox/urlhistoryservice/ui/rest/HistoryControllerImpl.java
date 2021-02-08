package ru.funbox.urlhistoryservice.ui.rest;

import ru.funbox.urlhistoryservice.doamin.HistoryService;
import ru.funbox.urlhistoryservice.ui.rest.representations.Status;
import ru.funbox.urlhistoryservice.ui.rest.representations.VisitedDomains;
import ru.funbox.urlhistoryservice.ui.rest.representations.VisitedLinks;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HistoryControllerImpl implements HistoryController{
    private final HistoryService historyService;

    @Inject
    public HistoryControllerImpl(HistoryService historyService) {
        this.historyService = historyService;
    }

    @Override
    @POST
    @Path("/visited_links")
    public Status saveVisitedLinks(VisitedLinks visitedLinks) {
        Status result = new Status("ok");

        try {
            this.historyService.saveVisitedLinks(visitedLinks.getLinks());
        } catch (Exception e) {
            result.setStatus(e.getMessage());
        }

        return result;
    }

    @Override
    @GET
    @Path("/visited_domains")
    public VisitedDomains getVisitedDomainsBetweenTime(@QueryParam("from") long from, @QueryParam("to") long to) {
        VisitedDomains result = new VisitedDomains(null, "ok");

        try {
            result.setDomains(this.historyService.getVisitedDomainsBetweenTime(from, to));
        } catch (Exception e) {
            result.setStatus(e.getMessage());
        }

        return result;
    }
}
