package AnalogInProject;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GIM {
	// �ε� ������Ʈ
	public static Analogin GameObject;
	
	// LOCK
	public static Object checkedBlockLock = new Object();
	public static Object updateBlockInfoLock = new Object();
	
	// HOST USER STATUS
	public static UserInfo me = new UserInfo();
	public static String currentRoomName = null;
	
	// STATUS ���� �ܺ� ����
	public static int turnTime = 10;
	public static final int FPS = 60;
	
	// ���� ������Ʈ [ ���� Manager ]
	public static KeyInputController keyInputBuffer = null;
	
	// GAME ����� �ʿ��� �������
	public static SceneManager currentScene = null;
	public static int blockPriority = 1;
	private static Block checkedBlock = null;
	public static ArrayList<Block> blockObject = null;
	
	protected static KeyInputCollector currentInputCollector = null;
	
	//GAME CREATE�� �ʿ��� �������
	public static blockChangedListener synUISender = null;
	public static ArrayList<BlockInformation> loadedBlockInfo = null;
	
	//GAme Play �� �ʿ��� �������
	public static RoomInfo playingGameRoom = null;
	public static String gmaeName = null;
	public static String rule = null;
	public static ArrayList<String> imageName = null;
	public static boolean gameStart = false; 
	public static String dir = null;
	
	public static void setCheckedBlock(Block _b){
			checkedBlock = _b;
	}
	public static void setCheckedBlockCreateRoom(Block _b){
		checkedBlock = _b;
		updateData(); 
	}
	public static void updateData(){
		if(synUISender!=null){
			synUISender.setUpdaate();
		}
	}
	public static Block getCheckdBlock(){
		return checkedBlock;
	}
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
	// G 
	public static String getInputField(String str1, String str2) {
        return JOptionPane.showInputDialog(
            GIM.GameObject,
            str1,
            str2,
            JOptionPane.QUESTION_MESSAGE);
    }
}
