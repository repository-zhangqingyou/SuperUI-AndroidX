package com.zqy.sutils.glide.manager;


import com.zqy.sutils.glide.RequestManager;

import java.util.Collections;
import java.util.Set;

/**
 * A {@link com.zqy.sutils.glide.manager.RequestManagerTreeNode} that returns no relatives.
 */
final class EmptyRequestManagerTreeNode implements RequestManagerTreeNode {

    @Override
    public Set<RequestManager> getDescendants() {
        return Collections.emptySet();
    }
}
