package com.neusoft.planewar.core;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.io.*;

import com.neusoft.planewar.client.PlaneWarClient;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.util.ImageUtil;
import com.neusoft.planewar.util.MusicUtil;

public class Plane extends PlaneWarObject {
	public double speed = 10;// 閫熷害
	public boolean left, up, right, down;
	public int blood;// 琛�閲�
	public int level;// 绛夌骇
	public int type;// 绛夌骇
	public int score = 0;// 绉垎
	public static boolean flagPause=false;
	public int topscore=getTopscore();	//历史最高分，通过读取文件获得

	/**
	 * 鏃犲弬鏋勯��
	 */
	public Plane() {
		super();
	}

	public Plane(PlaneWarClient pwc, boolean good) {
		this.fire = false;
		this.x = (Constant.GAME_WIDTH - width) / 2;
		this.y = Constant.GAME_HEIGHT - height;
		this.img = ImageUtil.images.get("myPlane_01_01");
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
		this.pwc = pwc;
		this.good = good;
		this.blood = Constant.MYPLANE_MAX_BOOLD;
		this.level = 1;
		this.type = 1;
	}

	/**
	 * 甯﹀弬鏋勯��
	 */

	public Plane(int x, int y, Image img, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.img = img;
		this.width = width;
		this.height = height;
	}

	public Plane(int x, int y, String imageName) {
		super();
		this.x = x;
		this.y = y;
		this.img = ImageUtil.images.get(imageName);
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}

	public Plane(int x, int y, Image img) {
		super();
		this.x = x;
		this.y = y;
		this.img = img;
		this.width = img.getWidth(null);
		this.height = img.getHeight(null);
	}

	/**
	 * 鍒ゆ柇鎴戞柟椋炴満鍑虹晫闂
	 */
	private void outOfBounds() {
		if (x <= 0-width/2)
			x = 0-width/2;
		if (x >= (Constant.GAME_WIDTH - width/2))
			x = Constant.GAME_WIDTH - width/2;
		if (y <= 0)
			y = 0;
		if (y >= (Constant.GAME_HEIGHT - height))
			y = Constant.GAME_HEIGHT - height;
	}

	/**
	 * 鏄惁寮�鐏�
	 */
	public boolean fire;

	/**
	 * 鎴戞柟椋炴満鍙戝瓙寮圭殑鏂规硶
	 */
	public void fire() {
		// pwc.musics.add(mu);
		new MusicUtil("fire3").start();
		Missile missile = new Missile(pwc, this.x, this.y, "myPlane_missile_0" + type + "_0" + level, good);
		missile.x += (this.width - missile.width) / 2;
		missile.y -= height;
		pwc.missiles.add(missile);
	}

	boolean superFire;

	/**
	 * 瓒呯骇瀛愬脊
	 */
	public void superFire() {
		if (superFireCount > 0) {
			int num = 24;
			for (int i = 1; i <= num; i++) {
				Missile missile = new Missile(pwc, this.x, this.y, "myPlane_missile_super", 6, good);
				int r = (int) (Math.sqrt(width * width + height * height) / 2);
				int theta = 360 * i / num;
				missile.setTheta(theta);
				missile.x = (int) (missile.x + (width / 2 + r * Math.sin(Math.toRadians(theta)) - missile.width / 2));
				missile.y = (int) (missile.y
						- ((r * Math.cos(Math.toRadians(theta)) - height / 2 + missile.height / 2)));
				pwc.missiles.add(missile);
			}
			superFireCount--;
		}
		superFire = false;
	}

	/**
	 * 鍒ゆ柇鏄惁瀛樻椿
	 */
	public boolean live = true;

	@Override
	public void move() {
		if(!Plane.flagPause) {
		if (left) {
			x -= speed;
		}
		if (right) {
			x += speed;
		}
		if (up) {
			y -= speed;
		}
		if (down) {
			y += speed;
		}
		outOfBounds();
		if (fire)
			fire();
		if (superFire)
			superFire();
	}
	}

	@Override
	public void draw(Graphics g) {
		img = ImageUtil.images.get("myPlane_0" + type + "_0" + level);
		if (blood <= 0 && live) {
			live = false;
			Explode ex = new Explode(pwc, x, y);
			ex.x += (width - ex.width) / 2;
			ex.y += (height - ex.height) / 2;
			pwc.explodes.add(ex);
			pwc.enemyPlanes.clear();
			pwc.missiles.clear();
			pwc.items.clear();

		}

		if (live) {
			g.drawImage(img, x, y, null);
			move();
		}
		drawBloodAndScore(g);
	}

