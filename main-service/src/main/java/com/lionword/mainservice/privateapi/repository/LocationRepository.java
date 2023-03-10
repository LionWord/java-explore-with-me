package com.lionword.mainservice.privateapi.repository;

import com.lionword.mainservice.entity.location.Location;
import com.lionword.mainservice.privateapi.repository.limitedparents.LimitedPrivateLocationRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends LimitedPrivateLocationRepository<Location, Long> {
}
