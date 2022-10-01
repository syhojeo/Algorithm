package stackAndQueue;

/*
    문제 설명
    일반적인 프린터는 인쇄 요청이 들어온 순서대로 인쇄합니다. 그렇기 때문에 중요한 문서가 나중에 인쇄될 수 있습니다. 이런 문제를 보완하기 위해 중요도가 높은 문서를 먼저 인쇄하는 프린터를 개발했습니다. 이 새롭게 개발한 프린터는 아래와 같은 방식으로 인쇄 작업을 수행합니다.

    1. 인쇄 대기목록의 가장 앞에 있는 문서(J)를 대기목록에서 꺼냅니다.
    2. 나머지 인쇄 대기목록에서 J보다 중요도가 높은 문서가 한 개라도 존재하면 J를 대기목록의 가장 마지막에 넣습니다.
    3. 그렇지 않으면 J를 인쇄합니다.
    예를 들어, 4개의 문서(A, B, C, D)가 순서대로 인쇄 대기목록에 있고 중요도가 2 1 3 2 라면 C D A B 순으로 인쇄하게 됩니다.

    내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 알고 싶습니다. 위의 예에서 C는 1번째로, A는 3번째로 인쇄됩니다.

    현재 대기목록에 있는 문서의 중요도가 순서대로 담긴 배열 priorities와 내가 인쇄를 요청한 문서가 현재 대기목록의 어떤 위치에 있는지를 알려주는 location이 매개변수로 주어질 때, 내가 인쇄를 요청한 문서가 몇 번째로 인쇄되는지 return 하도록 solution 함수를 작성해주세요.

    제한사항
    현재 대기목록에는 1개 이상 100개 이하의 문서가 있습니다.
    인쇄 작업의 중요도는 1~9로 표현하며 숫자가 클수록 중요하다는 뜻입니다.
    location은 0 이상 (현재 대기목록에 있는 작업 수 - 1) 이하의 값을 가지며 대기목록의 가장 앞에 있으면 0, 두 번째에 있으면 1로 표현합니다.
    입출력 예
    priorities	location	return
    [2, 1, 3, 2]	2	1
    [1, 1, 9, 1, 1, 1]	0	5
    입출력 예 설명
    예제 #1

    문제에 나온 예와 같습니다.

    예제 #2

    6개의 문서(A, B, C, D, E, F)가 인쇄 대기목록에 있고 중요도가 1 1 9 1 1 1 이므로 C D E F A B 순으로 인쇄합니다.
 */

/*
    문제 분석
    Stack을 시뮬레이션을 돌려보면 몇번째로 해야할지 알 수 있다 하지만 stack으로 시뮬을 돌린다면 문서의 양이 많을때
    시간복잡도가 너무 증가한다는 단점이 있다

    때문에 배열을 탐색하며 한번에 할 수 있도록 수식윽 짜는게 더 좋을것이라고 생각된다


    해결 방법
    location의 인쇄 차례
    = location 보다 높은 우선순위 개수
    + location 앞에 있는 같은 우선순위의 개수
    + 마지막에 인쇄될 높은 우선순위의 위치 뒤에 있는 같은 우선순위의 개수
    + 1

    Step1.
    먼저 프린트하려는 위치의 우선순위가 몇인지 확인한다

    Step2.
    priorities 배열을 location 위치까지
    location 보다 높은 우선순위 count
    location 과 같은 우선순위 count

    Step3.
    location 위치 뒤에서 location 우선순위보다 높은 우선순위 중 가장 마지막으로 높은 작업의끝까지
    location 보다 높은 우선순위 count

    but 가장 마지막으로 높은 작업의 위치를 미리 어떻게 아는가?... 2번탐색을 해야 하는가?...

    = 2번 탐색을 하는데 하나는 뒤에서부터 탐색해서 마지막으로 우선순위가 높은 작업의 위치를 찾는다?

    최소값을 찾는 방식으로 뒤에서부터 lcoation 위치까지 탐색하여 location 보다 높은 최소 우선순위의 마지막 위치를 찾는다
    ---------------------------------------------------------해결------------------------------------------------

    1. 그러려면 뒤에서부터 location 위치까지 중에서 location 보다 높지만 최소우선순위(x)가 몇인지 찾는다

    2. 뒤에서부터 탐색하여 가장 먼저 만나는 x값의 위치(y)를 찾는다

    3. y의 위치로부터 끝까지 location과 우선순위가 같은 작업의 개수를 센다 (Step4)

    Step4.
    마지막까지 location과 같 우선순위 count

    Step5.
    count 한 값 모두 합하고 + 1 하여 리턴

    ----------------불가능 우선순위 처리할때마다 매번 위치가 변하기 때문에 첫 배열만보고 판단하는 알고리즘을 만들어낼 수 없다---------

    힙 사용할것!

    Step1.
    배열을 탐색하여 location에 위치한 우선순위보다 높은 값들을 모두 알아낸다

    Step2.
    heap 에 모든 배열값을 넣는다

    Step3.
    heap에서 값을 뽑은 다음
    가장 높은 우선순위를 기준으로 아닐경우 뒤로보낸다
    같을경우 +1

    Step4.
    한바퀴를 다돌고 다음 우선순위 기준으로 위의 알고리즘 반복
    만약 location의 우선순위까지 기준우선순위가 왔을 경우 해당 lo
    ----------------힙 사용해도 되지만 다른 방법 사용------------------
    최종 방법

    Step1.
    location의 우선순위보다 높은 우선순위 중 가장 낮은 값 찾기

    Step2.
    존재한다면 location(l) 기준으로 앞(x), 뒤(y) 각각 가장 가까운 위치 찾기

    1구간  x      2구간       l      3구간      y        4구간

    Step3.
    1구간  x      2구간       l      3구간      y        4구간
    각 구간별 count 하기

    1, 4 구간의 경우 l과 같은 우선순위는 count하지 않고 높은 우선순위만 count 한다
    2, 3 구간의 경우 l과 같은 우선순위까지 포함하여 높은 우선순위를 count 한다

    Step4.
    count + 1(location 순서 포함) 하여 값을 리턴한다
 */

