package tdd.vendingMachine.machine.cli.util;

public class AnsiColorDecorator {

	private static final String RESET = "\u001B[0m";
	private static final String RED = "\u001B[31m";
	private static final String GREEN = "\u001B[32m";
	private static final String YELLOW = "\u001B[33m";
	private static final String WHITE = "\u001B[37m";

	public static String green(String message) {
		return decorate(message, GREEN);
	}

	private static String decorate(String message, String color) {
		return color + message + RESET;
	}

}