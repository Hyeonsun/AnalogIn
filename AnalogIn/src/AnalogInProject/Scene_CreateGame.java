package AnalogInProject;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class Scene_CreateGame extends SceneManager {
	/************************************************
	   Resource
	*************************************************/
	// Music
//	private Audio introMusic;
	
	// Image
	private Image background = ImageManager.background;
	private Image image_mainFrame = ImageManager.mainFrame;
	private Image image_subFrame = ImageManager.subFrame;
	
	/************************************************
	   Component/object
	*************************************************/	
	private Scene_CreateGame thisInstance = this; // thisInstance
	private JButton createBlockButton = new JButton(
			new ImageIcon(ImageManager.testButtonImage_1.getScaledInstance(240, 320, Image.SCALE_SMOOTH)));
	private JButton imageLoadButton = new JButton(
			new ImageIcon(ImageManager.testButtonImage_2.getScaledInstance(240, 320, Image.SCALE_SMOOTH)));
	private JButton deleteButton = new JButton(
			new ImageIcon(ImageManager.testButtonImage_3.getScaledInstance(240, 320, Image.SCALE_SMOOTH)));
	private JButton ruleButton = new JButton(
			new ImageIcon(ImageManager.testButtonImage_4.getScaledInstance(240, 320, Image.SCALE_SMOOTH)));
	private JButton exitButton = new JButton(
			new ImageIcon(ImageManager.testButtonImage_5.getScaledInstance(240, 320, Image.SCALE_SMOOTH)));
	
	private JLabel createBlockField = new JLabel();
	
	/************************************************
	   GameController
	*************************************************/	
	
	
	///GAME APPLICATION�� ����� �� �ݵ�� �ʱ�ȭ�ؾ��ϴ� GIM ����
	/// - KeyInputBuffer / GIM-currentScene / GIM-blockPriority // BlockObject
	/// - 
	public Scene_CreateGame() {
		// 
		GIM.currentScene = this;
		// Input ���
		GIM.keyInputBuffer = new KeyInputBuffer();
		GIM.keyInputBuffer.start();		
		GIM.blockObject = blockObject;
		
		// Block�� �켱����
		GIM.blockPriority = 1;
		
		// button
		createBlockButton.setBounds(910, 90, 100, 100);
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
				createBlockField.setVisible(true);
			}
		});
		systemObject.add(createBlockButton);
		
		// image Upload button
		imageLoadButton.setBounds(1027, 90, 100, 100);
		imageLoadButton.setBorderPainted(true); // ��ư ���� ���� ������ true�� ���� �� �׽�Ʈ��
		imageLoadButton.setContentAreaFilled(false); // ä������
		imageLoadButton.setFocusPainted(false);
		imageLoadButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				imageLoadButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				imageLoadButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}

			@Override
			public void mousePressed(MouseEvent e) {
				if(GIM.checkedBlock!=null)
				{
					String path = ImageManager.loadImage();
					if(path!=null && GIM.checkedBlock!=null)
					{
						GIM.checkedBlock.blockInfo.setImagePath(path);
						GIM.checkedBlock.synBlockInfo();				
					}
				}
			}
		});
		systemObject.add(imageLoadButton);
		
		// delete button
		deleteButton.setBounds(1146, 90, 100, 100);
		deleteButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
		deleteButton.setContentAreaFilled(false);
		deleteButton.setFocusPainted(false);
		deleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				deleteButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // �̺�Ʈ ó���� createBlock�̶� �Ȱ��� �����Ǿ����� ������ rule�� �߰� �ؾ���
			}
				
			@Override
			public void mouseExited(MouseEvent e) {
				// button.setIcon���� �����ܺ���
				deleteButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
				
			@Override
			public void mousePressed(MouseEvent e) {
				deleteButton.setVisible(true);
			}
		});
		systemObject.add(deleteButton);
				
		createBlockField.setBounds(0, 20, 1280, 700); // ũ�� ���� �������� �ƴϿ���? �׽�Ʈ ����?
		createBlockField.setVisible(false);
		createBlockField.setCursor(new Cursor(Cursor.TEXT_CURSOR));
		createBlockField.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				System.out.println(e.getX() + " " + e.getY());
				Block b = new Block(new BlockInformation(e.getX(),e.getY(),50,50,""));
				blockObject.add(b);
				GIM.GameObject.add(b,GIM.blockPriority);
				createBlockField.setVisible(false);
			}
		});
		systemObject.add(0,createBlockField);
		
		// rule button
				ruleButton.setBounds(910, 580, 175, 115);
				ruleButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
				ruleButton.setContentAreaFilled(false); // ä������
				ruleButton.setFocusPainted(false);
				ruleButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseEntered(MouseEvent e) {
						// button.setIcon���� �����ܺ���
						ruleButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						// button.setIcon���� �����ܺ���
						ruleButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}

					@Override
					public void mousePressed(MouseEvent e) {
						ruleButton.setVisible(true);
					}
				});
				systemObject.add(ruleButton);
		
		// exit button
				exitButton.setBounds(1220, 8, 29, 29);
				exitButton.setBorderPainted(true); // ��ư ��ġ �׽�Ʈ ������ true�� ����
				exitButton.setContentAreaFilled(false); // ä������
				exitButton.setFocusPainted(false);
				exitButton.addMouseListener(new MouseAdapter() {
					// ������ ������ �̺�Ʈ ó������� ��
					@Override
					public void mouseEntered(MouseEvent e) {
						exitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
					}

					@Override
					public void mouseExited(MouseEvent e) {
						exitButton.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
					}

					@Override
					public void mousePressed(MouseEvent e) {
						exitButton.setVisible(true);
					}
				});
				systemObject.add(exitButton);		
//		blockObject.add(new Block(new BlockInformation(400,300,300,300,ImageManager.testButtonImage)));
//		blockObject.add(new Block(new BlockInformation(500,100,300,300,ImageManager.testButtonImage)));		
		

		for(Component b : systemObject){
			GIM.GameObject.add(b);	
		}
		GIM.blockPriority = systemObject.size();
		for(Block b : blockObject){
			GIM.GameObject.add(b);	
		}

		GIM.GameObject.repaint();
		
//		introMusic = new Audio("testMusic.mp3", true); �ڲ� �������� �ּ�ó�� �س���
//		introMusic.start();	
	}
	@Override
	public void removeScene()
	{
		GIM.removeGIM();
		for(Component c : systemObject){
			GIM.GameObject.remove(c);			
		}
		for(Block b : blockObject){
			GIM.GameObject.remove(b);			
		}
//		introMusic.close();
	}
	public void screenDraw(Graphics g) {
		g.drawImage(background, 0, 0, 1280, 720, null);
		g.drawImage(image_subFrame, 890, 35, 375, 670, null);
		g.drawImage(image_mainFrame, 15, 35, 860, 670, null);
		
		GIM.GameObject.paintComponents(g);
		GIM.GameObject.repaint();
	}
}
