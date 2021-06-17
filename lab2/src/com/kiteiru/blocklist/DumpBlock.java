package com.kiteiru.blocklist;

import com.kiteiru.exceptions.CommandException;
import java.io.*;
import java.util.List;

public class DumpBlock implements BlockBaseInterface {

    @Override
    public void ExecuteWF(List<String> context) throws CommandException {
        if (args.size() != 1) {
            throw new CommandException("Wrong amount of args");
        } else {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(args.get(0)))) {
                for (String line : context) {
                    writer.write(line);
                    writer.newLine();
                }
            } catch (IOException e) {
                throw new CommandException("Error occured with writing in file", e);
            }
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