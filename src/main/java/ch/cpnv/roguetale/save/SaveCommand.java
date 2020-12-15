package ch.cpnv.roguetale.save;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;

import org.newdawn.slick.Input;

import ch.cpnv.roguetale.save.enums.CommandType;

public class SaveCommand implements Serializable {
	private static final long serialVersionUID = 4041611389358502458L;
	private LinkedHashMap<CommandType, Integer> commands = new LinkedHashMap<CommandType, Integer>();

	public void setDefaultData() {
		commands.put(CommandType.Up, Input.KEY_W);
		commands.put(CommandType.Down, Input.KEY_S);
		commands.put(CommandType.Left, Input.KEY_A);
		commands.put(CommandType.Right, Input.KEY_D);
		commands.put(CommandType.Dash, Input.KEY_LSHIFT);
	}

	public HashMap<CommandType, Integer> getCommands() {
		return commands;
	}

	public void setCommands(LinkedHashMap<CommandType, Integer> commands) {
		this.commands = commands;
	}
	
	public int getKey(CommandType command) {
		return commands.get(command);
	}
	
	public void setKey(CommandType command, int key) {
		this.commands.replace(command, key);
	}
	 
}
