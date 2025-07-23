package com.test.part1.ch7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * N(5 ≤ N ≤ 1,000)개의 자연수들로 이루어진 집합 U가 있다.
 * 이 중에서 적당히 세 수를 골랐을 때, 그 세 수의 합 d도 U안에 포함되는 경우가 있을 수 있다.
 * 이러한 경우들 중에서, 가장 큰 d를 찾으라.
 * 예를 들어 {2, 3, 5, 10, 18}와 같은 집합이 있다고 하자. 2+3+5 = 10이 되고, 이 수는 집합에 포함된다.
 * 하지만 3+5+10 = 18이 되고, 이 경우가 세 수의 합이 가장 커지는 경우이다.
 *
 *
 * 입력
 * 첫째 줄에 자연수 N이 주어진다. 다음 N개의 줄에 차례로 U의 원소가 하나씩 주어진다.
 * 주어진 U는 집합이 되므로 입력되는 두 수가 같아서는 안 된다.
 * U의 원소는 200,000,000보다 작거나 같은 자연수이다.
 * 답이 항상 존재하는 경우만 입력으로 주어진다.
 *
 * 입력
 * 5
 * 2
 * 3
 * 5
 * 10
 * 18
 */
public class _2_BOJ2295 {

    static int[] arr;
    static int window = 3;
    static boolean[] exists = new boolean[200000001];

    public static void main(String[] args) throws Exception {

        /// 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        /// 첫번째 줄 입력
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        /// 배열 생성 및 값 넣기
        arr = new int[n];

        /// u의 원소
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
            exists[arr[i]] = true;
        }

        /// 정렬
        Arrays.sort(arr);

        /// 세 수를 골랐을 때, 제일 큰 값이 나오도록 하는 것
        /// 슬라이딩 윈도우(window 사이즈는 3)
        /// Deque또는 투포인터 슬라이딩 윈도우

        int L = 0;
        int R = 0;
        int sum = 0;
        int size = 0;
        int max = Integer.MIN_VALUE;

        /// 슬라이딩 윈도우
        /// 해당 값도 집합안에 포함되었는지 체크
        while (R < n) {

            size = R - L + 1;

            /// 1칸,2칸의 윈도우라면
            if (size <= window) {

                /// 3칸일때만 최대 값 비교
                if (size == window) {
                    sum += arr[R];

                    /// 실제로 집합에 존재하는 값만 가능
                    if (exists[sum]){
                        max = Math.max(max, sum);

                    }
                } else {
                    sum += arr[R];
                }

                /// 우측으로 증가 시킨다.
                R++;
            }
            /// 4칸,5칸 이상의의 윈도우라면
            else if (size > window) {

                /// 왼쪽 값 삭제하기
                sum -= arr[L];

                L++;
            }
        }

        System.out.println(max);

    }
}
