package com.example.endoscope;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

public class model {
    String patientName,mediaPath;
    public model(String patientName, String mediaPath) {
        this.patientName = patientName;
        this.mediaPath = mediaPath;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Bitmap getMediaPath() {
        Uri mediaUri = Uri.parse(mediaPath);
        Bitmap trail = BitmapFactory.decodeFile(mediaPath);
        return trail;
    }

    public void setMediaPath(String mediaPath) {
        this.mediaPath = mediaPath;
    }
}
