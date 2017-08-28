package com.design.patterns.window.decorator.organized;

public abstract class WindowDecorator implements Window {

	protected Window windowToBeDecorated;

	public WindowDecorator(Window window) {
		this.windowToBeDecorated = window;
	}

	@Override
	public void draw() {
		windowToBeDecorated.draw();
	}

	@Override
	public String getDescription() {
		return windowToBeDecorated.getDescription();
	}
}
