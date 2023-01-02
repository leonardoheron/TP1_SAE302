package com.example.tp1;

import static androidx.core.content.ContentProviderCompat.requireContext;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.net.wifi.*;
import android.content.*;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.tp1.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ActivitePrincipal extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    EditText TextInput_Client_Name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        TextInput_Client_Name = findViewById(R.id.TextInput_Client_Name);
        System.out.println(TextInput_Client_Name);

        setSupportActionBar(binding.toolbar);

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        appBarConfiguration = new AppBarConfiguration.Builder(navController.getGraph()).build();
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);


        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }


        });
    }
    public void init_fragment2(){
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, appBarConfiguration)
                || super.onSupportNavigateUp();
    }

    public String Client_name() {
        TextView textView;
        EditText TextInput_Client_Name;


        TextInput_Client_Name = (EditText) findViewById(R.id.TextInput_Client_Name);
        String nomClient = TextInput_Client_Name.getText().toString();

        System.out.println(nomClient);
        return nomClient;
    }

    public String Server_IP() {
        EditText TextInput_Server_IP;


        TextInput_Server_IP = (EditText) findViewById(R.id.TextInput_Server_IP);
        String IP_Server = TextInput_Server_IP.getText().toString();

        System.out.println(IP_Server);
        return IP_Server;
    }


    public void setVisibility_PB(int visib) {
        ProgressBar PB = findViewById(R.id.progressBar_server);
        PB.setVisibility(visib);
    }

}