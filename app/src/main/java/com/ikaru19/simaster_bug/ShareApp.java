package com.ikaru19.simaster_bug;

import android.content.Intent;
import android.os.Bundle;

public class ShareApp extends SettingsActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "Simaster \n Saya ingin berbagi aplikasi Simaster. \nKamu dapat mengunduhnya di google playstore.\n https://play.google.com/store/apps/details?id=com.ikaru19.simaster_bug");
        startActivity(emailIntent);
        super.onCreate(savedInstanceState);
    }
}
