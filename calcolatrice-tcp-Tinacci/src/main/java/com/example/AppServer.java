package com.example;

import java.io.*;
import java.net.*;

public class AppServer 
{
    ServerSocket server = null;
    Socket socketClient = null;

    int porta = 6789;

    BufferedReader in;
    DataOutputStream out;

    int letto;

    int numClient;
    String operatoreClient;

    int risposta;

    public Socket attendi(){

        try {

            System.out.println("Inizializzo il server...");

            server = new ServerSocket(porta);

            System.out.println("Server pronto in ascolto sulla porta: " + porta);

            socketClient = server.accept();

            System.out.println("Connessione stabilita con un client!");

            server.close();

            in = new BufferedReader( new InputStreamReader (socketClient.getInputStream()));
            out = new DataOutputStream(socketClient.getOutputStream());
        
        } catch (IOException e) {
           
            e.printStackTrace();
        }

        return socketClient;
    }

    public void comunica()
    {
        try {

            do{

                System.out.println("Aspetto un messaggio dal client...");
                letto = Integer.parseInt(in.readLine());
                operatoreClient = in.readLine();
                numClient = Integer.parseInt(in.readLine());
                System.out.println("Operazione ricevuta:" + letto + operatoreClient + numClient);
                if(operatoreClient.equals("+")){
                    risposta = letto + numClient;
                    System.out.println(risposta);
                    out.writeBytes(String.valueOf((risposta)) + "\n");
                }else if(operatoreClient == "-"){
                    risposta = letto - numClient;
                    System.out.println(risposta);
                    out.writeBytes(String.valueOf((risposta))+"\n");
                }else if(operatoreClient == "*"){
                    System.out.println(risposta);
                    risposta = letto * numClient;
                    out.writeBytes(String.valueOf((risposta))+"\n");
                }else if(operatoreClient == "/"){
                    System.out.println(risposta);
                    risposta = letto / numClient;
                    out.writeBytes(String.valueOf((risposta))+"\n");
                }else{
                    out.writeBytes("operatore o numero non valido");
                }  
            }while(!String.valueOf(letto).toLowerCase().equals("esci"));
                System.out.println("Chiudo la connessione.");
                socketClient.close();   
            

        } catch (IOException e) {
           
            e.printStackTrace();
        }
    }


    public static void main( String[] args ) throws IOException
    {
        AppServer a =  new AppServer();

        a.attendi();
        a.comunica();
    }
}
