package heap;

/*
    디스크 컨트롤러
    문제 설명
    하드디스크는 한 번에 하나의 작업만 수행할 수 있습니다. 디스크 컨트롤러를 구현하는 방법은 여러 가지가 있습니다. 가장 일반적인 방법은 요청이 들어온 순서대로 처리하는 것입니다.

    예를들어

    - 0ms 시점에 3ms가 소요되는 A작업 요청
    - 1ms 시점에 9ms가 소요되는 B작업 요청
    - 2ms 시점에 6ms가 소요되는 C작업 요청
    와 같은 요청이 들어왔습니다. 이를 그림으로 표현하면 아래와 같습니다.
    Screen Shot 2018-09-13 at 6.34.58 PM.png

    한 번에 하나의 요청만을 수행할 수 있기 때문에 각각의 작업을 요청받은 순서대로 처리하면 다음과 같이 처리 됩니다.
    Screen Shot 2018-09-13 at 6.38.52 PM.png

    - A: 3ms 시점에 작업 완료 (요청에서 종료까지 : 3ms)
    - B: 1ms부터 대기하다가, 3ms 시점에 작업을 시작해서 12ms 시점에 작업 완료(요청에서 종료까지 : 11ms)
    - C: 2ms부터 대기하다가, 12ms 시점에 작업을 시작해서 18ms 시점에 작업 완료(요청에서 종료까지 : 16ms)
    이 때 각 작업의 요청부터 종료까지 걸린 시간의 평균은 10ms(= (3 + 11 + 16) / 3)가 됩니다.

    하지만 A → C → B 순서대로 처리하면
    Screen Shot 2018-09-13 at 6.41.42 PM.png

    - A: 3ms 시점에 작업 완료(요청에서 종료까지 : 3ms)
    - C: 2ms부터 대기하다가, 3ms 시점에 작업을 시작해서 9ms 시점에 작업 완료(요청에서 종료까지 : 7ms)
    - B: 1ms부터 대기하다가, 9ms 시점에 작업을 시작해서 18ms 시점에 작업 완료(요청에서 종료까지 : 17ms)
    이렇게 A → C → B의 순서로 처리하면 각 작업의 요청부터 종료까지 걸린 시간의 평균은 9ms(= (3 + 7 + 17) / 3)가 됩니다.

    각 작업에 대해 [작업이 요청되는 시점, 작업의 소요시간]을 담은 2차원 배열 jobs가 매개변수로 주어질 때, 작업의 요청부터 종료까지 걸린 시간의 평균을 가장 줄이는 방법으로 처리하면 평균이 얼마가 되는지 return 하도록 solution 함수를 작성해주세요. (단, 소수점 이하의 수는 버립니다)

    제한 사항
    jobs의 길이는 1 이상 500 이하입니다.
    jobs의 각 행은 하나의 작업에 대한 [작업이 요청되는 시점, 작업의 소요시간] 입니다.
    각 작업에 대해 작업이 요청되는 시간은 0 이상 1,000 이하입니다.
    각 작업에 대해 작업의 소요시간은 1 이상 1,000 이하입니다.
    하드디스크가 작업을 수행하고 있지 않을 때에는 먼저 요청이 들어온 작업부터 처리합니다.
    입출력 예
    jobs	return
    [[0, 3], [1, 9], [2, 6]]	9
    입출력 예 설명
    문제에 주어진 예와 같습니다.

    0ms 시점에 3ms 걸리는 작업 요청이 들어옵니다.
    1ms 시점에 9ms 걸리는 작업 요청이 들어옵니다.
    2ms 시점에 6ms 걸리는 작업 요청이 들어옵니다.

 */

/*
    문제분석
    이 문제의 요점은 시간이 적게 걸리는 작업들을 먼저 처리해야하는것이 요점이다
    때문에 들어오는 작업 요청을 최소값 기준인 queue에 넣은 후 순서대로 뽑아서 작업을 하게하면 된다
 */

import java.util.*;

/*
    코드 설계
    Step1. 소요시간 / 작업요청순번을 저장할 객체 Pair를 만든다
    Step2. Pair에서 Tree에 들어갈때 정렬될 기준을 작성한다
    Step3. job을 작업 요청시간순으로 정렬한다 (treeMap or Arrays.sort 사용)
    Step4. 시간을 증가시키며 작업이 요청된 시간이 되면 소요시간/작업요청순번 으로 Pair에 넣고 우선순위큐에 집어넣는다 (대기열 큐)
    Step5. 시간을 증가시키며 현재 진행중인 작업이 완료되었는지 확인한다
    Step6. 작업이 완료되었으면 값을 계산해서 총소요시간에 더해주고, 애초에 작업중인것이 없다면 대기열큐에서 꺼내와서 넣어준다
 */
public class 디스크컨트롤러 {

