package com.kiteiru.blocklist;

import com.kiteiru.exceptions.CommandException;
import java.util.List;

public class GrepBlock implements BlockBaseInterface {

    @Override
    public void ExecuteWF(List<String> context) throws CommandException {
        if (args.size() != 1) {
            throw new CommandException("Wrong amount of args");
        } else {
            context.removeIf(S -> (!S.contains(args.get(0))));
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