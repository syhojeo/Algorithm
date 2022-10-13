package sorting;

/*
    H-Index
    문제 설명
    H-Index는 과학자의 생산성과 영향력을 나타내는 지표입니다. 어느 과학자의 H-Index를 나타내는 값인 h를 구하려고 합니다. 위키백과1에 따르면, H-Index는 다음과 같이 구합니다.

    어떤 과학자가 발표한 논문 n편 중, h번 이상 인용된 논문이 h편 이상이고 나머지 논문이 h번 이하 인용되었다면 h의 최댓값이 이 과학자의 H-Index입니다.

    어떤 과학자가 발표한 논문의 인용 횟수를 담은 배열 citations가 매개변수로 주어질 때, 이 과학자의 H-Index를 return 하도록 solution 함수를 작성해주세요.

    제한사항
    과학자가 발표한 논문의 수는 1편 이상 1,000편 이하입니다.
    논문별 인용 횟수는 0회 이상 10,000회 이하입니다.
    입출력 예
    citations	return
    [3, 0, 6, 1, 5]	3
    입출력 예 설명
    이 과학자가 발표한 논문의 수는 5편이고, 그중 3편의 논문은 3회 이상 인용되었습니다. 그리고 나머지 2편의 논문은 3회 이하 인용되었기 때문에 이 과학자의 H-Index는 3입니다.
 */

/*
    문제 분석
    0 1 3 5 5 6
    n = 6
    가장 뒤부터
    6 일 경우 n - 현재 위치(5) = 1

    5일 경우 n - 3 = 2 -> 5와 같거나 크지않다

    3일 경우 n - 2 = 4 -> 3보다 크다

    정렬을 통해 순서대로 만들어주고
    뒤에서부터 확인을 해나간다
    전체 개수 - 현재값의 가장 낮은 인덱스 >= 현재값(h)
    라면 그값을 h로 리턴하면 된다
 */

import java.util.Arrays;

/*
    코드 설계
    Step1. Arrays.sort를 이용해 오름차순(default)로 정렬
    Step2. 배열의 뒤에서부터 h 이상인 값이 몇개인지 구한다
    Step3. 그 개수가 h이상인지 확인한다
    Step4. h 이상이라면 그값을 리턴
    Step5. 하나도 없는 경우에 대비해서 예외처리를 해줘야한다
 */
public class H_Index {

    public int solution(int[] citations) {

        //Step1. Arrays.sort를 이용해 오름차순(default)로 정렬
        Arrays.sort(citations);
        int h;
        int n = citations.length;
        int minIndex;
        /*
            h 이상인 값이 h개 이상인 h값을 찾아라
            h는 h개 이상이어야 하기 때문에 h = n 부터 h-- 해가면서 조건에 맞는 값을 찾는다
        */
        for (h = n; h > 0; h--) {
            //Step2. 배열의 뒤에서부터 h 이상인 값이 몇개인지 구한다
            minIndex = findMinIndex(citations, h, n);
            //Step3. 그 개수가 h이상인지 확인한다 , Step5. 하나도 없는 경우에 대비해서 예외처리를 해줘야한다 (-1)
            if (n - minIndex >= h && minIndex != -1) {
                //Step4. h 이상이라면 그값을 리턴
                return h;
            }
        }

        return 0;
    }

    private int findMinIndex(int[] citations, int h, int n) {
        int index = -1;
        for (int i = n - 1; i > -1; i--) {
            if (citations[i] >= h) {
                index = i;
            } else {
                break;
            }
        }
        //배열에 아무것도 안들어온 경우
        return index;
    }


    //다른사람 소스 코드
    /*
        public int solution(int[] citations) {
        Arrays.sort(citations);

        int max = 0;
        //0, 1, 2, 3, 4 => 2
        for(int i = citations.length-1; i > -1; i--){
            int min = (int)Math.min(citations[i], citations.length - i);
            4 1
            3 2
            2 3
            1 4
            0 5
            if(max < min) max = min;
        }

        return max;
    }
     */

    /*
        내가 짠 코드 보다 속도가 최대 2배 빨랐다
        코드 한줄에 닮긴 뜻을 알아내기가 너무어렵다
    */

    /*
        public int solution(int[] citations) {
            int answer = 0;

            Arrays.sort(citations); // 오름차순 정렬

            for(int i = citations.length - 1; i >= 0; i--) {
                if(citations.length - i >= citations[i]) break;
                answer++;
            }

            return answer;
        }
     */
    /*
        citations.length - i >= citations[i]
        ->               개수 >= 인용횟수 일때

     */
}

/*
    나는 h의 모든 경우의 수에 대한 반복문과 조건문을 준비했기 때문에 속도가 느릴 수 밖에 없었다

    하지만 다른사람의 풀이들을 보면 개수와 인용회수만을 인덱스를 줄여가면서 했기 때문에 속도가 더 빨랐다 (if문 차이)

    사실 이 문제의 핵심은 문제를 제대로 이해했느냐이다
    문제를 제대로 이해했다면 오름차순으로 논문의 개수가 키 포인트인것을 알아야 한다
    논문의 인용수는 사실상 논문의 개수만 넘으면 되기 때문에 개수를 기준으로 확인 하되, 해당 개수일떄 인용수가 개수의 크기만 넘으면 된다
    때문에 논문의 개수(n - index)가 인용회수(citations[index])를 넘는 순간 그값이 h의 최대값이 된다


    내가 짠 코드처럼 if문을 추가로 넣을 필요는 없었다

    결국 이 문제에서 중요했던점은 문제를 제대로 이해하고, 그 다음 좀 효율좋게 짜야한다는것이다
    다만 생각이 안날경우 내가 지금 짠것처럼 if문을 추가해서라도 구현을 해내는것이 좋을것이다

    이번문제를 통해서 문제를 제대로 이해하는것이 정말 중요하다는것을 알았고,
    또 예외처리 사항에 대해서 생각이 나면 그 부분을 꼭 상세히 예시까지 들어서 적어넣은 후 어느정도 로직이 완성되고,
    그 부분을 제대로 처리해야한다
 */


