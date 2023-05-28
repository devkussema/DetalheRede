/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package detalherede;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Augusto Kussema
 */
public class DetalheRede {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Process process = Runtime.getRuntime().exec("netsh wlan show interfaces");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;           
            
            if ( isConnected() ) {
                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (line.startsWith("Name")) {
                        String name = line.split(":")[1].trim();
                        System.out.println("Nome: " + name);
                    } else if (line.startsWith("Description")) {
                        String description = line.split(":")[1].trim();
                        System.out.println("Descrição: " + description);
                    } else if (line.startsWith("Physical address")) {
                        String physicalAddress = line.split(":")[1].trim();
                        System.out.println("Endereço físico: " + physicalAddress);
                    } else if (line.startsWith("State")) {
                        String state = line.split(":")[1].trim();
                        System.out.println("Estado: " + state);
                    } else if (line.startsWith("SSID")) {
                        String ssid = line.split(":")[1].trim();
                        System.out.println("SSID: " + ssid);
                    } else if (line.startsWith("Network type")) {
                        String networkType = line.split(":")[1].trim();
                        System.out.println("Tipo de rede: " + networkType);
                    } else if (line.startsWith("Radio type")) {
                        String radioType = line.split(":")[1].trim();
                        System.out.println("Tipo de rádio: " + radioType);
                    } else if (line.startsWith("Authentication")) {
                        String authentication = line.split(":")[1].trim();
                        System.out.println("Autenticação: " + authentication);
                    } else if (line.startsWith("Cipher")) {
                        String cipher = line.split(":")[1].trim();
                        System.out.println("Cifra: " + cipher);
                    } else if (line.startsWith("Channel")) {
                        String channel = line.split(":")[1].trim();
                        System.out.println("Canal: " + channel);
                    } else if (line.startsWith("Signal")) {
                        String signal = line.split(":")[1].trim();
                        System.out.println("Sinal: " + signal);
                    }
                }                 
            }else{
                System.out.println("Conecte-se a uma rede");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static boolean isConnected() {
        boolean isConnected = false;
        boolean hasInternet = false;
        
        try {
            Process process = Runtime.getRuntime().exec("netsh wlan show interfaces");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;

            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.startsWith("State")) {
                    String state = line.split(":")[1].trim();
                    if (state.equalsIgnoreCase("connected")) {
                        isConnected = true;
                    }
                } else if (line.startsWith("Profile")) {
                    String profile = line.split(":")[1].trim();
                    if (!profile.equals("All User Profile")) {
                        hasInternet = true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isConnected;
    }
    
}
