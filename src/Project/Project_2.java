package Project;

import java.util.Scanner;

public class Project_2 {

	static Scanner sc;

	/*
	 * Hàm gọi ra Weighted score(điểm có trọng số) để tính Overall percentage(phần
	 * trăm tổng thể)
	 */
	static float scoreMidterm, scoreFinal, total;

	/* Hàm tính tổng thể Weight = 100 */
	static int totalWeight = 0;

	/* Phương thức hiển thị thông báo chức năng của chương trình */
	public static void begin() {
		System.out.println("This program reads exem/homework scores and reports your overall course grade.");
	}

	/* Phương thức tính điểm midterm */
	public static void midterm() {
		System.out.println();
		System.out.println("Midterm:");
		scoreMidterm = inputScoreProcess();
		System.out.println();
	}

	/* Phương thức tính điểm finalterm */
	public static void finalterm() {
		System.out.println("Final:");
		scoreFinal = inputScoreProcess();
		System.out.println();
	}

	/* Hàm phương thức dùng cho tính điểm midterm and finalterm */
	public static float inputScoreProcess() {
		int shiftAmount = 0;
		int totalPoint = 0;
		int score = 0;
		int weight = 0;
		sc = new Scanner(System.in);
		/*
		 * gọi ra phương thức inputData sau đó nhập đoạn string mình muốn cho vào và số
		 * nhập vào trong khoảng đó
		 */
		/*
		 * totalWeight là phương thức được khai báo và mỗi lần nhập vào là trừ đi 100 và
		 * lần ké tiếp là trừ đi số mới trừ được cho đến 0 nếu như trừ hết đi thì số
		 * cuối cùng phải là số phù hợp nhất đề trừ đi để kết quả cuối cùng bằng 0
		 */
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
		// Làm tròn lên gồm 1 số thập phân
		System.out.println("Weighted score = " + (float) Math.round(weightedScore * 10) / 10 + " / " + weight);
		return weightedScore;
	}

	/*
	 * Phương thức kiểm tra giá trị nhập vào là có phải là số hay không, nếu không
	 * phải thì nhập lại và giới hạn số nhập vào từ 0->100
	 */
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

	/* Phương thức tính điểm homework */
	public static void homework() {
		System.out.println("Homework:");
		sc = new Scanner(System.in);
		int weight = inputData("Weight (0-100)? ", 0, 100 - totalWeight);
		totalWeight += weight;
		System.out.print("Number of assignment ");
		int assignment = inputData("", 0, 100);
		int realScore = 0;
		int maxScore = 0;
		int totalRealScore = 0;
		int totalMaxScore = 0;
		int totalScore = 0;
		int totalMax = 0;
		int attend;
		int totaAttend;
		int max = 30;
		String s1 = sc.next();
		String s2 = sc.next();
		// Vòng for có nhiệm vụ tăng bài ASM số N
		for (int i = 1; i <= assignment; i++) {
			// Đọc 2 giá trị nhập vào và coi như là 2 chuỗi string
			
			while (true) {
				System.out.print("Assignment " + i + " score and max? ");
				// ép kiểu cả 2 string thành lại 2 số
				// nếu 1 trong 2 bị fail thì sẽ phải nhập lại
				// nếu ép thành int thành công và score <= max thì coi như nhập đúng và break dùng để thoát vòng lặp while
				try {
					realScore = Integer.parseInt(s1);
					maxScore = Integer.parseInt(s2);
					if (realScore <= maxScore) {
						totalRealScore += realScore;
						totalMaxScore += maxScore;
						break;
					} else {
						System.out.println("Your input is incorrect. Please input again!");
					}
				} catch (Exception e) {
					System.out.println("Your input is incorrect. Please input again!");
				}
			}
		}
		System.out.print("How many sections did you attend? ");
		attend = inputData("", 0, 30);
		totaAttend = attend * 5;
		if (totaAttend >= 30) {
			totaAttend = 30;
		}
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

	/* Phương thức hiển thị kết quả tổng cộng */
	public static void report() {
		System.out.println();
		// Lấy ra phần trăm tổng thể của 3 phần điểm và tính 
		float overall = scoreMidterm + scoreFinal + total;
		float overallPercentage = (float) Math.round(overall * 10) / 10;
		System.out.println("Overall percentage = " + overallPercentage);
		// Điều kiện tìm ra điểm thấp nhất
		if (overallPercentage >= 85) {
			System.out.println("Your grade will be at least: 3.0");
		} else if (overallPercentage < 85 && overallPercentage >= 75) {
			System.out.println("Your grade will be at least: 2.0");
		} else if (overallPercentage < 75 && overallPercentage >= 60) {
			System.out.println("Your grade will be at least: 0.7");
		} else {
			System.out.println("Your grade will be at least: 0.0");
		}
		System.out.println("<< your custom grade message here >>");
	}

	public static void main(String[] args) {
		begin();
		midterm();
		finalterm();
		homework();
		report();
	}
}
