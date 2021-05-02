package com.element.hikingTrail.trail.infrastructure.database;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends MongoRepository<BookingEntity, String> {

    BookingEntity findByBookingId(String bookingId);
}
