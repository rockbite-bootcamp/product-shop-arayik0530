package com.rockbite.bootcamp.util.command;

import com.rockbite.bootcamp.Player;
import com.rockbite.bootcamp.Product;
import com.rockbite.bootcamp.util.pool.Pool;

import java.util.ArrayList;
import java.util.List;

/**
 * this class Manages commands
 */
public class CommandManager {
    //history of commands
    List<Command> history = new ArrayList<>();
    //cursor shows when we are in the history
    private int cursor = 0;

    //pool of buy commands
    Pool<BuyCommand> buyCommandPool = new Pool<BuyCommand>() {
        @Override
        public BuyCommand newObject() {
            return new BuyCommand();
        }
    };

    //pool of undo buy commands
    Pool<UndoBuyCommand> undoBuyCommandPool = new Pool<UndoBuyCommand>() {
        @Override
        public UndoBuyCommand newObject() {
            return new UndoBuyCommand();
        }
    };

    /**
     * main method of this class, it represents execution of main command
     * @param command Command
     * @param buyer PLayer
     * @param product Product
     * @throws Exception
     */
    public void executeCommand(Command command, Player buyer, Product product) throws Exception {
        command.execute(buyer, product);

        if (cursor < history.size()) {
            history.set(cursor, command);
        } else {
            history.add(command);
        }

        cursor++;
    }


    /**
     * undo the last command
     * @param buyer PLayer
     * @param product Product
     * @throws Exception
     */
    public void undo(Player buyer, Product product) throws Exception {
        if (cursor == 0) return;

        Command command = history.get(cursor - 1);
        command.undo(buyer, product);

        cursor--;
    }

    /**
     * redo the last undone command
     * @param buyer Player
     * @param product Product
     * @throws Exception
     */
    public void redo(Player buyer, Product product) throws Exception {
        if (cursor > history.size() - 1) return;

        Command command = history.get(cursor);
        command.execute(buyer, product);
        cursor++;
    }


    //Getters

    public List<Command> getHistory() {
        return history;
    }

    public int getCursor() {
        return cursor;
    }

    public Pool<BuyCommand> getBuyCommandPool() {
        return buyCommandPool;
    }

    public Pool<UndoBuyCommand> getUndoBuyCommandPool() {
        return undoBuyCommandPool;
    }
}
