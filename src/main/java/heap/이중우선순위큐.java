package heap;

/*
    이중우선순위큐
    문제 설명
    이중 우선순위 큐는 다음 연산을 할 수 있는 자료구조를 말합니다.

    명령어	수신 탑(높이)
    I 숫자	큐에 주어진 숫자를 삽입합니다.
    D 1	큐에서 최댓값을 삭제합니다.
    D -1	큐에서 최솟값을 삭제합니다.
    이중 우선순위 큐가 할 연산 operations가 매개변수로 주어질 때, 모든 연산을 처리한 후 큐가 비어있으면 [0,0] 비어있지 않으면 [최댓값, 최솟값]을 return 하도록 solution 함수를 구현해주세요.

    제한사항
    operations는 길이가 1 이상 1,000,000 이하인 문자열 배열입니다.
    operations의 원소는 큐가 수행할 연산을 나타냅니다.
    원소는 “명령어 데이터” 형식으로 주어집니다.- 최댓값/최솟값을 삭제하는 연산에서 최댓값/최솟값이 둘 이상인 경우, 하나만 삭제합니다.
    빈 큐에 데이터를 삭제하라는 연산이 주어질 경우, 해당 연산은 무시합니다.
    입출력 예
    operations	return
    ["I 16", "I -5643", "D -1", "D 1", "D 1", "I 123", "D -1"]	[0,0]
    ["I -45", "I 653", "D 1", "I -642", "I 45", "I 97", "D 1", "D -1", "I 333"]	[333, -45]
    입출력 예 설명
    입출력 예 #1

    16과 -5643을 삽입합니다.
    최솟값을 삭제합니다. -5643이 삭제되고 16이 남아있습니다.
    최댓값을 삭제합니다. 16이 삭제되고 이중 우선순위 큐는 비어있습니다.
    우선순위 큐가 비어있으므로 최댓값 삭제 연산이 무시됩니다.
    123을 삽입합니다.
    최솟값을 삭제합니다. 123이 삭제되고 이중 우선순위 큐는 비어있습니다.
    따라서 [0, 0]을 반환합니다.

    입출력 예 #2

    -45와 653을 삽입후 최댓값(653)을 삭제합니다. -45가 남아있습니다.
    -642, 45, 97을 삽입 후 최댓값(97), 최솟값(-642)을 삭제합니다. -45와 45가 남아있습니다.
    333을 삽입합니다.
    이중 우선순위 큐에 -45, 45, 333이 남아있으므로, [333, -45]를 반환합니다

 */

/*
    문제 분석
    I, D 을 split을 통해 문자열파싱을 잘할것
    최소값 최대값 모두를 알아야하기 때문에 우선순위큐를 두개 사용할것 (최소힙, 최대힙)
    만약 최대값이나 최소값이 같은게 들어왔을경우에는 하나만 삭제할것
 */

import java.util.Collections;
import java.util.PriorityQueue;

import static java.lang.Integer.max;

/*
    코드 설계
    Step1. 들어오는 문자열을 Split을 이용해 하나씩 파싱해나간다
    Step2. 최소힙, 최대힙 모두 만들고 I인 경우 둘 모두 값을 넣어준다
    Step3. "D 1"이 들어온경우 최대값에서 값을 뺴오고 해당값을 최소값에서도 빼 와준다 (D -1 일경우반대로)
    Step4. 만약 큐가 비어져있는데 삭제명령어가 올경우에는 무시한다
    Step5. 문자열파싱이 끝났을때 큐가 비어져있다면 {0, 0}을 반환하고, 비어있지않다면 {최대값, 최소값} 을 반환한다

 */
public class 이중우선순위큐 {

    /*public int[] solution(String[] operations) {

        int[] answer = new int[2];
        //Step2. 최소힙, 최대힙 모두 만들고 I인 경우 둘 모두 값을 넣어준다
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        //최소대힙 우선순위큐 만드는법 Collections.reverseOrder() 추가
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        String[] splitedOperation;
        int operand;
        String instruction;
        int maxNumber;
        int minNumber;

        for (String operation : operations) {
            splitedOperation = operation.split(" ");
            instruction = splitedOperation[0];
            operand = Integer.parseInt(splitedOperation[1]);
            if (instruction.equals("I")) {
                minHeap.offer(operand);
                maxHeap.offer(operand);
            } else if (instruction.equals("D") && !maxHeap.isEmpty()) {
                if (operand == 1) {
                    maxNumber = maxHeap.poll();
                    minHeap.remove(maxNumber);
                } else {
                    minNumber = minHeap.poll();
                    maxHeap.remove(minNumber);
                }
            }
        }

        if (maxHeap.isEmpty()) {
            answer[0] = 0;
            answer[1] = 0;
        } else {
            answer[0] = maxHeap.poll();
            answer[1] = minHeap.poll();
        }

        return answer;
    }*/

    //다른사람 풀이
    public int[] solution(String[] arguments) {
        MidV q = new MidV();

        for(int i = 0; i < arguments.length; i++){
            String[] commend = arguments[i].split(" ");

            int v = Integer.parseInt(commend[1]);
            if(commend[0].equals("I")){
                q.push(v);
            }else{
                switch (v){
                    case 1 : q.removeMax();
                        break;
                    case -1: q.removeMin();
                        break;
                }
            }
        }


        int[] aw = new int[]{q.getMaxValue(),q.getMinValue()};

        return aw;
    }
}

class MidV{
    private PriorityQueue<Integer> leftHeap;
    private PriorityQueue<Integer> rightHeap;

    public MidV(){
        leftHeap = new PriorityQueue<>(10,Collections.reverseOrder());//최대값;
        rightHeap = new PriorityQueue<>();//최소값
    }


    public void push(int v){
        leftHeap.add(v);
    }

    public void removeMax(){

        while(!rightHeap.isEmpty()){
            leftHeap.add(rightHeap.poll());
        }

        leftHeap.poll();
    }

    public void removeMin(){

        while(!leftHeap.isEmpty()){
            rightHeap.add(leftHeap.poll());
        }

        rightHeap.poll();
    }

    public int getMaxValue(){

        if(leftHeap.size() == 0 && rightHeap.size() == 0)
            return 0;

        while(!rightHeap.isEmpty()){
            leftHeap.add(rightHeap.poll());
        }

        return leftHeap.peek();
    }

    public int getMinValue(){

        if(leftHeap.size() == 0 && rightHeap.size() == 0)
            return 0;

        while(!leftHeap.isEmpty()){
            rightHeap.add(leftHeap.poll());
        }

        return rightHeap.peek();
    }
    /*
        문제가 쉬워서 금방 풀었지만 다른 사람 풀이 중 이 코드를 보고 반성하게 되었다
        이 문제를 풀면서 어떻게하면 C언어의 알고리즘 풀이와 같은 문제풀이법에서 벗어날 수 있을지 고민했었는데
        방법을 찾지 못해 하드코딩으로 문제를 풀었다

        하지만 이 문제풀이를 보면 완벽하다 이중우선순위큐 객체 자체를 만들었고
        여기서 각각의 동작들에 대한 메서드를 만듦으로써 완벽한 객체지향을 이루었다

        따라할 수 있을지는 모르겠지만 정말 많이 배웠다 좀 더 노력해봐야겠다
     */
}
