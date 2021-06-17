package com.kiteiru.blocklist;

import com.kiteiru.exceptions.CommandException;
import java.io.*;
import java.util.List;

public class ReadFileBlock implements BlockBaseInterface {

    public void ExecuteWF(List<String> context) throws CommandException {
        if (args.size() != 1) {
            throw new CommandException("Wrong amount of args");
        } else {
            try (BufferedReader reader = new BufferedReader(new FileReader(args.get(0)))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    context.add(line);
                }
            } catch (IOException e) {
                throw new CommandException("Error occured with reading file", e);
            }
        }
    }

    @Override
    public void addArg(String arg) {
        args.add(arg);
    }

    @Override
    public int getPosition() {
        return 1;
    }
}