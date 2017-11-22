package AnalogInProject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;

public class NetworkHandler extends Thread {
	private Socket socket;
	public BufferedReader in;
	public PrintWriter out;
	private Player a;

	private String[] outputBuffer = new String[10];
	private Queue<String> inputBuffer = new LinkedList<String>();
	/**
         * Constructs a handler thread, squirreling away the socket.
         * All the interesting work is done in the run method.
         */
	
	// Consumer Provider ����
	// �� ������� �Է¹��۸� ����ϴٰ� ó���Ѵ�.
	
	public NetworkHandler(Socket socket) {
            this.socket = socket;
    }
	public synchronized void setInputBuffer(int outputPort, String str){
		if(outputPort>=10){
			System.out.println("ERROR!!!!");
			return;
		}
		inputBuffer.add(outputPort+"/"+str);
	}

	public void run() {
		// ���� try Catch �������� �������� �����ϸ� �� �÷��̾�� ����������.
		try{
            in = new BufferedReader(new InputStreamReader(
                    socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream(), true);
			
			while(true)
			{
				if(!inputBuffer.isEmpty()){
					inputBuffer.remove();
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			// �÷��̾� ��������
		}
	}
}
