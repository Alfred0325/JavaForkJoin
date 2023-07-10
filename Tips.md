In functionBLAFair_getavoid：
> 1. 在p, q循环内每一次都提交到forkjoinpool任务太慢了，原因是每次提交任务（即使是空任务）也需要9ms左右的时间， 而在p，q循环内执行一次不到 1 ms（0 ms）
