package com.neusoft.planewar.core;

import java.awt.Graphics;
import java.awt.Image;

import com.neusoft.planewar.client.PlaneWarClient;
import com.neusoft.planewar.util.ImageUtil;

public class Explode extends PlaneWarObject {

	public boolean live;
	static int count = 0;
	public static Image images[] = new Image[8];
	static {
		for (int i = 0; i < 8; i++) {
			images[i] = ImageUtil.images.get("explode_0" + (i + 1));
		}
	}

	public Explode() {
		super();
	}

	public Explode(PlaneWarClient pwc, int x, int y) {
		this.pwc = pwc;
		this.x = x;
		this.y = y;
		this.height = images[0].getHeight(null);
		this.width = images[0].getWidth(null);
		live = true;
	}

	@Override
	public void move() {

	}

	@Override
	public void draw(Graphics g) {
		if (count >= 8) {
			count = 0;
			live = false;
			pwc.explodes.remove(this);
		}
		if (live) {
			g.drawImage(images[count], x, y, null);
			count++;
		}
	}

}
