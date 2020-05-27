package com.neusoft.planewar.core;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.util.Random;

import com.neusoft.planewar.client.PlaneWarClient;
import com.neusoft.planewar.constant.Constant;
import com.neusoft.planewar.util.GameUtil;
import com.neusoft.planewar.util.ImageUtil;
import com.neusoft.planewar.util.MusicUtil;

public class EnemyPlane extends Plane {
	public int type;// 绫诲瀷
	public int exp;// 缁忛獙

	/**
	 * 鏃犲弬鏋勯��
	 */
	public EnemyPlane() {
		super();
	}

	/**
	 * 甯﹀弬鏋勯��
	 */
	public EnemyPlane(int x, int y, String imageName) {
		super(x, y, imageName);
		this.speed = 0.2;
	}

	public EnemyPlane(PlaneWarClient pwc, int x, int y, int type, boolean good) {
		this.pwc = pwc;
		this.x = x;
		this.y = y;
		if (type == 100) {
			img = ImageUtil.images.get("enemyPlane_boss_0"+(int)(Math.random()*5+1));
			this.width = img.getWidth(null);
			this.height = img.getHeight(null);
			this.blood = Constant.ENEMYPLANE_BOSS_MAX_BOOLD;
		} else {
			this.width = images[type * 4 - 4].getWidth(null);
			this.height = images[type * 4 - 4].getHeight(null);
			this.blood = Constant.ENEMYPLANE_MAX_BOOLD;
		}
		this.speed = 0.3 + 0.1 * type;
		this.type = type;
		this.good = good;
		live = true;
	}

	/**
	 * 鍙戝瓙寮�
	 */
	@Override
	public void fire() {
		if (type == 100) {
			int num = (int) (Math.random()*36)+24;
			for (int i = 1; i <= num; i++) {
				Missile missile = new Missile(pwc, this.x, this.y, "enemyPlane_missile_0" + type, type+new Random().nextInt(3), good);
				int r = (int) (Math.sqrt(width * width + height * height) / 2);
				int theta = 360 * i / num;
				missile.setTheta(theta);
				missile.x = (int) (missile.x + (width / 2 + r * Math.sin(Math.toRadians(theta)) - missile.width / 2));
				missile.y = (int) (missile.y
						- ((r * Math.cos(Math.toRadians(theta)) - height / 2 + missile.height / 2)));
				pwc.missiles.add(missile);
			}
		} else {
			Missile missile = new Missile(pwc, this.x, this.y, "enemyPlane_missile_0" + type, type, good);
			missile.x += (this.width - missile.width) / 2;
			missile.y += height;
			pwc.missiles.add(missile);
		}
	}

