/*기능개발
        문제 설명
        프로그래머스 팀에서는 기능 개선 작업을 수행 중입니다. 각 기능은 진도가 100%일 때 서비스에 반영할 수 있습니다.

        또, 각 기능의 개발속도는 모두 다르기 때문에 뒤에 있는 기능이 앞에 있는 기능보다 먼저 개발될 수 있고, 이때 뒤에 있는 기능은 앞에 있는 기능이 배포될 때 함께 배포됩니다.

        먼저 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열 progresses와 각 작업의 개발 속도가 적힌 정수 배열 speeds가 주어질 때 각 배포마다 몇 개의 기능이 배포되는지를 return 하도록 solution 함수를 완성하세요.

        제한 사항
        작업의 개수(progresses, speeds배열의 길이)는 100개 이하입니다.
        작업 진도는 100 미만의 자연수입니다.
        작업 속도는 100 이하의 자연수입니다.
        배포는 하루에 한 번만 할 수 있으며, 하루의 끝에 이루어진다고 가정합니다. 예를 들어 진도율이 95%인 작업의 개발 속도가 하루에 4%라면 배포는 2일 뒤에 이루어집니다.
        입출력 예
        progresses	speeds	return
        [93, 30, 55]	[1, 30, 5]	[2, 1]
        [95, 90, 99, 99, 80, 99]	[1, 1, 1, 1, 1, 1]	[1, 3, 2]
        입출력 예 설명
        입출력 예 #1
        첫 번째 기능은 93% 완료되어 있고 하루에 1%씩 작업이 가능하므로 7일간 작업 후 배포가 가능합니다.
        두 번째 기능은 30%가 완료되어 있고 하루에 30%씩 작업이 가능하므로 3일간 작업 후 배포가 가능합니다. 하지만 이전 첫 번째 기능이 아직 완성된 상태가 아니기 때문에 첫 번째 기능이 배포되는 7일째 배포됩니다.
        세 번째 기능은 55%가 완료되어 있고 하루에 5%씩 작업이 가능하므로 9일간 작업 후 배포가 가능합니다.

        따라서 7일째에 2개의 기능, 9일째에 1개의 기능이 배포됩니다.

        입출력 예 #2
        모든 기능이 하루에 1%씩 작업이 가능하므로, 작업이 끝나기까지 남은 일수는 각각 5일, 10일, 1일, 1일, 20일, 1일입니다. 어떤 기능이 먼저 완성되었더라도 앞에 있는 모든 기능이 완성되지 않으면 배포가 불가능합니다.

        따라서 5일째에 1개의 기능, 10일째에 3개의 기능, 20일째에 2개의 기능이 배포됩니다.*/

package stackAndQueue;

import java.util.ArrayList;

/*
   문제 해결
   먼저 들어간 순서 즉 배열의 첫번째 원소부터 작업이 완료되었는지 순서대로 확인해야하기 때문에 Queue 자료구조를 쓰는것이 옳다고 판단된다

   Step1. 배열을 Queue에 모두 넣는다
   Step2. Queue의 가장 앞 원소를 꺼내 진도가 끝날때까지 날짜를 증가시킨다
   Step3. 그다음 Queue를 꺼내 날짜기준으로 진도가 끝났는지 확인한다
   Step4. 진도가 끝나지 않은 작업을 만날때까지 Step3을 반복한 뒤 Queue에서 꺼낸 작업의 수를 기록한다
   Step5. 기록한 값을 answer에 넣어 제출한다

    but 그냥 배열로 하는게 나아보임
*/


//Queue 사용 안함
public class FunctionDevelopment {

    public int[] solution(int[] progresses, int[] speeds) {

        TaskInfo taskInfo = new TaskInfo();
        ArrayList<Integer> answerList = new ArrayList<>();
        int tempDistributeNumber;

        while (true) {
            tempDistributeNumber = taskInfo.distributeNumber;
            checkingDistributeTask(progresses, speeds, taskInfo);
            taskInfo.days++;
            if (tempDistributeNumber != taskInfo.distributeNumber) {
                answerList.add(taskInfo.distributeNumber);
                taskInfo.distributeNumber = 0;
            }
            if (taskInfo.taskNumber >= progresses.length)
                break;
        }

        int[] answer = new int[answerList.size()];
        for (int i = 0; i < answerList.size(); i++) {
            answer[i] = answerList.get(i);
        }
        return answer;
    }

    void checkingDistributeTask(int[] progresses, int[] speeds, TaskInfo taskInfo) {
        if (progresses[taskInfo.taskNumber] + (taskInfo.days * speeds[taskInfo.taskNumber]) >= 100) {
            taskInfo.distributeNumber++;
            taskInfo.taskNumber++;
            if (taskInfo.taskNumber >= progresses.length)
                return;
            checkingDistributeTask(progresses, speeds, taskInfo);
        }
    }

    class TaskInfo {
        private int days = 1;
        private int taskNumber = 0;
        private int distributeNumber = 0;
    }
}
