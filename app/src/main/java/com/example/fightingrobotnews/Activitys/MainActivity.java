package com.example.fightingrobotnews.Activitys;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.fightingrobotnews.R;

public class MainActivity extends AppCompatActivity {

    private ConstraintLayout ContainerContacts;

    private Intent intent = new Intent();
    private Uri uri = null;
    private Animation animClick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ContainerContacts = (ConstraintLayout) findViewById(R.id.ContainerContacts);

        animClick = AnimationUtils.loadAnimation(this, R.anim.btn_click1);
    }

    public void GoToCsGo(View view) {
        view.startAnimation(animClick);
        intent.setClass(MainActivity.this,NewsActivity.class);
        intent.putExtra("game", "cs-go");
        startActivity(intent);
    }
    public void GoToDota2(View view) {
        view.startAnimation(animClick);
        intent.setClass(MainActivity.this,NewsActivity.class);
        intent.putExtra("game", "dota-2");
        startActivity(intent);
    }
    public void GoToLol(View view) {
        view.startAnimation(animClick);
        intent.setClass(MainActivity.this,NewsActivity.class);
        intent.putExtra("game", "lol");
        startActivity(intent);
    }
    public void GoToValorant(View view) {
        view.startAnimation(animClick);
        intent.setClass(MainActivity.this,NewsActivity.class);
        intent.putExtra("game", "valorant");
        startActivity(intent);
    }

    public void ViewContacts(View view) {
        view.startAnimation(animClick);
        ContainerContacts.setVisibility(View.VISIBLE);
    }
    public void StartInstagram(View view) {
        view.startAnimation(animClick);
        intent = new Intent();
        uri = Uri.parse(getString(R.string.instagram_url));
        intent.setPackage("com.instagram.android");
        StartIntentContacts();
    }
    public void StartFacebook(View view) {
        view.startAnimation(animClick);
        intent = new Intent();
        uri = Uri.parse(getString(R.string.facebook_url));
        intent.setPackage("com.facebook.android");
        StartIntentContacts();
    }
    public void StartTelegram(View view) {
        view.startAnimation(animClick);
        intent = new Intent();
        uri = Uri.parse(getString(R.string.telegram_url));
        intent.setPackage("org.telegram.messenger");
        StartIntentContacts();
    }
    private void StartIntentContacts(){
        try {
            intent.setAction(Intent.ACTION_VIEW);
            intent.setData(uri);
            startActivity(intent);
        } catch (ActivityNotFoundException e) {
            startActivity(new Intent(Intent.ACTION_VIEW,uri));
        }
    }

    @Override
    public void onBackPressed() {
        if(ContainerContacts.getVisibility() == View.VISIBLE){
            ContainerContacts.setVisibility(View.GONE);
        }else{
            super.onBackPressed();
        }
    }

    public void StopWar(View view) {
        view.startAnimation(animClick);
        AlertDialog.Builder dialogComm = new AlertDialog.Builder(MainActivity.this);
        View v = View.inflate(MainActivity.this, R.layout.rusian_fack_you_widget, null);
        dialogComm.setView(v);
        AlertDialog newDialog = dialogComm.create();
        newDialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        newDialog.show();
    }
}