package pl.goeuropa.websocketconverter.client;


import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import pl.goeuropa.websocketconverter.utils.StompSessionHolder;


import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;


@Slf4j
@Component
public class ConnectManager {

    private final WebSocketStompClient stompClient;
    private final StompSessionHolder stompSessionHolder;


    private final static String URL = "wss://dslocate.datasystem.pl/fleetservice";

    private final long TIMEOUT = 5L;

    private final StompSessionHandler sessionHandler = new StompSessionHandler();

    public ConnectManager(WebSocketStompClient stompClient, StompSessionHolder stompSessionHolder) {
        this.stompClient = stompClient;
        this.stompSessionHolder = stompSessionHolder;
    }

    @PostConstruct
    public String connect() throws Exception {

        CompletableFuture<StompSession> stompSessionFuture = stompClient.connectAsync(URL, sessionHandler);

        try {
            StompSession stompSession = stompSessionFuture.get(TIMEOUT,TimeUnit.SECONDS);
            stompSessionHolder.setStompSession(stompSession);

            return "Successfully connected to the dslocate network.";
        } catch (Exception ex) {
            log.error("Unable to connect to the dslocate network", ex);
            return "";
        }

    }

    private class StompSessionHandler extends StompSessionHandlerAdapter {

        @Override
        public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
            connectedHeaders.add("login", "goeuropa");
            connectedHeaders.add("passcode", "827ccb0eea8a706c4c34a16891f84e7b");
            log.info("Session run : {}", session.getSessionId());
            session.subscribe("/vehicles/positions", sessionHandler);
        }

        @Override
        public Type getPayloadType(StompHeaders headers) {
            return Message.class;
        }

        @Override
        public void handleFrame(StompHeaders headers, Object payload) {
            log.info("------------ Received : -------------");
            System.out.println(((Message) payload).getVehicles().toString());
        }

        @Override
        public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
            throw new RuntimeException(exception);
        }
    }
}
