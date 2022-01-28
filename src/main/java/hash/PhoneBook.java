/*문제 설명 (프로그래머스 해시 2단계)
    전화번호부에 적힌 전화번호 중, 한 번호가 다른 번호의 접두어인 경우가 있는지 확인하려 합니다.
    전화번호가 다음과 같을 경우, 구조대 전화번호는 영석이의 전화번호의 접두사입니다.

    구조대 : 119
    박준영 : 97 674 223
    지영석 : 11 9552 4421
    전화번호부에 적힌 전화번호를 담은 배열 phone_book 이 solution 함수의 매개변수로 주어질 때, 어떤 번호가 다른 번호의 접두어인 경우가 있으면 false를 그렇지 않으면 true를 return 하도록 solution 함수를 작성해주세요.

    제한 사항
    phone_book의 길이는 1 이상 1,000,000 이하입니다.
    각 전화번호의 길이는 1 이상 20 이하입니다.
    같은 전화번호가 중복해서 들어있지 않습니다.
    입출력 예제
    phone_book	return
    ["119", "97674223", "1195524421"]	false
    ["123","456","789"]	true
    ["12","123","1235","567","88"]	false
    입출력 예 설명
    입출력 예 #1
    앞에서 설명한 예와 같습니다.

    입출력 예 #2
    한 번호가 다른 번호의 접두사인 경우가 없으므로, 답은 true입니다.

    입출력 예 #3
    첫 번째 전화번호, “12”가 두 번째 전화번호 “123”의 접두사입니다. 따라서 답은 false입니다.*/
package hash;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class PhoneBook {

/*    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        String[] compare = phone_book;

        for (int i = 0; i < phone_book.length; i++) {
            for (int j = i + 1; j < compare.length; j++) {
                //이 조건문을 넣지 않으면 substring에서 문제가 발생한다 뿐만아니라 속도도 증가 시킬수 있다
                if (phone_book[i].length() >= compare[j].length()) {
                    break;
                } else {
                    if (phone_book[i].equals(compare[j].substring(0, phone_book[i].length()))) {
                        return false;
                    } else {
                        break;
                    }
                }
            }
        }
        return answer;
    }*/
// hash로 푼 다른사람 풀이 containsKey를 사용하여 간단하게 비교하였다 하지만 대부분의 로직이 배열과 비슷하다
    //다만 정렬을 하지않고 풀었기 때문에 속도가 좀더 빠르다
    public boolean solution(String[] phoneBook) {
        boolean answer = true;

        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i < phoneBook.length; i++) {
            map.put(phoneBook[i], i);
        }
        for(int i = 0; i < phoneBook.length; i++) {
            for(int j = 0; j < phoneBook[i].length(); j++) {
                if(map.containsKey(phoneBook[i].substring(0,j))) {
                    answer = false;
                    return answer;
                }
            }
        }
        return answer;
    }
}