package stackAndQueue;
/*
    다리를 지나는 트럭
    문제 설명
    트럭 여러 대가 강을 가로지르는 일차선 다리를 정해진 순으로 건너려 합니다. 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 알아내야 합니다. 다리에는 트럭이 최대 bridge_length대 올라갈 수 있으며, 다리는 weight 이하까지의 무게를 견딜 수 있습니다. 단, 다리에 완전히 오르지 않은 트럭의 무게는 무시합니다.

    예를 들어, 트럭 2대가 올라갈 수 있고 무게를 10kg까지 견디는 다리가 있습니다. 무게가 [7, 4, 5, 6]kg인 트럭이 순서대로 최단 시간 안에 다리를 건너려면 다음과 같이 건너야 합니다.

    경과 시간	다리를 지난 트럭	다리를 건너는 트럭	대기 트럭
    0	[]	[]	[7,4,5,6]
    1~2	[]	[7]	[4,5,6]
    3	[7]	[4]	[5,6]
    4	[7]	[4,5]	[6]
    5	[7,4]	[5]	[6]
    6~7	[7,4,5]	[6]	[]
    8	[7,4,5,6]	[]	[]
    따라서, 모든 트럭이 다리를 지나려면 최소 8초가 걸립니다.

    solution 함수의 매개변수로 다리에 올라갈 수 있는 트럭 수 bridge_length, 다리가 견딜 수 있는 무게 weight, 트럭 별 무게 truck_weights가 주어집니다. 이때 모든 트럭이 다리를 건너려면 최소 몇 초가 걸리는지 return 하도록 solution 함수를 완성하세요.

    제한 조건
    bridge_length는 1 이상 10,000 이하입니다.
    weight는 1 이상 10,000 이하입니다.
    truck_weights의 길이는 1 이상 10,000 이하입니다.
    모든 트럭의 무게는 1 이상 weight 이하입니다.
    입출력 예
    bridge_length	weight	truck_weights	return
    2	10	[7,4,5,6]	8
    100	100	[10]	101
    100	100	[10,10,10,10,10,10,10,10,10,10]	110
 */

/*
    문제 분석
    1. 차례, 순서에 관한 알고리즘이기 때문에 queue와 stack 중 하나를 사용하면 좋을것같다
    2. 트럭에 대한 선입 선출 관련된 문제이기 때문에 queue 자료구조를 사용

    조건문을 통해 여러 조건을 잘 버무리면 어렵지 않게 풀 수 있는 문제 같다
    조건1. 다음 순서의 트럭에 다리에 올라가 있는 트럭무게를 더했을때 다리무게를 넘어서면 안된다 (트럭이 다리에 들어갈 수 있는가)
    조건2. 다리의 길이를 고려하여 현재 다리에 올라가있는 트럭무게를 빼줘야한다 (트럭이 다리에서 나올 수 있는가)
    다리의 현재 상태를 고려하는 queue를 쓴다? -> 다리에 트럭이 들어갈때마다 시간을 재는것을 만들어야한다

    이 두가지 조건을 충족할때 queue에서 값을 하나씩 빼면서 시뮬레이션을 진행하면 될것
 */

/*
    문제 풀이
    Step1.
    Step2. queue에서 값을 하나씩 빼서 조건1, 조건2를 충족하는지 확인한다 (모두 충족할 필요 없다)
    Step3. 충족할 경우 queue에서 뺀 값을 다리 상태에 추가하여 반영해준다
    Step4. queue가 비워지면 소요시간을 리턴해준다
 */

import java.util.LinkedList;
import java.util.Queue;

public class TruckPassBridge {

    public int solution(int bridge_length, int weight, int[] truck_weights) {

        Queue<Integer> truckQueue = new LinkedList<>();
        Queue<Integer> locationQueue = new LinkedList<>();

        int currentWeight = 0;
        int time = 1;
        int index = 0;

        while (index < truck_weights.length || !locationQueue.isEmpty()) {
            //queue가 비어있어서 조건식을 실행할 수 없을때 진행
            if (locationQueue.isEmpty()) {
                locationQueue.add(time);
                truckQueue.add(truck_weights[index]);
                currentWeight += truck_weights[index++];
            } else {
                //조건2. 트럭이 다리에서 빠져나올수 있는가
                if (time - locationQueue.peek() == bridge_length) {
                    //다리에서 트럭 빼기
                    currentWeight -= truckQueue.poll();
                    locationQueue.poll();
                }
                //index가 length보다 크거나 같으면 ArrayIndexOutOfBoundsException이 발생한다
                //index가 length보다 크거나 같으면 트럭을 집어넣을 필요가 없기 때문에 해당로직을 실행할 필요가 없다
                if (index < truck_weights.length) {
                    //조건1. 트럭이 다리에 들어갈 수 있는가?
                    if (currentWeight + truck_weights[index] <= weight) {
                        //다리에 트럭 집어넣기
                        truckQueue.add(truck_weights[index]);
                        currentWeight += truck_weights[index++];
                        locationQueue.add(time);
                    }
                }
            }
            time++;
        }
        return time - 1;
    }
}


/*
다른 사람풀이 
객체 지향을 위한 truck에 대한 완벽한 추상화가 되어있다
truck을 객체로 만들고 moving 메서드를 통해 트럭의 현재위치와 무게의 정보를 만들었다
해당 객체를 queue넣어 사용하며 이를 통해 두개의 queue를 만든 내 풀이 보다 시간, 공간복잡도에서 유리하다

또한 truckWeight 의 배열을 그대로 사용한 나와 다르게 이또한 queue를 만들어 사용함으로써 훨씬더 직관적인 코드를 구현하였다
내 코드같은경우 배열과 그에 대한 인덱스를 사용하여 조건에 대한 직관성이 떨어졌다 이 코드에선 watingQueue.isEmpty() 를 통해 직관성이 증가

class Solution {
    class Truck {
        int weight;
        int move;

        public Truck(int weight) {
            this.weight = weight;
            this.move = 1;
        }

        public void moving() {
            move++;
        }
    }

    public int solution(int bridgeLength, int weight, int[] truckWeights) {
        Queue<Truck> waitQ = new LinkedList<>();
        Queue<Truck> moveQ = new LinkedList<>();

        for (int t : truckWeights) {
            waitQ.offer(new Truck(t));
        }

        int answer = 0;
        int curWeight = 0;

        while (!waitQ.isEmpty() || !moveQ.isEmpty()) {
            answer++;

            if (moveQ.isEmpty()) {
                Truck t = waitQ.poll();
                curWeight += t.weight;
                moveQ.offer(t);
                continue;
            }

            for (Truck t : moveQ) {
                t.moving();
            }

            if (moveQ.peek().move > bridgeLength) {
                Truck t = moveQ.poll();
                curWeight -= t.weight;
            }

            if (!waitQ.isEmpty() && curWeight + waitQ.peek().weight <= weight) {
                Truck t = waitQ.poll();
                curWeight += t.weight;
                moveQ.offer(t);
            }
        }

        return answer;
    }
}
 */

/*
    이 문제는 생각보다 간단한 문제처럼 보였지만
    조건문을 설정하는데 있어서 하나하나 정교한 설계를 하지 못해서 테스트를 계속 실패했다
    
    코드를 다 짜고 테스트를 돌려봐야지만 부족한 설계가 보인다
    완벽한 설계는 할 수 없겠지만 설계단계에서 좀더 정교하게 설계를 해야할 필요가 있어보인다
    하지만 그러기엔 내 사고력이 너무 부족하다
 */