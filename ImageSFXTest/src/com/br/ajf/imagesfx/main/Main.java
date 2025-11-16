package com.br.ajf.imagesfx.main;

import com.br.ajf.imagesfx.scene.ImageScene;

import br.com.ajf.game.model.Game;
import br.com.ajf.game.scene.Scene;
import br.com.ajf.game.thread.IGameThreadManager;

public final class Main
{
	
	private Main()
	{
		
	}
	
	public static void main(String[] args)
	{
//		System.setProperty("sun.java2d.translaccel", "true");
//		System.setProperty("sun.java2d.ddforcevram", "true");
//		System.setProperty("sun.java2d.opengl","true");
		
		Game game = new Game("ImageSFX", 720, 596, IGameThreadManager.GAME_THREAD_DEFAULT);
		game.setIcon("/img/gameicon.png");
		Scene imageScene = new ImageScene(game);
		imageScene.start();
		
		game.addScene(imageScene);
		
		game.init(IGameThreadManager.GAME_THREAD_DEFAULT);
	}
}