    /*public int solution(int[][] jobs) {
        int totalTime = 0;
        int currentTime = 0;
        PriorityQueue<Pair> watingQueue = new PriorityQueue<>();
        Pair working = null;
        int workingTime = 0;
        int sortedMapkeyIndex = 0;
        //Step3. job을 작업 요청시간순으로 정렬한다 (treeMap or Arrays.sort 사용)
        Map<Integer, Integer> jobsMap = new HashMap<>();
        sortOrderNumber(jobs, jobsMap);
        Set<Integer> sortedMapkey = jobsMap.keySet();
        Iterator iterator = sortedMapkey.iterator();

        int key = (Integer)iterator.next();
        while (working != null || !watingQueue.isEmpty() || iterator.hasNext()) {
            //Step4. 시간을 증가시키며 작업이 요청된 시간이 되면 소요시간/작업요청순번 으로 Pair에 넣고 우선순위큐에 집어넣는다 (대기열 큐)
            if (currentTime == key) {
                watingQueue.add(new Pair(jobsMap.get(key), key));
                if (working == null) {
                    working = watingQueue.poll();
                }
                //더이상 watingQueue 에 넣을 작업이 없을떄
                if (!iterator.hasNext()) {
                    key = -1;
                } else {
                    key = (Integer)iterator.next();
                }
            }

            if (working == null) {
                currentTime++;
                continue;
            }
            //Step5. 시간을 증가시키며 현재 진행중인 작업이 완료되었는지 확인하고, 작업이 완료되었으면 값을 계산해서 총소요시간에 더해준다
            if (working.first == workingTime) {
                //작업종료
                totalTime += currentTime - working.second;
                //Step6. 작업중인것이 없다면 대기열큐에서 꺼내와서 넣어준다
                if (!watingQueue.isEmpty()) {
                    working = watingQueue.poll();
                    workingTime = 0;
                } else {
                    working = null;
                }
            }

            workingTime++;
            currentTime++;
        }

        return totalTime / jobs.length;
    }

    private void sortOrderNumber(int[][] jobs, Map<Integer, Integer> jobsMap) {
        for (int[] job : jobs) {
            jobsMap.put(job[0], job[1]);
        }
        Object[] mapkey = jobsMap.keySet().toArray();
        Arrays.sort(mapkey);
    }


    //Step1. 소요시간 / 작업요청순번을 저장할 객체 Pair를 만든다
    //Step2. Pair에서 Tree에 들어갈때 정렬될 기준을 작성한다

    //Pair를 만들고 이를 이용하여 우선순위 큐를 사용한다
    class Pair implements Comparable<Pair> {
        int first, second;

        Pair(int f, int s) {
            this.first = f;
            this.second = s;
        }

        //소요시간이 작은순서대로 + 소요시간이 같다면 일찍 들어온 순서대로
        public int compareTo(Pair pair) {
            if (this.first < pair.first) {
                return -1; // 오름차순 -> 최소힙
            }
            else if (this.first == pair.first) {//만약 같다면
                if (this.second < pair.second) {
                    return -1; //작은거먼저 (먼저들어온 순서대로)
                }
            }
            return 1;
        }
    }*/
    //틀림
    //--------------------다른사람풀이---------------------------
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[0] <= o2[0]) {
                    return -1;
                }
                return 1;
            }
        });

        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2) {
                if (o1[1] < o2[1]) {
                    return -1;
                }
                return 1;
            }
        });

        int time = 0;
        int index = 0;
        float answer = 0;

        while (true) {
            while (index < jobs.length && jobs[index][0] <= time) {
                queue.offer(jobs[index]);
                index++;
            }
            if (queue.size() == 0) {
                time = jobs[index][0];
                continue;
            }
            int[] job = queue.poll();
            time += job[1];
            answer += time - job[0];
            if (index == jobs.length && queue.size() == 0) {
                break;
            }
        }

        answer /= jobs.length;
        return (int) answer;
    }
}
/*
    다른사람풀이를 보았을때 놀랍게도 내가 짠 구조와 100퍼센트 같았다
    하지만 좀더 세련되게 코딩이 되어있었고 정렬하는 방식이 달랐다
    하지만 내 소스코드는 대부분이 시간초과가 났고, 틀린 부분도 존재한다

    왜 이런차이가 존재하채ㅡ는지 모르겠다

    다만 정렬하는 방법에 대해서 공부할 필요가 있어보인다
    특히 Comparator를 사용하여 compare 메서드를 재정의하는 방법으로 정렬하는 방법을 배워가자
 */

class Study{

    private void study(int jobs[][]) {
        //1. Arrays.sort를 이용한 정렬에서 comparator를 이용하여 정렬 기준 설정해주기
        //          정렬할 배열,
        Arrays.sort(jobs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //오름차순으로 정렬
                if (o1[0] <= o2[0]) {
                    return -1;
                }
                return 0;
            }
        });

        //2. Priority Queue 에 대해 Comparator 설정을 해두고 Priority Queue의 heap 이 최소힙인지 최대힙인지
        //  설정해준다

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                //작업 소요시간 오름차순
                if (o1[1] < o2[1]) {
                    return -1;
                } else if (o1[1] == o2[1]) {
                    //작업요청시간 오름차순
                    if (o1[0] <= o2[0]) {
                        return -1;
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            }
        });

    }
}

/*
    Comparable, Comparator
    Comparable 을 통한 정렬시 compareTo 메서드를 사용하여 정렬 기준을 설정한다

    Comparable이 없는 경우 Comparator를 사용하여 정렬기준을 설정한다
    Comparator의 경우 compare 메서드를 사용한다

    둘다
    o1 < o2 return -1
    o1 > o2 return 1
    의 경우 오름차순으로 정렬되게 된다
    -> 선행 원소가 후행원소보다 작을 경우 음수를 리턴
    -> 자리를 옮길지 말지에 대한 양, 음수 리턴이다 때문에 음수가 나오면 자리를 옮기지 않는다
    => 오름차순으로 인해 선행원소가 후행원소보다 작을경우 음수를 리턴해서 자리를 옮기지 않는다
 */