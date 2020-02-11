package com.craftinginterpreters.lox;

import java.util.ArrayList;
import java.util.HashMap;
import java.List;
import java.Map;

import static com.craftinginterpreters.lox.TokenType.*;

class Scanner {
	private final String source;
	private final List<Token> tokens = new ArrayList<>();

	Scanner(String source) {
		this.source = source;
	}
}