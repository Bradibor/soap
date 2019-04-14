package ru.mirea.orgserver.model;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

public class OrganizationMapper implements Function<ru.mirea.xmltools.domain.Organization, Organization> {
    @Override
    public Organization apply(ru.mirea.xmltools.domain.Organization oldOrg) {
        Organization org = new Organization();
        org.setInn(oldOrg.getInn());
        org.setKpp(oldOrg.getKpp());
        org.setOgrn(oldOrg.getOgrn());
        org.setStatus(String.valueOf(oldOrg.getStatus()));
        org.setFounders(
                oldOrg.getFounders().stream().map(oldFounder -> {
                    Organization.Founders.Founder founder = new Organization.Founders.Founder();
                    founder.setOgrn(oldFounder.getOgrn());
                    founder.setEntityType(String.valueOf(oldFounder.getType()));
                    founder.setCapital(
                            Optional.ofNullable(oldFounder.getCapital()).map(oldCapital->{
                                Organization.Founders.Founder.Capital capital = new Organization.Founders.Founder.Capital();
                                capital.setSize(oldCapital.getSize());
                                capital.setPercent(Optional.ofNullable(oldCapital.getPercent()).map(BigDecimal::doubleValue).orElse(null));
                                return capital;
                            }).orElse(null)
                    );
                    founder.setName(
                            Optional.ofNullable(oldFounder.getName()).map(oldName->{
                                Name name = new Name();
                                name.setFullName(oldName.getFullName());
                                name.setShortName(oldName.getShortName());
                                return name;
                            }).orElse(null)
                    );
                    return founder;
                }).collect(Collectors.collectingAndThen(Collectors.toList(), founders -> {
                    Organization.Founders foundersWrap = new Organization.Founders();
                    foundersWrap.founder = founders;
                    return foundersWrap;
                }))
        );
        org.setLeaders(
                oldOrg.getLeaders().stream().map(oldLeader -> {
                    Organization.Leaders.Leader leader = new Organization.Leaders.Leader();
                    leader.setEntityType(String.valueOf(oldLeader.getType()));
                    leader.setName(Optional.ofNullable(oldLeader.getName()).map(oldName->{
                        Name name = new Name();
                        name.setFullName(oldName.getFullName());
                        name.setShortName(oldName.getShortName());
                        return name;
                    }).orElse(null));
                    leader.setOgrn(oldLeader.getOgrn());
                    leader.setPositionName(oldLeader.getPositionName());
                    return leader;
                }).collect(Collectors.collectingAndThen(Collectors.toList(), leaders -> {
                    Organization.Leaders leadersWrap = new Organization.Leaders();
                    leadersWrap.leader = leaders;
                    return leadersWrap;
                }))
        );
        org.setName(Optional.ofNullable(oldOrg.getName()).map(oldName->{
            Name name = new Name();
            name.setFullName(oldName.getFullName());
            name.setShortName(oldName.getShortName());
            return name;
        }).orElse(null));
        return org;
    }
}
