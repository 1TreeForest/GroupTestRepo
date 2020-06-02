package com.neusoft.planewar.core;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RuleFrame extends JFrame {
	public JPanel rulePanel; // 游戏面板
	private ImageIcon rule;

	public RuleFrame() {
		super();
		rule=new ImageIcon("com/neusoft/planewar/img/rule.png");
		JLabel imgLabel = new JLabel(rule);// 将背景图放在标签里。
		imgLabel.setBounds(0, 0, rule.getIconWidth(), rule.getIconHeight());// 设置背景标签的位置	
		rulePanel = (JPanel) this.getContentPane();// 将Frame的内容窗格放到Panel上
		rulePanel.setOpaque(false);// 将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。
		rulePanel.setLayout(null); // 不设置布局管理器，不需要
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));// 将背景标签添加到JFrame的LayeredPane面板里。
		this.setResizable(false);		
	}
}
