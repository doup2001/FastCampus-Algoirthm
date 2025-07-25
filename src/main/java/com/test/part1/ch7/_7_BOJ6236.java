package com.test.part1.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제
 * - 현우는 용돈을 효율적으로 활용하기 위해 계획을 짜기로 하였다.
 * - 현우는 앞으로 N일 동안 자신이 사용할 금액을 계산하였고, 돈을 펑펑 쓰지 않기 위해 정확히 M번만 통장에서 돈을 빼서 쓰기로 하였다.
 * - 현우는 통장에서 K원을 인출하며, 통장에서 뺀 돈으로 하루를 보낼 수 있으면 그대로 사용하고, 모자라게 되면 남은 금액은 통장에 집어넣고 다시 K원을 인출한다.
 * - 다만 현우는 M이라는 숫자를 좋아하기 때문에, 정확히 M번을 맞추기 위해서 남은 금액이 그날 사용할 금액보다 많더라도 남은 금액은 통장에 집어넣고 다시 K원을 인출할 수 있다.
 * - 현우는 돈을 아끼기 위해 인출 금액 K를 최소화하기로 하였다.
 * - 현우가 필요한 최소 금액 K를 계산하는 프로그램을 작성하시오.
 *
 * 입력
 * - 1번째 줄에는 N과 M이 공백으로 주어진다. (1 ≤ N ≤ 100,000, 1 ≤ M ≤ N)
 * - 2번째 줄부터 총 N개의 줄에는 현우가 i번째 날에 이용할 금액이 주어진다. (1 ≤ 금액 ≤ 10000)
 *
 * 출력
 * - 첫 번째 줄에 현우가 통장에서 인출해야 할 최소 금액 K를 출력한다.
 */
/// 고정된 수열이니까, 슬라이딩 윈도우 문제이지 않을까? X
/// 투포인터 문제일까 ?
public class _7_BOJ6236 {

    static int N;
    static int M;
    static int[] arr;
    static int min = Integer.MAX_VALUE;

    private static void input() throws IOException {
        /// 문제
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        /// 첫번째 줄
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        /// 값 입력
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            /// 배열에 값 넣기
            arr[i] = Integer.parseInt(st.nextToken());
        }

    }

    public static void main(String[] args) throws IOException {

        /// 입력
        input();

        /// 문제 풀이
        binarySearch();

        /// 결과 출력
        System.out.println(min);
    }

    /// 문제 풀이
    private static void binarySearch() {

        /// 제일 큰 값
        int low = Arrays.stream(arr).max().getAsInt();
        /// 합산
        int high = Arrays.stream(arr).sum();
        int cnt = 0;
        int mid;

        while (low <= high) {

            mid = (low + high) / 2;

            /// 계산하기
            cnt = calculate(mid);

            /// 인출 횟수가 많다면, 금액을 늘리기
            if (cnt <= M) {

                min = Math.min(min, mid);
                high = mid - 1;
            }

            /// 돈이 많으면 많을수록 인출횟수가 적다.
            /// 인출 횟수가 적다면, 금액을 줄이기.
            else if (cnt > M){
                low = mid + 1;
            }
        }

    }

    /// 계산하기
    private static int calculate(int price) {

        int cnt = 1;

        /// 1차 값은 받은 값
        int checkPrice = price;

        /// 몇 번이 출력되는지 체크
        /// i번째 일에 사용하는 금액으로 계산
        for (int i =0; i<N; i++) {

            /// 잔액에서 가격을 뺸 것
            checkPrice -= arr[i];

            /// 잔액에서 가격을 뺸 것으로 사용 불가능하다면 인출
            if (checkPrice < 0) {

                /// 인출 횟수 증가
                cnt++;

                /// 인출된 가격으로 변경
                checkPrice = price;

                /// 인출된 가격에서 뺀 값을 다시 리턴
                checkPrice -= arr[i];
            }

            /// 잔액에서 가격을 뺸 것으로 사용가능하다면 패스
            else if (checkPrice >= 0) {
            }

        }

        return cnt;
    }
}
