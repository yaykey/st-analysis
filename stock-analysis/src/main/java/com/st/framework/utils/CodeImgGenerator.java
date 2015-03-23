/*
 * 文件名： CodeImageGenerator.java
 * 
 * 创建日期： 2008-2-26
 *
 * Copyright(C) 2008, by Along.
 *
 * 原始作者: <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 *
 */
package com.st.framework.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * 一个生成图片的JAVA类 用来做验证码
 * 
 * @author <a href="mailto:xiaozhi19820323@hotmail.com">xiaozhi</a>
 * 
 * @version $Revision: 1.1 $
 * 
 * @since 2008-2-26
 */
public class CodeImgGenerator {

	/** 默认图片的宽度 */
	private final static int DEF_WIDTH = 60;

	/** 默认图片的高度 */
	private final static int DEF_HEIGHT = 20;

	/** CODE代码 */
	private String code = "";

	/** 图片的宽度 */
	private int width;

	/** 图片的高度 */
	private int height;

	private BufferedImage image;

	public CodeImgGenerator() {
		this(DEF_WIDTH, DEF_HEIGHT);
	}

	public CodeImgGenerator(int width, int height) {
		this.width = width;
		this.height = height;

		generateCodeImage();
	}

	/**
	 * 给定范围获得随机颜色
	 * 
	 * @param begin
	 *            范围的开始数据
	 * @param end
	 * @return
	 */
	Color getRandColor(int begin, int end) {
		Random random = new Random();

		if (begin > 255)
			begin = 255;

		if (end > 255)
			end = 255;

		int r = begin + random.nextInt(end - begin);
		int g = begin + random.nextInt(end - begin);
		int b = begin + random.nextInt(end - begin);

		return new Color(r, g, b);

	}

	/**
	 * 生成一个验证码
	 * 
	 */
	private void generateCodeImage() {
		// 创建图片
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics g = image.getGraphics();

		// 设置背景颜色
		g.setColor(this.getRandColor(200, 255));
		g.fillRect(0, 0, width, height);
		g.drawRect(0, 0, width - 1, height - 1);
		// 设置字体
		g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

		// 创建随即对象
		Random random = new Random();
		g.setColor(this.getRandColor(160, 200));

		for (int i = 0; i < 155; i++) {
			int x = random.nextInt(width);
			int y = random.nextInt(height);
			int xl = random.nextInt(12);
			int yl = random.nextInt(12);

			g.drawLine(x, y, x + xl, y + yl);
		}

		// 画一个验证码
		for (int i = 0; i < 4; i++) {
			String rand = String.valueOf(random.nextInt(10));
			code += rand;

			// 调用函数出来的颜色相同，可能是因为种子太接近，所以只能直接生成
			g.setColor(new Color(20 + random.nextInt(110), 20 + random
					.nextInt(110), 20 + random.nextInt(110)));

			g.drawString(rand, 13 * i + 6, 16);
		}

		g.dispose();
	}

	/**
	 * 获得图片数据缓存对象
	 * 
	 * @return 返回图片数据缓存对象
	 */
	public BufferedImage getImage() {

		return image;
	}

	/**
	 * 获得code码
	 * 
	 * @return 返回code码
	 */
	public String getCode() {
		return code;
	}

}
