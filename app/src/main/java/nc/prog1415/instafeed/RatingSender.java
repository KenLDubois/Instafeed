package nc.prog1415.instafeed;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

import static androidx.constraintlayout.widget.Constraints.TAG;
import static nc.prog1415.instafeed.MainActivity.btnTestServer;

public class RatingSender extends AsyncTask<Void, Void, Void> {

    private static final long serialVersionUID = 1L;

    //Networking Objects
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private String IPAddress = "192.168.56.1";
    private int PortNum = 8000;

    @Override
    protected Void doInBackground(Void... voids) {

        try{

            InetAddress address = InetAddress.getByName(IPAddress);
            socket = new Socket(address,PortNum);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            Log.d(TAG,"Connected to TCP SERVER SUCCESSFULLY");

//            s = new Socket(IPAddress,PortNum);
//            pw = new PrintWriter(s.getOutputStream());
//            pw.write(message);
//            pw.flush();
//            pw.close();
//            s.close();

        } catch (IOException e){
            e.printStackTrace();
        }

        AddButtonListner abl = new AddButtonListner();
        abl.run();

//        if(message.length() > 0) {
//            try {
//                out.writeObject(message);
//                out.flush();
//            } catch (IOException e1) {
//                e1.printStackTrace();
//            }
//        }

        return null;
    }

    public class AddButtonListner implements Runnable {
        @Override
        public void run() {

        btnTestServer.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Log.d(TAG,"Test server click DETECTED!!!!!!!!!!!!!!");

                String output = "fasljfjslkjfjsdlj";

                try {
                    out.writeObject(output);
                    out.flush();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        }
    }
}
