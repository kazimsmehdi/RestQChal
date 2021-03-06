package dk.ksm.fstask.service.broadcaster;

import org.eclipse.jetty.websocket.api.Session;
import org.eclipse.jetty.websocket.api.WebSocketAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

public class BroadcastSocket extends WebSocketAdapter {

    private static final Logger log = LoggerFactory.getLogger(BroadcastSocket.class);

    private static Set<Session> sessions = new CopyOnWriteArraySet<>();

    public static void broadcast(String text) {
        sessions.forEach(session -> {
            try {
                session.getRemote().sendString(text);
            } catch (IOException e) {
                log.error("Problem broadcasting message", e);
            }
        });
    }

    @Override
    public void onWebSocketConnect(Session session) {
        super.onWebSocketConnect(session);
        sessions.add(session);
        log.info("Socket Connected: {}", Integer.toHexString(session.hashCode()));
    }

    @Override
    public void onWebSocketClose(int statusCode, String reason) {
        sessions.remove(getSession());
        super.onWebSocketClose(statusCode, reason);
        log.info("Socket Closed: [{}] {}", statusCode, reason);
    }

    @Override
    public void onWebSocketError(Throwable cause) {
        super.onWebSocketError(cause);
        log.error("Websocket error", cause);
    }

    @Override
    public void onWebSocketText(String message) {
        log.info("Got text {} from {}", message, Integer.toHexString(getSession().hashCode()));
    }
}
