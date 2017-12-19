package AnalogInProject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class SaveLoadManager {
	static boolean saveMap(ArrayList<Block> blockList, String dirPath, String rule, String gameName) {
		ObjectOutputStream oos = null;
		try {
			File forder = new File(dirPath + "\\\\" + gameName);
			String savePath = dirPath +"\\\\"+ gameName + "\\\\";
			if (!forder.exists())
				forder.mkdirs();
			oos = new ObjectOutputStream(new FileOutputStream(savePath + "map.ain"));
			ArrayList<BlockInformation> blockInfo = new ArrayList<BlockInformation>();
			ArrayList<String> imageName = new ArrayList<String>();

			for (Block b : blockList) {
				blockInfo.add(b.blockInfo);
				String beforeFilePath = b.blockInfo.imagePath; // �ű� ��� ���
				String[] fileName = beforeFilePath.split("\\\\");

				imageName.add(fileName[fileName.length - 1]);
				Path file = Paths.get(beforeFilePath);
				Path afterFile = Paths.get(savePath);
				try{
					Files.copy(file, afterFile.resolve(file.getFileName()));					
				}catch(FileAlreadyExistsException e){}
			}
			// �����̸� -> ���Ӽ��� -> ���������̼� -> �̹������
			oos.reset();
			oos.writeObject(gameName);
			oos.reset();
			oos.writeObject(rule);
			oos.reset();
			oos.writeObject(blockInfo);
			oos.reset();
			oos.writeObject(imageName);
			oos.close();
			return true;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(oos!=null)
				try {
					oos.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return false;
	}
	static ArrayList<BlockInformation> loadMap(String path) {
		ObjectInputStream ois = null;
		try {
			String gameName;
			String rule;
			ArrayList<String> imagePathList = new ArrayList<String>();
			ArrayList<BlockInformation> blockInformation = null;
			int index = path.lastIndexOf("\\");
			Path p = Paths.get(path);
			String forderPath = p.getParent().toString() + "\\";
			
			ois = new ObjectInputStream(new FileInputStream(path));			
			
			gameName = (String)ois.readObject();
			rule = (String)ois.readObject();
			blockInformation = (ArrayList<BlockInformation>)ois.readObject();
			imagePathList = (ArrayList<String>)ois.readObject();
			for(int i=0;i!=blockInformation.size();i++){
				blockInformation.get(i).imagePath = forderPath+imagePathList.get(i);
			}
			GIM.dir = forderPath;
			GIM.loadedBlockInfo = blockInformation;
			GIM.gmaeName = gameName;
			GIM.rule = rule;
			GIM.imageName = imagePathList;
			return blockInformation;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if(ois!=null)
				try {
					ois.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		return null;
	}
	public static String getLoadDirectory() {
		JFileChooser chooser = new JFileChooser();
		if(cache==null)
			chooser.setCurrentDirectory(new File("C:\\")); // ��ó����θ� C�� ��
		else	
			chooser.setCurrentDirectory(new File(cache)); // ��ó����θ� C�� ��
		
		FileNameExtensionFilter filter = new FileNameExtensionFilter("ain File", "ain");
		chooser.setFileFilter(filter);
		int returnVal = chooser.showOpenDialog(GIM.GameObject);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			String str = chooser.getSelectedFile().getAbsolutePath();
			String returnValue = str.replace("\\", "\\\\");
			System.out.println("path" + returnValue);
			cache = returnValue;
			return returnValue;
		}
		return null;
	}
	static String cache = null;
	static String getSaveDirectory() {
		File savefile;
		String savepathname;
		JFileChooser chooser;
		if(cache==null)
			chooser = new JFileChooser(cache);// ��ü ����
		else
			chooser = new JFileChooser(System.getProperty("user.home")+"/Desktop");// ��ü ����
		chooser.setFileSelectionMode(chooser.DIRECTORIES_ONLY); // ���丮�� ���ð���

		int re = chooser.showSaveDialog(null);
		if (re == JFileChooser.APPROVE_OPTION) { // ���丮�� ����������
			savefile = chooser.getSelectedFile(); // ���õ� ���丮 �����ϰ�
			savepathname = savefile.getAbsolutePath(); // ���丮���+�����̸�
			String returnValue = savepathname.replace("\\", "\\\\");
			cache = returnValue;
			return returnValue;
		} else {
			JOptionPane.showMessageDialog(null, "��θ� ���������ʾҽ��ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
			return null;
		}
	}
}
