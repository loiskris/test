package com.school.software;

import java.util.ArrayList;
import java.util.Scanner;

public class ReverseOutput {

	private static ArrayList<String> list = new ArrayList<String>();

	public static void main(String[] args) {
		System.out.println("please input strings:");
		Scanner sc = new Scanner(System.in);
		String str = sc.next();
		String[] strs = str.split(" ");
		for (String string : strs) {
			list.add(string);
		}
		for (int i = list.size(); i <= 0; i--) {
			System.out.print(list.get(i) + " ");
		}
		System.out.println();
	}
}
