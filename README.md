JPush框架封装使用指南
-------------------
###您需要做的
* 选用推送平台，请选用<code>PlatformEnum</code> 枚举给出的几种情况
* 构造消息推送消息<code>JpushMessage</code>，其中包括alert,msg(通知和消息)两种类型的消息，
使用该类提供的Builder进行构造
* 采用<code>JpushSender</code>进行消息发送

<pre>
    // 同时发送msg,alert给andriod,ios终端设备
    // 选择消息发送的终端平台
    PlatformEnum platformEnum = PlatformEnum.ANDROID_IOS;
    // 构造消息对象
    JpushMessage message = JpushMessage.newBuilder()
            .withAlert(alert)
            .withAlertTitle(alertTitle)
            .withAlertExtras(extras)
            .withIosAlertBadge(2)
            .withIosAlertSound("happy")
            .withMsg(msg)
            .withMsgExtras(msgExtras).build();
            
    // 构造消息发送器
    JpushSender sender = JpushSender.getSender();
    // 发送消息并获取消息发送结果
    PushResult result = sender.sendMessage(params);
</pre>

## 更多实例请参考src/test/java/com/zheng/msg/MsgTest.java类