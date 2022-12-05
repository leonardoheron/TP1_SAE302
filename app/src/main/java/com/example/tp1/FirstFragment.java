package com.example.tp1;

import com.example.tp1.ActivitePrincipal;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Context;

import java.io.DataOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tp1.databinding.FragmentFirstBinding;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;
    private EditText TextInput_Client_Name;
    private TextView textView;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);




        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(FirstFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
            }
        });

        binding.buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Client C = new Client();
                C.setActivity((ActivitePrincipal)getActivity());
                C.execute();
                }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    
    


}

class Client extends AsyncTask<Void, String, String> {

    /*
    TextView textView;
    EditText inputText;



    @Override
    protected void OnCreate()
    inputText = (EditText) findViewById(R.id.TextInput_Client_Name);
    String nomClient = simpleEditText.getText().toString();*/

    private ActivitePrincipal Activite;

    protected void setActivity(ActivitePrincipal AP){
        Activite = AP;

    }
    /*
    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        ActivitePrincipal AP = new ActivitePrincipal();
        String nomClient = AP.Client_name();
        System.out.println(nomClient);

        }

    */

    @Override
    protected String doInBackground(Void... voids) {
        Socket client = null;
        try {
            String nomClient = Activite.Client_name();
            client = new Socket("192.168.1.16",5001);
            System.out.println("Nom client");
            System.out.println(nomClient);
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            out.writeUTF(nomClient);
            DataInputStream in = new DataInputStream(client.getInputStream());
            String s1 = in.readUTF();
            System.out.println(s1);
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Termine client");
        return "Terminé client";
    }

/*
    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }
*/
}