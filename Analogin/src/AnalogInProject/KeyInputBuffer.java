package AnalogInProject;

import java.util.ArrayList;

public class KeyInputBuffer extends Thread {
	// �� Ŭ������ ���� �����ð��� ���� ��ǻ���� �ð��� �˰� �־�� �Ѵ�.
	// ��� Ŭ���̾�Ʈ�� �ð��� ���߾� �ൿ�� ���� ���־�� ��.
	// �� ������� �����ؾ߸� �Ѵ�.
	// 5�ʿ� �ѹ��� ��ǲ����� ����
	ArrayList<Block> bl = GameInformationMaster.GameObject.currentScene.blockObject;
	Analogin GameObject = GameInformationMaster.GameObject;
	public ArrayList<String> input = new ArrayList<String>();
	
	//Turn Timer
	int timer = 0;
	private void processInputBuffer()
	{
		@SuppressWarnings("unchecked")
		ArrayList<String> temp = (ArrayList<String>) input.clone();
		for(String s : temp){
			// TYPE_OBJECTNUMBER_X_Y_OPTION
			String[] commend = s.split("_");
	//		System.out.println(commend[0]+" "+commend[1]+" "+commend[2]);
			int objNum = Integer.parseInt(commend[1]);
			int x = Integer.parseInt(commend[2]);
			int y = Integer.parseInt(commend[3]);
			if(commend[0].equals("CLICK")){
	//			System.out.println("ok");
				GameInformationMaster.GameObject.remove(bl.get(objNum));
				GameInformationMaster.GameObject.add(bl.get(objNum),0);
			}
			if(commend[0].equals("MOVE")){
	//			System.out.println("ok2");
				bl.get(objNum).setLocation(x, y);
			}
		}

		 synchronized(input){
				input.clear();
		 }
	}
	public synchronized void playIn(String s)
	{
		 synchronized(input){
				input.add(s + "_" + timer);
		 }
	}
	@Override
	public void run(){
		try{
			do{
				Thread.sleep(200);
//				System.out.println(System.nanoTime());
				processInputBuffer();
			}while(true);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
}
