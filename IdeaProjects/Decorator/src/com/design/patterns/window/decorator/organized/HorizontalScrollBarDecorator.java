package com.design.patterns.window.decorator.organized;

public class HorizontalScrollBarDecorator extends WindowDecorator {

	public HorizontalScrollBarDecorator(Window window) {
		super(window);
	}

	@Override
	public void draw() {
		super.draw();
		drawHorizontalScrollBar();
	}

	public void drawHorizontalScrollBar() {
	}

	@Override
	public String getDescription() {
		return super.getDescription() + ", including horizontal scrollbars";
	}
}
