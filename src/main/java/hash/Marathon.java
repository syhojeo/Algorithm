/*문제 설명 (프로그래머스 해시 1단계)
    수많은 마라톤 선수들이 마라톤에 참여하였습니다. 단 한 명의 선수를 제외하고는 모든 선수가 마라톤을 완주하였습니다.

    마라톤에 참여한 선수들의 이름이 담긴 배열 participant와 완주한 선수들의 이름이 담긴 배열 completion이 주어질 때, 완주하지 못한 선수의 이름을 return 하도록 solution 함수를 작성해주세요.

    제한사항
    마라톤 경기에 참여한 선수의 수는 1명 이상 100,000명 이하입니다.
    completion의 길이는 participant의 길이보다 1 작습니다.
    참가자의 이름은 1개 이상 20개 이하의 알파벳 소문자로 이루어져 있습니다.
    참가자 중에는 동명이인이 있을 수 있습니다.
    입출력 예
    participant	completion	return
    ["leo", "kiki", "eden"]	["eden", "kiki"]	"leo"
    ["marina", "josipa", "nikola", "vinko", "filipa"]	["josipa", "filipa", "marina", "nikola"]	"vinko"
    ["mislav", "stanko", "mislav", "ana"]	["stanko", "ana", "mislav"]	"mislav"
    입출력 예 설명
    예제 #1
    "leo"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.

    예제 #2
    "vinko"는 참여자 명단에는 있지만, 완주자 명단에는 없기 때문에 완주하지 못했습니다.

    예제 #3
    "mislav"는 참여자 명단에는 두 명이 있지만, 완주자 명단에는 한 명밖에 없기 때문에 한명은 완주하지 못했습니다.*/
package hash;

import java.util.HashMap;
import java.util.Map;

public class Marathon {

    public String solution(String[] participant, String[] completion) {
        String answer = "";
        //hashmap 에 완주자 명단 넣기
        HashMap<String, Integer> completiontHashMap = new HashMap<String, Integer>();
        for (int i = 0; i < completion.length; i++) {
            //동명이인 value값으로 카운트
            //Hashmap.getOrDefault : 찾는 키가 존재한다면 찾는 키의 값을 반환하고, 없다면 기본값을 반환하는 메서드
            completiontHashMap.put(completion[i],
                completiontHashMap.getOrDefault(completion[i], 0) + 1);
        }
        //hashmap 에서 완주하지 못한 인원 탐색
        for (int i = 0; i < participant.length; i++) {
            if (completiontHashMap.get(participant[i]) == null) {
                answer = participant[i];
                break;
            }
            //동명이인인지 확인
            completiontHashMap.put(participant[i], completiontHashMap.get(participant[i]) - 1);
            if (completiontHashMap.get(participant[i]) == -1) {
                answer = participant[i];
                break;
            }
        }
        return answer;
    }

    //다른사람 풀이
/*    public String solution(String[] participant, String[] completion) {
        String answer = "";
        HashMap<String, Integer> hm = new HashMap<>();
        //Hashmap.getOrDefault : 찾는 키가 존재한다면 찾는 키의 값을 반환하고, 없다면 기본값을 반환하는 메서드
        for (String player : participant) hm.put(player, hm.getOrDefault(player, 0) + 1);
        for (String player : completion) hm.put(player, hm.get(player) -1);
        //key, value 두가지값을 모두 가져올때는 keySet()로 읽어들이는 것보다 entrySet()이 더좋다
        //둘다 getKey, getvalue 를 시행하게 되지만
        // 내부에서는 entrySet의 경우 entry의 속성값을 바로 가져오는 반면에
        //keySet()후 HashMap.get(key) 를 하는경우 hashmap에서 찾아야되서 hashcode(), equals() 등의 함수를 내부에서
        //실행하여 시간을 더사용한다
        //때문에 key, value를 모두 get해와야하는 경우에는 entrySet을 사용하는것을 권장한다

//        for (String key : hm.keySet()){
//            if (hm.get(key) != 0) {
//                answer = key;
//            }
//        }

        for (Map.Entry<String, Integer> entry : hm.entrySet()) {
            if (entry.getValue() != 0) {
                answer = entry.getKey();
                break;
            }
        }
        return answer;
    }*/
}
