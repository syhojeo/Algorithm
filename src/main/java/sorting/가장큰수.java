package sorting;

/*
    가장 큰 수
    문제 설명
    0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.

    예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.

    0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.

    제한 사항
    numbers의 길이는 1 이상 100,000 이하입니다.
    numbers의 원소는 0 이상 1,000 이하입니다.
    정답이 너무 클 수 있으니 문자열로 바꾸어 return 합니다.
    입출력 예
    numbers	return
    [6, 10, 2]	"6210"
    [3, 30, 34, 5, 9]	"9534330"
 */

/*
    문제분석
    가장큰숫자는 앞자리가 가장큰숫자를 넣는법이다
    하지만 앞자리가 같다면 뒷자리까지 확인해주어야한다
    int형으로 하기에는 비교가 어렵고
    numbers 각각의 원소를 String형으로 변환후 하나씩 비교하며 우선순위를 정해줘야한다
 */

import java.util.*;

/*
    코드 설계
    Step1. int형 배열 numbers 를 String[] 로 변환해준다
    Step2. 변환된 String numbers를 Arrays.sort을 해주는데 Comparator의 compare 메서드를 Override해서 정렬한다
    Step3. Override 할때 앞자리부터 비교해가며 선행이 후행보다 작으면 자리를 옮긴다 (내림차순)
    Step4. 다만 비교할때 String의 bounds of index Exception을 어떻게 처리할지 예외처리를 꼭! 해줘야할것이다
    Step5. 정렬된 문자열을 합하면 결과가 나온다

    3 330 33330
    3 33330 330

    3 33330
    33330 3

    3 330 33330
 */
public class 가장큰수 {

    public String solution(int[] numbers) {
        String answer = "";
        //Step1. int형 배열 numbers 를 String[] 로 변환해준다
        String[] convertedNumbers = new String[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            convertedNumbers[i] = String.valueOf(numbers[i]);
        }

        /*List<Integer> convertedNumbers = new ArrayList<>();
        for (int number : numbers) {
            convertedNumbers.add(number);
        }*/

        /*Collections.sort(convertedNumbers, (a, b) ->{
            String as = String.valueOf(a), bs = String.valueOf(b);
            return -Integer.compare(Integer.parseInt(as + bs), Integer.parseInt(bs + as));
        });*/

        //Step2. 변환된 String numbers를 Arrays.sort을 해주는데 Comparator의 compare 메서드를 Override해서 정렬한다
        /*Arrays.sort(convertedNumbers, new Comparator<String>() {
            //Step3. Override 할때 앞자리부터 비교해가며 선행이 후행보다 작으면 자리를 옮긴다 (내림차순)
            @Override
            public int compare(String o1, String o2) {
                return -(o1 + o2).compareTo(o2 + o1);
            }
        });*/

        Arrays.sort(convertedNumbers, (o1, o2) -> {
            //Step3. Override 할때 앞자리부터 비교해가며 선행이 후행보다 작으면 자리를 옮긴다 (내림차순)
            return -(o1 + o2).compareTo(o2 + o1);
        });
        //Step5. 정렬된 문자열을 합하면 결과가 나온다
        StringBuilder sb = new StringBuilder();
        for (String convertedNumber : convertedNumbers) {
            sb.append(convertedNumber);
        }
        answer = sb.toString();
        if (answer.charAt(0) == '0') {
            answer = "0";
        }
        return answer;
    }
}

/*
    정확도에서는 문제가 없었는데 시간복잡도에서 무너져내린 역대급 개같은 문제였다
    이 문제에서 꼭!!! 알아가야할점
    
    1. Arrays.sort 의 Comparator에서 compare를 override 할때 if문을 써서 return 을 하게되면 시간복잡도가 정말로 많이 올라간다
    때문에 이번 문제와 같이 단순 크고 작은것에 대한 것은 두수를 빼거나 String의 compareTo 메서드를 사용해서 비교하는게 훠~얼씬 빠르다
    이것이 차이가 없을것이라고 생각한 나의 큰 착오였다

    2. 또한 Comparator의 compare 메서드를 override하여 정렬 기준을 정해주는것보다 람다식을 이용해서 곧바로 원하는 값을 리턴해주는
    방식이 성능이 더 좋게 나왔다
    아마 Arrays.sort의 내부에 compare 메서드가 있을텐데 이를 오버라이딩하는것 자체가 리소스 낭비이기 때문이지 않을까 싶다
    
    3. 문자열을 합치는 ex) str1 = "123", str2 = "456" 을 하려할때
    단순 덧셈 str3 = str1 + str2 를 하는것보다
    
    StringBuilder sb = new StringBuilder();
    sb.append(str1);
    sb.append(str2);
    str3 = sb.toString();
    즉, StringBuilder를 사용하는것이 훠~얼씬더 빠르다 진짜로 이건 진짜 성능이 눈에 띄게 좋아진다
    
    진짜 애를 많이 썩인 문제였지만 훌륭한 문제였다 내가 짠 코드가 아마 커트라인에서 딱 통과하지 못하는 코드였던것 같다
    로컬에서 돌렸을때는 정확도나  속도가 별로 차이 나지 않았기 때문이다 그런데 이문제를 해결하기위해서 정답인 코드들과 비교하는 과정에서
    좀더 빠른 방법들을 알게된 좋은 문제였다
    
    궁금한점 
    1. Arrays.sort와 collections.sort 는 성능차이가 있는가?
    2. Arrays.sort의 경우 Comparator의 compare 메서드를 Override하지 않고 람다를 이용해서 1, 0, -1 만을 리턴해줬는데
    어떻게 이것이 정렬이 된것인지, 또한 Override를 했을때 왜 성능이 안좋아지는지 Arrays.sort의 정확한 정렬 매커니즘에 대한 공부가
    필요하다

    1. Arrays.sort와 collections.sort 는 성능차이가 있는가?
    Arrays.sort()
    정렬방법: DualPivotQuicksort
    평균 : O(nlog(n)) / 최악 : O(n^2)


    Collections.sort()
    정렬 방법: TimeSort (삽입정렬과 합병정렬을 결합한 정렬)
    평균, 최악 : O(nlog(n))

    웬만하면 Collections.sort()를 사용하는 방법을 추천한다 최악의 경우 nlog(n) 이 n^2 보다 더 빠르기 때문이다
    하지만 배열을 Collections.sort() 사용하기 위해 Collection 자료구조형으로 바꿔줘야하기 때문에 때에 맞게 사용하면 될 것 같다

    2. 모르겠다 람다를 쓰는게 오버라이딩 하는것보다 빠르고 if 를 써서 서로의값을 비교하는것 보다 String.compareTo 를 쓰는것보다 빠르다
    람다식은 지연연산을 지원해서 퍼포먼스가 뛰어나다고 한다
    또한 람다식은 애초에 익명함수이기 때문에 Comparator가 오는자리에 익명함수를 쓰면 자동으로 Comparator에 대한 익명함수로 인식하고
    compare 메서드에 대한 오버라이드를 할 필요가 없게 되는것 같다
 */
