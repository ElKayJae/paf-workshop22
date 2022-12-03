package vttp.pafworkshop22.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.pafworkshop22.models.Rsvp;
import vttp.pafworkshop22.repositories.RsvpRepo;

@Service
public class RsvpService {
    
    @Autowired
    private RsvpRepo rsvpRepo;

    public List<Rsvp> getRsvpList(){
        return rsvpRepo.getAllRsvps();
    }

    public Boolean insertRsvp(String name, String email, String phone, String confirmationDate, String comments){
        return rsvpRepo.insertRsvp(name, email, phone, confirmationDate, comments);
    }

    public Boolean updateRsvp(String name, String email, String phone, String confirmationDate, String comments){
        return rsvpRepo.updateRsvp(name, email, phone, confirmationDate, comments);
    }

    public Boolean checkEntryExists(String email){
        return rsvpRepo.checkEntryExists(email);
    }

    public Integer getCount(){
        return rsvpRepo.getCount();
    }

}
