package com.kiteiru.blocklist;

import com.kiteiru.exceptions.CommandException;
import java.util.List;

public class SortBlock implements BlockBaseInterface {

    @Override
    public void ExecuteWF(List<String> context) throws CommandException {
        if (args.size() != 0) {
            throw new CommandException("Wrong amount of args");
        } else {
            context.sort(String::compareTo);
        }
    }

    @Override
    public void addArg(String arg) {
        args.add(arg);
    }

    @Override
    public int getPosition() {
        return 2;
    }
}