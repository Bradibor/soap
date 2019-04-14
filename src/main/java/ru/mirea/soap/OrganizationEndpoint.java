package ru.mirea.soap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.mirea.orgserver.model.GetOrganizationByOgrnRequest;
import ru.mirea.orgserver.model.GetOrganizationByOgrnResponse;
import ru.mirea.orgserver.model.OrganizationMapper;
import ru.mirea.xmltools.xmlprocessing.OrganizationService;

@Endpoint
public class OrganizationEndpoint {
    private static final String NAMESPACE_URI = "http://www.mirea.ru/orgserver/model";
    private final OrganizationService organizationService;

    @Autowired
    public OrganizationEndpoint(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrganizationByOgrnRequest")
    @ResponsePayload
    public GetOrganizationByOgrnResponse getOrganizationByOgrn(@RequestPayload GetOrganizationByOgrnRequest request) {
        GetOrganizationByOgrnResponse response = new GetOrganizationByOgrnResponse();

        response.setOrganization(organizationService.getByOgrn(request.getOgrn()).map(new OrganizationMapper()).orElse(null));

        return response;
    }
}
