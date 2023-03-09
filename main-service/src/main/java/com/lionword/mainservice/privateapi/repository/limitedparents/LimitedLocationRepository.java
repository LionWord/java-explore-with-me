package com.lionword.mainservice.privateapi.repository.limitedparents;

import com.lionword.mainservice.entity.location.Location;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

@NoRepositoryBean
public interface LimitedLocationRepository<Location, Long> extends Repository<Location, Long> {
    Location save(Location location);
}
