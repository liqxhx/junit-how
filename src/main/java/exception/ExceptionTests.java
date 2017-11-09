package exception;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * junit对异常的测试用法
 */
public class ExceptionTests {
    // 这种写法看上去和实现类的写法很相似，当没有异常被抛出的时候fail方法会被调用，测试失败
    @Test
    public void testFail() {
        try {
            new ArrayList<Object>().get(0);
            fail("不会被执行");
        } catch (IndexOutOfBoundsException anIndexOutOfBoundsException) {
            assertThat(anIndexOutOfBoundsException.getMessage(), is("Index: 0, Size: 0"));
        }

    }


    /*
        这种写法看上去简单了一些，
        但是它有一个潜在的问题：
        当被标记的这个测试方法中的任何一个操作抛出了相应的异常时，这个测试就会通过。
        这就意味着有可能抛出异常的地方并不是我们期望的那个操作。
        虽然这种情况可以在写test case的时候人为的避免，
        但是还是有更好的方法来测试异常抛出。*/
    @Test(expected = IndexOutOfBoundsException.class)
    public void testAnno() {
        new ArrayList<Object>().get(0);
    }


    /*
         这种方法除了可以指定期望抛出的异常类型之外还可以指定在抛出异常时希望同时给出的异常信息。
         它需要在测试之前使用Rule标记来指定一个ExpectedException，
         并在测试相应操作之前指定期望的Exception类型（如IndexOutOfBoundException.class）*/
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testExceptionRule() throws IndexOutOfBoundsException {
        List<Object> list = new ArrayList<Object>();
        thrown.expect(IndexOutOfBoundsException.class);
        thrown.expectMessage("Index: 0, Size: 0");
        list.get(0); // execution will never get past this line
    }

}
