package vttp.pafworkshop22.repositories;

public class Queries {
    public static final String SQL_GET_ALL_RSVPS = "select * from rsvp";
    public static final String SQL_INSERT_RSVP = "insert into rsvp(name, email, phone, confirmation_date, comments) values (?,?,?,?,?)";
    public static final String SQL_UPDATE_RSVP = "update rsvp set name = ?, phone = ?, confirmation_date = ?, comments = ? where email = ?";
    public static final String SQL_CHECK_EXISTS = "select count(*) from rsvp where email = ?";
    public static final String SQL_GET_COUNT = "select count(*) from rsvp";

}
