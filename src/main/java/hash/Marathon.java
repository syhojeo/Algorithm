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
