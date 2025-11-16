package com.br.ajf.imagesfx.img;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public final class ImageLoader
{
	public Image getImage(String filename)
	{
		Image img = Toolkit.getDefaultToolkit().getImage(this.getClass().getResource(filename));
		return img;
	}
	
	public BufferedImage getBufferedImage(String filename)
	{
		BufferedImage img = null;
		try
		{
			img = ImageIO.read(this.getClass().getResource(filename));
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		
		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		
		GraphicsConfiguration gc = ge.getDefaultScreenDevice().getDefaultConfiguration();
		
		int transparency = img.getColorModel().getTransparency();
		
		BufferedImage copy = gc.createCompatibleImage(img.getWidth(), img.getHeight(), transparency);
		
		Graphics2D  g = copy.createGraphics();
		
		g.drawImage(img, 0, 0, null);
		g.dispose();
		
		return copy;
	}
	
	public BufferedImage convertImageToBufferedImage(Image img,int width,int height)
	{
		BufferedImage imgBuffrer = new BufferedImage(width,height, BufferedImage.TYPE_INT_ARGB);
		
		Graphics2D g = imgBuffrer.createGraphics();
		
		g.drawImage(img, 0, 0, null);
		g.dispose();
		
		return imgBuffrer;
	}
}