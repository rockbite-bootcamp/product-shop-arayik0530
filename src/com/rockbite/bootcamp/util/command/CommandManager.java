package com.rockbite.bootcamp.util.command;

import com.rockbite.bootcamp.Player;
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
    private int historyCursor = 0;

    //pool of buy commands
    Pool<PurchaseCommand> buyCommandPool = new Pool<PurchaseCommand>() {
        @Override
        public PurchaseCommand newObject() {
            return new PurchaseCommand();
        }
    };

    //pool of undo buy commands
    Pool<RefundCommand> undoBuyCommandPool = new Pool<RefundCommand>() {
        @Override
        public RefundCommand newObject() {
            return new RefundCommand();
        }
    };

    /**
     * main method of this class, it represents execution of main command
     *
     * @param command   Command
     * @param buyer     PLayer
     * @param productId unique id of the Product
     */
    public void executeCommand(Command command, Player buyer, int productId) {
        command.execute(buyer, productId);

        if (historyCursor < history.size()) {
            history.set(historyCursor, command);
        } else {
            history.add(command);
        }

        historyCursor++;
    }


    /**
     * undo the last command
     *
     * @param buyer     PLayer
     * @param productId unique id of the Product
     */
    public void undo(Player buyer, int productId) {
        if (historyCursor == 0) return;

        Command command = history.get(historyCursor - 1);
        command.undo(buyer, productId);

        historyCursor--;
    }

    /**
     * redo the last undone command
     *
     * @param buyer     Player
     * @param productId unique id of the Product
     */
    public void redo(Player buyer, int productId) {
        if (historyCursor > history.size() - 1) return;

        Command command = history.get(historyCursor);
        command.execute(buyer, productId);
        historyCursor++;
    }


    //Getters

    public Pool<PurchaseCommand> getBuyCommandPool() {
        return buyCommandPool;
    }

    public Pool<RefundCommand> getUndoBuyCommandPool() {
        return undoBuyCommandPool;
    }
}
