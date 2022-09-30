package stackAndQueue;

/*
        올바른 괄호
        문제 설명
        괄호가 바르게 짝지어졌다는 것은 '(' 문자로 열렸으면 반드시 짝지어서 ')' 문자로 닫혀야 한다는 뜻입니다. 예를 들어

        "()()" 또는 "(())()" 는 올바른 괄호입니다.
        ")()(" 또는 "(()(" 는 올바르지 않은 괄호입니다.
        '(' 또는 ')' 로만 이루어진 문자열 s가 주어졌을 때, 문자열 s가 올바른 괄호이면 true를 return 하고, 올바르지 않은 괄호이면 false를 return 하는 solution 함수를 완성해 주세요.

        제한사항
        문자열 s의 길이 : 100,000 이하의 자연수
        문자열 s는 '(' 또는 ')' 로만 이루어져 있습니다.
        입출력 예
        s	answer
        "()()"	true
        "(())()"	true
        ")()("	false
        "(()("	false
        입출력 예 설명
        입출력 예 #1,2,3,4
        문제의 예시와 같습니다.

 */

/*
        문제 분석

        스택을 이용하면 간단한 풀이가 가능할 것 같다
        문자열을 분석하여 '(' 문자가 나오면 스택을 증가 시키고 ')' 문자가 나오면 스택을 감소시킨다
        다만 스택이 비어 있을때 ')'가 나온다면 문제가 있는 문자열이기 때문에 이 경우는 예외 처리해줘야한다

        Step.1
        반복문을 통한 문자열을 하나씩 분석

        Step.2
        스택이 비었는데 ')'가 나오는 경우 false 리턴

        Step.3
        반복문이 종료되었는데 만약 스택이 비어있지않다면 false 리턴

        Step.4
        Step2~3 에서 문제가 없었다면 true 리턴
 */

import java.util.Stack;

public class CorrectParenthesis {

    boolean solution(String s) {
        Stack<Integer> stack = new Stack<>();

        //Step.1
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.push(1);
            } else { // ')'
                //Step.2
                if (stack.isEmpty()) {
                    return false;
                }
                stack.pop();
            }
        }
        //Step.3
        if (!stack.isEmpty()) {
            return false;
        }
        //Step.4
        return true;
    }

}

/*
    스택을 상용하여 문제를 풀었지만 사실 스택이 아닌 count 변수를 두고 스택처럼 사용하는 방법도 좋다
    stack 자료구조 자체가 배열의 top의 위치를 세면서 하는 구조이기 때문에 사실상 같은 방법이다
    다만 stack을 사용했을때 코드의 직관성이 count 세는것보다 올라갈것 같다
 */


/*
    boolean solution(String s) {
            boolean answer = false;
            int count = 0;
            for(int i = 0; i<s.length();i++){
                if(s.charAt(i) == '('){
                    count++;
                }
                if(s.charAt(i) == ')'){
                    count--;
                }
                if(count < 0){
                    break;
                }
            }
            if(count == 0){
                answer = true;
            }

            return answer;
        }
 */