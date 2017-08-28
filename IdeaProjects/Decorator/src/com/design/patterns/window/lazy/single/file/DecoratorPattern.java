package com.design.patterns.window.lazy.single.file;

import com.design.patterns.coffee.lazy.SimpleCoffee;

public class DecoratorPattern {
	public static void main(String[] args) {
		// create a decorated Window with horizontal and vertical scrollbars
		WindowWithHorizontalScrollBarWithVerticalScrollBar windowWithHorizontalScrollBarWithVerticalScrollBar = new WindowWithHorizontalScrollBarWithVerticalScrollBar();

		// print the Window's description
		System.out.println(windowWithHorizontalScrollBarWithVerticalScrollBar.getDescription());
	}
}

// the Window interface
interface Window {
	public void draw(); // draws the Window

	public String getDescription(); // returns a description of the Window
}

// implementation of a simple Window without any scrollbars
class SimpleWindow implements Window {
	public void draw() {
		// draw window
	}

	public String getDescription() {
		return "simple window";
	}
}

// the first concrete decorator which adds vertical scrollbar functionality
class WindowWithVerticalScrollBar extends SimpleWindow {
	public WindowWithVerticalScrollBar() {
		super();
	}

	public void draw() {
		super.draw();
		drawVerticalScrollBar();
	}

	private void drawVerticalScrollBar() {
		// draw the vertical scrollbar
	}

	public String getDescription() {
		return super.getDescription()
			+ ", including vertical scrollbars";
	}
}

// the second concrete decorator which adds horizontal scrollbar functionality
class WindowWithHorizontalScrollBar extends SimpleWindow {
	public WindowWithHorizontalScrollBar() {
		super.draw();
	}

	public void draw() {
		super.draw();
		drawHorizontalScrollBar();
	}

	private void drawHorizontalScrollBar() {
		// draw the horizontal scrollbar
	}

	public String getDescription() {
		return super.getDescription()
			+ ", including horizontal scrollbars";
	}
}

// the second concrete decorator which adds horizontal scrollbar functionality
class WindowWithHorizontalScrollBarWithVerticalScrollBar extends SimpleWindow {
	public WindowWithHorizontalScrollBarWithVerticalScrollBar() {
		super.draw();
	}

	public void draw() {
		super.draw();
		drawHorizontalScrollBar();
		drawVerticalScrollBar();
	}

	private void drawHorizontalScrollBar() {
		// draw the horizontal scrollbar
	}

	private void drawVerticalScrollBar() {
		// draw the vertical scrollbar
	}

	public String getDescription() {
		return super.getDescription()
			+ ", including horizontal scrollbars"
			+ ", including vertical scrollbars";
	}
}