	/**
	 * 鐢昏鏉″拰绉垎
	 * 
	 * @param g
	 */
	private void drawBloodAndScore(Graphics g) {
		Image bloodImg = ImageUtil.images.get("myBlood");
		Image blood_blank = ImageUtil.images.get("myBlood_blank");
		Image scoreImg = ImageUtil.images.get("score");
		Image topscoreImg = ImageUtil.images.get("topscore");
		int i = 0;
		g.drawImage(bloodImg, 10, 40, null);
		int num = (int) (((double) ((bloodImg.getWidth(null)) - 56) / (Constant.MYPLANE_MAX_BOOLD))
				* (Constant.MYPLANE_MAX_BOOLD - blood) / blood_blank.getWidth(null));
		for (int j = 0; j < num; j++) {
			g.drawImage(blood_blank, 10 + bloodImg.getWidth(null) - blood_blank.getWidth(null) * (j + 1), 40 + 14,
					null);
		}
		// 画积分
		g.drawImage(ImageUtil.images.get("score"), 10, 40 + bloodImg.getHeight(null) + 12, null);
		g.setFont(new Font("微软雅黑", Font.BOLD, 40));
		g.setColor(Color.WHITE);
		g.drawString(score + "", 10 + scoreImg.getWidth(null) + 10, 40 + bloodImg.getHeight(null) + 50);
		//画最高积分
		g.drawImage(ImageUtil.images.get("topscore"), bloodImg.getWidth(null)+30, topscoreImg.getHeight(null), null);
		g.setFont(new Font("微软雅黑", Font.BOLD, 30));
		g.setColor(Color.BLUE);
		g.drawString(getTopscore()+ "", scoreImg.getWidth(null) + 150, topscoreImg.getHeight(null)+75);
		
		
		if(flagPause) {
			g.setFont(new Font("微软雅黑",Font.BOLD,80));
			g.setColor(Color.RED);
			g.drawString("游戏暂停", 100, 400);
		}//游戏暂停
	}

	/**
	 * 澶ф嫑鍓╀綑娆℃暟
	 */
	public int superFireCount = 0;

	/**
	 * 鎸変笅閿洏鐨勬柟娉�
	 * 
	 * @param e
	 */
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = true;
			break;
		case KeyEvent.VK_S:
			down = true;
			break;
		case KeyEvent.VK_D:
			right = true;
			break;
		case KeyEvent.VK_W:
			up = true;
			break;
		case KeyEvent.VK_J:// 鍙戝瓙寮�
			superFire = false;
			fire = true;
			break;
		case KeyEvent.VK_SPACE:// 鍙戣秴绾у瓙寮�
			fire = false;
			superFire = true;
			break;
		case KeyEvent.VK_L:
			if(!flagPause) {
				
			flagPause =true;
			}else {
				
				flagPause=false;
			}
			break;
		}
	}

	/**
	 * 鏉惧紑閿洏鐨勬柟娉�
	 * 
	 * @param e
	 *            閿洏浜嬩欢
	 */
	public void keyReleased(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_A:
			left = false;
			break;
		case KeyEvent.VK_S:
			down = false;
			break;
		case KeyEvent.VK_D:
			right = false;
			break;
		case KeyEvent.VK_W:
			up = false;
			break;
		case KeyEvent.VK_J:// 鍙戝瓙寮�
			fire = false;
			break;
		}
	}
	/**
	 * 从文件中读取最高分
	 * @return
	 */
	public int getTopscore(){
		int top=readFile();
		return top;
	}

	/**
	 * 更改最高分
	 */
	public void writeTopscore(){
		if(score>topscore){
			writeFile();
		}
	}

	/**
	 * 读取文件内容操作
	 * @return
	 */
	public int readFile(){
		File file = new File("TopScore.txt");
		//如果文件不存在，文件输出流会自动创建文件
		if (!file.exists()) {
			try {
				file.createNewFile();
				String a = String.valueOf(0);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
				bw.write(a);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//读取文件
		int topscorenow=0;
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String topscorenow1 =br.readLine();
			if(topscorenow1!=null) {
				topscorenow = Integer.parseInt(topscorenow1);
			}
			br.close();

		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return topscorenow;
	}
	public void writeFile(){
		File file = new File("TopScore.txt");
		//如果文件不存在，文件输出流会自动创建文件
		if (!file.exists()) {
			try {
				file.createNewFile();
                String a = String.valueOf(0);
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
				bw.write(a);//向文件写入初始最高分
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		//写文件
		try {
            String a = String.valueOf(score);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)));
			bw.write(a);//向文件写入最高分
			bw.close();//关闭流

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
