package edu.kit.informatik.ui.commands;

import java.lang.reflect.InvocationTargetException;
import edu.kit.informatik.exceptions.*;
/**
 * 
 * @author Rodi
 *@version 1.0
 */

public enum Command {
    /**
     * instance for add command
     */
    ADD("add", Add.class),
    /**
     * instance for list command
     */
    LIST("list", ListCommand.class),
    /**
     * instance for print command
     */
    PRINT("print", Print.class),
    /**
     * instance for flow command
     */
    FLOW("flow", Flow.class);
    
    private String commandString;
    private Class<? extends UserRequest> commandClass;
    
    private Command(String command, Class<? extends UserRequest> commandClass) {
        this.commandString = command;
        this.commandClass = commandClass;
    }
    
    /**
     * get method for commandString
     * @return String of commandString
     */
    public String getCommandString() {
        return commandString;
    }
    
    /**
     * 
     * @return class of an instance
     */
    public Class<? extends UserRequest> getCommandClass() {
        return commandClass;
    }
    
    /**
     * this method takes an inputString and returns an instance of the corresponding command class
     * @param inputString
     * @return an instance of a corresponding class of an enum instance
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     * @throws SecurityException
     * @throws SyntacticException
     */
    public static UserRequest getCorrespondingInstance(String inputString) throws InstantiationException, 
        IllegalAccessException, IllegalArgumentException, InvocationTargetException, 
        NoSuchMethodException, SecurityException, SyntacticException {
        for (Command command : Command.values()) {
            if (command.getCommandString().equals(inputString) ) {
                return command.getCommandClass().getDeclaredConstructor().newInstance();
            }
        }
        throw new SyntacticException();
    }

}
