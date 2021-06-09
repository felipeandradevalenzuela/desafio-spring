package meli.desafio_spring.Time;

import java.sql.Timestamp;

public class Time {
    public static Timestamp getTime(){
        return new Timestamp(System.currentTimeMillis());
    }
}
