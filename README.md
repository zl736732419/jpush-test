JPush框架封装使用指南
-------------------
### 您需要做的
1. 选用消息接收端平台，请使用<code>PlatformEnum</code> 枚举类给出的几种平台进行消息发送
2. 构造消息推送对象<code>JpushMessage</code>，其中包括alert,msg(通知和消息)两种类型的消息参数设定，
该类提供了几种Builder分别用于构造alert,msg或者alert/msg共存的消息
3. 采用<code>JpushSender</code>进行消息发送
4. 使用Jpush原生对象PushResult接受消息发送结果
#### 下面是一个简单的代码实例
<pre>
    // 同时发送msg,alert给andriod,ios终端设备
    // 1.选择消息发送的终端平台
    PlatformEnum platformEnum = PlatformEnum.ANDROID_IOS;
    // 2.构造消息对象
    JpushMessage message = JpushMessage.newBuilder()
            .withAlert(alert)
            .withAlertTitle(alertTitle)
            .withAlertExtras(extras)
            .withIosAlertBadge(2)
            .withIosAlertSound("happy")
            .withMsg(msg)
            .withMsgExtras(msgExtras).build();
            
    // 3.构造消息发送器
    JpushSender sender = JpushSender.getSender();
    // 4.发送消息并获取消息发送结果
    PushResult result = sender.sendMessage(params);
</pre>

## 更多实例请参考src/test/java/com/zheng/msg/MsgTest.java类