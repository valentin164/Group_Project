package com.socket;

import com.ui.ChatFrame;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Download implements Runnable{
    public Socket socket;
    public ServerSocket serverSock;
    public int portNum;
    public String saveLocation = "";
    public InputStream inputStream;
    public FileOutputStream outStream;
    public ChatFrame ui;
    
    public Download(String saveLoc, ChatFrame ui){
        try {
            serverSock = new ServerSocket(0);
            portNum = serverSock.getLocalPort();
            this.saveLocation = saveLoc;
            this.ui = ui;
        } 
        catch (IOException ex) {
            
        }
    }

    @Override
    public void run() {
        try {
            socket = serverSock.accept();
            
            
            inputStream = socket.getInputStream();
            outStream = new FileOutputStream(saveLocation);
            
            byte[] buffer = new byte[1024];
            int count;
            
            while((count = inputStream.read(buffer)) >= 0){
                outStream.write(buffer, 0, count);
            }
            
            outStream.flush();
         
            
            if(outStream != null){
                outStream.close(); 
            }
            if(inputStream != null){
                inputStream.close(); 
            }
            if(socket != null){ 
                socket.close(); 
            }
        } 
        catch (Exception ex) {
            
        }
    }
}