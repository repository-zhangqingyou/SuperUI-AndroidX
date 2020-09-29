package com.zqy.sutils.glide.request.transition;

import com.zqy.sutils.glide.load.DataSource;
import com.zqy.sutils.glide.util.Synthetic;

/**
 * A simple {@link com.zqy.sutils.glide.request.transition.Transition} that performs no actions.
 *
 * @param <R> the resource type that will be transitioned into a
 * {@link com.zqy.sutils.glide.request.target.Target}.
 */
public class NoTransition<R> implements com.zqy.sutils.glide.request.transition.Transition<R> {
  @Synthetic static final NoTransition<?> NO_ANIMATION = new NoTransition<>();
  @SuppressWarnings("rawtypes")
  private static final TransitionFactory<?> NO_ANIMATION_FACTORY = new NoAnimationFactory();

  /**
   * A factory that always returns the same {@link NoTransition}.
   *
   * @param <R> the resource type that will be transitioned into a
   * {@link com.zqy.sutils.glide.request.target.Target}.
   */
  public static class NoAnimationFactory<R> implements TransitionFactory<R> {
    @SuppressWarnings("unchecked")
    @Override
    public com.zqy.sutils.glide.request.transition.Transition<R> build(DataSource dataSource, boolean isFirstResource) {
      return (com.zqy.sutils.glide.request.transition.Transition<R>) NO_ANIMATION;
    }
  }

  /**
   * Returns an instance of a factory that produces {@link NoTransition}s.
   */
  @SuppressWarnings("unchecked")
  public static <R> TransitionFactory<R> getFactory() {
    return (TransitionFactory<R>) NO_ANIMATION_FACTORY;
  }

  /**
   * Returns an instance of {@link NoTransition}.
   */
  @SuppressWarnings("unchecked")
  public static <R> com.zqy.sutils.glide.request.transition.Transition<R> get() {
    return (Transition<R>) NO_ANIMATION;
  }

  /**
   * Performs no animation and always returns {@code false}.
   */
  @Override
  public boolean transition(Object current, ViewAdapter adapter) {
    return false;
  }
}
