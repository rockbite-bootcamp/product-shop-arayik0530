package com.rockbite.bootcamp.util.command;

import com.rockbite.bootcamp.Player;
import com.rockbite.bootcamp.Product;

/**
 * Command interface represents buying or undo buying commands
 */
public interface Command {

    void execute(Player buyer, Product product) throws Exception;

    void undo(Player buyer, Product product) throws Exception;
}
