package com.rudie.severin.machosquad.EventBus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by erikrudie on 1/6/17.
 */

public final class RefreshLoadListBus {

  // private constructor to prevent instantiation (Singleton)
  private RefreshLoadListBus() {
  }

  private PublishSubject<Boolean> subject = PublishSubject.create();
  private static RefreshLoadListBus bus;

  public static RefreshLoadListBus getInstance() {
    if (bus == null) {
      bus = new RefreshLoadListBus();
    }
    return bus;
  }

  public void refreshLoadList() {
    subject.onNext(true);
  }

  public Observable<Boolean> getSubject() {
    return subject;
  }

}
