package com.example.android.javadevlagos;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static com.example.android.javadevlagos.R.id.imageView;
import static com.example.android.javadevlagos.R.id.profile;
import static com.example.android.javadevlagos.R.id.url;
import static com.example.android.javadevlagos.R.id.username;

public class Profile extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_profile );

        Intent intent = getIntent ( );
        String Img = intent.getStringExtra ( "developerImg" );
        String Username = intent.getStringExtra ( "developerUsername" );
        final String Url = intent.getStringExtra ( "developerUrl" );

        TextView fab;

        fab = ( TextView ) findViewById ( url );

        //set image to view
        ImageView userImageView = ( ImageView ) findViewById ( R.id.imageView );
        Picasso.with ( this ).load ( Img ).into ( userImageView );

        //set username to view
        TextView usernameView = ( TextView ) findViewById ( R.id.username );
        usernameView.setText ( Username );

        //set url to view
       // TextView urlView = ( TextView ) findViewById ( R.id.url );
        fab.setText ( Url );

        fab.setOnClickListener ( new View.OnClickListener ( ) {
            @Override
            public void onClick(View view) {
                // Convert the String URL into a URI object (to pass into the Intent constructor)
                Uri developerUri = Uri.parse ( Url );

                // Create a new intent to view the earthquake URI
                Intent websiteIntent = new Intent ( Intent.ACTION_VIEW, developerUri );

                // Send the intent to launch a new activity
                startActivity ( websiteIntent );
            }
        } );
    }

    public void share(View v) {

        Intent intent = getIntent ( );
        String Username = intent.getStringExtra ( "developerUsername" );
        final String Url = intent.getStringExtra ( "developerUrl" );


        String msg = "Check out this awesome developer @" + Username + " , " + Url;

        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT, msg);
        sendIntent.setType("text/plain");
        startActivity(sendIntent);
    }

}
