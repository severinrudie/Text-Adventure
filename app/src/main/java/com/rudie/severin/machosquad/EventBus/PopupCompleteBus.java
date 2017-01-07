package com.rudie.severin.machosquad.EventBus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by erikrudie on 1/6/17.
 */

public final class PopupCompleteBus {

  // private constructor to prevent instantiation (Singleton)
  private PopupCompleteBus() {
  }

  private PublishSubject<Boolean> subject = PublishSubject.create();
  private static PopupCompleteBus bus;

  public static PopupCompleteBus getInstance() {
    if (bus == null) {
      bus = new PopupCompleteBus();
    }
    return bus;
  }

  public void popupIsComplete() {
    subject.onNext(true);
  }

  public Observable<Boolean> getSubject() {
    return subject;
  }

}
