package fx.zy.ns;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.FileNameExtensionFilter;

public class DownloadFileFrame {
	private JFrame frame;
	private JTextField _Download_Url;
	private JTextField _File_Path;

	/**
	 * Create the application.
	 * @param _Get_Message_Content 
	 */
	public DownloadFileFrame(JTextArea _Get_Message_Content) {
		initialize(_Get_Message_Content);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param _Get_Message_Content 
	 */
	public void initialize(final JTextArea _Get_Message_Content) {
		frame = new JFrame("文件下载");
		frame.setVisible(true);
		Util.setMainFrame(frame,598,160);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("文件下载地址：");
		label.setBounds(10, 13, 84, 23);
		frame.getContentPane().add(label);
		
		_Download_Url = new JTextField();
		_Download_Url.setColumns(10);
		_Download_Url.setBounds(92, 10, 489, 30);
		frame.getContentPane().add(_Download_Url);
		
		JLabel label_1 = new JLabel("文件保存地址：");
		label_1.setBounds(10, 51, 84, 23);
		frame.getContentPane().add(label_1);
		
		_File_Path = new JTextField();
		_File_Path.setColumns(10);
		_File_Path.setBounds(92, 47, 489, 30);
		frame.getContentPane().add(_File_Path);
		
		JButton button = new JButton("下载");
		button.setBounds(245, 89, 93, 30);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				String url = _Download_Url.getText();
				String uploadFilePaths = _File_Path.getText();
				
				String rslt = SendRequest.downloadFile(frame,url,uploadFilePaths);

				_Get_Message_Content.append("================send_message_content===============\n");
				_Get_Message_Content.append("下载文件：\n");
				_Get_Message_Content.append("url："+url+"\n");
				_Get_Message_Content.append("response:\n");
				_Get_Message_Content.append(Util.formatJson(rslt)+"\n");
				_Get_Message_Content.append("===================================================\n");
				_Get_Message_Content.append("\n");
				
						
			}
		});
		frame.getContentPane().add(button);
	}
}
