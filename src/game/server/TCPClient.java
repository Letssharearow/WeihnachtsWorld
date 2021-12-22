package game.server;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TCPClient {

    public static final int PORT = 4999;

    public static void main(String[] args)
    {
        TCPClient client = new TCPClient();
        System.out.println(client.getClass().getName());
        client.connect();
    }


    public void connect() {
        final String HOST = "192.168.2.112";
        Scanner scanner = new Scanner(System.in);

        try(Socket so = new Socket(HOST, PORT);){


            try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(so.getOutputStream()));
                InputStreamReader br = new InputStreamReader(so.getInputStream());
                FileOutputStream os = new FileOutputStream("text\\read.txt");) {


                String eingabe = "";
                while(true) {
                        int n = 0;
                        while(n != -1) {
                            n = br.read();
                            if(n == -1)
                                break;
                            eingabe += (char) n;
                        }
                    System.out.println(eingabe);

                    eingabe = scanner.nextLine();
                    bw.write(eingabe);
                    bw.newLine();
                    bw.flush();
                    if(eingabe.equals("end")) break;
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        } catch (UnknownHostException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        scanner.close();
    }
}