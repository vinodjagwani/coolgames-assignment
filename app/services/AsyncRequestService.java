package services;

import org.apache.commons.lang3.StringUtils;
import play.libs.concurrent.Futures;
import play.libs.ws.WSClient;
import play.libs.ws.WSResponse;
import play.mvc.Result;
import play.mvc.Results;
import utils.Urls;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

/**
 * Created by Vinod on 6/29/17.
 * This is service layer for constructing logic.
 */

@Singleton
public class AsyncRequestService extends Results {

    @Inject
    private WSClient wsClient;

    public CompletionStage<List<WSResponse>> results() {
        List<CompletionStage<WSResponse>> wsResponses = new ArrayList<>();
        Urls.urls.forEach(url -> {
            CompletionStage<WSResponse> wsResponse = wsClient.url(url).get();
            wsResponses.add(wsResponse);
        });
        CompletionStage<List<WSResponse>> result = Futures.sequence(wsResponses);
        return result;
    }

    public CompletionStage<Result> responseBody(CompletionStage<List<WSResponse>> response) {
        return response.thenApplyAsync(responses -> ok(
                StringUtils.join(responses.stream().map(body -> body.getBody()).collect(Collectors.toList()))
        ));
    }

}
