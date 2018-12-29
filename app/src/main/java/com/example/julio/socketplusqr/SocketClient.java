package com.example.julio.socketplusqr;

import java.io.PrintWriter;
import java.net.Socket;

public class SocketClient {
    private String dstAddress;
    private int dstPort;
    private PrintWriter out1;
    private Socket socket = null;
    private String message;

    public SocketClient(String dirIp, int port){
        this.dstAddress = dirIp;
        this.dstPort = port;
    }
    public void send (String txt){
        this.message = txt;
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    socket = new Socket(dstAddress,dstPort);
                    out1 = new PrintWriter(socket.getOutputStream());
                    out1.write(message);
                    out1.flush();
                    out1.close();
                    //socket.close();
                }catch (Exception e){

                }
            }
        }).start();
    }
}
