package com.kiteiru.blocklist;

import com.kiteiru.exceptions.CommandException;
import java.util.*;

public interface BlockBaseInterface {
    List<String> args = new ArrayList<>();

    void ExecuteWF(List<String> context) throws CommandException;

    void addArg(String arg);

    int getPosition();
}