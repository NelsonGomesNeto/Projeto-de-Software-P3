package com.design.patterns.window.decorator.organized;

public class VerticalScrollBarDecorator extends WindowDecorator {

	public VerticalScrollBarDecorator (Window window) {
		super(window);
	}

	@Override
	public void draw() {
		super.draw();
		drawVerticalScrollBar();
	}

	public void drawVerticalScrollBar() {
	}

	@Override
	public String getDescription() {
		return super.getDescription() + ", including vertical scrollbars";
	}
}
