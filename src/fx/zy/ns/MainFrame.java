package fx.zy.ns;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ComboBoxEditor;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.SystemColor;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

public class MainFrame implements ActionListener,ItemListener{
	private JFrame frm;
	private final JPanel _Url_box = new JPanel();
	private JButton _Url_Submit;
	private JTextField _Url_Text1;
	private JTextField _Url_Text2;
	public static JTextArea _Get_Message_Content;
	private JPanel _RightPanel;
	private JTextField _Par_Name_Text;
	private JTextArea _Par_Value_Text;
	private JPanel _Send_Message_Box;
	private JPanel _Label_Box;
	private JLabel _Label_1;
	private String page;
	private JComboBox<String> comboBox;
	private String type = "GET";
	private JPanel _Type_Panel;
	private JPanel _Content_Panel;
	private JPanel panel;
	private JTextArea _Content;
	private JLabel _Post_Content;

	/**
	 * Launch the application.
	 */
	public void init() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame window = new MainFrame();
					window.frm.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frm = new JFrame();
		Util.setMainFrame(frm,1085, 638);
		//frm.setBounds(100,100,1085, 638);
		frm.setTitle("Data Test");
		frm.getContentPane().setBackground(Color.WHITE);
		frm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frm.getContentPane().setLayout(null);
//		_Url_box.setBackground(Color.ORANGE);
		_Url_box.setBounds(2, 2, 1064, 45);
		frm.getContentPane().add(_Url_box);
		_Url_box.setLayout(null);
		
		JLabel _Url_Label1 = new JLabel("url:");
		_Url_Label1.setFont(new Font("SansSerif", Font.PLAIN, 16));
		_Url_Label1.setBounds(6, 12, 24, 15);
		_Url_box.add(_Url_Label1);
		
		_Url_Text1 = new JTextField();
		_Url_Text1.setBounds(31, 8, 320, 25);
		_Url_Text1.setText("http://");
		_Url_box.add(_Url_Text1);
		_Url_Text1.setColumns(50);
		
		JLabel _Url_Label2 = new JLabel("/");
		_Url_Label2.setFont(new Font("SansSerif", Font.PLAIN, 20));
		_Url_Label2.setBounds(355, 13, 6, 15);
		_Url_box.add(_Url_Label2);
		
		_Url_Text2 = new JTextField(".action");
		_Url_Text2.setBounds(368, 8, 232, 25);
		_Url_box.add(_Url_Text2);
		_Url_Text2.setColumns(20);
		
//		JLabel _Url_Label3 = new JLabel(".action");
//		_Url_Label3.setFont(new Font("SansSerif", Font.PLAIN, 16));
//		_Url_Label3.setBounds(494, 12, 53, 15);
//		_Url_box.add(_Url_Label3);
		
		_Url_Submit = new JButton("提交");
		_Url_Submit.setBounds(948, 9, 70, 25);
		_Url_Submit.addActionListener(this);
		_Url_box.add(_Url_Submit);
		
		JButton _open_uplode_window = new JButton("上传图片");
		_open_uplode_window.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				UploadFileFrame window = new UploadFileFrame(_Get_Message_Content);
			}
		});
		_open_uplode_window.setBounds(663, 9, 97, 25);
		_Url_box.add(_open_uplode_window);
		
		JButton button_4 = new JButton("下载文件");
		button_4.setVisible(true);
//		button_4.setVisible(false);
		button_4.setBounds(783, 9, 97, 25);
		button_4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				DownloadFileFrame window = new DownloadFileFrame(_Get_Message_Content);
			}
		});
		_Url_box.add(button_4);
		
		
		JPanel _Get_Message_Box = new JPanel();
//		_Get_Message_Box.setBackground(Color.ORANGE);
		_Get_Message_Box.setBounds(2, 50, 465, 547);
		frm.getContentPane().add(_Get_Message_Box);
		_Get_Message_Box.setLayout(new BorderLayout(0, 0));
		
		_Get_Message_Content = new JTextArea(25,39);
		_Get_Message_Content.setLineWrap(true);
		_Get_Message_Content.setSize(new Dimension(465, 547));
		_Get_Message_Content.setEditable(false);
