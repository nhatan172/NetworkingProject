/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client.ConnectToServer;

import Client.GamePlay.Home.Main;
import Client.GamePlay.Main.StartingClass;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author nhata
 */
public class Client {
    private JFrame frame;
    private StartingClass main;
    private Socket s;
     public  void start() throws IOException {
            //connect to socket
                String input = JOptionPane.showInputDialog(null, "Please insert Server IP", "Find Server",
                        JOptionPane.WARNING_MESSAGE);
                System.out.println("Connecting to " + input);
                s = new Socket(input, 9000);
                main = new StartingClass();
                frame = new JFrame("Plane War");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               frame.getContentPane().add(main);
               frame.setVisible(true);
               frame.setTitle("Plane War");
               frame.setSize(480, 800);
              frame.setResizable(false);
              frame.setFocusable(true);

               ClientThreadWriter cw = new ClientThreadWriter(s);
            main.setCw(cw);
            main.setClient(this);
            main.init();
            main.start();
            
            Runnable r = new ClientThreadListen(s, main);
            Thread t = new Thread(r);
           t.start();
          
     }
     public void end(int x) throws IOException{
         //1 = victory
         //0 = gameover
         
         if(x == 0){
//             Platform.runLater(() -> {
//                 try {
//                     Parent p = FXMLLoader.load(getClass().getResource("/Client/GamePlay/Home/GameOver.fxml"));
//                     Main.getStage().setScene(new Scene(p));
//                     Main.getStage().show();
//                 } catch (IOException ex) {
//                     Logger.getLogger(Client.class.getName()).log(Level.SEVERE, null, ex);
//                 }
//        });
//            
           
             JOptionPane.showMessageDialog(null, "Game over!");
             System.exit(0);
         }
         else
         {
           
             JOptionPane.showMessageDialog(null, "Victory!");
             System.exit(0);
         }
         frame.dispose();
         s.close();
         main.destroy();
     }
 } 

