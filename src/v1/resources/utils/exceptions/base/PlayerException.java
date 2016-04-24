package v1.resources.utils.exceptions.base;

import v1.resources.Player;
import v1.resources.constants.Constants;

@SuppressWarnings("serial")
public class PlayerException extends Exception{
	private String message;
	private Player player;
	
	public PlayerException() {
		// TODO Auto-generated constructor stub
		this.message = Constants.MESSAGE_PLAYER_EXCEPTION; 
		this.player = null;
	}
	
	public PlayerException(String message) {
		this.message = message;
		this.player = null;
	}
	
	public PlayerException(Player player) {
		this.message = Constants.MESSAGE_PLAYER_EXCEPTION;
		this.player = player;
	}
	
	public PlayerException(String message, Player player) {
		this.message = message;
		this.player = player;
	}
	
	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return message;
	}
	
	public Player getPlayer() {
		return player;
	}
}
