package org.learning.java.springlamiapizzeriacrud.service;

import org.learning.java.springlamiapizzeriacrud.model.SpecialOffer;
import org.learning.java.springlamiapizzeriacrud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpecialOfferService {

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    public SpecialOffer create(SpecialOffer formSpecialOffer) {
        return specialOfferRepository.save(formSpecialOffer);
    }
}
