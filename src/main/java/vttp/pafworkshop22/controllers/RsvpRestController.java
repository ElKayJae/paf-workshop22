package vttp.pafworkshop22.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonArrayBuilder;
import vttp.pafworkshop22.models.Rsvp;
import vttp.pafworkshop22.services.RsvpService;

@RestController
@RequestMapping("api")
public class RsvpRestController {

    @Autowired
    public RsvpService rsvpService;
    
    @GetMapping(path="rsvps", produces= MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllRsvps(){
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        List<Rsvp> rsvpList = rsvpService.getRsvpList();
        for (Rsvp rsvp : rsvpList){
            arrayBuilder.add(rsvp.toJson());
        }
        return ResponseEntity.ok().body(arrayBuilder.build().toString());
    }

    @PostMapping(path="rsvp", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> insertRsvp(@RequestBody MultiValueMap<String,String> form){
        String name = form.getFirst("name");
        String email = form.getFirst("email");
        String phone = form.getFirst("phone");
        String confirmationDate = form.getFirst("confirmationDate");
        String comments = form.getFirst("comments");
        if (rsvpService.checkEntryExists(email)){
            rsvpService.updateRsvp(name, email, phone, confirmationDate, comments);
        } else {
            rsvpService.insertRsvp(name, email, phone, confirmationDate, comments);
        }

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping(path="rsvp/{email}", produces=MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> updateRsvp(@RequestBody MultiValueMap<String,String> form, @PathVariable String email){
        String name = form.getFirst("name");
        String phone = form.getFirst("phone");
        String confirmationDate = form.getFirst("confirmationDate");
        String comments = form.getFirst("comments");
        if (rsvpService.checkEntryExists(email)){
            rsvpService.updateRsvp(name, email, phone, confirmationDate, comments);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping (path = "rsvps/count")
    public ResponseEntity<String> getCount(){
        Integer count = rsvpService.getCount();

        return ResponseEntity.status(HttpStatus.CREATED).body(Integer.toString(count));
    }

}
