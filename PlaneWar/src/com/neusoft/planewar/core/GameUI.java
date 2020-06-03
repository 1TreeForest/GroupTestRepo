package com.neusoft.planewar.core;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.neusoft.planewar.client.PlaneWarClient;
import com.neusoft.planewar.constant.Constant;

public class GameUI extends JFrame implements ActionListener {
	private JPanel imagePanel;

	/* 三个按钮 */
	JButton begin = new JButton("开始");
	JButton exit = new JButton("退出");
	JButton rule = new JButton("规则");

	public GameUI(String name) {
		super(name);
		JLabel imgLabel = new JLabel(new ImageIcon("src/com/neusoft/planewar/img/background.png"));// 将图片放在标签里。
		this.getLayeredPane().add(imgLabel, new Integer(Integer.MIN_VALUE));// 将背景标签添加到JFrame的LayeredPane面板里（分层）
		imgLabel.setBounds(0, 0, 421, 646);// 设置背景标签的位置
		imagePanel = (JPanel) this.getContentPane();// 将Frame的内容窗格放到Panel上
		imagePanel.setOpaque(false);// 将内容面板设为透明。这样LayeredPane面板中的背景才能显示出来。
		imagePanel.setLayout(null); // 设置布局管理器空

		/* 设置按钮位置 */
		begin.setBounds(160, 240, 100, 40);
		rule.setBounds(76, 340, 60, 20);
		exit.setBounds(275, 340, 60, 20);

		/* 添加按钮到JFrame里 */
		this.add(begin);
		this.add(rule);
		this.add(exit);

		/* 给按钮添加监听器 */
		begin.addActionListener(this);
		rule.addActionListener(this);
		exit.addActionListener(this);

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 * 监听器的实现代码
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == exit) {
			System.exit(0); // 退出的监听事件
		}
		if (e.getSource() == begin) {
			this.dispose();
			PlaneWarClient s = new PlaneWarClient();// 点“开始”后，出现一个新界面，作为游戏界面
			s.setTitle("飞机大战");// 界面的标题
			s.setSize(Constant.GAME_HEIGHT, Constant.GAME_WIDTH);// 界面初始大小
			s.setLocationRelativeTo(null);// 确定窗口位置，null时在屏幕中间
			s.setVisible(true); // 令组件可见
			s.launchFrame(); // 启动游戏方法
		}

		if (e.getSource() == rule) {
			RuleFrame r = new RuleFrame(); // 点击“规则”后，出现一个新界面
			r.setTitle("游戏规则"); // 界面的标题
			r.setSize(224,420); // 界面初始大小
			r.setLocationRelativeTo(null); // 确定窗口位置，null时在屏幕中间
			r.setVisible(true); // 令组件可见
			
		}
	}

}
