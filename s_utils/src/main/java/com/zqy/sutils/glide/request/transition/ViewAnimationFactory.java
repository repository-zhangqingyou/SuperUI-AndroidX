package com.zqy.sutils.glide.request.transition;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.zqy.sutils.glide.load.DataSource;

/**
 * A {@link com.zqy.sutils.glide.request.transition.TransitionFactory} that produces {@link com.zqy.sutils.glide.request.transition.ViewTransition}s.
 *
 * @param <R> The type of the resource that will be transitioned into a view.
 */
public class ViewAnimationFactory<R> implements TransitionFactory<R> {
  private final com.zqy.sutils.glide.request.transition.ViewTransition.ViewTransitionAnimationFactory viewTransitionAnimationFactory;
  private com.zqy.sutils.glide.request.transition.Transition<R> transition;

  // Public API.
  @SuppressWarnings("unused")
  public ViewAnimationFactory(Animation animation) {
    this(new ConcreteViewTransitionAnimationFactory(animation));
  }

  public ViewAnimationFactory(int animationId) {
    this(new ResourceViewTransitionAnimationFactory(animationId));
  }

  ViewAnimationFactory(
      com.zqy.sutils.glide.request.transition.ViewTransition.ViewTransitionAnimationFactory viewTransitionAnimationFactory) {
    this.viewTransitionAnimationFactory = viewTransitionAnimationFactory;
  }

  /**
   * Returns a new {@link com.zqy.sutils.glide.request.transition.Transition} for the given arguments. If isFromMemoryCache is {@code true}
   * or isFirstImage is {@code false}, returns a {@link com.zqy.sutils.glide.request.transition.NoTransition} and otherwise returns a new
   * {@link com.zqy.sutils.glide.request.transition.ViewTransition}.
   *
   * @param dataSource {@inheritDoc}
   * @param isFirstResource   {@inheritDoc}
   */
  @Override
  public Transition<R> build(DataSource dataSource, boolean isFirstResource) {
    if (dataSource == DataSource.MEMORY_CACHE || !isFirstResource) {
      return NoTransition.get();
    }

    if (transition == null) {
      transition = new com.zqy.sutils.glide.request.transition.ViewTransition<>(viewTransitionAnimationFactory);
    }

    return transition;
  }

  private static class ConcreteViewTransitionAnimationFactory implements com.zqy.sutils.glide.request.transition.ViewTransition
      .ViewTransitionAnimationFactory {
    private final Animation animation;

    ConcreteViewTransitionAnimationFactory(Animation animation) {
      this.animation = animation;
    }

    @Override
    public Animation build(Context context) {
      return animation;
    }
  }

  private static class ResourceViewTransitionAnimationFactory implements ViewTransition
      .ViewTransitionAnimationFactory {
    private final int animationId;

    ResourceViewTransitionAnimationFactory(int animationId) {
      this.animationId = animationId;
    }

    @Override
    public Animation build(Context context) {
      return AnimationUtils.loadAnimation(context, animationId);
    }
  }
}
