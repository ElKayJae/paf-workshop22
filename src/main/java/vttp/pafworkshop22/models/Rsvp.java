package vttp.pafworkshop22.models;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Rsvp {
    private String name;
    private String email;
    private String phone;
    private Date confirmatioDate;
    private String comments;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Date getConfirmatioDate() {
        return confirmatioDate;
    }
    public void setConfirmatioDate(Date confirmatioDate) {
        this.confirmatioDate = confirmatioDate;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }

    public static Rsvp createRsvp(SqlRowSet rs){
        Rsvp rsvp = new Rsvp();
        rsvp.setName(rs.getString("name"));
        rsvp.setEmail(rs.getString("email"));
        rsvp.setPhone(rs.getString("phone"));
        rsvp.setConfirmatioDate(rs.getDate("confirmation_date"));
        rsvp.setComments(rs.getString("comments"));

        return rsvp;
    }

    public JsonObject toJson(){
        JsonObjectBuilder builder = Json.createObjectBuilder()
                                .add("name", getName())
                                .add("email", getEmail())
                                .add("phone", getPhone())
                                .add("confirmation_date", getConfirmatioDate().toString())
                                .add("comments", getComments());
        return builder.build();
 
    }
}
