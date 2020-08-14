package Project;

import java.util.Scanner;

public class Project_2 {

	static Scanner sc;
	static float scoreMidterm, scoreFinal, total;
	static int totalWeight = 0;

	// Phương thức hiển thị thông báo chức năng của chương trình
	public static void begin() {
		System.out.println("This program reads exem/homework scores and reports your overall course grade.");
	}

	// Phương thức tính điểm midterm
	public static void midterm() {
		System.out.println();
		System.out.println("Midterm:");
		scoreMidterm = pt();
		System.out.println();
	}

	// Phương thức tính điểm finalterm
	public static void finalterm() {
		System.out.println("Final:");
		scoreFinal = pt();
		System.out.println();
	}

	// Hàm phương thức dùng cho tính điểm midterm and finalterm
	public static float pt() {
		int shiftAmount = 0;
		int totalPoint = 0;
		int score = 0;
		int weight = 0;
		sc = new Scanner(System.in);
		weight = inputData("Weight (0-100)? ", 0, 100 - totalWeight);
		totalWeight += weight;
		score = inputData("Score earned (0-100)? ", 0, 100);
		int av = inputData("Were scores shifted (1=yes, 2=no)? ", 1, 2);
		if (av == 1) {
			System.out.print("Shift amount? ");
			shiftAmount = sc.nextInt();
		}
		totalPoint = score + shiftAmount;
		if (totalPoint > 100) {
			totalPoint = 100;
		}
		System.out.println("Total points = " + totalPoint + " / 100");
		float weightedScore = ((float) Math.round(totalPoint) / 100) * weight;
		System.out.println("Weighted score = " + (float) Math.round(weightedScore * 10) / 10 + " / " + weight);
		return weightedScore;
	}

	// Phương thức kiểm tra giá trị nhập vào là có phải là số hay không, nếu không
	// phải thì nhập lại và giới hạn số nhập vào từ 0->100
	public static int inputData(String message, int min, int max) {
		int value = 0;
		do {
			System.out.print(message);
			while (!sc.hasNextInt()) {
				sc.next();
				System.out.print(message);
			}
			value = sc.nextInt();
		} while (value < min || value > max);
		return value;
	}

	// Phương thức tính điểm homework
	public static void homework() {
		System.out.println("Homework:");
		sc = new Scanner(System.in);
		int weight = inputData("Weight (0-100)? ", 0, 100 - totalWeight);
		totalWeight += weight;
		int totalHomework = totalWeight;
		System.out.print("Number of assignment ");
		int assignment = inputData("", 0, 100);
		int realScore = 1;
		int maxScore = 0;
		int totalRealScore = 0;
		int totalMaxScore = 0;
		int totalScore = 0;
		int totalMax = 0;
		int attend;
		int totaAttend;
		int max = 30;
		for (int i = 1; i <= assignment; i++) {
			do {
				System.out.print("Assignment " + i + " score and max? ");
				while (!sc.hasNextInt()) {
					sc.next();
					sc.next();
					System.out.print("Assignment " + i + " score and max? ");
				}
				realScore = sc.nextInt();
				maxScore = sc.nextInt();
			} while (realScore > maxScore);
			totalRealScore += realScore;
			totalMaxScore += maxScore;
		}
		System.out.print("How many sections did you attend? ");
		attend = inputData("", 0, 30);
		totaAttend = attend * 5;
		System.out.println("Section points = " + totaAttend + " / 30");
		totalScore = totalRealScore + totaAttend;
		totalMax = totalMaxScore + max;
		if (totalScore > 150 && totalMax > 150) {
			totalScore = 150;
			totalMax = 150;
		}
		System.out.println("Total points = " + totalScore + " / " + totalMax);
		total = (float) totalScore / totalMax * weight;
		System.out.println("Weighted score = " + (float) Math.round(total * 10) / 10 + " / " + weight);
	}

	// Phương thức hiển thị kết quả tổng cộng
	public static void report() {
		System.out.println();
		float overall = scoreMidterm + scoreFinal + total;
		float overallPercentage = (float) Math.round(overall * 10) / 10;
		System.out.println("Overall percentage = " + overallPercentage);
		if (overallPercentage >= 85) {
			System.out.println("Your grade will be at least: 3.0");
		} else if (overallPercentage <= 84.99 && overallPercentage >= 75) {
			System.out.println("Your grade will be at least: 2.0");
		} else if (overallPercentage <= 74.99 && overallPercentage >= 60) {
			System.out.println("Your grade will be at least: 0.7");
		} else {
			System.out.println("Your grade will be at least: 0.0");
		}
		System.out.println("<< your custom grade message here >>");
	}

	public static void main(String[] args) {
		int value = 0;
        do {
        	begin();
    		midterm();
    		finalterm();
    		homework();
    		report();
    		value = sc.nextInt();
        } while (value < 100 || value > 100);
        
	}
}
