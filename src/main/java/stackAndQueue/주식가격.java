package stackAndQueue;
/*
    주식가격
    문제 설명
    초 단위로 기록된 주식가격이 담긴 배열 prices가 매개변수로 주어질 때, 가격이 떨어지지 않은 기간은 몇 초인지를 return 하도록 solution 함수를 완성하세요.

    제한사항
    prices의 각 가격은 1 이상 10,000 이하인 자연수입니다.
    prices의 길이는 2 이상 100,000 이하입니다.
    입출력 예
    prices	return
    [1, 2, 3, 2, 3]	[4, 3, 1, 1, 0]
    입출력 예 설명
    1초 시점의 ₩1은 끝까지 가격이 떨어지지 않았습니다.
    2초 시점의 ₩2은 끝까지 가격이 떨어지지 않았습니다.
    3초 시점의 ₩3은 1초뒤에 가격이 떨어집니다. 따라서 1초간 가격이 떨어지지 않은 것으로 봅니다.
    4초 시점의 ₩2은 1초간 가격이 떨어지지 않았습니다.
    5초 시점의 ₩3은 0초간 가격이 떨어지지 않았습니다.
 */

/*
    문제 분석
    모양새가 값이 비쌀수록 가격이 잘떨어진다 (선입 후출의 모양새를 가진다)
    스택을 이용하여 값을 넣고 바로 이전값과 비교할 수 있다

    순서대로 들어가되 바로 이전값을 사용해야할때(선입후출) 스택을 사용한다

    주식을 객체로 만들어 스택에 넣고 새로들어오는 값이 이전값보다 작으면 커지거나 같아질때까지 꺼내면서 확인한다
 */

import java.util.Stack;

/*
    코드 설계

    Step1.
    배열을 읽어들이며 각각의 stock 객체를생성하여 stack 저장한다

    Step2.
    저장할때 stack의 top값과 비교하여 top값 보다 같거나 클경우에만 저장한다

    Step3.
    만약 top보다 작을 경우에는 스택에 있는 이전값을 뽑아온다
    같거나 클때까지 계속 반복한다

    Step4.
    스택에서 이전값을 뽑은경우에는 그 주식의 location (배열에 있을때의 인덱스값)위치에 stack 에 저장되어있던 시간을 기록한다

    Step5.
    만약 배열을 끝까지 탐색을 했을 경우 내부 값을 확인하고 Step4 와 같이 Stack에 저장되어있던 시간을 기록한다

 */
public class 주식가격 {

    public class Stock {

        private int price;
        private int time;
        private int location;

        public Stock(int price, int time, int location) {
            this.price = price;
            this.time = time;
            this.location = location;
        }

        public int getLocation() {
            return location;
        }

        public int getTime() {
            return time;
        }

        public int getPrice() {
            return price;
        }
    }

    public int[] solution(int[] prices) {

        int[] answer = new int[prices.length];
        Stack<Stock> stockStack = new Stack<>();
        int currentTime = 1;

        //Step1. 배열을 읽어들이며 각각의 stock 객체를생성하여 stack 저장한다
        for(int i = 0; i < prices.length; i++) {
            Stock s = new Stock(prices[i], currentTime, i);

            if (stockStack.isEmpty()) {
                stockStack.push(s);
            } else {
                checkPushOrPop(answer, stockStack, currentTime, s);
            }
            currentTime++;
        }

        //Step5. 만약 배열을 끝까지 탐색을 했을 경우 스택 내부 값을 확인하고 Step4 와 같이 Stack에 저장되어있던 시간을 기록한다
        while (!stockStack.isEmpty()) {
            Stock s = stockStack.pop();
            //위에서 반복문이 끝날떄 currentTime++ 을 해줬기 때문에 -1 을 해줘야 한다
            answer[s.getLocation()] = currentTime - s.getTime() - 1;
        }

        return answer;
    }

    private void checkPushOrPop(int[] answer, Stack<Stock> stockStack, int currentTime, Stock s) {
        //Step2. 저장할때 stack의 top값과 비교하여 top값 보다 같거나 클경우에만 저장한다 (push)
        if (stockStack.peek().getPrice() <= s.getPrice()) {
            stockStack.push(s);
        } else {
            //Step3. 만약 top보다 작을 경우에는 스택에 있는 이전값을 뽑아온다 (pop)
            Stock topStock = stockStack.pop();
            //Step4.스택에서 이전값을 뽑은경우에는 그 주식의 location (배열에 있을때의 인덱스값)위치에 stack 에 저장되어있던 시간을 기록한다
            answer[topStock.getLocation()] = currentTime - topStock.getTime();
            
            //같거나 클때까지 반복하기 위해 재귀함수 사용
            //단, 스택이 비어있다면 비교를 할 수 없기 때문에 비어있지 않을 경우에만 재귀를 돌도록 한다
            if (!stockStack.isEmpty()) {
                checkPushOrPop(answer, stockStack, currentTime, s);
            } else {
                stockStack.push(s);
            }
        }
    }

}

/*
    순서가 필요한 문제일때 선입후출인 경우 Stack 자료구조, 선입선출인 경우 queue 자료구조를 사용하자

    Stack에 stock이라는 객체를 만들어 사용했고
    pop을할지 push를 할지에 대한 판단을 하는 메서드를 따로 빼서 재귀함수로 사용했기 때문에
    조금 더 무겁고 속도가 오래걸렸다

    다만 테스트를 충분히 통과할 성능을 가졌고
    이 코드가 다른 코드들 보다 훨씬 더 객체 지향적이고 가독성이 좋은 (유지보수 비용이 적은) 코드라고 생각된다

 */