/*
문제 설명 (프로그래머스 연습문제)
    스트리밍 사이트에서 장르 별로 가장 많이 재생된 노래를 두 개씩 모아 베스트 앨범을 출시하려 합니다. 노래는 고유 번호로 구분하며, 노래를 수록하는 기준은 다음과 같습니다.

    속한 노래가 많이 재생된 장르를 먼저 수록합니다.
    장르 내에서 많이 재생된 노래를 먼저 수록합니다.
    장르 내에서 재생 횟수가 같은 노래 중에서는 고유 번호가 낮은 노래를 먼저 수록합니다.
    노래의 장르를 나타내는 문자열 배열 genres와 노래별 재생 횟수를 나타내는 정수 배열 plays가 주어질 때, 베스트 앨범에 들어갈 노래의 고유 번호를 순서대로 return 하도록 solution 함수를 완성하세요.

    제한사항
    genres[i]는 고유번호가 i인 노래의 장르입니다.
    plays[i]는 고유번호가 i인 노래가 재생된 횟수입니다.
    genres와 plays의 길이는 같으며, 이는 1 이상 10,000 이하입니다.
    장르 종류는 100개 미만입니다.
    장르에 속한 곡이 하나라면, 하나의 곡만 선택합니다.
    모든 장르는 재생된 횟수가 다릅니다.
    입출력 예
    genres	plays	return
    ["classic", "pop", "classic", "classic", "pop"]	[500, 600, 150, 800, 2500]	[4, 1, 3, 0]
    입출력 예 설명
    classic 장르는 1,450회 재생되었으며, classic 노래는 다음과 같습니다.

    고유 번호 3: 800회 재생
    고유 번호 0: 500회 재생
    고유 번호 2: 150회 재생
    pop 장르는 3,100회 재생되었으며, pop 노래는 다음과 같습니다.

    고유 번호 4: 2,500회 재생
    고유 번호 1: 600회 재생
    따라서 pop 장르의 [4, 1]번 노래를 먼저, classic 장르의 [3, 0]번 노래를 그다음에 수록합니다.
    */
package hash;

/*
    요구 사항 분석
    1. 각 장르별 플레이 순위 구해서 순서대로 정렬
    2. 정렬된 장르에 플레이곡 2순위까지 넣기

    -> 해시맵에 (장르) : 총 플레이수
    -> 해시맵을 탐색하며 장르별로 2개씩 정렬

    단, 플레이횟수가 같은경우 고유번호 비교하는 조건문과 장르에 곡이 2개가 아닐경우를 고려해서 짜야할것
 */


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class BestAlbum {

    //time over
    public int[] solution(String[] genres, int[] plays) {
        //map을 저장하기 위해 Object형으로 value값을 저장
        HashMap<String, Object> genresMap = new HashMap<String, Object>(); //<장르, 곡정보>
        HashMap<String, Integer> playMap = new HashMap<String, Integer>(); // <장르, 총 장르 재생수>
        ArrayList<Integer> resultAL = new ArrayList<Integer>();

        //포문 돌려가며 HashMap 채우기
        for (int i = 0; i <genres.length; i++) {
            String key = genres[i];
            HashMap<Integer, Integer> infoMap; //곡 정보 : <곡 고유 번호, 재생횟수>

            //genresMap으로부터 get하기 위한 조건문 (genresMap에 key 값이 들어가 있는가?)
            if (genresMap.containsKey(key)) {
                //들어 있다면 genresMap안의 value(HashMap)값을 infoMap으로 가져온다
                infoMap = (HashMap<Integer, Integer>) genresMap.get(key);
            }
            else { //들어 있지 않다면 infoMap의 값을 비어있는 해시맵으로 초기화
                infoMap = new HashMap<Integer, Integer>(); //<곡정보, 재생횟수>
            }

            infoMap.put(i, plays[i]); //곡정보, 재생횟수 세팅
            genresMap.put(key, infoMap); //장르, map 타입 세팅

            //재생수
            //안에 초기값이 있다면
            if (playMap.containsKey(key)) {
                playMap.put(key, playMap.get(key) + plays[i]);
            }
            else { //초기값이 없다면
                playMap.put(key,plays[i]);
            }
        }

        int mCnt = 0;
        Iterator it = sortByValue(playMap).iterator();

        while(it.hasNext()){
            String key = (String)it.next();
            Iterator indexIt = sortByValue((HashMap<Integer, Integer>)genresMap.get(key)).iterator();
            int playsCnt = 0;

            while(indexIt.hasNext()){
                resultAL.add((int)indexIt.next());
                mCnt++;
                playsCnt++;
                if(playsCnt > 1) break;
            }
        }

        int[] answer = new int[resultAL.size()];

        for(int i = 0; i < resultAL.size(); i++){
            answer[i] = resultAL.get(i).intValue();
        }
        return answer;
    }

    private ArrayList sortByValue(final Map map) {
        ArrayList<Object> keyList = new ArrayList();
        keyList.addAll(map.keySet());

        Collections.sort(keyList, new Comparator() {
            public int compare(Object o1, Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);
                return ((Comparable) v2).compareTo(v1);
            }
        });

        return keyList;
    }


}