	Point center = new Point(Constant.GAME_WIDTH / 2, Constant.GAME_HEIGHT / 2);
	double theta;
	int r = 100;
	public static Image images[] = new Image[24];
	static {
		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 4; j++) {
				images[4 * i + j] = ImageUtil.images.get("enemyPlane_0" + (i + 1) + "_0" + (j + 1));
			}
		}
	}

	@Override
	public String toString() {
		return "EnemyPlane [type=" + type + ", exp=" + exp + ", center=" + center + ", theta=" + theta + ", r=" + r
				+ "]";
	}

	@Override
	public void move() {
		
		switch (type) {
		case 100:// 水平平移
			if (x >= (Constant.GAME_WIDTH) || x <= 0 - width) {
				speed = -speed;
			}
			x += speed;
			break;
		case 1:// 竖直平移
			y += speed * 5;
			break;
		case 2:// 圆圈
			x=(int) (center.x+2*r*Math.cos(theta));
			y=(int) (center.y+2*r*Math.sin(theta));
		        theta+=speed/20;
		        break;
		case 3:// 正弦线
			x = (int) (center.x - width + (center.x - width) * Math.sin(theta));
			theta += speed / 10;
			y += speed * 10;
			break;
		case 4:// 余弦线
			x = (int) (center.x - width + (center.x - width) * Math.cos(theta));
			theta += speed / 10;
			y += speed * 10;
			break;
		case 5:// 双曲线
			x = (int) (center.x - width + 50 * 1 / Math.cos(theta));
			y = (int) (center.y - height + 50 * Math.sin(theta) / Math.cos(theta));
			theta += speed / 20;
			break;
		case 6:// 星形线
			x = (int) (center.x - width / 2 + 200 * Math.pow(Math.cos(theta), 3));
			y = (int) (center.y + 200 * Math.pow(Math.sin(theta), 3)) - 200;
			theta += speed / 20;
			break;
		case 7:// 心形线
			x = (int) (center.x + r * (2 * Math.cos(theta + Math.PI / 2) + Math.cos(2 * theta + Math.PI / 2)));
			y = (int) (center.y + r * (2 * Math.sin(theta + Math.PI / 2) + Math.sin(2 * theta + Math.PI / 2)));
			theta += speed;
			break;
		}
		if (type!=100&&random.nextInt(1000) > 995 && live) {
			fire();
		}
		if(type==100&&random.nextInt(1000)>980&&live){
			fire();
		}
	}

	static Random random = new Random();
	static int count[] = new int[6];

	@Override
	public void draw(Graphics g) {
		
		if(!Plane.flagPause) {

		if (blood <= 0 && live) {
			live = false;
			pwc.myPlane.score += 100 * type;
			//最高分更新
			pwc.myPlane.writeTopscore();
			/**
			 * boss死了就更新最后一次爆炸时间
			 */
			if(type == 100) {
				pwc.explodeTime = System.currentTimeMillis();
			}
			// 鐖嗙偢
			Explode ex = new Explode(pwc, x, y);
			ex.x += (width - ex.width) / 2;
			ex.y += (height - ex.height) / 2;
			pwc.explodes.add(ex);

			// 浜х敓閬撳叿
			Item item = new Item(pwc, x, y);
			item.x += (width - item.width) / 2;
			item.y += height;
			pwc.items.add(item);
			
			if(type==100){
				pwc.enemyPlanes.clear();
				pwc.missiles.clear();
				pwc.items.clear();
			}
		}
		if (type == 100 && live) {
			g.drawImage(img, x, y, null);
			drawBlood(g);// 鐢昏鏉�
		}
		if (type != 100 && count[type - 1] >= 4 * type) {
			count[type - 1] = 4 * (type - 1);
		}
		if (type != 100 && live) {
			g.drawImage(images[count[type - 1]], x, y, null);
			drawBlood(g);// 鐢昏鏉�
			count[type - 1]++;
		}
		move();
	}
	}

	/**
	 * 鐢昏鏉�
	 * 
	 * @param g
	 */
	private void drawBlood(Graphics g) {
		Image bloodImg = ImageUtil.images.get("blood");
		Image blood_blank = ImageUtil.images.get("blood_blank");
		int i = 0;
		for (; i < width / bloodImg.getWidth(null); i++) {
			g.drawImage(bloodImg, x + bloodImg.getWidth(null) * i, y + height, null);
		}
		if(type==100){
			int num = (int) (((double) (bloodImg.getWidth(null) * i) / (Constant.ENEMYPLANE_BOSS_MAX_BOOLD))
					* (Constant.ENEMYPLANE_BOSS_MAX_BOOLD - blood) / blood_blank.getWidth(null));
			for (int j = 0; j < num; j++) {
				g.drawImage(blood_blank, x + bloodImg.getWidth(null) * i - blood_blank.getWidth(null) * (j + 1), y + height,
						null);
			}
		}else{
			int num = (int) (((double) (bloodImg.getWidth(null) * i) / (Constant.ENEMYPLANE_MAX_BOOLD))
					* (Constant.ENEMYPLANE_MAX_BOOLD - blood) / blood_blank.getWidth(null));
			for (int j = 0; j < num; j++) {
				g.drawImage(blood_blank, x + bloodImg.getWidth(null) * i - blood_blank.getWidth(null) * (j + 1), y + height,
						null);
			}
		}
	}
}
