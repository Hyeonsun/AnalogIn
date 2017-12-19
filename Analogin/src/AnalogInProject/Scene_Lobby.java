package AnalogInProject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class Scene_Lobby extends SceneManager {
	/************************************************
	 * Resource
	 *************************************************/
	// Music
	// private Audio introMusic;
	private JButton backButton = new JButton();
	private JButton createButton = new JButton();
	
	// Image
	private Image background = ImageManager.Lobby;

	/************************************************
	 * Component/object
	 *************************************************/
	private Scene_Lobby thisInstance = this; // thisInstance
	private JList roomList = new JList();
	public static HashMap<String, RoomInfo> roomInfoList = new HashMap<String, RoomInfo>();
	public static DefaultListModel dlm = new DefaultListModel();
	public static RoomLobby currentRoom = null;

	/************************************************
	 * GameController
	 *************************************************/
	private ArrayList<Thread> backendObject = new ArrayList<Thread>();
	private ArrayList<Handler> systemHandler = new ArrayList<Handler>();
	
	/// GAME APPLICATION�� ����� �� �ݵ�� �ʱ�ȭ�ؾ��ϴ� GIM ����
	/// - KeyInputBuffer / GIM-currentScene / GIM-blockPriority // BlockObject
	/// -
	// ETC..
	Font font1 = new Font("SansSerif", Font.BOLD, 20);

	/*
	 * room join
	 */
	/*
	 * room refresh
	 */
	private static void roomRef() {
		synchronized (dlm) {
			dlm.clear();
			for (String roomName : roomInfoList.keySet()) {
				dlm.addElement(roomInfoList.get(roomName));
			}
		}
	}
	
	/*
	 * set RoomInfoList
	 */
	public static void synRoomInfoWithServer() {
		System.out.println("[synRoomInfoWithServer] Syn Start");
		synchronized (roomInfoList) {
			HashMap<String, RoomInfo> temp = NetworkRoomServer.getRoomInfo();
			if (temp != null) {
				roomInfoList = NetworkRoomServer.getRoomInfo();
				roomRef();
			}
		}
		if(currentRoom!=null){
			System.out.println("REFRESH");
			currentRoom.refresh();
		}
	}

	/**
	 * init() 1) Room Server register / USER ID AND USER 2) receive Room list 3)
	 * roomList initialization
	 */
	private void init() {
		synRoomInfoWithServer();
		
		for (Thread t : backendObject) {
			t.start();
		}
		systemHandler.add(NetworkRoomServer.systemHandler("LOBBY_STATE_CHANGE"));
		//systemHandler.add(NetworkRoomServer.systemHandler("STUN_SERVER_STEP1"));
	}

	public Scene_Lobby() {
		// currentSecene register
		GIM.currentScene = this;

		// initialization
		init();

		// Room List Panel
		roomList = new JList(dlm);
		roomList.setFixedCellWidth(600);
		roomList.setFont(font1);
		roomList.setOpaque(false);
		roomList.setVisibleRowCount(9);
		roomList.setCellRenderer(new TransparentListCellRenderer());
		roomList.setFixedCellHeight(60);
		roomList.setSelectedIndex(0);
		roomList.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					// JOIN GAME ROOM
					if (currentRoom != null) {
						currentRoom.roomLobbyClose();
						currentRoom = null;
					}
					RoomInfo room = (RoomInfo) roomList.getSelectedValue();
					// User ���� ������ -> �ٽ� �� �ʱ�ȭ
					if(!NetworkRoomServer.joinRoom(room.roomName)){
						return;
					}

					synRoomInfoWithServer();
					currentRoom = new RoomLobby(room.roomName);
					GIM.currentRoomName = room.roomName;
				}
			}
		});
		JScrollPane j = new JScrollPane();
		j.setViewportView(roomList);
		j.setBounds(42, 144, 625, 540);
		j.getViewport().setOpaque(false);
		j.setOpaque(false);
		systemObject.add(j);

		// Back Button
		createButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		createButton.setContentAreaFilled(false); // ä������
		createButton.setBounds(970, 600, 82, 47);
	//	createButton.setFocusPainted(false);
		createButton.setText("CREATE");
		createButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				createButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				createButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				ArrayList<BlockInformation> blockInfo = null;
				String loadpath = SaveLoadManager.getLoadDirectory();
				if(loadpath!=null)
					blockInfo = SaveLoadManager.loadMap(loadpath);
				else{
					System.out.println("LoadPathFail");
				}
				GIM.loadedBlockInfo = blockInfo;
				GIM.currentRoomName = "CHESS GAME!!" + (int)(Math.random()*100);
				
				//GAME �̸� �˰�, RULE �ƴϱ� �׳� �����ϸ� �� gameName] [UserName] [RULE]
				String res;
				if((res = (String)NetworkRoomServer.Sender("CREATE_ROOM " + GIM.currentRoomName + "-" + 
				GIM.gmaeName + "-" +
				GIM.me.id + "-" +
				GIM.rule + "-", "object", true))==null){
					System.out.println("�游��� ����");
					return;
				};
				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// JOIN GAME ROOM
				if (currentRoom != null) {
					currentRoom.roomLobbyClose();
					currentRoom = null;
				}
				//SENDER���� ��û// �������� ROOM�� �����ش�.
				RoomInfo room;					
				synchronized(roomInfoList){
					if(roomInfoList.containsKey(GIM.currentRoomName))
						room = roomInfoList.get(GIM.currentRoomName);
					else
					{
						System.out.println("���� �������� �������� ���� return");
						return;
					}
				}
				synRoomInfoWithServer();
				currentRoom = new RoomLobby(room.roomName);
				GIM.currentRoomName = room.roomName;
			}
		});
		systemObject.add(createButton);
		// MakeGame
		backButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		backButton.setContentAreaFilled(false); // ä������
		backButton.setBounds(1182, 20, 52, 47);
		backButton.setFocusPainted(false);
		backButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				backButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				NetworkRoomServer.networkClose();
				GIM.GameObject.changeScene(thisInstance, "Start");
			}
		});
		systemObject.add(backButton);
		// Input ���

		for (Component b : systemObject) {
			GIM.GameObject.add(b);
		}
		GIM.blockPriority = systemObject.size();
		for (Block b : blockObject) {
			GIM.GameObject.add(b);
		}

		GIM.GameObject.repaint();

		// introMusic = new Audio("testMusic.mp3", true); �ڲ� �������� �ּ�ó�� �س���
		// introMusic.start();
	}

	@Override
	public void removeScene() {
		NetworkRoomServer.removeHandler();
		GIM.removeGIM();
		for (Component c : systemObject) {
			GIM.GameObject.remove(c);
		}
		for (Block b : blockObject) {
			GIM.GameObject.remove(b);
		}
		for (Thread t : backendObject) {
			t.interrupt();
		}
		for (Handler t : systemHandler) {
			System.out.println("REmove : " + t.getName());
			t.interrupt();
		}
		// introMusic.close();
	}

	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, 1280, 720, null);

		GIM.GameObject.paintComponents(g);
		GIM.GameObject.repaint();
	}
}
