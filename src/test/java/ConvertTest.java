import com.blxt.utils.Converter;
import com.blxt.utils.check.CheckUtils;
import com.blxt.utils.network.IPTools;
import com.blxt.utils.network.ping.PingNet;
import com.blxt.utils.network.ping.PingNetEntity;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;

/**
 * @Author: Zhang.Jialei
 * @Date: 2020/9/25 9:41
 */
public class ConvertTest {

    @Test
    public void test() throws IOException {
        byte[] src = {0x10, 0x76, (byte) 0xFF};

//        System.out.println(IPTools.getLocalHostIpsIPv4());
//
//        PingNetEntity pingNetEntity = PingNet.ping("47.242.60.114");
//        if (pingNetEntity.isResult()) {
//            System.out.println("Ping测试" + pingNetEntity.getIp());
//            System.out.println("Ping测试, time=" + pingNetEntity.getPingTime());
//            System.out.println("Ping测试 " + pingNetEntity.isResult());
//        } else {
//            System.out.println("IP不存在");
//        }
//
//       boolean fal = IPTools.checkHostIP("centos.zhangjialei.cn", 26, 3000);
//        System.out.println("端口" + fal);
//
//        System.out.println("ping : " + InetAddress.getByName("127.0.0.1").isReachable(3000));
         CheckUtils.objectCheckNull(new File("E:/Documents/workspace/IotCloub/Mqtt/HiveMq-community/HiveMq4Heneng_Package/protocol2"), "协议根路径不得为空," , "1001", null);

        //  System.out.println(f[0] + "=" + f[1]); http://47.242.60.114/
    }
}
