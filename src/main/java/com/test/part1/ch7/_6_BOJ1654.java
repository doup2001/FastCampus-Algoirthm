package com.test.part1.ch7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 문제풀이
 * - 집에서 시간을 보내던 오영식은 박성원의 부름을 받고 급히 달려왔다. 박성원이 캠프 때 쓸 N개의 랜선을 만들어야 하는데 너무 바빠서 영식이에게 도움을 청했다.*
 * - 이미 오영식은 자체적으로 K개의 랜선을 가지고 있다. 그러나 K개의 랜선은 길이가 제각각이다.
 * - 박성원은 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶었기 때문에 K개의 랜선을 잘라서 만들어야 한다.
 * - 예를 들어 300cm 짜리 랜선에서 140cm 짜리 랜선을 두 개 잘라내면 20cm는 버려야 한다. (이미 자른 랜선은 붙일 수 없다.)
 * - 편의를 위해 랜선을 자르거나 만들 때 손실되는 길이는 없다고 가정하며, 기존의 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다고 가정하자.
 * - 그리고 자를 때는 항상 센티미터 단위로 정수길이만큼 자른다고 가정하자. N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다.
 * - 이때 만들 수 있는 최대 랜선의 길이를 구하는 프로그램을 작성하시오.
 *
 * 입력
 * - 첫째 줄에는 오영식이 이미 가지고 있는 랜선의 개수 K, 그리고 필요한 랜선의 개수 N이 입력된다.
 * - K는 1이상 10,000이하의 정수이고, N은 1이상 1,000,000이하의 정수이다. 그리고 항상 K ≦ N 이다.
 * - 그 후 K줄에 걸쳐 이미 가지고 있는 각 랜선의 길이가 센티미터 단위의 정수로 입력된다. 랜선의 길이는 231-1보다 작거나 같은 자연수이다
 *
 * 출력
 * 첫째 줄에 N개를 만들 수 있는 랜선의 최대 길이를 센티미터 단위의 정수로 출력한다.
 */
public class _6_BOJ1654 {

    static int K;
    static long N;
    static long[] arr;
    static long max = Integer.MIN_VALUE;

    private static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        /// 첫 줄 입력
        st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        /// 이미 존재하는 배열 입력
        arr = new long[K];

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    public static void main(String[] args) throws IOException {

        /// 값 입력
        input();

        /// 정렬
        Arrays.sort(arr);

        /// 함수 실행
        binarySearch();

        /// 값 출력
        System.out.println(max);

    }

    /// 바이너리 서치
    /// 최대 전선의 길이와 개수 출력
    static void binarySearch() {

        long low = 1;
        long high = arr[K - 1];
        long mid;
        /// 개수
        long cnt;

        while (low <= high) {

            mid = (low + high) / 2;

            /// 개수 계산하기
            cnt = calculate(mid);

            /// 개수가 N개보다 크거나 같으면,
            if (cnt>=N){

                /// 최댓 값 계산하기
                max = Math.max(max, mid);

                /// 다음도 계산
                low = mid + 1;
            }

            /// 개수가 N개보다 작으면,
            if (cnt < N) {

                high = mid - 1;
            }

        }

    }

    static long calculate(long h) {

        long cnt = 0;

        /// 모든 배열 계산하기
        for (long a : arr){

            cnt += (a / h);
        }

        return cnt;

    }
}
