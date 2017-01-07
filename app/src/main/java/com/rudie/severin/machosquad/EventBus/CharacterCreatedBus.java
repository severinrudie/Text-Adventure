package com.rudie.severin.machosquad.EventBus;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by erikrudie on 1/6/17.
 */

public class CharacterCreatedBus {

  private PublishSubject<Boolean> subject = PublishSubject.create();
  private static CharacterCreatedBus bus;

  public static CharacterCreatedBus getInstance() {
    if (bus == null) {
      bus = new CharacterCreatedBus();
    }
    return bus;
  }

  public void characterCreated() {
    subject.onNext(true);
  }

  public Observable<Boolean> getSubject() {
    return subject;
  }

}
