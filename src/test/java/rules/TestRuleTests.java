package rules;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.rules.Timeout;
import org.junit.runner.Description;
import org.junit.runners.model.Statement;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 自定义规则
 * 重复100次测试方法
 * ref:http://blog.sina.com.cn/s/blog_6f6472410101dm1y.html
 */
public class TestRuleTests {

    @Rule
    public RepeatRule repeatRule = new RepeatRule();
    @Rule
    public Timeout timeoutRule = new Timeout(1000);

    static int i = 0;

    @Test
    @RepeatRule.Repeat(times = 100)
    public void testRepeat() {
        System.out.println("hello "+(i++));

    }
}


class RepeatRule implements TestRule {
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ java.lang.annotation.ElementType.METHOD })
    public @interface Repeat {
        public abstract int times();
    }

    private static class RepeatStatement extends Statement {

        private final int times;
        private final Statement statement;

        private RepeatStatement(int times, Statement statement) {
            this.times = times;
            this.statement = statement;
        }

        @Override
        public void evaluate() throws Throwable {
            for (int i = 0; i < times; i++) {
                statement.evaluate();
            }
        }
    }

    @Override
    public Statement apply(Statement statement, Description description) {
        Statement result = statement;
        Repeat repeat = description.getAnnotation(Repeat.class);
        if (repeat != null) {
            int times = repeat.times();
            result = new RepeatStatement(times, statement);
        }
        return result;
    }

}
