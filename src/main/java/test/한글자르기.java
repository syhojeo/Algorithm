package test;

import java.nio.charset.StandardCharsets;

/*
    String 으로 "한글" 문자열이 들어왔을 경우
    String.substring(0, 1)을 하여 한글을 자르게되면 ㅎ ㅏ ㄴ ㄱ ㅡ ㄹ 이런식으로 잘리기 때문에 다른방법을 사용해야한다

    방법은 두가지가 있다

    방법1. String.charAt()과 StringBuilder를 이용한 자르기

    방법2. String.getBytes() 를 이용한 자르기
    https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=kibani&logNo=220517266396
    이방법의 경우 사용하는 이유가 있다 DB와 같이 원하는 byte 값을 맞춰야하는데 한글이 있는 경우 한글자당 1byte가 아니기 때문에
    바이트 수 기준으로 값을 자르는 방법이다

    UTF-8 은 한글 한글자에 3Byte -> unicode 사용
    EUC-KR 은 한글 한글자에 2Byte -> Extend Unix Code 사용
 */

public class 한글자르기 {

    public String test(String source) {

        //방법1. String.charAt()과 StringBuilder를 이용한 자르기
/*      String result;
        StringBuilder sb = new StringBuilder();

        sb.append(source.charAt(0));
        sb.append(source.charAt(1));
        result = sb.toString();*/


        //방법2. String.getBytes()를 이용한 자르기 (두글자씩)
        String result;
        StringBuffer sb = new StringBuffer(2);
        int cnt = 0;
        for(char ch : source.toCharArray()){
            cnt += String.valueOf(ch).getBytes().length;
            if(cnt > 6)
                break;
            sb.append(ch);
        }
        result =  sb.toString();

        return result;
    }
}
