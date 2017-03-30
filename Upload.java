package com.socket;

import com.ui.ChatFrame;
import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Upload implements Runnable{

    public String address;
    public int portNum;
    public Socket socket;
    public FileInputStream inputStream;
    public OutputStream outputStream;
    public File file;
    public ChatFrame ui;
    
    public Upload(String addr, int port, File filepath, ChatFrame frame){
        super();
        try {
            file = filepath; ui = frame;
            socket = new Socket(InetAddress.getByName(addr), port);
            outputStream = socket.getOutputStream();
            inputStream = new FileInputStream(filepath);
        } 
        catch (Exception ex) {
            
        }
    }
    
    @Override
    public void run() {
        try {       
            byte[] buffer = new byte[1024];
            int count;
            
            while((count = inputStream.read(buffer)) >= 0){
                outputStream.write(buffer, 0, count);
            }
            outputStream.flush();
            
            
            
            
            if(inputStream != null){ 
                inputStream.close();
            }
            if(outputStream != null){ 
                outputStream.close(); 
            }
            if(socket != null){ 
                socket.close(); 
            }
        }
        catch (Exception ex) {
           
            ex.printStackTrace();
        }
    }

}