//		_Get_Message_Content.setForeground(Color.BLACK);
		_Get_Message_Content.setToolTipText("show the reponse message content");
		
		JScrollPane scrollPane = new JScrollPane(_Get_Message_Content);
		scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//scrollPane.setSize(600, 400);
		_Get_Message_Box.add(scrollPane);
		
		JPanel _Button_Box = new JPanel();
		_Button_Box.setBounds(469, 126, 119, 110);
		frm.getContentPane().add(_Button_Box);
		//_Button_Box.setBackground(Color.ORANGE);
		_Button_Box.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton button = new JButton("新增键值对");
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				addPar();
			}
		});		
		_Button_Box.add(button);
		
		JButton button_1 = new JButton("删除所有键值对");
		_Button_Box.add(button_1);
		button_1.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				delAllPar();
			}
		});	
		
		JButton button_2 = new JButton("删除当前键值对");
		_Button_Box.add(button_2);
		button_2.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub				
				delPar();
			}
		});	
		
		_Label_Box = new JPanel();
		//_Label_Box.setBackground(Color.ORANGE);
		_Label_Box.setBounds(469, 238, 119, 359);
		frm.getContentPane().add(_Label_Box);	
		
		_RightPanel = new JPanel();
		_RightPanel.setBounds(590, 50, 476, 547);
		frm.getContentPane().add(_RightPanel);
		
		_Type_Panel = new JPanel();
		_Type_Panel.setBounds(469, 50, 119, 73);
		frm.getContentPane().add(_Type_Panel);
		_Type_Panel.setLayout(null);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(6, 6, 107, 28);
		comboBox.addItem("GET");
		comboBox.addItem("POST");
		comboBox.setSelectedItem("GET");
		comboBox.addItemListener(this);
		_Type_Panel.add(comboBox);		

		panel = new JPanel();
		panel.setBounds(6, 5, 464, 536);
		_RightPanel.add(panel);
		
		_Post_Content = new JLabel("POST提交内容");
		_Post_Content.setSize(new Dimension(10, 7));
		_Post_Content.setPreferredSize(new Dimension(90, 25));
		_Post_Content.setOpaque(true);
		_Post_Content.setVisible(false);
		_Post_Content.setName("0");
		_Post_Content.setHorizontalTextPosition(SwingConstants.CENTER);
		_Post_Content.setHorizontalAlignment(SwingConstants.CENTER);
		_Post_Content.setForeground(Color.BLACK);
		_Post_Content.setFont(new Font("宋体", Font.PLAIN, 16));
		_Post_Content.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		_Post_Content.setBackground(new Color(245, 245, 220));
		_Post_Content.setAlignmentY(1.0f);
		_Post_Content.setAlignmentX(2.0f);
		_Post_Content.setBounds(6, 42, 107, 25);
		_Post_Content.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		_Post_Content.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				//JLabel lb = (JLabel) e.getSource();
				//lb.setBackground(Color.PINK);
				_Content_Panel.setVisible(true);
				for(Component c:_Label_Box.getComponents()){
					if(c instanceof JLabel){
						c.setBackground(Color.LIGHT_GRAY);
					}
				}
				panel.setVisible(false);
			}
		});
		_Type_Panel.add(_Post_Content);
						
		_Content_Panel = new JPanel();
		_RightPanel.add(_Content_Panel, "name_173066367451837");
		_Content_Panel.setVisible(false);
		_Content_Panel.setLayout(null);
		
		JScrollPane _Content_Scroll = new JScrollPane();
		_Content_Scroll.setBounds(6, 46, 470, 500);
		_Content_Scroll.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		_Content_Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		_Content_Panel.add(_Content_Scroll);
		
		_Content = new JTextArea("send_message_content");
		_Content.setLineWrap(true);
		_Content_Scroll.setViewportView(_Content);
		
		JLabel label = new JLabel("请输入要提交的内容：");
		label.setFont(new Font("SansSerif", Font.PLAIN, 16));
		label.setBounds(6, 16, 187, 25);
		_Content_Panel.add(label);
		
		addPar();
	}
	
	private void addPar() {
		_Content_Panel.setVisible(false);
		int lenght = panel.getComponents().length;
		int[] nums = new int[lenght];
		int k=0;
		for(Component c:panel.getComponents()){
			if(c instanceof JPanel){
				String num = c.getName().substring(c.getName().length()-1);
				nums[k]=Integer.parseInt(num);
				k++;
				c.setVisible(false);
			}
		}

		int i=0;
		if(nums.length>0){
			Arrays.sort(nums);
			for(int j=0;j<nums.length;j++){
				if(i==nums[j]){
					i++;
				}
			}
		}
		
		page = i+"";
		addLabel(i);
		_RightPanel.setLayout(new CardLayout(0, 0));
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		_Send_Message_Box = new JPanel();
		panel.add(_Send_Message_Box);
		_Send_Message_Box.setName("panel_"+i);
//		_Send_Message_Box.setBackground(Color.ORANGE);
		
		_Par_Name_Text = new JTextField();
		_Par_Name_Text.setText("par_name");
		_Par_Name_Text.setName("par_name_"+i);
		_Par_Name_Text.setColumns(20);
		
		JLabel lblKey = new JLabel("Key:");
		JLabel lblValue = new JLabel("Value:");
		
		JLabel lblParams = new JLabel("params"+i);
		lblParams.setFont(new Font("SansSerif", Font.PLAIN, 16));
		
		_Par_Value_Text = new JTextArea(18,20);
		_Send_Message_Box.add(_Par_Value_Text);
		_Par_Value_Text.setLineWrap(true);
		_Par_Value_Text.setName("par_value_"+i);
		_Par_Value_Text.setToolTipText("show the request message content");
		_Par_Value_Text.setText("par_value");
		
		JScrollPane scrollPane = new JScrollPane(_Par_Value_Text);
		scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER); 
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		GroupLayout gl__Send_Message_Box = new GroupLayout(_Send_Message_Box);
		gl__Send_Message_Box.setHorizontalGroup(
			gl__Send_Message_Box.createParallelGroup(Alignment.LEADING)
				.addGroup(gl__Send_Message_Box.createSequentialGroup()
					.addGap(6)
					.addGroup(gl__Send_Message_Box.createParallelGroup(Alignment.LEADING)
						.addComponent(lblKey, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl__Send_Message_Box.createSequentialGroup()
							.addComponent(_Par_Name_Text, GroupLayout.PREFERRED_SIZE, 206, GroupLayout.PREFERRED_SIZE)
							.addGap(96)
							.addComponent(lblParams))
						.addComponent(lblValue, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE)
						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 464, GroupLayout.PREFERRED_SIZE)))
		);
		gl__Send_Message_Box.setVerticalGroup(
			gl__Send_Message_Box.createParallelGroup(Alignment.LEADING)
				.addGroup(gl__Send_Message_Box.createSequentialGroup()
					.addGap(6)
					.addComponent(lblKey)
					.addGap(6)
					.addGroup(gl__Send_Message_Box.createParallelGroup(Alignment.LEADING)
						.addComponent(_Par_Name_Text, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblParams))
					.addGap(6)
					.addComponent(lblValue)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 462, GroupLayout.PREFERRED_SIZE))
		);
		_Send_Message_Box.setLayout(gl__Send_Message_Box);

	}
	
	private void addLabel(int i) {
		for(Component c:_Label_Box.getComponents()){
			if(c instanceof JLabel){						
				c.setBackground(Color.LIGHT_GRAY);
			}
		}
		_Label_1 = new JLabel("params"+i);
		_Label_1.setHorizontalTextPosition(SwingConstants.CENTER);
		_Label_1.setHorizontalAlignment(SwingConstants.CENTER);
		_Label_1.setPreferredSize(new Dimension(90, 25));
		_Label_1.setSize(new Dimension(10, 7));
		_Label_1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		_Label_1.setAlignmentY(1.0f);
		_Label_1.setAlignmentX(2.0f);
		_Label_1.setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
		_Label_1.setOpaque(true);
		_Label_Box.add(_Label_1);
		_Label_1.setName(""+i);
		_Label_1.setForeground(Color.BLACK);
		_Label_1.setBackground(Color.PINK);
		_Label_1.setFont(new Font("宋体", Font.PLAIN, 16));
		_Label_1.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if(type.equals("POST")){
					_Content_Panel.setVisible(false);
				}
				JLabel lb = (JLabel) e.getSource();
				String lbName= lb.getName();
				
				for(Component c:_Label_Box.getComponents()){
					if(c instanceof JLabel){						
						if (c.getName().equals(lbName)) {
							c.setBackground(Color.PINK);
						}else{
							c.setBackground(Color.LIGHT_GRAY);
						}
					}
				}
				
				for(Component c:panel.getComponents()){
					if(c instanceof JPanel){						
						if (c.getName().contains(lbName)) {
							page = lbName;
							c.setVisible(true);
						}else {
							c.setVisible(false);
						}
					}
				}				
			}
		});
	}
	
	private void delPar() {
		// TODO Auto-generated method stub
		int count = 0;
		for(Component c:panel.getComponents()){			
			if(c instanceof JPanel){
				count++;
			}
		}
		
		for(Component c:panel.getComponents()){
			if (count<=1) {
				return;
			}			
			if(c instanceof JPanel){
				count++;
				if (c.getName().contains(page)) {
					panel.remove(c);
					panel.repaint();
					page = c.getName().substring(c.getName().length()-1);
				}
			}
		}
		
		for(Component c:_Label_Box.getComponents()){
			if(c instanceof JLabel){						
				if (c.getName().contains(page)) {
					_Label_Box.remove(c);
					_Label_Box.repaint();
				}
			}
		}
		
		int i=0;
		for(Component c:panel.getComponents()){
			if(c instanceof JPanel){						
				if (i==0) {
					page = c.getName().substring(c.getName().length()-1);
					c.setVisible(true);
				}else {
					c.setVisible(false);
				}
				i++;
			}
		}
		i=0;
		for(Component c:_Label_Box.getComponents()){
			if(c instanceof JLabel){						
				if (i==0) {
					c.setBackground(Color.PINK);
				}else{
					c.setBackground(Color.LIGHT_GRAY);
				}
			}
			i++;
		}
	}
	
	private void delAllPar() {
		// TODO Auto-generated method stub
		for(Component c:panel.getComponents()){
			if(c instanceof JPanel){
				panel.remove(c);
				panel.repaint();
			}
		}
		
		for(Component c:_Label_Box.getComponents()){
			if(c instanceof JLabel){
				_Label_Box.remove(c);
				_Label_Box.repaint();
			}
		}
		addPar();
	}
	@Override
	public void itemStateChanged(ItemEvent e){
		if(e.getStateChange() == ItemEvent.SELECTED){
			//如果选中了一个 
			type = (String) comboBox.getSelectedItem();
			if (type.equals("POST")) {				
				delAllPar();
				_Content_Panel.setVisible(true);
				_Post_Content.setVisible(true);
			}
			if(type.equals("GET")){
				addPar();
				delAllPar();
				_Content_Panel.setVisible(false);
				_Post_Content.setVisible(false);
			}		
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource().equals(_Url_Submit)){
			// TODO Auto-generated method stub
			try {			
				String url = _Url_Text1.getText()+"/"+_Url_Text2.getText();
				List<String> key = new ArrayList<>();
				List<String> value = new ArrayList<>();
				for(Component c:panel.getComponents()){
					if(c instanceof JPanel){
						//String name = c.getName();
						for (Component s:((JPanel) c).getComponents()) {
							if (s instanceof JTextField) {
								key.add(((JTextField) s).getText());
		//						System.out.println(((JTextField) s).getText());
							}
							if (s instanceof JScrollPane) {
								JTextArea textarea = (JTextArea)(((JScrollPane) s).getViewport().getView());
								String data = Util.replaceBlank(textarea.getText());
								data = URLEncoder.encode(data,"UTF-8");
								value.add(data);		
		//						System.out.println(((JTextArea) s).getText());
							}
						}
					}
				}
				String rslt ="";
				if(type.equals("GET")){
					rslt = SendRequest.sendByGet(frm,url, key, value);
				}else if(type.equals("POST")){
					String content = _Content.getText();
					content = URLEncoder.encode(content,"UTF-8");
					rslt = SendRequest.sendByPost(frm,url, key, value,content);
				}else{
					Util.getDialog(frm, "未知传输类型");
				}
		
				System.out.println(url);
				
				rslt = SendRequest.sendByGet(frm,url, key, value);
				//String rslt ="这是本地测试";
				_Get_Message_Content.append("================send_message_content===============\n");
				_Get_Message_Content.append("type："+type+"\n");
				_Get_Message_Content.append("url："+url+"\n");
				_Get_Message_Content.append("action："+_Url_Text2.getText()+"\n");
				_Get_Message_Content.append("response:\n");
				_Get_Message_Content.append(Util.formatJson(rslt)+"\n");
				_Get_Message_Content.append("===================================================\n");
				_Get_Message_Content.append("\n");
			} catch (UnsupportedEncodingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
