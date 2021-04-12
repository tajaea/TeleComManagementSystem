package microStarCableVision;

import java.io.*;
import java.net.*;

public class ClientConnections extends Thread implements Serializable
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Socket is one endpoint of a two-way communication link between two programs 
	//running on the network.
	private Socket socket;
	//Java DataInputStream class allows an application to read primitive data from the
	//input stream in a machine-independent way.
	private DataInputStream clientInput;
	//Java DataOutputStream class allows an application to write primitive Java data 
	//types to the output stream in a machine-independent way.
	private DataOutputStream clientOutput;
	
	//Primary Constructor that accepts as parameters a socket and object of the Cient
	//class.
	public ClientConnections(Socket socket, Client client) 
	{
		this.socket = socket;
	}
	
	//sendDataServer function that accepts a String parameter that sends whatever the user
	//enters through the client will be reflected back through the server.
	public void sendDataToServer(String text) 
	{
		try 
		{
			//writeUTF(String str) method writes a string to the underlying output 
			//stream using modified UTF-8 encoding.
			//UTF-8 is limited to 256 characters.
			//So whatever the Client writes is stored in the variable text.
			clientOutput.writeUTF(text);
			//.flush() makes sure all data/messages is cleared out of the buffer stream
			clientOutput.flush();
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			//The close function is used to close the overall connection/ports between the
			//client and server.
			close();
		}
	}
	
	public void run() 
	{
		try {
			//Getting the Input Stream from the socket.
			clientInput = new DataInputStream(socket.getInputStream());
			//Writing the Output Stream from the socket.
			clientOutput = new DataOutputStream(socket.getOutputStream());
			
			//The program will run until the condition is false or the program reaches the break
			//statement below.
			while(true) 
			{
				try 
				{
					//While there is no input/messages for the Client to read.
					while(clientInput.available() == 0) 
					{
						try 
						{
						//then we want the thread the client is running on to sleep
						//for 1 millisecond as to not exhaust system resources.
							Thread.sleep(1);
						//Thrown when a thread is waiting, sleeping, or otherwise 
						//occupied, and the thread is interrupted, either before or 
						//during the activity.
						} catch (InterruptedException e) 
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					//The readUTF(DataInput in) method of DataInputStream class reads
					//from the stream in a representation of a Unicode character 
					//string encoded in modified UTF-8 format.
					String reply = clientInput.readUTF();
					//Prints to the Server Console
					System.out.println(reply);
				} catch (IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
					//The close function is used to close the overall connection/ports between the
					//client and server.
					close();
				}
			}
		} catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			//The close function is used to close the overall connection/ports between the
			//client and server.
			close();
		}
	}
	
	//The close function is used to close the overall connection/ports between the
	//client and server.
	public void close() 
	{
		try 
		{
			clientInput.close();
			clientOutput.close();
			socket.close();
		}catch(IOException ioe) 
		{
			System.err.println(ioe);
		}
	}	
}
