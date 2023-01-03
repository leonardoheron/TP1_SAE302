package com.example.tp1;

import static androidx.core.content.ContentProviderCompat.requireContext;

import com.example.tp1.ActivitePrincipal;

import android.content.Context;
import android.net.wifi.WifiManager;
import  android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.text.format.Formatter;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;




import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.tp1.databinding.FragmentSecondBinding;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    private SecondFragment that;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentSecondBinding.inflate(inflater, container, false);
        that = this;
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((ActivitePrincipal)getActivity()).init_fragment2();
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
                Serveur task = new Serveur(that);
                task.setActivity((ActivitePrincipal)getActivity());
                task.execute();

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    public void setProgressBar(int visible){
        ((ActivitePrincipal)getActivity()).setVisibility_PB(visible);
    }

}

class Serveur extends AsyncTask<Void, String, String> {

    /*
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }
     */
    private SecondFragment parent;

    Serveur(SecondFragment fragment){
        parent= fragment;
        parent.setProgressBar(0);
        //Context context = requireContext().getApplicationContext();
        //WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);


    }

    private ActivitePrincipal Activite;

    protected void setActivity(ActivitePrincipal AP){
        Activite = AP;

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        try {
            TextView ipadd = (TextView) Activite.findViewById(R.id.IP_serveur);
            InetAddress ia = InetAddress.getLocalHost();
            String test_ip = ia.getHostAddress();
            ipadd.setText(test_ip);
            System.out.println(test_ip);
        } catch (UnknownHostException e) {
            System.out.println(e);
        }
    }

    @Override
    protected String doInBackground(Void... voids) {

        ServerSocket ss = null;

        try {

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


    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        parent.setProgressBar(4);
    }
    /*
    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
    }

     */
}