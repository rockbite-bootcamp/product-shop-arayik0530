package com.rockbite.bootcamp.util.command;

import com.rockbite.bootcamp.Player;

/**
 * Command interface represents buying or undo buying commands
 */
public interface Command {

    void execute(Player buyer, int productId);

    void undo(Player buyer, int productId);
}
