package ju.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 검열
 *
 * ne
 * lukanevolisarmu -> lukavolisarmu
 *
 * banana
 * babananananadeda -> deda
 *
 */
public class _3111_not {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] words = br.readLine().split("");
        String[] texts = br.readLine().split("");

        int wordsSize = words.length;
        String firstWord = words[0];
        String lastWord = words[wordsSize -1];

        Deque<Word> deque = new ArrayDeque<>();
        Deque<Word> leftDeque = new ArrayDeque<>();
        Deque<Word> rightDeque = new ArrayDeque<>();

        for (int i = 0; i < texts.length; i++) {
            deque.addLast(new Word(i, texts[i]));
        }

        boolean front = true;
        while (!deque.isEmpty()) {

            if (deque.size() < wordsSize) break;

            //앞에서부터인지 여부
            if (front) {
                Word popDeq = deque.removeFirst();
                //첫 단어 체크
                if (popDeq.getWord().equals(firstWord)) {
                    boolean sameFlag = true;
                    for (int i = 0; i < wordsSize; i++) {
                        if (!words[i].equals(texts[popDeq.getIdx() + i])) { //인덱스 접근시 이전값이 삭제되면 이상한 인덱스에 접근하게됨.
                            sameFlag = false;
                            break;
                        }
                    }

                    if (sameFlag) {
                        for (int i = 1; i < wordsSize; i++) {
                            deque.removeFirst();
                        }

                        // 같은 단어면 단어 삭제처리 후 leftDeque에 들어있는 값 다시 Deque에 넣어야함
                        while (!leftDeque.isEmpty()) {
                            deque.addFirst(leftDeque.removeLast());
                        }
                        front = !front;
                    } else {
                        leftDeque.addLast(popDeq);
                    }
                } else {
                    leftDeque.addLast(popDeq);
                }

            } else {
                Word popDeq = deque.removeLast();
                String word = popDeq.getWord();
                //마지막 단어 체크
                if (word.equals(lastWord)) {
                    boolean sameFlag = true;
                    //같은 단어 체크
                    for (int i = wordsSize - 1; i >= 0; i--) {
                        if (!words[wordsSize - 1 - i].equals(texts[popDeq.getIdx() - i])) {
                            sameFlag = false;
                            break;
                        }
                    }

                    if (sameFlag) {
                        for (int i = 1; i < wordsSize; i++) {
                            deque.removeLast().getWord();
                        }

                        // 같은 단어면 단어 삭제처리 후 righDeque에 들어있는 값 다시 Deque에 넣어야함
                        while (!rightDeque.isEmpty()) {
                            deque.addLast(rightDeque.removeLast());
                        }
                        front = !front;
                    } else {
                        rightDeque.addLast(popDeq);
                    }
                } else {
                    rightDeque.addLast(popDeq);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        //왼쪽 Deque가 있다면 왼쪽 단어부터
        while (!leftDeque.isEmpty()) {
            sb.append(leftDeque.removeFirst().getWord());
        }

        //왼쪽부터라면 뒤에 알파벳이 남아있고 오른쪽이라면 앞에 알파벳이 남아있음
        while (!deque.isEmpty()) {
            sb.append(deque.removeFirst().getWord());
        }

        //오른쪽 Deque가 있다면 오른쪽 처리
        while (!rightDeque.isEmpty()) {
            sb.append(rightDeque.removeLast().getWord());
        }

        System.out.println(sb);
    }

    static class Word {
        int idx;
        String word;

        public Word(int idx, String word) {
            this.idx = idx;
            this.word = word;
        }

        public int getIdx() {
            return idx;
        }

        public String getWord() {
            return word;
        }
    }
}
