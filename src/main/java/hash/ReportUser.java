/*
문제 설명
신입사원 무지는 게시판 불량 이용자를 신고하고 처리 결과를 메일로 발송하는 시스템을 개발하려 합니다. 무지가 개발하려는 시스템은 다음과 같습니다.

각 유저는 한 번에 한 명의 유저를 신고할 수 있습니다.
신고 횟수에 제한은 없습니다. 서로 다른 유저를 계속해서 신고할 수 있습니다.
한 유저를 여러 번 신고할 수도 있지만, 동일한 유저에 대한 신고 횟수는 1회로 처리됩니다.
k번 이상 신고된 유저는 게시판 이용이 정지되며, 해당 유저를 신고한 모든 유저에게 정지 사실을 메일로 발송합니다.
유저가 신고한 모든 내용을 취합하여 마지막에 한꺼번에 게시판 이용 정지를 시키면서 정지 메일을 발송합니다.
다음은 전체 유저 목록이 ["muzi", "frodo", "apeach", "neo"]이고, k = 2(즉, 2번 이상 신고당하면 이용 정지)인 경우의 예시입니다.

유저 ID	유저가 신고한 ID	설명
"muzi"	"frodo"	"muzi"가 "frodo"를 신고했습니다.
"apeach"	"frodo"	"apeach"가 "frodo"를 신고했습니다.
"frodo"	"neo"	"frodo"가 "neo"를 신고했습니다.
"muzi"	"neo"	"muzi"가 "neo"를 신고했습니다.
"apeach"	"muzi"	"apeach"가 "muzi"를 신고했습니다.
각 유저별로 신고당한 횟수는 다음과 같습니다.

유저 ID	신고당한 횟수
"muzi"	1
"frodo"	2
"apeach"	0
"neo"	2
위 예시에서는 2번 이상 신고당한 "frodo"와 "neo"의 게시판 이용이 정지됩니다. 이때, 각 유저별로 신고한 아이디와 정지된 아이디를 정리하면 다음과 같습니다.

유저 ID	유저가 신고한 ID	정지된 ID
"muzi"	["frodo", "neo"]	["frodo", "neo"]
"frodo"	["neo"]	["neo"]
"apeach"	["muzi", "frodo"]	["frodo"]
"neo"	없음	없음
따라서 "muzi"는 처리 결과 메일을 2회, "frodo"와 "apeach"는 각각 처리 결과 메일을 1회 받게 됩니다.

이용자의 ID가 담긴 문자열 배열 id_list, 각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열 report, 정지 기준이 되는 신고 횟수 k가 매개변수로 주어질 때, 각 유저별로 처리 결과 메일을 받은 횟수를 배열에 담아 return 하도록 solution 함수를 완성해주세요.

제한사항
2 ≤ id_list의 길이 ≤ 1,000
1 ≤ id_list의 원소 길이 ≤ 10
id_list의 원소는 이용자의 id를 나타내는 문자열이며 알파벳 소문자로만 이루어져 있습니다.
id_list에는 같은 아이디가 중복해서 들어있지 않습니다.
1 ≤ report의 길이 ≤ 200,000
3 ≤ report의 원소 길이 ≤ 21
report의 원소는 "이용자id 신고한id"형태의 문자열입니다.
예를 들어 "muzi frodo"의 경우 "muzi"가 "frodo"를 신고했다는 의미입니다.
id는 알파벳 소문자로만 이루어져 있습니다.
이용자id와 신고한id는 공백(스페이스)하나로 구분되어 있습니다.
자기 자신을 신고하는 경우는 없습니다.
1 ≤ k ≤ 200, k는 자연수입니다.
return 하는 배열은 id_list에 담긴 id 순서대로 각 유저가 받은 결과 메일 수를 담으면 됩니다.
입출력 예
id_list	report	k	result
["muzi", "frodo", "apeach", "neo"]	["muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"]	2	[2,1,1,0]
["con", "ryan"]	["ryan con", "ryan con", "ryan con", "ryan con"]	3	[0,0]
입출력 예 설명
입출력 예 #1

문제의 예시와 같습니다.

입출력 예 #2

"ryan"이 "con"을 4번 신고했으나, 주어진 조건에 따라 한 유저가 같은 유저를 여러 번 신고한 경우는 신고 횟수 1회로 처리합니다. 따라서 "con"은 1회 신고당했습니다. 3번 이상 신고당한 이용자는 없으며, "con"과 "ryan"은 결과 메일을 받지 않습니다. 따라서 [0, 0]을 return 합니다.
 */
package hash;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Iterator;

//1. report를 순회하며 신고된 유저 - 신고자 로된 Map만들기
//2. 신고된 유저당 신고자 수를 계산하여 정지된 유저 추리기
//3. 맨처음 맵에서 정지된 유저에 있는 신고자들을 확인하여 신고자당 전달해줄 정지유저 수 집계
//4. 집계된 값을 answer에 넣고 리턴하기

class ReportUser {

    public int[] solution(String[] id_list, String[] report, int k) {
        int[] answer = new int[id_list.length];

        //1. report를 순회하며 신고된 유저 - 신고자(Set형태로) 로된 Map만들기
        Map<String, HashSet<String>> reportMap = new HashMap<>();
        for (String reportImf : report) {
            String[] reportUser = reportImf.split(" ");
            //처음 넣을경우 HashSet생성
            if (!reportMap.containsKey(reportUser[1])) {
                reportMap.put(reportUser[1], new HashSet<String>());
            }
            //Set에 신고한 유저 넣기
            reportMap.get(reportUser[1]).add(reportUser[0]);
        }

        //2,3. 신고된 유저당 신고자 수를 계산하여 정지된 유저 추리기
        //Map을 순회하며 신고된 유저의 value, 즉 Set의 size를 구하면 신고된 유저당 신고자수를 구할 수 있다
        Map<String, Integer> answerMap = new HashMap<>();
        for (Map.Entry<String, HashSet<String>> reportImf : reportMap.entrySet()) {
            //해당 유저가 정지사유가 맞다면
            if (reportImf.getValue().size() >= k) {
                //set(정지된 사람을 신고한사람)을 하나씩 불러오면서 answer Map에 신고한 사람당 정지가 성공한 수를 하나식 증가
                Iterator<String> itr = reportImf.getValue().iterator();
                while (itr.hasNext()) {
                    String reportUser = itr.next();
                    //answerMap 해당 유저 정지 성공횟수 1씩증가
                    answerMap.put(reportUser, answerMap.getOrDefault(reportUser, 0) + 1);
                }
            }
        }
        //3. 집계된 값을 answer에 넣고 리턴하기
        for (int i = 0; i < id_list.length; i++) {
            //정지된 정보를 넘겨줘야하는 유저만 값을 넣기
            //넘겨줄 정보가없는 (0) 값을 가진 유저에게 answerMap.get을 하게되면
            //int 형에 null값이 들어가면서 에러가 발생한다
            if (answerMap.containsKey(id_list[i])) {
                answer[i] = answerMap.get(id_list[i]);
            }
        }
        return answer;
    }
}
