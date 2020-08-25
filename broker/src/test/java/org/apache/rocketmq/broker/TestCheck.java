package org.apache.rocketmq.broker;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author lee
 **/
public class TestCheck {

    @Test
    public void test() {

        short A = 1, b = 1, c;

        // c = A + b;  编译错误： A + b 返回 int, c 为 short
        int a = 1;

        Assert.assertFalse(a == (a = 2));
    }
}
