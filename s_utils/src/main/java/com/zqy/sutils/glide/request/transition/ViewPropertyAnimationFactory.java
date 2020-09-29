package com.zqy.sutils.glide.request.transition;

import com.zqy.sutils.glide.load.DataSource;

/**
 * A {@link com.zqy.sutils.glide.request.transition.TransitionFactory} that produces ViewPropertyAnimations.
 *
 * @param <R> The type of the resource that will be transitioned into a view.
 */
public class ViewPropertyAnimationFactory<R> implements TransitionFactory<R> {
  private final com.zqy.sutils.glide.request.transition.ViewPropertyTransition.Animator animator;
  private com.zqy.sutils.glide.request.transition.ViewPropertyTransition<R> animation;

  public ViewPropertyAnimationFactory(com.zqy.sutils.glide.request.transition.ViewPropertyTransition.Animator animator) {
    this.animator = animator;
  }

  /**
   * Returns a new {@link com.zqy.sutils.glide.request.transition.Transition} for the given arguments. If isMemoryCache is {@code true} or
   * isFirstImage is {@code false}, returns a {@link com.zqy.sutils.glide.request.transition.NoTransition} and otherwise returns a new
   * {@link com.zqy.sutils.glide.request.transition.ViewPropertyTransition} for the {@link com.zqy.sutils.glide.request.transition.ViewPropertyTransition.Animator} provided in the
   * constructor.
   */
  @Override
  public Transition<R> build(DataSource dataSource, boolean isFirstResource) {
    if (dataSource == DataSource.MEMORY_CACHE || !isFirstResource) {
      return NoTransition.get();
    }
    if (animation == null) {
      animation = new ViewPropertyTransition<>(animator);
    }

    return animation;
  }
}
