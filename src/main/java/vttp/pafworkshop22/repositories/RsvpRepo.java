package vttp.pafworkshop22.repositories;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import vttp.pafworkshop22.models.Rsvp;

import static vttp.pafworkshop22.repositories.Queries.*;

@Repository
public class RsvpRepo {

    @Autowired
    private JdbcTemplate template;

    public List<Rsvp> getAllRsvps(){
        List<Rsvp> rsvpList = new LinkedList<>();
        SqlRowSet rs = template.queryForRowSet(SQL_GET_ALL_RSVPS);
        while (rs.next()){
            rsvpList.add(Rsvp.createRsvp(rs));
        }
        return rsvpList;
    }

    public Boolean insertRsvp(String name, String email, String phone, String confirmationDate, String comments){
        Integer inserted = template.update(SQL_INSERT_RSVP, name, email, phone, confirmationDate, comments);
        return inserted > 0;
    }

    public Boolean updateRsvp(String name, String email, String phone, String confirmationDate, String comments){
        Integer inserted = template.update(SQL_UPDATE_RSVP, name, phone, confirmationDate, comments, email);
        return inserted > 0;
    }

    public Boolean checkEntryExists(String email){
        SqlRowSet rs = template.queryForRowSet(SQL_CHECK_EXISTS, email);
        rs.next();
        Integer count = rs.getInt("count(*)");
        return count > 0;
    }

    public Integer getCount(){
        SqlRowSet rs = template.queryForRowSet(SQL_GET_COUNT);
        rs.next();
        Integer count = rs.getInt("count(*)");
        return count;
    }


}
