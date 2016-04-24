package v1.resources.utils;

import v1.resources.Player;

public interface PlayerStateChangeListener {
	public void onPlayerCardsExhausted(Player p);
	public void onPlayerCardsExhausted(Player p, String status);
}
