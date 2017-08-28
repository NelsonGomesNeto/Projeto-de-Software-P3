package com.design.patterns.window.decorator.organized;

public class WindowTest {

	public static void main(String[] args) {

		Window window = new SimpleWindow();
		System.out.println(window.getDescription());

		window = new VerticalScrollBarDecorator(window);
		System.out.println(window.getDescription());

		window = new HorizontalScrollBarDecorator(window);
		System.out.println(window.getDescription());

		Window fullWindow = new VerticalScrollBarDecorator(new HorizontalScrollBarDecorator(new SimpleWindow()));
		System.out.println(window.getDescription());
	}
}
