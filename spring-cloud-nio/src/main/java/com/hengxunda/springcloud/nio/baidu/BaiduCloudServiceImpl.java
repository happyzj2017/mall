package com.hengxunda.springcloud.nio.baidu;

import com.baidubce.services.lss.LssClient;
import com.baidubce.services.lss.model.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Slf4j
@Service
public class BaiduCloudServiceImpl implements BaiduCloudService {

    private LssClient client;

    @PostConstruct
    public void postConstruct() {
        client = BaiduCloud.LSS_CLIENT;
    }

    @Override
    public CreateStreamResponse createPushStream(String yourPlayDomain, String yourApp, String yourStream) {
        CreateStreamRequest request = new CreateStreamRequest();
        request
                // yourPlayDomain是用户提前创建的播放域名
                .withPlayDomain(yourPlayDomain)
                // yourApp是用户自定义的app名称
                .withApp(yourApp)
                // yourStream是用户自定义的Stream名称
                .withPublish(new CreateStreamRequest.PublishInfo().withPushStream(yourStream));

        return client.createStream(request);
    }

    @Override
    public ListStreamResponse listStream(String yourPlayDomain, String status) {
        ListStreamRequest request = new ListStreamRequest();
        request.withPlayDomain(yourPlayDomain)
                // status表示Stream的状态,有效值有READY、ONGOING、PAUSED
                .withStatus(status);
        return client.listStream(request);
    }

    @Override
    public GetStreamResponse getStream(String yourPlayDomain, String yourApp, String yourStream) {
        GetStreamRequest request = new GetStreamRequest();
        request.withPlayDomain(yourPlayDomain).withApp(yourApp).withStream(yourStream);
        return client.getStream(request);
    }
}
