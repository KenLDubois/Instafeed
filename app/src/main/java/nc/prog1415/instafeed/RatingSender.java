package nc.prog1415.instafeed;

import android.os.AsyncTask;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class RatingSender extends AsyncTask<String, Void, Void> {

    private static final long serialVersionUID = 1L;

    //Networking Objects
    private Socket socket;
    private ObjectInputStream in;
    private ObjectOutputStream out;

    private String IPAddress = "192.168.56.1";
    private int PortNum = 8000;

    Socket s;
    DataOutputStream dos;
    PrintWriter pw;

    @Override
    protected Void doInBackground(String... voids) {
        String message = voids[0];

        try{

            InetAddress address = InetAddress.getByName(IPAddress);
            socket = new Socket(address,PortNum);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

//            s = new Socket(IPAddress,PortNum);
//            pw = new PrintWriter(s.getOutputStream());
//            pw.write(message);
//            pw.flush();
//            pw.close();
//            s.close();

        } catch (IOException e){
            e.printStackTrace();
        }

        if(message.length() > 0) {
            try {
                out.writeObject(message);
                out.flush();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return null;
    }
}
