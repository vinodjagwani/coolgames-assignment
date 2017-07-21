package controllers;

import org.apache.commons.lang3.StringUtils;
import play.libs.ws.WSResponse;
import play.mvc.Controller;
import play.mvc.Result;
import services.AsyncRequestService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;
import java.util.concurrent.CompletionStage;
import java.util.stream.Collectors;

/**
 * Created by Vinod on 6/29/17.
 * This is controller for displaying combined result on the page.
 */


@Singleton
public class AsyncRequestController extends Controller {

    private AsyncRequestService asyncRequestService;

    @Inject
    public AsyncRequestController(AsyncRequestService asyncRequestService) {
        this.asyncRequestService = asyncRequestService;
    }

    public CompletionStage<Result> load() {
        CompletionStage<List<WSResponse>> response = asyncRequestService.results();
        return asyncRequestService.responseBody(response);
    }

}
