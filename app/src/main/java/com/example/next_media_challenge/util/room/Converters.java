package com.example.next_media_challenge.util.room;

import com.example.next_media_challenge.model.PostTitle;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.room.TypeConverter;

public class Converters {

    /**
     * for Convert PostTitle object to String
     * we use this coneverter to store PostTitle in room data
     * @param title
     * @return
     */
    @TypeConverter
    public String fromTitleToString(PostTitle title){
        return title.getRendered();
    }
    @TypeConverter
    public PostTitle fromStringToTitle(String title){
        return new PostTitle(title);
    }



    public static   SimpleDateFormat far = new SimpleDateFormat("dd MMMM YYYY", new Locale("ar","MA"));

    /**
     * Convert Date to String Format
     * @param stringDate
     * @return
     */
    @TypeConverter
    public static Date fromStringToDate(String stringDate){
        Date date=new Date();
        try {
            date  = far.parse(stringDate);
        } catch (ParseException e) {

            //   e.printStackTrace();
        }

        return  date;
    }


}
