import org.junit.Test;
import java.util.List;

public class test {
    @Test
    public void test1() {
        List<String> a = Boggle.solve(7, "exampleBoard.txt");
        for (String s : a) {
            System.out.println(s);
        }
    }


    @Test
    public void test2() {
        Boggle.dictPath = "trivial_words.txt";
        List<String> a = Boggle.solve(20, "exampleBoard2.txt");
        for (String s : a) {
            System.out.println(s);
        }
    }
    @Test
    public void test3() {
        Boggle.dictPath = "words.txt";
        List<String> a = Boggle.solve(7, "smallBoard.txt");
        for (String s : a) {
            System.out.println(s);
        }
    }
}
