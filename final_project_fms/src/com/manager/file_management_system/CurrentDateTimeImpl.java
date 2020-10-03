package com.manager.file_management_system;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CurrentDateTimeImpl implements CurrentDateTimeInterface {

    @Override
    public String currentDateTime() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        String currentDate = formatter.format(date);
        return currentDate;
    }


}