/*public class Printer {

    public int solution(int[] priorities, int location) {

        int answer = 0;
        int targetPriority = priorities[location];
        //Step1. location의 우선순위보다 높은 우선순위 중 가장 낮은 값 찾기
        int minimumPriority = findMinimumPriority(priorities, location);

        if (minimumPriority == 10) {
            for (int i = 0; i < location; i++) {
                if (priorities[i] == targetPriority) {
                    answer++;
                }
            }
            return answer + 1;
        }
        //Step2. 존재한다면 location(l) 기준으로 앞(x), 뒤(y) 각각 가장 가까운 위치 찾기
        answer += countFrontLocation(priorities, location, minimumPriority);
        answer += countBackLocation(priorities, location, minimumPriority);
        
        // +1 은 location 자체 순위 추가
        return answer + 1;
    }

    private int countFrontLocation(int[] priorities, int location, int minimumPriority) {

        int count = 0;
        int targetPriority = priorities[location];
        boolean checkMinimumPriority = false;
        int minimumPriorityLocation = location - 1;

        //location 앞쪽에 location보다 우선순위가 높은 작업이 있는지 확인
        for (int i = 0; i > location; i++) {
            if (priorities[i] == minimumPriority) {
                checkMinimumPriority = true;
            }
        }
        if (checkMinimumPriority) {
            for (int i = location - 1; priorities[i] != minimumPriority; i--) {
                minimumPriorityLocation = i - 1;
                if (priorities[i] >= targetPriority) {
                    count++;
                }
            }
            for (int i = minimumPriorityLocation; i >= 0; i--) {
                if (priorities[i] > targetPriority) {
                    count++;
                }
            }
        } else {
            for (int i = 0; i < location; i++) {
                if (priorities[i] > targetPriority) {
                    count++;
                }
            }
        }
        
        return count;
    }

    private int countBackLocation(int[] priorities, int location, int minimumPriority) {

        int count = 0;
        int targetPriority = priorities[location];
        boolean checkMinimumPriority = false;
        int minimumPriorityLocation = priorities.length - 1;

        //뒤쪽에 minimumPriority가 있는지 없는지
        for (int i = priorities.length - 1; i > location; i--) {
            if (priorities[i] == minimumPriority) {
                checkMinimumPriority = true;
            }
        }

        if (checkMinimumPriority) {
            for (int i = priorities.length - 1; priorities[i] != minimumPriority; i--) {
                minimumPriorityLocation = i - 1;
                if (priorities[i] >= targetPriority) {
                    count++;
                }
            }
            for (int i = minimumPriorityLocation; i > location; i--) {
                if (priorities[i] > targetPriority) {
                    count++;
                }
            }
        } else {
            for (int i = priorities.length - 1; i > location; i--) {
                if (priorities[i] > targetPriority) {
                    count++;
                }
            }
        }

        return count;
    }


    private int findMinimumPriority(int[] priorities, int location) {
        int minimumPriority = 10;
        int targetPriority = priorities[location];

        for (int i = 0; i < priorities.length; i++) {
            if (priorities[i] > targetPriority) {
                if (priorities[i] < minimumPriority) {
                    minimumPriority = priorities[i];
                }
            }
        }

        return minimumPriority;
    }
}*/

//-----------------------------------------실패-------------------------------------------------------------------------

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

//다른사람 풀이
/*
    queue를 이용하여 시뮬레이션을 돌리는 방식으로 문제를 풀었다
    1. priority를 정렬한뒤 뒤에서부터 탐색하여 가장 우선순위가 높은 값부터 하나씩 비교가능하도록 만들어둔 뒤
    2. queue에서 값을 하나씩 빼면서 비교하는 방식으로 시뮬레이션을 돌렸다
    3. 또한 location값을 계속 기록하면서 우리가 계산해야할 작업의 위치를 기록하였다 
    
    순서에 관련된 알고리즘에 대해 queue 자료구조를 적용하여 순서에 대한 시뮬레이션을 돌릴 수 있도록 설계하였다
    
    순서에 관한 알고리즘일 경우 queue와 stack 자료구조를 적용할것을 고려해봐야겠다
 */
public class Printer {

    public int solution(int[] priorities, int location) {
        int answer = 0;
        int l = location;

        Queue<Integer> que = new LinkedList<Integer>();
        for(int i : priorities){
            que.add(i);
        }

        Arrays.sort(priorities);
        int size = priorities.length-1;

        while(!que.isEmpty()){
            Integer i = que.poll();
            if(i == priorities[size - answer]){
                answer++;
                l--;
                if(l <0)
                    break;
            }else{
                que.add(i);
                l--;
                if(l<0)
                    l=que.size()-1;
            }
        }

        return answer;
    }
}
