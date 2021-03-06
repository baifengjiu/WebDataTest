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

public class UploadFileFrame {
	private JFrame frame;
	private JTextField _Upload_Url;
	private JTextField _File_Path;

	/**
	 * Create the application.
	 * @param _Get_Message_Content 
	 */
	public UploadFileFrame(JTextArea _Get_Message_Content) {
		initialize(_Get_Message_Content);
	}

	/**
	 * Initialize the contents of the frame.
	 * @param _Get_Message_Content 
	 */
	public void initialize(final JTextArea _Get_Message_Content) {
		frame = new JFrame("文件上传");
		frame.setVisible(true);
		Util.setMainFrame(frame,598,488);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		final JPanel _Fileter_Panel = new JPanel();
		_Fileter_Panel.setBounds(6, 6, 570, 318);
		frame.getContentPane().add(_Fileter_Panel);
		
		final JFileChooser fd = new JFileChooser();
		fd.setBounds(38, 5, 494, 268);
		fd.setControlButtonsAreShown(false);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
		        "图片文件", "jpg", "gif","png","jpeg");
		fd.setFileFilter(filter);
		
		JButton _Button = new JButton("确定");
		_Button.setBounds(404, 273, 90, 30);
		_Button.addActionListener (new ActionListener ()
        {
            @Override
            public void actionPerformed ( ActionEvent e )
            {
                	System.out.println("You chose to open this file: " + fd.getSelectedFile().getName());
        			String path = fd.getSelectedFile().getAbsolutePath();
        			System.out.println("path: "+path);
        			_File_Path.setText(path);
            }
        });
		
		_Fileter_Panel.setLayout(null);
		_Fileter_Panel.add(fd);
		_Fileter_Panel.add(_Button);
		
		
		JLabel label = new JLabel("上传地址：");
		label.setBounds(28, 327, 60, 23);
		frame.getContentPane().add(label);
		
		_Upload_Url = new JTextField();
		_Upload_Url.setColumns(10);
		_Upload_Url.setBounds(88, 323, 475, 30);
		frame.getContentPane().add(_Upload_Url);
		
		JLabel label_1 = new JLabel("选中的文件：");
		label_1.setBounds(16, 364, 72, 23);
		frame.getContentPane().add(label_1);
		
		_File_Path = new JTextField();
		_File_Path.setColumns(10);
		_File_Path.setBounds(87, 360, 476, 30);
		frame.getContentPane().add(_File_Path);
		
		JButton button = new JButton("上传");
		button.setBounds(251, 402, 93, 30);
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				String url = _Upload_Url.getText();
				String[] uploadFilePaths = new String[1];
				uploadFilePaths[0] = _File_Path.getText();
				
				String rslt = SendRequest.httpUpload(frame,url,uploadFilePaths);

				_Get_Message_Content.append("================send_message_content===============\n");
				_Get_Message_Content.append("上传文件：\n");
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
