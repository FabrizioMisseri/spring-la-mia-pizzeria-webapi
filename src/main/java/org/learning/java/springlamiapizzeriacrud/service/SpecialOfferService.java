package org.learning.java.springlamiapizzeriacrud.service;

import org.learning.java.springlamiapizzeriacrud.model.Pizza;
import org.learning.java.springlamiapizzeriacrud.model.SpecialOffer;
import org.learning.java.springlamiapizzeriacrud.repository.SpecialOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SpecialOfferService {

    @Autowired
    private SpecialOfferRepository specialOfferRepository;

    public SpecialOffer create(SpecialOffer formSpecialOffer) {

        return specialOfferRepository.save(formSpecialOffer);
    }

    public SpecialOffer getById(Integer id) {
        Optional<SpecialOffer> result = specialOfferRepository.findById(id);
        if (result.isPresent()) {
            return result.get();
        } else {
            throw new RuntimeException();
        }
    }

    public SpecialOffer update(Integer id, SpecialOffer formSpecialOffer) {
        SpecialOffer specialOfferToUpdate = getById(id);
        specialOfferToUpdate.setTitle(formSpecialOffer.getTitle());
        specialOfferToUpdate.setStartingDate(formSpecialOffer.getStartingDate());
        specialOfferToUpdate.setEndingDate(formSpecialOffer.getEndingDate());
        return specialOfferRepository.save(specialOfferToUpdate);
    }
}
