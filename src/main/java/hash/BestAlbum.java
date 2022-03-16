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
    코드 구조 설계
    1. 장르당 곡순위 정에서 정렬
    2. 장르 순위정해서 정렬
    3. 정렬된 장르 순위별 곡 2개씩 뽑아서 넣기

    map 구조
    Map1<장르, Map2<고유번호 , 플레이 횟수>>
    Map3<장르, 총 플레이 회수>

    1. Map2의 value 기준으로 고유번호 정렬 (TreeMap과 Comparator 를 이용)
    2. Map3<장르, 총 플레이 회수> value 기준 정렬 (TreeMap과 Comparator를 이용)
    불가능 -> treeMap으론 value 기준 정렬불가 이중맵에서 ArrayList의 sort를 이용해야 정렬가능
    3. Map3의 장르를 Key값으로 삼아 Map2의 장르의 value를 호출하고 호출된 Map에서 가장 높은 2개의 고유번호 뽑아서
    answer에 저장


    TheeMap을 사용하여 value 기준 자동정렬과 오름차순이 아닌 내림차순 정렬을 통해서 정렬을 하는법을 공부할것

    TreeMap value값 기준 내림차순 정렬

    이중맵과 hashMap value 값기준 정렬 하는법
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


public class BestAlbum {
    //TreeMap을 통해 이중Map 의 value 정렬하기
    //["classic", "pop", "classic", "classic", "pop"]	[500, 600, 150, 800, 2500]	[4, 1, 3, 0]
    public int[] solution(String[] genres, int[] plays) {
        //1.Map1<장르, treeMap<플레이 횟수, 고유번호>> 정렬하기
        int[] answer = {};
        return answer;
    }

}
