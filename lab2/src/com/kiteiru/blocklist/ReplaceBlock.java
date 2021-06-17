package com.kiteiru.blocklist;

import com.kiteiru.exceptions.CommandException;
import java.util.List;

public class ReplaceBlock implements BlockBaseInterface {

    @Override
    public void ExecuteWF(List<String> context) throws CommandException {
        if (args.size() != 2) {
            throw new CommandException("Wrong amount of args");
        } else {
            context.replaceAll(S -> (S.replaceAll(args.get(0), args.get(1))));
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
