package AnalogInProject;

import java.util.ArrayList;

public class GIM {
	// �ε� ������Ʈ
	public static Analogin GameObject;
	
	// STATUS ���� �ܺ� ����
	public static int turnTime = 10;
	public static final int FPS = 60;
	
	// ���� ������Ʈ [ ���� Manager ]
	public static KeyInputController keyInputBuffer;
		
	// GAME ����� �ʿ��� �������
	public static SceneManager currentScene = null;
	public static int blockPriority = 1;
	public static Block checkedBlock = null;
	public static ArrayList<Block> blockObject = null;
	public static KeyInputCollector currentInputCollector = null;
	// ���� ���� �Ұ�
	public static void removeGIM()
	{
		currentScene = null;
		blockPriority = 0;
		checkedBlock = null;
		blockObject = null;
		if(keyInputBuffer!=null)
			keyInputBuffer.interrupt();
		keyInputBuffer = null;
	}
}
