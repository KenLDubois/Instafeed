package nc.prog1415.instafeed;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import nc.sharedInstafeedClasses.ContentRequest;
import nc.sharedInstafeedClasses.Rating;

class ConnectionTask extends AsyncTask<Void,Void,Void> {
    private static final long serialVersionUID = 1L;

    //Networking Objects
    private static Socket socket;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;

    public static String feedback;

    private String IPAddress = "192.168.56.1";
    private int PortNum = 8000;

    private static final String TAG = "ConnectionTask";

    @Override
    protected Void doInBackground(Void... voids) {

        try{

            InetAddress address = InetAddress.getByName(IPAddress);
            socket = new Socket(address,PortNum);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            Thread receiveContent = new Thread(new ReceiveContent());
            receiveContent.run();

        } catch (IOException e){
            e.printStackTrace();
        }

        return null;
    }

    public static void sendRating(Rating rating){
        try {
            out.writeObject(rating);
            out.flush();
            Log.d(TAG, "Rating sent");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void sendMessage(String message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }

    public static void sendContentRequest(){
        try{
            out.writeObject(new ContentRequest());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class ReceiveContent implements Runnable{

        public boolean run = true;

        @Override
        public void run() {
            {
                while(true){
                    try {
                        Object obj = in.readObject();
                        if(obj instanceof String){
                            feedback = obj.toString();
                        }
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}


