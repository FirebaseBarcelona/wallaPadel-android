package firebasebarcelona.firepadel.domain.cases;

import firebasebarcelona.firepadel.data.chat.repository.ChatRepository;
import firebasebarcelona.firepadel.domain.models.Message;
import java.lang.ref.WeakReference;
import java.util.List;
import javax.inject.Inject;

public class GetChatMessagesByCourtIdUseCase extends AbstractUseCase {
  private final ChatRepository chatRepository;
  private String courtId;
  private WeakReference<OnMessagesReadyCallback> callbackRef;

  @Inject
  public GetChatMessagesByCourtIdUseCase(ChatRepository chatRepository) {
    this.chatRepository = chatRepository;
  }

  public void execute(String courtId, OnMessagesReadyCallback callback) {
    this.courtId = courtId;
    this.callbackRef = new WeakReference<>(callback);
    launch();
  }

  @Override
  protected void onRun() {
    chatRepository.getMessages(courtId, new OnMessagesReadyCallback() {
      @Override
      public void onMessageReady(final List<Message> messages) {
        final OnMessagesReadyCallback callback = callbackRef.get();
        if (callback != null) {
          launchOnMainThread(new Runnable() {
            @Override
            public void run() {
              callback.onMessageReady(messages);
            }
          });
        }
      }
    });
  }

  public interface OnMessagesReadyCallback {
    void onMessageReady(List<Message> messages);
  }
}
