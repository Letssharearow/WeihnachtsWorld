package game.server;

import game.Game;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    Game gm = new Game();

    public static void main(String[] args) {
        TCPServer server = new TCPServer();
        server.waitConnect();


    }

    public void waitConnect() {
        try (ServerSocket ss = new ServerSocket(TCPClient.PORT);
             Socket connection = ss.accept();
             BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));	){

            String eingabe = "";
            bw.write(gm.toString());
            bw.newLine();
            bw.flush();
            while(true) {

                eingabe = br.readLine();
                if(eingabe != null) {
                    if(eingabe.equals("end")) break;
                    else {
                        gm.manageInput(eingabe);
                        bw.write(gm.toString());
                        bw.newLine();
                        bw.flush();
                    }

                }
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
