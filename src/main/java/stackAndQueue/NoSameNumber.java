/*
    문제 설명
    배열 arr가 주어집니다. 배열 arr의 각 원소는 숫자 0부터 9까지로 이루어져 있습니다. 이때, 배열 arr에서 연속적으로 나타나는 숫자는 하나만 남기고 전부 제거하려고 합니다. 단, 제거된 후 남은 수들을 반환할 때는 배열 arr의 원소들의 순서를 유지해야 합니다. 예를 들면,

    arr = [1, 1, 3, 3, 0, 1, 1] 이면 [1, 3, 0, 1] 을 return 합니다.
    arr = [4, 4, 4, 3, 3] 이면 [4, 3] 을 return 합니다.
    배열 arr에서 연속적으로 나타나는 숫자는 제거하고 남은 수들을 return 하는 solution 함수를 완성해 주세요.

    제한사항
    배열 arr의 크기 : 1,000,000 이하의 자연수
    배열 arr의 원소의 크기 : 0보다 크거나 같고 9보다 작거나 같은 정수
    입출력 예
    arr	answer
    [1,1,3,3,0,1,1]	[1,3,0,1]
    [4,4,4,3,3]	[4,3]
    입출력 예 설명
    입출력 예 #1,2
    문제의 예시와 같습니다.

 */
package stackAndQueue;

import java.util.*;

/*
    Stack
    Stack<int> s = new Stack<int>();

    s.push(1);
    s.push(2);

    int result = s.pop(); //2
    result = s.pop(); //1
    -----------------------------------------------
    queue
    Queue<int> q = new Queue<int>();

    q.offer(1);
    q.offer(2);

    int result = q.poll(); // 1
    result = q.poll(); //2
 */


/*
    문제 해결
    1. 연속으로 중복된 수를 제거할것
    2. 제거된 후 남은 수를 리턴할때는 배열에 있던 순서 그대로 리턴할것

    순서 그대로 리턴해야하기때문에 자료구조를 queue를 사용하는것이 옳다고 판단된다

    Step1. queue 에 배열에 있는 값을 모두 넣기
    Step2. queue 에서 값을 하나씩 빼며 이전값이 같은지 확인하고, 중복된수가 있다면 배열 answer에 넣지 않는다
    
    수정
    answer의 크기를 설정하기 위해 queue에서 뺄때 중복확인을 하지 않고, queue에 넣을때 중복확인을 한다
    그후 queue의 크기를 통해 answer의 크기를 정의하고 queue에서 나오는 순서대로 answer에 저장한다
 */

public class NoSameNumber {

    public int[] solution(int[] arr) {
        Queue<Integer> q = new LinkedList<>();
        Integer temp = arr[0];
        q.offer(temp);

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != temp) {
                q.offer(arr[i]);
            }
            temp = arr[i];
        }

        int answerSize = q.size();
        int[] answer = new int[answerSize];

        for (int i = 0; i < answerSize; i++) {
            answer[i] = q.poll();
        }

        return answer;
    }

}
