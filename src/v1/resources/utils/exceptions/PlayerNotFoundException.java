package v1.resources.utils.exceptions;

import v1.resources.Player;
import v1.resources.utils.exceptions.base.PlayerException;

public class PlayerNotFoundException extends PlayerException {

	public PlayerNotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public PlayerNotFoundException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public PlayerNotFoundException(Player player) {
		super(player);
		// TODO Auto-generated constructor stub
	}

	public PlayerNotFoundException(String message, Player player) {
		super(message, player);
		// TODO Auto-generated constructor stub
	}

}
