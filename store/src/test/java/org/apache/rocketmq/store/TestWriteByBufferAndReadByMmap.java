package org.apache.rocketmq.store;

import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author lee
 **/
public class TestWriteByBufferAndReadByMmap {


    @Test
    public void test() throws IOException {

        String workDir = System.getProperty("user.dir");
        File file = new File(new File(workDir), "test.log");

        if (!file.exists()) {
            file.createNewFile();
        }
        try (RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw")) {
            FileChannel fileChannel = randomAccessFile.getChannel();

            byte[] content = "test_write_and_read".getBytes();
            MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE, 0, content.length);

            ByteBuffer byteBuffer = ByteBuffer.wrap(content);
            fileChannel.position(0);
            fileChannel.write(byteBuffer);

            System.out.println("写入数据");

            ByteBuffer mp1 = mappedByteBuffer.slice();

            byte[] tmp = new byte[content.length];
            mp1.get(tmp);

            String readStr = new String(tmp);

            Assert.assertEquals("test_write_and_read", readStr);
        }
    }

}
