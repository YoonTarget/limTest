package com.jin.test.run;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Run {

	public static void main(String[] args) {
		/*
		<CD롬 과제>
		1. CD롬의 사이즈는 650M
		2. CD롬안에 동영상을 담아야 합니다.
		3. 동영상 파일은 650M이하의 사이즈로 여러개가 존재합니다.
		4. 동영상파일을 기준으로 CD롬이 몇개 필요한지,
		    CD롬안에 최적으로 동영상을 담아야 합니다.
	
		아래는 예시
		
		10M, 110M, 50M, 20M, 5M, 500M, 70M의 동영상이 존재하는경우
		아래와 같이 CD에 동영상 파일들이 들어갑니다.
	
		CD1에는 500M, 110M, 20M, 10M, 5M
		CD2에는 70M, 50M
		*/
		
		// 스캐너 생성
		Scanner sc = new Scanner(System.in);
		
		// CD롬 사이즈는 650 고정
		final int cd = 650;
		
		// 프로세스 횟수
		int count = 0;
		
		// 처음에는 동영상 크기를 무조건 입력받아야 하기 때문에 Y로 초기화
		String answer = "Y";
		
		// X가 입력되기 전까지 무한 반복
		while(true) {
			
			// 몇 번째 프로세스인지 판단
			if(count > 0) {
				System.out.println("더 하시겠습니까??(Y or X)");
				answer = sc.nextLine().toUpperCase();
			}
			
			// X가 입력되면 프로그램 종료
			if(answer.equals("X")) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
			// Y가 입력되면 계속 진행
			else if(answer.equals("Y")) {
				
				// 입력받은 동영상의 크기를 임시로 담을 리스트 (첫 번째 CD롬)
				ArrayList<Object> list = new ArrayList<>();
				
				// 입력 안내 문구
				System.out.println("동영상의 크기를 입력해주세요.(1 <= 크기 <= 650 / 종료 : 0)");
				
				// 동영상 크기 초기화
				int size = 0;
				
				// 0이 입력되기 전까지 무한 반복
				while(true) {
					
					System.out.print(">> ");
					
					// 숫자 이외의 글자를 입력할 경우의 예외 처리 장치
					try {
						
						// 동영상의 용량을 담을 변수
						size = sc.nextInt();
						
						// 0이 입력되면 반복문 종료
						if(size == 0) {
							break;
						}
						// 범위 외의 숫자를 입력할 경우 다시 입력하도록 조치
						else if(size > 650 || size < 0) {
							System.out.println("1과 650 사이의 크기로 다시 입력해 주세요.");
						}
						// 0이 아니라면 리스트에 저장
						else {
							list.add(size);
						}
						
					} catch (Exception e) {
						
						System.out.println("숫자만 입력해 주세요.");
						
						// 메로리에 남아있는 공백을 개행 처리해서 숫자를 입력받을 자리를 마련해준다
						sc.nextLine();
						
					}
					
				}
				
				////// 반복문을 통한 내림차순 정렬 방식 ////
				/*
				// 리스트의 사이즈만큼 배열 할당
				int[] arr = new int[list.size()];

				// 배열에 옮겨담기
				for(int i = 0; i < list.size(); i++) {
					
						arr[i] = (int)list.get(i);
				
				}
				
				// 정렬을 도와줄 변수
				int temp;
				
				// 내림차순 정렬 반복문
				for(int i = 0; i < arr.length - 1; i++) {
					for(int j = i + 1; j < arr.length; j++) {
						
						if(arr[i] < arr[j]) {
							temp = arr[i];
							arr[i] = arr[j];
							arr[j] = temp;
						}
						
					}
				}
				*/
				/////////////////////////////////////
				
				//// sort 메소드를 통한 내림차순 정렬 방식 ////

				// 리스트의 사이즈만큼 배열 할당
				Integer[] arr = new Integer[list.size()];
				
				// 배열에 옮겨담기
				for(int i = 0; i < list.size(); i++) {
					
					arr[i] = (int)list.get(i);
			
				}
				
				// Arrays.sort 메소드를 활용하여 내림차순 정렬
				Arrays.sort(arr, Collections.reverseOrder());
				
				///////////////////////////////////////
				
				// 첫 번째 CD롬에 담기지 못한 동영상을 담을 두 번째 CD롬
				ArrayList<Object> newList = new ArrayList<Object>();
				
				// 전체 CD롬 리스트
				ArrayList<String> cdList = new ArrayList<String>();
				
				// 총 용량 리스트
				ArrayList<Object> sumList = new ArrayList<Object>();
				
				// CD롬에 다 담을 때까지 무한 반복
				while(true) {
					
					// 첫 번째 CD롬 초기화
					list = new ArrayList<Object>();
					
					// 가장 큰 동영상을 먼저 담는다
					list.add(arr[0]);
					
					// 남은 용량
					int spare = cd - arr[0];
					
					// CD룸 누적 용량
					int sum = arr[0];

					// CD롬에 남은 동영상 담기
					for(int i = 1; i < arr.length; i++) {
						
						// 첫 번째 CD롬에 담기
						if(arr[i] <= spare) {
							list.add(arr[i]);
							
							spare -= arr[i];
							
							sum += arr[i];
						}
						// 두 번째 CD롬에 담기
						else {
							newList.add(arr[i]);
							
							continue;
						}
						
					}

					// CD롬 리스트에 담기
					cdList.add(list.toString());
					
					// 총 용량 리스트에 담기
					sumList.add(sum);
					
					// CD롬에 남김없이 담았다면 반복문 종료
					if(newList.isEmpty()) {
						break;
					}
					// 남은 동영상이 있다면 다시 담기
					else {
						
						// 새로운 첫 번째 CD롬에 두 번째 CD롬 복사
						list = newList;
						
						// 반복문 방식에 필요한 배열
						//arr = new int[list.size()];
						
						// sort 방식에 필요한 배열
						arr = new Integer[list.size()]; 
						
						// 배열에 리스트 옮겨담기
						for(int i = 0; i < list.size(); i++) {
							
							arr[i] = (int)list.get(i);
							
						}
						
						// 두 번째 CD롬 초기화
						newList = new ArrayList<Object>();
						
					}
					
					
				}
				
				// 총 CD롬 갯수 출력
				System.out.println("CD롬 갯수 : " + cdList.size() + "개");
				
				// CD롬 리스트 출력
				for(int i = 0; i < cdList.size(); i++) {
					
					System.out.print("CD" + (i + 1) + " : " + cdList.get(i) + " => ");
					
					System.out.println("총 용량 : " + sumList.get(i) + "M/650M");
					
				}
				
				System.out.println("===============================================");
				
				// 프로세스 횟수 증가
				count++;
				
				// 메모리에 남은 공백 제거
				sc.nextLine();
				
			}
			// 다른 글자를 입력했을 경우
			else {
				System.out.println("Y 나 X를 입력해 주세요.");
			}
			
		}
		
		// 스캐너 반납
		sc.close();

	}

}
