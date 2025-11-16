package com.br.ajf.imagesfx.img;

import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsEnvironment;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;


//183pg
public final class ImageSFX
{
	public static final int VERTICAL_FLIP = 0;
	public static final int HORIZONTAL_FLIP = 1;
	public static final int DOUBLE_FLIP = 2;
	
	private static int imageColorSFX(BufferedImage img,int row,int col,int red,int green,int blue,int alpha)
	{
		int pixel = img.getRGB(row, col);
		
		int alphaVal = (pixel >> 24) & alpha;
		int redVal = (pixel >> 16) & red;
		int greenVal = (pixel >> 8) & green;
		int blueVal = pixel & blue;
		
		int newPixel = blueVal | (greenVal << 8) | ( redVal << 16) | (alphaVal << 24); 
		
		return newPixel;	
	}
	
	public static void imageColorSFX(BufferedImage img,int red,int green,int blue,int alpha)
	{
//		Raster color = img.getData();
//		
//		DataBufferInt buffer =  (DataBufferInt) color.getDataBuffer();
//		
//		int [] data = buffer.getData();
		
		for (int i = 0; i < img.getWidth(); i++)
		{
			for (int j = 0; j < img.getHeight(); j++)
			{
				img.setRGB(i, j, imageColorSFX(img, i, j, red, green, blue, alpha));
			}
		}
		
	}
	
	public static BufferedImage getRotatedImage(BufferedImage src, int angle)
	{
		 if (src == null) 
		 {
			 return null;
		 }
	 
		 int transparency = src.getColorModel( ).getTransparency( );
		 
		 BufferedImage dest = GraphicsEnvironment.getLocalGraphicsEnvironment()
				 .getDefaultScreenDevice().getDefaultConfiguration()
				 .createCompatibleImage(src.getWidth( ), src.getHeight( ), transparency );
 
		 Graphics2D g2d = dest.createGraphics( );
		 
		 AffineTransform origAT = g2d.getTransform( );  
		 AffineTransform rot = new AffineTransform( );
		
		 rot.rotate( Math.toRadians(angle), src.getWidth( )/2, src.getHeight( )/2);
		
		 g2d.transform(rot);
		
		 g2d.drawImage(src, 0, 0, null); 
		
		 g2d.setTransform(origAT);
		
		 g2d.dispose( );
	 return dest;
	}
	
	public static BufferedImage getFlippedImage(BufferedImage im,int flipKind)
	{
		 if (im == null) 
		 {
			 return null;
		 }
		 
		 int imWidth = im.getWidth( );
		 int imHeight = im.getHeight( );
		 int transparency = im.getColorModel( ).getTransparency( );
		 
		 GraphicsConfiguration gc = 
				 GraphicsEnvironment.getLocalGraphicsEnvironment().
				 getDefaultScreenDevice().getDefaultConfiguration();
		 
		 BufferedImage copy =
		 gc.createCompatibleImage(imWidth, imHeight, transparency);
		 Graphics2D g2d = copy.createGraphics( );
		
		 renderFlip(g2d, im, imWidth, imHeight, flipKind);
		 
		 g2d.dispose( );
		 
	 return copy;
	} 
	
	private static void renderFlip(Graphics2D g2d, BufferedImage im,int imWidth, int imHeight, int flipKind)
	{
		
		if (flipKind == VERTICAL_FLIP)
			 g2d.drawImage(im, imWidth, 0, 0, imHeight, 0, 0, imWidth, imHeight, null);
		else if (flipKind == HORIZONTAL_FLIP)
			 g2d.drawImage(im, 0, imHeight, imWidth, 0,0, 0, imWidth, imHeight, null);
		else 
			 g2d.drawImage(im, imWidth, imHeight, 0, 0, 0, 0, imWidth, imHeight, null);
	}
}