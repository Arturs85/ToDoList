package com.example.user.todolist;

import android.content.Context;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by user on 2017.02.14..
 */
public class FailaOperacijas {

Context myContext ;
    public FailaOperacijas(Context context){
        myContext = context;
    }

    //make picture and save to a folder
    private File getOutputMediaFile() {
        //make a new file directory inside the "sdcard" folder
        File dataStorageDir = new File("/sdcard/", "ToDoList_data");

        //if this "JCGCamera folder does not exist
        if (!dataStorageDir.exists()) {
            //if you cannot make this folder return
            if (!dataStorageDir.mkdirs()) {
                return null;
            }
        }

        //take the current timeStamp
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        //and make a media file:
        mediaFile = new File(dataStorageDir.getPath() + File.separator + "save.dat");

        return mediaFile;
    }
    public  void saglabat(ArrayList data){

        OutputStream outStream = null;
        // File file = new File(String.valueOf(getOutputMediaFile()));
        try {
            outStream = new FileOutputStream(getOutputMediaFile());
            //img.compressToJpeg(rect, 100, outStream);
           // outStream.write(data);
            outStream.flush();
            outStream.close();

            Toast toast = Toast.makeText(myContext, " saved: ", Toast.LENGTH_LONG);
            toast.show();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
