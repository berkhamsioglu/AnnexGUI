package AnnexGUI;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.DatagramPacket;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Network implements Runnable
{
        private String readOuts;
        public String getReadOuts() {
		return readOuts;
	}
public static String getD410() throws Exception
    {
     String RemoteIPAddress= "192.168.0.10";
     String SID, RSV, GCT, ICF;
     String SNA1= "00";
     String SA11= "78";//3E:Raspberry - 78:Laptop
     String SA21= "00";
     String DNA1= "00";
     String DA11= "0A";
     String DA21= "00";

     int CmdLength, Cnt;
     byte[] ByteArray;
     byte[] receiveData = new byte[1024];
     String  Header, tmp1, CMND;
     InetAddress Remote_IP;
     DatagramSocket Ethernet_Socket;

     Cnt = 0;
     ICF = "80";
     GCT = "02";
     RSV = "00";
     SID = "00";

     Header = ICF + RSV + GCT + DNA1 + DA11 + DA21 + SNA1 + SA11 + SA21 + SID;
     CMND = Header + "010182019A000018";
    
     CmdLength = CMND.length();
     ByteArray = new byte[1 + (CmdLength - 2) / 2];

     for(int X = 0;X<CmdLength-1;X=X+2)
     {
         tmp1 = CMND.substring(X, X+2);
         ByteArray[Cnt] = (byte)Integer.parseInt(tmp1,16);
         Cnt += 1;
     }
    
     Remote_IP = InetAddress.getByName(RemoteIPAddress);
     Ethernet_Socket = new DatagramSocket(9600);
     DatagramPacket sendPacket = new DatagramPacket(ByteArray, ByteArray.length, Remote_IP, 9600);
     Ethernet_Socket.send(sendPacket);
     Ethernet_Socket.setSoTimeout(200);
     DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
     Ethernet_Socket.receive(receivePacket);
     System.out.println("RECEIVED: " + receiveData[14]);
     String strValues = "";
     StringBuilder sb = new StringBuilder();
     int[] receivedValues = new int[24];
     for(int i = 0; i<24; i++)
     {
    	 receivedValues[i] = (int)receiveData[14+i];
         if(receivedValues[i]<0)
         {
        	 receivedValues[i] = 128 + receivedValues[i];
        	 receivedValues[i] = receivedValues[i] | 128;    	 
         }
         String hex1 = Integer.toHexString(receivedValues[i]);
         int intHex1 = Integer.parseInt(hex1, 16);
         char cHex1 = (char)intHex1;
         sb.append(cHex1);
     }

     strValues = sb.toString();
     System.out.println("RECEIVED: " + strValues);

     Ethernet_Socket.close();
     return strValues;
    }
	@Override
	public void run(){
		// TODO Auto-generated method stub
		while(true)
                {        
        		try {	
                            readOuts = getD410();
                            Thread.sleep(1000);
			} catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
			} catch (Exception ex) {
                        Logger.getLogger(Network.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
	}
}
