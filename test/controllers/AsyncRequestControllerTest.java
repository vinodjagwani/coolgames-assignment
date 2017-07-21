package controllers;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import static org.mockito.Matchers.*;
import play.libs.ws.WSResponse;
import services.AsyncRequestService;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
/**
 * Created by Vinod on 6/29/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class AsyncRequestControllerTest {

    @Mock
    private AsyncRequestService asyncRequestService;

    @InjectMocks
    private AsyncRequestController asyncRequestController;

    @BeforeClass
    public static void setUp() {
        MockitoAnnotations.initMocks(AsyncRequestController.class);
    }

    @Test
    public void testLoad() {
        CompletionStage<List<WSResponse>> result = new CompletableFuture<>();
        result.thenApplyAsync(response -> response.add(Mockito.mock(WSResponse.class)));
        Mockito.when(asyncRequestService.results()).thenReturn(result);
        Mockito.when(asyncRequestService.responseBody(any(CompletionStage.class))).thenReturn(result);
        asyncRequestController.load();
        Mockito.verify(asyncRequestService, Mockito.times(1)).results();
        Mockito.verify(asyncRequestService, Mockito.times(1)).responseBody(result);
    }

}
