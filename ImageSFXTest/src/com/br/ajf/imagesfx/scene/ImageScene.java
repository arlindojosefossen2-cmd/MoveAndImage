package com.br.ajf.imagesfx.scene;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import com.br.ajf.imagesfx.direction.I_8_Direction_Movement;
import com.br.ajf.imagesfx.img.ImageLoader;
import com.br.ajf.imagesfx.img.ImageSFX;
import com.br.ajf.imagesfx.math.Vector2I;

import br.com.ajf.game.button.GameButton;
import br.com.ajf.game.button.IGameButton;
import br.com.ajf.game.input.container.GameInputContainer;
import br.com.ajf.game.model.Game;
import br.com.ajf.game.scene.Scene;

public class ImageScene implements Scene
{
	Game game;
	IGameButton testing;
	
	Image img;
	ImageLoader imgLoader;
	BufferedImage img2;
	BufferedImage img3[] = new BufferedImage[8];
	
	Vector2I position = new Vector2I(120, 120);
	Vector2I velocity = new Vector2I(2, 2);
	int direction = I_8_Direction_Movement.DOWN;
	private boolean moving;
	int index = I_8_Direction_Movement.DOWN;

	public ImageScene(Game game)
	{
		this.game = game;
		
	}

	@Override
	public void start()
	{
		imgLoader = new ImageLoader();
		
		img = imgLoader.getImage("/img/monsteranimation.gif");
		
		testing = new GameButton("Testing", 620, 520, 90, 50, 18, 
		() ->
		{
			System.out.println("Testing!");
			
		});
		
		img2 = imgLoader.getBufferedImage("/img/testup.png");//up
		//8 direction images
		img3[I_8_Direction_Movement.UP] = img2;//up
		img3[I_8_Direction_Movement.UP_LEFT] = ImageSFX.getRotatedImage(img2, -45);//up left
		img3[I_8_Direction_Movement.UP_RIGHT] = ImageSFX.getRotatedImage(img2, 45); //up right
		img3[I_8_Direction_Movement.DOWN] = ImageSFX.getRotatedImage(img2, 180); //down
		img3[I_8_Direction_Movement.DOWN_LEFT] = ImageSFX.getRotatedImage(img2, 225); //down left
		img3[I_8_Direction_Movement.DOWN_RIGHT] = ImageSFX.getRotatedImage(img2, 135); // down right
		img3[I_8_Direction_Movement.LEFT] = ImageSFX.getRotatedImage(img2, -90); //left	
		img3[I_8_Direction_Movement.RIGHt] = ImageSFX.getRotatedImage(img2, 90); //right
	
		
		
//		ImageSFX.imageColorSFX(img2, 50, 60, 70, 255);
		
	}

	@Override
	public void update()
	{
		testing.update();	
		
		
		if((GameInputContainer.keys[KeyEvent.VK_W] && GameInputContainer.keys[KeyEvent.VK_D]) ||
			(GameInputContainer.keys[KeyEvent.VK_RIGHT] && GameInputContainer.keys[KeyEvent.VK_UP]))
		{
			 direction = I_8_Direction_Movement.UP_RIGHT;
			 moving = true;
		}
		else if((GameInputContainer.keys[KeyEvent.VK_W] && GameInputContainer.keys[KeyEvent.VK_A]) ||
				(GameInputContainer.keys[KeyEvent.VK_LEFT] && GameInputContainer.keys[KeyEvent.VK_UP]))
		{
				 direction = I_8_Direction_Movement.UP_LEFT;
				 moving = true;	
		}
		else if((GameInputContainer.keys[KeyEvent.VK_S] && GameInputContainer.keys[KeyEvent.VK_D]) ||
				(GameInputContainer.keys[KeyEvent.VK_RIGHT] && GameInputContainer.keys[KeyEvent.VK_DOWN]))
		{
				 direction = I_8_Direction_Movement.DOWN_RIGHT;
				 moving = true;	
		}
		else if((GameInputContainer.keys[KeyEvent.VK_S] && GameInputContainer.keys[KeyEvent.VK_A]) ||
				(GameInputContainer.keys[KeyEvent.VK_LEFT] && GameInputContainer.keys[KeyEvent.VK_DOWN]))
		{
				 direction = I_8_Direction_Movement.DOWN_LEFT;
				 moving = true;	
		}
		else if(GameInputContainer.keys[KeyEvent.VK_W] || GameInputContainer.keys[KeyEvent.VK_UP])
		{
			 direction = I_8_Direction_Movement.UP;
			 moving = true;
		}
		else if(GameInputContainer.keys[KeyEvent.VK_S] || GameInputContainer.keys[KeyEvent.VK_DOWN])
		{
			 direction = I_8_Direction_Movement.DOWN;
			 moving = true;
		}
		else if(GameInputContainer.keys[KeyEvent.VK_A] || GameInputContainer.keys[KeyEvent.VK_LEFT])
		{
			 direction = I_8_Direction_Movement.LEFT;
			 moving = true;
		}
		else if(GameInputContainer.keys[KeyEvent.VK_D] || GameInputContainer.keys[KeyEvent.VK_RIGHT])
		{
			 direction = I_8_Direction_Movement.RIGHt;
			 moving = true;
		}
		else
		{
			moving = false;
		}
		
		if(moving)
		{
			switch(direction)
			{
				case I_8_Direction_Movement.UP:
					position.setY(position.getY() - velocity.getY());
					index = 0;
					break;
				case I_8_Direction_Movement.UP_RIGHT:
					position.add(velocity.getX(),-velocity.getY());
					index = 2;
					break;
				case I_8_Direction_Movement.UP_LEFT:
					position.add(-velocity.getX(),-velocity.getY());
					index = 1;
					break;
				case I_8_Direction_Movement.DOWN_RIGHT:
					position.add(velocity.getX(),velocity.getY());
					index = 5;
					break;
				case I_8_Direction_Movement.DOWN_LEFT:
					position.add(-velocity.getX(),velocity.getY());
					index = 4;
					break;
				case I_8_Direction_Movement.DOWN:
					position.setY(position.getY() + velocity.getY());
					index = 3;
					break;
				case I_8_Direction_Movement.LEFT:
					position.setX(position.getX() - velocity.getX());
					index = 6;
					break;
				case I_8_Direction_Movement.RIGHt:
					position.setX(position.getX() + velocity.getX());
					index = 7;
					break;
			}
		}
		
	}
	
	@Override
	public void draw(Graphics2D arg0)
	{
	
//		arg0.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
//				 RenderingHints.VALUE_ANTIALIAS_ON);
//		arg0.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
//				 RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		
//		arg0.setColor(Color.blue);
//		arg0.fillRect(0, 0, 720, 596);
		
		if(index >= 0 && index < img3.length)
			arg0.drawImage(img3[index], position.getX(), position.getY(),96,96, null);
		
		testing.draw(arg0);
		
		
		
		
	}
}