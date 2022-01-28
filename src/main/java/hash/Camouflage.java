/*문제 설명 (프로그래머스 해시 2단계)
    스파이들은 매일 다른 옷을 조합하여 입어 자신을 위장합니다.

    예를 들어 스파이가 가진 옷이 아래와 같고 오늘 스파이가 동그란 안경, 긴 코트, 파란색 티셔츠를 입었다면 다음날은 청바지를 추가로 입거나 동그란 안경 대신 검정 선글라스를 착용하거나 해야 합니다.

    종류	이름
    얼굴	동그란 안경, 검정 선글라스
    상의	파란색 티셔츠
    하의	청바지
    겉옷	긴 코트
    스파이가 가진 의상들이 담긴 2차원 배열 clothes가 주어질 때 서로 다른 옷의 조합의 수를 return 하도록 solution 함수를 작성해주세요.

    제한사항
    clothes의 각 행은 [의상의 이름, 의상의 종류]로 이루어져 있습니다.
    스파이가 가진 의상의 수는 1개 이상 30개 이하입니다.
    같은 이름을 가진 의상은 존재하지 않습니다.
    clothes의 모든 원소는 문자열로 이루어져 있습니다.
    모든 문자열의 길이는 1 이상 20 이하인 자연수이고 알파벳 소문자 또는 '_' 로만 이루어져 있습니다.
    스파이는 하루에 최소 한 개의 의상은 입습니다.
    입출력 예
    clothes	return
    [["yellowhat", "headgear"], ["bluesunglasses", "eyewear"], ["green_turban", "headgear"]]	5
    [["crowmask", "face"], ["bluesunglasses", "face"], ["smoky_makeup", "face"]]	3
    입출력 예 설명
    예제 #1
    headgear에 해당하는 의상이 yellow_hat, green_turban이고 eyewear에 해당하는 의상이 blue_sunglasses이므로 아래와 같이 5개의 조합이 가능합니다.

    1. yellow_hat
    2. blue_sunglasses
    3. green_turban
    4. yellow_hat + blue_sunglasses
    5. green_turban + blue_sunglasses
    예제 #2
    face에 해당하는 의상이 crow_mask, blue_sunglasses, smoky_makeup이므로 아래와 같이 3개의 조합이 가능합니다.

    1. crow_mask
    2. blue_sunglasses
    3. smoky_makeup*/
package hash;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Camouflage {
    //문제풀이 실패
/*    public int solution(String[][] clothes) {
        int answer = 0;
        int num = 0;
        int caseNum;
        int allClothesNum = 0;

        HashMap<String, Integer> ch = new HashMap<>();

        //헤시에 넣기 key: clothType, value: clothNum
        for (String[] key : clothes) {
            ch.put(key[1], ch.getOrDefault(key[1],0) + 1);
            //전체 옷의 개수 카운팅
            allClothesNum++;
        }

        //각 옷의 종류 당 옷의 개수 배열로 뽑기 (인자값들에 대한 경우의 수 계산이 해시보다 쉽기 때문)
        int clothTypePerNum[] = new int[ch.size()];
        for (HashMap.Entry<String, Integer> entry : ch.entrySet()) {
            clothTypePerNum[num] = entry.getValue();
            num++;
        }

        //경우의 수 구하기
        //step1. 옷을 하나만 입을때의 경우의 수 구하기
        answer += allClothesNum;

        //옷을 두개 이상 입을 때의 경우의 수 구하기
        for (int i = 0; i < clothTypePerNum.length - 1; i++) { //마지막값은 경우의 수 구할 필요가 없다
            caseNum = clothTypePerNum[i];
            for (int j = i + 1; j < clothTypePerNum.length; j++) {
                caseNum *= clothTypePerNum[j];
            }
            answer += caseNum;
        }
        return answer;
    }*/

//다른사람 풀이
    //각각의 경우의 수를 곱하기전 + 1을해서 옷을 안입는 경우 또한 경우의 수로 생각해서 넣어줬다
    //다만 모두 안입는 경우의 수가 있기 때문에 마지막 answer 값에 -1을 해준다
    public int solution(String[][] clothes) {
        int answer = 1;
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<clothes.length; i++){
            String key = clothes[i][1];
            if(!map.containsKey(key)) {
                map.put(key, 1);
            } else {
                map.put(key, map.get(key) + 1);
            }
        }
        Iterator<Integer> it = map.values().iterator();
        while(it.hasNext()) {
            answer *= it.next().intValue()+1;
        }
        return answer-1;
    }
}
