package nc.prog1415.instafeed;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

class ConnectionTask extends AsyncTask<Void,Void,Void> {
    private static final long serialVersionUID = 1L;

    //Networking Objects
    private static Socket socket;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;

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
}