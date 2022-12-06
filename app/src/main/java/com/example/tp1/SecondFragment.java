package com.example.tp1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tp1.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SecondFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        binding.buttonServer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Serveur task = new Serveur();
                task.execute();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}

class Serveur extends AsyncTask<Void, String, String> {

    /*
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
     */

    private ActivitePrincipal Activite;

    protected void setActivity(ActivitePrincipal AP){
        Activite = AP;

    }

    @Override
    protected String doInBackground(Void... voids) {

        ServerSocket ss = null;
        try {
            Activite.setVisibility_PB();
            ss = new ServerSocket(5001);
            System.out.println("En attente de connexion d'un client");
            Socket s = ss.accept();
            System.out.println("Connexie établie");
            DataInputStream in = new DataInputStream(s.getInputStream());
            String nomClient = in.readUTF();
            String s1 = "Bienvenue "+nomClient+", t'es bien connecté bro";
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            out.writeUTF(s1);
            ss.close();
        } catch (IOException e) {
            System.out.println("Ceci est une erreur.");
            e.printStackTrace();
        }
        return "Terminé";


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