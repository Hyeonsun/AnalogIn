package AnalogInProject;
import javax.swing.JOptionPane;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintWriter;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.xml.ws.Response;

public class Scene_Start extends SceneManager {
	/************************************************
	 * Resource
	 *************************************************/
	// Music
	// private Audio introMusic;
	private JButton createBlockButton = new JButton();
	private JButton participateBlockButton = new JButton();
	private JTextField idTextField = new JTextField();
	// Image
	private Image background = ImageManager.Opening;

	/************************************************
	 * Component/object
	 *************************************************/
	private Scene_Start thisInstance = this; // thisInstance

	/************************************************
	 * GameController
	 *************************************************/

	/// GAME APPLICATION�� ����� �� �ݵ�� �ʱ�ȭ�ؾ��ϴ� GIM ����
	/// - KeyInputBuffer / GIM-currentScene / GIM-blockPriority // BlockObject
	/// -
	// ETC..
	Font font1 = new Font("SansSerif", Font.BOLD, 20);
	
	public Scene_Start() {
		//
		GIM.currentScene = this;
		// Input ���

		// Create Button
		createBlockButton.setBounds(475, 500, 155, 50);
		createBlockButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		createBlockButton.setContentAreaFilled(false); // ä������
		createBlockButton.setFocusPainted(false);
		createBlockButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				createBlockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				createBlockButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				Scene_Start.gameJoin(idTextField.getText());
			}
		});
		systemObject.add(createBlockButton);

		// part Button
		participateBlockButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		participateBlockButton.setContentAreaFilled(false); // ä������
		participateBlockButton.setBounds(633, 500, 155, 50);
		participateBlockButton.setFocusPainted(false);
		participateBlockButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				participateBlockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				participateBlockButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(!Scene_Start.gameJoin(idTextField.getText()))
					return;
				GIM.me = new UserInfo();
				GIM.me.id = idTextField.getText();
				
				GIM.GameObject.changeScene(thisInstance, "Lobby");
			}
		});
		systemObject.add(participateBlockButton);

		// input Button
		idTextField.setBounds(530, 410, 200, 50);
		idTextField.setOpaque(false);
		idTextField.setFont(font1);
		idTextField.setBorder(BorderFactory.createEmptyBorder());
		idTextField.setHorizontalAlignment(JTextField.CENTER);
		idTextField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				idTextField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				idTextField.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				idTextField.setVisible(true);
			}
		});
		systemObject.add(idTextField);
		
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
	
	//INPUT NETWORK METHOD
	public static boolean gameJoin(String id){
		if(!id.matches("[a-zA-Z0-9]{4,10}")){
			JOptionPane.showMessageDialog(null, "INVALID ID FORMAT");
			return false;	
		}
		if(!NetworkRoomServer.networkStart() || !NetworkRoomServer.setGameRegister(id)){
			JOptionPane.showMessageDialog(null, "NETWORK ERROR!");
			return false;	
		}
		GIM.me.id = id;
		
		return true;
	}


	@Override
	public void removeScene() {
		GIM.removeGIM();
		for (Component c : systemObject) {
			GIM.GameObject.remove(c);
		}
		for (Block b : blockObject) {
			GIM.GameObject.remove(b);
		}
		// introMusic.close();
	}
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, 1280, 720, null);

		GIM.GameObject.paintComponents(g);
		GIM.GameObject.repaint();
	}
}
