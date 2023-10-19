package com.movieticket.services;

import com.movieticket.dao.TheatreRepository;
import com.movieticket.entities.Theatre;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public Set<Theatre> getTheatersByIds(Set<Long> theaterIds) {
        return theatreRepository.findAllByIdIn(theaterIds);
    }

    // Other theater-related methods...
}