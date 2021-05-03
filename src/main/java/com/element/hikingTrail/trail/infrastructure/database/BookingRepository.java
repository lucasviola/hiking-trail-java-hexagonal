package com.element.hikingTrail.trail.infrastructure.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookingRepository extends MongoRepository<BookingEntity, String> {

    Optional<BookingEntity> findByBookingId(String bookingId);
}
